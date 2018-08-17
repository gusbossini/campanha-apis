package br.com.gustavo.campanha.service;

import br.com.gustavo.campanha.model.ApiError;
import br.com.gustavo.campanha.model.Campanha;
import br.com.gustavo.campanha.model.Usuario;
import br.com.gustavo.campanha.repository.CampanhaRepository;
import br.com.gustavo.campanha.repository.UsuarioRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private static final String URL_CAMPANHAS = "http://localhost:8080/campanhas/";

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CampanhaRepository campanhaRepository;

    /**
     * Essa parte ficou bem confusa na documentacao por isso coloquei partes do enunciado do exercicio aqui:
     * <p>
     * 1* Se for usuário novo: Após o cadastramento do usuário, o sistema deverá solicitar as campanhas ativas para
     * aquele time do coração e efetuar a associação;
     * <p>
     * 2* Dado um E-mail que já existe, informar que o cadastro já foi efetuado,
     * <p>
     * 3* porém, caso o cliente não tenha nenhum campanha associada, o serviço deverá enviar as novas campanhas
     * como resposta;
     * <p>
     * O ITEM 4 ABAIXO CONFLITA COM A REGRA DO ITEM 2, PORTANTO FAREI A REGRA DO ITEM 4 NA CONTROLLER EM OUTRA URL
     * 4* Se for um usuário já cadastrado: Deverá ser feita a associação das campanhas novas, ou seja, as que o usuário
     * daquele time do coração não tem relacionamento até o momento.
     *
     * @param usuario
     * @return
     * @throws IOException
     */
    public ResponseEntity<?> create(Usuario usuario) throws IOException {
        Optional<Usuario> usuarioOptional = findById(usuario.getEmail());
        if (usuarioOptional.isPresent()) {
            Usuario usuarioBase = usuarioOptional.get();
            if (usuarioBase.getCampanhas().isEmpty()) {
                // 3*
                return buscarCampanhasResponse(usuarioBase.getTimeCoracao().getId());
            } else {
                // 2*
                return new ResponseEntity<>(new ApiError("Email ja cadastrado"), HttpStatus.BAD_REQUEST);
            }
        }
        // 1*
        return new ResponseEntity<>(save(usuario), HttpStatus.OK);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    private Usuario save(Usuario usuario) throws IOException {
        // solucao muito feia mas necessaria para nao travar o cadastro quando o sistema de campanhas estiver DOWN
        try {
            List<Campanha> campanhas = buscarCampanhasEntity(usuario.getTimeCoracao().getId());
            campanhaRepository.saveAll(campanhas);
            usuario.setCampanhas(campanhas);
        } catch (ResourceAccessException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return usuarioRepository.save(usuario);
    }

    /**
     * Se for um usuário já cadastrado: Deverá ser feita a associação das campanhas novas, ou seja,
     * as que o usuário daquele time do coração não tem relacionamento até o momento.
     *
     * @param email
     * @return
     * @throws IOException
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Usuario associarCampanhasUsuarioJaExistente(String email) throws IOException, NoSuchElementException {
        Usuario usuario = findById(email).orElseThrow(() -> new NoSuchElementException());
        List<Campanha> campanhas = buscarCampanhasEntity(usuario.getTimeCoracao().getId());

        List<Campanha> campanhasUsuario = usuario.getCampanhas();
        List<Campanha> campanhasNaoAssociadas = campanhas.stream()
                .filter(c -> !campanhasUsuario.contains(c))
                .collect(Collectors.toList());

        campanhaRepository.saveAll(campanhasNaoAssociadas);
        usuario.getCampanhas().addAll(campanhasNaoAssociadas);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findById(String email) {
        return usuarioRepository.findById(email);
    }

    /**
     * Busca as campanhas via rest api e retorna o objeto JAVA
     *
     * @param idTimeCoracao
     * @return
     * @throws IOException
     */
    private List<Campanha> buscarCampanhasEntity(Long idTimeCoracao) throws IOException {
        return new ObjectMapper().readValue(getCampanhas(idTimeCoracao).getBody(), new TypeReference<List<Campanha>>() {});
    }

    /**
     * Busca as campanhas via rest api e retorna JSON
     *
     * @param idTimeCoracao
     * @return
     * @throws IOException
     */
    private ResponseEntity<?> buscarCampanhasResponse(Long idTimeCoracao) throws IOException {
        return new ResponseEntity<>(new ObjectMapper().readTree(getCampanhas(idTimeCoracao).getBody()), HttpStatus.OK);
    }

    /**
     * Responsavel por enviar a requisicao api e retornar o objeto JSON mapeado para String
     *
     * @param idTimeCoracao
     * @return
     */
    private ResponseEntity<String> getCampanhas(Long idTimeCoracao) {
        String campanhasUrl = URL_CAMPANHAS + idTimeCoracao;
        return new RestTemplate().getForEntity(campanhasUrl, String.class);
    }

}
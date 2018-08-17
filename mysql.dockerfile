FROM mysql
ADD init.sql /docker-entrypoint-initdb.d/cargainicial.sql
RUN /etc/init.d/mysql start
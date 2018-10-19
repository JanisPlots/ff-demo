FROM postgres:9.6

COPY docker-entrypoint-initdb.d/*.sql /docker-entrypoint-initdb.d/
RUN echo "host    all             all             0.0.0.0/0               md5" | tee -a /var/lib/postgresql/data/pg_hba.conf

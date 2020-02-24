FROM mysql
# allow no password
ENV MYSQL_ALLOW_EMPTY_PASSWORD yes

# copy file into container
COPY my.conf /etc/my.conf
COPY setup.sh /mysql/setup.sh
COPY schema.sql /mysql/schema.sql
COPY privileges.sql /mysql/privileges.sql

# exec these command when container start up
CMD ["sh", "/mysql/setup.sh"]
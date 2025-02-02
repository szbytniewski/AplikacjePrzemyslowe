FROM mysql:8

# Set MySQL environment variables
ENV MYSQL_ROOT_PASSWORD=rootpassword
ENV MYSQL_DATABASE=ecommerce_db
ENV MYSQL_USER=ecommerce_user
ENV MYSQL_PASSWORD=ecommerce_pass

# Expose MySQL port
EXPOSE 3306

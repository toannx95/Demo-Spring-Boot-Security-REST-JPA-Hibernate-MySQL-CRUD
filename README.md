# Spring-Boot-Security-REST-JPA-Hibernate-MySQL-CRUD
Demo-Spring-Boot-Security-REST-JPA-Hibernate-MySQL-CRUD

# Software Used
1. Java 8 
2. Spring Boot 1.5.10.RELEASE 
3. Maven
4. MySQL 
5. Spring Tool Suite

# Database name: securitydb
# Dummy data:
  INSERT INTO `account` (`username`, `password`, `name`, `role`, `email`, `active`) VALUES
	('admin', '$2a$04$zT8wJi0sQokyRlWPEnsmNe2tNCMFge2h.xodnFehMQxjvRG0PZ2Sa', 'admin', 'ROLE_ADMIN', 'admin@gmail.com', true),
	('user', '$2a$04$KhxdnLl4Qrm7mkOhWXnxjeGpX41mDQmEjqj460vCO7vB4ZcZZVYzi', 'user', 'ROLE_USER', 'user@gmail.com', true); 

# Online Bcrypt Hash Generator and Checker:
http://www.devglan.com/online-tools/bcrypt-hash-generator


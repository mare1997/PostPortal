#MySQL
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
spring.datasource.password          = root
spring.datasource.url               = jdbc:mysql://localhost:3306/postportal?useSSL=false
spring.datasource.username          = root

logging.level.org.hibernate.SQL=debug

spring.jpa.hibernate.ddl-auto=create

spring.mvc.view.prefix: /WEB-INF/jsp/
spring.mvc.view.suffix: .jsp

logging.level.org.springframework.web=INFO
logging.level.osa.newsproject.controller=DEBUG
logging.level.org.hibernate=ERROR
logging.file=logs/spring-boot-logging.log
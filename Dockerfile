FROM openjdk:14  
VOLUME /tmp
EXPOSE 8080
ADD target/springbootpostgresqldocker.jar springbootpostgresqldocker.jar
ENTRYPOINT ["java","-jar","/springbootpostgresqldocker.jar"]
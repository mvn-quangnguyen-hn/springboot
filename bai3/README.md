### To enable support for mysql, we need to add `spring-boot-starter-data-jpa` and `mysql-connector-java` starter in the `pom.xml`.
- Spring Boot auto-configure DataSource if `spring-boot-starter-data-jpa` is in the class-path by reading configurations defined in the **application.properties**.

### Hibernate
ddl values:
```
- none: The default for MySQL. We make no change to the db structure.
- udate: Hibernate changes the db according to the entity structures.
- create: Creates the db every time but does not drop it on close.
- create-drop: Creates the db and drops it when SessionFactory closes.
```

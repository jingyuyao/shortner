# Shortner

Prerequisites
---
- Maven
- Java 8
- Mysql (run db_setup.sh after installation)
- Lombok plugin for IDE of your choice

How to start the Shortner application
---

1. Run `mvn clean package` to build your application
2. (First run or after schema update) `./shortner.sh db migrate config.yml`
3. Start application with `./shortner.sh server config.yml`
4. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

Dependency Injection
---

We chose to use Guice to do application specific injections.
These injection should be constructor injections.
JAX-RS specific objects should be injected via method or parameter injections.

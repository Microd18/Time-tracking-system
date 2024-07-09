# Система мониторинга времени выполнения методов

## Описание проекта:
Система мониторинга времени выполнения методов в приложении с использованием Spring Boot и Spring AOP.
Данная система позволяет синхронно и асинхронно логировать и анализировать данные о времени выполнения методов, помеченных специальными аннотациями.

## REST API
URL: http://localhost:8080

- GET /time_track/all - получение всех данных учета времени выполнения методов по всем методам
- GET /time_track/sync - получение статистики по среднему и общему времени выполнения методов, выполняемых синхронно
- GET /time_track/async - получение статистики по среднему и общему времени выполнения методов, выполняемых асинхронно

## Запуск приложения:
- клонировать проект в среду разработки;
- настроить подключение к базе данных в файле application.properties;
- аннотировать методы аннотациями @TrackTime и @TrackAsyncTime, время выполнения которых нужно отслеживать.
- запустить метод main в файле MethodExecutionTimeTrackingSystemApplication.java

В корне проекта доступен [OpenAPI](./api-docs.yaml) с которым можно работать через [Swagger Editor](https://editor.swagger.io/) 
## Технологии, используемые в проекте:
- Java 17;
- Spring Boot;
- Spring AOP;
- Spring Data JPA;
- SpringDoc;
- Maven;
- REST API;
- Lombok;
- PostgreSQL,
- Liquibase.
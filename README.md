# Magalu Communication Scheduler

## Visão Geral

Este projeto é uma API RESTful desenvolvida em Java utilizando o Spring Boot, que permite agendar envios de comunicação via email, SMS, push e WhatsApp. Os agendamentos são armazenados em um banco de dados MySql e podem ser consultados ou removidos.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.3.0
- Spring Data JPA
- MySQL
- Maven

## Pré-requisitos

- Java 21
- MySQL

## Configuração do Banco de Dados

1. Instale o MySQL e crie um banco de dados chamado `magaludb`.
2. Configure as credenciais do banco de dados no arquivo `src/main/resources/application.yml`:

    ```
    spring:
      datasource:
        url: jdbc:mysql://${MYSQL_HOST:localhost}:3306/magaludb
        username: admin
        password: 123
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true
    ```

## Executando a Aplicação

Clone o repositório e navegue até o diretório do projeto:

```bash
git clone https://github.com/btoledo90/magalu.git
cd magalu
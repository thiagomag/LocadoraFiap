# LocadoraVeiculosFiap

Este projeto é um sistema de gestão para locadoras de automóveis, desenvolvido para automatizar e otimizar o processo de aluguel de veículos, focando na administração da frota, cadastro de clientes, controle de reservas e devoluções.

## Índice

- [Introdução](#introdução)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Instalação](#instalação)
- [Uso](#uso)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Endpoints Principais](#endpoints-principais)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Introdução

A **LocadoraVeiculosFiap** é uma API RESTful que gerencia os processos essenciais de uma locadora de veículos, incluindo o cadastro de clientes e veículos, criação e confirmação de reservas e o controle de devolução dos veículos. Este sistema foi desenvolvido com uma abordagem de Domain-Driven Design (DDD) para facilitar a manutenção e a evolução da aplicação.

## Funcionalidades

- Cadastro e gerenciamento de clientes, incluindo verificação de idade mínima.
- Registro e controle da frota de veículos, incluindo disponibilidade e manutenção.
- Criação, confirmação e cancelamento de reservas.
- Gestão do ciclo completo de uma locação, desde a reserva até a devolução.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot** - para criação de APIs RESTful
- **Spring Data JPA** - para gerenciamento de persistência
- **H2 Database** - banco de dados em memória para desenvolvimento e testes
- **Swagger** - documentação interativa da API
- **Maven** - gerenciamento de dependências e build

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/thiagomag/LocadoraVeiculosFiap.git

2. Navegue até o diretório do projeto:
~~~
cd LocadoraVeiculosFiap
~~~

3. Compile e execute a aplicação usando Maven:
~~~
mvn spring-boot:run
~~~

4. Acesse a documentação da API: Abra o navegador e acesse [Swagger UI](http://localhost:8080/swagger-ui.html) para visualizar e testar os endpoints.

## Uso
Após iniciar o servidor, você pode utilizar ferramentas como Postman ou o próprio Swagger para interagir com a API. Abaixo estão listados os principais endpoints.

## Estrutura do Projeto
O projeto segue uma estrutura de Domain-Driven Design (DDD), onde cada agregado representa um contexto específico do domínio:

- Cliente: gerenciamento de informações dos clientes.
- Veículo: controle dos veículos, incluindo status de disponibilidade.
- Reserva: criação e controle de reservas.

## Endpoints Principais
### Cliente
- POST /clientes - Registra um novo cliente.
- GET /clientes/{id} - Retorna os dados de um cliente específico.
- PUT /clientes/{id} - Atualiza as informações de um cliente.
### Veículo
- POST /veiculos - Registra um novo veículo.
- GET /veiculos/{id} - Retorna os dados de um veículo específico.
- PUT /veiculos/{id} - Atualiza as informações de um veículo.
### Reserva
- POST /reservas - Cria uma nova reserva.
- PUT /reservas/{id}/confirmar - Confirma uma reserva.
- PUT /reservas/{id}/finalizar - Finaliza uma reserva, marcando a devolução do veículo.
Para mais detalhes sobre cada endpoint, incluindo parâmetros e respostas, consulte o [Swagger UI](http://localhost:8080/swagger-ui.html).

## Contribuição
Contribuições são bem-vindas! Siga os passos abaixo para contribuir com este projeto:

1. Fork o projeto.
2. Crie uma nova branch para sua feature (git checkout -b feature/MinhaFeature).
3. Commit suas mudanças (git commit -m 'Adiciona MinhaFeature').
4. Push para a branch (git push origin feature/MinhaFeature).
5. Abra um Pull Request.

## Licença
Este projeto é licenciado sob a Licença MIT. Consulte o arquivo LICENSE para mais detalhes.

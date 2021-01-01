# API-REST-Desafio-Orange-Talents
 🔨 🔧API REST que precisa suportar o processo de abertura de nova conta no banco. 
 
 ## Cadastrar os dados pessoais de uma pessoa. 
 
* Nome
* E-mail
*	CPF
*	Data de nascimento

## Tecnologias do mundo Spring 

* Maven Project: Ferramenta de gerenciamento de dependências utilizada para java

* Linguagem: Java 11 / versão LTS (Long Team Suport)  LTS é a versão que você paga para ter um longo período de serviço sem precisar atualizar a plataforma. Ela é ideal para empresas de grande porte e que não desejam ficar atualizando o programa a cada seis meses, quando um novo pacote de mudanças é implementado.

* Spring Boot Version: 2.3.7

* Dependencies:
Spring Web: Crie aplicativos da web, incluindo RESTful, usando Spring MVC. Usa Apache Tomcat como o contêiner embutido padrão.

* Spring Data JPA: Especificação da biblioteca padrão de persistência de dados no java, baseado no mapeamento objeto relacional (javax.percistence)

* Hibernate: É uma das implementações da especificação JPA mais popular

* H2: Banco de dados em memória, para testes

* PostgreSQL: É um sistema de gerenciamento de banco de dados que é objeto relacional, que permite que programas Java se conectem a um banco de dados PostgreSQL usando código Java padrão independente de banco de dados

* Bean Validation with Hibernate validator, biblioteca de validação

## Referências sobre Beans Validation

[Jakarta Bean Validation](https://beanvalidation.org/)

[Jakarta Bean Validation API 2.0.2](https://docs.jboss.org/hibernate/beanvalidation/spec/2.0/api/overview-summary.html)

[Package javax.validation.constraints](https://docs.jboss.org/hibernate/beanvalidation/spec/2.0/api/javax/validation/constraints/package-summary.html)

## Ferramentas:

* Postman: É uma plataforma de colaboração para desenvolvimento de API. Testar as requisições e criar um ambiente de produção.

## Abordagem e o processo de decisão para realizar a implementação

* Web Service: Aplicação rodando no navegador do usuário, com muito mais responsabilidade, onde a própria aplicação que estiver rodando no navegador, vai ser responsável por montar as telas. O sistema vai responder somente uma estrutura de dados no padrão JSON

* Parâmetros de rotas @PathVariable
* Parâmetros de requisição @RequestParam
* Corpo de requisição @RequestBody
* Resposta da Requisição ResponseEntity<T>

## Desenvolvimento do sistema e estrutura:

* Padrão de divisão do Backend em camadas lógicas, para organizar o sistema e deixando flexível e de fácil manutenção







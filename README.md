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

* Padrão de divisão do Backend em camadas lógicas, para organizar o sistema, deixando flexível e de fácil manutenção.

![alt text](https://github.com/wagnersistemalima/API-REST-Desafio-Orange-Talents/blob/main/imagens/camadas.jpg)

## Classes criadas no processo:

### Resource: ClientResource (rest controller)

* É a camada de recursos Web, que é implementada por um controlador Rest, obedecendo boas práticas de desenvolvimento.Esta camada vai ser responsável por receber as requisições da aplicação, e encaminhar as chamadas para os serviços responsáveis por realizar aquela ação do usuário de acordo com o comportamento do sistema.

### Service: ClientService

* Camada de serviço vai ter a lógica de negócio do sistema, é nela que iremos fazer algum cálculo, verificação, orquestração de alguma operação, a ordem que tem que acontecer as coisas, é nesta camada que teremos toda regra de negócio

* @Transactional(readOnly = true): Utilizei essa anotação do spring,  para garantir a integridade do banco de dados, onde o próprio framework vai tratar de envolver a transação com o banco de dados. Como é uma operação só de leitura  coloquei um parâmetro para evitar que faça um look no banco, pois não precisamos travar o banco de dados só para ler os dados, essa anotação vai melhorar a performance do banco.

### Repository: ClientRepository (data repositories)

## A camada de acesso a dados , onde irá conter somente as operações de acessar os dados no banco de dados, exemplo:

* Salvar
* Atualizar 
* Deletar
* Buscar
* Operações que vão no banco de dados e fazem alguma ação nele estarão nesta camada.

## Data Transfer Objects (DTO): ClientDTO

* Vai ser um objeto simples para carregar os dados entre o controlador Rest e a camada de serviço, e sua função será apenas para trafegar dados das entidades.
Vantagens: Dar segurança e menor tráfego, e customizar o que se quer entregar para a aplicação

* Atributos básico
* Construtor default / Construtor com argumentos
* Construtor personalizado recebendo uma entidade, para povoar o dto
* Getters & Setters
* Serializable

```
// construtor personalizado

public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		this.cpf = entity.getCpf();
		this.dataNascimento = entity.getDataNascimento();
	}
```

## Validando os dados com as anotações  Beans Validation

* @NotBlank(message = "Campo obrigatório")
* @Email(message = "Favor entrar com um email válido")
* @CPF(message = "Favor entrar com um cpf válido")
* @PastOrPresent(message = "A data de nascimento não pode ser futura")

```
import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.br.CPF;

import com.sistemalima.contabancaria.entities.Client;

public class ClientDTO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String nome;
	
	@Email(message = "Favor entrar com um email válido")
	private String email;
	
	@CPF(message = "Favor entrar com um cpf valido")
	private String cpf;
	
	@PastOrPresent(message = "A data de nascimento não pode ser futura")
	private  Instant dataNascimento;
```

### Entidades: Client

* checklist:
* Atributos básico
* Construtor default / Construtor com argumentos
* Getters & Setters
* hashCode & equals
* Serializable
* JPA mapping

```
@Entity
@Table(name = "tb_client")
public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// atributos basico
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String email;
	private String cpf;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataNascimento;
```

* Vai ser um objeto, que será monitorado e controlado, para manter a integridade do banco de dados.

## StandardError: Objeto criado no Resource para receber a exceção

* checklist:
* Atributos básico
* Construtor default 
* Getters & Setters
* Serializable

## ResourceExceptionHandller: Essa classe vai interceptar a exceção e fazer o tratamento adequado.

* Métodos para tratar as requisições e devolver as respostas adequadas

## FieldMessage : Classe auxiliar para carregar o nome do campo e as mensagem de respostas dos dados que não foram validados

* Atributos básico
* Construtor default / Construtor com argumentos
* Getters & Setters
* Serializable

## ValidationError: Essa classe vai herdar da classe StandardError

* Atributo básico , uma lista de erros.
* Getters da lista
* Método para adicionar erros

```
public class ValidationError extends StandardError{
	
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	// metodo para adicionar os erros na lista de erros
	
	public void addErro(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
```

## Classe para tratar exceções: ResourceNotfoundException herdando de RuntimeException

* Exceção personalizada da camada de serviço

## Configurar banco de dados de teste (H2)

* H2 database, perfil de teste, JPA
* checklist:
* application.properties
* application-test.properties

## application.properties:

* Perfil de teste para rodar um banco de dados provisório, para que toda vez que rodar a aplicação, o banco seja reiniciado para o estado inicial, onde podemos definir uma instância inicial para ele, vamos semear o banco de dados com alguns dados de testes, utilizando o banco de dados h2 em memória

```
spring.profiles.active=test 

```

* OBS: Como foi montado o backend do sistema em camadas, e a camada de serviço é quem conversa com a camada de acesso a dados, que por sua vez conversa com a entidade, então eu resolvi que toda parte de acessar o banco de dados, fazer transação, encerrar transação se encerre na camada de serviço, eu não posso deixar voltar para a camada do controlador com a seção do banco de dados aberta. As transações confirmadas serão encerradas na camada de serviço, e para garantir a propriedade open in view estará como false

```
spring.jpa.open-in-view=false

```

## application-test.properties:

```
application-test.properties:
spring.datasource.url=jdbc:h2:mem:testdb spring.datasource.username=sa spring.datasource.password= spring.h2.console.enabled=true spring.h2.console.path=/h2-console spring.jpa.show-sql=true spring.jpa.properties.hibernate.format_sql=true


```

## data.sql

* Seeding: Carga inicial da base de dados, inserindo 10 clientes

```
INSERT INTO tb_client (nome, email, cpf, data_nascimento) VALUES ('Maria do Bairro', 'maria@gmail.com', '111.225.333-81', TIMESTAMP WITH TIME ZONE '1981-08-08T14:15:10Z');

INSERT INTO tb_client (nome, email, cpf, data_nascimento) VALUES ('Pedro Carpinteiro', 'pedro@gmail.com', '111.222.633-81', TIMESTAMP WITH TIME ZONE '1985-08-08T14:15:10Z');

INSERT INTO tb_client (nome, email, cpf, data_nascimento) VALUES ('Antonio Pedreiro', 'antonio@gmail.com', '111.272.333-81', TIMESTAMP WITH TIME ZONE '1989-08-08T14:15:10Z');

INSERT INTO tb_client (nome, email, cpf, data_nascimento) VALUES ('Ana da Lanchonete', 'ana@gmail.com', '111.222.333-81', TIMESTAMP WITH TIME ZONE '1991-08-08T14:15:10Z');

INSERT INTO tb_client (nome, email, cpf, data_nascimento) VALUES ('Gabriela da Padaria', 'gabriela@gmail.com', '119.222.333-81', TIMESTAMP WITH TIME ZONE '1999-08-08T14:15:10Z');

INSERT INTO tb_client (nome, email, cpf, data_nascimento) VALUES ('eduardo Eletricista', 'eduardo@gmail.com', '111.222.313-81', TIMESTAMP WITH TIME ZONE '1996-08-08T14:15:10Z');

INSERT INTO tb_client (nome, email, cpf, data_nascimento) VALUES ('Jose da Tapioca', 'jose@gmail.com', '111.282.333-81', TIMESTAMP WITH TIME ZONE '1970-08-08T14:15:10Z');

INSERT INTO tb_client (nome, email, cpf, data_nascimento) VALUES ('Izabel da Costura', 'izabel@gmail.com', '151.222.333-81', TIMESTAMP WITH TIME ZONE '1988-08-08T14:15:10Z');

INSERT INTO tb_client (nome, email, cpf, data_nascimento) VALUES ('Josefa da Budega', 'josefa@gmail.com', '111.222.343-81', TIMESTAMP WITH TIME ZONE '1970-08-08T14:15:10Z');

INSERT INTO tb_client (nome, email, cpf, data_nascimento) VALUES ('Carlos Barbeiro', 'carlos@gmail.com', '131.222.333-81', TIMESTAMP WITH TIME ZONE '1965-08-08T14:15:10Z');


```

## API devolvendo a respostas inadequada para caso de falha de validação. estourando código 500, antes do tratamento


![alt text](https://github.com/wagnersistemalima/API-REST-Desafio-Orange-Talents/blob/main/imagens/3.jpg)

## Tratamento de Exceções

* Objeto não encontrado: resposta http 404, código de resposta padrão quando o usuário faz uma requisição  para encontrar um recurso que não existe


## ResourceNotfoundException herdando de RuntimeException

```
 package com.sistemalima.contabancaria.services.exceptions;

 public class ResourceNotFoundException extends RuntimeException{

	 private static final long serialVersionUID = 1L;
	
	 public ResourceNotFoundException(String msg) {
		   super(msg);
	}

}
```

## Metodo buscar cliente por id  da classe ClientService

```

// metodo buscar cliente por id

	@Transactional(readOnly = true)	
	public ClientDTO findById(Long id) {
		 Optional<Client> obj = repository.findById(id);
		 Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		 return new ClientDTO(entity);
	}

```

## Quando for chamado o serviço, e estourar a exceção personalizada, lá no Resource vou tratar a exceção, criando um objeto para receber o Timestamp, status, error, message, path

## StandardError: Objeto criado no Resource para receber a exceção

* checklist:
* Atributos básico
* Construtor default 
* Getters & Setters
* Serializable

## Manipulador de exceptions: Para que eu não tenha que ficar implementando try catch em todo método do controlador que for ter que tratar uma exceção, irei criar uma classe especial do spring ControllerAdivice. Essa classe vai interceptar a exceção e fazer o tratamento adequado.

## ResourceExceptionHandller:

```
@ControllerAdvice
public class ResourceExceptionHandler {
	
	// metodo para tratamento de erro / recurso não encontrado 404
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(status.value());
		erro.setError("Entity not found");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
```

## API devolvendo a resposta adqueda, codigo 404, o servidor não pode encontrar o recurso solicitado, entity not found.

[alt text](https://github.com/wagnersistemalima/API-REST-Desafio-Orange-Talents/blob/main/imagens/error%20404.jpg)

## Tratando exceções nos campos de cpf, email, nome, dataNascimento quando não validados, retornando resposta adequada para o usuário.

* Método da classe resourceExceptionHandller que vai tratar as exceções de validações

```
// metodo para retorna resposta http 422 / alguma entidade não foi possivel ser processada
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError erro = new ValidationError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(status.value());
		erro.setError("Validation exception");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			erro.addErro(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(erro);
	}
```

## Testando a requisição no Postman, inserindo dados inválidos, nome, cpf , email.

```
	"datanascimento" :  "1981-08-08T14:15:10Z",
        "nome" : "",
        "email" : "wagner.sistemalimagmail",
        "cpf" : "043.944.504"

```

## Resposta da requisição quando o cpf ou email ou nome não seja validado: código 422 alguma entidade não foi possível ser processada


![alt text](https://github.com/wagnersistemalima/API-REST-Desafio-Orange-Talents/blob/main/imagens/13.jpg)

## Item bônus: Proteger a aplicação de e-mail e CPF duplicados

### Classe: ClientService

* O método de inserir um novo recurso no banco de dados, verifica se já existe um cpf e email cadastrados, se existir lança uma exceção e retorna uma resposta de cod 422.

```
// metodo para inserir um cliente verificando no banco de dados, se já existe email ou cpf duplicados
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		
		List<Client> list = repository.findAll();
		Client entity = new Client();
		for (Client client: list) {
			if (!client.getEmail().equals(dto.getEmail()) && !client.getCpf().equals(dto.getCpf())) {
				entity.setNome(dto.getNome());
				entity.setEmail(dto.getEmail());
				entity.setCpf(dto.getCpf());
				entity.setDataNascimento(dto.getDatanascimento());
				entity = repository.save(entity);
			}
			else {
				throw new DataBaseException("Dados duplicados");
			}
		}
		
		return new ClientDTO(entity);
		
	}	
```

## Testando o recurso no Postman, verificando a validação no banco de dados para não houver cpf, email duplicados:

![alt text](https://github.com/wagnersistemalima/API-REST-Desafio-Orange-Talents/blob/main/imagens/testantoRequisicao.jpg)









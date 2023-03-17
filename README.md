<p align="center"><b>API REST</b> de Lanchonete<br>
	
A API Rest de Lanchonete tem o intuito de gerir a venda de lanches, possibilitando o registro de ingredientes e a criação de lanches personalizados, assim como calcular o valor do lanche e aplicando descontos promocionais para lanches com ingredientes específicos.

## Techs
  - Java 11
  - Spring Boot 2.7.2
  - MySQL
  - Hibernate
  - Spring Data JPA
  - Lombok
  - Maven
 
## Ferramentas

  - Postman
  - Intellij
  - Mysql Workbench
  
## Como rodar o projeto?

### Banco de dados:

O projeto no momento está configurado com Mysql, será necessário um banco com o nome `lanche`.

**Manualmente**

As configurações podem ser modificadas no `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/lanche
spring.datasource.username=root
spring.datasource.password=root
```

Troque o `localhost` e a porta `3306` para as equivalentes ao seu banco (assim como as credenciais `username` e `password`) antes de iniciar o projeto.

### Montando o projeto:

Baixe o projeto para seu computador através do ZIP ou do comando:

`git clone  https://github.com/ThiagoDevSantos/Lanche.git`

Na pasta do projeto execute `maven clean install` para montar o projeto baixando os arquivos necessários. Se as configurações do banco estiverem corretas, após os testes você receberá `BUILD SUCCESS`.

### Rodando o projeto:

Ainda na pasta do projeto, execute o comando `mvn spring-boot:run`.

## Banco de Dados

*Entidades:*
- ingredient (ingredientes dos lanches com seu valor por custo)
- promotion (promoções dos lanches, conforme regra de negocio)

## Requisições e Regras de Negocios               

**INSERINDO NOVO INGREDIENTE:**

adicionando pelo POST `/ingredient` da seguinte forma:
```
{
  "name": "Hambúrguer",
  "price": 3.0
}

```
**PROMOÇÕES**

A classe `PromotionService.java` gerencia a regra de negócios das promoções, que no momento são 3:


##### OBS: Utilizar o POST `/promotion` para testes dos cenarios abaixo!



*Light:* Se o lanche tem alface e não tem bacon, ganha 10% de desconto.

#### Exemplo de JSON teste :

```
{
  "ingredients": [
    {"name": "Hambúrguer", "price": 3.0},
    {"name": "Queijo", "price": 1.5},
    {"name": "Alface", "price": 0.4}
  ]
}
```

*Muita carne:* A cada 3 porções de hambúrguer o cliente só paga 2, a cada 6 porções, o cliente pagará 4 e assim sucessivamente.

#### Exemplo de JSON para teste :

```
{
  "ingredients": [
    {"name": "Hambúrguer", "price": 3.0},
    {"name": "Hambúrguer", "price": 3.0},
    {"name": "Hambúrguer", "price": 3.0},
    {"name": "Queijo", "price": 1.5},
    {"name": "Alface", "price": 0.4},
    {"name": "Bacon", "price": 2.0}
    
  ]
}
```

*Muito queijo:* A cada 3 porções de queijo o cliente só paga 2, a cada 6 porções, o cliente pagará 4 e assim sucessivamente.

#### Exemplo de JSON para teste :

```
{
  "ingredients": [
    {"name": "Hambúrguer", "price": 3.0},
    {"name": "Queijo", "price": 1.5},
    {"name": "Queijo", "price": 1.5},
    {"name": "Queijo", "price": 1.5},
    {"name": "Alface", "price": 0.4},
    {"name": "Bacon", "price": 2.0}
  ]
}
```


## Autor
* **Thiago Santos da Silva**  - [ThiagoDevSantos Github](https://github.com/ThiagoDevSantos) - `thiagosantosilva.2021@gmail.com`


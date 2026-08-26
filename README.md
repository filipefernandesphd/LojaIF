# Loja IF

## Contexto

A Loja IF é um sistema simplificado de comércio eletrônico usado em uma atividade prática de Engenharia de Software. A aplicação cadastra produtos físicos e digitais, cria pedidos, concede descontos, calcula frete, processa pagamentos, controla estoque, organiza entregas, envia notificações e gera relatórios de vendas.

O projeto representa uma aplicação funcional que cresceu conforme novas necessidades surgiram. Ele não utiliza interface gráfica, API ou banco de dados real: os cenários são executados diretamente pela classe `Main` e os dados são simulados em memória ou arquivo.

## Requisitos

- Java 21 ou superior;
- Maven 3.9 ou superior.

## Compilação

Na raiz do projeto, execute:

```bash
mvn clean compile
```

## Execução

Para executar os cenários da loja:

```bash
mvn exec:java
```

A execução cria também `target/relatorio-vendas.txt`.

## Testes

Para executar a suíte de testes:

```bash
mvn test
```

## Atividade prática

1. Execute o projeto e observe cada cenário apresentado.
2. Explore os pacotes e compreenda como as classes colaboram.
3. Identifique decisões de projeto que dificultam manutenção, extensão ou substituição de componentes.
4. Relacione cada problema encontrado a um dos princípios SOLID e registre evidências do código.
5. Justifique por que cada situação traz uma consequência concreta para o sistema.
6. Refatore o código preservando o comportamento atual.
7. Execute novamente todos os testes e a aplicação.
8. Implemente, na ordem indicada pelo professor, as demandas de `REQUIREMENTS.md`.

As refatorações serão avaliadas pela clareza, pela justificativa e pela adequação ao problema. Não é necessário criar uma interface para cada classe nem adotar frameworks.

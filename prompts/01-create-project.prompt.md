
Crie um projeto Java educacional completo para ser utilizado em uma atividade prática de Engenharia de Software sobre os princípios SOLID.

O todo o projeto deve estar em português. 

Use domínios que são conhecidos amplamente, como e-commerce, apps de mensagens, biblioteca, bando etc.

# 1. Objetivo pedagógico

O projeto deve representar um sistema realista, funcional e executável, mas deliberadamente possuir problemas de projeto orientado a objetos que possam ser identificados e corrigidos por meio dos princípios SOLID.

O objetivo NÃO é criar código absurdamente ruim ou artificial. O código deve parecer plausível: algo que poderia ter sido desenvolvido por um programador iniciante ou por uma equipe que priorizou inicialmente fazer o sistema funcionar e depois precisou evoluí-lo.

O estudante deverá:

1. executar e compreender o sistema;
2. analisar o código;
3. identificar problemas de design;
4. relacioná-los aos princípios SOLID;
5. refatorar o projeto;
6. manter o comportamento funcional existente;
7. justificar as decisões tomadas.

# 2. Requisito principal

O projeto deve possuir, no mínimo:

* 2 situações distintas relacionadas ao SRP — Single Responsibility Principle;
* 2 situações distintas relacionadas ao OCP — Open/Closed Principle;
* 2 situações distintas relacionadas ao LSP — Liskov Substitution Principle;
* 2 situações distintas relacionadas ao ISP — Interface Segregation Principle;
* 2 situações distintas relacionadas ao DIP — Dependency Inversion Principle.

Portanto, quero pelo menos 10 oportunidades reais de aplicação de SOLID.

IMPORTANTE:

Não crie simplesmente 10 exemplos isolados.

As situações devem estar organicamente integradas a um único sistema, de maneira que o projeto pareça uma aplicação coerente.

Uma mesma parte do código pode eventualmente apresentar mais de um problema de design, mas devem existir pelo menos duas situações claramente diferentes para cada princípio.

# 3. Domínio da aplicação

Crie um sistema de comércio eletrônico simplificado.

O sistema pode conter conceitos como:

* clientes;
* produtos;
* pedidos;
* itens de pedido;
* estoque;
* pagamentos;
* descontos;
* entrega;
* notificações;
* persistência;
* relatórios.

Não é necessário implementar frontend, API REST ou banco de dados real.

O foco é exclusivamente Orientação a Objetos e Engenharia de Software.

# 4. Tecnologias

Utilize:

* Java;
* versão LTS moderna do Java;
* Maven;
* JUnit 5.

Evite frameworks como:

* Spring;
* Hibernate;
* Jakarta EE;
* Lombok;
* bibliotecas de Dependency Injection.

Quero Java Orientado a Objetos relativamente puro, para que os estudantes consigam enxergar claramente classes, interfaces, herança, composição, polimorfismo e dependências.

# 5. Nível de dificuldade

Considere estudantes que já aprenderam:

* classes;
* objetos;
* atributos;
* métodos;
* construtores;
* encapsulamento;
* herança;
* polimorfismo;
* classes abstratas;
* interfaces;
* ArrayList;
* exceções;
* relacionamentos entre classes.

Não utilize técnicas avançadas que desviem o foco de SOLID.

Prefira código simples e legível.

# 6. Característica fundamental do projeto

O sistema deve FUNCIONAR antes da refatoração.

Não quero bugs deliberados.

O problema deve estar no DESIGN, e não no funcionamento do software.

Por exemplo:

* alto acoplamento;
* classes com responsabilidades excessivas;
* grandes estruturas condicionais;
* dependências de implementações concretas;
* hierarquias de herança inadequadas;
* interfaces excessivamente grandes;
* subclasses incapazes de cumprir contratos definidos por superclasses;
* dificuldade para adicionar novos comportamentos;
* dependências criadas internamente pelas próprias classes.

O estudante deve perceber a diferença entre:

"o código funciona"

e

"o código possui um bom design".

# 7. Situações de SRP

Crie pelo menos duas situações independentes em que classes possuam mais responsabilidades do que deveriam.

Exemplos possíveis:

Situação A:
uma classe relacionada a pedidos pode:

* calcular valores;
* aplicar desconto;
* persistir;
* imprimir informações;
* enviar notificações.

Situação B:
uma classe relacionada a relatórios pode:

* buscar dados;
* calcular estatísticas;
* formatar saída;
* salvar arquivo.

Não coloque comentários dizendo que existe violação de SRP.

O estudante deve descobrir.

# 8. Situações de OCP

Crie pelo menos duas situações nas quais adicionar novos comportamentos obrigue atualmente a modificar código existente.

Utilize estruturas como:

```java
if
else if
switch
```

quando forem plausíveis.

Exemplos de áreas:

* cálculo de desconto por tipo de cliente;
* cálculo de frete;
* diferentes tipos de relatórios;
* políticas de promoção.

O projeto deve permitir que posteriormente essas estruturas sejam substituídas por abstrações, composição ou polimorfismo.

Não implemente previamente a solução correta.

# 9. Situações de LSP

Esta parte precisa ser cuidadosamente planejada.

Crie pelo menos duas hierarquias nas quais exista uma violação significativa do princípio de substituição de Liskov.

Não quero exemplos artificiais como apenas:

```java
throw new UnsupportedOperationException();
```

sem contexto.

As violações devem surgir de contratos inadequados de herança.

Exemplos possíveis:

### Situação A — Pagamentos

Pode existir uma abstração que pressuponha determinadas funcionalidades, mas algum subtipo não consiga cumprir corretamente todas as expectativas.

### Situação B — Entregas ou produtos

Pode existir uma superclasse cujo contrato inclua comportamentos inadequados para determinados subtipos.

As subclasses devem compilar e o sistema deve funcionar nos cenários utilizados, mas deve ser possível demonstrar que um subtipo não pode substituir corretamente outro sem alterar as expectativas do cliente.

Crie situações que possam ser corrigidas posteriormente por:

* alteração da hierarquia;
* composição;
* separação de abstrações;
* redefinição adequada do contrato.

# 10. Situações de ISP

Crie pelo menos duas interfaces excessivamente grandes.

Exemplo conceitual:

```java
interface PaymentService {
    void pay();
    void refund();
    void payInInstallments();
    void generateBankSlip();
}
```

Algumas implementações devem ser obrigadas a implementar métodos que não fazem sentido para elas.

Entretanto, não use exatamente esse exemplo necessariamente.

Crie duas situações diferentes no domínio.

A solução futura deverá permitir aos estudantes separar interfaces menores e mais específicas.

# 11. Situações de DIP

Crie pelo menos duas situações de forte dependência em classes concretas.

Exemplos:

```java
class OrderService {

    private MySqlOrderRepository repository =
        new MySqlOrderRepository();

    private EmailNotifier notifier =
        new EmailNotifier();
}
```

Outra situação deve ocorrer em um contexto diferente.

Pode envolver:

* geração de relatórios;
* armazenamento;
* pagamento;
* comunicação;
* estoque.

As classes de alto nível devem atualmente conhecer implementações concretas.

A futura refatoração deverá possibilitar:

* abstrações;
* interfaces;
* constructor injection;
* inversão das dependências.

Não utilize framework de Dependency Injection.

# 12. Evite deixar a resposta evidente

Não utilize nomes como:

* BadOrderService;
* SolidViolation;
* SrpProblem;
* WrongPayment;
* RefactorMe;
* GodClass;
* BadInterface.

Os nomes devem parecer perfeitamente normais dentro de uma aplicação real.

Também NÃO adicione comentários como:

```java
// Violação do SRP
```

ou

```java
// TODO aplicar DIP
```

A identificação do problema faz parte da atividade.

# 13. Tamanho

O projeto deve ser suficientemente pequeno para ser compreendido por estudantes durante uma atividade prática, mas suficientemente grande para que as violações não sejam triviais.

Como referência, busque aproximadamente:

* 20 a 35 classes/interfaces;
* poucos pacotes;
* métodos relativamente pequenos;
* código total administrável em uma aula ou sequência curta de aulas.

Evite criar centenas de arquivos.

# 14. Organização de pacotes

Utilize uma organização simples, por exemplo:

```text
src/main/java/
    model/
    service/
    repository/
    payment/
    notification/
    shipping/
    report/
```

Não crie uma arquitetura excessivamente sofisticada.

# 15. Execução

Crie uma classe:

```text
Main.java
```

que monte alguns objetos e execute diferentes cenários.

Por exemplo:

1. cadastrar produtos;
2. criar clientes;
3. criar pedidos;
4. calcular desconto;
5. realizar pagamento;
6. atualizar estoque;
7. calcular entrega;
8. enviar notificação;
9. gerar relatório.

Ao executar o programa, deve ser possível observar claramente que o sistema funciona.

# 16. Dados

Não utilize banco de dados real.

Quando necessário, utilize:

* ArrayList;
* HashMap;
* implementações em memória.

Entretanto, é aceitável criar classes com nomes como:

```text
MySqlOrderRepository
FileProductRepository
```

que apenas simulem essas tecnologias por meio de mensagens ou armazenamento em memória.

Isso pode ser utilizado propositalmente para trabalhar DIP.

# 17. Testes

Crie testes JUnit 5 para os comportamentos fundamentais.

Os testes devem funcionar como uma rede de segurança para a refatoração.

IMPORTANTE:

Os testes devem validar COMPORTAMENTO e não estrutura interna.

Os estudantes devem conseguir alterar significativamente a arquitetura sem precisar reescrever todos os testes.

Crie testes para:

* cálculo dos pedidos;
* descontos;
* pagamentos;
* estoque;
* frete;
* regras principais.

Todos os testes devem passar antes da refatoração.

# 18. Requisitos de evolução

Além do código inicial, crie um arquivo:

```text
REQUIREMENTS.md
```

contendo novas demandas que serão entregues aos estudantes progressivamente.

As demandas devem explorar justamente as fragilidades do design atual.

Por exemplo:

### Evolução 1

Adicionar um novo tipo de pagamento.

### Evolução 2

Adicionar uma nova política de desconto.

### Evolução 3

Adicionar uma nova forma de notificação.

### Evolução 4

Adicionar um novo tipo de entrega.

### Evolução 5

Adicionar uma forma diferente de persistência.

As mudanças devem fazer o estudante perceber que o design atual dificulta evolução e que aplicar SOLID reduz esse problema.

Não explique nesse arquivo qual princípio deve ser utilizado.

# 19. README para o estudante

Crie:

```text
README.md
```

contendo apenas:

* contexto do sistema;
* requisitos;
* como compilar;
* como executar;
* como executar os testes;
* descrição geral da atividade.

A atividade deve dizer ao estudante para:

1. executar o projeto;
2. analisar a arquitetura;
3. identificar problemas;
4. relacionar cada problema a algum princípio SOLID;
5. justificar;
6. refatorar;
7. executar novamente os testes;
8. implementar os novos requisitos.

NÃO revele onde estão as violações.

# 20. Guia exclusivo do professor

Crie também:

```text
TEACHER_GUIDE.md
```

Este arquivo será exclusivo do professor.

Nele, documente todas as situações deliberadamente criadas.

Utilize a estrutura:

## SRP

### Situação SRP-1

* classes envolvidas;
* problema;
* por que representa violação;
* possível refatoração.

### Situação SRP-2

* classes envolvidas;
* problema;
* por que representa violação;
* possível refatoração.

Faça o mesmo para:

* OCP-1;
* OCP-2;
* LSP-1;
* LSP-2;
* ISP-1;
* ISP-2;
* DIP-1;
* DIP-2.

Para cada uma, descreva também:

* evidência no código;
* consequência para manutenção;
* mudança de requisito que evidencia o problema;
* uma possível solução;
* outras soluções aceitáveis.

Não trate a solução proposta como única.

# 21. Matriz de rastreabilidade

No `TEACHER_GUIDE.md`, inclua ao final uma matriz:

| ID | Princípio | Classes | Problema | Requisito que evidencia |
| -- | ---------- | ------- | -------- | ----------------------- |

Inclua pelo menos:

```text
SRP-1
SRP-2
OCP-1
OCP-2
LSP-1
LSP-2
ISP-1
ISP-2
DIP-1
DIP-2
```

# 22. Atenção especial às sobreposições

Uma classe pode violar mais de um princípio, porque isso acontece em sistemas reais.

Entretanto, não utilize a mesma situação para contabilizar artificialmente vários requisitos.

Por exemplo:

se uma classe depende diretamente de `EmailService`, isso pode ter consequências relacionadas a OCP e DIP, mas não considere esse único caso como OCP-1 e DIP-1.

Quero pelo menos 10 situações pedagogicamente distinguíveis.

# 23. Qualidade pedagógica

Antes de finalizar, revise cada situação e pergunte:

1. É uma violação real ou estou forçando o princípio?
2. Um estudante consegue descobrir o problema analisando o código?
3. Existe uma consequência concreta para evolução/manutenção?
4. Uma mudança de requisito evidencia essa consequência?
5. A aplicação de SOLID realmente melhora o design?
6. A solução exige apenas conceitos de OO compatíveis com estudantes iniciantes/intermediários?

Se alguma situação parecer artificial, redesenhe-a.

# 24. Não superarquitetar

O código inicial deve ser deliberadamente problemático, mas a solução esperada também NÃO deve transformar o sistema em uma arquitetura corporativa exagerada.

Evite criar abstrações sem necessidade.

O objetivo é ensinar:

> abstrações devem surgir para resolver problemas reais de mudança, acoplamento, responsabilidade e substituição.

Não:

> toda classe precisa ter uma interface.

# 25. Git

Inicialize o projeto como um repositório Git.

Faça um commit contendo a versão inicial funcional e problemática:

```text
Initial legacy implementation
```

Todos os testes devem passar nesse commit.

Não realize ainda a refatoração SOLID no código principal.

# 26. Validação final obrigatória

Antes de concluir:

1. execute:

```bash
mvn test
```

2. execute a aplicação;
3. confirme que todos os cenários funcionam;
4. revise manualmente as 10 situações SOLID;
5. confirme que existem pelo menos duas situações distintas para cada princípio;
6. confirme que o README não entrega as respostas;
7. confirme que o `TEACHER_GUIDE.md` documenta todas elas;
8. confirme que nenhuma classe ou comentário denuncia diretamente que determinada parte é propositalmente mal projetada.

Ao final, apresente um resumo contendo:

* número de classes;
* número de interfaces;
* número de testes;
* pacotes criados;
* as funcionalidades disponíveis;
* confirmação de `mvn test`;
* tabela apenas com os IDs SRP-1 a DIP-2, sem revelar as soluções no resumo.

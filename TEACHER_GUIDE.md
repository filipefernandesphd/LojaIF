# Guia do professor

Este documento descreve as fragilidades de projeto intencionalmente presentes na versão inicial. O sistema funciona nos cenários implementados; o foco da atividade é perceber como decisões plausíveis se tornam obstáculos quando surgem novas demandas.

## SRP

### Situação SRP-1

- **Classes envolvidas:** `ProcessadorPedido`, com colaboração de `CalculadoraDesconto`, `CalculadoraFrete`, `ServicoEstoque`, `RepositorioPedidosMySql` e `NotificadorEmail`.
- **Problema:** `ProcessadorPedido` coordena a compra, altera o estado do pedido, persiste dados, envia e-mail e formata/imprime o comprovante.
- **Por que representa violação:** a classe tem razões independentes para mudar: fluxo comercial, apresentação do comprovante, persistência e comunicação.
- **Evidência no código:** `processar` executa etapas de domínios distintos e `imprimirComprovante` contém formatação para console.
- **Consequência para manutenção:** uma mudança visual no comprovante ou no canal de comunicação exige alterar e testar novamente o coordenador da compra.
- **Mudança de requisito que evidencia:** Evoluções 9 e 10.
- **Uma possível solução:** manter no processador apenas a orquestração e extrair emissão de comprovante e comunicação para colaboradores próprios.
- **Outras soluções aceitáveis:** serviço de finalização com pequenos serviços especializados; eventos simples observados por notificadores; retorno de um objeto de resumo para a camada de apresentação.

### Situação SRP-2

- **Classes envolvidas:** `GeradorRelatorioVendas` e `ExportadorRelatorioArquivo`.
- **Problema:** o gerador busca pedidos, filtra vendas, calcula estatísticas, conhece dois formatos textuais e decide salvar o resultado.
- **Por que representa violação:** obtenção de dados, cálculo, apresentação e destino da saída são responsabilidades que mudam por motivos diferentes.
- **Evidência no código:** o método `gerar` acessa o repositório, executa agregações, monta `TEXTO`/`CSV` e chama o exportador.
- **Consequência para manutenção:** mudanças nas métricas podem afetar a formatação; novos destinos e formatos aumentam continuamente a mesma classe.
- **Mudança de requisito que evidencia:** Evolução 8.
- **Uma possível solução:** separar consulta/agregação, formatadores e destino do relatório.
- **Outras soluções aceitáveis:** criar um objeto `ResumoVendas` e deixar adaptadores externos apresentá-lo; usar pequenos geradores por formato e uma camada de aplicação para coordenar.

## OCP

### Situação OCP-1

- **Classes envolvidas:** `CalculadoraDesconto`, `TipoCliente` e `Cliente`.
- **Problema:** todas as políticas por categoria estão concentradas em um `switch`.
- **Por que representa violação:** a adição de uma categoria exige modificar a calculadora consolidada, com risco para regras já estáveis.
- **Evidência no código:** `CalculadoraDesconto.calcular` seleciona percentuais para `COMUM`, `PREMIUM` e `EMPRESARIAL`.
- **Consequência para manutenção:** a estrutura condicional cresce a cada campanha ou categoria e obriga reteste das ramificações existentes.
- **Mudança de requisito que evidencia:** Evolução 1.
- **Uma possível solução:** representar cada política de desconto por uma abstração escolhida pelo contexto.
- **Outras soluções aceitáveis:** mover comportamento para tipos de cliente polimórficos; compor regras pequenas; usar um mapa de estratégias quando a escolha for puramente configuracional.

### Situação OCP-2

- **Classes envolvidas:** `CalculadoraFrete`, `Pedido`, `Endereco` e produtos.
- **Problema:** modalidades e fórmulas de frete estão codificadas em uma cadeia de condicionais baseada em texto.
- **Por que representa violação:** cada nova modalidade obriga a edição da calculadora existente.
- **Evidência no código:** `CalculadoraFrete.calcular` trata `RETIRADA`, `PADRAO` e `EXPRESSA` com `if/else if`.
- **Consequência para manutenção:** regras regionais e novas transportadoras tornam o método maior, frágil a erros de digitação e difícil de testar isoladamente.
- **Mudança de requisito que evidencia:** Evolução 2.
- **Uma possível solução:** criar uma política de frete por modalidade, com um contrato comum.
- **Outras soluções aceitáveis:** composição de tarifa-base e regra de peso; catálogo de calculadores registrado por enumeração; seleção externa da estratégia apropriada.

## LSP

### Situação LSP-1

- **Classes envolvidas:** `Produto`, `ProdutoFisico`, `ProdutoDigital` e `ServicoEstoque`.
- **Problema:** a superclasse afirma que todo produto possui quantidade mutável em estoque, mas o produto digital simula estoque infinito e ignora retirada e reposição.
- **Por que representa violação:** clientes de `Produto` podem esperar que `retirarDoEstoque` reduza a quantidade e que `adicionarAoEstoque` a aumente; essas pós-condições deixam de valer para o subtipo digital.
- **Evidência no código:** `ProdutoDigital` sobrescreve ambos os métodos sem alterar estado e retorna `Integer.MAX_VALUE` em `getQuantidadeEmEstoque`.
- **Consequência para manutenção:** rotinas genéricas de inventário, auditoria ou reposição produzem resultados sem significado para alguns produtos.
- **Mudança de requisito que evidencia:** Evolução 5.
- **Uma possível solução:** retirar o controle de estoque do tipo geral e expô-lo somente para itens estocáveis, usando composição.
- **Outras soluções aceitáveis:** separar produtos vendáveis de recursos controlados em inventário; manter tipos irmãos sob uma abstração mínima que contenha somente dados comuns ao catálogo.

### Situação LSP-2

- **Classes envolvidas:** `Entrega`, `EntregaTransportadora` e `RetiradaNaLoja`.
- **Problema:** `Entrega` pressupõe destino alterável, despacho logístico e rastreamento de transporte, enquanto a retirada possui loja fixa e nenhum percurso até o cliente.
- **Por que representa violação:** um cliente que opera sobre `Entrega` espera que `atualizarEndereco` efetivamente substitua o endereço; `RetiradaNaLoja` rejeita silenciosamente essa operação e dá outro significado ao rastreamento.
- **Evidência no código:** `RetiradaNaLoja.atualizarEndereco` apenas imprime uma mensagem e preserva o endereço anterior.
- **Consequência para manutenção:** fluxos genéricos podem informar ao usuário que uma alteração ocorreu quando ela não ocorreu, exigindo testes de tipo ou condicionais especiais.
- **Mudança de requisito que evidencia:** Evolução 6.
- **Uma possível solução:** modelar entrega domiciliar e ponto de retirada como formas de atendimento sob um contrato menor, sem prometer endereço mutável.
- **Outras soluções aceitáveis:** composição de destino e acompanhamento; hierarquias independentes; capacidade opcional de alteração expressa por uma interface específica.

## ISP

### Situação ISP-1

- **Classes envolvidas:** `MeioPagamento`, `CartaoCredito`, `Pix` e `Boleto`.
- **Problema:** o contrato exige pagamento, estorno, parcelamento e geração de código de barras de todos os meios.
- **Por que representa violação:** clientes e implementações dependem de operações que não lhes dizem respeito; cartão não gera código, boleto não parcela e Pix adapta um código de cobrança a uma operação chamada código de barras.
- **Evidência no código:** métodos devolvem `""`, `false` ou dão semântica diferente somente para satisfazer `MeioPagamento`.
- **Consequência para manutenção:** chamadores precisam conhecer capacidades concretas ou interpretar valores sentinela, e novos meios começam com métodos sem sentido.
- **Mudança de requisito que evidencia:** Evolução 3.
- **Uma possível solução:** manter uma interface mínima para pagar e separar capacidades de estorno, parcelamento e cobrança por código.
- **Outras soluções aceitáveis:** objetos de operação específicos; composição de capacidades; contratos diferentes para pagamento imediato e pagamento faturado.

### Situação ISP-2

- **Classes envolvidas:** `CanalNotificacao`, `NotificadorEmail` e `NotificadorSms`.
- **Problema:** todo canal deve enviar anexos, agendar mensagens e confirmar leitura, embora SMS não ofereça essas capacidades.
- **Por que representa violação:** `NotificadorSms` é forçado a depender de e implementar operações irrelevantes.
- **Evidência no código:** três métodos de `NotificadorSms` devolvem `false` ou degradam o anexo para texto.
- **Consequência para manutenção:** o chamador não pode confiar no contrato sem conhecer o canal e verificar manualmente o resultado de cada capacidade.
- **Mudança de requisito que evidencia:** Evolução 4.
- **Uma possível solução:** criar um contrato mínimo de envio e interfaces independentes para anexos, agendamento e confirmação.
- **Outras soluções aceitáveis:** composição de funcionalidades; comandos de notificação especializados; descritor explícito de capacidades, se a seleção dinâmica justificar essa abordagem.

## DIP

### Situação DIP-1

- **Classes envolvidas:** `ProcessadorPedido`, `RepositorioPedidosMySql`, `NotificadorEmail`, `ServicoEstoque`, `CalculadoraDesconto` e `CalculadoraFrete`.
- **Problema:** o serviço de alto nível instancia diretamente todos os colaboradores concretos e não permite configurá-los.
- **Por que representa violação:** a política de processamento depende de detalhes de persistência e infraestrutura, em vez de contratos voltados às suas necessidades.
- **Evidência no código:** os cinco campos de `ProcessadorPedido` são inicializados com `new` na própria declaração.
- **Consequência para manutenção:** trocar armazenamento, comunicação ou política exige editar o processador; testes são obrigados a executar efeitos de console e a persistência simulada.
- **Mudança de requisito que evidencia:** Evoluções 7 e 10.
- **Uma possível solução:** definir abstrações pequenas nas fronteiras necessárias e recebê-las pelo construtor.
- **Outras soluções aceitáveis:** uma fábrica de composição fora do serviço; setters apenas quando a dependência for realmente opcional; receber estratégias como parâmetros de caso de uso.

### Situação DIP-2

- **Classes envolvidas:** `ServicoEstoque` e `RepositorioProdutosArquivo`.
- **Problema:** a regra de reserva e devolução cria diretamente o mecanismo concreto que registra o inventário em arquivo.
- **Por que representa violação:** a política de estoque fica subordinada ao detalhe de armazenamento e não oferece um ponto de configuração.
- **Evidência no código:** o campo `repositorio` de `ServicoEstoque` é inicializado com `new RepositorioProdutosArquivo()`.
- **Consequência para manutenção:** trocar o registro em arquivo por memória ou outro mecanismo exige editar o serviço e seus testes sempre executam mensagens do detalhe concreto.
- **Mudança de requisito que evidencia:** Evolução 7.
- **Uma possível solução:** expressar somente as operações de persistência necessárias ao estoque e receber uma implementação pelo construtor.
- **Outras soluções aceitáveis:** separar atualização dos objetos e sincronização do inventário; publicar uma atualização observável por diferentes armazenamentos; passar o repositório como dependência do caso de uso.

## Matriz de rastreabilidade

| ID | Princípio | Classes | Problema | Requisito que evidencia |
| -- | --------- | ------- | -------- | ----------------------- |
| SRP-1 | SRP | `ProcessadorPedido` | Orquestração, apresentação, persistência e comunicação reunidas | Evoluções 9 e 10 |
| SRP-2 | SRP | `GeradorRelatorioVendas` | Consulta, estatísticas, formatação e exportação reunidas | Evolução 8 |
| OCP-1 | OCP | `CalculadoraDesconto`, `TipoCliente` | Políticas de cliente em seleção fechada | Evolução 1 |
| OCP-2 | OCP | `CalculadoraFrete` | Modalidades e fórmulas em condicionais | Evolução 2 |
| LSP-1 | LSP | `Produto`, `ProdutoDigital` | Contrato de estoque não se aplica ao produto digital | Evolução 5 |
| LSP-2 | LSP | `Entrega`, `RetiradaNaLoja` | Contrato de endereço e transporte não se aplica à retirada | Evolução 6 |
| ISP-1 | ISP | `MeioPagamento` e implementações | Capacidades de pagamento agrupadas em contrato amplo | Evolução 3 |
| ISP-2 | ISP | `CanalNotificacao` e implementações | Recursos de comunicação agrupados em contrato amplo | Evolução 4 |
| DIP-1 | DIP | `ProcessadorPedido` e colaboradores | Fluxo de compra cria detalhes concretos | Evoluções 7 e 10 |
| DIP-2 | DIP | `ServicoEstoque`, `RepositorioProdutosArquivo` | Estoque cria o mecanismo concreto de registro | Evolução 7 |

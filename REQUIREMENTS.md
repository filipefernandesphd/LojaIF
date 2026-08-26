# Demandas de evolução

As demandas abaixo serão liberadas progressivamente. Antes de iniciar cada uma, mantenha todos os testes existentes passando e acrescente testes para o novo comportamento.

## Evolução 1 — Cliente universitário

Adicionar a categoria de cliente `UNIVERSITARIO`. Essa categoria recebe 8% de desconto em qualquer compra e mais 4% quando o subtotal ultrapassa R$ 600,00. Cupons válidos continuam acumuláveis e o desconto total permanece limitado a 30%.

## Evolução 2 — Entrega por bicicleta

Adicionar a modalidade `BICICLETA`, disponível para endereços de Juiz de Fora. O preço é R$ 8,00 para pedidos de até 2 kg e R$ 12,00 para pedidos mais pesados. Pedidos para outras cidades devem ser recusados nessa modalidade.

## Evolução 3 — Carteira da loja

Adicionar pagamento com créditos de uma carteira mantida pela Loja IF. A carteira permite pagar e receber devoluções, mas não possui parcelas nem código de barras.

## Evolução 4 — Notificação por aplicativo

Enviar atualizações do pedido por notificação no aplicativo. Esse canal aceita mensagens imediatas e agendadas, confirma a leitura, mas não envia anexos.

## Evolução 5 — Assinaturas digitais

Adicionar assinaturas mensais ao catálogo. Elas não possuem estoque físico, peso ou reposição, podem ser canceladas e liberam acesso enquanto estiverem ativas.

## Evolução 6 — Armário de retirada

Permitir que um pedido seja retirado em um armário inteligente. O cliente escolhe um armário, recebe um código de abertura e não pode trocar o armário depois que o pedido estiver disponível.

## Evolução 7 — Persistência alternativa

Permitir executar o sistema escolhendo entre o armazenamento atualmente simulado e um armazenamento inteiramente em memória, tanto para pedidos quanto para registros de estoque, sem alterar os respectivos fluxos de negócio.

## Evolução 8 — Exportação de relatórios

Permitir exportar o relatório de vendas em JSON e enviar seu conteúdo para um armazenamento remoto simulado, além de continuar aceitando arquivos locais.

## Evolução 9 — Processamento em lote

Criar um comando que processe uma lista de pedidos sem imprimir comprovantes no console e retorne um resumo com quantidades aprovadas e recusadas.

## Evolução 10 — Comunicação configurável

Permitir escolher, na inicialização da aplicação, um ou mais canais para confirmações de pedido sem modificar o fluxo principal de compra.

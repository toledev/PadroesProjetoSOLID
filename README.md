# Sistema de Checkout - Princípios SOLID em Java

## Sobre o projeto

Este projeto apresenta um sistema de checkout de e-commerce desenvolvido em Java puro. O sistema recebe um pedido, calcula o valor a ser pago, realiza o pagamento, salva o pedido e envia uma notificação ao cliente.

O domínio de checkout foi escolhido por possuir comportamentos que podem variar naturalmente, como formas de pagamento, canais de notificação e regras de desconto. Dessa forma, foi possível aplicar os princípios SOLID em situações próximas de um sistema real.

## Funcionalidades implementadas

- Cadastro de cliente, produto, item de pedido e pedido.
- Cálculo automático do valor total do pedido.
- Aplicação de uma política de desconto de 10%.
- Simulação de pagamento por Pix ou cartão.
- Salvamento de pedidos em memória.
- Simulação de notificação por WhatsApp ou e-mail no console.

## Estrutura do projeto

```text
src/main/java/com/faculdade/solid
├── contratos   Interfaces utilizadas pelos serviços
├── dominio     Classes que representam as informações do negócio
├── infra       Implementações de pagamento, notificação e repositório
├── servicos    Classes responsáveis pelos fluxos da aplicação
└── Main.java   Ponto de entrada e configuração das dependências
```

As classes de domínio são `Cliente`, `Produto`, `ItemPedido`, `Pedido` e `ReciboPagamento`. Elas mantêm apenas informações e regras relacionadas ao próprio estado. Por exemplo, um pedido não pode ser criado sem cliente ou sem itens, e um produto precisa ter preço positivo.

Os serviços concentram o fluxo da aplicação. O `PedidoService` processa o pedido, aplica o desconto, solicita o pagamento e salva o resultado. O `NotificacaoService` envia a confirmação de pagamento ao cliente.

## Aplicação dos princípios SOLID

### Single Responsibility Principle (SRP)

Cada classe possui uma responsabilidade bem definida. As entidades representam os dados do negócio. O `PedidoService` é responsável pelo processamento do pedido. O `NotificacaoService` é responsável pelo envio da confirmação. As classes `PagamentoPix`, `PagamentoCartao`, `Email` e `WhatsApp` executam somente o comportamento correspondente ao seu tipo.

### Open/Closed Principle (OCP)

O sistema permite incluir novos comportamentos sem alterar os serviços centrais. Caso seja necessário adicionar pagamento em dinheiro, por exemplo, basta criar uma classe `PagamentoDinheiro` que implemente `MetodoPagamento` e simule o recebimento, retornando um recibo. O mesmo vale para novos canais de notificação e políticas de desconto.

### Liskov Substitution Principle (LSP)

As implementações de `MetodoPagamento` podem ser utilizadas da mesma forma pelo `PedidoService`. Tanto `PagamentoPix` quanto `PagamentoCartao` cumprem o contrato de receber um valor e retornar um recibo. Da mesma forma, `Email` e `WhatsApp` podem substituir um ao outro no `NotificacaoService`, pois ambos cumprem o contrato de `CanalNotificacao`.

### Interface Segregation Principle (ISP)

As interfaces foram separadas por responsabilidade: `MetodoPagamento`, `CanalNotificacao`, `RepositorioPedido` e `PoliticaDesconto`. Cada uma possui apenas o método necessário para aquele comportamento. Assim, uma classe de pagamento não precisa depender de métodos relacionados a notificações ou persistência.

### Dependency Inversion Principle (DIP)

Os serviços dependem de abstrações para seus colaboradores. O `PedidoService` recebe `RepositorioPedido`, `PoliticaDesconto` e `MetodoPagamento` pelo construtor. O `NotificacaoService` recebe `CanalNotificacao` pelo construtor. O `Main` cria e injeta essas implementações manualmente, conforme o enunciado do trabalho.

São criadas duas instâncias de `PedidoService`: uma configurada com Pix e outra com cartão. Ambas usam o mesmo repositório. O método `processar(pedido)` utiliza o pagamento recebido na construção, sem escolher tipos com `if`, `switch` ou `instanceof`.

## Cenários executados

O arquivo `Main.java` monta os objetos e executa dois cenários:

1. Pedido `PED-001`: pagamento por Pix e confirmação por WhatsApp.
2. Pedido `PED-002`: pagamento por cartão e confirmação por e-mail.

Nos dois casos, o pedido é salvo e o valor final considera o desconto de 10%.

## Como executar

É necessário ter o JDK 11 ou superior instalado. No PowerShell, dentro da pasta do projeto, execute:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Path src\main\java -Filter *.java | ForEach-Object { $_.FullName })
java -cp out com.faculdade.solid.Main
```

## Possível extensão

Para adicionar uma nova forma de pagamento, deve-se criar uma classe que implemente a interface `MetodoPagamento` e passar essa implementação ao construtor de `PedidoService` no `Main`. O contrato deste exemplo é síncrono: retornar um recibo representa pagamento aprovado. Não é necessário alterar o `PedidoService`, pois ele já trabalha com a abstração. Essa extensão demonstra diretamente o princípio Open/Closed.


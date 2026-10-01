# Sistema de Checkout - Princípios SOLID em Java

## Sobre o projeto

Este projeto apresenta um sistema de checkout de e-commerce desenvolvido em Java puro. O sistema recebe um pedido, calcula o valor a ser pago, realiza o pagamento, salva o pedido e envia uma notificação ao cliente.

O domínio de checkout foi escolhido por possuir comportamentos que podem variar naturalmente, como formas de pagamento, canais de notificação e regras de desconto. Dessa forma, foi possível aplicar os princípios SOLID em situações próximas de um sistema real.

## Funcionalidades implementadas

- Cadastro de cliente, produto, item de pedido e pedido.
- Cálculo automático do valor total do pedido.
- Aplicação de uma política de desconto de 10%.
- Pagamento por Pix ou cartão.
- Salvamento de pedidos em memória.
- Notificação por WhatsApp ou e-mail.

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

O sistema permite incluir novos comportamentos sem alterar os serviços centrais. Caso seja necessário adicionar pagamento por boleto, por exemplo, basta criar uma classe `PagamentoBoleto` que implemente `MetodoPagamento`. O mesmo vale para novos canais de notificação e políticas de desconto.

### Liskov Substitution Principle (LSP)

As implementações de `MetodoPagamento` podem ser utilizadas da mesma forma pelo `PedidoService`. Tanto `PagamentoPix` quanto `PagamentoCartao` cumprem o contrato de receber um valor e retornar um recibo. Da mesma forma, `Email` e `WhatsApp` podem substituir um ao outro no `NotificacaoService`, pois ambos cumprem o contrato de `CanalNotificacao`.

### Interface Segregation Principle (ISP)

As interfaces foram separadas por responsabilidade: `MetodoPagamento`, `CanalNotificacao`, `RepositorioPedido` e `PoliticaDesconto`. Cada uma possui apenas o método necessário para aquele comportamento. Assim, uma classe de pagamento não precisa depender de métodos relacionados a notificações ou persistência.

### Dependency Inversion Principle (DIP)

Os serviços dependem de abstrações, e não de implementações concretas. O `PedidoService` recebe `RepositorioPedido` e `PoliticaDesconto` pelo construtor e recebe `MetodoPagamento` ao processar o pedido. O `NotificacaoService` recebe `CanalNotificacao` pelo construtor. As implementações concretas são criadas somente no `Main`, onde ocorre a injeção manual das dependências.

## Cenários executados

O arquivo `Main.java` monta os objetos e executa dois cenários:

1. Pedido `PED-001`: pagamento por Pix e confirmação por WhatsApp.
2. Pedido `PED-002`: pagamento por cartão e confirmação por e-mail.

Nos dois casos, o pedido é salvo e o valor final considera o desconto de 10%.

## Como executar

É necessário ter o JDK instalado. No PowerShell, dentro da pasta do projeto, execute:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Path src\main\java -Filter *.java | ForEach-Object { $_.FullName })
java -cp out com.faculdade.solid.Main
```

## Possível extensão

Para adicionar uma nova forma de pagamento, deve-se criar uma classe que implemente a interface `MetodoPagamento` e utilizar essa implementação no `Main`. Não é necessário alterar o `PedidoService`, pois ele já trabalha com a abstração. Essa extensão demonstra diretamente o princípio Open/Closed.


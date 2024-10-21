# Projeto: Pizzaria - Pixx Pizza

Este é um projeto Java que simula o gerenciamento de pedidos de uma pizzaria. Ele permite a criação de clientes, a realização de pedidos de pizzas com diferentes sabores e tamanhos, alteração de pedidos e cálculo de frete.

## Funcionalidades:

1. **Fazer um Pedido**: O usuário pode selecionar um cliente, adicionar pizzas ao pedido, escolher o tamanho e os sabores das pizzas, além de visualizar o valor total do pedido.
2. **Alterar Pedido**: É possível alterar um pedido, adicionando, removendo ou alterando sabores das pizzas já inseridas.
3. **Adicionar Cliente**: Adicione novos clientes ao sistema, inserindo nome, endereço, telefone e email.
4. **Gerar Relatório de Vendas**: Exibe o faturamento total e a quantidade de pedidos feitos para cada sabor.
5. **Lista de Clientes**: Exibe uma lista de todos os clientes cadastrados.
6. **Calcular Frete**: O programa calcula o frete com base na distância e quantidade de pizzas, e exibe o valor total do pedido, incluindo o frete.

## Regras de Preço:
- **Tamanhos de Pizza**: 
  - Brotinho (50% do valor da pizza grande).
  - Grande (preço base).
  - Extra Grande (+ R$ 10,00 em relação à pizza grande).
- O preço é calculado com base nos sabores e no tamanho da pizza.
  
## Cálculo do Frete:
O frete é calculado como:
- Preço por quilômetro: R$ 2,00
- Preço por pizza: R$ 1,50

O valor total do pedido é a soma do preço das pizzas e o frete.

## Estrutura do Projeto:

```bash
projetopizza/
├── src/
│   └── Projeto/
│       ├── Pizzaria.java
│       ├── Cliente.java
│       ├── Pedido.java
│       ├── Pizza.java
│       └── Cardapio.java
├── README.md
└── bin/

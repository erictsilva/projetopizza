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

## Requisitos:

- **Java 8** ou superior instalado no sistema.
- Um ambiente de desenvolvimento Java como Eclipse, IntelliJ IDEA, ou apenas o terminal para compilar e executar.

## Como Executar o Projeto:

1. Navegue até o diretório do projeto.
   - No Windows: `C:\Users\SeuUsuario\Desktop\projetopizza`
   - No Linux/macOS: `/home/SeuUsuario/Área de Trabalho/projetopizza`
   
2. Compile o código-fonte:
   - Usando terminal: `javac -d bin src/Projeto/*.java`
   - Usando uma IDE como Eclipse ou IntelliJ IDEA: basta importar o projeto e executar o arquivo `Pizzaria.java`.

3. Execute o programa:
   - Via terminal: `java -cp bin Projeto.Pizzaria`

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

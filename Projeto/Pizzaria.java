package Projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Projeto.Pizza.TamanhoPizza;

public class Pizzaria {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Cliente> listaClientes = new ArrayList<>();
        List<Pedido> listaPedidos = new ArrayList<>();
        double faturamentoTotal = 0;

        boolean continuar = true;
        while (continuar) {
            System.out.println("**Pixx Pizza** ");
            System.out.println();
            System.out.println("1 - Fazer um pedido...");
            System.out.println("2 - Alterar pedido...");
            System.out.println("3 - Adicionar cliente...");
            System.out.println("4 - Gerar relatório de vendas");
            System.out.println("5 - Gerar lista de clientes");
            System.out.println("6 - Calcular frete...");
            System.out.println("0 - Sair");

            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (opcao) {
                case 1:
                    fazerPedido(scanner, listaPedidos, listaClientes);
                    break;
                case 2:
                    alterarPedido(scanner, listaPedidos);
                    break;
                case 3:
                    listaClientes.add(adicionarCliente(scanner));
                    System.out.println("Cliente adicionado com sucesso!");
                    break;
                case 4:
                    gerarRelatorio(listaPedidos, faturamentoTotal);
                    break;
                case 5:
                    gerarListaClientes(listaClientes);
                    break;
                case 6:
                    System.out.print("Digite a distância para entrega (em km): ");
                    double distancia = scanner.nextDouble();
                    double frete = calcularFrete(distancia, 0); // Inicialmente 0, será atualizado depois
                    double valorTotalPedido = somarPizzas(listaPedidos.stream().map(Pedido::getPizzas).flatMap(List::stream).toList()); // Somando todas as pizzas
                    double totalComFrete = valorTotalPedido + frete;
                    System.out.println("O frete custa: R$ " + frete);
                    System.out.println("Valor total do pedido (incluindo frete): R$ " + totalComFrete);
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até logo...");
                    continuar = false;
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
    }

    private static void fazerPedido(Scanner scanner, List<Pedido> listaPedidos, List<Cliente> listaClientes) {
        List<Pizza> pizzas = new ArrayList<>();
        System.out.println("Faça um pedido...");

        int x = 1;
        System.out.println("Selecione um cliente: ");
        for (Cliente cliente : listaClientes) {
            System.out.println(x + " - " + cliente.getNome());
            x++;
        }
        System.out.print("Escolha uma opção: ");
        int cliente = scanner.nextInt();
        scanner.nextLine();

        boolean continuar = true;
        while (continuar) {
            pizzas.add(criarPizza(scanner));

            System.out.println("Pizza cadastrada com sucesso!");
            System.out.println();
            System.out.println("Deseja cadastrar mais uma pizza no pedido?");
            System.out.print("1 - Sim, 2 - Não: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao != 1) {
                continuar = false;
            }
        }

        Pedido pedido = new Pedido(listaPedidos.size() + 1, listaClientes.get(cliente - 1), pizzas, somarPizzas(pizzas));
        listaPedidos.add(pedido);

        // Exibir valor total do pedido
        System.out.println("Valor total do pedido: R$ " + pedido.getValorTotal());
    }

    private static Pizza criarPizza(Scanner scanner) {
        int x = 1;
        System.out.println("Qual o tamanho da pizza? ");
        System.out.println("Selecione um tamanho: ");
        for (TamanhoPizza tamanhos : Pizza.TamanhoPizza.values()) {
            System.out.println(x + " - " + tamanhos);
            x++;
        }
        System.out.print("Escolha uma opção: ");
        int tamanho = scanner.nextInt();
        scanner.nextLine();

        int quantiSabores = 0;
        while (quantiSabores < 1 || quantiSabores > 4) {
            System.out.println("Digite a quantidade de sabores: 1 - 4 ");
            System.out.print("Escolha uma opção: ");
            quantiSabores = scanner.nextInt();
            scanner.nextLine();
        }

        Cardapio cardapio = new Cardapio();
        List<String> saboresList = new ArrayList<>();
        List<String> saboresSelect = new ArrayList<>();

        for (int i = 0; i < quantiSabores; i++) {
            System.out.println("Selecione um sabor: ");

            x = 1;
            for (String sabor : cardapio.getCardapio().keySet()) {
                saboresList.add(sabor);
                System.out.println(x + " - " + sabor + " (R$ " + cardapio.getCardapio().get(sabor) + ")");
                x++;
            }
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            saboresSelect.add(saboresList.get(opcao - 1));
        }

        double precoPizza = cardapio.getPrecoJusto(saboresSelect);
        return new Pizza(saboresSelect, precoPizza, TamanhoPizza.getByIndex(tamanho - 1));
    }

    private static double somarPizzas(List<Pizza> pizzas) {
        double valorTotal = 0;
        for (Pizza pizza : pizzas) {
            valorTotal += pizza.getPreco();
        }
        return valorTotal;
    }

    private static void alterarPedido(Scanner scanner, List<Pedido> listaPedidos) {
        System.out.println("Alterar pedido...");
        System.out.println("Digite o nome ou ID do cliente: ");
        String identificador = scanner.nextLine();

        Pedido pedidoEncontrado = null;

        for (Pedido pedido : listaPedidos) {
            if (String.valueOf(pedido.getId()).equals(identificador) ||
                pedido.getCliente().getNome().equalsIgnoreCase(identificador)) {
                pedidoEncontrado = pedido;
                break;
            }
        }

        if (pedidoEncontrado != null) {
            System.out.println("Pedido encontrado! O que deseja alterar?");
            System.out.println("1 - Adicionar Pizza");
            System.out.println("2 - Remover Pizza");
            System.out.println("3 - Alterar sabor de pizza");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarPizzaAoPedido(scanner, pedidoEncontrado);
                    break;
                case 2:
                    removerPizzaDoPedido(scanner, pedidoEncontrado);
                    break;
                case 3:
                    alterarSaborPizza(scanner, pedidoEncontrado);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            // Exibir valor total do pedido após as alterações
            System.out.println("Valor total atualizado do pedido: R$ " + pedidoEncontrado.getValorTotal());
        } else {
            System.out.println("Pedido não encontrado. Tente novamente.");
        }
    }

    private static void adicionarPizzaAoPedido(Scanner scanner, Pedido pedidoEncontrado) {
        Pizza novaPizza = criarPizza(scanner);
        pedidoEncontrado.getPizzas().add(novaPizza);
        pedidoEncontrado.setValorTotal(somarPizzas(pedidoEncontrado.getPizzas()));
        System.out.println("Pizza adicionada com sucesso!");
    }

    private static void removerPizzaDoPedido(Scanner scanner, Pedido pedidoEncontrado) {
        List<Pizza> pizzas = pedidoEncontrado.getPizzas();
        if (pizzas.isEmpty()) {
            System.out.println("Não há pizzas para remover.");
            return;
        }

        System.out.println("Selecione a pizza para remover:");
        for (int i = 0; i < pizzas.size(); i++) {
            System.out.println((i + 1) + " - Pizza com sabores: " + pizzas.get(i).getSabores());
        }
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao > 0 && opcao <= pizzas.size()) {
            pizzas.remove(opcao - 1);
            pedidoEncontrado.setValorTotal(somarPizzas(pizzas));
            System.out.println("Pizza removida com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void alterarSaborPizza(Scanner scanner, Pedido pedidoEncontrado) {
        List<Pizza> pizzas = pedidoEncontrado.getPizzas();
        if (pizzas.isEmpty()) {
            System.out.println("Não há pizzas para alterar.");
            return;
        }

        System.out.println("Selecione a pizza para alterar os sabores:");
        for (int i = 0; i < pizzas.size(); i++) {
            System.out.println((i + 1) + " - Pizza com sabores: " + pizzas.get(i).getSabores());
        }
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao > 0 && opcao <= pizzas.size()) {
            pizzas.set(opcao - 1, criarPizza(scanner));
            pedidoEncontrado.setValorTotal(somarPizzas(pizzas));
            System.out.println("Pizza alterada com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static Cliente adicionarCliente(Scanner scanner) {
        System.out.println("Adicione um cliente...");
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o endereço do cliente: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite o telefone do cliente: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();

        return new Cliente(nome, endereco, telefone, email, 0);
    }

    private static void gerarRelatorio(List<Pedido> listaPedidos, double faturamentoTotal) {
        System.out.println("Gerar relatorio...");

        Map<String, Integer> saboresMaisPedidos = new HashMap<>();

        for (Pedido pedido : listaPedidos) {
            faturamentoTotal += pedido.getValorTotal();
            for (Pizza pizza : pedido.getPizzas()) {
                for (String sabor : pizza.getSabores()) {
                    saboresMaisPedidos.put(sabor, saboresMaisPedidos.getOrDefault(sabor, 0) + 1);
                }
            }
        }

        System.out.println("Faturamento total: R$ " + faturamentoTotal);
        System.out.println("Sabores mais pedidos:");
        saboresMaisPedidos.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue() + " pedidos"));
    }

    private static void gerarListaClientes(List<Cliente> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("Lista de clientes está vazia.");
        } else {
            int x = 1;
            for (Cliente cliente : listaClientes) {
                System.out.println("Cliente " + x);
                System.out.println(cliente.getNome());
                System.out.println(cliente.getEndereco());
                System.out.println(cliente.getTelefone());
                System.out.println();
                x++;
            }
        }
    }

    public static double calcularFrete(double distancia, int quantidadePizzas) {
        double precoPorKm = 2.0;
        double precoPorPizza = 1.5;

        return (distancia * precoPorKm) + (quantidadePizzas * precoPorPizza);
    }
}

package main;

import service.InventoryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryService service = new InventoryService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	System.out.println("Escolha uma opção abaixo para gerenciar produtos");
            System.out.println("\n|== Selecione um número ==|");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("5. Buscar Produto");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adicionarProduto(service, scanner);
                    break;
                case 2:
                    service.listProducts();
                    break;
                case 3:
                    atualizarProduto(service, scanner);
                    break;
                case 4:
                    removerProduto(service, scanner);
                    break;
                case 5:
                    buscarProduto(service, scanner);
                    break;
                case 6:
                    System.out.println("Finalizado!");
                    return;
                default:
                    System.out.println("Escolha uma opção de 1 a 6! Para iniciarmos.");
            }
        }
    }

    private static void adicionarProduto(InventoryService service, Scanner scanner) {
        try {
            System.out.print("Nome do Produto: ");
            String nome = scanner.nextLine();
            System.out.print("Categoria: ");
            String categoria = scanner.nextLine();
            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();
            System.out.print("Preço: ");
            double preco = scanner.nextDouble();

            service.addProduct(nome, categoria, quantidade, preco);
            System.out.println("Produto adicionado!");
        } catch (Exception e) {
            System.out.println("Produto não adicionado. Verifique informações");
            scanner.nextLine();
        }
    }

    private static void atualizarProduto(InventoryService service, Scanner scanner) {
        System.out.print("ID do Produto a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        service.updateProduct(id, scanner);
    }

    private static void removerProduto(InventoryService service, Scanner scanner) {
        System.out.print("ID do Produto a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        service.deleteProduct(id, scanner);
    }

    private static void buscarProduto(InventoryService service, Scanner scanner) {
        System.out.println("\nOpções de Busca:");
        System.out.println("1. Nome");
        System.out.println("2.Categoria");
        System.out.println("3.Quantidade");
        System.out.println("4.Preço");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Digite o Nome ou parte do Nome: ");
                String name = scanner.nextLine();
                service.searchByName(name);
                break;
            case 2:
                System.out.print("Digite a Categoria: ");
                String category = scanner.nextLine();
                service.searchByCategory(category);
                break;
            case 3:
                System.out.print("Digite a Quantidade: ");
                int quantity = scanner.nextInt();
                service.searchByQuantity(quantity);
                break;
            case 4:
                System.out.print("Digite o Preço máximo: ");
                double price = scanner.nextDouble();
                service.searchByPrice(price);
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }
}

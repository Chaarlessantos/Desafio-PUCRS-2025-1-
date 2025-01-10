package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryService {
    private List<Product> inventory = new ArrayList<>();
    private int nextId = 1;

   
    public void addProduct(String name, String category, int quantity, double price) {
        Product product = new Product(nextId++, name, category, quantity, price);
        inventory.add(product);
        System.out.println("Produto adicionado!");
    }


    public void listProducts() {
        if (inventory.isEmpty()) {
            System.out.println("Não há produtos na lista");
            return;
        }

        System.out.println("\nLista dos Produtos:");
        System.out.printf("%-5s %-20s %-15s %-10s %-10s\n", "ID", "Nome", "Categoria", "Qtd", "Preço");
        for (Product product : inventory) {
            System.out.printf("%-5d %-20s %-15s %-10d %-10.2f\n", product.getId(), product.getName(), product.getCategory(), product.getQuantity(), product.getPrice());
        }
    }

    
    public void updateProduct(int id, Scanner scanner) {
        Product product = findById(id);
        if (product == null) {
            System.out.println("Produto com ID " + id + " não encontrado ou inválido.");
            return;
        }

        System.out.println("\nAtualizando Produto: " + product.getName());

        System.out.print("Novo Nome: ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) product.setName(newName);

        System.out.print("Nova Categoria: ");
        String newCategory = scanner.nextLine();
        if (!newCategory.isEmpty()) product.setCategory(newCategory);

        System.out.print("Nova Quantidade (digite -1 para manter a atual): ");
        int newQuantity = scanner.nextInt();
        if (newQuantity >= 0) product.setQuantity(newQuantity);

        System.out.print("Novo Preço (digite -1 para manter o atual): ");
        double newPrice = scanner.nextDouble();
        if (newPrice >= 0) product.setPrice(newPrice);

        System.out.println("Produto foi atualizado!");
        scanner.nextLine();
    }

 
    public void deleteProduct(int id, Scanner scanner) {
        Product product = findById(id);
        if (product == null) {
            System.out.println("Produto com ID " + id + " não encontrado ou inválido.");
            return;
        }

        System.out.print("Realmente quer excluir o produto " + product.getName() + "? (digite: s/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("s")) {
            inventory.remove(product);
            System.out.println("Produto removido!");
        } else {
            System.out.println("Ação cancelada.");
        }
    }

   
    public void searchByName(String name) {
        System.out.println("\nNome contendo: " + name);
        inventory.stream()
                 .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                 .forEach(this::printProduct);
    }


    public void searchByCategory(String category) {
        System.out.println("\nResultados para Categoria: " + category);
        inventory.stream()
                 .filter(p -> p.getCategory().equalsIgnoreCase(category))
                 .forEach(this::printProduct);
    }


    public void searchByQuantity(int quantity) {
        System.out.println("\n Resultado para quantidade: " + quantity);
        inventory.stream()
                 .filter(p -> p.getQuantity() == quantity)
                 .forEach(this::printProduct);
    }


    public void searchByPrice(double maxPrice) {
        System.out.println("\nResultados para Preço até: " + maxPrice);
        inventory.stream()
                 .filter(p -> p.getPrice() <= maxPrice)
                 .forEach(this::printProduct);
    }

  
    private void printProduct(Product product) {
        System.out.printf("%-5d %-20s %-15s %-10d %-10.2f\n",
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getQuantity(),
                product.getPrice());
    }

  
    private Product findById(int id) {
        for (Product product : inventory) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}

package level3.datastructures.bst;

import java.util.Scanner;

public class BSTApp {

    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        int choice;
        int value;

        do {
            System.out.println("\n===== BINARY SEARCH TREE MENU =====");
            System.out.println("1. Insert Node");
            System.out.println("2. Delete Node");
            System.out.println("3. Search Node");
            System.out.println("4. Traversals");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter value to insert: ");
                    value = scanner.nextInt();
                    bst.root = bst.insert(bst.root, value);
                    System.out.println("Inserted!");
                }

                case 2 -> {
                    System.out.print("Enter value to delete: ");
                    value = scanner.nextInt();
                    bst.root = bst.delete(bst.root, value);
                    System.out.println("Deleted (if existed).");
                }

                case 3 -> {
                    System.out.print("Enter value to search: ");
                    value = scanner.nextInt();
                    boolean found = bst.search(bst.root, value);
                    System.out.println(found ? "Found!" : "Not Found!");
                }

                case 4 -> {
                    System.out.println("Inorder:");
                    bst.inorder(bst.root);

                    System.out.println("\nPreorder:");
                    bst.preorder(bst.root);

                    System.out.println("\nPostorder:");
                    bst.postorder(bst.root);
                }

                case 5 -> System.out.println("Exiting...");

                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        scanner.close();
    }
}


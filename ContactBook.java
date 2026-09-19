import java.util.Scanner;

// Contact class
class Contact {
    String name;
    String phone;

    Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

// BST Node
class Node {
    Contact contact;
    Node left;
    Node right;

    Node(Contact contact) {
        this.contact = contact;
        left = null;
        right = null;
    }
}

// Binary Search Tree
class ContactBST {

    Node root;

    // Insert contact
    Node insert(Node root, Contact contact) {

        if (root == null) {
            return new Node(contact);
        }

        if (contact.name.compareToIgnoreCase(root.contact.name) < 0) {
            root.left = insert(root.left, contact);
        } 
        else if (contact.name.compareToIgnoreCase(root.contact.name) > 0) {
            root.right = insert(root.right, contact);
        } 
        else {
            System.out.println("Contact already exists!");
        }

        return root;
    }

    void addContact(String name, String phone) {
        Contact contact = new Contact(name, phone);
        root = insert(root, contact);
        System.out.println("Contact added successfully.");
    }

    // Search contact
    Node search(Node root, String name) {

        if (root == null ||
            root.contact.name.equalsIgnoreCase(name)) {
            return root;
        }

        if (name.compareToIgnoreCase(root.contact.name) < 0) {
            return search(root.left, name);
        }

        return search(root.right, name);
    }

    void searchContact(String name) {

        Node result = search(root, name);

        if (result != null) {
            System.out.println("\nContact Found!");
            System.out.println("Name  : " + result.contact.name);
            System.out.println("Phone : " + result.contact.phone);
        } 
        else {
            System.out.println("Contact not found.");
        }
    }

    // Inorder traversal
    void inorder(Node root) {

        if (root != null) {
            inorder(root.left);

            System.out.println(
                root.contact.name + " - " +
                root.contact.phone
            );

            inorder(root.right);
        }
    }

    void displayContacts() {

        if (root == null) {
            System.out.println("Contact Book is empty.");
            return;
        }

        System.out.println("\n--- Contact List ---");
        inorder(root);
    }

    // Find minimum node
    Node findMin(Node root) {

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    // Delete contact
    Node delete(Node root, String name) {

        if (root == null) {
            return null;
        }

        if (name.compareToIgnoreCase(root.contact.name) < 0) {
            root.left = delete(root.left, name);
        } 
        else if (name.compareToIgnoreCase(root.contact.name) > 0) {
            root.right = delete(root.right, name);
        } 
        else {

            // Case 1: No child
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: One child
            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            // Case 3: Two children
            Node successor = findMin(root.right);

            root.contact = successor.contact;

            root.right = delete(
                root.right,
                successor.contact.name
            );
        }

        return root;
    }

    void deleteContact(String name) {

        if (search(root, name) == null) {
            System.out.println("Contact not found.");
            return;
        }

        root = delete(root, name);
        System.out.println("Contact deleted successfully.");
    }

    // Update contact
    void updateContact(String name, String newPhone) {

        Node result = search(root, name);

        if (result != null) {
            result.contact.phone = newPhone;
            System.out.println("Contact updated successfully.");
        } 
        else {
            System.out.println("Contact not found.");
        }
    }

    // Minimum contact
    void minimumContact() {

        if (root == null) {
            System.out.println("Contact Book is empty.");
            return;
        }

        Node min = findMin(root);

        System.out.println("\nMinimum Contact:");
        System.out.println(
            min.contact.name + " - " +
            min.contact.phone
        );
    }

    // Maximum contact
    void maximumContact() {

        if (root == null) {
            System.out.println("Contact Book is empty.");
            return;
        }

        Node current = root;

        while (current.right != null) {
            current = current.right;
        }

        System.out.println("\nMaximum Contact:");
        System.out.println(
            current.contact.name + " - " +
            current.contact.phone
        );
    }
}

// Main class
public class ContactBook {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContactBST bst = new ContactBST();

        while (true) {

            System.out.println("\n===== CONTACT BOOK =====");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Display Contacts");
            System.out.println("6. Minimum Contact");
            System.out.println("7. Maximum Contact");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter phone: ");
                    String phone = sc.nextLine();

                    bst.addContact(name, phone);
                    break;

                case 2:
                    System.out.print("Enter name to search: ");
                    name = sc.nextLine();

                    bst.searchContact(name);
                    break;

                case 3:
                    System.out.print("Enter name to update: ");
                    name = sc.nextLine();

                    System.out.print("Enter new phone: ");
                    phone = sc.nextLine();

                    bst.updateContact(name, phone);
                    break;

                case 4:
                    System.out.print("Enter name to delete: ");
                    name = sc.nextLine();

                    bst.deleteContact(name);
                    break;

                case 5:
                    bst.displayContacts();
                    break;

                case 6:
                    bst.minimumContact();
                    break;

                case 7:
                    bst.maximumContact();
                    break;

                case 8:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
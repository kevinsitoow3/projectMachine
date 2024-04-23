package projectMachine;

import java.util.Scanner;

public class Menu {
    public static void menu(Products product, Users user, UsersCRUD UsersCRUD) {
    	
        int option0;
        int authenticationStatus = 0;
        //uno de los muchos ejemplos de ciclos en el codigo:
        do {
            while (authenticationStatus != 1) {
                if (Users.authenticateUser()==true) {
                    System.out.println("Authentication successful! Welcome.");
                    authenticationStatus = 1;
                } else {
                    System.out.println("Authentication failed. Invalid username or password.\n");
                }
            }
            
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nMenu:");
            System.out.println("1. View products");
            System.out.println("2. CRUD Products");
            System.out.println("3. CRUD Users");
            System.out.println("4. Make a purchase");
            System.out.println("5. View transactions");
            System.out.println("6. View the maximum amount to buy");
            System.out.println("7. Deposit money to a user");
            System.out.println("8. Transfer money to another user");
            System.out.println("9. Change password");
            System.out.println("10. Log Out");
            System.out.println("0. Exit of the program");
            System.out.print("Enter the option: ");

            option0 = scanner.nextInt();

            switch (option0) {
                case 1:
                    Products.printProductList();
                    break;

                case 2:
                    while (true) {
                        System.out.println("\nPlease choose an option: ");
                        System.out.println("1. Create a new product.");
                        System.out.println("2. Consult product.");
                        System.out.println("3. Delete product.");
                        System.out.println("4. Update product.");
                        System.out.println("5. Go back to the main menu.");

                        int option2 = scanner.nextInt();

                        switch (option2) {
                            case 1:
                                product.createProduct();
                                break;
                            case 2:
                                System.out.println("\nWrite product code to search: ");
                                int code = scanner.nextInt();
                                Products.getProductByCode(code);
                                break;
                            case 3:
                                System.out.println("\nWrite product code to delete: ");
                                int code2 = scanner.nextInt();
                                product.deleteProduct(code2);
                                break;
                            case 4:
                                System.out.println("\nWrite product code to update: ");
                                int code3 = scanner.nextInt();

                                System.out.println("Write the new product name: ");
                                String newName = scanner.next();

                                System.out.println("Write the new product cost: ");
                                int newCost = scanner.nextInt();

                                System.out.println("Write the new product available quantity: ");
                                int newQuantity = scanner.nextInt();

                                product.updateProduct(code3, newName, newCost, newQuantity);
                                break;
                            case 5:
                                System.out.println("\nGoing back to the main menu....");
                                break;
                            default:
                                System.out.println("\nPlease choose the correct option.");
                                break;
                        }

                        if (option2 == 5) {
                            break; // si el usuario escoge 5 vuelve al menu principal
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        System.out.println("\nPlease choose an option: ");
                        System.out.println("1. Show users.");
                        System.out.println("2. Create a new user.");
                        System.out.println("3. Consult user.");
                        System.out.println("4. Delete user.");
                        System.out.println("5. Update user.");
                        System.out.println("6. Go back to the main menu.");

                        int option3 = scanner.nextInt();

                        switch (option3) {
                            case 1:
                                Users.showUserList();
                                break;
                            case 2:
                            	UsersCRUD.createUser();
                                break;
                            case 3:
                                System.out.println("\nWrite user ID to search: ");
                                int id = scanner.nextInt();
                                user.getUserById(id);
                                break;
                            case 4:
                                System.out.println("\nWrite user ID to delete: ");
                                int id2 = scanner.nextInt();
                                UsersCRUD.deleteUser(id2);
                                break;
                            case 5:
                                System.out.println("\nWrite user ID to update: ");
                                int id3 = scanner.nextInt();

                                System.out.println("Write the new username: ");
                                String newNameUser = scanner.next();

                                System.out.println("Write the new user's last name: ");
                                String newLastName = scanner.next();

                                System.out.println("Write the new user's available cash: ");
                                int newAvailableCash = scanner.nextInt();
                                
                                System.out.println("Write the new user's password: ");
                                String newPassword = scanner.next();

                                UsersCRUD.updateUser(id3, newNameUser, newLastName, newAvailableCash, newPassword);
                                break;
                            case 6:
                                System.out.println("\nGoing back to the main menu....");
                                break;
                            default:
                                System.out.println("\nPlease choose the correct option.");
                                break;
                        }

                        if (option3 == 6) {
                            break; // si el usuario escoge 6 vuelve al menu principal
                        }
                    }
                    break;

                case 4:
                    while (true) {
                        System.out.println("\nPlease choose an option:");
                        System.out.println("1. Buy a product.");
                        System.out.println("2. Go back to the main menu.");

                        int option4 = scanner.nextInt();

                        switch (option4) {
	                        case 1:
	                            System.out.println("Choose a product to buy:");
	                            Products.printProductList();
	                            int productCode = scanner.nextInt();
	                            Products selectedProduct = Products.getProductByCode(productCode);
	
	                            if (selectedProduct != null) {
	                                System.out.println("\nEnter the quantity you want to buy:");
	                                int quantity = scanner.nextInt();
	
	                                double[] values = Purchase.calculateValues(selectedProduct, quantity, option0);
	
	                                // Muestra el costo total antes de pedir el metodo de pago
	                                System.out.println("\nTotal amount to pay: $" + values[3]);
	
	                                System.out.println("\nPlease choose a payment method:");
	                                System.out.println("1. Coins");
	                                System.out.println("2. Card");
	
	                                int paymentMethod = scanner.nextInt();
	
	                                switch (paymentMethod) {
	                                    case 1:
	                                        // Para pago en moneda  
	                                    	int payment;
	                                        do {
	                                            System.out.println("Insert coins for payment: ");
	                                            payment = scanner.nextInt();

	                                            if (payment > 50000) {
	                                                System.out.println("The entered amount exceeds the maximum limit of $50,000. Please enter a lower amount or select a smaller number of products.");
	                                            }
	                                        } while (payment > 50000);
	
	                                        if (payment >= values[0]) {
	                                            Purchase.purchase(selectedProduct, quantity, 1, null, payment);
	                                        } else {
	                                            System.out.println("Insufficient payment. Please insert more coins.");
	                                        }
	                                        break;
	
	                                    case 2:
	                                        // Para pago en tarjeta
	                                        System.out.println("\nPlease choose a user:");
	                                        Users.showUserList();
	                                        int selectedUserId = scanner.nextInt();
	                                        Users selectedUser = new Users().getUserById(selectedUserId);
	
	                                        if (selectedUser != null) {
	                                            double cardPaymentAmount = values[3];
	                                            
	                                            if (cardPaymentAmount > 50000) {
	                                                System.out.println("The entered amount exceeds the maximum limit of $50,000. Please enter a lower amount or select a smaller number of products.");
	                                                break;
	                                            }
	
	                                            Purchase.purchase(selectedProduct, quantity, 2, selectedUser, cardPaymentAmount);
	                                            
	                                        } else {
	                                            System.out.println("Invalid user ID.");
	                                        }
	                                        break;
	
	                                    default:
	                                        System.out.println("Please choose a valid payment method.");
	                                        break;
	                                }
	                            } else {
	                                System.out.println("Invalid product code.");
	                            }
	                            break;

                            case 2:
                                // Vuelve al menu principal
                                break;

                            default:
                                System.out.println("Please choose a valid option.");
                                break;
                        }
                        // Sale del ciclo cuando la opcion seleccionada es 2
                        if (option4 == 2) {
                            break;
                        }
                    }
                    break;

                case 5:
                    Purchase.showTransactionLog();
                    break;
                case 6:
                	System.out.println("The maximum amount to buy is 50.000");
                	break;
                case 7:
                	Users.rechargeBalance();
                	break;
                case 8:
                	Users.transferMoney();
                	break;
                case 9:
                	Users.changePassword();
                	break;
                case 10:
                	System.out.println("You are logged out.\n");
                	authenticationStatus = 0;
                	break;
                case 999:
                	//Prueba para demostrar el uso de sobrecarga (Overloading) en el codigo
                	System.out.println("This is a test to demonstrate the use of overload in our code...");
                	Users.greetUser();
                	Users.greetUser("Juan");
                	
                	//Prueba para demostrar el uso de sobrescritura (Overriding) en el codigo
                	System.out.println("\nThis is a test to demonstrate the use of override in our code...");
                	System.out.println("From superclass:");
                	Users italianUser = new Users();
                	italianUser.greetInItalian();
                	System.out.println("From subclass:");
                	UsersCRUD italianUser2 = new UsersCRUD();
                	italianUser2.greetInItalian();
                	
                	//Prueba para demostrar el uso de interfaces en el codigo
                	int numClass;
                	numClass=0;
                	System.out.println("\nThis is a test that proves the existence of an interface in our code and the use of it...");
                	System.out.println("From the interface 2 methods are declared (greet and getClassInfo) and are used in the following classes in order:");
                	numClass++;
                	Products a = new Products();
                	System.out.println("\n1.Class Products.");
                	a.greet();
                	a.getClassInfo(numClass);
                	numClass++;
                	Purchase b = new Purchase();
                	System.out.println("\n2.Class Purchase.");
                	b.greet();
                	b.getClassInfo(numClass);
                	numClass++;
                    Users c = new Users();
                    System.out.println("\n3.Superclass Users.");
                	c.greet();
                	c.getClassInfo(numClass);
                	numClass++;
                	UsersCRUD d = new UsersCRUD();
                    System.out.println("\n3.Class Purchase.");
                	d.greet();
                	d.getClassInfo(numClass);
                	break;
                	
                case 0:
                    // Si el usuario escoge 0 se sale del ciclo
                    System.out.println("Exiting of the program... Bye.");
                    break;
                    
                default:
                    System.out.println("Please choose the correct option.");
                    break;
            }

        } while (option0 != 0);
    }
}

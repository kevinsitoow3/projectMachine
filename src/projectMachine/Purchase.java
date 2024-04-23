package projectMachine;

import java.util.ArrayList;
import java.util.List;

public class Purchase implements Machine{ //Aqui se ve el uso de interfaces donde se implementa la interfaz Machine
    private static List<String> transactionLog = new ArrayList<>();

    public static void giveChange(double change) {
        System.out.println("Change given: " + change + " coins");
        transactionLog.add("Change given: " + change + " coins");
    }

    public static void purchase(Products product, int quantity, int paymentMethod, Users user, double payment) {

        if (paymentMethod == 1) { // Pago en monedas
            if (payment >= product.cost * quantity) {
            	double change = (float) (payment - (product.cost * quantity) * 1.19);
                giveChange(change);
                if (dispenseProduct(product, quantity)==true) {
                    // Imprime recibo solo si el pago fue exitoso
                    printReceipt(product, quantity, 1, payment, change);
                }
            } else {
                System.out.println("Insufficient payment. Please insert more coins.");
            }
        }
        
        else if (paymentMethod == 2) { // Pago con tarjeta
            // se revisa si tiene duficiente dinero en la cuenta
            if (user.availableCash >= product.cost * quantity) {
                user.availableCash -= product.cost * quantity; //le descuenta ese dinero de la cuenta
                System.out.println("Payment successful with card. Product dispensed: " + product.nameProduct);
                transactionLog.add("Product dispensed: " + product.nameProduct + " - Quantity: " + quantity + " - Payment: Card");

                if (dispenseProduct(product, quantity)==true) {
                	// Imprime recibo solo si el pago fue exitoso
                    printReceipt(product, quantity, 2, payment, 0);
                }
            } else {
                System.out.println("Insufficient funds. Payment failed.");
            }
        } else {
            System.out.println("Invalid payment method.");
        }
    }

    public static boolean dispenseProduct(Products product, int quantity) {
        // Revisa si hay suficiente inventario del producto a dispensar
        if (product.availableQuantity >= quantity) {
            product.availableQuantity -= quantity; // Se descuenta esa cantidad del inventario disponible
            System.out.println("\nDispensing " + quantity + " " + product.nameProduct + "(s).");
            return true;
        } else {
            System.out.println("\nInsufficient inventory. Cannot dispense " + quantity + " " + product.nameProduct + "(s).");
            return false;
        }
    }

    public static void printReceipt(Products product, int quantity, int paymentMethod, double payment, double change) {
        double[] values = calculateValues(product, quantity, paymentMethod);

        System.out.println("\nReceipt:");
        System.out.println("1. Value per product for " + quantity + " " + product.nameProduct + "(s)-> ($" + values[0] + ") each product to $" + product.cost);
        System.out.println("2. Total discount: $" + values[1]);
        System.out.println("3. Value with IVA: $" + values[2]);
        System.out.println("4. Total purchase value: $" + values[3]);
        System.out.println("5. Payment received: $" + payment);
        System.out.println("6. Change given: $" + change);
    }

    public static double[] calculateValues(Products product, int quantity, int paymentMethod) {
        double valuePerProduct = product.cost * quantity;
        double discount = 0.0;  

        if (paymentMethod == 2) {
            discount = valuePerProduct * 0.1; // 10% de descuento si paga con tarjeta 
        } else {
            discount = 0.0; // NO hay descuento si paga con monedas
        }

        double valueWithIVA = valuePerProduct * 1.19; // 19% del IVA
        double totalPurchaseValue = valueWithIVA - discount;
        
        //Uso de ARREGLOS dentro de nuestro codigo:
        return new double[]{valuePerProduct, discount, valueWithIVA, totalPurchaseValue};
    }
    
    public static void showTransactionLog() {
        System.out.println("Transaction Log:");
        for (String transaction : transactionLog) {
            System.out.println(transaction);
        }
    }
    
    //Metodos que se implemetan desde la interfaz
    public void greet() {
    	System.out.println("Hi, from de class Purchase.");
    }
	public void getClassInfo(int numClass) {
		System.out.println("In the class #"+numClass+" (Purchase):");
	    System.out.println("It mainly does the actions that encompass the purchase such as calculating the cost of the product, \nselecting payment method, dispensing the product and displaying the transaction log.");
	}
}

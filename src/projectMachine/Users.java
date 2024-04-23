package projectMachine;

// Libraries used
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Users implements Machine { //Aqui se ve el uso de interfaces donde se implementa la interfaz Machine
    // ATRIBUTOS
    int id, availableCash;
    private String nameUser, lastName, password; //Aqui se hace uso de ENCAPSULAMIENTO al declarar las variables como privadas y acceder a ellas por medio de set y get.
    static List<Users> listUser = new ArrayList<>();
    
    //Uso de getters y setters en el codigo:
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAvailableCash() {
		return availableCash;
	}
	public void setAvailableCash(int availableCash) {
		this.availableCash = availableCash;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// COMPORTAMIENTOS/ACCIONES
	// Inicializar los usuarios predefinidos
    public static void initializeUsers() {
        Users user1 = new Users();
        user1.id = 1;
        user1.nameUser = "Juan";
        user1.lastName = "Lozano";
        user1.availableCash = 100000;
        user1.password = "12345";

        Users user2 = new Users();
        user2.id = 2;
        user2.nameUser = "Kevin";
        user2.lastName = "Correa";
        user2.availableCash = 25000;
        user2.password = "54321";

        listUser.add(user1);
        listUser.add(user2);
    }

    // Mostrar lista de usuarios 
    public static void showUserList() {
        if (listUser.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            System.out.println("\nUser List:");
            for (Users user : listUser) {
                System.out.println("ID: " + user.id);
                System.out.println("Name: " + user.nameUser);
                System.out.println("Last Name: " + user.lastName);
                System.out.println("Available Cash: " + user.availableCash);
                System.out.println("------------------------");
            }
        }
    }

    // Buscar usuario
    public Users getUserById(int id) {
    	//uno de los muchos ejemplos de ciclos en el codigo:
        for (Users user : listUser) {
            if (user.id == id) {
                System.out.println("\nUser found:");
                System.out.println("ID: " + user.id);
                System.out.println("Name: " + user.nameUser);
                System.out.println("Last Name: " + user.lastName);
                System.out.println("Available Cash: " + user.availableCash);
                return user;
            }
        }
        System.out.println("User with id " + id + " not found.");
        return null;
    }
    
    //Autenticar usuario en el menu
    public static boolean authenticateUser() {
        Scanner authenticate = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username1 = authenticate.nextLine();
        System.out.print("Enter password: ");
        String password1 = authenticate.nextLine();
        
    	for (Users user : listUser) {
            if (username1.equals(user.nameUser)&& password1.equals(user.password)) {
                return true;
            }
        }
        return false;
    }
    
    //recargar dinero de un usuario
    public static void rechargeBalance() {
        Scanner recharge = new Scanner(System.in);
        
        // Mostrar la lista de usuarios para que el usuario pueda elegir a quién recargarle dinero
        showUserList();
        
        System.out.print("Enter the ID of the user you want to recharge: ");
        int userId = recharge.nextInt();
        
        Users userI = new Users();

        Users user = userI.getUserById(userId);
        
        if (user != null) {
            System.out.print("Enter the amount you want to recharge: $");
            int amount = recharge.nextInt();
            
            // Recargar el saldo del usuario
            user.availableCash += amount;
            System.out.println("Balance successfully recharged. New balance for user " + user.getNameUser() + ": $" + user.getAvailableCash());
        } else {
            System.out.println("User with ID " + userId + " not found.");
        }
    }
    
    public static void transferMoney() {
        Scanner transfer = new Scanner(System.in);
        
        // Mostrar la lista de usuarios para que el usuario pueda elegir quién transferirá el dinero
        showUserList();
        
        System.out.print("Enter the ID of the user who will transfer money: ");
        int senderId = transfer.nextInt();
        
        Users userT = new Users();
        // Buscar al usuario que realizará la transferencia por su ID
        Users sender = userT.getUserById(senderId);
        
        if (sender != null) {
            System.out.print("Enter the ID of the user who will receive the money: ");
            int receiverId = transfer.nextInt();
            
            Users userR = new Users();
            // Buscar al usuario que recibirá el dinero por su ID
            Users receiver = userR.getUserById(receiverId);
            
            if (receiver != null) {
                System.out.print("Enter the amount you want to transfer: $");
                int amount = transfer.nextInt();
                
                // Verificar si el usuario tiene suficiente saldo para transferir
                if (sender.getAvailableCash() >= amount) {
                    // Transferir dinero
                    sender.setAvailableCash(sender.getAvailableCash() - amount);
                    receiver.setAvailableCash(receiver.getAvailableCash() + amount);
                    System.out.println("Transfer successful! $" + amount + " transferred from user " + sender.getNameUser() + " to user " + receiver.getNameUser() + ".");
                } else {
                    System.out.println("Insufficient funds. Unable to complete the transfer.");
                }
            } else {
                System.out.println("Receiver user with ID " + receiverId + " not found.");
            }
        } else {
            System.out.println("Sender user with ID " + senderId + " not found.");
        }
    }

    public static void changePassword() {
        Scanner input = new Scanner(System.in);
        
        // Mostrar la lista de usuarios para que el usuario pueda elegir a quién cambiar la contraseña
        showUserList();
        
        System.out.print("Enter the ID of the user whose password you want to change: ");
        int userId = input.nextInt();
        
        Users userC = new Users();
        // Buscar al usuario por su ID
        Users user = userC.getUserById(userId);
        
        if (user != null) {
            System.out.print("Enter the new password: ");
            String newPassword = input.next();
            
            // Actualizar la contraseña del usuario
            user.setPassword(newPassword);
            System.out.println("Password changed successfully for user " + user.getNameUser() + ".");
        } else {
            System.out.println("User with ID " + userId + " not found.");
        }
    }

    
    //Metodos que se implemetan desde la interfaz
    public void greet() {
    	System.out.println("Hi, from de superclass Users.");
    }
	public void getClassInfo(int numClass) {
		System.out.println("In the superclass #"+numClass+" (Users):");
	    System.out.println("It mainly performs the actions that include initializing pre-created users, displaying the list of users, and searching for a user by their ID.");
	}
	
	//uso de sobrecarga (Overloading):
	public static void greetUser() {
		System.out.println("Hi user.");
	}
	public static String greetUser(String name) {
		System.out.println("hi user "+ name);
		return " ";
	}
	
	//uso de sobrescritura (Overriding):
	// Se crea el metodo desde la Superclase y este metodo será sobreescrito en la subclase
	void greetInItalian() {
        System.out.println("Ciao utente, dalla superclasse");
    }
}

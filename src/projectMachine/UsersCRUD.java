package projectMachine;

import java.util.Scanner;

public class UsersCRUD extends Users implements Machine { //Aqui se ve el uso de herencia donde esta clase hereda variables de la superclase Users
	//Aqui arriba tambien se ve el uso de interfaces donde se implementa la interfaz Machine
    
	// Funciones CRUD 

    // Crear usuario
    public void createUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write user ID: ");
        id = scanner.nextInt();

        System.out.println("Write name user: ");
        setNameUser(scanner.next());

        System.out.println("Write last name user: ");
        setLastName(scanner.next());

        System.out.println("Write available cash user: ");
        availableCash = scanner.nextInt();
        
        System.out.println("Set a password for the user: ");
        setPassword(scanner.next());

        Users newUser = new Users();
        newUser.id = id;
        newUser.setNameUser(getNameUser());
        newUser.setLastName(getLastName());
        newUser.availableCash = availableCash;
        newUser.setPassword(getPassword());

        listUser.add(newUser);
        System.out.println("User created.");
    }

    // Borrar usuario
    public void deleteUser(int id) {
        Users userDelete = getUserById(id);
        if (userDelete != null) {
            listUser.remove(userDelete);
            System.out.println("User deleted.");
        }
    }

    // Actualizar usuario
    public void updateUser(int id, String newNameUser, String newLastName, int newAvailableCash, String newPassword) {
        Users userUpdate = getUserById(id);
        if (userUpdate != null) {
            userUpdate.id = id;
            userUpdate.setNameUser(newNameUser);
            userUpdate.setLastName(newLastName);
            userUpdate.availableCash = newAvailableCash;
            userUpdate.setPassword(newPassword);
            System.out.println("\nThe user has been updated.");
            System.out.println("\nThe new details are: ");
            Users userUpdate2 = getUserById(id);
        }
    }
    
    //Metodos que se implemetan desde la interfaz
    public void greet() {
    	System.out.println("Hi, from the UserActions class that extends the superclass Users.");
    }
	public void getClassInfo(int numClass) {
		System.out.println("In the class #"+numClass+" (UserActions):");
	    System.out.println("This class covers user CRUD actions.");
	}
	
	//uso de sobrescritura (Overriding):
	@Override
	void greetInItalian() {
        System.out.println("Buongiorno utente, dalla sottoclasse");
    }//Se sobreescribe el metodo en la subclase con diferente implementacion
}

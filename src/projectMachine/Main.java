package projectMachine;

public class Main {

	public static void main(String[] args) {
        Products product = new Products();
        Users user = new Users();
        UsersCRUD UsersCRUD = new UsersCRUD();

        // Se inicializan los productos y los usuarios
        Users.initializeUsers();
        Products.initializeProducts();

        System.out.println("\nWelcome to the Project Machine Program!");
        Menu.menu(product, user, UsersCRUD);

	}
}

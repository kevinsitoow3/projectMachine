package projectMachine;
//interfaz llamada Machine que se implementa en 4 clases utiles las otras son para funcionamiento del codigo. 
public interface Machine {//Aqui se evidencia un ejemplo de BAJA cohesion porque un metodo como saludar no tiene nada que ver con la clase machine
	void greet();
	void getClassInfo(int numClass);
}

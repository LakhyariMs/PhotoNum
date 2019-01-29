package Views;

public class MenuTest {

	private void mainMenu() {
		Menu menu = new Menu();
		menu.setTitle("*** Menu Principal ***");
		menu.addItem(new MenuItem("Option A", this, "sousMenuA"));
		menu.addItem(new MenuItem("Option B", this, "sousMenuB"));
		menu.addItem(new MenuItem("Option C", this, "performOptionC"));
		menu.execute();
	}

	public void sousMenuA() {
		Menu menu = new Menu();
		menu.setTitle("*** Je suis le titre de Sous Menu A ***");
		menu.addItem(new MenuItem("Option Aa", this, "performOptionAa"));
		menu.addItem(new MenuItem("Option Ab"));
		menu.execute();
	}
	
	public void performOptionAa() {
		System.out.println("Cuucou Option Aa");
		ConsoleUtils.pauseExecution();
	}

	public void sousMenuB() {
		Menu menu = new Menu();
		menu.setTitle("*** Sous Menu B ***");
		menu.execute();
	}

	public void performOptionC() {
		boolean confirm = ConsoleUtils.requestConfirmation();
		if (confirm)
			System.out.println("\nFaire Option C...");
		else
			System.out.println("\nOption C annulée!");
		ConsoleUtils.pauseExecution();
	}

	public static void main(String[] args) {
		new MenuTest().mainMenu();
	}
}
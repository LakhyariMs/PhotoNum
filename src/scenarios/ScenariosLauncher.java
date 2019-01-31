package scenarios;

import views.Menu;
import views.MenuItem;

public class ScenariosLauncher {
	
	private void mainMenu() {
		Menu menu = new Menu();
		menu.setTitle("*** Menu Scénarios ***");
		menu.addItem(new MenuItem("Scénario 1", this, "sousMenu1"));
		menu.addItem(new MenuItem("Scénario 2", this, "sousMenu2"));
		menu.addItem(new MenuItem("Scénario 3", this, "sousMenu3"));
		menu.addItem(new MenuItem("Scénario 4", this, "sousMenu4"));
		menu.execute();
	}
	
	public void sousMenu1() {
		Menu menu = new Menu();
		menu.setTitle("*** Choix Terminal ***");
		menu.addItem(new MenuItem("Terminal 1", new Scenario1(), "performScenarioT1"));
		menu.addItem(new MenuItem("Terminal 2", new Scenario1(), "performScenarioT2"));
		menu.execute();
	}
	
	public void sousMenu2() {
		Menu menu = new Menu();
		menu.setTitle("*** Choix Terminal ***");
		menu.addItem(new MenuItem("Terminal 1", new Scenario2(), "performScenarioT1"));
		menu.addItem(new MenuItem("Terminal 2", new Scenario2(), "performScenarioT2"));
		menu.execute();
	}
	
	public void sousMenu3() {
		Menu menu = new Menu();
		menu.setTitle("*** Choix Terminal ***");
		menu.addItem(new MenuItem("Terminal 1", new Scenario3(), "performScenarioT1"));
		menu.addItem(new MenuItem("Terminal 2", new Scenario3(), "performScenarioT2"));
		menu.execute();
	}
	
	public void sousMenu4() {
		Menu menu = new Menu();
		menu.setTitle("*** Choix Terminal ***");
		menu.addItem(new MenuItem("Terminal 1", new Scenario4(), "performScenarioT1"));
		menu.addItem(new MenuItem("Terminal 2", new Scenario4(), "performScenarioT2"));
		menu.execute();
	}
	
	public static void main(String[] args) {
		
		new ScenariosLauncher().mainMenu();
	}
}

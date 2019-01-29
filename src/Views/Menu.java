package Views;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu {
	private static BufferedReader in;
	private static Menu rootMenu;
	private List<MenuItem> itemList;
	private MenuItem exitItem;
	private String title;
	private boolean isRootMenu;

	public Menu() {
		this.itemList = new ArrayList<MenuItem>();

		if (Menu.rootMenu == null) {
			Menu.in = new BufferedReader(new InputStreamReader(System.in));
			Menu.rootMenu = this; 
			this.isRootMenu = true; 
			this.setTitle("Menu");

			this.exitItem = new MenuItem("Sortie"); 
		} else {
			this.setTitle("Sous Menu");
			this.exitItem = new MenuItem("Retour");
		}
		this.exitItem.setExitItem(true);
	}

	public void addItem(MenuItem item) {
		this.itemList.add(item);
	}

	public void execute() {
		MenuItem item = null;
		do {
			this.print();
			item = this.getUserInput();
			item.invoke();
		} while (!item.isExitItem());
	}

	private int getExitIndex() {
		return this.itemList.size() + 1;
	}

	@SuppressWarnings("finally")
	private MenuItem getUserInput() {
		MenuItem item = null;
		String input = null;

		try {
			input = Menu.in.readLine();
			int option = Integer.parseInt(input); // Throws NumberFormatException for non-numberic input

			if (option < 1 || option > this.getExitIndex())
				throw new NumberFormatException(); // Taking advantage of above to catch out-of-range numbers

			if (option == this.getExitIndex()) {
				item = exitItem;

				// If 'this' menu is the root menu, close the input stream
				if (this.isRootMenu) {
					item.setExitItem(false);
					boolean confirm = ConsoleUtils.requestConfirmation();
					if (confirm) {
						item.setExitItem(confirm);	
						System.out.println("\nAu revoir !\n");
						Menu.in.close();
					}
				}
			} else
				item = itemList.get(option - 1); // -1 as itemList(0) -> item 1 in printed menu
		} catch (NumberFormatException ex) {
			System.out.println("\nErreur: '" + input + "' ce n'est pas une option de menu valide!");
			item = new MenuItem(null); // Return a dummy menu item which ensures it cannot be invoked
			ConsoleUtils.pauseExecution();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			return item;
		}
	}

	private void print() {
		StringBuilder sb = new StringBuilder();

		sb.append("\n");

		if (this.title.equals("") == false)
			sb.append(this.title + "\n");

		for (int i = 0; i < this.itemList.size(); i++)
			sb.append("\n" + (i + 1) + "... " + this.itemList.get(i).getLabel());

		sb.append("\n" + getExitIndex() + "... " + exitItem.getLabel());
		sb.append("\n> ");

		System.out.print(sb.toString());
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String toString() {
		return "menu=[" + this.title + "]  items=" + this.itemList.toString();
	}
}
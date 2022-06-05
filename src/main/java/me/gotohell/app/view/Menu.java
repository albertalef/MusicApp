package me.gotohell.app.view;

import me.gotohell.app.util.ConsoleInput;
import me.gotohell.app.util.ConsoleTable;

public class Menu {

	private final String[] options;
	private final ConsoleTable menuTable;


	public Menu(String... options){
		this.options = options;
		this.menuTable = loadMenuTable();
	}

	private ConsoleTable loadMenuTable(){
		ConsoleTable table = new ConsoleTable("Buscar por:", 34);

		for(int index = 0; index < options.length; index++) table.addCell(options[index], String.valueOf(index));
		return table;
	}

	public int getOption(){
		menuTable.show();
		return ConsoleInput.getNumberFromUserInRange(Integer.class,
				"Digite uma função a ser executada:",
				0,
				options.length - 1);
	}

}

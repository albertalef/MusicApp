package me.gotohell.app;


import me.gotohell.app.util.ConsoleInput;
import me.gotohell.app.util.ConsoleTable;
import me.gotohell.app.view.Menu;
import me.gotohell.app.view.MusicController;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class App {

	private static final Map<String, Runnable> COMMANDS;
	private static final String[] OPTIONS;
	private static final ConsoleTable HEADER;

	static {
		final Map<String, Runnable> commands = new LinkedHashMap<>();

		commands.put("Nome", MusicController::findByName);
		commands.put("Autor", MusicController::findByAuthor);
		commands.put("Genero", MusicController::findByGenre);
		commands.put("Trecho", MusicController::findByLyric);
		commands.put("Intervalo de tempo", MusicController::findByYearInterval);
		commands.put("Sair", () -> System.exit(0));

		COMMANDS = Collections.unmodifiableMap(commands);
		OPTIONS = COMMANDS.keySet().toArray(new String[0]);

		HEADER = new ConsoleTable("Algorítimo de Músicas", 70)
				.addCell("",
						"Bem vindo!", "Neste aplicativo você consegue buscar músicas no nosso",
						"bando de dados. Utilize a vontade!");
	}

	public void run() {
		HEADER.show();

		Menu menu = new Menu(OPTIONS);

		do {

			int optionId = menu.getOption();
			COMMANDS.get(OPTIONS[optionId]).run();

		} while(ConsoleInput.getNumberFromUser(Integer.class, "Deseja continuar usando o aplicativo? (1 - Sim / 0 - Não)") == 1);
	}
}

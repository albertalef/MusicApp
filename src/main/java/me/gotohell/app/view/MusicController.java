package me.gotohell.app.view;

import me.gotohell.app.model.Music;
import me.gotohell.app.repository.MusicRepository;
import me.gotohell.app.util.ConsoleInput;

import java.util.Calendar;
import java.util.List;
import java.util.function.Function;

public class MusicController {

	private MusicController(){}

	private static final int ACTUAL_YEAR = Calendar.getInstance().get(Calendar.YEAR);

	public static void findByGenre() {
		find("Digite um genero a ser pesquisado:", MusicRepository::filterByGenre); }

	public static void findByName() {
		find("Digite um nome a ser pesquisado:", MusicRepository::filterByName);
	}
	public static void findByAuthor() {
		find("Digite um autor a ser pesquisado:", MusicRepository::filterByAuthor);
	}
	public static void findByLyric() {
		find("Digite um trecho a ser pesquisado:", MusicRepository::filterByLyric);
	}

	public static void findByYearInterval() {
		do {
			int firstYear = ConsoleInput.getNumberFromUserInRange(Integer.class,
					"Digite o primeiro ano", 1500, ACTUAL_YEAR);
			int secondYear = ConsoleInput.getNumberFromUserInRange(Integer.class,
					"Digite o segundo ano", firstYear, ACTUAL_YEAR);

			List<Music> musicList = MusicRepository.filterByInterval(firstYear, secondYear);

			if(!musicList.isEmpty()) MusicPrinter.printMusicList(musicList);
			else System.out.println("\n[!!!] Música não encontrada! [!!!]");

		} while(ConsoleInput.getNumberFromUser(Byte.class, "Deseja continuar? (1 = Sim / 0 = Não)") == 1);
	}

	private static void find( String mainText, Function<String, List<Music>> findFunction ) {
		do {
			String userInput = ConsoleInput.getLineFromUser(mainText);

			List<Music> musicList = findFunction.apply(userInput);

			if(!musicList.isEmpty()) MusicPrinter.printMusicList(musicList);
			else System.out.println("\n[!!!] Música não encontrada! [!!!]");

		} while(ConsoleInput.getNumberFromUser(Byte.class, "Deseja continuar? (1 = Sim / 0 = Não)") == 1);
	}

}

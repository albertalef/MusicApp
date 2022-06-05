package me.gotohell.app.view;

import me.gotohell.app.model.Music;
import me.gotohell.app.util.ConsoleTable;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class MusicPrinter {

	private MusicPrinter(){}

	public static void printMusic( Music music ){
		printMusicWithText(music, "");
	}
	public static void printMusicList( List<Music> musicList ){
		if(musicList.size() == 1) {
			printMusic(musicList.get(0));
			return;
		}
		for(int i = 0; i < musicList.size(); i++) {
			printMusicWithText(musicList.get(i), String.valueOf(i + 1));
		}
	}
	public static void printMusicWithText( Music music, String index ){
		if(music == null) return;

		String musicHeader = String.format(" Música %s", index.equals("") ? "" : index + ' ');

		ConsoleTable consoleTable = new ConsoleTable(musicHeader, 33);

		consoleTable
				.addCell("Nome", music.getName())
				.addCell("Autor", music.getAuthor())
				.addCell("Gênero", StringUtils.capitalize(music.getGenre()))
				.addCell("Lançamento", String.valueOf(music.getYear()));

		consoleTable.show();

	}
}

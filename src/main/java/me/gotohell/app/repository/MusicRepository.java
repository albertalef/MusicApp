package me.gotohell.app.repository;

import me.gotohell.app.model.Music;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MusicRepository {

	private MusicRepository(){}

	private static final List<Music> musicList = new MusicLoader().loadMusics();

	public static List<Music> 	filterByName(String name){
		return filter(music -> StringUtils.containsIgnoreCase(music.getName(), name));
	}
	public static List<Music> filterByAuthor(String author){
		return filter(music -> StringUtils.containsIgnoreCase(music.getAuthor(), author));
	}
	public static List<Music> filterByGenre(String genre){
		return filter(music -> StringUtils.containsIgnoreCase(music.getGenre(), genre));
	}
	public static List<Music> filterByLyric(String lyricStep){
		return filter(music -> StringUtils.containsIgnoreCase(music.getLyrics(), lyricStep));
	}
	public static List<Music> filterByInterval(int year1, int year2){
		int higher = Math.max(year1, year2);
		int lower = Math.min(year1, year2);

		return filter(music -> (music.getYear() >= lower) && (music.getYear() <= higher));
	}
	public static List<Music> findAll(){
		return musicList;
	}
	public static  List<Music> filter( Predicate<Music> filter ){
		List<Music> result = new ArrayList<>();
		for(Music music : musicList) if(filter.test(music)) result.add(music);
		return result;
	}

}

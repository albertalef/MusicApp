package me.gotohell.app.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.gotohell.app.model.Music;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MusicLoader {

	private final Reader musicsFileReader;
	private final Gson gson;


	public MusicLoader() {
		try {
			URL musicsResourceURL = this.getClass().getResource("/musics/musics.json");
			if(musicsResourceURL == null) throw new NullPointerException();
			musicsFileReader = new InputStreamReader(musicsResourceURL.openStream());
			gson = new Gson();
		}catch(IOException e) {
			throw new NullPointerException();
		}
	}

	public List<Music> loadMusics(){
		return gson.fromJson(musicsFileReader, TypeToken.getParameterized(ArrayList.class, Music.class).getType());
	}
}

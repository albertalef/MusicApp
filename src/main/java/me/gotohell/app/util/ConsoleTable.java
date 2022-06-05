package me.gotohell.app.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleTable {

	private String name = null;
	private int width = 30;
	private final Map<String, String[]> cells;

	public ConsoleTable( String name, int width ) {
		setName(name);
		setWidth(width);
		this.cells = new LinkedHashMap<>();
	}

	public ConsoleTable addCell( String label, String... values ) {
		cells.put(label, values);
		return this;
	}
	public void resetCells(){
		cells.clear();
	}
	public void show() {
		Iterator<String> labelIterator = cells.keySet().iterator();

		String centeredHeader = StringUtils.center(" " + name + " ", width, '=');
		String footer = '*' + getLineFromLetters('=', width) + '*';
		String line = getLineFromLetters('-', width + 2);

		System.out.println("\n*" + centeredHeader + '*');

		if(!labelIterator.hasNext()) return;

		do {
			String label = labelIterator.next();
			String[] values = cells.get(label);


			String[] centeredValues =  Arrays.stream(values).map(value -> StringUtils.center(value, width)).toArray(String[]::new);

			if(!label.isEmpty()){
				String centeredLabel = StringUtils.center(label + ':', width);
				System.out.println('|' + centeredLabel + '|');
			}

			for(String centeredValue : centeredValues) System.out.println('|' + centeredValue + '|');

			if(labelIterator.hasNext()) System.out.println(line);
		} while(labelIterator.hasNext());

		System.out.println(footer + '\n');
	}

	private String getLineFromLetters( char pattern, int width){
		return new String(new char[width]).replace('\0', pattern);
	}
	public void setName( String name ) {
		if(!name.equals("")) this.name = name;
	}
	public void setWidth( int width ) {
		if(width > name.length() + 2) this.width = width;

	}


}

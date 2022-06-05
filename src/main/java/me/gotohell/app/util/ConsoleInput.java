package me.gotohell.app.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ConsoleInput {
	private static final Scanner sc = new Scanner(System.in);

	private ConsoleInput(){}

	public static String getLineFromUser( String text){
		System.out.print("\n" + text + "\n>> ");
		return sc.nextLine();
	}
	@SuppressWarnings("unchecked")
	public static <T extends Number> T getNumberFromUser(Class<T> numberClass, String text) {
		String mainText = text;
		while (true) {
			System.out.print("\n" + mainText + "\n>> ");
			try {
				String valueInString = sc.next();
				Method parseNumberMethod = numberClass.getMethod("valueOf", String.class);
				sc.nextLine();
				return (T) parseNumberMethod.invoke(null, valueInString);
			} catch (NumberFormatException | InvocationTargetException ex) {
				mainText = "Erro de sintaxe, tente novamente!";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static <T extends Number & Comparable<T>> T getNumberFromUserInRange(Class<T> numberClass, String text, T from, T to){
		String mainText = text;
		T choice;
		while(true){
			choice = getNumberFromUser(numberClass, mainText);
			if( choice.compareTo(from) >= 0 && choice.compareTo(to) <= 0) return choice;
			mainText = "Número fora do esperado, esperado: " + from + '-' + to;
		}
	}
}

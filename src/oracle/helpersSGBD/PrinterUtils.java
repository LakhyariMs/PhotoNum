package oracle.helpersSGBD;

import java.util.ArrayList;

public class PrinterUtils {

	public static <T> void print(ArrayList<T> list) {
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (T o: list) {
			sb.append("n : " + i + " -> ");
			sb.append(o.toString() + " \n");
			i++;
		}
		System.out.println(sb.toString());
	}
}

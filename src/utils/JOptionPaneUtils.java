package utils;

import javax.swing.JOptionPane;

public class JOptionPaneUtils {

	public static void alert(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public static boolean confirm(String message) {
		int i = JOptionPane.showConfirmDialog(null, message);
		return i == 0 ? true : false;
	}
	
	public static String inputDialog(String question) {
		return JOptionPane.showInputDialog(question);
	}
	
}

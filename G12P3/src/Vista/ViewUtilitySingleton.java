package Vista;

import java.awt.Dimension;

import javax.swing.Box.Filler;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class ViewUtilitySingleton {
private static ViewUtilitySingleton utility = null;
	
	public static ViewUtilitySingleton getUtility() {
		if(utility == null) {
			utility = new ViewUtilitySingletonImp(); 
		}
		return utility;
	}

	public abstract JTextField createDefaultJTextField(String valordefault);
	public abstract JTextField createDefaultJTextField(String valordefault, Dimension dim);
	
	public abstract JLabel createDefaultJlabel(String message);
	
	protected abstract JButton createButton(String texto, int width ,int height);
	
	protected abstract JTable createTable(int filas, int columnas, String[] columns);
	
	public abstract Filler createDefaultBoxFiller(int x,int y);
	
	public abstract JTextArea createDefaultJTextArea(String msg,int x,int y);

}

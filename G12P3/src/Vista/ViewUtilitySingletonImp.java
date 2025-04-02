package Vista;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ViewUtilitySingletonImp extends ViewUtilitySingleton{//
	
	protected ViewUtilitySingletonImp(){
	}

	@Override
	public JLabel createDefaultJlabel(String message) {
		JLabel messageLabel = new JLabel(message);
		messageLabel.setFont(new Font("Italic", Font.PLAIN, 17));
		messageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	
		return messageLabel;
	}

	@Override
	protected JButton createButton(String texto, int width ,int height) {
		JButton button = new JButton(texto);
		button.setPreferredSize(new Dimension(width, height));
		return button;
	}
	
	protected JTable createTable(int filas, int columnas, String[] columns) {
		JTable table = new JTable();
		table.setModel(new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

			@Override
			public String getColumnName(int index) {
				return columns[index];
			}

			@Override
			public int getColumnCount() {
				return columnas;
			}

			@Override
			public int getRowCount() {
				return filas;
			}
		});
		return table;
	}

	@Override //https://victomanolo.wordpress.com/jtextfield-en-java/
	public JTextField createDefaultJTextField(String valordefault) {
		 JTextField ventana= new JTextField(10);
		 ventana.setLayout(new FlowLayout());
		 ventana.setSize(30, 10);
		 ventana.setText(valordefault);
		 ventana.addKeyListener(new KeyAdapter(){	
			 public void keyTyped(KeyEvent e){
				 char caracter = e.getKeyChar();
				 if(((caracter < '0') ||(caracter > '9')) &&(caracter != '\b') && (caracter != '.')){
					 e.consume();  // ignorar el evento de teclado
				 }
			 }
		 });
		 ventana.setPreferredSize(new Dimension(220,20));
		 ventana.setMaximumSize(new Dimension(220,20));
		 ventana.setEditable(true); 
		 ventana.setVisible(true);
		 return ventana;
	}

	@Override
	public Filler createDefaultBoxFiller(int x,int y) {
		return new Box.Filler(new Dimension(x/2,y/2), new Dimension(x,y), new Dimension(x*2,y*2));
	}

	@Override
	public JTextField createDefaultJTextField(String valordefault, Dimension dim) {
		JTextField ventana= new JTextField(10);
		 ventana.setLayout(new FlowLayout());
		 ventana.setSize(30, 10);
		 ventana.setText(valordefault);
		 ventana.setPreferredSize(dim);
		 ventana.setMaximumSize(dim);
		 ventana.setEditable(false); 
		 ventana.setVisible(true);
		 return ventana;
	}

	@Override
	public JTextArea createDefaultJTextArea(String msg, int x, int y) {
		JTextArea ventana=new JTextArea(x,y);
		ventana.setLayout(new FlowLayout());
		ventana.setFont(new Font("Arial", Font.PLAIN,20));
		ventana.setText(msg);
		ventana.setEditable(false); 
		ventana.setVisible(true);
		return ventana;
	}

}

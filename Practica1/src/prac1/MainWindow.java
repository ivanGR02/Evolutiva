package prac1;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

import AlgoritmoGenerico.AlgoritmoGenerico;
import Vista.ViewUtilitySingleton;
import population.strategy.CruceStrategy;
import population.strategy.MutationStrategy;
import population.strategy.SelectionStrategy;
import population.strategy.selection.EstocasticoUniversal;
import population.strategy.selection.Restos;
import population.strategy.selection.Ruleta;
import population.strategy.selection.TorneoAleatorio;
import population.strategy.selection.TorneoDeterminista;
import population.strategy.selection.Truncamiento;
import prac1.Funciones.Funcion;
import prac1.Funciones.Funcion1;
import prac1.Funciones.Funcion2;
import prac1.Funciones.Funcion3;
import prac1.Funciones.Funcion4;
import prac1.Strategy.Cruce.CruceAritmetico;
import prac1.Strategy.Cruce.CruceBlxAlpha;
import prac1.Strategy.Cruce.CruceMonopunto;
import prac1.Strategy.Cruce.CruceUniforme;
import prac1.Strategy.Mutation.MutationBasica;
import prac1.cromosoma.BinaryCromosoma;
import prac1.cromosoma.Cromosoma;
import prac1.cromosoma.DoubleCromosoma;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{//
	//private Controller _ctrl;
	private JButton run,reset;
	private Plot2DPanel evolution;
	private Plot2DPanel presionEvolutiva;
	private JComboBox<Funcion> tipoFuncion;
	private JComboBox<CruceStrategy> tipoCruces;
	private JComboBox<MutationStrategy> tiposmutar;
	private JComboBox<SelectionStrategy> selecciones;
	private JComboBox<Cromosoma> cromosomas;
	private JTextField insertePob;
	private JTextField inserteGeneraciones;
	private JTextField inserteError;
	private JTextField inserteCruce;
	private JTextField inserteMutar;
	private JTextField inserteElite;
	private JTextField solucionTextField;
	private JTextField inserteDimension;
	private JPanel boton;
	private AlgoritmoGenerico algoritmoGenerico;
	private JCheckBox calibre;
	
	public MainWindow() {
		super("Algoritmo evolutivo");
		algoritmoGenerico=new AlgoritmoGenerico(this);
		onInit();
		initGUI();
	}
	
	private void initGUI() {
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
	
		JPanel viewsPanel = new JPanel(new GridLayout(1, 1)); // el esqueleto de la interfaz que se divide en 2
		mainPanel.add(viewsPanel, BorderLayout.CENTER);
		
		
		JPanel variablePanel = new JPanel(); // ponemos las variables y las selecciones
		variablePanel.setLayout(new BoxLayout(variablePanel, BoxLayout.Y_AXIS));
		mainPanel.add(variablePanel,BorderLayout.WEST);
		//viewsPanel.add(variablePanel);
		
		//solucionPanel
		JPanel solucion=new JPanel();
		solucion.setLayout(new BoxLayout(solucion, BoxLayout.Y_AXIS));
		mainPanel.add(solucion,BorderLayout.SOUTH);
				
		
		JPanel graficasPanel = new JPanel(); // donde estarian las graficas
		graficasPanel.setLayout(new BoxLayout(graficasPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(graficasPanel);
		
		
		//solucion
		solucion.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,10));
		JPanel solucionContent=new JPanel();
		solucionContent.setLayout(new BoxLayout(solucionContent, BoxLayout.X_AXIS));
		solucion.add(solucionContent);
		JLabel solucionLabel = ViewUtilitySingleton.getUtility().createDefaultJlabel("      Solución :   ");
		solucionContent.add(solucionLabel);
		solucionTextField = ViewUtilitySingleton.getUtility().createDefaultJTextField("No hay solucion aún",new Dimension(1200,30));
		solucionContent.add(solucionTextField);
		solucion.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,15));
				
		// variables
		
		JLabel pctDimension = ViewUtilitySingleton.getUtility().createDefaultJlabel("Dimension, para Michalewicz");// poner el porcentaje cruce
		inserteDimension = ViewUtilitySingleton.getUtility().createDefaultJTextField("2");
		inserteDimension.setEditable(false);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		JLabel funcion = ViewUtilitySingleton.getUtility().createDefaultJlabel("Funciones");// poner cruces
		variablePanel.add(funcion);

		tipoFuncion.setPreferredSize(new Dimension(220, 36));;
		tipoFuncion.setMaximumSize(new Dimension(220, 36));;
		tipoFuncion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tipoFuncion.getSelectedItem() instanceof Funcion4) {
					inserteDimension.setEditable(true);
				}else {
					inserteDimension.setEditable(false);
				}
			}
		});
		variablePanel.add(tipoFuncion);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		JLabel cromosomastf = ViewUtilitySingleton.getUtility().createDefaultJlabel("Cromosoma Type");// poner cruces
		variablePanel.add(cromosomastf);
		cromosomas.setPreferredSize(new Dimension(220, 36));;
		cromosomas.setMaximumSize(new Dimension(220, 36));;
		variablePanel.add(cromosomas);
		
		//calibrar checkBox
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		JPanel calibrePanel = new JPanel(); // donde estarian las graficas
		calibrePanel.setLayout(new BoxLayout(calibrePanel, BoxLayout.X_AXIS));
		variablePanel.add(calibrePanel);
		JLabel calibreJL = ViewUtilitySingleton.getUtility().createDefaultJlabel("Calibrar Fitness : ");// poner las poblaciones
		calibreJL.setFont(new Font("Italic", Font.PLAIN, 16));
		calibrePanel.add(calibreJL);
		calibre=new JCheckBox();
		calibre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(calibre.isSelected()) {
					algoritmoGenerico.setCalibrar(true);
				}else {
					algoritmoGenerico.setCalibrar(false);
				}
			}
		});
		calibrePanel.add(calibre);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		variablePanel.add(pctDimension);
		variablePanel.add(inserteDimension);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		JLabel tamanoPob = ViewUtilitySingleton.getUtility().createDefaultJlabel("Tamano Población");// poner las poblaciones
		variablePanel.add(tamanoPob);
		insertePob = ViewUtilitySingleton.getUtility().createDefaultJTextField("100");
		variablePanel.add(insertePob);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		JLabel genPob = ViewUtilitySingleton.getUtility().createDefaultJlabel("   Generaciones de la poblacion   ");// poner las generaciones
		variablePanel.add(genPob);
		inserteGeneraciones = ViewUtilitySingleton.getUtility().createDefaultJTextField("100");
		variablePanel.add(inserteGeneraciones);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		JLabel error = ViewUtilitySingleton.getUtility().createDefaultJlabel("Valor de error");// poner el valor de error
		variablePanel.add(error);
		inserteError = ViewUtilitySingleton.getUtility().createDefaultJTextField("0.001");
		variablePanel.add(inserteError);

		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		JLabel seleccion = ViewUtilitySingleton.getUtility().createDefaultJlabel("Selecciones");// poner selecciones
		variablePanel.add(seleccion);

		selecciones.setPreferredSize(new Dimension(220, 36));
		selecciones.setMaximumSize(new Dimension(220, 36));
		variablePanel.add(selecciones);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		JLabel cruces = ViewUtilitySingleton.getUtility().createDefaultJlabel("Cruces");// poner cruces
		variablePanel.add(cruces);

		tipoCruces.setPreferredSize(new Dimension(220, 36));;
		tipoCruces.setMaximumSize(new Dimension(220, 36));;
		variablePanel.add(tipoCruces);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		JLabel pctCruce = ViewUtilitySingleton.getUtility().createDefaultJlabel("% cruce");// poner el porcentaje cruce
		variablePanel.add(pctCruce);
		inserteCruce = ViewUtilitySingleton.getUtility().createDefaultJTextField("0.6");
		variablePanel.add(inserteCruce);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		JLabel mutar = ViewUtilitySingleton.getUtility().createDefaultJlabel("Mutaciones");// poner mutaciones
		variablePanel.add(mutar);

		tiposmutar.setPreferredSize(new Dimension(220, 36));;
		tiposmutar.setMaximumSize(new Dimension(220, 36));;

		variablePanel.add(tiposmutar);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		JLabel pctmutar = ViewUtilitySingleton.getUtility().createDefaultJlabel("% mutar");// poner el porcentaje mutacion
		variablePanel.add(pctmutar);
		inserteMutar = ViewUtilitySingleton.getUtility().createDefaultJTextField("0.05");
		variablePanel.add(inserteMutar);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		JLabel elite = ViewUtilitySingleton.getUtility().createDefaultJlabel("elitismo %");// poner el valor de error
		variablePanel.add(elite);
		inserteElite = ViewUtilitySingleton.getUtility().createDefaultJTextField("0");
		variablePanel.add(inserteElite);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6));
		boton=new JPanel();
		boton.setLayout(new BoxLayout(boton, BoxLayout.X_AXIS));
		variablePanel.add(boton);
		//run button
		run = new JButton ("Run");
		run.setToolTipText("Click to Run");
		
		boton.add(run);
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Funcion current=(Funcion) tipoFuncion.getSelectedItem();
				current.setPrecision(Double.parseDouble(inserteError.getText()));
				if(current instanceof Funcion4) {
					((Funcion4) current).setNumGen(Integer.parseInt(inserteDimension.getText()));
				}
				algoritmoGenerico.setters(Integer.parseInt(insertePob.getText()),
						Double.parseDouble(inserteCruce.getText()),
						Double.parseDouble(inserteMutar.getText()), 
						Integer.parseInt(inserteGeneraciones.getText()),
						(SelectionStrategy)selecciones.getSelectedItem(), 
						(CruceStrategy)tipoCruces.getSelectedItem(),
						(MutationStrategy)tiposmutar.getSelectedItem(), 
						Double.parseDouble(inserteElite.getText()),
						current,(Cromosoma)cromosomas.getSelectedItem()
						);
				algoritmoGenerico.run();
			}
		});
		boton.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(15,0));
		reset = new JButton ("Reset");
		reset.setToolTipText("Click to reset variable");
		boton.add(reset);
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onReset();
			}
		});
		
		// graficas
		evolution=new Plot2DPanel();
		presionEvolutiva=new Plot2DPanel();
		JPanel mapView = createViewPanel(evolution, "Evolution");// pongamos la grafica de la funcion
		mapView.setPreferredSize(new Dimension(1200, 410));
		mapView.setMaximumSize(new Dimension(1400,410));
		graficasPanel.add(mapView);
		JPanel mapByRoadView = createViewPanel(presionEvolutiva, "Presion Evolutiva");// grafica de la presión selectiva
		mapByRoadView.setPreferredSize(new Dimension(1200, 410));
		mapByRoadView.setMaximumSize(new Dimension(1400,410));
		graficasPanel.add(mapByRoadView);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel createViewPanel(JComponent c, String title) {
		JPanel p = new JPanel( new BorderLayout() );
		p.add(new JScrollPane(c));
		return p;
	}
	
	private void onReset() {
		insertePob.setText("100");
		inserteGeneraciones.setText("100");
		inserteError.setText("0.001");
		inserteCruce.setText("0.6");
		inserteMutar.setText("0.05");
		inserteElite.setText("0");
	}

	private void onInit(){
		//Algoritmo generico
		tipoFuncion = new JComboBox<Funcion>();
		tipoFuncion.addItem(new Funcion1());
		tipoFuncion.addItem(new Funcion2());
		tipoFuncion.addItem(new Funcion3());
		tipoFuncion.addItem(new Funcion4());
		//Cromosomas
		cromosomas = new JComboBox<Cromosoma>();
		cromosomas.addItem(new BinaryCromosoma());
		cromosomas.addItem(new DoubleCromosoma());
		//Estrategias de Cruces
		tipoCruces = new JComboBox<CruceStrategy>();
		tipoCruces.addItem(new CruceMonopunto());
		tipoCruces.addItem(new CruceUniforme());
		tipoCruces.addItem(new CruceAritmetico());
		tipoCruces.addItem(new CruceBlxAlpha());
		//Estrategias de Mutacion
		tiposmutar = new JComboBox<MutationStrategy>();
		tiposmutar.addItem(new MutationBasica());
		//Estrategias de Seleccion
		selecciones = new JComboBox<SelectionStrategy>();
		selecciones.addItem(new EstocasticoUniversal());
		selecciones.addItem(new Restos());
		selecciones.addItem(new Ruleta());
		selecciones.addItem(new TorneoAleatorio());
		selecciones.addItem(new TorneoDeterminista());
		selecciones.addItem(new Truncamiento());
	}
	
	public void onUpdateResult(double[] mejorAbsoluto,double[] mejorGeneracion,double[] mediaGeneracion,double[] presionEvo,String sol, int numGen) {
		evolution.removeAllPlots();
		double [] x = new double[numGen];
		for(int i=0;i<x.length;i++) {
			x[i]=i;
		}
		evolution.addLegend("SOUTH");
		evolution.addLinePlot("Mejor Absoluto",Color.red, x,mejorAbsoluto);
		evolution.addLinePlot("Mejor Generacion",Color.blue, x,mejorGeneracion);
		evolution.addLinePlot("Media Generacion",Color.green, x,mediaGeneracion);
		presionEvolutiva.removeAllPlots();
		presionEvolutiva.addLegend("SOUTH");
		presionEvolutiva.addLinePlot("Presion Evolutiva",Color.magenta, x,presionEvo);
		solucionTextField.setText(sol);
	}
}

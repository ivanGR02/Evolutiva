package Vista;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

import AlgoritmoGenerico.AlgoritmoGenerico;
import AlgoritmoGenerico.AnalisisGenerico;
import AlgoritmoGenerico.Pair;
import Funciones.Funcion;
import Funciones.FuncionP3;
import population.cromosoma.Cromosoma;
import population.cromosoma.CromosomaTree;
import population.strategy.CruceStrategy;
import population.strategy.MutationStrategy;
import population.strategy.SelectionStrategy;
import population.strategy.cruce.CruceSubarbol;
import population.strategy.mutation.MutacionAleatoria3;
import population.strategy.mutation.MutacionContraccion;
import population.strategy.mutation.MutacionExpansion;
import population.strategy.mutation.MutacionFuncional;
import population.strategy.mutation.MutacionHoist;
import population.strategy.mutation.MutacionPermutacion;
import population.strategy.mutation.MutacionSubarbol;
import population.strategy.mutation.MutacionTerminal;
import population.strategy.selection.EstocasticoUniversal;
import population.strategy.selection.Ranking;
import population.strategy.selection.Restos;
import population.strategy.selection.Ruleta;
import population.strategy.selection.TorneoAleatorio;
import population.strategy.selection.TorneoDeterminista;
import population.strategy.selection.Truncamiento;
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
import prac1.cromosoma.DoubleCromosoma;
import prac2.Funciones.FuncionTSP;
import prac2.Population.PermutationCromosoma;
import prac2.Strategy.Cruce.CruceAleatorio;
import prac2.Strategy.Cruce.CruceCO;
import prac2.Strategy.Cruce.CruceCX;
import prac2.Strategy.Cruce.CruceERX;
import prac2.Strategy.Cruce.CruceOX;
import prac2.Strategy.Cruce.CruceOXPP;
import prac2.Strategy.Cruce.CruceOXPri;
import prac2.Strategy.Cruce.CrucePMX;
import prac2.Strategy.Cruce.CrucePropio;
import prac2.Strategy.Mutation.MutacionAleatoria;
import prac2.Strategy.Mutation.MutacionHeuristica;
import prac2.Strategy.Mutation.MutacionInsercion;
import prac2.Strategy.Mutation.MutacionIntercambio;
import prac2.Strategy.Mutation.MutacionInversion;
import prac2.Strategy.Mutation.MutacionPropio;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{//
	//private Controller _ctrl;
	private JButton run,reset;
	private Plot2DPanel evolution;
	private Plot2DPanel presionEvolutiva;
	private JComboBox<String> practica;
	private JComboBox<String> practica3MetodoInicializar;
	private JComboBox<String> modo;
	private JComboBox<Funcion> tipoFuncion;
	private JComboBox<CruceStrategy> tipoCruces;
	private JComboBox<MutationStrategy> tiposmutar;
	private JComboBox<SelectionStrategy> selecciones;
	private ArrayList<Funcion> funcionPractica1;
	private ArrayList<Funcion> funcionPractica2;
	private ArrayList<Funcion> funcionPractica3;
	private ArrayList<MutationStrategy> mutationPractica1;
	private ArrayList<MutationStrategy> mutationPractica2;
	private ArrayList<MutationStrategy> mutationPractica3;
	private ArrayList<CruceStrategy> crucePractica1;
	private ArrayList<CruceStrategy> crucePractica2;
	private ArrayList<CruceStrategy> crucePractica3;
	private ArrayList<Cromosoma> cromosomaPractica1;
	private ArrayList<Cromosoma> cromosomaPractica2;
	private ArrayList<Cromosoma> cromosomaPractica3;
	private JComboBox<Cromosoma> cromosomas;
	private JComboBox<Integer> numeroDeProfundidad;
	private JTextField inserteGeneraciones;
	private JLabel error;
	private JTextField inserteError;
	private JLabel tamanoPob;
	private JTextField insertePob;
	private JLabel pctCruce;
	private JTextField inserteCruce;
	private JLabel pctmutar;
	private JTextField inserteMutar;
	private JLabel tamanoPobAnal;
	private JTextField insertePobAnal;
	private JLabel pctCruceAnal;
	private JTextField inserteCruceAnal;
	private JLabel pctmutarAnal;
	private JTextField inserteMutarAnal;
	private JLabel elite;
	private JTextField inserteElite;
	private JTextArea solucionTextField;
	private JTextField inserteDimension;
	private JPanel boton;
	private AlgoritmoGenerico algoritmoGenerico;
	private JCheckBox calibre;
	private JCheckBox pob;
	private JCheckBox cruce;
	private JCheckBox mutar;
	private JPanel pobAnalPanel;
	private JPanel cruceAnalPanel;
	private JPanel mutarAnalPanel;
	private int practicasActual=3;
	private Filler myFille=ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,6);
	private Filler myFille2=ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3);
	private Filler myFille3=ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3);
	private Filler myFille4=ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3);
	private Filler myFille5=ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3);
	private Filler myFille6=ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3);
	private Filler myFille7=ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3);
	private Filler myFille8=ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3);
	private Filler myFille9=ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3);
	private Filler myFille10=ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3);
	private JLabel pctDimension;
	private int analisis;
	private JLabel numAlgoritmo;
	private JTextField inserteNumAlgoritmo;
	private AnalisisGenerico analisisGenerico;
	private JPanel mapView;// pongamos la grafica de la funcion
	private JPanel fitnessViewGrafica;
	private JPanel graficasPanel ;
	private JLabel metodoInicializacion;
	private JLabel profundidadMax;
	
	public MainWindow() {
		super("Programacion Evolutiva");
		algoritmoGenerico=new AlgoritmoGenerico(this,true);
		analisisGenerico=new AnalisisGenerico(this);
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
				
		
		graficasPanel = new JPanel(); // donde estarian las graficas
		graficasPanel.setLayout(new BoxLayout(graficasPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(graficasPanel);
		onInit(variablePanel);
		//solucion
		solucion.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,10));
		JPanel solucionContent=new JPanel();
		solucionContent.setLayout(new BoxLayout(solucionContent, BoxLayout.X_AXIS));
		solucion.add(solucionContent);
		JLabel solucionLabel = ViewUtilitySingleton.getUtility().createDefaultJlabel("      Solución :   ");
		solucionContent.add(solucionLabel);
		solucionTextField = ViewUtilitySingleton.getUtility().createDefaultJTextArea("No hay solucion aún",1,1);
		solucionContent.add(solucionTextField);
		solucion.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,15));
				
		// variables
		//eleccion de practica
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,10));
		JLabel practicaLabel = ViewUtilitySingleton.getUtility().createDefaultJlabel("Elegir la practica :");
		variablePanel.add(practicaLabel);
		practica.setPreferredSize(new Dimension(220, 31));;
		practica.setMaximumSize(new Dimension(220, 31));;
		practica.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onChangePractica(variablePanel);
			}
		});
		variablePanel.add(practica);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3));
		JLabel modoLabel = ViewUtilitySingleton.getUtility().createDefaultJlabel("Elegir el modo :");
		variablePanel.add(modoLabel);
		modo.setPreferredSize(new Dimension(220, 31));;
		modo.setMaximumSize(new Dimension(220, 31));;
		modo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				onChangeModo(variablePanel);
			}
		});
		variablePanel.add(modo);
		
		pctDimension = ViewUtilitySingleton.getUtility().createDefaultJlabel("Dimension, para Michalewicz");// poner el porcentaje cruce
		inserteDimension = ViewUtilitySingleton.getUtility().createDefaultJTextField("2");
		inserteDimension.setEditable(false);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3));
		JLabel funcion = ViewUtilitySingleton.getUtility().createDefaultJlabel("Funciones");// poner cruces
		variablePanel.add(funcion);

		tipoFuncion.setPreferredSize(new Dimension(220, 31));;
		tipoFuncion.setMaximumSize(new Dimension(220, 31));;
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
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3));
		JLabel cromosomastf = ViewUtilitySingleton.getUtility().createDefaultJlabel("Cromosoma Type");// poner cruces
		variablePanel.add(cromosomastf);
		cromosomas.setPreferredSize(new Dimension(220, 31));;
		cromosomas.setMaximumSize(new Dimension(220, 31));;
		variablePanel.add(cromosomas);
		
		//calibrar checkBox
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3));
		JPanel calibrePanel = new JPanel(); // donde estarian las graficas
		calibrePanel.setLayout(new BoxLayout(calibrePanel, BoxLayout.X_AXIS));
		variablePanel.add(calibrePanel);
		JLabel calibreJL = ViewUtilitySingleton.getUtility().createDefaultJlabel("Calibrar Fitness : ");// poner las poblaciones
		calibreJL.setFont(new Font("Italic", Font.PLAIN, 13));
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
		
		tamanoPob = ViewUtilitySingleton.getUtility().createDefaultJlabel("Tamano Población");// poner las poblaciones
		insertePob = ViewUtilitySingleton.getUtility().createDefaultJTextField("100");
		
		pobAnalPanel = new JPanel(); // donde estarian las graficas
		tamanoPobAnal=ViewUtilitySingleton.getUtility().createDefaultJlabel("Analizar Población (x-y)");
		insertePobAnal=ViewUtilitySingleton.getUtility().createDefaultJTextField("100-600");
		pobAnalPanel.setLayout(new BoxLayout(pobAnalPanel, BoxLayout.X_AXIS));
		tamanoPob.setFont(new Font("Italic", Font.PLAIN, 13));
		pobAnalPanel.add(tamanoPobAnal);
		pob=new JCheckBox();
		pob.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pob.isSelected()) {
					analisis=0;
					inserteCruceAnal.setEditable(false);
					cruce.setEnabled(false);
					inserteMutarAnal.setEditable(false);
					mutar.setEnabled(false);
				}else {
					inserteCruceAnal.setEditable(true);
					cruce.setEnabled(true);
					inserteMutarAnal.setEditable(true);
					mutar.setEnabled(true);
					analisis=-1;
				}
			}
		});
		pobAnalPanel.add(pob);
		cruceAnalPanel = new JPanel(); // donde estarian las graficas
		pctCruceAnal=ViewUtilitySingleton.getUtility().createDefaultJlabel("Analizar Cruce(x-y)");
		inserteCruceAnal=ViewUtilitySingleton.getUtility().createDefaultJTextField("0.3-0.6");
		cruceAnalPanel.setLayout(new BoxLayout(cruceAnalPanel, BoxLayout.X_AXIS));
		pctCruceAnal.setFont(new Font("Italic", Font.PLAIN, 13));
		cruceAnalPanel.add(pctCruceAnal);
		cruce=new JCheckBox();
		cruce.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cruce.isSelected()) {
					analisis=1;
					insertePobAnal.setEditable(false);
					pob.setEnabled(false);
					inserteMutarAnal.setEditable(false);
					mutar.setEnabled(false);
				}else {
					insertePobAnal.setEditable(true);
					pob.setEnabled(true);
					inserteMutarAnal.setEditable(true);
					mutar.setEnabled(true);
					analisis=-1;
				}
			}
		});
		cruceAnalPanel.add(cruce);
		mutarAnalPanel = new JPanel(); // donde estarian las graficas
		pctmutarAnal=ViewUtilitySingleton.getUtility().createDefaultJlabel("Analizar mutar (x-y)");
		inserteMutarAnal=ViewUtilitySingleton.getUtility().createDefaultJTextField("0.05-0.31");
		mutarAnalPanel.setLayout(new BoxLayout(mutarAnalPanel, BoxLayout.X_AXIS));
		pctmutarAnal.setFont(new Font("Italic", Font.PLAIN, 13));
		mutarAnalPanel.add(pctmutarAnal);
		mutar=new JCheckBox();
		mutar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mutar.isSelected()) {
					analisis=2;
					insertePobAnal.setEditable(false);
					pob.setEnabled(false);
					inserteCruceAnal.setEditable(false);
					cruce.setEnabled(false);
				}else {
					insertePobAnal.setEditable(true);
					pob.setEnabled(true);
					inserteCruceAnal.setEditable(true);
					cruce.setEnabled(true);
					analisis=-1;
				}
			}
		});
		mutarAnalPanel.add(mutar);
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3));
		JLabel genPob = ViewUtilitySingleton.getUtility().createDefaultJlabel("   Generaciones de la poblacion   ");// poner las generaciones
		variablePanel.add(genPob);
		inserteGeneraciones = ViewUtilitySingleton.getUtility().createDefaultJTextField("100");
		variablePanel.add(inserteGeneraciones);
		
		numAlgoritmo=ViewUtilitySingleton.getUtility().createDefaultJlabel("Numero de Algoritmo a lanzar");
		inserteNumAlgoritmo=ViewUtilitySingleton.getUtility().createDefaultJTextField("36");
		error = ViewUtilitySingleton.getUtility().createDefaultJlabel("Valor de error");// poner el valor de error
		inserteError = ViewUtilitySingleton.getUtility().createDefaultJTextField("0.001");

		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3));
		JLabel seleccion = ViewUtilitySingleton.getUtility().createDefaultJlabel("Selecciones");// poner selecciones
		variablePanel.add(seleccion);

		selecciones.setPreferredSize(new Dimension(220, 31));
		selecciones.setMaximumSize(new Dimension(220, 31));
		variablePanel.add(selecciones);
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3));
		JLabel cruces = ViewUtilitySingleton.getUtility().createDefaultJlabel("Cruces");// poner cruces
		variablePanel.add(cruces);

		tipoCruces.setPreferredSize(new Dimension(220, 31));;
		tipoCruces.setMaximumSize(new Dimension(220, 31));;
		variablePanel.add(tipoCruces);
		
		pctCruce = ViewUtilitySingleton.getUtility().createDefaultJlabel("% cruce");// poner el porcentaje cruce
		inserteCruce = ViewUtilitySingleton.getUtility().createDefaultJTextField("0.36");
		
		variablePanel.add(ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,3));
		JLabel mutar = ViewUtilitySingleton.getUtility().createDefaultJlabel("Mutaciones");// poner mutaciones
		variablePanel.add(mutar);

		tiposmutar.setPreferredSize(new Dimension(220, 31));;
		tiposmutar.setMaximumSize(new Dimension(220, 31));;

		variablePanel.add(tiposmutar);
		
		pctmutar = ViewUtilitySingleton.getUtility().createDefaultJlabel("% mutar");// poner el porcentaje mutacion
		inserteMutar = ViewUtilitySingleton.getUtility().createDefaultJTextField("0.05");
		
		elite = ViewUtilitySingleton.getUtility().createDefaultJlabel("elitismo %");// poner el valor de error
		inserteElite = ViewUtilitySingleton.getUtility().createDefaultJTextField("0");
		
		//eleccion de metodo de inicializacion
		
		metodoInicializacion=ViewUtilitySingleton.getUtility().createDefaultJlabel("Elegir el metodo de inicializacion");
		profundidadMax=ViewUtilitySingleton.getUtility().createDefaultJlabel("Tamaño maximo de profundidad");
		practica3MetodoInicializar.setPreferredSize(new Dimension(220, 31));;
		practica3MetodoInicializar.setMaximumSize(new Dimension(220, 31));;
		numeroDeProfundidad.setPreferredSize(new Dimension(220,31));
		numeroDeProfundidad.setMaximumSize(new Dimension(220,31));
		
		
		
		boton=new JPanel();
		boton.setLayout(new BoxLayout(boton, BoxLayout.X_AXIS));
		
		//run button
		run = new JButton ("Run");
		run.setToolTipText("Click to Run");
		
		boton.add(run);
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(modo.getSelectedItem().equals("Modo normal")) {
					Funcion current=(Funcion) tipoFuncion.getSelectedItem();
					current.setPrecision(Double.parseDouble(inserteError.getText()));
					if(current instanceof Funcion4) {
						((Funcion4) current).setNumGen(Integer.parseInt(inserteDimension.getText()));
					}else if(cromosomas.getSelectedItem() instanceof CromosomaTree) {
						algoritmoGenerico.setTipoInicializacion(getMetodosDeInicializacion());
						algoritmoGenerico.setTamProfund((Integer)numeroDeProfundidad.getSelectedItem());
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
				}else {
					Pair<Double,Double> mutarDatos,cruzarDatos;
					Pair<Integer,Integer> pobDatos;
					pobDatos=parseInt(insertePobAnal.getText());
					analisisGenerico.setNum_indiv(pobDatos.getFirst());
					analisisGenerico.setNum_indiv_max(pobDatos.getSecond());
					mutarDatos=parseDouble(inserteMutarAnal.getText());
					cruzarDatos=parseDouble(inserteCruceAnal.getText());
					analisisGenerico.setProb_cruce(cruzarDatos.getFirst());
					analisisGenerico.setProb_cruce_max(cruzarDatos.getSecond());
					analisisGenerico.setProb_mut(mutarDatos.getFirst());
					analisisGenerico.setProb_mut_max(mutarDatos.getSecond());
					analisisGenerico.setTipo_analisis(analisis);
					analisisGenerico.setNum_algoritmos(Integer.parseInt(inserteNumAlgoritmo.getText()));
					analisisGenerico.setCromosoma((Cromosoma)cromosomas.getSelectedItem());
					try {
						analisisGenerico.run();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
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
		mapView = createViewPanel(evolution, "Evolution");// pongamos la grafica de la funcion
		mapView.setPreferredSize(new Dimension(1400, 410));
		mapView.setMaximumSize(new Dimension(1400,410));
		graficasPanel.add(mapView);
		fitnessViewGrafica = createViewPanel(presionEvolutiva, "Presion Evolutiva");// grafica de la presión selectiva
		fitnessViewGrafica.setPreferredSize(new Dimension(1400, 410));
		fitnessViewGrafica.setMaximumSize(new Dimension(1400,410));
		
		graficasPanel.add(fitnessViewGrafica);
		onChangeModo(variablePanel);
		variablePanel.add(myFille9);
		variablePanel.add(metodoInicializacion);
		variablePanel.add(practica3MetodoInicializar);
		variablePanel.add(myFille10);
		variablePanel.add(profundidadMax);
		variablePanel.add(numeroDeProfundidad);
		myFille=ViewUtilitySingleton.getUtility().createDefaultBoxFiller(0,10);
		variablePanel.add(myFille);
		variablePanel.add(boton);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private Pair<Double,Double> parseDouble(String aux){
		Pair<Double,Double> ret=null;
		try {
			if(aux.contains("-")) {
				String[] auxi=aux.split("-");
				Double min,max;
				min=Double.parseDouble(auxi[0]);
				max=Double.parseDouble(auxi[1]);
				ret= new Pair<Double,Double>(Math.min(min, max),Math.max(min, max));
			}else {
				JPanel panelConfirm = new JPanel();
				JOptionPane.showMessageDialog(panelConfirm, "Introduzca - para el rango");
			}
			
		}catch (Exception ex) {
			JPanel panelConfirm = new JPanel();
			JOptionPane.showMessageDialog(panelConfirm, "Datos no validos vuelve a introducirlo");
		}
		return ret;
	}
	
	private Pair<Integer,Integer> parseInt(String aux){
		Pair<Integer,Integer> ret=null;
		try {
			if(aux.contains("-")) {
				String[] auxi=aux.split("-");
				int min,max;
				min=Integer.parseInt(auxi[0]);
				max=Integer.parseInt(auxi[1]);
				ret= new Pair<Integer,Integer>(Math.min(min, max),Math.max(min, max));
			}else {
				JPanel panelConfirm = new JPanel();
				JOptionPane.showMessageDialog(panelConfirm, "Introduzca - para el rango");
			}
		}catch (Exception ex) {
			JPanel panelConfirm = new JPanel();
			JOptionPane.showMessageDialog(panelConfirm, "Datos no validos vuelve a introducirlo");
		}
		return ret;
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
		inserteCruce.setText("0.36");
		inserteMutar.setText("0.05");
		inserteElite.setText("0");
		insertePobAnal.setText("100-600");
		inserteCruceAnal.setText("0.1-0.6");
		inserteMutarAnal.setText("0.05-0.31");
		solucionTextField.setText("No hay solucion aún");
		inserteNumAlgoritmo.setText("36");
		analisis=0;
		pob.setEnabled(true);
		pob.setSelected(true);
		insertePobAnal.setEditable(true);
		cruce.setEnabled(false);
		cruce.setSelected(false);
		inserteCruceAnal.setEditable(false);
		mutar.setEnabled(false);
		mutar.setSelected(false);
		inserteMutarAnal.setEditable(false);
		evolution.removeAllPlots();
		presionEvolutiva.removeAllPlots();
	}
	
	private void onChangeModo(JPanel variablePanel) {
		if(modo.getSelectedItem().equals("Modo normal")) {
			variablePanel.remove(myFille);
			variablePanel.remove(boton);
			variablePanel.remove(myFille4);
			variablePanel.remove(pobAnalPanel);
			variablePanel.remove(insertePobAnal);
			variablePanel.remove(myFille5);
			variablePanel.remove(cruceAnalPanel);
			variablePanel.remove(inserteCruceAnal);
			variablePanel.remove(myFille6);
			variablePanel.remove(mutarAnalPanel);
			variablePanel.remove(inserteMutarAnal);
			variablePanel.remove(myFille8);
			variablePanel.remove(numAlgoritmo);
			variablePanel.remove(inserteNumAlgoritmo);
			variablePanel.add(myFille4);
			variablePanel.add(pctCruce);
			variablePanel.add(inserteCruce);
			variablePanel.add(myFille5);
			variablePanel.add(pctmutar);
			variablePanel.add(inserteMutar);
			variablePanel.add(myFille6);
			variablePanel.add(tamanoPob);
			variablePanel.add(insertePob);
			variablePanel.add(myFille7);
			variablePanel.add(elite);
			variablePanel.add(inserteElite);
			variablePanel.add(myFille);
			variablePanel.add(boton);
			solucionTextField.setText("No hay solucion aún");
			evolution.removeAllPlots();
			presionEvolutiva.removeAllPlots();
			mapView.setPreferredSize(new Dimension(1200, 360));
			mapView.setMaximumSize(new Dimension(1400,360));
			graficasPanel.add(fitnessViewGrafica);
		}else {
			variablePanel.remove(myFille);
			variablePanel.remove(boton);
			variablePanel.remove(myFille4);
			variablePanel.remove(pctCruce);
			variablePanel.remove(inserteCruce);
			variablePanel.remove(myFille5);
			variablePanel.remove(pctmutar);
			variablePanel.remove(inserteMutar);
			variablePanel.remove(myFille6);
			variablePanel.remove(tamanoPob);
			variablePanel.remove(insertePob);
			variablePanel.remove(myFille7);
			variablePanel.remove(elite);
			variablePanel.remove(inserteElite);
			variablePanel.add(myFille4);
			pob.setSelected(true);
			cruce.setEnabled(false);
			inserteCruceAnal.setEditable(false);
			mutar.setEnabled(false);
			inserteMutarAnal.setEditable(false);
			variablePanel.add(pobAnalPanel);
			variablePanel.add(insertePobAnal);
			variablePanel.add(myFille5);
			variablePanel.add(cruceAnalPanel);
			variablePanel.add(inserteCruceAnal);
			variablePanel.add(myFille6);
			variablePanel.add(mutarAnalPanel);
			variablePanel.add(inserteMutarAnal);
			variablePanel.add(myFille7);
			variablePanel.add(numAlgoritmo);
			variablePanel.add(inserteNumAlgoritmo);
			variablePanel.add(myFille8);
			variablePanel.add(elite);
			variablePanel.add(inserteElite);
			variablePanel.add(myFille);
			variablePanel.add(boton);
			solucionTextField.setText("No hay solucion aún");
			evolution.removeAllPlots();
			presionEvolutiva.removeAllPlots();
			graficasPanel.remove(fitnessViewGrafica);
			mapView.setPreferredSize(new Dimension(1200, 720));
			mapView.setMaximumSize(new Dimension(1400,720));
		}
		this.pack();
	}
	
	private void onChangePractica(JPanel variablePanel) {
		tipoFuncion.removeAllItems();
		cromosomas.removeAllItems();
		tipoCruces.removeAllItems();
		tiposmutar.removeAllItems();
		if(practica.getSelectedItem().equals("Practica 1")){
			for(Funcion f:funcionPractica1) {
				tipoFuncion.addItem(f);
			}
			for(Cromosoma c:cromosomaPractica1) {
				cromosomas.addItem(c);
			}
			for(CruceStrategy c:crucePractica1) {
				tipoCruces.addItem(c);
			}
			for(MutationStrategy m:mutationPractica1) {
				tiposmutar.addItem(m);
			}
			if(practicasActual!=1 && practicasActual!=0) {
				variablePanel.remove(myFille);
				variablePanel.remove(boton);
				variablePanel.remove(myFille9);
				variablePanel.remove(metodoInicializacion);
				variablePanel.remove(practica3MetodoInicializar);
				variablePanel.remove(myFille10);
				variablePanel.remove(profundidadMax);
				variablePanel.remove(numeroDeProfundidad);
				variablePanel.add(myFille3);
				variablePanel.add(pctDimension);
				variablePanel.add(inserteDimension);
				variablePanel.add(myFille2);
				variablePanel.add(error);
				variablePanel.add(inserteError);
				variablePanel.add(myFille);
				variablePanel.add(boton);
			}
			practicasActual=1;
		}else if(practica.getSelectedItem().equals("Practica 2")) {
			for(Funcion f:funcionPractica2) {
				tipoFuncion.addItem(f);
			}
			for(Cromosoma c:cromosomaPractica2) {
				cromosomas.addItem(c);
			}
			for(CruceStrategy c:crucePractica2) {
				tipoCruces.addItem(c);
			}
			for(MutationStrategy m:mutationPractica2) {
				tiposmutar.addItem(m);
			}
			if(practicasActual!=2 && practicasActual!=0) {
				variablePanel.remove(myFille3);
				variablePanel.remove(myFille2);
				variablePanel.remove(pctDimension);
				variablePanel.remove(inserteDimension);
				variablePanel.remove(inserteError);
				variablePanel.remove(error);
				variablePanel.remove(myFille9);
				variablePanel.remove(metodoInicializacion);
				variablePanel.remove(practica3MetodoInicializar);
				variablePanel.remove(myFille10);
				variablePanel.remove(profundidadMax);
				variablePanel.remove(numeroDeProfundidad);
			}
			practicasActual=2;
		}else if(practica.getSelectedItem().equals("Practica 3")) {
			for(Funcion f:funcionPractica3) {
				tipoFuncion.addItem(f);
			}
			for(Cromosoma c:cromosomaPractica3) {
				cromosomas.addItem(c);
			}
			for(CruceStrategy c:crucePractica3) {
				tipoCruces.addItem(c);
			}
			for(MutationStrategy m:mutationPractica3) {
				tiposmutar.addItem(m);
			}
			if(practicasActual!=3 && practicasActual!=0) {
				variablePanel.remove(myFille3);
				variablePanel.remove(myFille2);
				variablePanel.remove(pctDimension);
				variablePanel.remove(inserteDimension);
				variablePanel.remove(inserteError);
				variablePanel.remove(error);
				variablePanel.remove(myFille);
				variablePanel.remove(boton);
				//
				variablePanel.add(myFille9);
				variablePanel.add(metodoInicializacion);
				variablePanel.add(practica3MetodoInicializar);
				variablePanel.add(myFille10);
				variablePanel.add(profundidadMax);
				variablePanel.add(numeroDeProfundidad);
				variablePanel.add(myFille);
				variablePanel.add(boton);
			}
			practicasActual=3;
		}
		if(evolution!=null) {
			evolution.removeAllPlots();
			presionEvolutiva.removeAllPlots();
		}
		if(solucionTextField!=null) {
			solucionTextField.setText("No hay solucion aún");
		}
		this.pack();
	}

	private int getMetodosDeInicializacion() {
		if(practica3MetodoInicializar.getSelectedItem().equals("Métodos Creciente")) {
			return 0;
		}else if(practica3MetodoInicializar.getSelectedItem().equals("Métodos Completa")) {
			return 1;
		}else {
			return 2;
		}
	}
	
	private void onInit(JPanel variablePanel){
		//practica
		practica = new JComboBox<String>();
		practica.addItem("Practica 3");
		practica.addItem("Practica 2");
		practica.addItem("Practica 1");
		//practica3 metodo de inicializacion
		practica3MetodoInicializar=new JComboBox<String>();
		practica3MetodoInicializar.addItem("Métodos Ramped and half");
		practica3MetodoInicializar.addItem("Métodos Creciente");
		practica3MetodoInicializar.addItem("Métodos Completa");
		//numProf
		numeroDeProfundidad=new JComboBox<Integer>();
		numeroDeProfundidad.addItem(2);
		numeroDeProfundidad.addItem(3);
		numeroDeProfundidad.addItem(4);
		numeroDeProfundidad.addItem(5);
		
		//modo
		modo = new JComboBox<String>();
		modo.addItem("Modo normal");
		modo.addItem("Modo analisis");
		//Algoritmo generico
		tipoFuncion = new JComboBox<Funcion>();
		//funcionPractica1
		funcionPractica1=new ArrayList<Funcion>();
		funcionPractica1.add(new Funcion1());
		funcionPractica1.add(new Funcion2());
		funcionPractica1.add(new Funcion3());
		funcionPractica1.add(new Funcion4());
		//funcionPractica2
		funcionPractica2=new ArrayList<Funcion>();
		funcionPractica2.add(new FuncionTSP());
		//funcionPractica3
		funcionPractica3=new ArrayList<Funcion>();
		funcionPractica3.add(new FuncionP3());
		//Cromosomas
		cromosomas = new JComboBox<Cromosoma>();
		//listas de cromosomas
		//practica1
		cromosomaPractica1=new ArrayList<Cromosoma>();
		cromosomaPractica1.add(new BinaryCromosoma());
		cromosomaPractica1.add(new DoubleCromosoma());
		//practica2
		cromosomaPractica2=new ArrayList<Cromosoma>();
		cromosomaPractica2.add(new PermutationCromosoma());
		//practica3
		cromosomaPractica3=new ArrayList<Cromosoma>();
		cromosomaPractica3.add(new CromosomaTree(5,0));
		//Estrategias de Cruces
		tipoCruces = new JComboBox<CruceStrategy>();
		//cruce practica 1
		crucePractica1 = new ArrayList<CruceStrategy>();
		crucePractica1.add(new CruceMonopunto());
		crucePractica1.add(new CruceUniforme());
		crucePractica1.add(new CruceAritmetico());
		crucePractica1.add(new CruceBlxAlpha());
		//cruce practica 2
		crucePractica2 = new ArrayList<CruceStrategy>();
		crucePractica2.add(new CruceAleatorio());
		crucePractica2.add(new CruceCO());
		crucePractica2.add(new CruceCX());
		crucePractica2.add(new CruceERX());
		crucePractica2.add(new CruceOX());
		crucePractica2.add(new CruceOXPP());
		crucePractica2.add(new CruceOXPri());
		crucePractica2.add(new CrucePMX());
		crucePractica2.add(new CrucePropio());
		//cruce practica 1
		crucePractica3 = new ArrayList<CruceStrategy>();
		crucePractica3.add(new CruceSubarbol());
		//Estrategias de Mutacion
		tiposmutar = new JComboBox<MutationStrategy>();
		//mutacion practica 1
		mutationPractica1 = new ArrayList<MutationStrategy>();
		mutationPractica1.add(new MutationBasica());
		//mutacion practica 2
		mutationPractica2 = new ArrayList<MutationStrategy>();
		mutationPractica2.add(new MutacionAleatoria());
		mutationPractica2.add(new MutacionHeuristica());
		mutationPractica2.add(new MutacionInsercion());
		mutationPractica2.add(new MutacionIntercambio());
		mutationPractica2.add(new MutacionInversion());
		mutationPractica2.add(new MutacionPropio());
		//mutacion practica 1
		mutationPractica3 = new ArrayList<MutationStrategy>();
		mutationPractica3.add(new MutacionFuncional());
		mutationPractica3.add(new MutacionTerminal());
		mutationPractica3.add(new MutacionSubarbol());
		mutationPractica3.add(new MutacionPermutacion());
		mutationPractica3.add(new MutacionHoist());
		mutationPractica3.add(new MutacionExpansion());
		mutationPractica3.add(new MutacionContraccion());
		mutationPractica3.add(new MutacionAleatoria3());
		//Estrategias de Seleccion
		selecciones = new JComboBox<SelectionStrategy>();
		selecciones.addItem(new EstocasticoUniversal());
		selecciones.addItem(new Ranking());
		selecciones.addItem(new Restos());
		selecciones.addItem(new Ruleta());
		selecciones.addItem(new TorneoAleatorio());
		selecciones.addItem(new TorneoDeterminista());
		selecciones.addItem(new Truncamiento());
		onChangePractica(variablePanel);
	}
	
	public AlgoritmoGenerico createAlgoritmoGenerico() {
		AlgoritmoGenerico nuevo=new AlgoritmoGenerico(this,false);
		Funcion current=(Funcion) tipoFuncion.getSelectedItem();
		current.setPrecision(Double.parseDouble(inserteError.getText()));
		if(current instanceof Funcion4) {
			((Funcion4) current).setNumGen(Integer.parseInt(inserteDimension.getText()));
		}else if(current instanceof FuncionP3) {
			nuevo.setTipoInicializacion(getMetodosDeInicializacion());
			nuevo.setTamProfund((Integer)numeroDeProfundidad.getSelectedItem());
		}
		nuevo.setters(Integer.parseInt(insertePob.getText()),
				Double.parseDouble(inserteCruce.getText()),
				Double.parseDouble(inserteMutar.getText()), 
				Integer.parseInt(inserteGeneraciones.getText()),
				(SelectionStrategy)selecciones.getSelectedItem(), 
				(CruceStrategy)tipoCruces.getSelectedItem(),
				(MutationStrategy)tiposmutar.getSelectedItem(), 
				Double.parseDouble(inserteElite.getText()),
				current,(Cromosoma)cromosomas.getSelectedItem()
				);
		
		return nuevo;
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
	
	public void onUpdateAnalisis(double[] mejorAbsoluto, double[] analisis,int num_algoritmos,String sol) {
		evolution.removeAllPlots();
		evolution.addLegend("SOUTH");
		evolution.addLinePlot("Mejor Absoluto",Color.red, analisis,mejorAbsoluto);
		solucionTextField.setText(sol);
	}
	
	public void onUpdateP3(double[] ideal, double[] database,Cromosoma sol, int numGen, double[] presionEvo,double[] mejor,double[] media,double[] absoluto) {
		evolution.removeAllPlots();
		presionEvolutiva.removeAllPlots();
		double []  solucion= new double[((CromosomaTree)sol).getSol().size()];
		for(int i=0;i<solucion.length;i++) {
			solucion[i]=((CromosomaTree)sol).getSol().get(i);
		}
		double [] x = new double[numGen];
		for(int i=0;i<x.length;i++) {
			x[i]=i;
		}
		double [] y =new double[Integer.parseInt(inserteGeneraciones.getText())];
		for(int i=0;i<Integer.parseInt(inserteGeneraciones.getText());i++) {
			y[i]=i;
		}
		evolution.addLegend("SOUTH");
		evolution.addLinePlot("Ideal",Color.red, database,ideal);
		evolution.addLinePlot("Mejor obtenido",Color.blue, database,solucion);
		solucionTextField.setText(sol.toStringSol());
		presionEvolutiva.addLinePlot("Presion Evolutiva",Color.CYAN, y,presionEvo);
		presionEvolutiva.addLinePlot("Mejor Absoluto",Color.red, y,absoluto);
		presionEvolutiva.addLinePlot("Mejor Generacion",Color.blue, y,mejor);
		presionEvolutiva.addLinePlot("Media Generacion",Color.green, y,media);
		presionEvolutiva.addLegend("SOUTH");
	}
	
}

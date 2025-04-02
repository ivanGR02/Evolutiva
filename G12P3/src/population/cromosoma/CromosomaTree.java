package population.cromosoma;

import java.util.ArrayList;

import Funciones.FuncionP3;
import population.Random_Utilities;
import prac2.Population.PermutationCromosoma;

public class CromosomaTree extends Cromosoma{
	public static final String terminales[] = { "X", "-2", "-1", "0", "1", "2" };
	public static final String funciones[] = { "ADD", "SUB", "MUL"};
	private ArrayList<Double> sol;
	private Tree arbol;
	private double fitness_bruto; //Aptitud antes de transformarla
	private String fenotipo;
	
	public CromosomaTree(int profundidad, int tipoCreacion) {
		super();
		arbol = new Tree(1, profundidad);
		switch(tipoCreacion){
			case 0:
				arbol.inicializacionCreciente();
				break;
			case 1:
				arbol.inicializacionCompleta();
				break;
			case 2:
				int ini = Random_Utilities.getInstance().nextInt(2);
				if(ini == 0) arbol.inicializacionCreciente();
				else arbol.inicializacionCompleta();
			break;
		}
	}
	
	public CromosomaTree() {
		super();
	}
	
	public void initSol() {
		if(getLongitud()>3) {
			arbol.eliminarNulo();
		}
		sol=new ArrayList<>();
		for(int i=0;i<FuncionP3.database.length;i++) {
			   sol.add(arbol.Calcular(FuncionP3.database[i]));
		}
	}

	public void mutacion(int caso){
		switch(caso){
			case 0: //mutacion funcional
			arbol.mutacionFuncional();
			break;
			case 1: //mutacion Permutacione
			arbol.mutacionPermutacion();
			break;
			case 2://mutacion Subarbol
			arbol.mutacionSubarbol();
			break;
			case 3:// mutacion Terminal
			arbol.mutacionTerminal();
			break;
			case 4:// mutacion hoist
			arbol.mutacionhoist();
			break;
			case 5:// mutacion expansion
			arbol.mutacionExpansion();
			break;
			case 6: // mutacion contraccion
			arbol.mutacionContraccion();
			break;
		}
	}
	public Tree cruzar(){// devuelve el arbol que se cruza
		return this.arbol.cruzar();
	}

	@Override
	public Cromosoma generateCopy() {
		CromosomaTree copy = new CromosomaTree();
		copy.setArbol(arbol.copic());
		copy.setFitness(getFitness());
		copy.setFitnessAjus(getFitnessAjus());
		copy.setValor(getValor());
		return copy;
	}


	public Tree getArbol() {
		return arbol;
	}


	public void setArbol(Tree arbol) {
		this.arbol = arbol;
	}


	public double getFitness_bruto() {
		return fitness_bruto;
	}


	public void setFitness_bruto(double fitness_bruto) {
		this.fitness_bruto = fitness_bruto;
	}


	public String getFenotipo() {
		return fenotipo;
	}


	public void setFenotipo(String fenotipo) {
		this.fenotipo = fenotipo;
	}
	
	@Override
	public void onCreate(ArrayList<Double> max, ArrayList<Double> min, Double precision, int numGen) {
		// TODO Auto-generated method stub
		
	}
	public String toString(){
		return "Cromosoma Tree";
	}
	
	public String toStringSol(){
		return arbol.toArray();
	}

	public ArrayList<Double> getSol() {
		return sol;
	}

	public void setSol(ArrayList<Double> sol) {
		this.sol = sol;
	}
	
	@Override
	public int getLongitud() {
		return arbol.tamanyoArbol();
	}

}

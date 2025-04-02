package population.cromosoma;

import java.util.ArrayList;
import java.util.Comparator;

import Funciones.Funcion;

public abstract class Cromosoma{
	
	double puntuacion;
	private double puntuacionAcum;
	private double valor;
	private double fitness;
	private double fitnessAjus;
	protected ArrayList<Gen> cromosoma;
	
	public Cromosoma() {
		this.cromosoma=new ArrayList<Gen>();
	};

	public Cromosoma(ArrayList<Double> max,ArrayList<Double> min
			,Double precision,int numGen) {
		this.cromosoma=new ArrayList<Gen>(numGen);
		onCreate(max,min,
				precision,numGen);
	}
	
	public void setSizeCromosoma(int numGen) {
		cromosoma=new ArrayList<Gen>(numGen);
	}
	
	public abstract void onCreate(ArrayList<Double> max,ArrayList<Double> min
			,Double precision,int numGen);
	public abstract Cromosoma generateCopy();
	
	public double getFitnessCalculo(Boolean calibrar) {
		return calibrar?fitnessAjus:fitness;
	}
	
	public double getPuntuacion(){
		return this.puntuacion;
	}
	public Double getPuntuacionAcum(){
		return puntuacionAcum;
	}
	public void setPuntuacion(double punt){
		puntuacion=punt;
	}
	public void setPuntuacionAcum(double puntAcum){
		puntuacionAcum=puntAcum;
	}
	
	public ArrayList<Gen> getCromosoma() {
		return cromosoma;
	}
	public void setCromosoma(ArrayList<Gen> cromosoma) {
		this.cromosoma = cromosoma;
	}

	public int getLongitud() {
		int aux=0;
		for(int i=0;i<getCromosoma().size();i++){
			aux+=getCromosoma().get(i).getTam();
		}
		return aux;
	}
	
	public String toStringSol() {
		int count=0;
		String soluc="Solucion : " + getValor() + " , con valores de la variable : \n";
		for(int i=1;i<=cromosoma.size();i++) {
			if(soluc.length()/120>count) {
				count++;
				soluc+="\n";
			}
			soluc+=" x"+i+" = " + cromosoma.get(i-1).getFenotipo() + " ,";
		}
		return soluc;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public double getFitness() {
		return fitness;
	}
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public void ajustarFitness(double sumaTotal, int pob, double mejorfitness){
		double P=1.5;// individuos esperados del mejor en la poblacion
		double media=sumaTotal/pob;
		double a=((P-1)*media)/(mejorfitness-media);
		double b=(1-a)*media;
		double g=getFitness();
		setFitnessAjus((a*g)+b);
	}
	
	public void setFitnessAjus(double valor){
		fitnessAjus=valor;
	}
	public double getFitnessAjus(){
		return fitnessAjus;
	}

}

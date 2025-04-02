package population.cromosoma;

import java.util.ArrayList;
import java.util.HashMap;

import population.Random_Utilities;
import prac1.cromosoma.Cromosoma;
import prac1.gen.Gen;

public class PermutationCromosoma{
	
	double puntuacion;
	private double puntuacionAcum;
	private double valor;
	private double fitness;
	private double fitnessAjus;
	//comprueba si hay un cierto valor en el individuo
	private HashMap<Integer,Integer> hayGen;
	
	protected ArrayList<Integer> individuo;
	
	public PermutationCromosoma() {
		this.hayGen=new HashMap<Integer,Integer>();
		this.individuo=new ArrayList<Integer>();
	};

	public PermutationCromosoma(int numGen) {
		this.hayGen=new HashMap<Integer,Integer>();
		this.individuo=new ArrayList<Integer>();
		onCreate(numGen);
	}
	
	public void onCreate(int numGen) {
		int numAleatorio;
		while(individuo.size()<numGen) {
			numAleatorio=Random_Utilities.getInstance().nextInt()%numGen;
			if(hayNumero(numAleatorio)==-1) {
				hayGen.put(numAleatorio, hayGen.size());
				individuo.add(numAleatorio);
			}
		}
	}
	public PermutationCromosoma generateCopy() {
		PermutationCromosoma copy = new PermutationCromosoma();
		int num;
		for(int i=0;i<individuo.size();i++) {
			num=individuo.get(i);
			copy.getHayGen().put(num, i);
			copy.getCromosoma().add(num);
		}
		copy.setFitness(fitness);
		copy.setFitnessAjus(fitnessAjus);
		copy.setValor(valor);
		return copy;
		
	}
	
	//comprueba si hay dicho numero en el individuo, si lo hay devuelve su pos
	//en el cromosoma y en caso de que no lo hay devuelve un -1
	public int hayNumero(int num) {
		if(hayGen.containsKey(num)) {
			return hayGen.get(num);
		}else {
			return -1;
		}
	}
	
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
	
	public ArrayList<Integer> getCromosoma() {
		return individuo;
	}
	public void setCromosoma(ArrayList<Integer> cromosoma) {
		this.individuo = cromosoma;
	}

	public int getLongitud() {
		return individuo.size();
	}
	
	public String toStringSol() {
		String soluc="Solucion : " + getValor() + " , con valores : {" + individuo.get(0);
		for(int i=1;i<individuo.size();i++) {
			soluc+=", " + individuo.get(i);
		}
		soluc+="}.";
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

	public HashMap<Integer, Integer> getHayGen() {
		return hayGen;
	}

	public void setHayGen(HashMap<Integer, Integer> hayGen) {
		this.hayGen = hayGen;
	}

}

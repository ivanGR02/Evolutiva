package AlgoritmoGenerico;

import java.util.ArrayList;

import Funciones.Funcion;
import Vista.MainWindow;
import population.cromosoma.Cromosoma;
import population.cromosoma.CromosomaTree;
import population.strategy.CruceStrategy;
import population.strategy.MutationStrategy;
import population.strategy.SelectionStrategy;
import prac1.cromosoma.BinaryCromosoma;
import prac1.cromosoma.DoubleCromosoma;
import prac2.Population.PermutationCromosoma;

public class AnalisisGenerico {
    //poblacion
	 protected ArrayList<AlgoritmoGenerico> algoritmos;
     private int tipo_analisis;// poblacion=0, cruce=1, mutar=2
     private int num_algoritmos=10; 
	 private int num_indiv;
     private int num_indiv_max;
	 private double prob_mut;
     private double prob_mut_max;
	 private double prob_cruce;
     private double prob_cruce_max;
     private Cromosoma cromosoma;

	 public Cromosoma getCromosoma() {
		return cromosoma;
	}

	public void setCromosoma(Cromosoma cromosoma) {
		this.cromosoma = cromosoma;
	}
	private MainWindow mainW;
	 double[] analizados;

	
	 
	 public AnalisisGenerico(MainWindow mainW) {
		 this.mainW=mainW;
		  algoritmos=new ArrayList<AlgoritmoGenerico>();
		  analizados= new double[num_algoritmos];
	 }
	 
	 public void setters(int num_indiv, int num_indiv_max,double prob_cruce, double prob_cruce_max, double prob_mut, 
           double prob_mut_max, int tipo_analisis) {
			algoritmos=new ArrayList<AlgoritmoGenerico>();
		 //mejoresGeneracion=new ArrayList<Cromosoma>();
		 this.num_indiv=num_indiv;
         this.num_indiv_max=num_indiv_max;
		 this.prob_cruce=prob_cruce;
         this.prob_cruce_max=prob_cruce_max;
		 this.prob_mut=prob_mut;
         this.prob_mut_max=prob_mut_max;
		 this.tipo_analisis=tipo_analisis;
		 iniciarProblema();
	 }
	 
	 public void run() throws InterruptedException {
		// tienes que hacer run de todos los algoritmos genericos
		iniciarProblema();
        for(int i=0;i<num_algoritmos;i++){
            algoritmos.get(i).start();
        }
        
        for(int i=0;i<num_algoritmos;i++){
            algoritmos.get(i).join();
        }
		double mejor=1000000;
		int indice=-1;
		double[] mejores=new double[num_algoritmos];
		for(int i=0;i<num_algoritmos;i++){
			mejores[i]=algoritmos.get(i).getMejor().getValor();
			if(algoritmos.get(i).getMejor().getValor()<mejor){
				mejor=algoritmos.get(i).getMejor().getValor();
				indice=i;
			}
		}
		String sol;
		if(cromosoma instanceof CromosomaTree ){
			 sol="El mejor resultado corresponde al parametro "
			+analizados[indice]+" y la funcion es la siguiente :\n"+algoritmos.get(indice).getMejor().toStringSol();
		} else{
			sol="El mejor resultado corresponde al parametro "
			+analizados[indice]+" y la ruta es "+algoritmos.get(indice).getMejor().toStringSol();
		}
		mainW.onUpdateAnalisis(mejores, analizados, num_algoritmos, sol);
	 }
	 
	 public void iniciarProblema() {
		onReset();
		analizados= new double[num_algoritmos];
		for(int i=0;i<num_algoritmos;i++){
			algoritmos.add(mainW.createAlgoritmoGenerico());
			if(tipo_analisis==0) {
                int valor =(num_indiv_max-num_indiv)/num_algoritmos;// cada cuanto varia el analisis
				analizados[i]=num_indiv+(valor*i);
                algoritmos.get(i).setNum_indiv(num_indiv+(valor*i));
                algoritmos.get(i).setProb_mut(0.05);
                algoritmos.get(i).setProb_cruce(0.6);
			}else if(tipo_analisis==1) {
                double valor =(prob_cruce_max-prob_cruce)/num_algoritmos;
				analizados[i]=prob_cruce+(valor*i);
				algoritmos.get(i).setNum_indiv(100);
				algoritmos.get(i).setProb_mut(0.05);
				algoritmos.get(i).setProb_cruce(prob_cruce+(valor*i));// valor que toque
			}else if(tipo_analisis==2) {
                double valor =(prob_mut_max-prob_mut)/num_algoritmos;
                analizados[i]=prob_mut+(valor*i);
                algoritmos.get(i).setNum_indiv(100);
                algoritmos.get(i).setProb_mut(prob_mut+(valor*i));// valor que toque
                algoritmos.get(i).setProb_cruce(0.6);
			}
			algoritmos.get(i).iniciarProblema(cromosoma);
		}	
	}
	 
	 
	public void onReset() {
		algoritmos.clear();
	}
	
	public int getTipo_analisis(){
		return tipo_analisis;
	}

	public void setTipo_analisis(int tipo_analisis){
		this.tipo_analisis=tipo_analisis;
	}

	public int getNum_indiv() {
		return num_indiv;
	}

	public void setNum_indiv(int num_indiv) {
		this.num_indiv = num_indiv;
	}
    public int getNum_indiv_max() {
		return num_indiv_max;
	}

	public void setNum_indiv_max(int num_indiv_max) {
		this.num_indiv_max = num_indiv_max;
	}

	public double getProb_mut() {
		return prob_mut;
	}

	public void setProb_mut(double prob_mut) {
		this.prob_mut = prob_mut;
	}

    public double getProb_mut_max() {
		return prob_mut_max;
	}

	public void setProb_mut_max(double prob_mut_max) {
		this.prob_mut_max = prob_mut_max;
	}

	public double getProb_cruce() {
		return prob_cruce;
	}

	public void setProb_cruce(double prob_cruce) {
		this.prob_cruce = prob_cruce;
	}

    public double getProb_cruce_max() {
		return prob_cruce_max;
	}

	public void setProb_cruce_max(double prob_cruce_max) {
		this.prob_cruce_max = prob_cruce_max;
	}

	public ArrayList<AlgoritmoGenerico> getAlgoritmos() {
		return algoritmos;
	}

	public void setPob(ArrayList<AlgoritmoGenerico> algoritmos) {
		this.algoritmos = algoritmos;
	}
	
	public String toString() {
		return "Analisis";
	}
	public void setNum_algoritmos(int num_algoritmos){
		this.num_algoritmos=num_algoritmos;
	}
}



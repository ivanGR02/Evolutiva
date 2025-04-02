package AlgoritmoGenerico;

import java.util.ArrayList;

import population.strategy.CruceStrategy;
import population.strategy.MutationStrategy;
import population.strategy.SelectionStrategy;
import prac1.MainWindow;
import prac1.Funciones.Funcion;
import prac1.cromosoma.BinaryCromosoma;
import prac1.cromosoma.Cromosoma;
import prac1.cromosoma.DoubleCromosoma;

public class AlgoritmoGenerico{
	//poblacion
	 protected ArrayList<Cromosoma> pob;
	 private int num_indiv;
	 private double prob_mut;
	 private double prob_cruce;
	 private int posMejor;
	 private Cromosoma mejor;
	 //private ArrayList<Cromosoma> mejoresGeneracion;
	 private int maxGeneration;
	 //Strategy
	 private SelectionStrategy seleccion;
	 private CruceStrategy reproduccion;
	 private MutationStrategy mutacion;
	 private double porcentajeElitismo;
	 protected ArrayList<Cromosoma> elite;//todo
	 protected double[] mediaGeneracion;//todo
	 protected double[] mejorAbsoluto;
	 protected double[] mejorGeneracion;//todo
	 protected double[] presionAdaptativa;//todo
	 private MainWindow mainW;
	 private Boolean calibrar=false;
	 //funcion
	 private Funcion funcion;
	 //solucion del problema
	 protected Cromosoma sol;
	 
	 public AlgoritmoGenerico(MainWindow mainW) {
		 this.mainW=mainW;
	 }
	 
	 public void setters(int num_indiv, double prob_cruce, double prob_mut, int maxGeneration, 
			 SelectionStrategy seleccion, CruceStrategy reproduccion, MutationStrategy mutacion,
			  double porcentajeElitismo, Funcion funcion,Cromosoma cromosoma) {
		 pob=new ArrayList<Cromosoma>();
		 //mejoresGeneracion=new ArrayList<Cromosoma>();
		 this.num_indiv=num_indiv;
		 this.prob_cruce=prob_cruce;
		 this.prob_mut=prob_mut;
		 this.maxGeneration=maxGeneration;
		 this.funcion=funcion;
		 this.seleccion=seleccion;
		 this.reproduccion=reproduccion;
		 this.mutacion=mutacion;
		 this.porcentajeElitismo=porcentajeElitismo;
		 elite=new ArrayList<Cromosoma>();
		 mediaGeneracion=new double[maxGeneration+1];
		 mejorAbsoluto=new double[maxGeneration+1];
		 mejorGeneracion=new double[maxGeneration+1];
		 presionAdaptativa=new double[maxGeneration+1];
		 iniciarProblema(cromosoma);
	 }
	 
	 public void run() {
		 //iniciarproblema
		 evaluacion();
		 onUpdateDate(0);
		 for(int i=0;i<maxGeneration;i++) {
			 seleccion.seleccionarPob(pob, porcentajeElitismo, elite);
			 reproduccion.reproduccion(pob, prob_cruce);
			 mutacion.mutacion(pob, prob_mut);
			 incluirElite();
			 evaluacion();
			 onUpdateDate(i+1);
		 }
		 //mostrar resultado
		 mainW.onUpdateResult(mejorAbsoluto, mejorGeneracion, mediaGeneracion, presionAdaptativa,sol.toStringSol(), mejorAbsoluto.length);
	 }
	 
	 public void iniciarProblema(Cromosoma cromosomaType) {
			onReset();
			Cromosoma nuevo=null;
			for(int i=0;i<getNum_indiv();i++){
				if(cromosomaType instanceof BinaryCromosoma) {
					nuevo = new BinaryCromosoma(funcion.getMaxTam(),funcion.getMinTam(),
							funcion.getPrecision(),funcion.getNumGen());
				}else if(cromosomaType instanceof DoubleCromosoma) {
					nuevo = new DoubleCromosoma(funcion.getMaxTam(),funcion.getMinTam(),
							funcion.getPrecision(),funcion.getNumGen());
				}
				pob.add(nuevo);
			}	
		}
	 
	 public void evaluacion(){
		 double mejorPuntuacion=0,sumaAdap=0,puntAcum=0;
		 int posMejor=0;
		 double currentFitness;
		 for(int i=0;i<pob.size();i++) {
			 funcion.calcular(pob.get(i));
			 currentFitness=pob.get(i).getFitness();//calcula y devuelve el valor de la función
			 sumaAdap+=currentFitness;
			 if(currentFitness>mejorPuntuacion) {
				 posMejor=i;
				 mejorPuntuacion=currentFitness;
			 }
		 }
		 setMejor(pob.get(posMejor));
		 for(int i=0;i<pob.size();i++) {
			pob.get(i).ajustarFitness(sumaAdap,pob.size(),mejorPuntuacion);
		 } 
		 for(int i=0;i<pob.size();i++) {
			 pob.get(i).setPuntuacion(pob.get(i).getFitnessCalculo(calibrar)/sumaAdap);// devuelve el fitness ajustado
			 puntAcum+=pob.get(i).getPuntuacion();
			 pob.get(i).setPuntuacionAcum(puntAcum);
		 } 
	}
	 
	 public void onUpdateDate(int generacionActual) {
		 
		//sol y mejor absoluto
		 if(generacionActual==0) {
			 updateSol(mejor);
			 mejorAbsoluto[generacionActual]=mejor.getValor();
		 }else if(sol.getFitness()<mejor.getFitness()) {
			 updateSol(mejor);
			 mejorAbsoluto[generacionActual]=mejor.getValor();
		 }else {
			 mejorAbsoluto[generacionActual]=mejorAbsoluto[generacionActual-1];
		 }
		 //mejorGeneracion
		 mejorGeneracion[generacionActual]=mejor.getValor();
		 //media poblacion
		 double sumaAcum=0;
		 for(Cromosoma cr:pob) {
			 sumaAcum+=cr.getValor();
		 }
		 mediaGeneracion[generacionActual]=sumaAcum/num_indiv;
		 //precion evolutiva
		 //todo
		 presionAdaptativa[generacionActual]=mejor.getPuntuacion()*num_indiv;
	 }
	 
	 public void updateSol(Cromosoma copiado) {
		 sol=copiado.generateCopy();
	 }
	 
	public void onReset() {
		pob.clear();
		elite.clear();
	}
	
	public void incluirElite(){
		for(Cromosoma cr:elite) {
			pob.add(cr);
		}
		elite.clear();
	}

	public int getNum_indiv() {
		return num_indiv;
	}

	public void setNum_indiv(int num_indiv) {
		this.num_indiv = num_indiv;
	}

	public double getProb_mut() {
		return prob_mut;
	}

	public void setProb_mut(double prob_mut) {
		this.prob_mut = prob_mut;
	}

	public double getProb_cruce() {
		return prob_cruce;
	}

	public void setProb_cruce(double prob_cruce) {
		this.prob_cruce = prob_cruce;
	}

	public int getPosMejor() {
		return posMejor;
	}

	public void setPosMejor(int posMejor) {
		this.posMejor = posMejor;
	}

	public Cromosoma getMejor() {
		return mejor;
	}

	public void setMejor(Cromosoma mejor) {
		this.mejor = mejor;
	}

	public int getMaxGeneration() {
		return maxGeneration;
	}

	public void setMaxGeneration(int maxGeneration) {
		this.maxGeneration = maxGeneration;
	}

	public SelectionStrategy getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(SelectionStrategy seleccion) {
		this.seleccion = seleccion;
	}

	public CruceStrategy getReproduccion() {
		return reproduccion;
	}

	public void setReproduccion(CruceStrategy reproduccion) {
		this.reproduccion = reproduccion;
	}

	public MutationStrategy getMutacion() {
		return mutacion;
	}

	public void setMutacion(MutationStrategy mutacion) {
		this.mutacion = mutacion;
	}

	public double getPorcentajeElitismo() {
		return porcentajeElitismo;
	}

	public void setPorcentajeElitismo(double porcentajeElitismo) {
		this.porcentajeElitismo = porcentajeElitismo;
	}

	public ArrayList<Cromosoma> getElite() {
		return elite;
	}

	public void setElite(ArrayList<Cromosoma> elite) {
		this.elite = elite;
	}

	public Cromosoma getSol() {
		return sol;
	}

	public void setSol(Cromosoma sol) {
		this.sol = sol;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public Boolean getCalibrar() {
		return calibrar;
	}

	public void setCalibrar(Boolean calibrar) {
		this.calibrar = calibrar;
	}
	
	public ArrayList<Cromosoma> getPob() {
		return pob;
	}

	public void setPob(ArrayList<Cromosoma> pob) {
		this.pob = pob;
	}
	
	public String toString() {
		return "Practica 1";
	}
}



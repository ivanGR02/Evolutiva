package AlgoritmoGenerico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import Funciones.Funcion;
import Funciones.FuncionP3;
import Vista.MainWindow;
import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.CromosomaTree;
import population.strategy.CruceStrategy;
import population.strategy.MutationStrategy;
import population.strategy.SelectionStrategy;
import prac1.cromosoma.BinaryCromosoma;
import prac1.cromosoma.DoubleCromosoma;
import prac2.Population.PermutationCromosoma;

public class AlgoritmoGenerico extends Thread{
	//poblacion
	 protected ArrayList<Cromosoma> pob;
	 private int num_indiv;
	 private double prob_mut;
	 private double prob_cruce;
	 private int posMejor;
	 private Cromosoma mejor;
	 private int maxGeneration;
	 //Strategy
	 private SelectionStrategy seleccion;
	 private CruceStrategy reproduccion;
	 private MutationStrategy mutacion;
	 private double porcentajeElitismo;
	 protected ArrayList<Cromosoma> elite;//todo
	 protected double[] mediaGeneracion;//todo;
	 protected double[] mejorAbsoluto;
	 protected double[] mejorGeneracion;//todo
	 protected double[] presionAdaptativa;//todo
	 private MainWindow mainW;
	 private Boolean calibrar=false;
	 private Boolean mostrar;
	 //funcion
	 private Funcion funcion;
	 //solucion del problema
	 protected Cromosoma sol;
	 private AnalisisGenerico myAnalisis;
	 private int tipoInicializacion;
	 private int tamProfund;
	 private int numGrupo;
	 private double mediaTam;
	 
	

	public AnalisisGenerico getMyAnalisis() {
		return myAnalisis;
	}

	public void setMyAnalisis(AnalisisGenerico myAnalisis) {
		this.myAnalisis = myAnalisis;
	}

	public AlgoritmoGenerico(MainWindow mainW, boolean mostrar) {
		 this.mainW=mainW;
		 this.mostrar=mostrar;
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
			 seleccion.seleccionarPob(this);
			 reproduccion.reproduccion(pob, prob_cruce);
			 mutacion.mutacion(pob, prob_mut);
			 incluirElite();
			 evaluacion();
			 onUpdateDate(i+1);
			 if(sol instanceof CromosomaTree) {
				 penalizacionBienFundamentada();
				 //zactualizarMediaTam();
				 //metodoTarpeian();
			 }
		 }
		 //mostrar resultado
		 if(mostrar) {
			 if(mejor instanceof CromosomaTree) {
				 ((CromosomaTree)sol).initSol();
				 mainW.onUpdateP3(FuncionP3.soluciones, FuncionP3.database, sol, pob.size(), presionAdaptativa,mejorGeneracion,mediaGeneracion,mejorAbsoluto);
			 }else {
				 mainW.onUpdateResult(mejorAbsoluto, mejorGeneracion, mediaGeneracion, presionAdaptativa,sol.toStringSol(), mejorAbsoluto.length);
			 }
		 }
	 }
	 
	 public void iniciarProblema(Cromosoma cromosomaType) {
		onReset();
		seleccion=seleccion.generateCopy();
		reproduccion=reproduccion.generateCopy();
		mutacion=mutacion.generateCopy();
		Cromosoma nuevo=null;
		
		for(int i=0;i<getNum_indiv();i++){
			if(cromosomaType instanceof BinaryCromosoma) {
				nuevo = new BinaryCromosoma(funcion.getMaxTam(),funcion.getMinTam(),
						funcion.getPrecision(),funcion.getNumGen());
			}else if(cromosomaType instanceof DoubleCromosoma) {
				nuevo = new DoubleCromosoma(funcion.getMaxTam(),funcion.getMinTam(),
						funcion.getPrecision(),funcion.getNumGen());
			}else if(cromosomaType instanceof PermutationCromosoma) {
				nuevo=new PermutationCromosoma(funcion.getNumGen(),true);
			}else {
				nuevo=new CromosomaTree((i%numGrupo)+2, tipoInicializacion);
			}
			pob.add(nuevo);
		}
		 actualizarMediaTam();
	}
	 
	 public void evaluarFitness() {
		 for(int i=0;i<pob.size();i++) {
			 funcion.calcular(pob.get(i));
		 }
	 }
	 
	 public void evaluacion(){
		 double mejorPuntuacion=0,sumaAdap=0,puntAcum=0;
		 int posMejor=0;
		 double currentFitness;
		 evaluarFitness();
		 for(int i=0;i<pob.size();i++) {
			
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
			 mejorAbsoluto[generacionActual]=sol.getValor();
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
		this.sol = sol.generateCopy();
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
	
	public int getTamProfund() {
		return tamProfund;
	}

	public void setTamProfund(int tamProfund) {
		numGrupo=tamProfund-1;
		this.tamProfund = tamProfund;
	}

	public int getTipoInicializacion() {
		return tipoInicializacion;
	}

	public void setTipoInicializacion(int tipoInicializacion) {
		this.tipoInicializacion = tipoInicializacion;
	}
	public void metodoTarpeian() {
		for(Cromosoma cr:pob) {
			if(cr.getLongitud()>mediaTam && Random_Utilities.getInstance().nextBoolean()) {
				cr.setFitness(0);
			}
		}
		actualizarMediaTam();
	}
	private int contador=0;
	public void actualizarMediaTam() {
		double[] tam=new double[pob.size()];
		int i=0;
		for(Cromosoma cr:pob) {
			  tam[i]=(double)((CromosomaTree)cr).getLongitud();
			  i++;
		}
		this.mediaTam=calcularMedia(tam);
		if(mediaTam>31) {
			System.out.println("contador " + contador + " : " + mediaTam);
		}
	}
	 public void penalizacionBienFundamentada() {
		  double[] tam=new double[pob.size()];
		  double[] fitness=new double[pob.size()];
		  int i=0;
		  for(Cromosoma cr:pob) {
			  tam[i]=(double)((CromosomaTree)cr).getLongitud();
			  fitness[i]=((CromosomaTree)cr).getFitness();
			  i++;
		  }
		  double varianza=calcularVarianza(tam);
		  double covarianza=calcularCovarianza(tam, fitness);
		  
		  if(varianza!=0.0) {
			  double div=covarianza/varianza;
			  double factor;
			  i=0;
			  for(Cromosoma cr:pob) {
				  factor=(div*(double)cr.getLongitud());
				  double nuevoFitness=cr.getFitness()+factor;
				  cr.setFitness(nuevoFitness<0?0:nuevoFitness);
				  
			  }
		  }
	 }
	   
	// Método para calcular la varianza
	   private double calcularVarianza(double[] datos) {
	       double media = calcularMedia(datos);
	       double sumaCuadrados = 0.0;
	       for (double dato : datos) {
	           sumaCuadrados += Math.pow(dato - media, 2);
	       }
	       return sumaCuadrados / (double)datos.length;
	   }
	   
	   // Método para calcular la media aritmética
	   private double calcularMedia(double[] datos) {
	       double suma = 0.0;
	       for (double dato : datos) {
	           suma += dato;
	       }
	       return suma / (double)datos.length;
	   }
	   
	   // Método para calcular la covarianza
	   private double calcularCovarianza(double[] datos1, double[] datos2) {
	       if (datos1.length != datos2.length) {
	           throw new IllegalArgumentException("Los conjuntos de datos deben tener la misma longitud");
	       }
	       double media1 = calcularMedia(datos1);
	       double media2 = calcularMedia(datos2);
	       double suma = 0.0;
	       for (int i = 0; i < datos1.length; i++) {
	           suma += (datos1[i] - media1) * (datos2[i] - media2);
	       }
	       return suma / (double)datos1.length;
	   }
	   
	
	public String toString() {
		return "Practica 3";
	}
}



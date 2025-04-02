package prac1.cromosoma;

import java.util.ArrayList;

import Funciones.Funcion;
import population.cromosoma.Cromosoma;
import prac1.gen.DoubleGen;

public class DoubleCromosoma extends Cromosoma{
	
	public DoubleCromosoma() {
		super();
	}

	public DoubleCromosoma(ArrayList<Double> max, ArrayList<Double> min, Double precision, int numGen) {
		super(max, min, precision, numGen);
	}

	@Override
	public void onCreate(ArrayList<Double> max,ArrayList<Double> min,Double precision,int numGen) {
		for(int i=0;i<numGen;i++) {
			cromosoma.add(new DoubleGen(max.get(i),
					min.get(i),precision));
		}
	}
	
	public String toString(){
		return "Cromosoma Double";
	}

	@Override
	public Cromosoma generateCopy() {
		DoubleCromosoma copy=new DoubleCromosoma();
		for(int i=0;i<cromosoma.size();i++) {
    		copy.getCromosoma().add(getCromosoma().get(i).generateCopy());
    	}
		copy.setFitness(getFitness());
		copy.setValor(getValor());
		copy.setFitnessAjus(getFitnessAjus());
		return copy;
	}
	
	/*@Override
	public Cromosoma generateCopy(ArrayList<Double> max, ArrayList<Double> min, Double precision, int numGen) {
		DoubleCromosoma copy=new DoubleCromosoma(max, min, precision, numGen);
		for(int i=0;i<cromosoma.size();i++) {
    		for(int j=0;j<cromosoma.get(i).getAlelos().size();j++) {
    			copy.getCromosoma().get(i).setGenotipo(cromosoma.get(i).getFenotipo().doubleValue());
    		}
    	}
		copy.setFitness(getFitness());
		copy.setValor(getValor());
		copy.setFitnessAjus(getFitnessAjus());
		return copy;
	}*/
}

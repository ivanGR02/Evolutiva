package prac1.cromosoma;

import java.util.ArrayList;

import Funciones.Funcion;
import population.cromosoma.Cromosoma;
import prac1.gen.BinaryGen;

public class BinaryCromosoma extends Cromosoma{
	
	public BinaryCromosoma() {
		super();
	}
	
	public BinaryCromosoma(ArrayList<Double> max, ArrayList<Double> min, Double precision, int numGen) {
		super(max, min, precision, numGen);
	}

	@Override
	public void onCreate(ArrayList<Double> max, ArrayList<Double> min, Double precision, int numGen) {
		for(int i=0;i<numGen;i++) {
    		cromosoma.add(new BinaryGen(max.get(i)
    				,min.get(i),precision));
    	}
	}
	
	public String toString(){
		return "Cromosoma Binario";
	}

	@Override
	public Cromosoma generateCopy() {
		BinaryCromosoma copy=new BinaryCromosoma();
		for(int i=0;i<cromosoma.size();i++) {
			copy.getCromosoma().add(getCromosoma().get(i).generateCopy());
		}
		copy.setFitness(getFitness());
		copy.setValor(getValor());
		copy.setFitnessAjus(getFitnessAjus());
		return copy;
	}
	/*
	public Cromosoma generateCopy() {
		BinaryCromosoma copy=new BinaryCromosoma();
		
		for(int i=0;i<cromosoma.size();i++) {
			for(int j=0;j<cromosoma.get(i).getAlelos().size();j++) {
				copy.getCromosoma().get(i).getAlelos().set(j, getCromosoma().get(i).getAlelos().get(j));
			}
		}
		copy.setFitness(getFitness());
		copy.setValor(getValor());
		copy.setFitnessAjus(getFitnessAjus());
		return copy;
	}*/

}

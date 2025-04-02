package prac1.Funciones;

import java.util.ArrayList;

import Funciones.Funcion;
import population.cromosoma.Cromosoma;

public class Funcion4 extends Funcion{
	
	public Funcion4() {
		setNumGen(2);
	}
	
	@Override
	public void setNumGen(int numGen) {
		super.setNumGen(numGen);
		ArrayList<Double> min = new ArrayList<Double>();
		ArrayList<Double> max = new ArrayList<Double>(); 
		for(int i=0;i<getNumGen();i++){
			min.add(0.0);
			max.add(Math.PI);
		}
		setMinTam(min);
		setMaxTam(max);
	}

	@Override
	public void calcular(Cromosoma cromosoma) {
		cromosoma.setValor(operacion(cromosoma));
		cromosoma.setFitness(-cromosoma.getValor());
	}
	
	public double operacion(Cromosoma cromosoma){// necesitamos las dimensiones
		double sum = 0;
		for (int i = 0; i <getNumGen() ; i++) {
			double x =(double)cromosoma.getCromosoma().get(i).getFenotipo();
			sum -= Math.sin(x) * Math.pow(Math.sin((i+1)*Math.pow(x, 2)/Math.PI), 2*10);
		}
		return sum;
	}
	
	public String toString(){
		return "Michalewicz";
	}

}

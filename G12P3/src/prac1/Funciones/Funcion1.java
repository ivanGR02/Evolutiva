package prac1.Funciones;

import java.util.ArrayList;

import Funciones.Funcion;
import population.cromosoma.Cromosoma;

public class Funcion1 extends Funcion{
	public Funcion1() {
		ArrayList<Double> min = new ArrayList<Double>(); min.add(-3.0); min.add(4.1);
		ArrayList<Double> max = new ArrayList<Double>();; max.add(12.1);max.add(5.8);
		setMaxTam(max);
		setMinTam(min);
		setNumGen(2);
	}

	@Override
	public void calcular(Cromosoma cromosoma) {
		double x1=(double)cromosoma.getCromosoma().get(0).getFenotipo(); 
		double x2=(double)cromosoma.getCromosoma().get(1).getFenotipo();
		cromosoma.setValor(21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
		cromosoma.setFitness(21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
	}
	
	public String toString(){
		return "Calibracion y prueba";
	}
}

package prac1.Funciones;

import java.util.ArrayList;

import Funciones.Funcion;
import population.cromosoma.Cromosoma;

public class Funcion2 extends Funcion{
	
	public Funcion2() {
		ArrayList<Double> min = new ArrayList<Double>(); min.add(-600.0); min.add(-600.0);
		ArrayList<Double> max = new ArrayList<Double>();; max.add(600.0);max.add(600.0);
		setMinTam(min);
		setMaxTam(max);
		setNumGen(2);
	}

	@Override
	public void calcular(Cromosoma cromosoma) {
        double x1=(double)cromosoma.getCromosoma().get(0).getFenotipo(); 
		double x2=(double)cromosoma.getCromosoma().get(1).getFenotipo();
        double solSumatorio=Math.pow(x1, 2)/4000 + Math.pow(x2, 2)/4000;
        double solMultip=Math.cos(x1/Math.sqrt(1))*Math.cos(x2/Math.sqrt(2));
        
        cromosoma.setValor(solSumatorio-solMultip+1);
        cromosoma.setFitness(200-cromosoma.getValor());// Tenemos que restarle al maximo de la funcion el resultado
	}
	
	public String toString(){
		return "GrieWank";
	}

}

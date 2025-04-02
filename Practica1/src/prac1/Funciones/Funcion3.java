package prac1.Funciones;

import java.util.ArrayList;

import prac1.cromosoma.Cromosoma;

public class Funcion3 extends Funcion{
	
	public Funcion3() {
		ArrayList<Double> min = new ArrayList<Double>(); min.add(-5.0); min.add(-5.0);
		ArrayList<Double> max = new ArrayList<Double>(); max.add(5.0);max.add(5.0);
		setMinTam(min);
		setMaxTam(max);
		setNumGen(2);
	}

	@Override
	public void calcular(Cromosoma cromosoma) {
		 double x1=cromosoma.getCromosoma().get(0).getFenotipo(); 
		 double x2=cromosoma.getCromosoma().get(1).getFenotipo();
		 cromosoma.setValor(0.5*((po(x1,4) - 16*po(x1,2) + 5*x1 )+( po(x2,4)- 16*po(x2,2) + 5*x2)));
		 cromosoma.setFitness(250-cromosoma.getValor());// Tenemos que restarle al maximo de la funcion el resultado
	}
	
	private double po(double valor, int potencia){//potencia
		double aux=valor;
		for(int i=1;i<potencia;i++){
			aux*=valor;
		}
		return aux;
	}
	
	public String toString(){
		return "Styblinski-tang";
	}

}

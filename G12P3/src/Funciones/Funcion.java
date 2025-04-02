package Funciones;

import java.util.ArrayList;

import population.cromosoma.Cromosoma;

public abstract class Funcion {

	private ArrayList<Double> minTam;
	private ArrayList<Double> maxTam;
	protected double precision = 0.01;
	private int numGen;
	
	public abstract void calcular(Cromosoma cromosoma);
	
	public ArrayList<Double> getMinTam() {
		return minTam;
	}
	public void setMinTam(ArrayList<Double> minTam) {
		this.minTam = minTam;
	}
	public ArrayList<Double> getMaxTam() {
		return maxTam;
	}
	public void setMaxTam(ArrayList<Double> maxTam) {
		this.maxTam = maxTam;
	}
	public double getPrecision() {
		return precision;
	}
	public void setPrecision(double precision) {
		this.precision = precision;
	}
	public int getNumGen() {
		return numGen;
	}
	public void setNumGen(int numGen) {
		this.numGen = numGen;
	}
}

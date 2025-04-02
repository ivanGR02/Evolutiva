package prac1.gen;

import java.util.ArrayList;

public abstract class Gen {
	
	private int tamGen;
	private Double max,min,precision;
	ArrayList<Object> alelos;
	
	public Gen() {
		alelos=new ArrayList<Object>();
	}

	public Gen(Double max,Double min,Double precision) {
		this.setMax(max);
		this.setMin(min);
		this.setPrecision(precision);
		this.setTamGen(calcularTamGen());
		alelos=new ArrayList<Object>();
		initGen();
	}
	
	public abstract Gen generateCopy();

	public int getTam() {
		return alelos.size();
	}

	public abstract Double getFenotipo();
	public abstract void setGenotipo(double valor);

	abstract int calcularTamGen();
	public abstract void initGen();
	public abstract void mutation(double prob);
	
	//get and set
	public int getTamGen() {
		return tamGen;
	}
	public void setTamGen(int tamGen) {
		this.tamGen = tamGen;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getPrecision() {
		return precision;
	}
	public void setPrecision(Double precision) {
		this.precision = precision;
	}

	public ArrayList<Object> getAlelos() {
		return alelos;
	}

	public void setAlelos(ArrayList<Object> alelos) {
		this.alelos = alelos;
	}
	public void setValue(int i, Object valor){
		alelos.set(i, valor);
	}
	public Object getValue(int i){
		return alelos.get(i);
	}
	
}

package prac1.gen;

import population.Random_Utilities;

public class DoubleGen extends Gen {
	
	public DoubleGen() {
		super();
	}
	
    public DoubleGen(Double max, Double min, Double precision) {
		super(max, min, precision);
		
	}

	@Override
	public Double getFenotipo() {
		return (Double) alelos.get(0);
	}

	@Override
	int calcularTamGen() {
		return 1;// solo esta el real
	}

	@Override
	public void initGen() {
		alelos.add((Double)Random_Utilities.getInstance().nextDouble(getMin(), getMax()+getPrecision()));
	}

	@Override
	public void mutation(double prob) {
		if(Random_Utilities.getInstance().nextDouble()<prob) {
			alelos.set(0,Random_Utilities.getInstance().nextDouble(getMin(),getMax()+Double.MIN_VALUE));
		}
	}

	@Override
	public void setGenotipo(double valor) {
		alelos.set(0,valor);
	}

	@Override
	public Gen generateCopy() {
		DoubleGen copy=new DoubleGen();
		copy.setMax(getMax());
		copy.setMin(getMin());
		copy.setPrecision(getPrecision());
		copy.setTamGen(getTamGen());
		//solo tiene un alelo que es el double
		copy.getAlelos().add(getAlelos().get(0));
		return copy;
	}

}

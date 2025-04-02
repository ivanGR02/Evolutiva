package prac1.gen;

import population.Random_Utilities;

public class BinaryGen extends Gen{
	
	public BinaryGen() {
		super();
	}

	public BinaryGen(Double max, Double min, Double precision) {
		super(max, min, precision);
	}

	@Override
	int calcularTamGen() {
		return (int) (Math.log10(((this.getMax() - this.getMin()) / this.getPrecision()) + 1) / Math.log10(2));
	}

	@Override
	public void initGen() {
		for(int i=0;i<getTamGen();i++) {
			alelos.add(Random_Utilities.getInstance().nextBoolean());
		}
	}
	
	@Override
	public void mutation(double prob) {
		for(int i=0;i<getTamGen();i++) {
			if(Random_Utilities.getInstance().nextDouble()<prob) {
				alelos.set(i, !(boolean)alelos.get(i));
			}
		}
	}
	
	private double calcularBinario() {
		int acum=0;
		for(int i=0;i<getTamGen();i++) {
			if((boolean) alelos.get(i)) {
				acum+=Math.pow(2, i);
			}
		}
		return acum;
	}
	
	@Override
	public Double getFenotipo() {
		return getMin()+calcularBinario()*(getMax()-getMin())/(Math.pow(2, getTamGen())-1);
	}

	@Override
	public void setGenotipo(double valor) {
		double aux=valor-getMin();
		double valorBinUnidad=(getMax()-getMin())/(Math.pow(2, getTamGen())-1);
		aux=aux/valorBinUnidad;

		for(int i=getTamGen()-1;i>=0;i--){
			if(aux-Math.pow(2,i)>=0){
				alelos.set(i,true);
				aux-=Math.pow(2,i);
			}else{
				alelos.set(i,false);
			}
		}
	}

	@Override
	public Gen generateCopy() {
		BinaryGen copy=new BinaryGen();
		copy.setMax(getMax());
		copy.setMin(getMin());
		copy.setPrecision(getPrecision());
		copy.setTamGen(getTamGen());
		for(int i=0;i<getAlelos().size();i++) {
			copy.getAlelos().add(getAlelos().get(i));
		}
		return copy;
	}
	
}

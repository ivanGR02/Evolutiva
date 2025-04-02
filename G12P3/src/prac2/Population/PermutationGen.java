package prac2.Population;

import population.Random_Utilities;
import population.cromosoma.Gen;

public class PermutationGen extends Gen{
	
	public PermutationGen() {
		super();
		initGen();
	}

	@Override
	public Gen generateCopy() {
		PermutationGen copy=new PermutationGen();
		//su alelo solo es un numero entero
		copy.getAlelos().set(0, getFenotipo());
		return copy;
	}

	@Override
	public Object getFenotipo() {
		return this.getAlelos().get(0);
	}

	@Override
	public void setGenotipo(Object valor) {
		this.alelos.clear();
		this.getAlelos().add(valor);
	}

	@Override
	public int calcularTamGen() {
		return 1;
	}

	@Override
	public void initGen() {
		alelos.add(-1);
	}

	@Override
	public void mutation(double prob) {
		//nohay
	}

	public String toStringSol() {
		return (String) getFenotipo();
	}
}

package prac1;

import java.util.Comparator;

import prac1.cromosoma.Cromosoma;

public class OrdenarCromosoma implements Comparator<Cromosoma>{

	@Override
	public int compare(Cromosoma o1, Cromosoma o2) {
		if(o1.getFitness()<o2.getFitness()) {
			return 1;
		}else if(o1.getFitness()==o2.getFitness()) {
			return 0;
		}else {
			return -1;
		}
	}

}

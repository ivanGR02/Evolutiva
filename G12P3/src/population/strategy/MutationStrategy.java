package population.strategy;

import java.util.ArrayList;

import population.cromosoma.Cromosoma;

public interface MutationStrategy{
	void mutacion(ArrayList<Cromosoma> pob,Double prob_mutation);
	public MutationStrategy generateCopy();
}

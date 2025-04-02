package population.strategy;

import java.util.ArrayList;

import prac1.cromosoma.Cromosoma;

public interface MutationStrategy{
	void mutacion(ArrayList<Cromosoma> pob,Double prob_mutation);
}

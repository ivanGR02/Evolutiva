package population.strategy;

import java.util.ArrayList;

import population.cromosoma.Cromosoma;

public interface CruceStrategy {
	void reproduccion(ArrayList<Cromosoma> pob,Double prob_cruse);
	public CruceStrategy generateCopy();
}

package population.strategy.mutation;

import java.util.ArrayList;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.CromosomaTree;
import population.strategy.MutationStrategy;

public class MutacionExpansion implements MutationStrategy {

	@Override
	public void mutacion(ArrayList<Cromosoma> pob, Double prob_mutation) {
		for(int i=0;i<pob.size();i++) {
			if(Random_Utilities.getInstance().nextDouble()<=prob_mutation) {
               ((CromosomaTree) pob.get(i)).mutacion(5);
			}
		}
	}

	@Override
	public MutationStrategy generateCopy() {
		return new MutacionExpansion();
	}
	public String toString(){
		return "Mutacion Expansion";
	}

}

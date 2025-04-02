package population.strategy.mutation;

import java.util.ArrayList;
import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.CromosomaTree;
import population.strategy.MutationStrategy;
public class MutacionAleatoria3 implements MutationStrategy {

	@Override
	public void mutacion(ArrayList<Cromosoma> pob, Double prob_mutation) {
		for(int i=0;i<pob.size();i++) {
			if(Random_Utilities.getInstance().nextDouble()<=prob_mutation) {
				int mut = Random_Utilities.getInstance().nextInt(7);
               ((CromosomaTree) pob.get(i)).mutacion(mut);
			}
		}
	}

	@Override
	public MutationStrategy generateCopy() {
		return new MutacionAleatoria3();
	}
	public String toString(){
		return "Mutacion Aleatoria";
	}

}

package prac2.Strategy.Mutation;

import java.util.ArrayList;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.MutationStrategy;
import prac2.Population.PermutationCromosoma;

public class MutacionIntercambio implements MutationStrategy{

	@Override
	public void mutacion(ArrayList<Cromosoma> pob, Double prob_mutation) {
		for(int i=0;i<pob.size();i++) {
			if(Random_Utilities.getInstance().nextDouble()<=prob_mutation) {
				int punto1=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				int punto2=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				Gen aux =pob.get(i).getCromosoma().get(punto1).generateCopy();
				Gen aux2 =pob.get(i).getCromosoma().get(punto2).generateCopy();
				// eliminas genes
				((PermutationCromosoma) pob.get(i)).eliminarElemento(punto1);
				((PermutationCromosoma) pob.get(i)).eliminarElemento(punto2);
				// los cambias de lugar
				((PermutationCromosoma) pob.get(i)).anyadirElemento(punto1, aux2);
				((PermutationCromosoma) pob.get(i)).anyadirElemento(punto2, aux);
			}
		}
	
		
	}
	public String toString(){
		return "Mutacion intercambio";
	}
	@Override
	public MutationStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new MutacionIntercambio();
	}
}

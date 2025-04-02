package prac1.Strategy.Mutation;

import java.util.ArrayList;

import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.MutationStrategy;

public class MutationBasica implements MutationStrategy{

	@Override
	public void mutacion(ArrayList<Cromosoma> pob, Double prob_mutation) {
		for(Cromosoma cro:pob) {
			for(Gen genes:cro.getCromosoma()) {
				genes.mutation(prob_mutation);
			}
		}
		
	}
	public String toString(){
		return "Mutacion Basica";
	}
	@Override
	public MutationStrategy generateCopy() {
		return new MutationBasica();
	}
}

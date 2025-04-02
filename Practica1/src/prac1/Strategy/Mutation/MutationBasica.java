package prac1.Strategy.Mutation;

import java.util.ArrayList;

import population.strategy.MutationStrategy;
import prac1.cromosoma.Cromosoma;
import prac1.gen.Gen;

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
}

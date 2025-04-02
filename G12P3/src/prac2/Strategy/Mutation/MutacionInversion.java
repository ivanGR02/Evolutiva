package prac2.Strategy.Mutation;

import java.util.ArrayList;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.MutationStrategy;
import prac2.Population.PermutationCromosoma;

public class MutacionInversion implements MutationStrategy {

	@Override
	public void mutacion(ArrayList<Cromosoma> pob, Double prob_mutation) {
		for(int i=0;i<pob.size();i++) {
			if(Random_Utilities.getInstance().nextDouble()<=prob_mutation) {
				int punto1=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				int punto2=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				Gen aux =pob.get(i).getCromosoma().get(punto1).generateCopy();
				mutar(pob.get(i),punto1,punto2);
			}
		}
	}
	
	public void mutar(Cromosoma cromosoma,int punto1, int punto2) {
		ArrayList<Gen> genes=new ArrayList<Gen>();
		int aux=Math.max(punto1, punto2);
		punto1=Math.min(punto1, punto2);
		punto2=aux;
		// eliminas genes y guardar los genes en una lista auxiliar
		for(int i=punto1;i<=punto2;i++) {
			genes.add(cromosoma.getCromosoma().get(i).generateCopy());
			((PermutationCromosoma) cromosoma).eliminarElemento(i);
		}
		//anyades genes inversas en el rango de los dos puntos
		for(int i=punto1;i<=punto2;i++) {
			((PermutationCromosoma) cromosoma).anyadirElemento(i, genes.get(punto2-i));
		}
	}
	
	public String toString(){
		return "Mutacion inversion";
	}

	@Override
	public MutationStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new MutacionInversion();
	}


}

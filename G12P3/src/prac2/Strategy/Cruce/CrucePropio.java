package prac2.Strategy.Cruce;

import java.util.ArrayList;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.CruceStrategy;
import prac2.Population.PermutationCromosoma;

public class CrucePropio implements CruceStrategy {

	@Override
	public void reproduccion(ArrayList<Cromosoma> pob, Double prob_cruse) {
		ArrayList<Cromosoma> parejas = new ArrayList<>();
		for(int i=0;i<pob.size();i++) {
			if(Random_Utilities.getInstance().nextDouble()<=prob_cruse) {
				parejas.add(pob.get(i));
			}
		}
		if(parejas.size()%2==1) {
			parejas.remove(parejas.size()-1);
		}
		for(int i=0;i<parejas.size();i+=2) {
			cruce(parejas.get(i),parejas.get(i+1));
		}
	}

	protected void cruce(Cromosoma cromosoma, Cromosoma cromosoma2) {
		PermutationCromosoma auxi =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
		PermutationCromosoma auxi2 =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
		int pos=0;
		// Intercambiar los segmentos
		
		// Intenta primero meter los genes del padre 1 y luego los del padre 2
		for(int i=0;i<cromosoma.getCromosoma().size();i++){
			Gen aux =cromosoma.getCromosoma().get(i).generateCopy();
			Gen aux2 =cromosoma2.getCromosoma().get(i).generateCopy();
			if(auxi.anyadirElemento(pos, aux)) {
				pos++;
			}
			if(auxi.anyadirElemento(pos, aux2)) {
				pos++;
			}
		}
		pos=0;
		// Intenta primero meter los genes del padre 2 y luego los del padre 1
		for(int i=0;i<cromosoma.getCromosoma().size();i++){
			Gen aux =cromosoma.getCromosoma().get(i).generateCopy();
			Gen aux2 =cromosoma2.getCromosoma().get(i).generateCopy();
			if(auxi2.anyadirElemento(pos, aux2)) {
				pos++;
			}
			if(auxi2.anyadirElemento(pos, aux)) {
				pos++;
			}
		}
		cromosoma.setCromosoma(auxi.getCromosoma());
		cromosoma2.setCromosoma(auxi2.getCromosoma());
		((PermutationCromosoma)cromosoma).setHayGen(((PermutationCromosoma)auxi).getHayGen());
		((PermutationCromosoma)cromosoma2).setHayGen(((PermutationCromosoma)auxi2).getHayGen());
	}
	public String toString() {
		return "Cruce Propio";
	}

	@Override
	public CruceStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new CrucePropio();
	}
	
}

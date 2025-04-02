package prac2.Strategy.Cruce;

import java.util.ArrayList;


import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.CruceStrategy;
import prac2.Population.PermutationCromosoma;

public class CruceOX  implements CruceStrategy  {

	@Override
	public void reproduccion(ArrayList<Cromosoma> pob,Double prob_cruse) {
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
		int punto1=Random_Utilities.getInstance().nextInt(0, cromosoma.getCromosoma().size());
		int punto2=Random_Utilities.getInstance().nextInt(0, cromosoma.getCromosoma().size());
		intercambiarSegmento(cromosoma,cromosoma2,punto1,punto2);
	}

	private void intercambiarSegmento(Cromosoma cromosoma, Cromosoma cromosoma2, int punto1, int punto2) {
		int ini=Math.min(punto1,punto2);
		int max=Math.max(punto1,punto2);
		PermutationCromosoma auxi =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
		PermutationCromosoma auxi2 =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
		// Intercambiar los segmentos
		for(int i=ini;i<=max;i++){
			Gen aux =cromosoma.getCromosoma().get(i).generateCopy();
			Gen aux2 =cromosoma2.getCromosoma().get(i).generateCopy();
			auxi.anyadirElemento(i, aux2);
			auxi2.anyadirElemento(i,aux);
		}
		// para el cromosoma 1 
		int mirargen=(max+1)%cromosoma.getCromosoma().size(); // que gen estoy mirando en el padre durante el ciclo
		int pos= (max+1)%cromosoma.getCromosoma().size(); // donde pongo el gen del padre en el hijo
		// cromosoma.size()-max+ini son los genes que tengo que añadir hasta que este completo
		for(int i=max-ini+1;i<cromosoma.getCromosoma().size();i++){
			Gen aux =cromosoma.getCromosoma().get(mirargen).generateCopy();
			while(!auxi.anyadirElemento(pos,aux)){// hasta que no introduzca un gen
			 //no para de intentarlo hasta que llegue a uno que no este añadido
				mirargen=(mirargen+1)%cromosoma.getCromosoma().size();
				aux =cromosoma.getCromosoma().get(mirargen).generateCopy();
			}
			pos=(pos+1)%cromosoma.getCromosoma().size();
			mirargen=(mirargen+1)%cromosoma.getCromosoma().size();
		}
		mirargen=(max+1)%cromosoma.getCromosoma().size(); // que gen estoy mirando en el padre durante el ciclo
		pos= (max+1)%cromosoma.getCromosoma().size(); // donde pongo el gen del padre en el hijo
		for(int i=max-ini+1;i<cromosoma2.getCromosoma().size();i++){

			Gen aux =cromosoma2.getCromosoma().get(mirargen).generateCopy();
			while(!auxi2.anyadirElemento(pos,aux)){// si falla el anyadirElemento
				mirargen=(mirargen+1)%cromosoma.getCromosoma().size();
				aux =cromosoma2.getCromosoma().get(mirargen).generateCopy();
			}
			pos=(pos+1)%cromosoma.getCromosoma().size();
			mirargen=(mirargen+1)%cromosoma.getCromosoma().size();
		}
		// los individuos originales los sustituyo por su padre.
		cromosoma.setCromosoma(auxi.getCromosoma());
		cromosoma2.setCromosoma(auxi2.getCromosoma());
		((PermutationCromosoma)cromosoma).setHayGen(((PermutationCromosoma)auxi).getHayGen());
		((PermutationCromosoma)cromosoma2).setHayGen(((PermutationCromosoma)auxi2).getHayGen());
	}

	public String toString(){
		return "Cruce por orden sin Prioridad";
	}
	@Override
	public CruceStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new CruceOX();
	}

}

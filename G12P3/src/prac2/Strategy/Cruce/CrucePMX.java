package prac2.Strategy.Cruce;

import java.util.ArrayList;
import java.util.HashMap;

import AlgoritmoGenerico.AlgoritmoGenerico;
import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.CruceStrategy;
import prac2.Population.PermutationCromosoma;

public class CrucePMX implements CruceStrategy {

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

	void cruce(Cromosoma cromosoma, Cromosoma cromosoma2) {
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
		// los valores entre el inicio y el punto de corte
		for(int i=0;i<ini;i++){
			Gen aux =cromosoma.getCromosoma().get(i).generateCopy();
			Gen aux2 =cromosoma2.getCromosoma().get(i).generateCopy();
			auxi.anyadirElemento(i,aux);
			auxi2.anyadirElemento(i,aux2);
		}
		int count=(max+1)%cromosoma.getCromosoma().size();
		// los valores entre el fin del cruce y el fin del cromosoma
		for(int i=max+1;i<cromosoma.getCromosoma().size();i++){
			Gen aux =cromosoma.getCromosoma().get(i).generateCopy();
			Gen aux2 =cromosoma2.getCromosoma().get(i).generateCopy();
			auxi.anyadirElemento(i,aux);
			auxi2.anyadirElemento(i,aux2);
		}
		
		while(count!=ini) {
			if(!auxi.hayNumEnPos(count)) {
				Gen aux =cromosoma.getCromosoma().get(count).generateCopy();
				
				while(!auxi.anyadirElemento(count,aux)){
					int pos=((PermutationCromosoma) cromosoma2).hayNumero((int)aux.getFenotipo());
					aux=cromosoma.getCromosoma().get(pos).generateCopy();
				}
			}
			if(!auxi2.hayNumEnPos(count)) {
				Gen aux2 =cromosoma2.getCromosoma().get(count).generateCopy();
				while(!auxi2.anyadirElemento(count,aux2)){
					int pos=((PermutationCromosoma) cromosoma).hayNumero((int)aux2.getFenotipo());
					aux2=cromosoma2.getCromosoma().get(pos).generateCopy();
				}
			}
			count=(count+1)%cromosoma.getCromosoma().size();
		}
		// los individuos originales los sustituyo por su padre.
		cromosoma.setCromosoma(auxi.getCromosoma());
		cromosoma2.setCromosoma(auxi2.getCromosoma());
		((PermutationCromosoma)cromosoma).setHayGen(((PermutationCromosoma)auxi).getHayGen());
		((PermutationCromosoma)cromosoma2).setHayGen(((PermutationCromosoma)auxi2).getHayGen());

	}
	public String toString(){
		return "Cruce por emparejamiento parcial";
	}

	@Override
	public CruceStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new CrucePMX();
	}
}

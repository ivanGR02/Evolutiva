package prac2.Strategy.Cruce;

import java.util.ArrayList;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.strategy.CruceStrategy;
import prac2.Population.PermutationCromosoma;

public class CruceOXPP implements CruceStrategy{

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
		int punto1=Random_Utilities.getInstance().nextInt(0, cromosoma.getCromosoma().size());
		int punto2=Random_Utilities.getInstance().nextInt(0, cromosoma.getCromosoma().size());
		while(punto1==punto2) {
			punto2=Random_Utilities.getInstance().nextInt(0, cromosoma.getCromosoma().size());
		}
		intercambiarSegmento(cromosoma,cromosoma2,punto1,punto2);
		
	}

	private void intercambiarSegmento(Cromosoma cromosoma, Cromosoma cromosoma2, int punto1, int punto2) {
		int ini=Math.min(punto1,punto2);
		int max=Math.max(punto1,punto2);
		PermutationCromosoma auxi =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
		PermutationCromosoma auxi2 =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
		auxi.anyadirElemento(ini, cromosoma2.getCromosoma().get(ini).generateCopy());
		auxi.anyadirElemento(max,cromosoma2.getCromosoma().get(max).generateCopy());
		auxi2.anyadirElemento(ini, cromosoma.getCromosoma().get(ini).generateCopy());
		auxi2.anyadirElemento(max, cromosoma.getCromosoma().get(max).generateCopy());
		int faltan=auxi.getCromosoma().size()-2;
		int pos=(max+1)%auxi.getCromosoma().size();
		int current=(max+1)%auxi.getCromosoma().size();
		while(faltan!=0) {
			if(!auxi.hayNumEnPos(current)) {
				while(!auxi.anyadirElemento(current,cromosoma.getCromosoma().get(pos).generateCopy())) {
					pos=(pos+1)%auxi.getCromosoma().size();
				}
				pos=(pos+1)%auxi.getCromosoma().size();
				current=(current+1)%auxi.getCromosoma().size();
				faltan--;
			}else {
				current=(current+1)%auxi.getCromosoma().size();
			}
			
			
		}
		faltan=auxi2.getCromosoma().size()-2;
		pos=(max+1)%auxi2.getCromosoma().size();
		current=(max+1)%auxi2.getCromosoma().size();
		while(faltan!=0) {
			if(!auxi2.hayNumEnPos(current)) {
				while(!auxi2.anyadirElemento(current,cromosoma2.getCromosoma().get(pos).generateCopy())) {
					pos=(pos+1)%auxi2.getCromosoma().size();
				}
				pos=(pos+1)%auxi2.getCromosoma().size();
				current=(current+1)%auxi2.getCromosoma().size();
				faltan--;
			}else {
				current=(current+1)%auxi2.getCromosoma().size();
			}
		}
		cromosoma.setCromosoma(auxi.getCromosoma());
		cromosoma2.setCromosoma(auxi2.getCromosoma());
		((PermutationCromosoma)cromosoma).setHayGen(((PermutationCromosoma)auxi).getHayGen());
		((PermutationCromosoma)cromosoma2).setHayGen(((PermutationCromosoma)auxi2).getHayGen());
	}
	
	public String toString(){
		return "Cruce por orden Posiciones Prioritarias";
	}

	@Override
	public CruceStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new CruceOXPP();
	}

}

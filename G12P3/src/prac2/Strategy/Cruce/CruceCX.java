package prac2.Strategy.Cruce;

import java.util.ArrayList;
import java.util.HashSet;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.CruceStrategy;
import prac2.Population.PermutationCromosoma;

public class CruceCX  implements CruceStrategy  {

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
		PermutationCromosoma auxi =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
		PermutationCromosoma auxi2 =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
	
		HashSet<Integer> posic1 =new HashSet<Integer>(); posic1.add(0); // las posiciones ya añadidas al auxi
		
		Gen aux =cromosoma.getCromosoma().get(0).generateCopy();// el inicio del ciclo
		
		Integer pos =0; // la posicion del inicio del ciclo
		
		Gen val = cromosoma2.getCromosoma().get(pos).generateCopy(); // ver que gen esta en esa posicion en el padre opuesto
		while(auxi.anyadirElemento(pos, aux)){
			pos= ((PermutationCromosoma) cromosoma).hayNumero((int)val.getFenotipo());
			// ver donde esta el gen del padre opuesto en el padre 1
			val=cromosoma2.getCromosoma().get(pos).generateCopy();
			aux= cromosoma.getCromosoma().get(pos).generateCopy();
			posic1.add(pos);
		}
		for(int i=0;i<cromosoma.getCromosoma().size();i++){
			// rellenar los huecos vacios
			if(!posic1.contains(i)){
				Gen aux1 =cromosoma2.getCromosoma().get(i).generateCopy();
				auxi.anyadirElemento(i, aux1);
			}
		}
		// LO MISMO QUE ANTES PERO PARA EL OTRO CROMOSOMA 
		HashSet<Integer> posic2 =new HashSet<Integer>(); posic2.add(0); // las posiciones ya añadidas al auxi
		
		aux =cromosoma2.getCromosoma().get(0).generateCopy();// el inicio del ciclo
		
		pos =0; // la posicion del inicio del ciclo
		
		val = cromosoma.getCromosoma().get(pos).generateCopy(); // ver que gen esta en esa posicion en el padre opuesto
		
		while(auxi2.anyadirElemento(pos, aux)){
			pos= ((PermutationCromosoma) cromosoma2).hayNumero((int)val.getFenotipo());
			// ver donde esta el gen del padre opuesto en el padre 1
			val=cromosoma.getCromosoma().get(pos).generateCopy();
			aux= cromosoma2.getCromosoma().get(pos).generateCopy();
			posic2.add(pos);
		}

		for(int i=0;i<cromosoma.getCromosoma().size();i++){
			// rellenar los huecos vacios
			if(!posic2.contains(i)){
				Gen aux1 =cromosoma.getCromosoma().get(i).generateCopy();
				auxi2.anyadirElemento(i, aux1);
			}
		}
		// hacer el cruce
		cromosoma.setCromosoma(auxi.getCromosoma());
		cromosoma2.setCromosoma(auxi2.getCromosoma());
		((PermutationCromosoma)cromosoma).setHayGen(((PermutationCromosoma)auxi).getHayGen());
		((PermutationCromosoma)cromosoma2).setHayGen(((PermutationCromosoma)auxi2).getHayGen());
	}

	public String toString(){
		return "Cruce por Ciclos";
	}

	@Override
	public CruceStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new CruceCX();
	}

}

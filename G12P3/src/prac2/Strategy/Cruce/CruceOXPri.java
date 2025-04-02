package prac2.Strategy.Cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.CruceStrategy;
import prac2.Population.PermutationCromosoma;

public class CruceOXPri implements CruceStrategy{


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
		int casillas=Random_Utilities.getInstance().nextInt(0, cromosoma.getCromosoma().size());
        ArrayList<Integer> puntos = new ArrayList<Integer>();
        HashSet<Integer> estan = new HashSet<Integer>();
        for(int i=0;i<casillas;i++){
            int aux=Random_Utilities.getInstance().nextInt(0, cromosoma.getCromosoma().size());
            while(estan.contains(aux)){
                aux++;
				aux=aux%cromosoma.getCromosoma().size(); 
            }
           puntos.add(aux);
           estan.add(aux);
        } 
		Collections.sort(puntos);
		intercambiarSegmento(cromosoma,cromosoma2,puntos);
	}

	private void intercambiarSegmento(Cromosoma cromosoma, Cromosoma cromosoma2,ArrayList<Integer> puntos) {
		PermutationCromosoma auxi =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
		PermutationCromosoma auxi2 =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);

		ArrayList<Integer> posi1 =new ArrayList<Integer>();
		ArrayList<Integer> posi2 =new ArrayList<Integer>();
		HashSet<Integer> posic1 =new HashSet<Integer>();
		HashSet<Integer> posic2 =new HashSet<Integer>();
		for(int i=0;i<puntos.size();i++){
			Integer aux = (Integer) cromosoma.getCromosoma().get(puntos.get(i)).getFenotipo();
			// devuelve que valor esta guardado en esa posicion
			Integer aux2 = (Integer) cromosoma2.getCromosoma().get(puntos.get(i)).getFenotipo();
			// Las posiciones que se quedan libre del padre opuesto. 
			Integer pos = ((PermutationCromosoma) cromosoma).hayNumero(aux2); 
			Integer pos2 = ((PermutationCromosoma) cromosoma2).hayNumero(aux);
			posi1.add(pos); 
			posic1.add(pos);
			posi2.add(pos2);
			posic2.add(pos2);

		}
		// ordeno las posiciones disponibles
		Collections.sort(posi1);
		Collections.sort(posi2);
		// 
		for(int i=0;i<posi1.size();i++){
			Gen aux =cromosoma.getCromosoma().get(puntos.get(i)).generateCopy();
			Gen aux2 =cromosoma2.getCromosoma().get(puntos.get(i)).generateCopy();
			auxi.anyadirElemento(posi2.get(i), aux);
			auxi2.anyadirElemento(posi1.get(i),aux2);
		}
		// ahora copio los valores que no tienen prioridad
		for(int i=0;i<cromosoma.getCromosoma().size();i++) {
			Gen aux =cromosoma.getCromosoma().get(i).generateCopy();
			Gen aux2 =cromosoma2.getCromosoma().get(i).generateCopy();
			if(!posic1.contains(i)) {
				auxi2.anyadirElemento(i,aux);
			}
			if(!posic2.contains(i)) {
				auxi.anyadirElemento(i,aux2);
			}
		}
		// los individuos originales los sustituyo por su padre.
		cromosoma.setCromosoma(auxi.getCromosoma());
		cromosoma2.setCromosoma(auxi2.getCromosoma());
		((PermutationCromosoma)cromosoma).setHayGen(((PermutationCromosoma)auxi).getHayGen());
		((PermutationCromosoma)cromosoma2).setHayGen(((PermutationCromosoma)auxi2).getHayGen());
	}
	
    public String toString(){
		return "Cruce por orden con Prioridad";
	}
	@Override
	public CruceStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new CruceOXPri();
	}

	

}

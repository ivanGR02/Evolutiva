package population.strategy.selection;

import java.util.ArrayList;
import java.util.Comparator;

import population.strategy.SelectionStrategy;
import prac1.cromosoma.Cromosoma;
import prac1.cromosoma.OrdenarCromosoma;

public class Truncamiento implements SelectionStrategy  {

	@Override
	public void seleccion(ArrayList<Cromosoma> pob, int numPob) {
		ArrayList<Cromosoma> seleccionados=new ArrayList<Cromosoma>();
		pob.sort(new OrdenarCromosoma());// ordeno por puntuacio
		int porcentaje10= numPob/10; // cuanto es el 10
		for(int i=0;i<porcentaje10;i++){
			for(int j=0;j<porcentaje10;j++){
				seleccionados.add(pob.get(i).generateCopy());
			}
		}
		int i=0;
		while(seleccionados.size()<numPob){
			// si no es exacto el 10% entonces voy añadiendo de uno en uno los mejores
			// hasta que sea la misma cantidad que la inicial
			seleccionados.add(pob.get(i));
			i++;
		}
		pob.clear();
		for(Cromosoma cr:seleccionados) {
			pob.add(cr);
		}
	}

     public String toString(){
        return "Truncamiento 10%";
    }

}

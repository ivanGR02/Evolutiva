package population.strategy;

import java.util.ArrayList;

import prac1.cromosoma.Cromosoma;
import prac1.cromosoma.OrdenarCromosoma;

public interface SelectionStrategy {
	
	public default void seleccionarPob(ArrayList<Cromosoma> pob,
			double porcentajeElite,ArrayList<Cromosoma> elite) {
		int seleccionados=(int) (pob.size()*porcentajeElite);
		ArrayList<Cromosoma> ordenados =new ArrayList<Cromosoma>(pob);
		ordenados.sort(new OrdenarCromosoma());
		elite.clear();
		for(int i=0;i<seleccionados;i++){
			 elite.add(ordenados.get(i).generateCopy());
		}
		seleccion(pob,pob.size()-seleccionados);
	}
	
	void seleccion(ArrayList<Cromosoma> pob, int numPob);
}

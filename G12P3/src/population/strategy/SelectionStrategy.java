package population.strategy;

import java.util.ArrayList;

import AlgoritmoGenerico.AlgoritmoGenerico;
import population.cromosoma.Cromosoma;
import prac1.cromosoma.OrdenarCromosoma;

public interface SelectionStrategy {
	
	public default void seleccionarPob(AlgoritmoGenerico algoritmo) {
		int seleccionados=(int) (algoritmo.getPob().size()*algoritmo.getPorcentajeElitismo());
		ArrayList<Cromosoma> ordenados =new ArrayList<Cromosoma>(algoritmo.getPob());
		ordenados.sort(new OrdenarCromosoma());
		algoritmo.getElite().clear();
		for(int i=0;i<seleccionados;i++){
			algoritmo.getElite().add(ordenados.get(i).generateCopy());
		}
		seleccion(algoritmo);
	}
	
	void seleccion(AlgoritmoGenerico algoritmo);
	
	public SelectionStrategy generateCopy();
}

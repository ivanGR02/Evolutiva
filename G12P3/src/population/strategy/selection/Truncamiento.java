package population.strategy.selection;

import java.util.ArrayList;
import java.util.Comparator;

import AlgoritmoGenerico.AlgoritmoGenerico;
import population.cromosoma.Cromosoma;
import population.strategy.SelectionStrategy;
import prac1.cromosoma.OrdenarCromosoma;

public class Truncamiento implements SelectionStrategy  {

	@Override
	public void seleccion(AlgoritmoGenerico algoritmo) {
		ArrayList<Cromosoma> seleccionados=new ArrayList<Cromosoma>();
		int numSeleccionados=algoritmo.getPob().size()-algoritmo.getElite().size();
		algoritmo.getPob().sort(new OrdenarCromosoma());// ordeno por puntuacio
		int porcentaje10= algoritmo.getPob().size()/10; // cuanto es el 10
		for(int i=0;i<porcentaje10;i++){
			for(int j=0;j<porcentaje10;j++){
				seleccionados.add(algoritmo.getPob().get(i).generateCopy());
			}
		}
		int i=0;
		while(seleccionados.size()<numSeleccionados){
			// si no es exacto el 10% entonces voy añadiendo de uno en uno los mejores
			// hasta que sea la misma cantidad que la inicial
			seleccionados.add(algoritmo.getPob().get(i));
			i++;
		}
		algoritmo.setPob(seleccionados);
	}

     public String toString(){
        return "Truncamiento 10%";
    }

	@Override
	public SelectionStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new Truncamiento();
	}

}

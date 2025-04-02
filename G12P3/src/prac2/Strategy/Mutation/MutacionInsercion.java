package prac2.Strategy.Mutation;

import java.util.ArrayList;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.MutationStrategy;
import prac2.Population.PermutationCromosoma;

public class MutacionInsercion implements MutationStrategy {

	@Override
	public void mutacion(ArrayList<Cromosoma> pob, Double prob_mutation) {
		for(int i=0;i<pob.size();i++) {
			if(Random_Utilities.getInstance().nextDouble()<=prob_mutation) {
				int punto1=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				int punto2=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				// mutar cromosoma
				mutar(pob.get(i),punto1,punto2);
			}
		}
	}
	
	//Mutacion por Insercion de un solo punto
	public void mutar(Cromosoma cromosoma, int punto1, int punto2) {
		//elimino el gen a insertar del cromosoma
		Gen genInsert=cromosoma.getCromosoma().get(punto1).generateCopy();
		((PermutationCromosoma) cromosoma).eliminarElemento(punto1);
		//si el genEliminado esta en una posicion mayor que el a insertar, 
		//desplazo elementos a la derecha de derecha a izquierda hasta llegar
		//a la posicion a insertar y lo inserto
		if(punto1>punto2) {
			for(int i=punto1;i>punto2;i--) {
				Gen aux=cromosoma.getCromosoma().get(i-1).generateCopy();
				((PermutationCromosoma) cromosoma).eliminarElemento(i-1);
				((PermutationCromosoma) cromosoma).anyadirElemento(i, aux);
			}
			((PermutationCromosoma) cromosoma).anyadirElemento(punto2, genInsert);
		}//en caso contrario hago el desplazamiento contrario
		else {
			for(int i=punto1;i<punto2;i++) {
				Gen aux=cromosoma.getCromosoma().get(i+1).generateCopy();
				((PermutationCromosoma) cromosoma).eliminarElemento(i+1);
				((PermutationCromosoma) cromosoma).anyadirElemento(i, aux);
			}
			((PermutationCromosoma) cromosoma).anyadirElemento(punto2, genInsert);
		}
	}
	
	public String toString(){
		return "Mutacion insercion";
	}

	@Override
	public MutationStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new MutacionInsercion();
	}

}

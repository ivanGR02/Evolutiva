package population.strategy.cruce;

import java.util.ArrayList;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.strategy.CruceStrategy;
import population.cromosoma.Tree;
import population.cromosoma.CromosomaTree;

public class CruceSubarbol implements CruceStrategy{

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

	private void cruce(Cromosoma cromosoma, Cromosoma cromosoma2) {
		Tree aux = ((CromosomaTree) cromosoma).cruzar();
		Tree aux1 = ((CromosomaTree) cromosoma2).cruzar();
		Tree copia = aux.copic();
		aux.copiarFake(aux1); // cambia el valor al de aux1 y hace que sus hijos sean los del aux1
		aux1.copiarFake(copia);
		if(cromosoma.getLongitud()>3) {
			aux.eliminarNulo();
		}
		if(cromosoma2.getLongitud()>3) {
			aux1.eliminarNulo();
		}
	}

	@Override
	public CruceStrategy generateCopy() {
		return new CruceSubarbol();
	}
    public String toString(){
        return "Cruce subarbol";
    }

}

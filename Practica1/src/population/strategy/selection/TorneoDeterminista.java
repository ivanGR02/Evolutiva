package population.strategy.selection;
import java.util.ArrayList;

import population.Random_Utilities;
import population.strategy.SelectionStrategy;
import prac1.cromosoma.Cromosoma;

public class TorneoDeterminista implements SelectionStrategy  {

	@Override
	public void seleccion(ArrayList<Cromosoma> pob, int numPob) {
		ArrayList<Cromosoma> seleccionados=new ArrayList<Cromosoma>();
		for(int i=0;i<numPob;i++){
            int x1=Random_Utilities.getInstance().nextInt(pob.size());
            int x2=Random_Utilities.getInstance().nextInt(pob.size());
            int x3=Random_Utilities.getInstance().nextInt(pob.size());;
			seleccionados.add(Torneo(pob,x1,x2,x3).generateCopy());
		}
		pob.clear();
		for(Cromosoma cr:seleccionados) {
			pob.add(cr);
		}
	}
     private Cromosoma Torneo(ArrayList<Cromosoma> pob, int x1, int x2, int x3) {
		// el mejor es x1
		if(pob.get(x1).getFitness()>pob.get(x2).getFitness() && pob.get(x1).getFitness()>pob.get(x3).getFitness()){
            return pob.get(x1);
        }// el mejor es x2
        else if(pob.get(x2).getFitness()>pob.get(x1).getFitness() && pob.get(x2).getFitness()>pob.get(x2).getFitness()){
            return pob.get(x2);
        }
        else{
            return pob.get(x3);
        }
	}
	public String toString(){
        return "Torneo Determinista";
    }

}

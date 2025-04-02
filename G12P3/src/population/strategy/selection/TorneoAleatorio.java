package population.strategy.selection;
import java.util.ArrayList;

import AlgoritmoGenerico.AlgoritmoGenerico;
import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.strategy.SelectionStrategy;

public class TorneoAleatorio implements SelectionStrategy  {
	@Override
	public void seleccion(AlgoritmoGenerico algoritmo) {
		ArrayList<Cromosoma> seleccionados=new ArrayList<Cromosoma>();
		int numSeleccionados=algoritmo.getPob().size()-algoritmo.getElite().size();
		for(int i=0;i<numSeleccionados;i++){
            int x1=Random_Utilities.getInstance().nextInt(algoritmo.getPob().size());
            int x2=Random_Utilities.getInstance().nextInt(algoritmo.getPob().size());
            int x3=Random_Utilities.getInstance().nextInt(algoritmo.getPob().size());;
			seleccionados.add(Torneo(algoritmo.getPob(),x1,x2,x3).generateCopy());
		}
		algoritmo.setPob(seleccionados);
		
	}
     private Cromosoma Torneo(ArrayList<Cromosoma> pob, int x1, int x2, int x3) {

        if(Random_Utilities.getInstance().nextBoolean()){
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
        else{
               // el peor es x1
            if(pob.get(x1).getFitness()<pob.get(x2).getFitness() && pob.get(x1).getFitness()<pob.get(x3).getFitness()){
                return pob.get(x1);
            }// el peor es x2
            else if(pob.get(x2).getFitness()<pob.get(x1).getFitness() && pob.get(x2).getFitness()<pob.get(x2).getFitness()){
                return pob.get(x2);
            }
            else{
                return pob.get(x3);
            }

        }
      
	}
	public String toString(){
        return "Torneo Aleatorio";
    }
	@Override
	public SelectionStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new TorneoAleatorio();
	}

}

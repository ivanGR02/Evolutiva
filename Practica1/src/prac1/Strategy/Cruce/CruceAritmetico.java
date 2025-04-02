package prac1.Strategy.Cruce;

import java.util.ArrayList;

import population.Random_Utilities;
import population.strategy.CruceStrategy;
import prac1.cromosoma.Cromosoma;

public class CruceAritmetico implements CruceStrategy {

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

	public void cruce(Cromosoma padre1, Cromosoma padre2) {   
        for(int i=0;i<padre1.getCromosoma().size();i++){
            double p =Random_Utilities.getInstance().nextDouble();
            Double aux1 =p*padre1.getCromosoma().get(i).getFenotipo()+(1-p)*padre2.getCromosoma().get(i).getFenotipo();
			Double aux2 =p*padre2.getCromosoma().get(i).getFenotipo()+(1-p)*padre1.getCromosoma().get(i).getFenotipo();
            padre1.getCromosoma().get(i).setGenotipo(aux1);
            padre2.getCromosoma().get(i).setGenotipo(aux2);
        }
    }

	 public String toString(){
        return "Cruce Aritmetico";
    }
	
}

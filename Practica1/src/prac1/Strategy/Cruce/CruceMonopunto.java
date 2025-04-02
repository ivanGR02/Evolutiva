package prac1.Strategy.Cruce;

import java.util.ArrayList;

import population.Random_Utilities;
import population.strategy.CruceStrategy;
import prac1.cromosoma.Cromosoma;

public class CruceMonopunto implements CruceStrategy {

	@Override
	public void reproduccion(ArrayList<Cromosoma> pob, Double prob_cruse) {
		ArrayList<Cromosoma> parejas = new ArrayList<>();
		for(int i=0;i<pob.size();i++) {
			if(Random_Utilities.getInstance().nextDouble()<prob_cruse) {
				parejas.add(pob.get(i));
			}
		}
		if(parejas.size()%2==1) {
			parejas.remove(parejas.size()-1);
		}
		for(int i=0;i<parejas.size();i+=2) {
			cruce(parejas.get(i),parejas.get(i+1),Random_Utilities.getInstance().nextInt(parejas.get(0).getLongitud()));
		}
	}

	public void cruce(Cromosoma padre1, Cromosoma padre2, int punto_cruse) {

		int total=0; // cuantos bits has cambiado en total
        int punto_current=0;// posición del gen actual
        int i=0;// gen en el que estoy
        while(total<punto_cruse) {
			if(punto_current>=padre1.getCromosoma().get(i).getTam()){// si la posicion del gen actual es mayor pasamos al siguiente gen
				i++;// pasas all siguiente gen
				punto_current=0;// inicio del siguiente gen
			}
			Object aux=padre1.getCromosoma().get(i).getValue(punto_current);
			padre1.getCromosoma().get(i).setValue(punto_current,padre2.getCromosoma().get(i).getValue(punto_current));
			padre2.getCromosoma().get(i).setValue(punto_current,aux);
        	punto_current++;
			total++;
        }
	}
	public String toString(){
		return "Cruce Monopunto";
	}

}

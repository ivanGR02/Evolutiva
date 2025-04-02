package prac2.Strategy.Cruce;

import java.util.ArrayList;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.strategy.CruceStrategy;

public class CruceAleatorio implements CruceStrategy {
    ArrayList<CruceStrategy> cruzadores;
    
    public CruceAleatorio() {
		cruzadores=new ArrayList<CruceStrategy>(){{add(new CruceCO());
	    add(new CruceCX()); add(new CruceERX()); add(new CruceOX()); add(new CruceOXPP()); add(new CruceOXPri()); 
	    add(new CrucePMX()); add(new CrucePropio());}};
	}

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
            int numero =Random_Utilities.getInstance().nextInt(0,8);
            switch(numero){
                case 0: 
                ((CruceCO) cruzadores.get(numero)).cruce(parejas.get(i),parejas.get(i+1));
                break;
                case 1: 
                ((CruceCX) cruzadores.get(numero)).cruce(parejas.get(i),parejas.get(i+1));
                break;
                case 2: 
                ((CruceERX) cruzadores.get(numero)).cruce(parejas.get(i),parejas.get(i+1));
                break;
                case 3: 
                ((CruceOX) cruzadores.get(numero)).cruce(parejas.get(i),parejas.get(i+1));
                break;
                case 4: 
                ((CruceOXPP) cruzadores.get(numero)).cruce(parejas.get(i),parejas.get(i+1));
                break;
                case 5: 
                ((CruceOXPri) cruzadores.get(numero)).cruce(parejas.get(i),parejas.get(i+1));
                break;
                case 6: 
                ((CrucePMX) cruzadores.get(numero)).cruce(parejas.get(i),parejas.get(i+1));
                break;
                case 7: 
                ((CrucePropio) cruzadores.get(numero)).cruce(parejas.get(i),parejas.get(i+1));
                break;
            }
			
		}
	}
    public String toString(){
        return "Cruce Aleatorio";
    }
	@Override
	public CruceStrategy generateCopy() {
		return new CruceAleatorio();
	}
}

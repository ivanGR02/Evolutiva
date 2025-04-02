package prac1.Strategy.Cruce;

import java.util.ArrayList;
import java.util.Random;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.CruceStrategy;

public class CruceBlxAlpha implements CruceStrategy {

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
		double cmin,cmax,intervalo,alpha,value,intervaloMin,intervaloMax;

		for(int i=0;i<cromosoma.getCromosoma().size();i++) {
			cmin=Math.min((double)cromosoma.getCromosoma().get(i).getFenotipo(), (double)cromosoma2.getCromosoma().get(i).getFenotipo());
			cmax=Math.max((double)cromosoma.getCromosoma().get(i).getFenotipo(), (double)cromosoma2.getCromosoma().get(i).getFenotipo());
			intervalo=cmax-cmin;
			alpha=Random_Utilities.getInstance().nextDouble();
			intervaloMin=cmin-intervalo*alpha;
			intervaloMax=cmax+intervalo*alpha;
			
			intervaloMin=(intervaloMin<cromosoma.getCromosoma().get(i).getMin()?cromosoma.getCromosoma().get(i).getMin():intervaloMin);
			intervaloMax=(intervaloMin<cromosoma.getCromosoma().get(i).getMax()?cromosoma.getCromosoma().get(i).getMax():intervaloMax);
			cromosoma.getCromosoma().get(i).setGenotipo(
					Random_Utilities.getInstance().nextDouble(intervaloMin, intervaloMax+Double.MIN_VALUE));
			cromosoma2.getCromosoma().get(i).setGenotipo(
					Random_Utilities.getInstance().nextDouble(intervaloMin, intervaloMax+Double.MIN_VALUE));
		}
	}
	 public String toString(){
        return "Cruce BLX-alpha";
    }

	@Override
	public CruceStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new CruceBlxAlpha();
	}

}

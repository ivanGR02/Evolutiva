package population.strategy.selection;

import java.util.ArrayList;

import population.Random_Utilities;
import population.strategy.SelectionStrategy;
import prac1.cromosoma.Cromosoma;

public class EstocasticoUniversal implements SelectionStrategy {

	@Override
	public void seleccion(ArrayList<Cromosoma> pob,int numPob) {
	    double p=Random_Utilities.getInstance().nextDouble();;
		ArrayList<Cromosoma> seleccionados=new ArrayList<Cromosoma>();
		for(int i=0;i<numPob;i++){
            double valor=(p+i)/numPob;
			seleccionados.add(pob.get(busquedaBinaria(pob,valor,0,pob.size()-1)).generateCopy());
		}
		pob.clear();
		for(Cromosoma cr:seleccionados) {
			pob.add(cr);
		}
	}
	private int busquedaBinaria(ArrayList<Cromosoma> pob,Double p, int init,int fin) {
		if(init==fin) {
			return init;
		}else {
			int m=(init+fin)/2;
			if(p<=pob.get(m).getPuntuacionAcum()){
				return busquedaBinaria(pob,p,init,m);
			}else {
				return busquedaBinaria(pob,p,m+1,fin);
			}
		}
	}
	public String toString(){
        return "Estocastico Universal";
    }

}

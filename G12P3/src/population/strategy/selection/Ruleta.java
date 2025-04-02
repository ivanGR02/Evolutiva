package population.strategy.selection;

import java.util.ArrayList;

import AlgoritmoGenerico.AlgoritmoGenerico;
import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.strategy.SelectionStrategy;

public class Ruleta implements SelectionStrategy{

	@Override
	public void seleccion(AlgoritmoGenerico algoritmo) {
		double p;
		ArrayList<Cromosoma> seleccionados=new ArrayList<Cromosoma>();
		int numSeleccionados=algoritmo.getPob().size()-algoritmo.getElite().size();
		for(int i=0;i<numSeleccionados;i++){
			p=Random_Utilities.getInstance().nextDouble();
			seleccionados.add(algoritmo.getPob().get(busquedaBinaria(algoritmo.getPob(),p,0,algoritmo.getPob().size()-1)).generateCopy());
		}
		algoritmo.setPob(seleccionados);
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
		return "Ruleta";
	}

	@Override
	public SelectionStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new Ruleta();
	}
}

package population.strategy.selection;
import java.util.ArrayList;

import AlgoritmoGenerico.AlgoritmoGenerico;
import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.strategy.SelectionStrategy;

public class Restos implements SelectionStrategy  {

	@Override
	public void seleccion(AlgoritmoGenerico algoritmo) {
		ArrayList<Cromosoma> seleccionados=new ArrayList<Cromosoma>();
		int numSeleccionados=algoritmo.getPob().size()-algoritmo.getElite().size();
		for(int i=0;i<algoritmo.getPob().size() && seleccionados.size()<numSeleccionados;i++){
            double valor=algoritmo.getPob().get(i).getPuntuacion()*algoritmo.getPob().size();
			int integerPart = (int) valor;
			for(int j=0;j<integerPart; j++){
				seleccionados.add(algoritmo.getPob().get(i).generateCopy());
			}
		}
		int faltan =numSeleccionados-seleccionados.size();// uso estocastico universal
		double p=Random_Utilities.getInstance().nextDouble();;
		for(int i =0;i<faltan;i++) {
            double valor=(p+i)/faltan;
			seleccionados.add(algoritmo.getPob().get(busquedaBinaria(algoritmo.getPob(),valor,0,algoritmo.getPob().size()-1)).generateCopy());
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
        return "Restos";
    }
	@Override
	public SelectionStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new Restos();
	}

}

package population.strategy.selection;
import java.util.ArrayList;

import population.Random_Utilities;
import population.strategy.SelectionStrategy;
import prac1.cromosoma.Cromosoma;

public class Restos implements SelectionStrategy  {

	@Override
	public void seleccion(ArrayList<Cromosoma> pob,int numPob) {
		ArrayList<Cromosoma> seleccionados=new ArrayList<Cromosoma>();
		for(int i=0;i<pob.size();i++){
            double valor=pob.get(i).getPuntuacion()*pob.size();
			int integerPart = (int) valor;
			for(int j=0;j<integerPart; j++){
				seleccionados.add(pob.get(i).generateCopy());
			}
		}
		int faltan =numPob-seleccionados.size();// uso estocastico universal
		double p=Random_Utilities.getInstance().nextDouble();;
		for(int i =0;i<faltan;i++) {
            double valor=(p+i)/faltan;
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
        return "Restos";
    }

}

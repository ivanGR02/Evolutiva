package population.strategy.selection;

import java.util.ArrayList;

import AlgoritmoGenerico.AlgoritmoGenerico;
import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.OrdenarCromosoma;
import population.strategy.SelectionStrategy;

public class Ranking implements SelectionStrategy {

	@Override
	public void seleccion(AlgoritmoGenerico algoritmo) {
		ArrayList<Cromosoma> seleccionados=new ArrayList<Cromosoma>();
		int numSeleccionados=algoritmo.getPob().size()-algoritmo.getElite().size();
		algoritmo.getPob().sort(new OrdenarCromosoma());
        ArrayList<Double> rankings =new ArrayList<Double>();
        double acum=0.0;
        for(int i=0;i<algoritmo.getNum_indiv();i++){//2*(1.5-1)=1 
            double div =(double)(i/(algoritmo.getPob().size()-1));
            double p=(double)1/algoritmo.getPob().size();
            p*=(1.5-div);
            rankings.add(p+acum);
            acum+=p;
        }
        for(int i=0;i<numSeleccionados;i++){
			double p=Random_Utilities.getInstance().nextDouble();
			int pos=busquedaBinaria(rankings,p,0,algoritmo.getPob().size()-1);
			seleccionados.add(algoritmo.getPob().get(pos).generateCopy());
		}
        algoritmo.setPob(seleccionados);
	}

    private int busquedaBinaria(ArrayList<Double> rankings, double p, int init, int fin) {
		if(init==fin) {
			return init;
		}else {
			int m=(init+fin)/2;
			if(p<=rankings.get(m)){
				return busquedaBinaria(rankings,p,init,m);
			}else {
				return busquedaBinaria(rankings,p,m+1,fin);
			}
		}
	}

	public String toString(){
        return "Ranking con beta 1,5";
    }

	@Override
	public SelectionStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new Ranking();
	}
}

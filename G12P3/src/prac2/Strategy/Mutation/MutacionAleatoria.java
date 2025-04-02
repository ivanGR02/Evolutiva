package prac2.Strategy.Mutation;

import java.util.ArrayList;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.MutationStrategy;
import prac2.Population.PermutationCromosoma;

public class MutacionAleatoria implements MutationStrategy {
	ArrayList<MutationStrategy> mutadores=new ArrayList<MutationStrategy>(){{add(new MutacionHeuristica());
    add(new MutacionInsercion()); add(new MutacionIntercambio()); add(new MutacionInversion()); add(new MutacionPropio());}};

	@Override
	public void mutacion(ArrayList<Cromosoma> pob, Double prob_mutation) {
		ArrayList<Integer> puntos=new ArrayList<Integer>();
		for(int i=0;i<pob.size();i++) {
			if(Random_Utilities.getInstance().nextDouble()<=prob_mutation) {
				int mutar=Random_Utilities.getInstance().nextInt(0, 5);
                int punto1,punto2,punto3;
                Gen aux,aux2;
                switch(mutar){
                    case 0://Heuristica
                        punto1=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
			            punto2=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
                        while(punto2==punto1) {
                            punto2=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
                        }
                        punto3=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
                        while(punto3==punto1 || punto3==punto2) {
                            punto3=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
                         }
                            // mutar cromosoma
                        puntos.clear();
                        puntos.add(punto1);
                        puntos.add(punto2);
                        puntos.add(punto3);
                        ((MutacionHeuristica) mutadores.get(0)).mutar(pob.get(i),puntos);
                    break;
                    case 1://Insercion
                        punto1=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				        punto2=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				        // mutar cromosoma
				        ((MutacionInsercion) mutadores.get(1)).mutar(pob.get(i),punto1,punto2);
                    break;
                    case 2://Intercambio
                        punto1=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				        punto2=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
                        aux =pob.get(i).getCromosoma().get(punto1).generateCopy();
                        aux2 =pob.get(i).getCromosoma().get(punto2).generateCopy();
                        // eliminas genes
                        ((PermutationCromosoma) pob.get(i)).eliminarElemento(punto1);
                        ((PermutationCromosoma) pob.get(i)).eliminarElemento(punto2);
                        // los cambias de lugar
                        ((PermutationCromosoma) pob.get(i)).anyadirElemento(punto1, aux2);
                        ((PermutationCromosoma) pob.get(i)).anyadirElemento(punto2, aux);
                    break;
                    case 3: //Inversion
                        punto1=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
                        punto2=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
                        aux=pob.get(i).getCromosoma().get(punto1).generateCopy();
                        ((MutacionInversion) mutadores.get(3)).mutar(pob.get(i),punto1,punto2);
                    break;
                    case 4: //Propio
                        ArrayList<Integer> lista=new ArrayList<Integer>();
                        punto1=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size()/3);
                        while(punto1==0) {
                            punto1=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size()/3);
                        }
                        for(int j=0;j<punto1;j++) {
                            lista.add(Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size()/3));
                        }
                    
                        ((MutacionPropio) mutadores.get(4)).mutar(pob.get(i),lista);
                        }
                    break;
			}
		}
	}
    public String toString(){
        return "Mutacion Aleatoria";
    }
	@Override
	public MutationStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new MutacionAleatoria();
	}
}

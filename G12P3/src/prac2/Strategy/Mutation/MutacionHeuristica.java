package prac2.Strategy.Mutation;

import java.util.ArrayList;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.MutationStrategy;
import prac2.Funciones.FuncionTSP;
import prac2.Population.PermutationCromosoma;
import prac2.Population.PermutationGen;

public class MutacionHeuristica implements MutationStrategy {
	
	private FuncionTSP funcion;
	
	private final int numPermutation = 6;
	
	private final int numGenMutation = 3;
	
	private final static int[][] permutation = {
			{0,1,2},{0,2,1},{1,0,2},
			{1,2,0},{2,0,1},{2,1,0}
	};
	
	private Gen genes[] = {new PermutationGen(),new PermutationGen(),new PermutationGen()};
	
	public MutacionHeuristica() {
		funcion = new FuncionTSP();
	}

	@Override
	public void mutacion(ArrayList<Cromosoma> pob, Double prob_mutation) {
		ArrayList<Integer> puntos=new ArrayList<Integer>();
		for(int i=0;i<pob.size();i++) {
			if(Random_Utilities.getInstance().nextDouble()<=prob_mutation) {
				int punto1=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				int punto2=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				while(punto2==punto1) {
					punto2=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				}
				int punto3=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				while(punto3==punto1 || punto3==punto2) {
					punto3=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size());
				}
				// mutar cromosoma
				puntos.clear();
				puntos.add(punto1);
				puntos.add(punto2);
				puntos.add(punto3);
				mutar(pob.get(i),puntos);
			}
		}
	}
	//obtengo los correspondientes genes segun el numero del 1 al 3;
	private Gen getGen(int num) {
		return genes[num];
	}
	//Mutacion por Heuristica de tres genes
	public void mutar(Cromosoma cromosoma, ArrayList<Integer> puntos) {
		
		Cromosoma prueba =cromosoma.generateCopy();
		for(int i=0;i<puntos.size();i++) {
			genes[i]=prueba.getCromosoma().get(puntos.get(i));
		}
		//obtengo los copias de los genes correspondiente y los elimino del cromosoma
		//valoro los permutaciones posibles en los puntos
		for(int i=0;i<permutation.length;i++) {
			for(int k=0;k<permutation[i].length;k++) {
				((PermutationCromosoma)prueba).eliminarElemento(puntos.get(k));
			}
			for(int j=0;j<permutation[i].length;j++) {
				((PermutationCromosoma)prueba).anyadirElemento(puntos.get(permutation[i][j]), getGen(j));
			}
			funcion.calcular(prueba);
			if(prueba.getFitness()>cromosoma.getFitness()) {
				for(int k=0;k<numGenMutation;k++) {
					((PermutationCromosoma)cromosoma).eliminarElemento(puntos.get(k));
				}
				for(int j=0;j<numGenMutation;j++) {
					((PermutationCromosoma)cromosoma).anyadirElemento(puntos.get(j), getGen(permutation[i][j]));
				}
				cromosoma.setFitness(prueba.getFitness());
				cromosoma.setValor(prueba.getValor());
			}
		}
	}
	
	public String toString(){
		return "Mutacion heuristica";
	}

	@Override
	public MutationStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new MutacionHeuristica();
	}
}

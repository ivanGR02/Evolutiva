package prac2.Strategy.Mutation;

import java.util.ArrayList;
import java.util.Comparator;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.strategy.MutationStrategy;
import prac2.Funciones.FuncionTSP;
import prac2.Population.PermutationCromosoma;
import prac2.Population.PermutationGen;

public class MutacionPropio implements MutationStrategy{

	private FuncionTSP funcion;
	

	public MutacionPropio() {
		funcion = new FuncionTSP();
	}
	
	@Override
	public void mutacion(ArrayList<Cromosoma> pob, Double prob_mutation) {
		ArrayList<Integer> lista=new ArrayList<Integer>();
		for(int i=0;i<pob.size();i++) {
			if(Random_Utilities.getInstance().nextDouble()<=prob_mutation) {
				lista.clear();
				int punto1=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size()/3);
				while(punto1==0) {
					punto1=Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size()/3);
				}
				for(int j=0;j<punto1;j++) {
					lista.add(Random_Utilities.getInstance().nextInt(0, pob.get(i).getCromosoma().size()/3));
				}
				// mutar cromosoma
				mutar(pob.get(i),lista);
			}
		}
	}

	public void mutar(Cromosoma cromosoma, ArrayList<Integer> lista) {
		ArrayList<Cromosoma> listaPrueba=new ArrayList<Cromosoma>();
		for(int i=0;i<lista.size();i++) {
			listaPrueba.add(cromosoma.generateCopy());
			quitarGenes(listaPrueba.get(i),lista.get(i));
			rellenarGenes(listaPrueba.get(i));
		}
		for(int i=0;i<listaPrueba.size();i++) {
			funcion.calcular(listaPrueba.get(i));
		}
		listaPrueba.sort(new Comparator<Cromosoma>() {

			@Override
			public int compare(Cromosoma o1, Cromosoma o2) {
				if(o1.getFitness()>o2.getFitness()) {
					return -1;
				}else if(o1.getFitness()<o2.getFitness()){
					return 1;
				}else {
					return 0;
				}
			}
		});
		cromosoma.setCromosoma(listaPrueba.get(0).getCromosoma());
	}
	
	private void quitarGenes(Cromosoma cromosoma, int num) {
		int quitado=0;
		while(quitado<num) {
			int punto=Random_Utilities.getInstance().nextInt(0, cromosoma.getCromosoma().size());
			if(((PermutationCromosoma)cromosoma).hayNumEnPos(punto)) {
				quitado++;
				((PermutationCromosoma)cromosoma).eliminarElemento(punto);
			}
		}
	}
	
	private void rellenarGenes(Cromosoma cromosoma) {
		int num=0;
		PermutationGen aux=new PermutationGen();
		for(int i=0;i<cromosoma.getCromosoma().size();i++) {
			if(!((PermutationCromosoma)cromosoma).hayNumEnPos(i)) {
				num=Random_Utilities.getInstance().nextInt(0, cromosoma.getCromosoma().size());
				aux.setGenotipo(num);
				while(!(((PermutationCromosoma) cromosoma)).anyadirElemento(i, aux.generateCopy())){
					num=Random_Utilities.getInstance().nextInt(0, cromosoma.getCromosoma().size());
					aux.setGenotipo(num);
				}
			}
		}
	}
	
	public String toString(){
		return "Mutacion Propio";
	}

	@Override
	public MutationStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new MutacionPropio();
	}

}

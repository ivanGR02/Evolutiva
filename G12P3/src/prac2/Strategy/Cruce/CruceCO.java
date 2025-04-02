package prac2.Strategy.Cruce;

import java.util.ArrayList;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.strategy.CruceStrategy;
import prac2.Population.PermutationCromosoma;
import prac2.Population.PermutationGen;

public class CruceCO  implements CruceStrategy  {

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

	protected void cruce(Cromosoma cromosoma, Cromosoma cromosoma2) {
		int punto1=Random_Utilities.getInstance().nextInt(0, cromosoma.getCromosoma().size());
		intercambiarSegmento(cromosoma,cromosoma2,punto1);
	}

	private void intercambiarSegmento(Cromosoma cromosoma, Cromosoma cromosoma2, int punto1) {
		ArrayList<Boolean> lista=new ArrayList<Boolean>();
		ArrayList<Integer> listaI1=new ArrayList<Integer>();
		ArrayList<Integer> listaI2=new ArrayList<Integer>();
		PermutationCromosoma I1 =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
		PermutationCromosoma I2 =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
		PermutationGen genAux=new PermutationGen();
		//inicio la lista
		for(int i=0;i<cromosoma.getCromosoma().size();i++) {
			lista.add(true);
		}
		//proceso la listaI1
		for(int i=0;i<cromosoma.getCromosoma().size();i++) {
			int num=1,count=0;
			while(count<(int)cromosoma.getCromosoma().get(i).getFenotipo()) {
				if(lista.get(count)){
					num++;
				}
				count++;
			}
			lista.set((int)cromosoma.getCromosoma().get(i).getFenotipo(), false);
			listaI1.add(num);
		}
		//proceso la listaI2
		for(int i=0;i<cromosoma2.getCromosoma().size();i++) {
			int num=1,count=0;
			while(count<(int)cromosoma2.getCromosoma().get(i).getFenotipo()) {
				if(!lista.get(count)){
					num++;
				}
				count++;
			}
			lista.set((int)cromosoma2.getCromosoma().get(i).getFenotipo(), true);
			listaI2.add(num);
		}
		//cruso la lista segun el monopunto
		ArrayList<Integer> listaI1result=new ArrayList<Integer>();
		ArrayList<Integer> listaI2result=new ArrayList<Integer>();
		for(int i=0;i<cromosoma.getCromosoma().size();i++) {
			if(i<punto1) {
				listaI1result.add(listaI2.get(i));
				listaI2result.add(listaI1.get(i));
			}else {
				listaI1result.add(listaI1.get(i));
				listaI2result.add(listaI2.get(i));
			}
		}
		//empiezo a cruzarlos para el individuo1
		for(int i=0;i<cromosoma.getCromosoma().size();i++) {
			int num=listaI1result.get(i);
			int count=0;
			int current=-1;
			while(count<num) {
				current++;
				if(lista.get(current)) {
					count++;
				}
			}
			lista.set(current, false);
			genAux=new PermutationGen();
			genAux.setGenotipo(current);
			I1.anyadirElemento(i,genAux);
		}
		//empiezo a cruzarlos para el individuo2
		for(int i=0;i<cromosoma2.getCromosoma().size();i++) {
			int num=listaI2result.get(i);
			int count=0;
			int current=-1;
			while(count<num) {
				current++;
				if(!lista.get(current)) {
					count++;
				}
			}
			lista.set(current, true);
			genAux=new PermutationGen();
			genAux.setGenotipo(current);
			I2.anyadirElemento(i,genAux);
		}
		//hago setters de cromosoma para que el cruce haga efecto
		cromosoma.setCromosoma(I1.getCromosoma());
		cromosoma2.setCromosoma(I2.getCromosoma());
		((PermutationCromosoma)cromosoma).setHayGen(((PermutationCromosoma)I1).getHayGen());
		((PermutationCromosoma)cromosoma2).setHayGen(((PermutationCromosoma)I2).getHayGen());
	}
	
	public String toString(){
		return "Cruce Codificacion Ordinal";
	}

	@Override
	public CruceStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new CruceCO();
	}

}

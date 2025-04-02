package prac2.Population;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;

public class PermutationCromosoma extends Cromosoma{
	
	//comprueba si hay un cierto valor en el individuo
	private HashMap<Integer,Integer> hayGen;
	String[] Nombres= {"Albacete","Alicante","Almeria","Avila","Badajoz","Barcelona","Bilbao","Burgos","Cáceres","Cádiz","Castellón","Ciudad Real","Córdoba","A Coruña","Cuenca","Gerona","Granada","Guadalajara","Huelva","Huesca","Jaén","León","Lérida","Logroño","Lugo","Madrid","Málaga","Murcia"};
	public PermutationCromosoma() {
		super();
		this.hayGen=new HashMap<Integer,Integer>();
	};

	public PermutationCromosoma(int numGen,Boolean init) {
		super();
		this.hayGen=new HashMap<Integer,Integer>();
		onCreate(numGen,init);
	}
	
	public void onCreate(int numGen,Boolean init) {
		if(init) {
			int numAleatorio;
			PermutationGen permutationGen;
			while(cromosoma.size()<numGen) {
				numAleatorio=Random_Utilities.getInstance().nextInt(0,numGen);
				numAleatorio=Math.abs(numAleatorio);
				if(hayNumero(numAleatorio)==-1) {
					hayGen.put(numAleatorio, cromosoma.size());
					permutationGen=new PermutationGen();
					permutationGen.setGenotipo(numAleatorio);
					cromosoma.add(permutationGen);
				}
			}
		}else {
			while(cromosoma.size()<numGen) {
				cromosoma.add(new PermutationGen());
			}
		}
	}
	
	@Override
	public Cromosoma generateCopy() {
		PermutationCromosoma copy = new PermutationCromosoma();
		Gen num;
		for(int i=0;i<cromosoma.size();i++) {
			num=cromosoma.get(i).generateCopy();
			copy.getHayGen().put((int)num.getFenotipo(), i);
			copy.getCromosoma().add(num);
		}
		copy.setFitness(getFitness());
		copy.setFitnessAjus(getFitnessAjus());
		copy.setValor(getValor());
		return copy;
	}

	@Override
	public String toStringSol() {
		int count=0;
		String soluc="Solucion : " + getValor() + " , con valores : \n{";
		for(int i=0;i<cromosoma.size();i++) {
			if(soluc.length()/130>count) {
				soluc+="\n";
				count++;
			}
			soluc+= Nombres[(int) cromosoma.get(i).getFenotipo()] + " --> ";
		}
		soluc+=Nombres[(int) cromosoma.get(0).getFenotipo()];
		soluc+="}.";
		return soluc;
	}
	public String toStringSol2() {
		int count=0;
		String soluc="Solucion : " + getValor() + " , con valores : \n{";
		for(int i=0;i<cromosoma.size();i++) {
			if(soluc.length()/130>count) {
				soluc+="\n";
				count++;
			}
			soluc+= (int) cromosoma.get(i).getFenotipo() + " --> ";
		}
		soluc+=(int) cromosoma.get(0).getFenotipo();
		soluc+="}.";
		return soluc;
	}
	
	public HashMap<Integer, Integer> getHayGen() {
		return hayGen;
	}

	public void setHayGen(HashMap<Integer, Integer> hayGen) {
		this.hayGen = hayGen;
	}
	
	//no se utiliza en este cromosoma
	@Override
	public void onCreate(ArrayList<Double> max, ArrayList<Double> min, Double precision, int numGen) {
		// TODO Auto-generated method stub
		
	}
	
	//comprueba si hay dicho numero en el individuo, si lo hay devuelve su pos
	//en el cromosoma y en caso de que no lo hay devuelve un -1
	public int hayNumero(int num) {
		if(hayGen.containsKey(num)) {
			return hayGen.get(num);
		}else {
			return -1;
		}
	}
	
	public boolean hayNumEnPos(int pos) {
		return ((int)cromosoma.get(pos).getFenotipo()>-1)?true:false;
	}
	
	public boolean anyadirElemento(int posicion,Gen valor){
		if(hayGen.containsKey((int)valor.getFenotipo())) {
			return false;
		}else {
			hayGen.put((int)valor.getFenotipo(), posicion);
			getCromosoma().set(posicion, valor);
			return true;
		}
	}
	
	public void eliminarElemento(Gen valor) {
		if(hayGen.containsKey((int)valor.getFenotipo())) {
			int pos=hayNumero((int)valor.getFenotipo());
			if(pos!=-1) {
				hayGen.remove((int)valor.getFenotipo());
				cromosoma.set(pos, new PermutationGen());
			}
		}
	}
	
	public void eliminarElemento(int pos) {
		if(pos<cromosoma.size() && (int)cromosoma.get(pos).getFenotipo()!=-1) {
			hayGen.remove((int)cromosoma.get(pos).getFenotipo());
			cromosoma.set(pos, new PermutationGen());
		}
	}
	
	public String toString(){
		return "Permutation Cromosoma";
	}

}

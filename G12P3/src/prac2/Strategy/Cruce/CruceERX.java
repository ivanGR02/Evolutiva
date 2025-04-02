package prac2.Strategy.Cruce;

import java.util.ArrayList;
import java.util.HashSet;

import AlgoritmoGenerico.AlgoritmoGenerico;
import AlgoritmoGenerico.Pair;
import population.Random_Utilities;
import population.cromosoma.Cromosoma;
import population.cromosoma.Gen;
import population.strategy.CruceStrategy;
import prac2.Population.PermutationCromosoma;

public class CruceERX  implements CruceStrategy {

	@Override
	public void reproduccion(ArrayList<Cromosoma> pob,Double prob_cruse) {
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
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Gen>> tabla = new ArrayList<ArrayList<Gen>>(); 

		for(int i=0;i<cromosoma.getCromosoma().size();i++){
			ArrayList<Gen> conectados = new ArrayList<Gen>();
			HashSet<Gen> esta =new HashSet<Gen>();

			//Los conectados al gen i del padre 1 (i es el valor del gen no la posicion)
			int pos= ((PermutationCromosoma) cromosoma).hayNumero(i);
			Gen aux = pegado(cromosoma,pos-1);
			esta.add(aux);
			conectados.add(aux);
			aux =pegado(cromosoma,pos+1);
			if(!esta.contains(aux)){
				esta.add(aux);
				conectados.add(aux);
			}
			//Los conectados al gen i del padre 2 (i es el valor del gen no la posicion)
			pos= ((PermutationCromosoma) cromosoma2).hayNumero(i);
			aux = pegado(cromosoma2,pos-1);
			if(!esta.contains(aux)){
				esta.add(aux);
				conectados.add(aux);
			}
			aux =pegado(cromosoma2,pos+1);
			if(!esta.contains(aux)){
				esta.add(aux);
				conectados.add(aux);
			}
			// Lo añado a la tabla
			tabla.add(conectados);
		}
		// hacemos la primera ruta
		PermutationCromosoma auxi =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
		PermutationCromosoma auxi2 =new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
		// empiezo el recorrido con el primer valor del padre 1
		auxi.anyadirElemento(0, cromosoma.getCromosoma().get(0).generateCopy());
		// el valor que hay en ese gen
		int val=(int)cromosoma.getCromosoma().get(0).getFenotipo();
		Pair<Integer,Boolean> aux=new Pair<Integer,Boolean>(val,true);
		int i=1;
		while(i<cromosoma.getCromosoma().size()) {
			aux=getConexiones(tabla,aux.getFirst(),auxi,i);
			if(!aux.getSecond()) {
				i=0;
				aux=new Pair<Integer,Boolean>(val,true);
				auxi=new PermutationCromosoma(cromosoma.getCromosoma().size(),false);
				auxi.anyadirElemento(0, cromosoma.getCromosoma().get(0).generateCopy());
			}
			i++;
		}
		
		auxi2.anyadirElemento(0, cromosoma2.getCromosoma().get(0).generateCopy());
		// el valor que hay en ese gen
		val=(int)cromosoma2.getCromosoma().get(0).getFenotipo();
		aux=new Pair<Integer,Boolean>(val,true);
		i=1;
		while(i<cromosoma.getCromosoma().size()) {
			aux=getConexiones(tabla,aux.getFirst(),auxi2,i);
			if(!aux.getSecond()) {
				i=0;
				aux=new Pair<Integer,Boolean>(val,true);
				auxi2=new PermutationCromosoma(cromosoma2.getCromosoma().size(),false);
				auxi2.anyadirElemento(0, cromosoma2.getCromosoma().get(0).generateCopy());
			}
			i++;
		}
		cromosoma.setCromosoma(auxi.getCromosoma());
		cromosoma2.setCromosoma(auxi2.getCromosoma());
		((PermutationCromosoma)cromosoma).setHayGen(((PermutationCromosoma)auxi).getHayGen());
		((PermutationCromosoma)cromosoma2).setHayGen(((PermutationCromosoma)auxi2).getHayGen());
	}
	private Pair<Integer,Boolean> getConexiones(ArrayList<ArrayList<Gen>> tabla, int val,PermutationCromosoma hijo, int pos) {
		boolean retBool=false;
		Gen minimo = null;
		int minConexiones=5; // quien se conecta a menos genes
		for(int i=0;i<tabla.get(val).size();i++) {
			int conexiones=(int) tabla.get(val).get(i).getFenotipo();
			if(hijo.hayNumero(conexiones)==-1 && !retBool) {
				minimo = tabla.get(val).get(0).generateCopy(); // quien es el conectado a menos genes
				retBool=true;
			}
		}
		
		// bucle para saber que elemento es el que tiene menos conexiones 
		for(int i=0;i<tabla.get(val).size();i++){
			int conexiones=(int) tabla.get(val).get(i).getFenotipo();
			if(tabla.get(conexiones).size()<minConexiones &&
					hijo.hayNumero(conexiones)==-1){
				minConexiones=tabla.get(conexiones).size();
				minimo=tabla.get(val).get(i).generateCopy();
			}// en caso de empate se soluciona por sorteo con el randomUtilities
			else if(tabla.get(conexiones).size()==minConexiones && Random_Utilities.getInstance().nextBoolean()
					&& hijo.hayNumero(conexiones)==-1){
				minConexiones=tabla.get(conexiones).size();
				minimo=tabla.get(val).get(i).generateCopy();
			}
		}
		int retInteger=-1;
		if(retBool) {
			retInteger=(int) minimo.getFenotipo();
			hijo.anyadirElemento(pos, minimo);
		}
		Pair<Integer,Boolean> ret= new Pair<Integer,Boolean>(retInteger,retBool);
		return ret;
	}

	private Gen pegado(Cromosoma cromosoma, int pos){
		if(pos==-1) return cromosoma.getCromosoma().get(cromosoma.getCromosoma().size()-1).generateCopy();
		if (pos==cromosoma.getCromosoma().size()) return cromosoma.getCromosoma().get(0).generateCopy();
		return cromosoma.getCromosoma().get(pos).generateCopy();
	} 
	

	public String toString(){
		return "Cruce por recombinacion de rutas";
	}

	@Override
	public CruceStrategy generateCopy() {
		// TODO Auto-generated method stub
		return new CruceERX();
	}
}

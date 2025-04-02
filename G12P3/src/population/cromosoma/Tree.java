package population.cromosoma;

import java.util.ArrayList;

import Funciones.FuncionP3;
import population.Random_Utilities;

public class Tree {
    private String valor;
    private Tree der,izq;
    private int max_prof;
    private int profundidad;
    private boolean esTerminal;
    
    public Tree() {
	}

    public Tree(int profundidad, int max_prof) {
		this.profundidad=profundidad;
		this.max_prof=max_prof;
	}
    
	public Tree(String v) {
		this.valor=v;
	}
	public void copiarFake(Tree a){
		this.valor=a.valor;
		this.der=a.der;
		this.izq=a.izq;
		this.esTerminal=a.esTerminal;
	}
	// Devuelve el arbol en forma de string
    public String toArray(){
        ArrayList<String> array = new ArrayList<String>();
        toArrayAux(array);
        String result="";
        int i=0;
        for(String current:array) {
        	result+=current;
        	if(result.length()/100!=i) {
        		i++;
        		result+="\n";
        	}
        }
        return result;
    }
    // Insertar un valor en el arbol (nodo simple)
    public void insert(String v, Boolean index){
        Tree a = new Tree(v);
        if(index){
            der=a;
        }
        else {
        	izq=a;
        }
    }

    // Insertar un arbol en otro arbol.
    public void insert(Tree a, Boolean index){
    	if(index){
            der=a;
        }
        else {
        	izq=a;
        }
    }

    public void copic(Tree copiado) {
    	setValor(copiado.getValor());
		setEsTerminal(copiado.esTerminal);
		setMax_prof(copiado.getMax_prof());
		setProfundidad(copiado.getProfundidad());
    	if(!copiado.esTerminal) {
    		Tree izquierda=new Tree(copiado.getProfundidad()+1,copiado.getMax_prof()),
        			derecha=new Tree(copiado.getProfundidad()+1,copiado.getMax_prof());
    		izquierda.copic(copiado.izq);
    		derecha.copic(copiado.der);
    		this.izq=izquierda;
    		this.der=derecha;
    	}
    }
    public Tree copic() {
    	Tree copiar=new Tree();
    	copiar.setValor(getValor());
    	copiar.setEsTerminal(esTerminal);
    	copiar.setMax_prof(getMax_prof());
    	copiar.setProfundidad(getProfundidad());
    	if(!this.esTerminal) {
    		Tree izquierda=izq.copic(),
        			derecha=der.copic();
    		copiar.setIzq(izquierda);
    		copiar.setDer(derecha);
    	}
    	return copiar;
    }
    
    private void toArrayAux(ArrayList<String> array){
        if(!esTerminal){
            array.add("(");
            this.izq.toArrayAux(array);
            array.add(" "+this.valor+" ");
            this.der.toArrayAux(array);
            array.add(")");
        }else{
            array.add(this.valor);
        }
    }
    public void inicializacionCompleta(){
        if(profundidad<this.max_prof) {
        	initOperator(true);
        }else {
        	initLeaf();
        }
    }

	public void mutacionTerminal(){
		ArrayList<Tree> posiblesMutados = new ArrayList<>();// cojo todos los nodos con funcion
		this.recorrerArbol(posiblesMutados,2);// cojo todos los nodos con funcion
        int mut=Random_Utilities.getInstance().nextInt(posiblesMutados.size()); // cual muto
	    int index=Random_Utilities.getInstance().nextInt(CromosomaTree.terminales.length); // que funcion cojo
	    posiblesMutados.get(mut).setValor(CromosomaTree.terminales[index]); //cambio la funcion del arbol escogido  
	}
	
	private void recorrerArbol(ArrayList<Tree> ret,int casos){
		switch(casos){
			case 0: //incluye todos los nodos, terminal y no-terminal
			if(this.isEsTerminal()){
				ret.add(this);
			}else{
				if(!izq.esTerminal)izq.recorrerArbol(ret,casos);
				else ret.add(izq);
				ret.add(this);
				if(!der.esTerminal) der.recorrerArbol(ret,casos);
				else ret.add(der);
			}
			break;
			case 1: //incluye solo los no-terminales
				if(!izq.esTerminal) izq.recorrerArbol(ret,casos);
				ret.add(this);
				if(!der.esTerminal) der.recorrerArbol(ret,casos);
			break;
			case 2: // incluye solo los terminales
				if(this.esTerminal) ret.add(this);
				else{
					izq.recorrerArbol(ret,casos);
					der.recorrerArbol(ret,casos);
				}
			break;
		}
	}

	public int tamanyoArbol() {
		ArrayList<Tree> tamanio=new ArrayList<>();
		recorrerArbol(tamanio, 0);
		return tamanio.size();
	}
	
	public void mutacionFuncional(){
		ArrayList<Tree> posiblesMutados = new ArrayList<>();// cojo todos los nodos con funcion
		this.recorrerArbol(posiblesMutados,1);
        int mut=Random_Utilities.getInstance().nextInt(posiblesMutados.size()); // cual muto
	    int index=Random_Utilities.getInstance().nextInt(CromosomaTree.funciones.length); // que funcion cojo 
	    posiblesMutados.get(mut).setValor(CromosomaTree.funciones[index]); //cambio la funcion del arbol escogido  
	}

	public void mutacionSubarbol(){
		ArrayList<Tree> posiblesMutados = new ArrayList<>();// cojo todos los nodos con funcion
		this.recorrerArbol(posiblesMutados,0);// cojo todos los nodos con funcion
        int mut=Random_Utilities.getInstance().nextInt(posiblesMutados.size()); // cual muto	
		boolean tipo=Random_Utilities.getInstance().nextBoolean();
		if(tipo){
			 posiblesMutados.get(mut).inicializacionCreciente();
		}
		else{
			posiblesMutados.get(mut).inicializacionCompleta();
		}
		       
	}

	public void mutacionPermutacion() {
		ArrayList<Tree> posiblesMutados = new ArrayList<>();
		recorrerArbol(posiblesMutados,1);// cojo todos los nodos con funcion
		int mut=Random_Utilities.getInstance().nextInt(posiblesMutados.size()); // cual muto
		posiblesMutados.get(mut).permutar();  //permuto el arbol escogido     
	}

	private void permutar() {
		Tree aux= der;
		der=izq;
		izq=aux;
	}
	 
	public void mutacionhoist(){
		ArrayList<Tree> posiblesMutados = new ArrayList<>();
		recorrerArbol(posiblesMutados,1);// cojo todos los nodos con funcion
		int mut=Random_Utilities.getInstance().nextInt(posiblesMutados.size()); // cual muto
		this.copic(posiblesMutados.get(mut));
		this.actualizaProfundidad(1);
	} 

	private void actualizaProfundidad(int newProfundidad){
		this.setProfundidad(newProfundidad);
		if(!this.isEsTerminal()){
			this.getDer().actualizaProfundidad(newProfundidad+1);
			this.getIzq().actualizaProfundidad(newProfundidad+1);
		}
		 // lo copias y actualizas luego las profundidades.
	}

	public void mutacionExpansion() {
		ArrayList<Tree> posiblesMutados = new ArrayList<>();
		recorrerArbol(posiblesMutados,2);// cojo todos los terminales
		int mut=Random_Utilities.getInstance().nextInt(posiblesMutados.size()); // cual muto
		int extension=Random_Utilities.getInstance().nextInt(1,3); // cual muto
		boolean tipo=Random_Utilities.getInstance().nextBoolean();
		posiblesMutados.get(mut).setMax_prof(posiblesMutados.get(mut).getMax_prof()+extension);
		if(tipo){
			 posiblesMutados.get(mut).inicializacionCreciente();
		}
		else{
			posiblesMutados.get(mut).inicializacionCompleta();
		}
	}

	public void mutacionContraccion() {
		ArrayList<Tree> posiblesMutados = new ArrayList<>();
		recorrerArbol(posiblesMutados,1);// cojo todos los nodos con funcion
		if(posiblesMutados.size()>1){
			int mut=Random_Utilities.getInstance().nextInt(posiblesMutados.size()); // cual muto
			while(posiblesMutados.get(mut).profundidad==1){// evitar que la raiz mute
				mut=Random_Utilities.getInstance().nextInt(posiblesMutados.size()); // cual muto
			}
			posiblesMutados.get(mut).contraer();
		}
	}
	private void contraer(){
		this.setDer(null);
		this.setIzq(null);
		this.setEsTerminal(true);
		int index=Random_Utilities.getInstance().nextInt(CromosomaTree.terminales.length); // que funcion cojo
	    this.setValor(CromosomaTree.terminales[index]); //cambio la funcion del arbol escogido  
	}
    
	public void inicializacionCreciente(){
		inicializacionCrecienteAux(true);
    }
    public void inicializacionCrecienteAux(Boolean fin){
        if(this.profundidad<this.max_prof) {
            if(profundidad==1) {
            	initOperator(false);
            }else {
            	//hoja
            	if(fin) {
            		initOperator(false);
            	}else {
            		if(Random_Utilities.getInstance().nextBoolean() && !fin) {
                		initLeaf();
                	}else {//operator
                		initOperator(false);
                	}
            	}
            }
        }else {
            initLeaf();
		}
    }

	public Tree cruzar(){ // devuelve el arbol que se va a intercambiar
		ArrayList<Tree> posiblesMutados = new ArrayList<>();// cojo todos los nodos con funcion
		this.recorrerArbol(posiblesMutados,0);// cojo todos los nodos 
		int cruz=Random_Utilities.getInstance().nextInt(posiblesMutados.size()); // cual va a intermcabiarse
		while(posiblesMutados.get(cruz).getProfundidad()==1) {
			cruz=Random_Utilities.getInstance().nextInt(posiblesMutados.size()); // cual va a intermcabiarse
		}
		return posiblesMutados.get(cruz); 
	}



	private void initOperator(Boolean isCompleto) {
    	int index=Random_Utilities.getInstance().nextInt(CromosomaTree.funciones.length);
    	valor=CromosomaTree.funciones[index];
    	this.der=new Tree(this.profundidad+1,this.max_prof);
    	this.izq=new Tree(this.profundidad+1,this.max_prof);
    	if(isCompleto) {
    		this.der.inicializacionCompleta();
        	this.izq.inicializacionCompleta();
    	}else {
        	valor=CromosomaTree.funciones[index];
        	this.der=new Tree(this.profundidad+1,this.max_prof);
        	this.izq=new Tree(this.profundidad+1,this.max_prof);
        	if(Random_Utilities.getInstance().nextBoolean()) {
        		this.der.inicializacionCrecienteAux(true);
        		this.izq.inicializacionCrecienteAux(false);
        	}
        	else {
        		this.der.inicializacionCrecienteAux(false);
        		this.izq.inicializacionCrecienteAux(true);
        	}
    	}
    	this.esTerminal=false;
    }

    
    private void initLeaf() {
    	int index=Random_Utilities.getInstance().nextInt(CromosomaTree.terminales.length);
    	valor=CromosomaTree.terminales[index];
    	this.esTerminal=true;
  
    }

    
    public double Calcular(Double x) {
    	double result=0;
    	if(this.esTerminal) {
    		if(this.valor.equals("X")) {
    			return x;
    		}else {
    			return Double.parseDouble(this.valor);
    		}
    	}else {
    		double resultDer=der.Calcular(x),resultIzq=izq.Calcular(x);
    		switch (this.valor) {
			case "ADD":
				result=resultIzq+resultDer;
				break;
			case "SUB":
				result=resultIzq-resultDer;
				break;
			case "MUL":
				result=resultIzq*resultDer;
				break;
			}
    	}
		return result;
    }
    
	public void eliminarNulo() {
		if(!isEsTerminal()) {
			izq.eliminarNulo();
			der.eliminarNulo();
		}
		if(this.valor.equals("MUL") && this.izq.valor.equals("1")){
			setValor(this.der.valor);
			setEsTerminal(this.der.esTerminal);
			setIzq(der.izq);
			setDer(der.der);
		}else if(this.valor.equals("MUL") && this.der.valor.equals("1")) {
			setValor(this.izq.valor);
			setEsTerminal(this.izq.esTerminal);
			setDer(izq.der);
			setIzq(izq.izq);
		}else if(this.valor.equals("SUB") && this.der.valor.equals("0")){
			setValor(this.izq.valor);
			setEsTerminal(this.izq.esTerminal);
			setDer(izq.der);
			setIzq(izq.izq);
		}else if(this.valor.equals("ADD") && this.der.valor.equals("0")){
			setValor(this.izq.valor);
			setEsTerminal(this.izq.esTerminal);
			setDer(izq.der);
			setIzq(izq.izq);
		}else if(this.valor.equals("ADD") && this.izq.valor.equals("0")){
			setValor(this.der.valor);
			setEsTerminal(this.der.esTerminal);
			setIzq(der.izq);
			setDer(der.der);
		}else {
			int next=FuncionP3.database.length/3;
			double cambio=0;
			int i=0;
			while(i<FuncionP3.database.length) {
				cambio+=Calcular(FuncionP3.database[i]);
				i+=next;
			}
			if(cambio==0) {
				setValor("0");
				setEsTerminal(true);
				setDer(null);
				setIzq(null);
				setMax_prof(getProfundidad());
			}
		}
	}
    
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public int getMax_prof() {
		return max_prof;
	}
	public void setMax_prof(int max_prof) {
		this.max_prof = max_prof;
	}
	public int getProfundidad() {
		return profundidad;
	}
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public Tree getDer() {
		return der;
	}

	public void setDer(Tree der) {
		this.der = der;
	}

	public Tree getIzq() {
		return izq;
	}

	public void setIzq(Tree izq) {
		this.izq = izq;
	}

	public boolean isEsTerminal() {
		return esTerminal;
	}

	public void setEsTerminal(boolean esTerminal) {
		this.esTerminal = esTerminal;
	}
	public String toString(){
		return this.toArray();
	}




	
}
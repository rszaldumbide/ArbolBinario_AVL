/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AVL_Arbol_Binario;

/**
 *
 * @author ricar
 */
public class avl {
    
    private nodo raiz;

    public nodo getRaiz() {
        return raiz;
    }
    
    public avl(){
        this.raiz=null;
    }
    
    public nodo rotacionSimpleDerecha(nodo raiz){
        
        nodo tmp=raiz.izq;
        raiz.izq=tmp.der;
        tmp.der=raiz;
        return tmp;
    }
    
    public nodo rotacionSimpleIzquierda(nodo raiz){
        
        nodo tmp=raiz.der;
        raiz.der=tmp.izq;
        tmp.izq=raiz;
        return tmp;
    }
    
    public nodo rotacionDobleDerechaIzquierda(nodo raiz){
        
        raiz.der=rotacionSimpleDerecha(raiz.der);
        return rotacionSimpleIzquierda(raiz);
    }
    
    public nodo rotacionDobleIzquierdaDerecha(nodo raiz){
        
        raiz.izq=rotacionSimpleIzquierda(raiz.izq);
        return rotacionSimpleDerecha(raiz);
    }
    
    int valorAltura;
    public int altura(nodo raiz){
        valorAltura=0;
        altura(raiz, 1);
        return valorAltura;
    }
    private void altura(nodo aux, int nivel){
        if(aux!=null){
            altura(aux.izq, nivel+1);
            if (nivel>valorAltura) {
                valorAltura=nivel;
            }
            altura(aux.der, nivel+1);
        }
    }
    
    public void insertar(int dato){
        
        nodo nuevo=new nodo(dato);
        
        if(this.raiz==null){
            this.raiz=nuevo;
        }else{
            this.raiz=insertar(this.raiz, nuevo);
        }
    }
    
    private nodo insertar(nodo raiz, nodo nuevo){
        
        nodo nuevaRaiz=raiz;
        
        if(nuevo.info<raiz.info){//se va a la izq
            if(raiz.izq==null){
                raiz.izq=nuevo;
            }else{
                raiz.izq=insertar(raiz.izq, nuevo);
                //a partir de aqui se equilibria
                //calculando las alturas de cada rama, izq, der
                int altDer=altura(raiz.der);
                int altIzq=altura(raiz.izq);
                int fe=altDer-altIzq;
                
                if(fe==-2){
                    if(nuevo.info<raiz.izq.info){//si se cumple es simple
                        nuevaRaiz=rotacionSimpleDerecha(raiz);
                    }else{
                        nuevaRaiz=rotacionDobleIzquierdaDerecha(raiz);
                    }
                }
            }
        }else if(nuevo.info>raiz.info){//se va a la derecha
            if(raiz.der==null){
                raiz.der=nuevo;
            }else{
                raiz.der=insertar(raiz.der, nuevo);
                
                int altDer=altura(raiz.der);
                int altIzq=altura(raiz.izq);
                int fe=altDer-altIzq;
                
                if(fe==2){
                    if(nuevo.info>raiz.der.info){
                        nuevaRaiz=rotacionSimpleIzquierda(raiz);
                    }else{
                        nuevaRaiz=rotacionDobleDerechaIzquierda(raiz);
                    }
                }
            }
        }
        return nuevaRaiz;
    }
    //recorridos  
    //recorrer en preorden (raiz, izq, der)
    String datospre="";

    public String preOrden(){
        return preOrden(this.raiz);
    }
    private String preOrden(nodo raiz){
        //raiz, izq, der = recursivos
        if(raiz != null){
            datospre+= " => " + raiz.info;
            preOrden(raiz.izq);
            preOrden(raiz.der);
        }
        return datospre;
    }
    
    //recorrer en inorden (izq, raiz, der)
    String datosin = ""; 

    public String InOrden() {
        return InOrden(this.raiz);
    }
    private String InOrden(nodo raiz){
        
        if (raiz != null) {
            InOrden(raiz.izq);
            datosin += " => " + raiz.info;
            InOrden(raiz.der);
        }
        return datosin;
    }
    //recorrer en posorden (izq, der, raiz)
    String datospos = "";

    public String PosOrden() {
        return PosOrden(this.raiz);
    }
    private String PosOrden(nodo raiz){
        if(raiz != null){
            PosOrden(raiz.izq);
            PosOrden(raiz.der);
            datospos += " => " + raiz.info;
        }
        return datospos;
    }
    //metodos varios
    String hojas="";
    int cantHojas;
    
    public String nodosHojas(){
        return nodosHojas(this.raiz);
    }
    private String nodosHojas(nodo raiz){
        if(raiz!=null){
            if(raiz.der==null && raiz.izq==null){
                hojas+=raiz.info+", ";
                cantHojas++;
            }
            nodosHojas(raiz.izq);
            nodosHojas(raiz.der);
        }
        return "Existe un total de "+cantHojas+" hojas"
                +" de los cuales se tiene: ("+hojas+")";
    }

    String datosPrimos="";

    //metodo que verifica si es primo
    public boolean numPrimo(int numero){
        boolean validar=false;
        int cont=0;
        for(int i=1; i<numero+1; i++){
            if(numero%i==0){
                cont++;
            }
        }
        if(cont==2){
            validar=true;
        }else{
            validar=false;
        }
        return validar;
    }

    public String nodosPrimos(){
        return nodosPrimos(this.raiz);
    }
    public String nodosPrimos(nodo raiz){
        if(raiz!=null){
            if(numPrimo(raiz.info)==true){
                datosPrimos+=raiz.info+", ";
                }
            nodosPrimos(raiz.izq);
            nodosPrimos(raiz.der);
        }
        return datosPrimos;
    }

    int contnodos=-1;
    public String Nodos(){
        return Nodos(this.raiz);
    }
    private String Nodos(nodo raiz){
        if(raiz!=null){
            contnodos++;
            Nodos(raiz.izq);
            Nodos(raiz.der);
        }
        return "La cantidad de nodos en el arbol es de: "+contnodos+" Sin contar la raiz.";
    }

    public nodo Buscar2(int datoBuscar){
        nodo raizAux=this.raiz;

        while(raizAux!=null){
            if(datoBuscar==raizAux.info){
                return raizAux;                 //rompe ciclo
            }else{
                if(datoBuscar<raizAux.info){
                    raizAux=raizAux.izq;
                }else{
                    raizAux=raizAux.der;
                }
            }
        }
        return null;
    }  
    //Ejercicio 2
    //Camino mas cercano
    
    public String caminoCorto(int datoBuscar){
        nodo buscar=Buscar2(datoBuscar);
        String camino="";
        if(buscar==null){
            camino+="No existe el nodo dentro del arbol.";
        }else{
            nodo raizAux=this.raiz;
        
            while(raizAux!=null){
                camino+="=> "+raizAux.info;
                if(datoBuscar==raizAux.info){
                    break;//rompe ciclo
                }else{
                    if(datoBuscar<raizAux.info){
                        raizAux=raizAux.izq;
                    }else{
                        raizAux=raizAux.der;
                    }
                }
            }
        }  
        return "El recorrido al nodo: "+datoBuscar+": es\n"
                + camino;
    }
    
    public nodo eliminarNodo(nodo raiz, int num){
        nodo padre= raiz.Padre;
        if(num<raiz.info){
            eliminarNodo(raiz.izq, num);
        }else if(num>raiz.info){
            eliminarNodo(raiz.der, num);     
        }else{
            //los 3 casos que existe
            //CASO 1: SI ES HOJA
            if(raiz.izq==null && raiz.der==null){
                if(num<padre.info){
                    padre.izq=null;
                }else if(num>padre.info){
                    padre.der=null;
                }
            }
            //CASO2: TIENE UN HIJO IZQUIERDO O DERECHO
            if(raiz.izq!=null && raiz.der==null){
                padre.izq=raiz.izq;
                raiz.izq.Padre=padre;
            }else{
                if(raiz.izq==null && raiz.der!=null){
                    padre.der=raiz.der;
                    raiz.der.Padre=padre;
                }
            }
            //CASO3: TIENE 2 HIJOS
            if(raiz.izq!=null && raiz.der!=null){
                nodo menorRama= null;
                        //this.mostrarMenorRama(raiz.der);
                nodo padreX= raiz.Padre;
                nodo aux=raiz.der;
                padreX.der=menorRama;
                menorRama.der=aux;
                menorRama.izq=raiz.izq;
                menorRama.Padre=padreX;
                aux.izq=null;
                }
            }
        return raiz;
    }
    
    //Ejercicio 1
    //peso de las ramas
    private int cont=0, cont2=0; //No se cuenta la raiz
    
    private int pesoRaizIzq(nodo raiz){
        if(raiz!=null){
            pesoRaizIzq(raiz.izq);
            pesoRaizDer(raiz.der);
            cont++;
        }
        return cont;
    }
    public int pesoRaizIzq(){
        return pesoRaizIzq(this.raiz.izq);
    }
    
    private int pesoRaizDer(nodo raiz){
        if(raiz!=null){
            pesoRaizDer(raiz.izq);
            pesoRaizDer(raiz.der);
            cont2++;
        }
        return cont2;
    }
    public int pesoRaizDer(){
        return pesoRaizDer(this.raiz.der);
    }
    
    String pesoRama="";   
    public String ramaPeso(){
        int pesoIzq=pesoRaizIzq();
        int pesoDer=pesoRaizDer();
        
        if (pesoIzq > pesoDer) {
            return "La rama izquierda tiene mayor peso, tiene " + String.valueOf(pesoIzq) + " nodos.";
        } else if (pesoDer > pesoIzq) {
            return "La rama derecha tiene mayor peso, tiene " + String.valueOf(pesoDer) + " nodos.";
        } else {
            return "El peso de las dos ramas es el mismo, tienen " + String.valueOf(pesoDer) + " nodos.";
        }
    }
        //metdo naty
    private int nodosIZQ = 0;
    private int nodosDER = 0;

    private int pesoNodosIZQ(nodo raiz) {
        //cuenta el número de nodos a la izquierda de la raíz
        if (raiz != null) {
            pesoNodosIZQ(raiz.izq);
            pesoNodosIZQ(raiz.der);
            nodosIZQ++;
        }
        return nodosIZQ;
    }

    private int pesoNodosIZQ() {
        return pesoNodosIZQ(this.raiz.izq);
    }

    private int pesoNodosDER(nodo raiz) {
        //cuenta el número de nodos a la derecha de la raíz

        if (raiz != null) {
            pesoNodosDER(raiz.izq);
            pesoNodosDER(raiz.der);
            nodosDER++;
        }
        return nodosDER;
    }

    private int pesoNodosDER() {
        return pesoNodosDER(this.raiz.der);
    }

    public String pesoNodos() {
        int izq = pesoNodosIZQ();
        int der = pesoNodosDER();

        if (izq > der) {
            return "La rama izquierda tiene mayor peso, tiene " + String.valueOf(izq) + " nodos.";
        } else if (der > izq) {
            return "La rama derecha tiene mayor peso, tiene " + String.valueOf(der) + " nodos.";
        } else {
            return "El peso de las dos ramas es el mismo, tienen " + String.valueOf(der) + " nodos.";
        }
    }
}
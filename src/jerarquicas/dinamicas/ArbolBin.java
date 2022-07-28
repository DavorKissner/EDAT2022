/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas.dinamicas;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

/**
 *
 * @author davor.kissner
 */
public class ArbolBin {
    
    //atributos
    private NodoArbol raiz;
    
    //constructor
    public ArbolBin(){
        this.raiz = null;
    }
    public boolean insertar(int elemN, int elemPadre, char lugar) {
        boolean exito = true;
        if (this.raiz == null) {
            // si el arbol esta vacio , ponemos el elemento en la raiz
            this.raiz = new NodoArbol(elemN, null, null);
        } else {
            // si no esta vacio ,se busca al padre
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {
                if (lugar == 'I' && nodoPadre.getIzquierdo() == null) {
                    // si el padre existe y no tiene hijo izq se lo agrega
                    nodoPadre.setIzquierdo(new NodoArbol(elemN, null, null));
                } else {
                    if (lugar == 'D' && nodoPadre.getDerecho() == null) {
                        //si el padre existe y no tiene hijo der se lo agrega
                        nodoPadre.setDerecho(new NodoArbol(elemN, null, null));
                    } else {
                        // si el padre no existe o tiene ese hijo da error
                        exito = false;

                    }
                }

            } else {
                exito = false;
            }
        }
        return exito;
    }
    private NodoArbol obtenerNodo(NodoArbol n, Object buscado){
        NodoArbol resultado = null;
        if (n != null){
            if (n.getElem().equals(buscado)){
                resultado = n;
            }
            else{
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
            }
            if (resultado == null){
                resultado = obtenerNodo(n.getDerecho(), buscado);
            }
        }
        return resultado;
    }
    public boolean esVacio(){
        return this.raiz == null;
    }
    public String toString(){
        String str = llamadoString(this.raiz);
        return str;
         
    }
    private String llamadoString(NodoArbol n) {
        String str = "";
        if (n != null) {
            str += "NODO: " + n.getElem().toString() + ".";
            if (n.getIzquierdo() != null) {
                str += " H.I: " + n.getIzquierdo().getElem().toString() + ".";
            }
            else{
                str += " H.I: -.";
            }
            if (n.getDerecho() != null) {
                str += " H.D: " + n.getDerecho().getElem().toString() + ".\n";
            } else {
                str += " H.D: - \n";
            }
            if (n.getIzquierdo() != null) {
                str += llamadoString(n.getIzquierdo());
            }
            if (n.getDerecho() != null) {
                str += llamadoString(n.getDerecho());
            }
        } else {
            str = "Arbol Vacio";
        }
        return str;
    }
    public void vaciar(){
        this.raiz = null;
    }
    public Object padre(Object elemento){
        Object retorno = null;
        
        if(!this.esVacio()){
            if(!this.raiz.getElem().equals(elemento)){
                retorno = padreRecursivo(this.raiz, elemento);
            }
        }     
        return retorno;
    }
    private Object padreRecursivo(NodoArbol raiz, Object elemento){
        //el valor de retorno que esta seteado en null
        Object retorno = null;
        boolean control = true;
        
        if(raiz.getIzquierdo() != null){
            //mientras que el hijo izquierdo no sea null
            if(raiz.getIzquierdo().getElem().equals(elemento)){
                retorno = raiz.getElem();
                control = false;
            }else{
                retorno = padreRecursivo(raiz.getIzquierdo(), elemento);
                if(retorno != null){
                    control = false;
                }
            }
        }
        
        if(control && raiz.getDerecho() != null){
            //mientras que el hijo derecho no sea null, y el control sea verdadero
            if(raiz.getDerecho().getElem().equals(elemento)){
                retorno = raiz.getElem();
            }else{
                retorno = padreRecursivo(raiz.getDerecho(), elemento);
            }
        }       
        return retorno;
    }
    public int altura() {
        int res;
        if (this.raiz == null) {
            res = -1;
        } else {
            res = alturaAux(this.raiz);
        }
        return res;
    }

    private int alturaAux(NodoArbol n) {
        int alt1, alt2, res;
        if (n.getIzquierdo() != null) {
            alt1 = alturaAux(n.getIzquierdo()) + 1;
        } else {
            alt1 = 0;
        }
        if (n.getDerecho() != null) {
            alt2 = alturaAux(n.getDerecho()) + 1;
        } else {
            alt2 = 0;
        }
        if (alt1 > alt2) {
            res = alt1;
        } else {
            res = alt2;
        }
        return res;
    }
    public int nivel(int elem) {
        int res = 0;
        if (this.raiz == null) {
            res = -1;
            
        } else {
            res = nivelAux(this.raiz, elem);
        }
        return res;
    }

    private int nivelAux(NodoArbol n, int elem) {
        int res = -1;
        int aux = -1;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                res = 0;
            } else {
                aux = nivelAux(n.getIzquierdo(), elem) + 1;
                if (aux == 0) {
                    aux = nivelAux(n.getDerecho(), elem) + 1;
                }
                if(aux == 0){
                    res = -1;
                }else{
                    res = aux;
                }
                   
            }
        }
        return res;
    }
    public ArbolBin clone() {
        ArbolBin clon = new ArbolBin();
        clon.raiz = clonarAux(this.raiz);
        return clon;
    }

    private NodoArbol clonarAux(NodoArbol n) {
        NodoArbol nClon = null;
        if (n != null) {
            nClon = new NodoArbol(n.getElem(), null, null);
            nClon.setIzquierdo(clonarAux(n.getIzquierdo()));
            nClon.setDerecho(clonarAux(n.getDerecho()));
        }
        return nClon;
    }

    public Lista listarPreorden() {
        Lista preorden = new Lista();
        if (this.raiz != null) {
            auxpreorden(this.raiz, preorden);
        }
        return preorden;
    }
    private void auxpreorden(NodoArbol raiz, Lista preorden){
        if(raiz != null){
            preorden.insertar(raiz.getElem(), preorden.longitud() + 1);
            
            auxpreorden(raiz.getIzquierdo(), preorden);
            auxpreorden(raiz.getDerecho(), preorden);
        }
    }
    public Lista listarInorden(){
        Lista inorden = new Lista();
        if(this.raiz != null){
            auxInorden(this.raiz, inorden);   
        }
        return inorden;
    }
    private void auxInorden(NodoArbol raiz, Lista inorden){
        if(raiz != null){
            auxInorden(raiz.getIzquierdo(), inorden);
            inorden.insertar(raiz.getElem(), inorden.longitud() +1);
            auxInorden(raiz.getDerecho(), inorden);
        }
    }
    public Lista listarPosorden(){
        Lista posorden = new Lista();
        if(this.raiz != null){
            auxPosOrden(this.raiz, posorden);
        }
        return posorden;
    }
    private void auxPosOrden(NodoArbol raiz, Lista posorden){
        if(raiz != null){
            auxPosOrden(raiz.getIzquierdo(), posorden);
            auxPosOrden(raiz.getDerecho(), posorden);
            posorden.insertar(raiz.getElem(), posorden.longitud() +1);
        }
    }
    public Lista listarPorNiveles(){
        //usamos una cola para poder recorrer los niveles
        Cola cola = new Cola();
        Lista nivel = new Lista();
        //ponemos el nodo raiz en la cola
        cola.poner(this.raiz);
        //recorremos mientras la cola no este vacia
        while(!cola.esVacia()){
            //obtenemos el tope de la cola
            NodoArbol aux = (NodoArbol) cola.obtenerFrente();
            nivel.insertar(aux.getElem(), nivel.longitud() +1);
            cola.sacar();
            
            //si el hijo izquierdo no es vacio lo ponemos en la cola
            if(aux.getIzquierdo() != null){
                cola.poner(aux.getIzquierdo());
            }
            //si el hijo derecho no es vacio lo ponemos en la cola
            if(aux.getDerecho() != null){
                cola.poner(aux.getDerecho());
            }
        }
        return nivel;
    }
    public Lista frontera(){
        Lista retorno = new Lista();
        if (this.raiz != null){
            fronteraAux(this.raiz,retorno);
        }  
        return retorno;
    }
    private void fronteraAux(NodoArbol n, Lista retorna){
        if(n != null){
            if (n.getIzquierdo() == null && n.getDerecho() == null){
                retorna.insertar(n.getElem(), 1);
            }else{
                if (n.getDerecho() != null){
                    fronteraAux(n.getDerecho(), retorna);
                }
                if (n.getIzquierdo() != null){
                    fronteraAux(n.getIzquierdo(), retorna);
                }
            }            
        }
    }
    public ArbolBin cloneInvertido() {
        ArbolBin clon = new ArbolBin();
        clon.raiz = clonarInvertidoAux(this.raiz);
        return clon;
    }
    private NodoArbol clonarInvertidoAux(NodoArbol n) {
        NodoArbol nClon = null;
        if (n != null) {
            nClon = new NodoArbol(n.getElem(), null, null);
            nClon.setIzquierdo(clonarInvertidoAux(n.getDerecho()));
            nClon.setDerecho(clonarInvertidoAux(n.getIzquierdo()));
        }
        return nClon;
    }
    public boolean equals(ArbolBin otro){
        return equalsAux(this.raiz,otro.raiz);
    }
    private boolean equalsAux(NodoArbol n, NodoArbol n2){
        boolean res = false;
        if(n != null && n2 != null ){
            if(  n2.getIzquierdo()== null && n2.getDerecho()== null && n.getIzquierdo() == null && n.getDerecho() == null && n.getElem() == n2.getElem()){
                res = true;
            }else{
                if(n.getElem() == n2.getElem()){
                    res = equalsAux(n.getIzquierdo(),n2.getIzquierdo());
                }
                if( res == true ){
                    res = equalsAux(n.getDerecho(),n2.getDerecho());
                }
            }
             
             }
    
        return res;
    }
}



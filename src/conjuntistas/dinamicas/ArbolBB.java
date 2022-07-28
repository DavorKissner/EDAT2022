/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas.dinamicas;
import lineales.dinamicas.Lista;

/**
 *
 * @author davor.kissner
 */
public class ArbolBB {
    private NodoABB raiz;
    
    public ArbolBB(){
        this.raiz = null;
    }
    public boolean insertar(Comparable elemento){
        boolean exito = true;
        if (this.raiz == null){
            this.raiz = new NodoABB(elemento, null, null);
        } else {
            exito = insertarAux(this.raiz, elemento);
        }
        return exito;
    }
    
    private boolean insertarAux(NodoABB n, Comparable elemento){
        boolean exito = true;
        
        if ((elemento.compareTo(n.getElem()) == 0)){
            exito = false;
            
        } else if (elemento.compareTo(n.getElem()) < 0){
            if (n.getIzquierdo() != null){
                exito = insertarAux(n.getIzquierdo(), elemento);
            } else {
                n.setIzquierdo(new NodoABB(elemento, null, null));
            }
        } else {
            if (n.getDerecho() != null) {
                exito = insertarAux(n.getDerecho(), elemento);
            } else {
                n.setDerecho(new NodoABB(elemento, null ,null));            }
        }
        return exito;
    }
    
    public boolean eliminar (Comparable elemento){
        boolean retorno = false;
        if (this.raiz != null) {
            if (this.raiz.getElem().equals(elemento)) {
                this.raiz = reemplazar(this.raiz);
                retorno = true;
            } else {
                if (elemento.compareTo(this.raiz.getElem()) < 0) {
                    retorno = eliminarAux(this.raiz, this.raiz.getIzquierdo(), elemento, 'I');                   
                } else {
                    retorno = eliminarAux(this.raiz, this.raiz.getDerecho(), elemento, 'D');
                }
            }
        }
        return retorno;
    }
    private boolean eliminarAux(NodoABB padre, NodoABB subRaiz, Comparable elemento, char hijo){
        boolean retorno = false;
        
        if (subRaiz != null){
            Comparable n = subRaiz.getElem();
            
            if (n.equals(elemento)) {
                NodoABB reemplazo = reemplazar(subRaiz);
                
                if (hijo == 'I') {
                    padre.setIzquierdo(reemplazo);
                } else {
                    padre.setDerecho(reemplazo);
                }
                retorno = true;
                
            } else {
                if (n.compareTo(elemento) > 0) {
                    retorno = eliminarAux(subRaiz, subRaiz.getIzquierdo(), elemento, 'I');
                } else {
                    retorno = eliminarAux(subRaiz, subRaiz.getDerecho(), elemento, 'D');
                }
            }
        }
        return retorno;
    }
    
    private NodoABB reemplazar (NodoABB subRaiz){
        NodoABB reemplazo = null;
        
        switch (condicion(subRaiz)) {
            case 0: reemplazo = null; break;
            case 1: reemplazo = metodo2(subRaiz); break;
            case 2: reemplazo = metodo3(subRaiz); break;
        }
        return reemplazo;
    }
    
    private int condicion (NodoABB nodo){
        int retorno = 0;
        
        if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
            retorno = 2;
        } else if (raiz.getIzquierdo() != null || raiz.getDerecho() != null){
            retorno = 1;
        }
        return retorno;
    }
    
    private NodoABB metodo2 (NodoABB nodo) {
        NodoABB retorno;
        if (nodo.getIzquierdo() != null) {
            retorno = nodo.getIzquierdo();
        } else {
            retorno = nodo.getDerecho();
        }
        return retorno;
    }
    private NodoABB metodo3 (NodoABB nodo){
        NodoABB candidatoDerecho = candidatoB(nodo.getDerecho());
        nodo.setElem(candidatoDerecho.getElem());
        
        eliminarAux(nodo, nodo.getDerecho(), candidatoDerecho.getElem(), 'D');
        return nodo;
    }
    
    private NodoABB candidatoB(NodoABB raiz){
        NodoABB aux = raiz;
        while (aux.getIzquierdo() != null) {
            aux = aux.getIzquierdo();
        }
        return aux;
    }
    
    public boolean pertenece (Comparable elemento) {
        boolean retorno = false;
        if (this.raiz != null) {
            retorno = perteneceAux(this.raiz, elemento);
        }
        return retorno;
    }
    private boolean perteneceAux(NodoABB n, Comparable elem){
        boolean retorno = false;
        if (n != null) {
            if (n.getElem().equals(elem)){
                retorno = true;
            } else {
                if (n.getElem().compareTo(elem) > 0) {
                    retorno = perteneceAux(n.getIzquierdo(), elem);
                } else {
                    retorno = perteneceAux(n.getDerecho(), elem);
                }
            }
        }
        return retorno;
    }
    public boolean esVacio(){
        return this.raiz == null;
    }

    public Comparable minimoElem() {
        Comparable minimo = null;
        if (this.raiz != null) {
            minimo = minimoElemAux(this.raiz);
        }
        return minimo;
    }
    private Comparable minimoElemAux(NodoABB n) {
        NodoABB aux = n;
        while (aux.getIzquierdo() != null) {
            aux = aux.getIzquierdo();
        }
        return aux.getElem();
    }
    public Comparable maximoElem() {
        Comparable minimo = null;
        if (this.raiz != null) {
            minimo = maximoElemAux(this.raiz);
        }
        return minimo;
    }
    private Comparable maximoElemAux(NodoABB n) {
        NodoABB aux = n;
        while (aux.getDerecho() != null) {
            aux = aux.getDerecho();
        }
        return aux.getElem();
    }
    public void eliminarMinimo(){
        if (this.raiz != null){
            if (this.raiz.getIzquierdo() == null){
                if (this.raiz.getDerecho() == null){
                    this.raiz = null;
                } else {
                    this.raiz = this.raiz.getDerecho();
                }
            } else {
                eliminarMinimoAux(this.raiz);
            }
        }
    }
    private void eliminarMinimoAux(NodoABB n){
        NodoABB aux = null;
        if (n != null){
            if (n.getIzquierdo() != null){
                while (n.getIzquierdo().getIzquierdo() != null){
                    n = n.getIzquierdo();
                }
            }
            if (n.getIzquierdo().getDerecho() != null){
                aux = n.getIzquierdo().getDerecho();
                n.setIzquierdo(aux);
            }else{
                n.setIzquierdo(null);
            }
        }
    }
    public String toString(){
        String str = llamadoString(this.raiz);
        return str;
         
    }
    private String llamadoString(NodoABB n) {
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
    private NodoABB obtenerNodo (Comparable elemento) {
        NodoABB retorno = null;
        if (this.raiz != null) {
            retorno = obtenerNodoAux(this.raiz, elemento);
        }
        return retorno;
    }
    private NodoABB obtenerNodoAux(NodoABB n, Comparable elem){
        NodoABB retorno = null;
        if (n != null) {
            if (n.getElem().equals(elem)){
                retorno = n;
            } else {
                if (n.getElem().compareTo(elem) > 0) {
                    retorno = obtenerNodoAux(n.getIzquierdo(), elem);
                } else {
                    retorno = obtenerNodoAux(n.getDerecho(), elem);
                }
            }
        }
        return retorno;
    }
    public ArbolBB clonarParteInvertida(Comparable elem) {
        ArbolBB clonInv = new ArbolBB();
        NodoABB aux = obtenerNodo(elem);
        if (aux != null) {
            clonInv.raiz = new NodoABB(aux.getElem(), null, null);
            clonarParteInvertidaAux(clonInv.raiz, aux);
        }
        return clonInv;
    }

    private void clonarParteInvertidaAux(NodoABB nuevo, NodoABB original) {
        if (original != null) {
            if (original.getDerecho() != null) {
                nuevo.setIzquierdo(new NodoABB(original.getDerecho().getElem(), null, null));
                clonarParteInvertidaAux(nuevo.getIzquierdo(), original.getDerecho());
            }
            if (original.getIzquierdo() != null) {
                nuevo.setDerecho(new NodoABB(original.getIzquierdo().getElem(), null, null));
                clonarParteInvertidaAux(nuevo.getDerecho(), original.getIzquierdo());
            }
        }
    } 
    public Lista listarMayorIgual(Comparable elem){
        Lista l1 = new Lista();
        if (this.raiz != null){
            listarMayorIgualAux(this.raiz, elem, l1);
        }
        return l1;
    }
    private void listarMayorIgualAux(NodoABB n, Comparable elem, Lista l1) {
        if (n != null) {
            if (n.getDerecho() != null) {
                listarMayorIgualAux(n.getDerecho(), elem, l1);
            }
            if (n.getElem().compareTo(elem) >= 0) {
                l1.insertar(n.getElem(), l1.longitud() + 1);
            }
            if (n.getIzquierdo() != null) {
                listarMayorIgualAux(n.getIzquierdo(), elem, l1);
            }
        }
    }
    public Lista listarMenores(Comparable elem){
        Lista l1 = new Lista();
        if (this.raiz != null){
            l1 = listarMenoresAux(this.raiz, elem, l1);
        }
        return l1;
    }
    private Lista listarMenoresAux(NodoABB n, Comparable elem, Lista l1){
        if (n != null){
            if (n.getElem().compareTo(elem) < 0){
                listarMenoresAux(n.getIzquierdo(), elem, l1);
                l1.insertar(n.getElem(), l1.longitud() + 1);
                listarMenoresAux(n.getDerecho(), elem, l1);
            } else {
                listarMenoresAux(n.getIzquierdo(), elem, l1);
            }
            
        }
        return l1;
    }
    public void eliminarHojasRango(Comparable min, Comparable max) {
        if (this.raiz != null) {
            if (esHojaEnRango(this.raiz, min, max)) {
                this.raiz = null;
            } else {
                if (this.raiz.getElem().compareTo(max) >= 0) {
                    if (this.raiz.getIzquierdo() != null) {
                        if (esHojaEnRango(this.raiz.getIzquierdo(), min, max)){
                            this.raiz.setIzquierdo(null);
                        } else {
                            eliminarHojasRangoAux(this.raiz.getIzquierdo(), min, max, this.raiz.getIzquierdo().getIzquierdo(), this.raiz.getIzquierdo().getDerecho());
                        }
                    }
                } else {
                    if (this.raiz.getElem().compareTo(min) <= 0) {
                        if (this.raiz.getDerecho() != null) {
                            if (esHojaEnRango(this.raiz.getDerecho(), min, max)){
                                this.raiz.setDerecho(null);                              
                            } else{
                                eliminarHojasRangoAux(this.raiz.getDerecho(), min, max, this.raiz.getDerecho().getIzquierdo(), this.raiz.getDerecho().getDerecho());
                            }                       
                        }
                    } else {
                        eliminarHojasRangoAux(this.raiz, min, max, this.raiz.getIzquierdo(), this.raiz.getDerecho());
                    }
                }
            }
        }
    }
    private boolean esHojaEnRango(NodoABB n, Comparable min, Comparable max){        
        boolean exito = false;
        if (n.getDerecho() == null && n.getIzquierdo() == null && n.getElem().compareTo(min) >= 0 && n.getElem().compareTo(max) <= 0) {
            exito = true;
        }
        return exito;
    }
    private void eliminarHojasRangoAux(NodoABB n, Comparable min, Comparable max, NodoABB hijoIzq, NodoABB hijoDer) { //recorrer preorden
        if (n != null) {
            if (hijoIzq != null) {
                if (hijoIzq.getDerecho() == null && hijoIzq.getIzquierdo() == null) {
                    if (hijoIzq.getElem().compareTo(min) >= 0 && hijoIzq.getElem().compareTo(max) <= 0) {
                        n.setIzquierdo(null);
                    }
                }
            }
            if (hijoDer != null) {
                if (hijoDer.getDerecho() == null && hijoDer.getIzquierdo() == null) {
                    if (hijoDer.getElem().compareTo(min) >= 0 && hijoDer.getElem().compareTo(max) <= 0) {
                        n.setDerecho(null);
                    }
                }
            }
            if (n.getIzquierdo() != null) {
                eliminarHojasRangoAux(n.getIzquierdo(), min, max, n.getIzquierdo().getIzquierdo(), n.getIzquierdo().getDerecho());
            }
            if (n.getDerecho() != null) {
                eliminarHojasRangoAux(n.getDerecho(), min, max, n.getDerecho().getIzquierdo(), n.getDerecho().getDerecho());
            }
        }
    }
    public Lista listarMayoresQue(Comparable valor, Comparable elem){
        Lista l1 = new Lista();
        if (pertenece(elem)){
            NodoABB nodoElem = obtenerNodo(this.raiz, elem);
            System.out.println(nodoElem.getElem());
            listarMayoresQueAux(nodoElem, valor, l1);                           
        }
        return l1;
    }
    private void listarMayoresQueAux(NodoABB n, Comparable valor, Lista l1){
        if (n != null){
            System.out.println(n.getElem());
            listarMayoresQueAux(n.getIzquierdo(), valor, l1);
            if (n.getElem().compareTo(valor) > 0){
                l1.insertar(n.getElem(), l1.longitud()+1);
            }
            listarMayoresQueAux(n.getDerecho(), valor, l1);
        }
    }
    private NodoABB obtenerNodo(NodoABB n, Comparable elem){
        NodoABB aux = null;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                aux = n;
            } else {
                if (n.getElem().compareTo(elem) > 0) {
                    aux = obtenerNodo(n.getIzquierdo(), elem);
                } else {
                    aux = obtenerNodo(n.getDerecho(), elem);
                }
            }
        }
        return aux;
    }
    public int diferenciaCandidatos(Comparable elemento) {
        int res = 0;
        if (pertenece(elemento)) {
            NodoABB nodoRaiz = obtenerNodo(this.raiz, elemento);
            if (nodoRaiz.getDerecho() == null && nodoRaiz.getIzquierdo() == null){
                res = -1;
            } else {
                res = diferenciaCandidatosAux(nodoRaiz);
            }
        }
        return res;
    }
    private int diferenciaCandidatosAux(NodoABB n){
        NodoABB aux1, aux2;
        int cand1, cand2, res;
        aux1 = n.getIzquierdo();
        aux2 = n.getDerecho();
        while (aux1.getDerecho() != null){
            aux1 = aux1.getDerecho();
        }
        while (aux2.getIzquierdo() != null){
            aux2 = aux2.getIzquierdo();
        }
        cand1 = (int)n.getElem() - (int)aux1.getElem();
        cand2 = (int)aux2.getElem() - (int)n.getElem();
        if (cand1 <= cand2) {
            res = cand1;
        } else {
            res = cand2;
        }
        return res;       
    }  
    public Lista listarPosorden(){
        Lista l1 = new Lista();
        if (this.raiz != null){
            listarPosordenAux(this.raiz, l1);
        }
        return l1;
    }
    private void listarPosordenAux(NodoABB n, Lista l1){
        if (n != null){
            if (n.getIzquierdo() != null){
                listarPosordenAux(n.getIzquierdo(), l1);
            }
            if (n.getDerecho() != null){
                listarPosordenAux(n.getDerecho(), l1);
            }
            l1.insertar(n.getElem(), l1.longitud()+1);          
        }
    }
    public Lista listarPreorden(){
        Lista l1 = new Lista();
        if (this.raiz != null){
            listarPreordenAux(this.raiz, l1);
        }
        return l1;
    }
    private void listarPreordenAux(NodoABB n, Lista l1){
        if (n != null){
            l1.insertar(n.getElem(), l1.longitud()+1);    
            if (n.getIzquierdo() != null){
                listarPreordenAux(n.getIzquierdo(), l1);
            }
            if (n.getDerecho() != null){
                listarPreordenAux(n.getDerecho(), l1);
            }        
        }
    }
    public Lista listarInorden(){
        Lista l1 = new Lista();
        if (this.raiz != null){
            listarInordenAux(this.raiz, l1);
        }
        return l1;
    }
    private void listarInordenAux(NodoABB n, Lista l1){
        if (n != null){              
            if (n.getIzquierdo() != null){
                listarInordenAux(n.getIzquierdo(), l1);               
            }
            l1.insertar(n.getElem(), l1.longitud()+1); 
            if (n.getDerecho() != null){
                listarInordenAux(n.getDerecho(), l1);
            }        
        }
    }
    public void eliminarHojasEnRango(Comparable min, Comparable max) {
        if (this.raiz != null) {
            if(esHojaEnRango(this.raiz, min, max)){
                this.raiz = null;
            } else {
                eliminarRangoEnAux(this.raiz, null, min, max);                      
            }
        }
    }
    private void eliminarRangoEnAux(NodoABB n, NodoABB padre, Comparable min, Comparable max) {
        if (n != null) {
            System.out.println(n.getElem().toString());
            if (n.getDerecho() == null && n.getIzquierdo() == null && min.compareTo(n.getElem()) <= 0 && max.compareTo(n.getElem()) >= 0) {
                eliminarHoja(n.getElem(), padre);
            } else {
                if (n.getIzquierdo() != null && n.getElem().compareTo(min) > 0) {
                    eliminarRangoEnAux(n.getIzquierdo(), n, min, max);
                }
                if (n.getDerecho() != null && n.getElem().compareTo(max) < 0) {
                    eliminarRangoEnAux(n.getDerecho(), n, min, max);
                }
            }

        }
    }
    private void eliminarHoja(Object elem, NodoABB padre) {
        if (padre.getDerecho() != null && padre.getDerecho().getElem().equals(elem)) {
            padre.setDerecho(null);
        } else {
            padre.setIzquierdo(null);
        }
    }
}

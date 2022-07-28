/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas.dinamicas;

/**
 *
 * @author davor.kissner
 */
public class NodoABB {
    private Comparable elem;
    private NodoABB izquierdo;
    private NodoABB derecho;
    
    public NodoABB(Comparable elem, NodoABB izquierdo, NodoABB derecho){
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }        
    public Comparable getElem(){
        return elem;
    }
    public NodoABB getIzquierdo(){
        return izquierdo;
    }
    public NodoABB getDerecho(){
        return derecho;
    }
    public void setElem(Comparable elem){
        this.elem = elem;
    }
    public void setIzquierdo(NodoABB izquierdo){
        this.izquierdo = izquierdo;
    }
    public void setDerecho(NodoABB derecho){
        this.derecho = derecho;
    }             
}

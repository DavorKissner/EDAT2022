/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author Kissner Davor FAI-3185
 */
public class Pila {
    /*Arreglo tipo Object, (elementos de la pila)*/
    private Object[] arreglo;
    /*Numero que indica el indice del tope (ultimo valor ingresado), -1 si es vacio*/
    private int tope;
    /*Tamaño de la Pila*/
    private static final int TAMANIO = 10;
    
    public Pila(){
        /*Metodo constructor de Pila*/
        this.arreglo = new Object [TAMANIO];
        this.tope = -1;
    }
    public boolean apilar(Object nuevoElem){
        /*Agrega un nuevo elemento a la pila*/
        boolean exito;
        if (this.tope+1 >= this.TAMANIO){
            /*ERROR: PILA LLENA*/
            exito = false;
        }
        else{
            this.tope++;
            this.arreglo[tope] = nuevoElem;
            exito = true;
            /* retorna true si se apilo el elemento*/
        }
        return exito;
    }
    
    public boolean desapilar(){
        /* Quita elementos de la Pila */
        boolean exito;
        if (this.tope == -1){
            /* ERROR: PILA VACIA */
            exito = false;
        }
        else{
            /* El valor se desapila y se le asigna el valor de null */
            this.arreglo[tope] = null;
            this.tope--;
            /* El tope cambia y retorna true */
            exito = true;
        }
        return exito;
    }
    
    public Object obtenerTope(){
        /* Obtiene el elemento del tope de la pila y lo retorna */
        Object elemento = null;
        if (this.tope >= 0){
            elemento = this.arreglo[tope];
        }       
        return elemento;
    }
    public boolean esVacia(){
        /* Retorna true si la pila esta vacia o false si no lo esta */
        return this.tope == -1;              
    }
    @Override
    public Pila clone(){
        /* Hace una copia de la Pila y la retorna */
        Pila clone = new Pila();
        clone.tope = this.tope;
        /* copia el el tope de la pila original y tambien copia el arreglo de la pila original por referencia */
        if (!esVacia()){
            clone.arreglo = this.arreglo.clone();
        }      
        return clone;
    }
    @Override
    public String toString(){
        /* Retorna en forma de String los elementos de la pila */
        int i, ite = this.tope;    
        String str;
        if (!esVacia()) {
            str = "[";
            for (i = 0; i <= ite; i++) {
                str += this.arreglo[i];
                if (i < ite){
                    str += ",";
                }              
            }
            str += "]";
        } else {
            /* Si la pila esta vacia retorna este mensaje */
            str = "Pila Vacia";
        }
        return str;
    }

    public void vaciar(){
        /* Vacia la pila, todos sus elementos son null y tope es -1 */
        int ite = this.tope;
        for (int j = 0; j <= ite; j++) {
            this.arreglo[j] = null;
        }
	this.tope = -1;
    }
}
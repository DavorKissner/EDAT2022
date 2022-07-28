/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author Davor Kissner
 */
public class Cola {
    //atributos
    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;
    
    //constructor
    public Cola(){
        this.arreglo = new Object[this.TAMANIO];
        this.frente = 0;
        this.fin = 0;              
    }
    
    public boolean poner(Object nuevoElem){
        //metodo que pone un elemento en la cola
        boolean exito;
        //verifica si la cola esta llena, si lo esta retorna falso
        if ((this.fin +1)%TAMANIO == this.frente){
            exito = false;
        }
        else{
            //inserta el elemento
            this.arreglo[this.fin] = nuevoElem;
            //avanza fin(de manera circular)
            this.fin = (this.fin + 1) % this.TAMANIO;
            exito = true;
        }
        return exito;
    }
    public boolean sacar(){
        //
        boolean exito = true;
        if (this.esVacia()){ //la cola esta vacia reporta error
            exito = false;           
        }
        else{ //hay al menos 1 elemento, avanza frente (de manera circular)
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAMANIO;
        }
        return exito;
    }
    public Object obtenerFrente(){    
        Object elem;
        if (this.arreglo[this.frente] == null){ //si la cola esta vacia retorna null
            elem = null;           
        }
        else{
            //si hay al menos 1 elemento, retorna el que se encuentra en el frente
            elem = this.arreglo[this.frente]; 
        }
        return elem;
    }

    public boolean esVacia(){
        //si el fin es igual al frente la cola esta vacia y retorna true, sino, retorna false
        return this.fin == this.frente;
    }
    public void vaciar(){
        int i;
        //vacia el arreglo de la cola
        for (i = 0; i < TAMANIO; i++){
            this.arreglo[i] = null;
        }
        //pone frente = fin en 0, (cola vacia)
        this.frente = 0;
        this.fin = 0;
    }
    public String toString(){
        String str = "[";
        int i;
        if (this.esVacia()){ //cola vacia
            str = "[]";
        }
        else{
            i = this.frente;
            while(i != this.fin){ //recorre hasta que i = fin
                if (i != this.fin-1){
                    str += this.arreglo[i].toString()+ ","; //agrega el elemento a la cadena
                }
                else{
                    str += this.arreglo[i].toString();
                }
                //condicion forma circular
                i = (i+1)%this.TAMANIO;
            }
            str += "]";
        }
        return str;
    }
    @Override
    public Cola clone(){
        //crea una copia exacta de la cola
        int i;
        Cola clon = new Cola();
        //recorre la cola original y copia los elementos en la cola clon
        for (i = 0; i < TAMANIO; i++){
            clon.arreglo[i] = this.arreglo[i];          
        }
        //iguala el frente y el fin
        clon.frente = this.frente;
        clon.fin = this.fin;
        return clon;
    }
    
    

}


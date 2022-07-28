/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;


/**
 *
 * @author Usuario
 */
public class TestsCadena {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cola cola = new Cola();
        Cola colaNueva = new Cola();
        Lista list = new Lista();
        cola.poner(0);
        cola.poner(1);
        cola.poner(2);
        cola.poner(3);
        cola.poner(4);
        cola.poner(5);
        cola.poner(6);
        cola.poner(7);
        cola.poner(8);
        cola.poner(9);
        Lista listita = new Lista();
        listita.insertar(1, 1);
        listita.insertar(2, 2);
        listita.insertar(3, 3);
        listita.insertar(4, 4);
        listita.insertar(5, 5);
        listita.insertar(6, 6);
        listita.insertar(7, 7);
        listita.insertar(8, 8);
        listita.insertar(9, 9);

        System.out.println(generarSecuencia(cola, 4).toString());

    }
    public static Cola generar(Cola c1) {
        //Metodo que devuelve una cola con el formato cc´c#
        Cola aux = new Cola();
        Pila pilAux = new Pila();
        Cola conca = new Cola();
        Cola clon = c1.clone(); 
        char elem = ' ';
        //Realiza un unico recorrido de la cola original
        while (!clon.esVacia()) {
            elem = (char)clon.obtenerFrente();
            if(elem != '#') {
                aux.poner(elem);
                pilAux.apilar(elem);
                conca.poner(elem);
            } else {
                conca.poner(armaCad(aux, pilAux));
                conca.poner('#');
            }
            //elimina el frente de la cola clon
            clon.sacar();
        }
        //genera cadena para la ultima aparicion de caracteres despues del ultimo numeral
        conca.poner(armaCad(aux, pilAux));
        return conca;
    }

    public static Cola armaCad(Cola aux, Pila pilAux) {
        Cola colaAux = new Cola();
        while (!pilAux.esVacia()) {
            colaAux.poner(pilAux.obtenerTope());
            pilAux.desapilar();
        }
        while (!aux.esVacia()) {
            colaAux.poner(aux.obtenerFrente());
            aux.sacar();
        }       
        return colaAux;
    }
    public static Lista generarSecuencia(Cola c, int t) {
        //Metodo que devuelve una cola con el formato cc´c#
        int cont = 1, cont2 = 1;
        Cola clon = new Cola();
        Cola aux = new Cola();
        Pila pilAux = new Pila();
        Lista conca = new Lista();
        clon = c.clone(); 
        //Realiza un unico recorrido de la cola original
        while (!clon.esVacia()) {
            pilAux.apilar((int)clon.obtenerFrente());
            aux.poner((int)clon.obtenerFrente());
            if (cont%t == 0){
                conca.insertar(armarCadena(pilAux,aux), cont2);             
                cont2++;   
                System.out.println(cont2);
            }
            clon.sacar();
            cont++; 
        }
        conca.insertar(armarCadena(pilAux,aux), cont2);  
        //genera cadena para la ultima aparicion de caracteres despues del ultimo numeral
        return conca;
    }
    public static Lista armarCadena(Pila pilAux, Cola aux){
        Lista nueva = new Lista();
        int i = 1;
        while (!pilAux.esVacia()){
            nueva.insertar(pilAux.obtenerTope(), i);
            pilAux.desapilar();
            i++;
        }
        while (!aux.esVacia()){
            nueva.insertar(aux.obtenerFrente(), i);
            aux.sacar();
            i++;
        }
        nueva.insertar('$', i);
        return nueva;
    }
}

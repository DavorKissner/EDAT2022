/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.jerarquicas;
import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;
//import conjuntistas.dinamicas.ArbolBB;
/**
 *
 * @author Usuario
 */
public class SimulacroGen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArbolGen unArbol = new ArbolGen();
        //Lista l1 = new Lista();
        unArbol.insertar(20, null);
        unArbol.insertar(54, 20);
        unArbol.insertar(13, 20); 
        unArbol.insertar(12, 13); 
        unArbol.insertar(15, 13); 
        unArbol.insertar(4, 54);
        unArbol.insertar(27, 54);
        unArbol.insertar(11, 54);
        unArbol.insertar(17, 27);
        System.out.println();
        /*unArbol.insertar(56);
        unArbol.insertar(13);
        unArbol.insertar(65); 
        unArbol.insertar(7); 
        unArbol.insertar(24); 
        unArbol.insertar(100);
        unArbol.insertar(15);
        System.out.println(unArbol.toString() +"\n");
        unArbol.eliminarHojasEnRango(5, 80);
        System.out.println(unArbol.toString());*/      
        
        
        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.lineales;
import lineales.dinamicas.Pila;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class TestPila {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pila unaPila = new Pila();
        char continuar = ' ';
        int elementos;
        boolean lleno = true, vacio = true;

        do {
            System.out.println("Desea ingresar un elemento a la pila? 's' para SI, 'n' para NO");
            continuar = sc.next().charAt(0);
            if (continuar == 's') {
                System.out.println("Ingrese un elemento");
                elementos = sc.nextInt();
                lleno = unaPila.apilar(elementos);
                if (!lleno) {
                    System.out.println("La pila esta llena");
                }
            }
        } while ((continuar == 's') && lleno == true);

        System.out.println("Desea realizar alguna operación con la pila? 's' para SI, 'n' para NO");
        continuar = sc.next().charAt(0);
        while (continuar == 's') {
            System.out.println("Que accion desea realizar? "
                    + "\n  1 para quitar elementos de la fila "
                    + "\n  2 para ver el tope de la pila"
                    + "\n  3 para vaciar la pila"
                    + "\n  4 para clonar la pila"
                    + "\n  5 para mostrar por pantalla la pila");

            int n = sc.nextInt();

            switch (n) {
                case 1:
                    desapilarPila(unaPila);
                    break;
                case 2:
                    muestraTope(unaPila);
                    break;
                case 3:
                    vaciarPila(unaPila);
                    break;
                case 4:
                    clonarPila(unaPila);
                    break;
                case 5:
                    muestraPila(unaPila);
                    break;
            }
            System.out.println("            ");
            System.out.println("Desea realizar alguna operación con la pila? 's' para SI, 'n' para NO");
            continuar = sc.next().charAt(0);

        }

    }
    public static void desapilarPila(Pila pila) {
        char continuar;
        boolean vacio = true;
        Scanner sc = new Scanner(System.in);
        if (pila.esVacia()) {
            System.out.println("La pila está vacia, no se oueden quitar elementos");
        } else {
            do {
                System.out.println("Desea quitar un elemento de la pila? 's' SI 'n' NO");
                continuar = sc.next().charAt(0);
                if (continuar == 's') {
                    vacio = pila.desapilar();
                }
            } while (continuar == 's' && vacio == true);

            System.out.println("Nueva pila: " + pila.toString());
            if (pila.esVacia()) {
                System.out.println("La pila está vacia");
            } else {
                System.out.println("Nuevo tope:" + pila.obtenerTope());
            }
        }
    }

    public static void muestraTope(Pila pila) {
        if (pila.esVacia()) {
            System.out.println("La pila está vacia");
        } else {
            System.out.println("Tope: " + pila.obtenerTope());
        }
    }

    public static void vaciarPila(Pila pila) {
        if (pila.esVacia()) {
            System.out.println("La pila ya se ecuentra vacia");
        } else {
            pila.vaciar();
            System.out.println("Pila vaciada " + pila.toString());
        }
    }

    public static void clonarPila(Pila pila) {
        Pila pilaClonada = pila.clone();
        System.out.println("Pila Original " + pila.toString());
        System.out.println("Nueva Pila: " + pilaClonada.toString());
    }

    public static void muestraPila(Pila pila) {
        System.out.println(pila.toString());
    }

   public static void esCapicua(){
    }
}

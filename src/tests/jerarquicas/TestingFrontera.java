package tests.jerarquicas;
import lineales.dinamicas.Lista;
import jerarquicas.dinamicas.ArbolGen;

/**
 *
 * @author Tomas y Davor 
 */
public class TestingFrontera {

    public static void main(String[] args) {
        System.out.println("TEST DE PRUEBA PARA EL MÉTODO sonFrontera():");
        System.out.println("CREAMOS EL ÁRBOL GENÉRICO:");
        ArbolGen unArbol = new ArbolGen();

        unArbol.insertar(1, null);
        unArbol.insertar(2, 1);
        unArbol.insertar(3, 1);
        unArbol.insertar(8, 1);
        unArbol.insertar(4, 2);
        unArbol.insertar(6, 2);
        unArbol.insertar(5, 3);
        unArbol.insertar(7, 5);
        System.out.println(unArbol.toString());
        
        System.out.println("--------------------------------------------------");
        System.out.println("PRIMER CASO: ELEMENTOS REPETIDOS");
        System.out.println("CREAMOS LISTA:");
        Lista lis = new Lista();
        lis.insertar(2, 1);
        lis.insertar(1, 2);
        lis.insertar(1, 3);
        lis.insertar(3, 4);
        System.out.println(lis.toString());
        boolean n = unArbol.sonFrontera(lis);
        System.out.println("DEBE DAR FALSE: ----->" + n);
        lis.vaciar();
        System.out.println("--------------------------------------------------");
        System.out.println("SEGUNDO CASO: ELEMENTOS QUE SON HOJAS");
        System.out.println("NUEVA LISTA:");
        lis.insertar(6, 1);
        lis.insertar(8, 2);
        lis.insertar(7, 3);
        lis.insertar(4, 4);
        System.out.println(lis.toString());
        n = unArbol.sonFrontera(lis);
        System.out.println("DEBE DAR TRUE: ---->" + n);
        lis.vaciar();
        System.out.println("--------------------------------------------------");
        System.out.println("TERCER CASO: ELEMENTOS NO HOJA CON ELEMENTOS HOJA");
        System.out.println("NUEVA LISTA:");
        lis.insertar(1, 1);
        lis.insertar(7, 2);
        lis.insertar(5, 3);
        lis.insertar(8, 4);
        System.out.println(lis.toString());
        n = unArbol.sonFrontera(lis);
        System.out.println("DEBE DAR FALSE: ----->" + n);
        lis.vaciar();
        System.out.println("--------------------------------------------------");
        System.out.println("CUARTO CASO: LISTA VACÍA");
        System.out.println("NUEVA LISTA:");
        System.out.println(lis.toString());
        n = unArbol.sonFrontera(lis);
        System.out.println("DEBE DAR FALSE: ----->" + n);
        System.out.println("--------------------------------------------------");
        System.out.println("QUINTO CASO: ELEMENTOS REPETIDOS QUE SON HOJAS");
        System.out.println("NUEVA LISTA:");
        lis.insertar(4, 1);
        lis.insertar(7, 2);
        lis.insertar(8, 3);
        lis.insertar(8, 4);
        System.out.println(lis.toString());
        n = unArbol.sonFrontera(lis);
        System.out.println("DEBE DAR FALSE: ----->" + n);
        lis.vaciar();
        System.out.println("--------------------------------------------------");
        System.out.println("SEXTO CASO: LISTA CON UN SOLO ELEMENTO HOJA");
        System.out.println("NUEVA LISTA:");
        lis.insertar(7, 1);
        System.out.println(lis.toString());
        n = unArbol.sonFrontera(lis);
        System.out.println("DEBE DAR TRUE: ----->" + n);
        lis.vaciar();
        System.out.println("--------------------------------------------------");
        System.out.println("SEPTIMO CASO: ARBOL VACÍO CON LISTA");
        System.out.println("VACIAMOS ARBOL");
        unArbol.vaciar();
        System.out.println("NUEVA LISTA:");
        lis.insertar(1, 1);
        lis.insertar(7, 2);
        lis.insertar(5, 3);
        lis.insertar(8, 4);
        System.out.println(lis.toString());
        n = unArbol.sonFrontera(lis);
        System.out.println("DEBE DAR FALSE: ----->" + n);
        

    }

}

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
public class ArbolGen {

    //atributos;
    private NodoGen raiz;

    public ArbolGen() {
        //constructor
        this.raiz = null;
    }

    public boolean insertar(Object elem, Object elemPadre) {
        boolean res = true;
        //si el arbol es vacio, instertamos el nodo raiz
        if (this.raiz == null) {
            this.raiz = new NodoGen(elem, null, null);
        } else {
            //buscamos el nodo padre
            NodoGen padre = obtenerNodo(this.raiz, elemPadre);
            if (padre != null) {
                //si el padre existe lo insertamos en el arbol
                padre.setHijoIzquierdo(new NodoGen(elem, null, padre.getHijoIzquierdo()));
            } else {
                //si no existe el padre retorna falso
                res = false;
            }

        }
        return res;
    }

    private NodoGen obtenerNodo(NodoGen n, Object elem) {
        //metodo que obtiene el nodo de un arbol a partir de un elemento
        NodoGen res = null;
        //si el nodo no es vacio
        if (n != null) {
            //se comparan ambos elementos
            if (n.getElem().equals(elem)) {
                res = n; //si son iguales obtuvimos el nodo buscado
            } else {
                //sino, obtenemos el hijo izquierdo del nodo
                NodoGen aux = n.getHijoIzquierdo();
                while (aux != null && res == null) {
                    //iteramos y llamamos recursivamente al metodo obtenerNodo
                    res = obtenerNodo(aux, elem);
                    aux = aux.getHermanoDerecho();
                }
            }
        }
        return res;
    }

    public String toString() {
        //metodo toString, imprime el arbol
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen n) {
        //metodo private para imprimir el arbol
        String s = "";
        if (n != null) {
            //visita del nodo n
            s += n.getElem().toString() + " -> ";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            //comienza recorrido de los hijos de n llamando recursivamente
            //para que cada hijo agregue su subcadena a la general
            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }

    public boolean pertenece(Object el) {
        boolean exito = false;
        if (this.raiz != null) {
            //llamamos al metodo obtenerNodo
            NodoGen n = obtenerNodo(this.raiz, el);
            //si no lo encuentra retorna false
            if (n != null) {
                exito = true;
            }
        }
        return exito;
    }

    public int altura() {
        int alt = -1;
        if (this.raiz != null) {
            //si el arbol no es vacio llamamos al metodo alturaAux
            alt = alturaAux(this.raiz);
        }
        return alt;
    }

    private int alturaAux(NodoGen n) {
        int it = 0;
        //si el nodo n no es null evaluamos
        if (n.getHijoIzquierdo() != null) {
            //obtenemos el resultado del hijo mas a la izquierda
            NodoGen hijo = n.getHijoIzquierdo();
            int retornoHijos = alturaAux(hijo);
            hijo = hijo.getHermanoDerecho();
            //mientras haya hijos repite
            while (hijo != null) {
                int retornoLlamado = alturaAux(hijo);
                if (retornoHijos < retornoLlamado) {
                    retornoHijos = retornoLlamado;
                }
                hijo = hijo.getHermanoDerecho();
            }
            it = retornoHijos + 1;
        }
        return it;
    }

    public Lista ancestros(Object elem) {
        //devuelve una lista con el recorrido de los ancestros de un nodo del arbol
        Lista l1 = new Lista();
        listarAncestrosAux(this.raiz, l1, elem);
        l1.eliminar(l1.longitud());
        return l1;
    }

    private void listarAncestrosAux(NodoGen n, Lista l1, Object elem) {
        if (n != null) {
            //cuando encuentre el nodo lo inserta en la lista
            if (n.getElem().equals(elem)) {
                l1.insertar(elem, 1);
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                //mientras no haya un elemento en la lista sigue iterando
                while (hijo != null && l1.esVacia()) {
                    //llamado recursivo para obtener hijo izquierdo
                    listarAncestrosAux(hijo, l1, elem);
                    hijo = hijo.getHermanoDerecho();
                }
                if (!l1.esVacia()) {
                    //cuando ya hay un elemento desapila e inserta a sus anscestros
                    l1.insertar(n.getElem(), 1);
                }
            }
        }
    }

    public int nivel(Object elem) {
        //devuelve un entero que nos dice el nivel que se encuentra el nodo
        return nivelAux(this.raiz, elem, 0);
    }

    private int nivelAux(NodoGen n, Object elem, int piso) {
        int res = -1;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                //cuando encuentra el nodo pone el "piso" (nivel 0)
                res = piso;
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && res == -1) {
                    //recorre el arbol y suma 1 al piso mientras desapila
                    res = nivelAux(hijo, elem, piso + 1);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return res;
    }

    public Object padre(Object elem) {
        Object retorno = null;
        if (this.raiz != null) {
            //si el elemento no es la raiz
            if (!this.raiz.getElem().equals(elem)) {
                //llama al metodo padreAux
                retorno = padreAux(this.raiz, elem);
            }
        }
        return retorno;
    }

    private Object padreAux(NodoGen n, Object elem) {
        Object res = null;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && res == null) {
                //busca si el elemento del hijo del nodo n es igual al elem
                if (hijo.getElem().equals(elem)) {
                    res = n.getElem();
                } else {
                    //si no lo es hace el llamado recursivo
                    res = padreAux(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return res;
    }

    @Override
    public ArbolGen clone() {
        ArbolGen c = new ArbolGen();
        c.raiz = clonarAux(this.raiz);
        return c;
    }

    private NodoGen clonarAux(NodoGen n) {
        NodoGen nodoClon = null;
        if (n != null) {
            //pone la raiz del clone
            nodoClon = new NodoGen(n.getElem(), null, null);
            //pone al hijo izquierdo y hace llamado recursivo
            nodoClon.setHijoIzquierdo(clonarAux(n.getHijoIzquierdo()));
            //pone el hermano derecho y hace llamado recursivo
            nodoClon.setHermanoDerecho(clonarAux(n.getHermanoDerecho()));
        }
        return nodoClon;
    }

    public Lista listarPreorden() {
        //metodo publico que devuelve una lista con los nodos del arbol generico en preorden
        Lista salida = new Lista();
        listarPreordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPreordenAux(NodoGen n, Lista l) {
        if (n != null) {
            //inserta el nodo n el la lista
            l.insertar(n.getElem(), l.longitud() + 1);
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                //llamado recursivo para obtener los nodos hijos izquierdos
                listarPreordenAux(hijo, l);
                //recorre los hermanos derechos del nodo n
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public Lista listarPosorden() {
        //metodo publico que devuelve una lista con los nodos del arbol generico en posorden
        Lista salida = new Lista();
        listarPosordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPosordenAux(NodoGen n, Lista lis) {
        //metodo privado que realiza el recorrido y la incersión a la lista
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                //llamado recursivo para obtener los hijos izquierdos
                listarPosordenAux(hijo, lis);
                //recorre los hermanos derechos del nodo n
                hijo = hijo.getHermanoDerecho();
            }
            //inserta el elemento en la lista
            lis.insertar(n.getElem(), lis.longitud() + 1);
        }
    }

    public Lista listarInorden() {
        //metodo publico que lista un arbol en inorden
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }

    private void listarInordenAux(NodoGen n, Lista ls) {
        if (n != null) {
            //Llamado recursivo con primer hijo de n
            if (n.getHijoIzquierdo() != null) {
                listarInordenAux(n.getHijoIzquierdo(), ls);
            }

            // visita del nodo n
            ls.insertar(n.getElem(), ls.longitud() + 1);

            // llamados recursivos con los otros hijos de n
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPorNiveles() {
        //metodo publico que devuelve una lista con los nodos del arbol generico por niveles
        Lista salida = new Lista();
        listarNivelesAux(this.raiz, salida);
        return salida;
    }

    private void listarNivelesAux(NodoGen n, Lista l) {
        //metodo privado que realiza el recorrido y la incersión a la lista
        Cola q = new Cola();
        NodoGen nodo;
        q.poner(n);
        //inserta el elemento raiz del arbol en la cola
        //repetitiva para insetar los elementos hijos del nodo n
        while (!q.esVacia()) {
            nodo = (NodoGen) q.obtenerFrente();
            q.sacar();
            //inserta el nodo en la lista
            l.insertar(nodo.getElem(), l.longitud() + 1);
            NodoGen hijo = nodo.getHijoIzquierdo();

            while (hijo != null) {
                //repetitiva para los hermanos derechos
                q.poner(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public boolean sonFrontera(Lista unaLista) {
        boolean ver = noSeRepiten(unaLista), exito = false;
        //llamado al metodo noSeRepiten para verificar la lista
        if (ver && this.raiz != null) {
            //si la lista no contiene elementos repetidos y el arbol no es vacio
            exito = sonFronteraAux(this.raiz, unaLista);
        }
        return exito;
    }

    private boolean sonFronteraAux(NodoGen n, Lista l) {
        boolean exito = true;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && exito) {
                //llamado recursivo con los hijos izquierdos de n
                exito = sonFronteraAux(hijo, l);
                //recorrido de los hermanos derechos del nodo n
                hijo = hijo.getHermanoDerecho();
            }
            //condicion si existe un elemento que se encuentre en la lista y no es hoja
            //el metodo privado debe devolver falso (no es frontera)
            if (l.localizar(n.getElem()) != -1 && n.getHijoIzquierdo() != null) {
                exito = false;
            }
        }
        return exito;
    }

    private boolean noSeRepiten(Lista l) {
        //metodo privado que verifica que los elementos de una lista no se repiten
        boolean exito = true;
        int longi = l.longitud();
        int j = 1;
        int i = 2;
        //si la lista es vacia retorna falso
        if (l.esVacia()) {
            exito = false;
        } else {
            //sino, compara cada elemento de la lista
            while (j < longi && exito) {
                if (l.recuperar(i) == l.recuperar(j)) {
                    exito = false;
                }
                i++;
                //se reinicia la busqueda con la posicion incial+1
                if (i > longi) {
                    j++;
                    i = j + 1;
                }
            }
        }
        return exito;
    }

    public void vaciar() {
        //vacia el arbol generico
        this.raiz = null;
    }

    public boolean esVacio() {
        //retorna un booleano que nos dice si el arbol es vacio o no
        return this.raiz == null;
    }
    public boolean verificarCamino(Lista l1) {
        boolean res = false;
        if (this.raiz != null && this.raiz.getElem().equals(l1.recuperar(1))){
            if (l1.longitud() == 1){
                res = true;
            } else {
                l1.eliminar(1);
                res = verificarCaminoAux(this.raiz, l1);
            }
        }
        return res;
    }
    private boolean verificarCaminoAux(NodoGen n, Lista l1) {
        boolean res = false;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && !res) {
                if (hijo.getElem().equals(l1.recuperar(1))) {
                    if (l1.longitud() == 1 && hijo.getHijoIzquierdo() == null){
                        res = true;
                    } else {
                        l1.eliminar(1);
                        res = verificarCaminoAux(hijo, l1);
                        if (!res){
                            l1.insertar(hijo.getElem(), 1);
                            hijo = hijo.getHermanoDerecho();                           
                        }
                    }                   
                } else {
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    return res;

    }
    public Lista entreNiveles(int niv1, int niv2){
        Lista l1 = new Lista();
        entreNivelesAux(this.raiz, niv1, niv2, l1, 0);
        return l1;
    }
    private void entreNivelesAux(NodoGen n, int min, int max, Lista l1, int piso){
        if (n != null && piso <= max) {
            NodoGen hijo = n.getHijoIzquierdo();
            entreNivelesAux(hijo, min, max, l1, piso + 1);
            if (piso >= min && piso <= max) {
                l1.insertar(n.getElem(), l1.longitud() + 1);
            }
            while (hijo != null && piso < max) {
                hijo = hijo.getHermanoDerecho();
                entreNivelesAux(hijo, min, max, l1, piso + 1);
            }    
        }
    }
    public void eliminar(Object elem){
        boolean res = false;
        if(this.raiz.getElem().equals(elem)){
            this.raiz = null;
        } else {
            NodoGen n = obtenerNodoPadre(this.raiz, elem);
            if (n != null){
                if(n.getHijoIzquierdo().getElem().equals(elem)){
                    n.setHijoIzquierdo(n.getHijoIzquierdo().getHermanoDerecho());
                } else {
                    NodoGen aux = n.getHijoIzquierdo();
                    while (aux != null && !res){
                        if (aux.getHermanoDerecho().getElem().equals(elem)){
                            aux.setHermanoDerecho(aux.getHermanoDerecho().getHermanoDerecho());
                            res = true;
                        } else {
                            aux = aux.getHermanoDerecho();
                        }
                    }
                }
            }
        }
    }
    public NodoGen obtenerNodoPadre(NodoGen n, Object elem) {
        NodoGen retorno = null;
        if (this.raiz != null) {
            //si el elemento no es la raiz
            if (!this.raiz.getElem().equals(elem)) {
                //llama al metodo padreAux
                retorno = obtenerNodoPadreAux(this.raiz, elem);
            }
        }
        return retorno;
    }

    private NodoGen obtenerNodoPadreAux(NodoGen n, Object elem) {
        NodoGen res = null;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && res == null) {
                //busca si el elemento del hijo del nodo n es igual al elem
                if (hijo.getElem().equals(elem)) {
                    res = n;
                } else {
                    //si no lo es hace el llamado recursivo
                    res = obtenerNodoPadreAux(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return res;
    }
    public Lista listarHastaNivel(int nivel){
        Lista l1 = new Lista();
        if (this.raiz != null){
            listarHastaNivelAux(this.raiz, nivel, l1, 0);
        }
        return l1;
    }
    private Lista listarHastaNivelAux(NodoGen n, int nivel, Lista l1, int nivActual){
        if (n != null){
            if (nivActual <= nivel){
                l1.insertar(n.getElem(),l1.longitud() + 1);
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null){
                    listarHastaNivelAux(hijo, nivel, l1, nivActual + 1);
                    hijo = hijo.getHermanoDerecho();
                }
            }          
        }
        return l1;
    }
    public boolean verificarCamino2(Lista l1) {
        boolean res = false;
        if (this.raiz != null && this.raiz.getElem().equals(l1.recuperar(1))){
            if (l1.longitud() == 1){
                res = true;
            } else {
                l1.eliminar(1);
                res = verificarCaminoAux2(this.raiz, l1);
            }
        }
        return res;
    }
    private boolean verificarCaminoAux2(NodoGen n, Lista l1) {
        boolean res = false;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && !res) {
                if (hijo.getElem().equals(l1.recuperar(1))) {
                    if (hijo.getHijoIzquierdo().getHijoIzquierdo() == null && l1.longitud() == 1){
                        res = true;
                    } else {
                        Lista lisClon = l1.clone();
                        lisClon.eliminar(1);
                        res = verificarCaminoAux(hijo, lisClon);
                        if (!res){
                            hijo = hijo.getHermanoDerecho();                           
                        }
                    }                   
                } else {
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    return res;
    }
    
    public void hermanoExtremoDerecho(Object letra){
        if (pertenece(letra)){
            boolean exito = false;
            hermanoExtremoDerechoAux(this.raiz, letra, exito);
        } else {
            System.out.println("EL elemento no existe en el arbol");
        }
    }
    private boolean hermanoExtremoDerechoAux(NodoGen n, Object elem, boolean exito){
        if (n != null){           
            if(n.getElem().equals(elem)){
                exito = true;
                NodoGen hijo = n.getHijoIzquierdo();
                if (hijo != null){
                    boolean encontrado = false;
                    Object elemHijo = hijo.getElem();
                    while (hijo.getHermanoDerecho() != null && !encontrado){
                        if(elemHijo.equals(hijo.getHermanoDerecho().getElem())){
                            encontrado = true;
                        } else {
                            hijo = hijo.getHermanoDerecho();
                        }
                    }
                    if (!encontrado) {
                        hijo.setHermanoDerecho(new NodoGen(elemHijo, null, null));
                    }
                }
            } else {              
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && !exito) {
                    exito = hermanoExtremoDerechoAux(hijo, elem, exito);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return exito;
    }
    public void insertarEnPosicion(Object elem, Object padre, int posicion) {
        if (pertenece(padre)){
            NodoGen nodoPadre = obtenerNodo(this.raiz, padre);
            insertarEnPosicionAux(elem, nodoPadre, posicion);                  
        }
    }
    private void insertarEnPosicionAux(Object elem, NodoGen n, int posicion){
        if (n.getHijoIzquierdo() != null){
            if (posicion == 1){
                n.setHijoIzquierdo(new NodoGen(elem,null,n.getHijoIzquierdo()));
            } else {
                int i = 2;
                NodoGen hijo = n.getHijoIzquierdo();
                while (i < posicion && hijo.getHermanoDerecho() != null){
                    hijo = hijo.getHermanoDerecho();
                    i++;
                }
                hijo.setHermanoDerecho(new NodoGen(elem, null, hijo.getHermanoDerecho()));
            }
        } else {
            n.setHijoIzquierdo(new NodoGen(elem, null, null));
        }              
    }
    public boolean verificarCamino3(Lista l1) {
        Lista lclone = l1.clone();
        return verificarCamino3Aux(this.raiz, lclone);
    }

    private boolean verificarCamino3Aux(NodoGen n, Lista l1) {
        boolean exito = false;
        if (n.getElem().equals(l1.recuperar(1)) && l1.longitud() == 1 && n.getHijoIzquierdo() == null) {
            exito = true;
        } else {
            if (n.getElem().equals(l1.recuperar(1))) {
                l1.eliminar(1);
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && !exito) {
                    exito = verificarCamino3Aux(hijo, l1);
                    hijo = hijo.getHermanoDerecho();
                }
                if (!exito) {
                    l1.insertar(n.getElem(), 1);
                }                
            }
        }
        return exito;
    }
    private NodoGen obtenerNodoPadre2(Object elemento){
        NodoGen nodito = null;
        if (this.raiz != null){
            nodito = obtenerNodoPadre2Aux(this.raiz, elemento);
        }
        return nodito;
    }
    private NodoGen obtenerNodoPadre2Aux(NodoGen n, Object elemento){
        NodoGen nodoRet = null;
        if (n != null){
            if (n.getElem().equals(elemento)){
                nodoRet = n;
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && nodoRet == null){
                    nodoRet = obtenerNodoPadre2Aux(hijo, elemento);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return nodoRet;
    }
}

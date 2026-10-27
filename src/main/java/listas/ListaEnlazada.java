package listas;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Lista simplemente enlazada genérica.
 *
 * INSTRUCCIONES PARA EL ESTUDIANTE
 * --------------------------------
 * 1. Cada método marcado con "TODO" lanza UnsupportedOperationException.
 * Reemplace esa línea por su implementación.
 * 2. NO modifique las pruebas (src/test).
 * 3. Resuelva en orden (Etapa 1, 2, 3...): cada etapa usa las anteriores.
 * 4. Solo se permite usar nodos propios (clase Nodo). No use
 * java.util.LinkedList ni ArrayList dentro de esta clase.
 */
public class ListaEnlazada<T> {

    /** Primer nodo de la lista (null cuando la lista está vacía). */
    private Nodo<T> cabeza;

    /** Cantidad de elementos. Debe mantenerse actualizada en cada operación. */
    private int cantidad;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    // =====================================================================
    // ETAPA 1. Estado de la lista
    // =====================================================================

    /** @return true si la lista no tiene elementos. */
    public boolean estaVacia() {
        // TODO Etapa 1: escriba su código aquí
        return cantidad == 0;

    }

    /** @return número de elementos de la lista. */
    public int tamanio() {
        // TODO Etapa 1: escriba su código aquí
        return cantidad;
    }

    // =====================================================================
    // ETAPA 2. Agregar en los extremos
    // =====================================================================

    /** Inserta el dato como nuevo primer elemento. */
    public void agregarAlInicio(T dato) {
        // TODO Etapa 2: cree un Nodo, enlácelo a la cabeza actual
        // y actualice la cabeza y la cantidad.
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.setSiguiente(this.cabeza);
        this.cabeza = nuevoNodo;
        this.cantidad++;
    }

    /** Inserta el dato como nuevo último elemento. */
    public void agregarAlFinal(T dato) {
        // TODO Etapa 2: si la lista está vacía es igual que agregarAlInicio;
        // si no, recorra hasta el último nodo y enlace el nuevo.
        if (estaVacia()) {
            agregarAlInicio(dato);
        } else {
            Nodo<T> puntero = this.cabeza;
            while (puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }

            puntero.setSiguiente(new Nodo<>(dato));
            this.cantidad++;
        }
    }
    // =====================================================================
    // ETAPA 3. Consultar elementos
    // =====================================================================

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerPrimero() {
        // TODO Etapa 3: escriba su código aquí
        if(estaVacia()){
            throw new NoSuchElementException("La lista esta vacia");
        }
        return this.cabeza.getDato();
    }

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerUltimo() {
        // TODO Etapa 3: escriba su código aquí
        if(estaVacia()){
            throw new NoSuchElementException("La lista esta vacia");
        }
        return obtener(this.cantidad-1);
    }
    /**
     * @param indice posición (0 = primer elemento)
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T obtener(int indice) {
        // TODO Etapa 3: valide el índice y recorra la lista 'indice' veces.
        if(indice<0||indice>=this.cantidad){
            throw new IndexOutOfBoundsException("Indice Ivalido: "+ indice);
        }
        Nodo <T> puntero = this.cabeza;
        for(int i =0; i<indice; i++){
            puntero=puntero.getSiguiente();
        }
        return puntero.getDato();
    }

    // =====================================================================
    // ETAPA 4. Búsqueda
    // =====================================================================

    /** @return true si algún elemento es igual (equals) al dato. Admite null. */
    public boolean contiene(T dato) {
        // TODO Etapa 4: use indiceDe o recorra la lista.
        // Cuidado: para comparar use Objects.equals(a, b).
        return indiceDe(dato) != -1;
        
    }

    /** @return posición de la primera aparición del dato, o -1 si no está. */
    public int indiceDe(T dato) {
        // TODO Etapa 4: escriba su código aquí
        Nodo <T> puntero = this.cabeza;
        int indice =0;
        while (puntero!=null){
            if(Objects.equals (puntero.getDato(),dato)){
                return indice;
            }
            puntero=puntero.getSiguiente();
            indice++;
        }
        return -1;
    }

    // =====================================================================
    // ETAPA 5. Insertar y reemplazar en cualquier posición
    // =====================================================================

    /**
     * Inserta el dato en la posición indicada, desplazando los siguientes.
     * Se permite indice == tamanio() (equivale a agregar al final).
     *
     * @throws IndexOutOfBoundsException si indice < 0 o indice > tamanio()
     */
    public void insertar(int indice, T dato) {
        // TODO Etapa 5: casos: indice 0 (inicio), o detenerse en el nodo
        // ANTERIOR a la posición y enlazar allí el nuevo nodo.
        if (indice<0 ||indice>this.cantidad){
            throw new IndexOutOfBoundsException("Indice invalido: "+indice);
        }
        if(indice==0){
            agregarAlInicio(dato);
            return ;
        }
        Nodo <T> anterior = this.cabeza;
        for(int i=0; i<indice-1; i++){
            anterior=anterior.getSiguiente();
        }
        Nodo<T> nuevoNodo=new Nodo<>(dato);
        nuevoNodo.setSiguiente(anterior.getSiguiente());
        anterior.setSiguiente(nuevoNodo);
        this.cantidad++;
    }

    /**
     * Reemplaza el dato de la posición indicada.
     *
     * @return el dato que había antes
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T reemplazar(int indice, T dato) {
        // TODO Etapa 5: escriba su código aquí
        if (indice<0||indice>= this.cantidad){
            throw new IndexOutOfBoundsException("Indice invalido: "+indice);
        }
        Nodo <T> puntero=this.cabeza;
        for(int i =0; i<indice;i++){
            puntero=puntero.getSiguiente();
        }
        T datoAnterior = puntero.getDato();
        puntero.setDato(dato);
        return datoAnterior;
    }

    // =====================================================================
    // ETAPA 6. Eliminar
    // =====================================================================

    /**
     * Elimina y retorna el primer elemento.
     * 
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarPrimero() {
        // TODO Etapa 6: mueva la cabeza al siguiente nodo.
        if(estaVacia()){
            throw new NoSuchElementException("La lista está vacía");
        }
        T datoEliminado = this.cabeza.getDato();
        
        this.cabeza=this.cabeza.getSiguiente();
        this.cantidad--;
        return datoEliminado;
    }

    /**
     * Elimina y retorna el último elemento.
     * 
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarUltimo() {
        // TODO Etapa 6: hay que detenerse en el PENULTIMO nodo.
        // Atención al caso de un solo elemento.
        if(estaVacia()){
            throw new NoSuchElementException("La lista está vacía");
        }
        if(this.cantidad==1){
            return eliminarPrimero();
        }
        Nodo <T> penultimo=this.cabeza;
        while(penultimo.getSiguiente().getSiguiente() != null){
            penultimo=penultimo.getSiguiente();
        }
        T datoEliminado = penultimo.getSiguiente().getDato();
        
        penultimo.setSiguiente(null);
        this.cantidad--;
        return datoEliminado;
    }

    /**
     * Elimina y retorna el elemento de la posición indicada.
     * 
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T eliminar(int indice) {
        // TODO Etapa 6: escriba su código aquí
        if (indice<0||indice>=this.cantidad){
            throw new IndexOutOfBoundsException("Indice invalido: "+indice);
        }
        if (indice==0){
            return eliminarPrimero();
        }
        Nodo <T> anterior = this.cabeza;
        for (int i =0; i <indice-1 ; i++){
            anterior=anterior.getSiguiente();
        }
        Nodo <T> aElminar = anterior.getSiguiente();
        T datoEliminado = aElminar.getDato();
        anterior.setSiguiente(aElminar.getSiguiente());
        this.cantidad--;
        return datoEliminado;
    }

    /**
     * Elimina la primera aparición del dato.
     * 
     * @return true si se eliminó algo, false si el dato no estaba
     */
    public boolean eliminarValor(T dato) {
        // TODO Etapa 6: puede apoyarse en indiceDe y eliminar(int).
        int indice = indiceDe(dato);
        if(indice==-1){
            return false;
        }
        eliminar(indice);
        return true;
    }

    /** Deja la lista vacía. */
    public void vaciar() {
        // TODO Etapa 6: escriba su código aquí
        this.cabeza=null;
        this.cantidad=0;
    }

    // =====================================================================
    // ETAPA 7. Representación en texto
    // =====================================================================

    /**
     * Formato: "[1, 2, 3]". Lista vacía: "[]".
     * Un elemento null se imprime como "null".
     */
    @Override
    public String toString() {
        // TODO Etapa 7: recorra la lista con un StringBuilder.
        StringBuilder  texto = new StringBuilder();
        texto.append("[");
        Nodo <T> puntero = this.cabeza;
        while (puntero!=null){
            texto.append(puntero.getDato());
            if(puntero.getSiguiente()!=null){
                texto.append(", ");
            }
            puntero=puntero.getSiguiente();
        }
        texto.append("]");
        return texto.toString();
    }

    // =====================================================================
    // ETAPA 8. Retos
    // =====================================================================

    /**
     * Invierte la lista EN SITIO: no cree nodos nuevos, solo cambie
     * las referencias 'siguiente' de los nodos existentes.
     */
    public void invertir() {
        // TODO Etapa 8: use tres referencias: anterior, actual, siguiente.
        Nodo<T> anterior = null;
        Nodo <T> actual = this.cabeza;
        
        while (actual!= null) {
            Nodo <T> siguiente = actual.getSiguiente();
            actual.setSiguiente(anterior);
            anterior=actual;
            actual=siguiente;
        }
        this.cabeza=anterior;
    }

    /**
     * Retorna el elemento del medio. Si el tamaño es par, el segundo de los
     * dos centrales (ej.: [1,2,3,4] -> 3). Pista: dos punteros, uno avanza
     * de a 1 y otro de a 2.
     *
     * @throws NoSuchElementException si la lista está vacía
     */
    public T obtenerMedio() {
        // TODO Etapa 8: escriba su código aquí
         if(estaVacia()){
            throw new NoSuchElementException("La lista está vacía");
        }
        Nodo <T> lento = this.cabeza;
        Nodo <T> rapido=this.cabeza;

        while(rapido!=null && rapido.getSiguiente() != null){
            lento=lento.getSiguiente();
            rapido=rapido.getSiguiente().getSiguiente();
        }
        return lento.getDato();
    }

    /**
     * Elimina los elementos repetidos dejando solo la primera aparición
     * de cada uno, conservando el orden. Ej.: [1,2,1,3,2] -> [1,2,3]
     */
    public void eliminarDuplicados() {
        // TODO Etapa 8: escriba su código aquí
        Nodo<T> actual = this.cabeza;
        while (actual!=null){
            Nodo<T> corredor=actual;
            while(corredor.getSiguiente()!=null){
                if (Objects.equals(corredor.getSiguiente().getDato(), actual.getDato())){
                    corredor.setSiguiente((corredor.getSiguiente().getSiguiente()));
                    this.cantidad--;
                }
                else{
                    corredor=corredor.getSiguiente();
                }
            }
            actual=actual.getSiguiente();
        }
    }
}

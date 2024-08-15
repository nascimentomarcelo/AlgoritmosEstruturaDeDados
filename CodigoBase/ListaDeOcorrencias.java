/**
 * Esta classe guarda os numeros das paginas em que uma palavra ocorre.
 * @author Isabel H. Manssour
 */
public class ListaDeOcorrencias {
        
    // Classe interna Node
    private class Node {
        public int numeroDaPagina;
        public Node next;    
        public Node(int n) {
            numeroDaPagina = n;
            next = null;
        }
    }
    
    // Atributos
    private Node head;
    private Node tail;
    private int count;

    // Metodos 
    public ListaDeOcorrencias() {
        head = null;
        tail = null;
        count = 0;
    }
    
    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (head == null);
    }   
    
    /**
     * Retorna o numero de elementos da lista.
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }  
    
    /**
     * Esvazia a lista
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Adiciona um numero de pagina ao final da lista, caso ele ainda
     * nao tenha sido adicionado.
     * @param numPagina número da página a ser adicionado ao final da lista
     * @return true se adicionou no final da lista o numero de pagina  
     * recebido por parametro, e false caso contrario.
     */
    public boolean add(int numPagina)  {
        Node n = new Node(numPagina);
        if (head == null) { // Se a lista esta vazia, adiciona
            head = n;
            tail = n;
        } 
        else if (tail.numeroDaPagina != numPagina) {
            tail.next = n;
            tail = n;
        } 
        else {
            return false; // Se o numero da página ja existe, nao sera adicionado novamente
        }
        count++;
        return true;
    }  
    
    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */    
    public Integer get(int index) {
        // Primeiro verifica se o indice eh valido
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Indice invalido");
        }
        Node aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        return aux.numeroDaPagina;
    }

    /**
     * Retorna true se a lista contem o numero de pagina passado
     * por parametro.
     * @param numPagina o elemento a ser procurado
     * @return true se a lista contem o elemento especificado
     */
    public boolean contains(int numPagina) {
        Node aux = head;
        while (aux != null) {
            if (aux.numeroDaPagina == numPagina) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }    
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node aux = head;
        while (aux != null) {
            s.append(aux.numeroDaPagina);
            if (aux.next != null) {
                s.append(", ");
            }
            aux = aux.next;
        }
        return s.toString();
    }
}
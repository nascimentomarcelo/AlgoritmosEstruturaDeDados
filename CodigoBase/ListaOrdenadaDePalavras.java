/**
 * Esta classe guarda as palavras do indice remissivo em ordem alfabetica.
 * 
 * @author Isabel H. Manssour
 * @author Thomaz Abrantes de O. Martinelli S.
 * @author Tiago Dias Dieterich
 */

public class ListaOrdenadaDePalavras {

    // Classe interna
    private class Palavra {
        public String s;
        public ListaDeOcorrencias listaOcorrencias;
        public Palavra next;

        public Palavra(String str) {
            s = str;
            next = null;
            listaOcorrencias = new ListaDeOcorrencias();
        }

        // Metodos

    }

    // Atributos
    private Palavra first;

    // Metodos
    public ListaOrdenadaDePalavras() {
        this.first = null;
    }

    /**
     * 
     * @param palavra
     *                // Metodo de verificar se uma palavra eh uma stopword
     * @return true se a palavra for uma das stopwords consideradas
     */
    private boolean isStopword(String palavra) {
        // As stopWords consideradas são:
        String[] stopwords = { "a", "about", "above", "across", "after", "afterwards", "again", "against", "all",
                "almost", "alone", "along", "already",
                "also", "although", "always", "am", "among", "amongst", "amoungst", "amount", "an", "and", "another",
                "any", "anyhow", "anyone", "anything", "anyway",
                "anywhere", "are", "around", "as", "at", "back", "be", "became", "because", "become", "becomes",
                "becoming", "been", "before", "beforehand", "behind",
                "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom", "but", "by",
                "call", "can", "cannot", "cant", "co", "computer",
                "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during",
                "each", "eg", "eight", "either", "eleven", "else",
                "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere",
                "except", "few", "fifteen", "fify", "fill",
                "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front",
                "full", "further", "get", "give", "go", "had",
                "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon",
                "hers", "herse", "him", "himse", "his", "how", "however",
                "hundred", "i", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itse",
                "keep", "last", "latter", "latterly", "least", "less",
                "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most",
                "mostly", "move", "much", "must", "my", "myse", "name",
                "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor",
                "not", "nothing", "now", "nowhere", "of", "off",
                "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours",
                "ourselves", "out", "over", "own", "part", "per",
                "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems",
                "serious", "several", "she", "should", "show", "side",
                "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime",
                "sometimes", "somewhere", "still", "such", "system",
                "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there",
                "thereafter", "thereby", "therefore", "therein",
                "thereupon", "these", "they", "thick", "thin", "third", "this", "those", "though", "three", "through",
                "throughout", "thru", "thus", "to", "together",
                "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon",
                "us", "very", "via", "was", "we", "well", "were",
                "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby",
                "wherein", "whereupon", "wherever", "whether", "which",
                "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within",
                "without", "would", "yet", "you", "your", "yours",
                "yourself", "yourselves" };

        // Verifica se a palavra esta na lista de stopwords
        for (String stopword : stopwords) {
            if (palavra.equalsIgnoreCase(stopword)) { // ignora diferencas maiusculas/minusculas
                return true; // A palavra eh uma stopword
            }
        }
        return false; // A palavra nao eh uma stopword
    }

    /**
     * Metodo para inserir a ocorrencia da palavra na lista e a pagina onde ocorreu
     * Chama o metodo isStopword para evitar adicionar uma stopword na lista
     * 
     * @param s
     * @param pagina
     */
    public void addPalavra(String s, int pagina) {
        if (isStopword(s)) {
            return; // Se for uma stopword, nao a adiciona ao indice
        }

        s = s.toLowerCase(); // Antes de adicionar a palavra, ele eh convertida em letras minusculas

        // Os caracteres especiais devem ser removidos
        s = s.replaceAll("\\t", " ");
        s = s.replaceAll(",", "");
        s = s.replaceAll("\\.", "");
        s = s.replaceAll("\\?", "");
        s = s.replaceAll("\\!", "");

        Palavra novaPalavra = new Palavra(s);
        if (first == null) { // Se a lista estiver vazia
            first = novaPalavra; // A palavra sera a primeira a ser adicionada na lista
        } else {
            Palavra anterior = null;
            Palavra atual = first;

            while (atual != null && s.compareTo(atual.s) > 0) {
                anterior = atual;
                atual = atual.next;
            }

            if (atual == null || !s.equals(atual.s)) {
                novaPalavra.next = atual;

                if (anterior == null) {
                    first = novaPalavra;
                } else {
                    anterior.next = novaPalavra;
                }
            } else {
                // Se a palavra ja existe, verifica se a pagina atual ja esta registrada
                if (!atual.listaOcorrencias.contains(pagina)) {
                    atual.listaOcorrencias.add(pagina);
                }
            }
        }
    }

    /**
     * Metodo para exibir todo o índice remissivo em ordem alfabética
     */
    public void exibirIndiceRemissivoEmOrdemAlfabetica() {
        Palavra atual = first;

        while (atual != null) {
            if (!isStopword(atual.s)) 
            System.out.print(atual.s + ": ");
            System.out.print(atual.listaOcorrencias);
            System.out.println();
            atual = atual.next;
        }
    }

    /**
     * Metodo para calcular o percentual das stopwords encontradas em relacao ao
     * total de palavras
     * Chama o metodo isStopword
     * 
     * @return o percentual das stopwords
     */
    /* 
    public double calcularPercentualStopwords() {
        int totalPalavras = 0;
        int stopwords = 0;

        Palavra atual = first;

        while (atual != null) {
            totalPalavras++;
            if (isStopword(atual.s)) {
                stopwords++;
            }
            atual = atual.next;
        }

        if (totalPalavras == 0) {
            return 0.0; // Evita divisão por zero
        }

        return (stopwords * 100.0) / totalPalavras;
    }
    */
    public double calcularPercentualStopwords() {
        int totalPalavras = 0;
        int stopwords = 0;
    
        Palavra atual = first;
    
        while (atual != null) {
            totalPalavras++;
            if (isStopword(atual.s)) {
                stopwords++;
            }
            atual = atual.next;
        }
    
        if (totalPalavras == 0) {
            return 0.0; // Evita divisão por zero
        }
    
        return (stopwords * 100.0) / totalPalavras;
    }
    

    /**
     * Metodo para encontrar a palavra mais frequente, com maior número de
     * ocorrências
     * 
     * @return a palavra que mais aparece no texto
     */
    /* 
    public String encontrarPalavraMaisFrequente() {
        Palavra atual = first;
        String palavraMaisFrequente = null;
        int qtdMaxOcorrencias = 0;

        while (atual != null) {
            int ocorrencias = atual.listaOcorrencias.size();
            if (ocorrencias > qtdMaxOcorrencias) {
                qtdMaxOcorrencias = ocorrencias;
                palavraMaisFrequente = atual.s;
            }
            atual = atual.next;
        }

        return palavraMaisFrequente;
    }
    */

    public String encontrarPalavraMaisFrequente() {
        Palavra atual = first;
        String palavraMaisFrequente = null;
        int qtdMaxOcorrencias = 0;
    
        while (atual != null) {
            int ocorrencias = atual.listaOcorrencias.size();
            if (ocorrencias > qtdMaxOcorrencias) {
                qtdMaxOcorrencias = ocorrencias;
                palavraMaisFrequente = atual.s;
            }
            atual = atual.next;
        }
    
        if (palavraMaisFrequente != null && !palavraMaisFrequente.isEmpty()) {
            return "A palavra mais frequente é " + palavraMaisFrequente + " (" + qtdMaxOcorrencias + " ocorrências).";
        } else {
            return "Nenhuma palavra mais frequente encontrada.";
        }
    }

    /**
     * Metodo para pesquisar palavras
     * O usuário informa uma palavra e o programa lista os números das páginas em
     * que a palavra ocorre
     * 
     * @param palavra
     *                Se nao encontrar a palavra, uma mensagem eh enviada
     * 
     */
    public void pesquisarPalavra(String palavra) {
        Palavra atual = first;

        while (atual != null) {
            if (atual.s.equals(palavra)) {
                System.out.println("Palavra: " + palavra);
                System.out.println("Ocorrências: " + atual.listaOcorrencias);
                return; // Encerra a pesquisa quando a palavra é encontrada
            }
            atual = atual.next;
        }

        System.out.println("Palavra não encontrada no índice remissivo.");
    }
}
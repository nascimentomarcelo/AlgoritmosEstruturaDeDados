import java.util.*;
import java.util.Scanner;

/**
 * Classe que inicializa a execução da aplicacao.
 * 
 * @author Isabel H. Manssour
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int nLinha = 0;
        int nPagina = 0;

        ArquivoTexto arquivo = new ArquivoTexto(); // objeto que gerencia o arquivo
        LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
        String l;
        ListaOrdenadaDePalavras indiceRemissivo = new ListaOrdenadaDePalavras();

        // arquivo.open("Alice.txt");
        arquivo.open("Alice.txt");

        do // laco que passa em cada linha do arquivo
        {
            l = arquivo.getNextLine();
            if (l == null) // acabou o arquivo?
                break;
            nLinha++; // conta mais uma linha lida do arquivo
            if (nLinha == 40) // chegou ao fim da pagina?
            {
                nLinha = 0;
                nPagina++;
            }
            // System.out.println("Linha " + nLinha + ":");

            linha.setLine(l); // define o texto da linha
            do // laco que passa em cada palavra de uma linha
            {
                String palavra = linha.getNextWord(); // obtem a proxima palavra da linha
                if (palavra == null)// acabou a linha
                {
                    break;
                }
                indiceRemissivo.addPalavra(palavra, nPagina);
                // System.out.println("-" + palavra + "-");
            } while (true);

        } while (true);
        arquivo.close();

        while (true) {
            int op = menu();
            if (op == 5) {
                break;
            }
            switch (op) {
                case 1:
                    System.out.println("Indice remissivo em ordem alfabetica:");
                    indiceRemissivo.exibirIndiceRemissivoEmOrdemAlfabetica();
                    break;

                case 2:
                    double percentualStopwords = indiceRemissivo.calcularPercentualStopwords();
                    System.out.println("Percentual de Stopwords: " + percentualStopwords + "%");
                    break;

                case 3:
                    String palavraMaisFrequente = indiceRemissivo.encontrarPalavraMaisFrequente();
                    System.out.printf("A palavra mais frequente é %s. \n", palavraMaisFrequente);
                    break;

                case 4:
                    System.out.println("Digite a palvra a ser pesquisada:");
                    String palavra = input.nextLine();
                    indiceRemissivo.pesquisarPalavra(palavra);
                    break;
            }
        }
        input.close();
    }

    /*
     * // MENU
     * public static int menu() {
     * Scanner in = new Scanner(System.in);
     * try {
     * System.out.println("Qual a opção desejada?");
     * System.out.println("1: Exibir todo o indice remissivo");
     * System.out.println("2: Exibir percententual de stopwords do texto");
     * System.out.println("3: Encontrar palavra mais frequente");
     * System.out.println("4: Pesquisar palavra");
     * System.out.println("5: Encerrar programa");
     * int opcao = in.nextInt();
     * return opcao;
     * } finally {
     * in.close();
     * }
     * }
     */

    public static int menu() {
        Scanner in = new Scanner(System.in);
        int opcao = 0;

        while (true) {
            try {
                System.out.println("Qual a opção desejada?");
                System.out.println("1: Exibir todo o índice remissivo");
                System.out.println("2: Exibir percentual de stopwords do texto");
                System.out.println("3: Encontrar palavra mais frequente");
                System.out.println("4: Pesquisar palavra");
                System.out.println("5: Encerrar programa");
                opcao = in.nextInt();

                if (opcao >= 1 && opcao <= 5) {
                    break;
                } else {
                    System.out.println("Opção inválida. Por favor, escolha uma opção de 1 a 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, escolha uma opção de 1 a 5.");
                in.nextLine(); 
            } 
        }

        return opcao;
    }
}
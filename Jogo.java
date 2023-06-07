import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Jogo {
    private int poderJoia;
    private Cidade cidadeAtual;
    private int limiarJoia;
    private int moedasTransporte;
    private int emMissao;
	private String missao;
    private Map<String, Cidade> cidades;
    Scanner scan = new Scanner(System.in);

    public Jogo(){
        this.poderJoia = 0;
        this.cidadeAtual = null;
        this.limiarJoia = 7;
        this.moedasTransporte = 3;
        this.emMissao = 0;
        this.cidades = new HashMap<>();
    }

    public void adicionarCidade(String nomeCidade, int poderDaCidade){
        Cidade cidade = new Cidade(nomeCidade, poderDaCidade);
        cidades.put(nomeCidade, cidade);
    }

    public void adicionarVizinho(String nomeCidadeOrigem, String nomeCidadeDestino){
        Cidade cidadeOrigem = cidades.get(nomeCidadeOrigem);
        Cidade cidadeDestino = cidades.get(nomeCidadeDestino);
        
        if(cidadeOrigem != null && cidadeDestino != null){
            cidadeOrigem.adicionarVizinho(cidadeDestino);
        }
    }

    public void definirCidadeAtual(String nomeCidade){
        if(cidades.containsKey(nomeCidade)){
            cidadeAtual = cidades.get(nomeCidade);
        }else{
            System.out.println("Cidade nao encontrada");
        }
    }

    public void missaoLocal(int numeroMissao){
        if(numeroMissao == 1){
            System.out.println("\nMISSAO LOCAL\nVá até a cidade de Grand Duchy of Smalia e receba as luvas do poder.");
            System.out.println("Recompença por aceitar: +4 moedas de transporte");
            System.out.println("Recompensa por Concluir: +2 Moedas de Transporte e o limiar de poder da jóia aumenta em 2 pontos\nDeseja aceitar?[s/n]");
            String escolha = scan.nextLine();
            if(escolha.equals("s")){
                System.out.println("Voce recebeu 4 moedas de transporte");
                this.missao = "Vá até a cidade de Grand Duchy of Smalia e receba as luvas do poder.";
                moedasTransporte += 4;
            }
        }else if(numeroMissao == 2){
            System.out.println("\nMISSAO LOCAL\nVá até a cidade de Principality of Kasya e receba as botas do poder.");
            System.out.println("Recompença por aceitar: +6 moedas de transporte");
            System.out.println("Recompensa por Concluir: +3 Moedas de Transporte e o limiar de poder da jóia aumenta em 1 ponto\nDeseja aceitar?[s/n]");
            String escolha = scan.nextLine();
            if(escolha.equals("s")){
                System.out.println("Voce recebeu 6 moedas de transporte");
                this.missao = "Vá até a cidade de Principality of Kasya e receba as botas do poder.";
                moedasTransporte += 6;
            }
        }else if(numeroMissao == 3){
            System.out.println("\nMISSAO LOCAL\nVá até Ubud e recebe a Glória dos Retornados.");
            System.out.println("Recompença por aceitar: +1 moeda de transporte");
            System.out.println("Recompensa por Concluir: +10 Moedas de Transporte e o limiar de poder da jóia diminui em 4 pontos\nDeseja aceitar?[s/n]");
            String escolha = scan.nextLine();
            if(escolha.equals("s")){
                System.out.println("Voce recebeu 1 moeda de transporte");
                this.missao = "Vá até Ubud e recebe a Glória dos Retornados.";
                moedasTransporte += 1;
            }
        }
    }

    public void concluirMissao(int numeroMissao){
        if(numeroMissao == 1){
            System.out.println("Voce recebeu 2 moedas de transporte\nVoce recebeu as luvas do poder, com isso o limiar de poder da sua joia foi aumentado em 2 pontos");
            emMissao = 0;
            missao = "";
        }else if(numeroMissao == 2){
            System.out.println("Voce recebeu 3 moedas de transporte\nVoce recebeu as botas do poder, com isso o limiar de poder da sua joia foi aumentado em 1 ponto");
            emMissao = 0;
            missao = "";
        }else if(numeroMissao == 3){
            System.out.println("Voce conquistou a Gloria dos Retornados, com isso o limiar de poder da sua joia foi reduzido em 4 pontos\nVoce recebeu 10 moedas de transporte");
            emMissao = 0;
            missao = "";
        }
    }

    public void viajar(String nomeCidade){
        if(cidadeAtual != null){
            Cidade cidadeDestino = cidades.get(nomeCidade);
            if(cidadeAtual.getVizinhos().contains(cidadeDestino)){
                int poderGanho = cidadeDestino.getPoderDaCidade();
                poderJoia += poderGanho;
                cidadeAtual = cidadeDestino;

                if(poderJoia < 0){
                    poderJoia = 0;
                }

                if(poderJoia > limiarJoia){
                    System.out.println("Maxwell foi de caixa! A joia sobrecarregou!");
                    System.exit(0);
                }

                moedasTransporte--;
                if(moedasTransporte < 0){
                    System.out.println("Maxwell foi de arrasta! Acabaram as moedas");
                    System.exit(0);
                }

                if(nomeCidade.equals("Kingdom of Kalb")){
                    missaoLocal(1);
                }
                if(nomeCidade.equals("Defalsia")){
                    missaoLocal(2);
                }
                if(nomeCidade.equals("Vunese Empire")){
                    missaoLocal(3);
                }
                if(emMissao > 0){
                    if(nomeCidade.equals("Grand Duchy of Smalia") && emMissao == 1){
                        concluirMissao(1);
                    }
                    if(nomeCidade.equals("Principality of Kasya") && emMissao == 2){
                        concluirMissao(2);
                    }
                    if(nomeCidade.equals("Ubud") && emMissao == 3){
                        concluirMissao(3);
                    }
                }
            }
        }
    }





    public static void main(String[] args) {
        Jogo jogo = new Jogo();

        jogo.adicionarCidade("Ubud", 0);
        jogo.adicionarCidade("Kingdom of Legmod", 2);
        jogo.adicionarCidade("Principality of Nekikh", 1);
        jogo.adicionarCidade("Principality of Gritesthr", 5);
        jogo.adicionarCidade("Protectorate of Dogrove", 3);
        jogo.adicionarCidade("Vunese Empire", 0);

        jogo.adicionarVizinho("Ubud", "Kingdom of Legmod");
        jogo.adicionarVizinho("Kingdom of Legmod", "Ubud");
        jogo.adicionarVizinho("Ubud", "Principality of Nekikh");
        jogo.adicionarVizinho("Kingdom of Legmod", "Principality of Gritesthr");
        jogo.adicionarVizinho("Principality of Nekikh", "Protectorate of Dogrove");

        jogo.moedasTransporte = 0;
        jogo.emMissao = 1;
        jogo.definirCidadeAtual("Kingdom of Legmod");

        System.out.println(jogo.cidadeAtual.getNome());

        jogo.viajar("Ubud");

        
    }
}
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Jogo {
    private int poderJoia;
    private Cidade cidadeAtual;
    private int limiarJoia;
    private int moedasTransporte;
    private int emMissao;
    private String missao;
    private int nivel;
    private Map<String, Cidade> cidades;
    Scanner scan = new Scanner(System.in);

    public Jogo() {
        this.poderJoia = 0;
        this.cidadeAtual = null;
        this.limiarJoia = 7;
        this.moedasTransporte = 3;
        this.emMissao = 0;
        this.missao = "";
        this.nivel = 1;
        this.cidades = new HashMap<>();
    }

    //Funçao para lipar o terminal durante os acontecimentos do jogo
    public static void clearTerminal() {
        //pesquisei por fora pra melhorar a experiencia do jogo
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //Funçao para adicionar cada cidade ao mapa
    public void adicionarCidade(String nomeCidade, int poderDaCidade) {
        Cidade cidade = new Cidade(nomeCidade, poderDaCidade);
        cidades.put(nomeCidade, cidade);
    }

    //Funçao para adicionar os vizinhos de cada cidade
    public void adicionarVizinho(String nomeCidadeOrigem, String nomeCidadeDestino) {
        Cidade cidadeOrigem = cidades.get(nomeCidadeOrigem);
        Cidade cidadeDestino = cidades.get(nomeCidadeDestino);

        if (cidadeOrigem != null && cidadeDestino != null) {
            cidadeOrigem.adicionarVizinho(cidadeDestino);
            cidadeDestino.adicionarVizinho(cidadeOrigem);
        }
    }

    //Funçao para devinir a cidade atual (criei para testes, porem decidi deixar)
    public void definirCidadeAtual(String nomeCidade) {
        if (cidades.containsKey(nomeCidade)) {
            cidadeAtual = cidades.get(nomeCidade);
        } else {
            System.out.println("Cidade nao encontrada");
        }
    }

    //Funçao para mostar a lista de cidades do mapa para serem acessadas por numero
    public void mostrarCidades() {
        int numero = 1;
        for (Map.Entry<String, Cidade> entry : cidades.entrySet()) {
            String nomeCidade = entry.getKey();
            Cidade cidade = entry.getValue();
            System.out.println(numero + ". " + nomeCidade);
            numero++;
        }
    }

    //Funçao para acessar uma cidade pela numeraçao da lista anterior
    public Cidade escolherCidadePorNumero(int numeroCidade) {
        int numero = 1;

        for (Map.Entry<String, Cidade> entry : cidades.entrySet()) {
            if (numero == numeroCidade) {
                return entry.getValue();
            }
            numero++;
        }

        return null;
    }

    //Funçao para destacar uma missao caso exista na cidade atual
    public void missaoLocal(int numeroMissao) {
        if (numeroMissao == 1) {
            System.out.println("\nMISSAO LOCAL\nVá até a cidade de Grand Duchy of Smalia e receba as luvas do poder.");
            System.out.println("Recompença por aceitar: +4 moedas de transporte");
            System.out.println(
                    "Recompensa por Concluir: +2 Moedas de Transporte e o limiar de poder da jóia aumenta em 2 pontos\nDeseja aceitar?[s/n]");
            String escolha = scan.nextLine();
            if (escolha.equals("s")) {
                System.out.println("Voce recebeu 4 moedas de transporte");
                this.missao = "Vá até a cidade de Grand Duchy of Smalia e receba as luvas do poder.";
                emMissao = 1;
                moedasTransporte += 4;
            }
        } else if (numeroMissao == 2) {
            System.out.println("\nMISSAO LOCAL\nVá até a cidade de Principality of Kasya e receba as botas do poder.");
            System.out.println("Recompença por aceitar: +6 moedas de transporte");
            System.out.println(
                    "Recompensa por Concluir: +3 Moedas de Transporte e o limiar de poder da jóia aumenta em 1 ponto\nDeseja aceitar?[s/n]");
            String escolha = scan.nextLine();
            if (escolha.equals("s")) {
                System.out.println("Voce recebeu 6 moedas de transporte");
                this.missao = "Vá até a cidade de Principality of Kasya e receba as botas do poder.";
                emMissao = 2;
                moedasTransporte += 6;
            }
        } else if (numeroMissao == 3) {
            System.out.println("\nMISSAO LOCAL\nVá até Ubud e recebe a Glória dos Retornados.");
            System.out.println("Recompença por aceitar: +1 moeda de transporte");
            System.out.println(
                    "Recompensa por Concluir: +10 Moedas de Transporte e o limiar de poder da jóia diminui em 4 pontos\nDeseja aceitar?[s/n]");
            String escolha = scan.nextLine();
            if (escolha.equals("s")) {
                System.out.println("Voce recebeu 1 moeda de transporte");
                this.missao = "Vá até Ubud e recebe a Glória dos Retornados.";
                emMissao = 3;
                moedasTransporte += 1;
            }
        }
    }

    //Funçao para destacar o fim do jogo ao chegar em Nargumun
    public void theEnd() {
        clearTerminal();
        infos();
        System.out.println("\n\n");
        if (moedasTransporte > 10) {
            System.out.println(
                    "Apos uma jornada ardua e repleta de perigos, \nMaxwell finalmente chega a Nargumun, \ncarregando consigo "
                            + moedasTransporte
                            + " preciosas moedas. \nSeu feito extraordinario e reconhecido por todo o reino, \ne ele e coroado como o grande Rei de Nargumun. \nOs habitantes celebram sua chegada triunfal, \nprontos para seguir a lideranca sabia e corajosa de seu novo monarca.");
            System.out.println("\n\nfim de jogo, obrigado por jogar.");
        } else if (moedasTransporte >= 4) {
            System.out.println(
                    "Apos enfrentar diversos desafios e superar grandes obstaculos, \nMaxwell finalmente chega a Nargumun com suas "
                            + moedasTransporte
                            + " moedas de transporte restantes. \nSua perseveranca e habilidade nao passam despercebidas, \ne ele e honrado com o titulo de Lorde de Nargumun. \nO povo, admirado por sua coragem, oferece-lhe respeito e lealdade, \nansiosos para seguir os passos de seu nobre governante.");
            System.out.println("\n\nfim de jogo, obrigado por jogar.");
        } else {
            System.out.println(
                    "Apos uma exaustiva jornada, \nMaxwell chega a Nargumun com o coracao pesado e apenas algumas moedas remanescentes. \nSeus esforcos heroicos nao foram suficientes para acumular as riquezas necessarias, \ne a amarga realidade da derrota se instala. \nSem a conquista desejada, Maxwell e recebido pelo povo de Nargumun com olhares de compaixao misturados com decepcao. \nEle e aceito apenas como um servo da coroa, \num lembrete constante de sua falha em atingir as expectativas. \nO caminho a sua frente e incerto, \ne a sombra da derrota o acompanha enquanto ele enfrenta as consequencias de suas escolhas.");
            System.out.println("\n\nfim de jogo, obrigado por jogar.");
        }
        System.exit(0);
    }

    //Funçao para destacar a conclusao da missao atual ao chegar na cidade de conclusao
    public void concluirMissao(int numeroMissao) {
        if (numeroMissao == 1) {
            System.out.println(
                    "\nVoce recebeu 2 moedas de transporte\nVoce recebeu as luvas do poder, com isso o limiar de poder da sua joia foi aumentado em 2 pontos");
            moedasTransporte += 2;
            limiarJoia += 2;
            nivel += 2;
            emMissao = 0;
            missao = "";
        } else if (numeroMissao == 2) {
            System.out.println(
                    "\nVoce recebeu 3 moedas de transporte\nVoce recebeu as botas do poder, com isso o limiar de poder da sua joia foi aumentado em 1 ponto");
            moedasTransporte += 3;
            limiarJoia++;
            nivel += 2;
            emMissao = 0;
            missao = "";
        } else if (numeroMissao == 3) {
            System.out.println(
                    "\nVoce conquistou a Gloria dos Retornados, com isso o limiar de poder da sua joia foi reduzido em 4 pontos\nVoce recebeu 10 moedas de transporte");
            moedasTransporte += 10;
            limiarJoia -= 4;
            nivel += 2;
            emMissao = 0;
            missao = "";
        }
    }

    //Funçao para fazer viagens
    public void viajar(String nomeCidade) {
        if (cidadeAtual != null) {
            Cidade cidadeDestino = cidades.get(nomeCidade);
            if (cidadeAtual.getVizinhos().contains(cidadeDestino)) {
                int poderGanho = cidadeDestino.getPoderDaCidade();
                Cidade temp = cidadeAtual;
                poderJoia += poderGanho;
                cidadeAtual = cidadeDestino;
                moedasTransporte--;
                nivel++;

                if (poderJoia < 0) {
                    poderJoia = 0;
                }

                if (poderJoia > limiarJoia) {
                    System.out.println("A joia sobrecarregou! Maxwell foi desintegrado");
                    System.exit(0);
                }

                if (moedasTransporte < 0) {
                    System.out.println("Acabaram as moedas! Maxwell virou um escravo de " + temp.getNome());
                    System.exit(0);
                }

                if (nomeCidade.equals("Nargumun")) {
                    theEnd();
                    return;
                }

                mercador();

                if (emMissao == 0) {
                    if (nomeCidade.equals("Kingdom of Kalb")) {
                        missaoLocal(1);
                    }
                    if (nomeCidade.equals("Defalsia")) {
                        missaoLocal(2);
                    }
                    if (nomeCidade.equals("Vunese Empire")) {
                        missaoLocal(3);
                    }
                }

                if (emMissao > 0) {
                    if (nomeCidade.equals("Grand Duchy of Smalia") && emMissao == 1) {
                        concluirMissao(1);
                    }
                    if (nomeCidade.equals("Principality of Kasya") && emMissao == 2) {
                        concluirMissao(2);
                    }
                    if (nomeCidade.equals("Ubud") && emMissao == 3) {
                        concluirMissao(3);
                    }
                }

            }
        }
    }

    //Funçao de controle do mercador
    public void mercador() {
        clearTerminal();
        infos();
        System.out.println("\n\nOla estranho...");
        System.out.println("De onde vens? ");
        cidadeAtual.mostrarVizinhosEnumerados();
        int escolha1 = scan.nextInt();
        scan.nextLine();
        String vemDe = cidadeAtual.escolherVizinho(escolha1);

        System.out.println("Para onde vai? ");
        cidadeAtual.mostrarVizinhosEnumerados();
        int escolha2 = scan.nextInt();
        scan.nextLine();
        String vaiPara = cidadeAtual.escolherVizinho(escolha2);

        System.out.print("Quanto possui? ");
        int moedasInformadas = scan.nextInt();
        scan.nextLine();
        if (moedasInformadas != moedasTransporte) {
            clearTerminal();
            System.out.println("\nNao adianta mentir amigo, eu sei de tudo");
            System.out.println("Voce perdeu 2 moedas de transporte por mentir ao mercador\n");
            moedasTransporte -= 2;
            return;
        }

        System.out.print("Deseja negociar? [s/n]");
        String resposta = scan.nextLine();

        int distancia = calcularDistancia(vemDe, vaiPara);

        if (moedasTransporte < 5 && distancia < 3 && resposta.equalsIgnoreCase("s")) {
            clearTerminal();
            System.out.println("\nNegocio fechado...\nEu levo 1 moeda e voce ganha mais 1 de limiar!");
            limiarJoia++;
            moedasTransporte--;
        } else if (moedasTransporte < 5 && distancia < 3 && resposta.equalsIgnoreCase("n")) {
            clearTerminal();
            System.out.println("\nPessima ideia amigo...\nVocê perdeu 1 moeda!");
            moedasTransporte--;
        } else if (moedasTransporte < 5 && distancia >= 3 && resposta.equalsIgnoreCase("s")) {
            clearTerminal();
            System.out.println("\nNegocio fechado...\nEu levo 1 moeda e voce ganha mais 2 de limiar!");
            moedasTransporte--;
            limiarJoia += 2;
        } else if (moedasTransporte < 5 && distancia >= 3 && resposta.equalsIgnoreCase("n")) {
            clearTerminal();
            System.out.println("\nBoa escolha amigo...\nVocê leva 2 moedas!");
            moedasTransporte += 2;
        } else if (moedasTransporte >= 5 && distancia < 3 && resposta.equalsIgnoreCase("s")) {
            clearTerminal();
            System.out.println("\nNegocio fechado...\nEu levo 3 moedas e voce ganha mais 2 de limiar!");
            moedasTransporte -= 3;
            limiarJoia += 2;
        } else if (moedasTransporte >= 5 && distancia < 3 && resposta.equalsIgnoreCase("n")) {
            clearTerminal();
            System.out.println("\nPessima ideia amigo...\nVou levar 2 moedas!");
            moedasTransporte -= 2;
        } else if (moedasTransporte >= 5 && distancia >= 3 && resposta.equalsIgnoreCase("s")) {
            clearTerminal();
            System.out.println("\nNegocio fechado...\nEu levo 1 moeda e voce ganha mais 3 de limiar!");
            moedasTransporte--;
            limiarJoia += 3;
        } else if (moedasTransporte >= 5 && distancia >= 3 && resposta.equalsIgnoreCase("n")) {
            clearTerminal();
            System.out.println("\nPessima ideia amigo...\nVou levar 3 moedas!");
            moedasTransporte -= 3;
            limiarJoia += 2;
        }
    }

    //Funçao para calcular a distancia de uma cidade para otura(sendo distancia o poder somado do trajeto)
    public int calcularDistancia(String nomeCidadeOrigem, String nomeCidadeDestino) {
        if (!cidades.containsKey(nomeCidadeOrigem) || !cidades.containsKey(nomeCidadeDestino)) {
            return -1;
        }

        Cidade cidadeOrigem = cidades.get(nomeCidadeOrigem);
        Cidade cidadeDestino = cidades.get(nomeCidadeDestino);

        if (cidadeOrigem.equals(cidadeDestino)) {
            return cidadeOrigem.getPoderDaCidade();
        }

        Queue<Cidade> fila = new LinkedList<>();
        Set<Cidade> visitadas = new HashSet<>();

        fila.offer(cidadeOrigem);
        visitadas.add(cidadeOrigem);

        while (!fila.isEmpty()) {
            Cidade cidadeAtual = fila.poll();

            for (Cidade vizinho : cidadeAtual.getVizinhos()) {
                if (!visitadas.contains(vizinho)) {
                    visitadas.add(vizinho);
                    vizinho.setDistancia(cidadeAtual.getDistancia() + vizinho.getPoderDaCidade());
                    fila.offer(vizinho);

                    if (vizinho.equals(cidadeDestino)) {
                        return vizinho.getDistancia();
                    }
                }
            }
        }

        return -1;
    }

    //Funçao para exibir as informaçoes do jogador na tela
    public void infos() {
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("   Nome: Maxwell             Raça: Humano             Nivel: " + nivel);
        System.out.println("\n   Cidade Atual: " + cidadeAtual.getNome() + "\n   Poder da joia: " + poderJoia
                + "\n   Limiar da joia: " + limiarJoia + "\n   Moedas: " + moedasTransporte);
        System.out.println("   Missao Atual: " + missao);
    }

    //Funçao para iniciar o jogo
    public void start() {
        adicionarCidade("Ubud", 0);
        adicionarCidade("Kingdom of Legmod", 2);
        adicionarCidade("Principality of Nekikh", 1);
        adicionarCidade("Principality of Gritesthr", 5);
        adicionarCidade("Protectorate of Dogrove", 3);
        adicionarCidade("Kingdom of Lastwatch", -2);
        adicionarCidade("Grand Duchy of Smalia", 1);
        adicionarCidade("Kingdom of Oldcalia", 4);
        adicionarCidade("Kingdom of Kalb", 2);
        adicionarCidade("Aymar League", 1);
        adicionarCidade("Defalsia", -3);
        adicionarCidade("Vunese Empire", 0);
        adicionarCidade("Principality of Karhora", -2);
        adicionarCidade("Chandir Sultanate", 1);
        adicionarCidade("Bun", 5);
        adicionarCidade("Principality of Kasya", -7);
        adicionarCidade("Nargumun", 0);

        adicionarVizinho("Ubud", "Kingdom of Legmod");
        adicionarVizinho("Ubud", "Principality of Nekikh");

        adicionarVizinho("Kingdom of Legmod", "Principality of Nekikh");
        adicionarVizinho("Kingdom of Legmod", "Protectorate of Dogrove");
        adicionarVizinho("Kingdom of Legmod", "Kingdom of Oldcalia");
        adicionarVizinho("Kingdom of Legmod", "Principality of Gritesthr");

        adicionarVizinho("Principality of Nekikh", "Principality of Gritesthr");

        adicionarVizinho("Principality of Gritesthr", "Protectorate of Dogrove");
        adicionarVizinho("Principality of Gritesthr", "Kingdom of Lastwatch");

        adicionarVizinho("Protectorate of Dogrove", "Kingdom of Lastwatch");
        adicionarVizinho("Protectorate of Dogrove", "Kingdom of Oldcalia");

        adicionarVizinho("Kingdom of Lastwatch", "Grand Duchy of Smalia");

        adicionarVizinho("Grand Duchy of Smalia", "Kingdom of Oldcalia");

        adicionarVizinho("Kingdom of Oldcalia", "Defalsia");
        adicionarVizinho("Kingdom of Oldcalia", "Aymar League");
        adicionarVizinho("Kingdom of Oldcalia", "Kingdom of Kalb");

        adicionarVizinho("Defalsia", "Aymar League");

        adicionarVizinho("Aymar League", "Principality of Karhora");
        adicionarVizinho("Aymar League", "Nargumun");
        adicionarVizinho("Aymar League", "Bun");
        adicionarVizinho("Aymar League", "Chandir Sultanate");
        adicionarVizinho("Aymar League", "Vunese Empire");
        adicionarVizinho("Aymar League", "Kingdom of Kalb");

        adicionarVizinho("Principality of Karhora", "Nargumun");

        adicionarVizinho("Nargumun", "Bun");

        adicionarVizinho("Kingdom of Kalb", "Vunese Empire");

        adicionarVizinho("Chandir Sultanate", "Vunese Empire");
        adicionarVizinho("Chandir Sultanate", "Bun");
        adicionarVizinho("Chandir Sultanate", "Principality of Kasya");        
        
        definirCidadeAtual("Ubud");
        
        clearTerminal();
        while (true) {
            infos();
            System.out.println("O que desejas?\n1. Viajar\n2. Calcular Custo de Viagem");
            int escolha1 = scan.nextInt();
            scan.nextLine();
            if (escolha1 == 1) {
                System.out.println("\nPara onde deseja ir?");
                cidadeAtual.mostrarVizinhosEnumerados();
                int escolha2 = scan.nextInt();
                scan.nextLine();
                String proxCidade = cidadeAtual.escolherVizinho(escolha2);
                viajar(proxCidade);
            } else if (escolha1 == 2) {
                System.out.println("\nDeseja calcular o custo para qual cidade?\n");
                mostrarCidades();
                int numCidade = scan.nextInt();
                scan.nextLine();
                Cidade cidadeEscolhida = escolherCidadePorNumero(numCidade);
                System.out.println("\nA distancia minima daqui para " + cidadeEscolhida.getNome() + " é de "
                        + calcularDistancia(cidadeAtual.getNome(), cidadeEscolhida.getNome()) + "\n");
            }
        }
    }
}
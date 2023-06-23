import java.util.ArrayList;
import java.util.List;

public class Cidade {
    private String nome;
    private int poderDaCidade;
    private int distancia;
    private List<Cidade> vizinhos;

    public Cidade(String nome, int poderDaCidade) {
        this.nome = nome;
        this.poderDaCidade = poderDaCidade;
        this.vizinhos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPoderDaCidade() {
        return poderDaCidade;
    }

    public int getDistancia() {
        return this.distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public void adicionarVizinho(Cidade cidadeVizinha) {
        vizinhos.add(cidadeVizinha);
    }

    public List<Cidade> getVizinhos() {
        return vizinhos;
    }

    public void mostrarVizinhosEnumerados() {
        for (int i = 0; i < vizinhos.size(); i++) {
            Cidade vizinho = vizinhos.get(i);
            System.out.println((i + 1) + ". " + vizinho.getNome() + "  [" + vizinho.getPoderDaCidade() + "]");
        }
    }

    public String escolherVizinho(int escolha) {
        Cidade escolhido = vizinhos.get(escolha - 1);
        return escolhido.getNome();
    }

}
import java.util.ArrayList;
import java.util.List;

public class Cidade {
    private String nome;
    private int poderDaCidade;
    private List<Cidade> vizinhos;

    public Cidade(String nome, int poderDaCidade) {
        this.nome = nome;
        this.poderDaCidade = poderDaCidade;
        this.vizinhos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getPoderDaCidade() {
        return poderDaCidade;
    }

    public void adicionarVizinho(Cidade cidadeVizinha) {
        vizinhos.add(cidadeVizinha);
    }

    public List<Cidade> getVizinhos() {
        return vizinhos;
    }
}
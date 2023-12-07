import java.util.ArrayList;

public class Compra {

    String comprador;
    String nome;
    Proteina cProteina;
    String queijo;
    String bebida;
    ArrayList<String> adicionais;
    Double total;
    int qtd;

    public Compra(String comprador, Proteina cProteina, String queijo, String bebida, String nome, ArrayList<String> adicionais, int qtd,Double total){
        this.comprador = comprador;
        this.cProteina = cProteina;
        this.queijo = queijo;
        this.bebida = bebida;
        this.nome = nome;
        this.adicionais = adicionais;
        this.total = total;
        this.qtd = qtd;

    }

}

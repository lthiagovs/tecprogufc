package source;
import java.util.Scanner;

public class Pedido {

    public static String Proteinas[] = {"Salsicha","Frango","Bacon","Linguiça"};
    public static String Queijos[] = {"Mussarela","Prato","Parmesão","Coalho"};
    public static String Adicionais[] = {"Maionese","Ketchup","Ovo","Batata Palha"};
    public static String Bebidas[] = {"Coca-Cola","Del Rio","Suco do Chaves"};
    public static Scanner lerDados = new Scanner(System.in);

    public int codProteinas;
    public int codQueijos;
    public boolean codAdicionais[] = new boolean[Adicionais.length];
    public int codBebidas;
    public int codAluno;

    //Exibe as opções de Proteinas
    public static void exibirProteinas(){
        System.out.println("Você pode escolher entre as seguintes proteinas: \n");
        for(int i = 0;i< Proteinas.length;i++){
            System.out.println((i+1)+" - "+Proteinas[i]);
        }
    }

    //Exibe as opções de Queijos
    public static void exibirQueijos(){
        System.out.println("Você pode escolher entre os seguintes queijos: \n");
        for(int i = 0;i< Queijos.length;i++){
            System.out.println((i+1)+" - "+Queijos[i]);
        }
    }

    //Exibe as opções de Adicionais
    public static void exibirAdicionais(){
        System.out.println("Você pode escolher entre os seguintes adicionais: \n");
        for(int i = 0;i< Adicionais.length;i++){
            System.out.println((i+1)+" - "+Adicionais[i]);
        }
    }

    //Exibe as opções de Bebidas
    public static void exibirBebidas(){
        System.out.println("Você pode escolher entre as seguintes bebidas: \n");
        for(int i = 0;i< Bebidas.length;i++){
            System.out.println((i+1)+" - "+Bebidas[i]);
        }
    }

    //Escolhe uma opção de proteina
    public static int escolherProteina(){
        int codProteina = 0;
        exibirProteinas();
        int aux;
        do{
            System.out.println("Digite o código da proteina: ");
            aux=lerDados.nextInt();
        }while(aux<1||aux>Proteinas.length);
        codProteina=aux-1;
        return codProteina;
    }

    public static int escolherQueijos(){
        int codQueijo = 0;
        exibirQueijos();
        int aux;
        do{
            System.out.println("Digite o código do queijo: ");
            aux=lerDados.nextInt();
        }while(aux<1||aux>Queijos.length);
        codQueijo=aux-1;
        return codQueijo;
    }

    public static int escolherBebidas(){
        int codBebidas = 0;
        exibirBebidas();
        int aux;
        do{
            System.out.println("Digite o código do queijo: ");
            aux=lerDados.nextInt();
        }while(aux<1||aux>Bebidas.length);
        codBebidas=aux-1;
        return codBebidas;
    }

    public static boolean[] escolherAdicionais(){
        boolean adConfig[]= new boolean[Adicionais.length];
        System.out.println("Selecione seus adicionais: \n0 - Sair");
        exibirAdicionais();
        int aux;
        do{
            aux = lerDados.nextInt();
            if(aux>0&&aux<=Adicionais.length){
                adConfig[aux-1] = true;
            }
        }while(aux!=0);
        System.out.println("Adicionais salvos.");
        return adConfig;
    }

    public static void exibirPedido(int codPedido, Pedido pedido){
        System.out.println("-----------------------------");
        System.out.println("Pedido "+codPedido);
        System.out.println("   Código do Aluno: "+pedido.codAluno);
        System.out.println("   Proteina: "+Proteinas[pedido.codProteinas]);
        System.out.println("   Queijo: "+Queijos[pedido.codQueijos]);
        System.out.println("   Bebida: "+Bebidas[pedido.codBebidas]);
        System.out.println("   Adicionais: ");
        for(int i = 0;i<Adicionais.length;i++){
            if(pedido.codAdicionais[i]){
                System.out.println("      "+Adicionais[i]);
            }
        }
        System.out.println("-----------------------------");
    }

}
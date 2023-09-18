import java.util.Scanner;
import source.Aluno;
import source.Pedido;
//ralinho
public class Main {

    // ---------------Globais
    public static Scanner lerDados = new Scanner(System.in);
    public static boolean run = true;

    public static Aluno[] alunosLista = new Aluno[50];
    public static int alunoN = 0;

    public static Pedido[] pedidosLista = new Pedido[50];
    public static int pedidoN = 0;

    // ---------------Globais


    //Limpa a tela do console (Comando copiado da internet)
    public static void main(String[] args) {
        int Aux;
        while(run){
            System.out.println("0 - Sair\n1 - Cadastrar Aluno\n2 - Listar Alunos\n3 - Comprar\n4 - Listar Vendas");
            Aux = lerDados.nextInt();
            switch(Aux){
                case 0:
                    System.out.println("Até mais!\n");
                    run = false;
                    break;
                // -- Cadastrar Alunos -- //
                case 1:
                    System.out.println("Digite o nome e a matricula respectivamente: ");
                    String Nome = lerDados.next();
                    int Matricula = lerDados.nextInt();
                    alunosLista[alunoN] = new Aluno(Nome,Matricula);
                    alunoN++;
                    System.out.println("Cadastro concluido.");
                    break;
                // -- Cadastrar Alunos -- //

                // -- Listar Alunos -- //
                case 2:
                    System.out.println("Alunos cadastrados: ");
                    for(int i = 0;i<alunoN;i++){
                        System.out.println("Codigo do Aluno "+(i+1)+": "+alunosLista[i].Nome+" - Matricula: "+alunosLista[i].Matricula);
                    }
                    break;
                // -- Listar Alunos -- //

                // -- Realizar Compra -- //
                case 3:
                    pedidosLista[pedidoN] = new Pedido();
                    pedidosLista[pedidoN].codProteinas = Pedido.escolherProteina();
                    pedidosLista[pedidoN].codQueijos = Pedido.escolherQueijos();
                    pedidosLista[pedidoN].codAdicionais = Pedido.escolherAdicionais();
                    pedidosLista[pedidoN].codBebidas = Pedido.escolherBebidas();
                    System.out.println("Digite o codigo do aluno:");
                    pedidosLista[pedidoN].codAluno = lerDados.nextInt();
                    pedidoN++;
                    System.out.println("Pedido cadastrado com sucesso.");
                    break;
                // -- Realizar Compra -- //

                // -- Listar Compras -- //
                case 4:
                    for(int i = 0;i<pedidoN;i++){
                        Pedido.exibirPedido((i+1),pedidosLista[i]);
                    }
                    break;
                // -- Listar Compras -- //

                default:
                    System.out.println("Código incorreto.");
                    break;
            }

        }
        lerDados.close();

    }
}
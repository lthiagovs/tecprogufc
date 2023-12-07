import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        boolean rodar = true;
        ArrayList<Proteina> listaP = new ArrayList<Proteina>();
        ArrayList<String> listaQ = new ArrayList<String>();
        ArrayList<String> listaA = new ArrayList<String>();
        ArrayList<String> listaB = new ArrayList<String>();
        ArrayList<Compra> compras = new ArrayList<Compra>();


        Proteina salsicha = new Proteina("Salsicha",2.00);
        Proteina linguica = new Proteina("Linguiça",3.00);
        Proteina frango = new Proteina("Frango",2.50);
        Proteina bacon = new Proteina("Bacon",3.50);
        listaP.add(salsicha);
        listaP.add(linguica);
        listaP.add(frango);
        listaP.add(bacon);
        listaQ.add("Mussarela");
        listaQ.add("Prato");
        listaQ.add("Parmesão");
        listaQ.add("Coalho");
        listaA.add("Maionese");
        listaA.add("Ketchup");
        listaA.add("Ovo");
        listaA.add("Batata Palha");
        listaB.add("Coca-cola");
        listaB.add("Del Rio");
        listaB.add("Suco do Chaves");

        int pVendidas[] = new int[listaP.size()];
        int bVendidas[] = new int[listaB.size()];
        double total = 0;
        double totalDesc = 0;
        int totalVendas = 0;


        Scanner escolha = new Scanner(System.in);

        int selecionar;
        while(rodar){
            System.out.println("0 - Sair\n1 - Comprar\n2 - Exibir Vendas\n3 - Informações");
            selecionar = escolha.nextInt();

            switch (selecionar){
                case 0:
                    rodar = false;
                    break;
                case 1:
                    //Selecionar Matricula/SIAPE
                    String comprador;
                    String nome;
                    System.out.println("Digite sua matricula/SIAPE:");
                    comprador = escolha.next();
                    System.out.println("Digite seu nome:");
                    nome = escolha.next();

                    while(true) {
                        System.out.println("0 - Sair\n1 - Realizar uma compra");
                        selecionar = escolha.nextInt();
                        if (selecionar==1){
                            Proteina prot;
                            String queijo;
                            String bebida;
                            int qtd = 0;
                            ArrayList<String> adicionais = new ArrayList<String>();


                            //Selecionar proteina
                            do{
                                System.out.println("Selecione sua proteina: ");
                                for(int i = 0;i<listaP.size();i++){
                                    System.out.println(i+" - "+listaP.get(i).tipo+"  R$"+listaP.get(i).preço);

                                }
                                selecionar = escolha.nextInt();

                            }while(selecionar<0||selecionar>=listaP.size());
                            prot = listaP.get(selecionar);
                            pVendidas[selecionar]++;

                            //Selecionar queijo
                            do{
                                System.out.println("Selecione seu queijo: ");
                                for(int i = 0;i<listaQ.size();i++){
                                    System.out.println(i+" - "+listaQ.get(i));

                                }
                                selecionar = escolha.nextInt();

                            }while(selecionar<0||selecionar>=listaQ.size());
                            queijo = listaQ.get(selecionar);

                            //Selecionar Adicionais
                            selecionar = -1;
                            while(selecionar!=0){
                                System.out.println("Selecione seus adicionais: ");
                                System.out.println("0 - Prosseguir");
                                for(int i = 0;i<listaA.size();i++){
                                    System.out.println((i+1)+" - "+listaA.get(i));

                                }
                                selecionar = escolha.nextInt();
                                if(selecionar-1>0&&selecionar-1<listaA.size()){
                                    adicionais.add(listaA.get(selecionar-1));
                                    System.out.println("Adicionado ao pedido!");
                                }

                            }

                            //Selecionar Bebida
                            do{
                                System.out.println("Selecione sua bebida: ");
                                for(int i = 0;i<listaB.size();i++){
                                    System.out.println(i+" - "+listaB.get(i));

                                }
                                selecionar = escolha.nextInt();

                            }while(selecionar<0||selecionar>=listaB.size());
                            bebida = listaB.get(selecionar);
                            bVendidas[selecionar]++;


                            //Selecionar quantidade
                            do {
                                System.out.println("Digite a quantidade: ");
                                qtd = escolha.nextInt();
                            }while (qtd<=0);

                            //Concluir venda e calcular desconte
                            System.out.println("Compra concluida!");


                            double totalC = prot.preço*qtd;
                            totalVendas+=qtd;
                            //Calcular desc
                            if(qtd>2){
                                totalDesc+=totalC*0.14;
                                totalC-=totalC*0.14;
                            }

                            Compra nCompra = new Compra(comprador,prot,queijo,bebida,nome,adicionais,qtd,totalC);
                            compras.add(nCompra);
                            total+=totalC;


                        }else{
                            System.out.println("Encerrando vendas...");
                            break;
                        }
                    }

                    break;

                case 2:
                    int qCompras = 0;
                    System.out.println("Compra:");
                    for(Compra c:compras){
                        qCompras++;
                        System.out.println("    Quantidade: "+c.qtd);
                        System.out.println("    Valor: R$ "+c.total);
                        System.out.println("    "+c.nome+" - "+c.comprador);
                        System.out.println("    "+c.cProteina.tipo);
                        System.out.print("    Adicionais: ");
                        for(String s:c.adicionais){
                            System.out.print(s+" ");
                        }
                        System.out.println("\n    "+c.bebida);
                    }

                    break;

                case 3:
                    System.out.println("\nVendas de proteina:");
                    for(int i = 0;i<pVendidas.length;i++){
                        System.out.println("    "+listaP.get(i).tipo+" - "+pVendidas[i]);
                    }
                    System.out.println("\nVendas de bebidas:");
                    for(int i = 0;i<bVendidas.length;i++){
                        System.out.println("    "+listaB.get(i)+" - "+bVendidas[i]);
                    }
                    System.out.println("\nTotal arrecadado: "+total);
                    System.out.println("Total em desconto: "+totalDesc);
                    System.out.println("N de vendas: "+totalVendas);
                    break;

                default:
                    System.out.println("Codigo incorreto.");
                    break;
            }

        }

    }
}
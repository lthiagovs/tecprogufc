import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	// DEPOIS POLIR INTERFACE
    //1) OK (MAS PODE MELHORAR)
	//2) OK
	//3) OK
	//4) FALTA A COLISÃO
	//5) a) OK || b) diagonal n funfa || c/d) linhas = 0
	//6) SOMENTE EXIBIR NA INTERFACE
	//7) FALTA GRANDE PARTE ------- !!
	//8) FUMO GRANDE!! ------- !!
	
	
	//Cores no Terminal
    public static String BG_RESET = "\033[00m";
    public static String BG_BLACK = "\033[40m";
    public static String BG_BLUE = "\033[44m";
    public static String BG_YELLOW = "\033[43m";
    public static String BG_WHITE = "\033[47m";
    public static String BG_GREEN = "\u001B[42m";
    public static String BG_RED = "\u001B[41m";
    public static String STR_BLACK = "\033[0;30m";
    public static Scanner iDados = new Scanner(System.in);
    
    
    //Linhas de instrução do programa - 18 caracteres
    public static String insLinha[] = {"0 - Sair              ","1 - Novo Instante     ",
    		"2 - Adicionar Bug     ","3 - Adicionar Dev     ","4 - Ver Distâncias    ","5 - Ver Area Total    ","6 - Informações Gerais"};
    
    //Bugs e Desenvolvimentos
    public static ArrayList<Cometa> cometas = new ArrayList<>();
    
    //Exibir bugs/devs na tela
    public static void exibirCometas(){
    	System.out.println(Main.BG_BLACK+"Lista de Bugs/Devs:"+Main.BG_RESET);
    	for(int i = 0;i<cometas.size();i++){
    		System.out.print(Main.BG_RESET+"    ");
    		if(cometas.get(i).modificador==1){
    			System.out.print(Main.BG_GREEN+"DEV");
    		}else{
    			System.out.print(Main.BG_RED+"BUG");
    		}
    		System.out.print(Main.BG_RESET+" || X: "+cometas.get(i).x+" Y: "+cometas.get(i).y+"\n");
    	}
    }
    
    
    public static boolean RUN = true;

    public static void main(String[] args) {
    	
        //Iniciando planetas
        int nPlanetas = 7;
        Planeta conjuntoPlanetas[] = new Planeta[nPlanetas];
        conjuntoPlanetas[0] = new Planeta(1,4, " Py",24f);
        conjuntoPlanetas[1] = new Planeta(2,3, " Js",10f);
        conjuntoPlanetas[2] = new Planeta(3,2, "Rby",48f);
        conjuntoPlanetas[3] = new Planeta(4,2,"PHP",60f);
        conjuntoPlanetas[4] = new Planeta(5,1," C#",4f);
        conjuntoPlanetas[5] = new Planeta(6,2,"C++",0.5f);
        conjuntoPlanetas[6] = new Planeta(7,10, " C ",0.1f);


        //Iniciando o universo
        Universo mainUniverso = new Universo();
        mainUniverso.setTamanhoMax(Planeta.getTamanhoMax(conjuntoPlanetas,nPlanetas));
        mainUniverso.constroiUniverso();

        //Posicionando Planetas
        conjuntoPlanetas[0].iniciarPosicao(mainUniverso.distMaxX,mainUniverso.distMaxY);
        conjuntoPlanetas[1].iniciarPosicao(mainUniverso.distMaxX,mainUniverso.distMaxY);
        conjuntoPlanetas[2].iniciarPosicao(mainUniverso.distMaxX,mainUniverso.distMaxY);
        conjuntoPlanetas[3].iniciarPosicao(mainUniverso.distMaxX,mainUniverso.distMaxY);
        conjuntoPlanetas[4].iniciarPosicao(mainUniverso.distMaxX,mainUniverso.distMaxY);
        conjuntoPlanetas[5].iniciarPosicao(mainUniverso.distMaxX,mainUniverso.distMaxY);
        conjuntoPlanetas[6].iniciarPosicao(mainUniverso.distMaxX,mainUniverso.distMaxY);

        //Movimento dos Planetas
        int instante = 0, totalInstantes = 0, escolha;
        //Execução do programa
        System.out.println(Main.BG_RESET+"------------------------------------"+Main.BG_RESET);
        Planeta.moverPlanetas(conjuntoPlanetas,nPlanetas,instante);
        mainUniverso.printUniverso(conjuntoPlanetas,nPlanetas);
        while(RUN){
        	//System.out.println(Main.BG_RESET+"------------------------------------"+Main.BG_BLACK);
        	System.out.print(Main.BG_BLACK+"O que deseja fazer?: "+Main.BG_RESET);
        	escolha = iDados.nextInt();
        	
        	switch(escolha){
        		case 0:
        			System.out.println(Main.BG_BLACK+"Até mais!!!"+Main.BG_RESET);
        			RUN = false;
        			break;
        		case 1:
        			System.out.print(Main.BG_BLACK+"Digite o novo instante: "+Main.BG_RESET);
        			instante = iDados.nextInt();
        			System.out.println(Main.BG_RESET+"------------------------------------"+Main.BG_BLACK);
        			Planeta.moverPlanetas(conjuntoPlanetas,nPlanetas,instante);
        	        mainUniverso.printUniverso(conjuntoPlanetas,nPlanetas);
        	        totalInstantes+=1;
        	        Main.exibirCometas();
        	        System.out.println(Main.BG_BLACK+"[Instante: "+instante+"] [Total de Instantes: "+totalInstantes+"]"+Main.BG_RESET);
        	        break;
        		case 2:
        			int xC, yC, modificadorC = -1;
        			System.out.print(Main.BG_BLACK+"Digite a posição X: "+Main.BG_RESET);
        			xC = iDados.nextInt();
        			System.out.print(Main.BG_BLACK+"Digite a posição Y: "+Main.BG_RESET);
        			yC = iDados.nextInt();
        			Cometa nCometaC = new Cometa(xC-1,yC-1,modificadorC);
        			Main.cometas.add(nCometaC);
        			System.out.println(Main.BG_BLACK+"Bug criado com sucesso!"+Main.BG_RESET);
        			break;
        		case 3:
        			int x, y, modificador = 1;
        			System.out.print(Main.BG_BLACK+"Digite a posição X: "+Main.BG_RESET);
        			x = iDados.nextInt();
        			System.out.print(Main.BG_BLACK+"Digite a posição Y: "+Main.BG_RESET);
        			y = iDados.nextInt();
        			Cometa nCometa = new Cometa(x-1,y-1,modificador);
        			Main.cometas.add(nCometa);
        			System.out.println(Main.BG_BLACK+"Desenvolvimento criado com sucesso!"+Main.BG_RESET);
        			break;
        		case 6:
        			mainUniverso.getAreaPlanetas(conjuntoPlanetas, nPlanetas);
        			mainUniverso.printNorteSul(conjuntoPlanetas, nPlanetas);
        			mainUniverso.printAlinhamento(conjuntoPlanetas, nPlanetas);
        			break;
        		default:
        			System.out.println(Main.BG_YELLOW+"Comando incorreto..."+Main.BG_RESET);
        			break;
        	}
        	System.out.println(Main.BG_BLACK+"------------------------------------"+Main.BG_RESET);
        	
        }
        //Planeta.moverPlanetas(conjuntoPlanetas,nPlanetas,10000);
        //mainUniverso.printUniverso(conjuntoPlanetas,nPlanetas);
        
    }
}
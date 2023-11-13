import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
	//Cores no Terminal
    public static String BG_RESET = "\033[00m";
    public static String BG_BLACK = "\033[40m";
    public static String BG_BLUE = "\033[44m";
    public static String BG_YELLOW = "\033[43m";
    public static String BG_GREEN = "\u001B[42m";
    public static String BG_RED = "\u001B[41m";
    public static Scanner iDados = new Scanner(System.in);
	public static Random random = new Random();
    
    
    //Linhas de instru��o do programa - 18 caracteres
    public static String insLinha[] = {"0 - Sair              ","1 - Novo Instante     ",
    		"2 - Adicionar Bug     ","3 - Adicionar Dev     ","4 - Ver Dist�ncias    ","5 - Ver Area Total    ","6 - Informa��es Gerais",
	"7 - Lista de Colis�o  ","8 - Horarios          ","9 - Add Exoplaneta    "};
    
    //Bugs e Desenvolvimentos
    public static ArrayList<Cometa> cometas = new ArrayList<>();
    
    //Exibir bugs/devs na tela
    public static void exibirCometas(){
    	System.out.println(Main.BG_BLACK+"Lista de Bugs/Devs:"+Main.BG_RESET);
    	for(int i = 0;i<cometas.size();i++){
    		if(cometas.get(i).modificador==1){
				System.out.print(Main.BG_RESET+"    ");
    			System.out.print(Main.BG_GREEN+"DEV");
				System.out.print(Main.BG_RESET+" >> X > "+cometas.get(i).x+" Y > "+cometas.get(i).y+"\n");
    		}else if(cometas.get(i).modificador==-1){
				System.out.print(Main.BG_RESET+"    ");
    			System.out.print(Main.BG_RED+"BUG");
				System.out.print(Main.BG_RESET+" >> X > "+cometas.get(i).x+" Y > "+cometas.get(i).y+"\n");
    		}
    	}
    }

	//Exibe as instru��es na tela
    public static void exibirInstrucoes(){
		System.out.println();
		for(int i = 0;i<insLinha.length;i++){
			System.out.println(Main.BG_BLACK+insLinha[i]+Main.BG_RESET);
		}
		System.out.println();
	}

    public static boolean RUN = true;

	//Gera o relatorio final
	public static void gerarRelatorio(ArrayList<Planeta> planetas){
		System.out.println(Main.BG_BLACK+"Relatorio FinaL:"+Main.BG_RESET+"\n");
		//Colis�es
		System.out.println(Main.BG_BLACK+"	Colis�es:"+Main.BG_RESET+"\n");
		for (Cometa cometa: cometas) {
			if(cometa.modificador==0){
				System.out.println("		Colis�o em: "+cometa.x+" - "+cometa.y);
			}
		}
		//Explodiram
		System.out.println(Main.BG_BLACK+"	Explodiram:"+Main.BG_RESET+"\n");
		for (int i = 0;i<planetas.size();i++){
			if(planetas.get(i).deslocamento<=0){
				System.out.println("		Planeta que explodiu: "+planetas.get(i).Nome);
			}
		}
		System.out.println(Main.BG_BLACK+"	Informa��es:"+Main.BG_RESET+"\n");
		System.out.println("		Python � uma linguagem de programa��o de alto n�vel, interpretada de script, imperativa, orientada a objetos, funcional, de tipagem din�mica e forte."+Main.BG_RESET+"\n");
		System.out.println("		Ruby � uma linguagem de programa��o interpretada multiparadigma, de tipagem din�mica e forte, com gerenciamento de mem�ria autom�tico, originalmente planejada e desenvolvida no Jap�o em 1995, por Yukihiro \"Matz\" Matsumoto, para ser usada como linguagem de script."+Main.BG_RESET+"\n");
		System.out.println("		Java � uma linguagem de programa��o orientada a objetos desenvolvida na d�cada de 90 por uma equipe de programadores chefiada por James Gosling, na empresa Sun Microsystems, que em 2008 foi adquirido pela empresa Oracle Corporation."+Main.BG_RESET+"\n");
		System.out.println("		PHP � uma linguagem interpretada livre, usada originalmente apenas para o desenvolvimento de aplica��es presentes e atuantes no lado do servidor, capazes de gerar conte�do din�mico na World Wide Web."+Main.BG_RESET+"\n");
		System.out.println("		C � uma linguagem de programa��o compilada de prop�sito geral, estruturada, imperativa, procedural, padronizada pela Organiza��o Internacional para Padroniza��o, criada em 1972 por Dennis Ritchie na empresa AT&T Bell Labs para desenvolvimento do sistema operacional Unix."+Main.BG_RESET+"\n");
		System.out.println("		C++ � uma linguagem de programa��o compilada multi-paradigma e de uso geral. Desde os anos 1990 � uma das linguagens comerciais mais populares, sendo bastante usada tamb�m na academia por seu grande desempenho e base de utilizadores."+Main.BG_RESET+"\n");
		System.out.println("		C# � uma linguagem de programa��o, multiparadigma, de tipagem forte, desenvolvida pela Microsoft como parte da plataforma .NET. A sua sintaxe orientada a objetos foi baseada no C++ mas inclui muitas influ�ncias de outras linguagens de programa��o, como Object Pascal e, principalmente, Java."+Main.BG_RESET+"\n");
		System.out.println("		JavaScript � uma linguagem de programa��o interpretada estruturada, de script em alto n�vel com tipagem din�mica fraca e multiparadigma. Juntamente com HTML e CSS, o JavaScript � uma das tr�s principais tecnologias da World Wide Web.."+Main.BG_RESET+"\n");

	}

	//Adiciona um exoplaneta
	public static void addExoplaneta(ArrayList<Planeta> planetas, Universo universo){
		planetas.add(new Planeta(planetas.size(),10, "EXO",0.5f));
		universo.setTamanhoMax(Planeta.getTamanhoMax(planetas,planetas.size()));
		universo.constroiUniverso();

		for (Planeta p:planetas) {
			p.iniciarPosicao(universo.distMaxX,universo.distMaxY);
		}
	}

	//Fun��o Principal
    public static void main(String[] args) {
    	
        //Iniciando planetas
		ArrayList<Planeta> planetas = new ArrayList<>();
		planetas.add(new Planeta(1,4, " Py",24f));
		planetas.add(new Planeta(2,3, " Js",10f));
		planetas.add(new Planeta(3,2, "Rby",48f));
		planetas.add(new Planeta(4,2, "PHP",60f));
		planetas.add(new Planeta(5,1, " C#",4f));
		planetas.add(new Planeta(6,2, "C++",0.5f));
		planetas.add(new Planeta(7,10, "  C",0.5f));


        //Iniciando o universo
        Universo mainUniverso = new Universo();
        mainUniverso.setTamanhoMax(Planeta.getTamanhoMax(planetas,planetas.size()));
        mainUniverso.constroiUniverso();

        //Posicionando Planetas
		for (Planeta p:planetas) {
			p.iniciarPosicao(mainUniverso.distMaxX,mainUniverso.distMaxY);
		}


        //Movimento dos Planetas
        int instante = 0, totalInstantes = 0, escolha;
        //Execu��o do programa
        System.out.println(Main.BG_RESET+"------------------------------------"+Main.BG_RESET);
        Planeta.moverPlanetas(planetas,planetas.size(),instante);
        mainUniverso.printUniverso(planetas,planetas.size());
        while(RUN){
        	//System.out.println(Main.BG_RESET+"------------------------------------"+Main.BG_BLACK);
        	System.out.print(Main.BG_BLACK+"O que deseja fazer?: "+Main.BG_RESET);
        	escolha = iDados.nextInt();

			//Escolhas
        	switch(escolha){
        		case 0:
        			System.out.println(Main.BG_BLACK+"At� mais!!!"+Main.BG_RESET);
        			RUN = false;
					gerarRelatorio(planetas);
        			break;
        		case 1:
        			System.out.print(Main.BG_BLACK+"Digite o novo instante: "+Main.BG_RESET);
        			instante = iDados.nextInt();
        			System.out.println(Main.BG_BLACK+"------------------------------------"+Main.BG_RESET);
        			Planeta.moverPlanetas(planetas,planetas.size(),instante);
        	        mainUniverso.printUniverso(planetas,planetas.size());
        	        totalInstantes+=1;
        	        Main.exibirCometas();
        	        System.out.println(Main.BG_BLACK+"[Instante: "+instante+"] [Total de Instantes: "+totalInstantes+"]"+Main.BG_RESET);
        	        break;
        		case 2:
        			int xC, yC, modificadorC = -1;
        			//System.out.print(Main.BG_BLACK+"Digite a posi��o X: "+Main.BG_RESET);
        			//xC = iDados.nextInt();
					xC = random.nextInt(mainUniverso.distMaxX-1);
        			//System.out.print(Main.BG_BLACK+"Digite a posi��o Y: "+Main.BG_RESET);
        			yC = random.nextInt(mainUniverso.distMaxY-1);
        			Cometa nCometaC = new Cometa(xC,yC,modificadorC);
        			Main.cometas.add(nCometaC);
        			System.out.println(Main.BG_GREEN+"Bug criado com sucesso!"+Main.BG_RESET);
        			break;
        		case 3:
        			int x, y, modificador = 1;
        			//System.out.print(Main.BG_BLACK+"Digite a posi��o X: "+Main.BG_RESET);
        			x = random.nextInt(mainUniverso.distMaxX-1);
        			//System.out.print(Main.BG_BLACK+"Digite a posi��o Y: "+Main.BG_RESET);
        			y = random.nextInt(mainUniverso.distMaxY-1);
        			Cometa nCometa = new Cometa(x,y,modificador);
        			Main.cometas.add(nCometa);
        			System.out.println(Main.BG_GREEN+"Desenvolvimento criado com sucesso!"+Main.BG_RESET);
        			break;
				case 4: //OK
					mainUniverso.getAreaPlanetas(planetas,planetas.size());
					//mainUniverso.getAreaEuclidiana(planetas,planetas.size());
					break;
				case 5:
					Universo.calcAreaTotal(planetas,mainUniverso);
					break;
        		case 6:
        			mainUniverso.getAreaPlanetas(planetas, planetas.size());
        			mainUniverso.printNorteSul(planetas, planetas.size());
        			mainUniverso.printAlinhamento(planetas, planetas.size());
        			break;
				case 7:
					Cometa.exibirColisao(cometas);
					break;
				case 8:
					Planeta.horariosPlaneta(planetas);
					break;
				case 9:
					Main.addExoplaneta(planetas,mainUniverso);
					break;
        		default:
        			System.out.println(Main.BG_YELLOW+"Comando incorreto..."+Main.BG_RESET);
        			break;
        	}
        	System.out.println("\n"+Main.BG_BLACK+"-------------------------------------------------------------------------------------------------------------------------------------------"+Main.BG_RESET);
			if(escolha!=1&&escolha!=0){
				Main.exibirInstrucoes();
			}
        	
        }
        
    }
}
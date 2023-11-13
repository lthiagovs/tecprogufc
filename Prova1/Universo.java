import java.util.ArrayList;

public class Universo {

    private int universoArray[][];
    public int distMaxX;
    public int distMaxY;
	//Posição das estrelas
    public static Ponto estrelaPosicoes[];


    //Constroi o universo baseado no planeta de maior distancia
    public void setTamanhoMax(int distanciaMAX){
        this.distMaxX = 5+distanciaMAX + (distanciaMAX-2);
        this.distMaxY = 2*distanciaMAX+2;

        this.universoArray = new int[this.distMaxY][this.distMaxX];
    }

    //Povoa a matriz universo com espaços vazios
    public void constroiUniverso(){
        for(int y = 0;y<distMaxY;y++){
            for(int x = 0;x<distMaxX;x++){
                universoArray[y][x] = 0;
            }
        }

        //Construindo estrela manualmente...
        estrelaPosicoes = new Ponto[6];
        estrelaPosicoes[0] = new Ponto(((distMaxX-1)/2)-1,(distMaxY/2)-1);
        estrelaPosicoes[1] = new Ponto(((distMaxX-1)/2),(distMaxY/2)-1);
        estrelaPosicoes[2] = new Ponto(((distMaxX-1)/2)+1,(distMaxY/2)-1);
        estrelaPosicoes[3] = new Ponto(((distMaxX-1)/2)-1,(distMaxY/2));
        estrelaPosicoes[4] = new Ponto(((distMaxX-1)/2),(distMaxY/2));
        estrelaPosicoes[5] = new Ponto(((distMaxX-1)/2)+1,(distMaxY/2));

    }

    //Mostra os planetas ao norte e ao sul
    public void printNorteSul(ArrayList<Planeta> planetas, int size){
    	System.out.println(Main.BG_YELLOW+"Informação sobre norte/su da estrela:"+Main.BG_RESET);
    	String norte = Main.BG_BLACK+"Planetas ao norte:"+Main.BG_RESET+" ", sul = Main.BG_BLACK+"Planetas ao sul:"+Main.BG_RESET+" ";
    	for(int i = 0;i<size;i++){
    		if(planetas.get(i).y<(this.distMaxY/2)-1){
    			norte+=Main.BG_BLUE+planetas.get(i).Nome+Main.BG_RESET+"  ";
    		}else if(planetas.get(i).y>(this.distMaxY/2)+1){
    			sul+=Main.BG_BLUE+planetas.get(i).Nome+Main.BG_RESET+"  ";
    		}
    	}
    	System.out.println(norte+"\n"+sul);
    }
    
    //Mostra os planetas alinhados
    public void printAlinhamento(ArrayList<Planeta> planetas, int size){
    	System.out.println(Main.BG_YELLOW+"Informação quanto ao alinhamento:"+Main.BG_RESET);
    	//Alinhamento vertical
    	int aX = ((this.distMaxX-1)/2);
    	//Alinhamento horizontal
    	int aY = (this.distMaxY/2);
    	
    	int mX = (this.distMaxX-1)/2;
    	int mY = (this.distMaxY/2);
    	
    	String vertical = Main.BG_BLACK+"Planetas alinhados na horizontal:"+Main.BG_RESET;
    	String horizontal = Main.BG_BLACK+"Planetas alinhados na vertical:"+Main.BG_RESET;
    	String diagonal = Main.BG_BLACK+"Planetas alinhados na diagonal:"+Main.BG_RESET;
    	boolean triggerDiagonal = false;
    	
    	for(int i = 0;i<size;i++){
    		if(planetas.get(i).y==aX){
    			vertical+=Main.BG_RESET+"   "+Main.BG_BLUE+planetas.get(i).Nome+Main.BG_RESET;
    		}else if(planetas.get(i).x==aY||planetas.get(i).x==aY-1){
    			horizontal+=Main.BG_RESET+"   "+Main.BG_BLUE+planetas.get(i).Nome+Main.BG_RESET;
    		}else{
    			//diagonal 1
    			for(int x = 0;x<mX-1;x++){
    				if(planetas.get(i).x==x&&planetas.get(i).y==x){
    					triggerDiagonal = true;
    				}
    			}
    			//diagonal 2
    			int x = 0;
    			for(int y = this.distMaxY-1;x<mY;y--){
    				if(planetas.get(i).y==y&&planetas.get(i).x==x){
    					triggerDiagonal = true;
    				}
    				x+=1;
    			}
    			//diagonal 3
    			int y = 0;
    			for(x = this.distMaxX-1;x<mX==false;x--){
    				if(planetas.get(i).y==y&&planetas.get(i).x==x){
    					triggerDiagonal = true;
    				}
    				y+=1;
    			}
    			//diagonal 4
    			y = this.distMaxY-1;
    			for(x = this.distMaxX-1;x<mX==false;x--){
    				if(planetas.get(i).y==y&&planetas.get(i).x==x){
    					triggerDiagonal = true;
    				}
    				y-=1;
    			}
    		}
    		if(triggerDiagonal){
    			diagonal+=Main.BG_RESET+"   "+Main.BG_BLUE+planetas.get(i).Nome+Main.BG_RESET;
				triggerDiagonal = false;
    		}
    	}
    	System.out.println(horizontal);
    	System.out.println(vertical);
    	System.out.println(diagonal);
    }

    //Retorna a area entre dois planetas
    public int getAreaPlanetas(Planeta p1, Planeta p2){
    	return (Math.abs(p1.x-p2.x)+1) * (Math.abs(p1.y-p2.y)+1);
    }

	//Retorna a area entre duas coordenadas
	public int getAreaPlanetas(int x1, int y1, int x2, int y2){
		return (Math.abs(x1-x2)+1) * (Math.abs(y1-y2)+1);
	}

	//Retorna a distância euclidiana entre dois planetas
	public double getAreaEuclidiana(Planeta p1, Planeta p2){
		double x = (Math.abs(p1.x-p2.x)+1);
		double y = (Math.abs(p1.y-p2.y)+1);
		return Math.sqrt(Math.pow(x,2) * Math.pow(y,2.0));
	}

	//Distancia Planetas
	public static void criarLinha(Planeta p1, Planeta p2, Universo universo){
		int minX, maxX, minY, maxY;
		//Definindo o planeta mais extremo
		if(p1.x>p2.x){
			minX = p2.x-1;
			maxX = p1.x;
		}else{
			minX = p1.x-1;
			maxX = p2.x;
		}

		if(p1.y>p2.y){
			minY = p2.y-1;
			maxY = p1.y;
		}else{
			minY = p1.y-1;
			maxY = p2.y;
		}
		//Criando a linha no universo
		while(minX<maxX||minY<maxY){
			if(minX<maxX){
				minX++;
			}
			if(minY<maxY){
				minY++;
			}
			universo.universoArray[minY][minX] = 3;
		}

	}

	//Cria linha imaginaria
	public static void criarLinhas(ArrayList<Planeta> planetas, Universo universo){
		for(int i1 = 0;i1<planetas.size();i1++){
			for(int i2 = 0;i2<planetas.size();i2++){
				criarLinha(planetas.get(i1),planetas.get(i2),universo);
			}
		}
	}

	//Calcula a area total
	public static void calcAreaTotal(ArrayList<Planeta> planetas, Universo universo){
		criarLinhas(planetas,universo);
		boolean triggerStart = false;
		boolean triggerEnd = false;
		int area = 0;
		Ponto start = new Ponto(0,0);
		Ponto end = new Ponto(0,0);
		for(int y = 0;y<universo.distMaxY;y++){
			for(int x = 0;x<universo.distMaxX;x++){
				if(universo.universoArray[y][x]==3){
					if(!triggerStart){
						triggerStart=true;
						start=new Ponto(x,y);
					}else{
						triggerEnd=true;
						end=new Ponto(x,y);
					}
				}
			}
			if(triggerStart&&triggerEnd){
				area+=universo.getAreaPlanetas(start.x, start.y,end.x,end.y);
			}
			triggerStart=false;
			triggerEnd=false;
		}
		System.out.println(Main.BG_BLACK+"Area total aproximada: "+Main.BG_GREEN+area+Main.BG_RESET);
	}

    //Exibe a area de todos entre todos os planetas
    public void getAreaPlanetas(ArrayList<Planeta> planetas, int size){
    	System.out.println(Main.BG_YELLOW+"Informação quanto as areas:"+Main.BG_RESET);
    	for(int p = 0;p<size;p++){
    		System.out.print(Main.BG_BLACK+"Planeta:"+Main.BG_RESET+" "+Main.BG_BLUE+planetas.get(p).Nome+Main.BG_RESET+" || ");
    		for(int p2=0;p2<size;p2++){
    			if(p!=p2){
    				System.out.print(Main.BG_BLUE+planetas.get(p2).Nome+Main.BG_RESET+" "+getAreaPlanetas(planetas.get(p),planetas.get(p2))+Main.BG_RESET+"    ");
    			}
    		}
    		System.out.print("\n");
    	}
    }

	public void getAreaEuclidiana(ArrayList<Planeta> planetas, int size){
		System.out.println(Main.BG_YELLOW+"Informação quanto as areas:"+Main.BG_RESET);
		for(int p = 0;p<size;p++){
			System.out.print(Main.BG_BLACK+"Planeta:"+Main.BG_RESET+" "+Main.BG_BLUE+planetas.get(p).Nome+Main.BG_RESET+" || ");
			for(int p2=0;p2<size;p2++){
				if(p!=p2){
					System.out.print(Main.BG_BLUE+planetas.get(p2).Nome+Main.BG_RESET+" "+getAreaEuclidiana(planetas.get(p),planetas.get(p2))+Main.BG_RESET+"    ");
				}
			}
			System.out.print("\n");
		}
	}
    
    //Desenha o universo na tela com seus planetas e respectivas posições
    public void printUniverso(ArrayList<Planeta> planetas, int size){
        String uniLine = "";
        int i = 0;
        int trigger = 0;
        int triggerBUG = 0;
        //Percorrer todo o universo
        for(int y = 0;y<distMaxY;y++){
            for(int x = 0;x<distMaxX;x++){

                //Verificar se planeta esta na posição x e y, se estiver, desenha P
                for(int z = 0;z<size;z++){
                	//OBSERVA SE UM PLANETA OCUPA ESTA POSIÇÃO
                    if(planetas.get(z).x==x&&planetas.get(z).y==y&&planetas.get(z).deslocamento!=0){
                        trigger=1;
                        i = z;
                    //OBSERVA SE A ESTRELA OCUPA ESTA POSIÇÃO
                    }else if(z<6){
                        if(estrelaPosicoes[z].x==x&&estrelaPosicoes[z].y==y){
                            trigger=2;
                        }
                    }
                    //OBSERVA SE EXISTE UM BUG NESTA POSIÇÃO
                    if(z<Main.cometas.size()){
                    	if(Main.cometas.get(z).x ==x&&Main.cometas.get(z).y==y){
                    		if(Main.cometas.get(z).modificador==1){
                    			triggerBUG = 1;
                    		}else if(Main.cometas.get(z).modificador==-1){
                    			triggerBUG = -1;
                    		}
                    	}
                    }
                }
                if(trigger==1){
                    uniLine+=Main.BG_BLUE+planetas.get(i).Nome;
                }else if(trigger==2&&trigger!=1) {
					uniLine += Main.BG_YELLOW + "   ";
                }else{ //DESENHA A COR DOS BUGS E DEV NA TELA
                	if(triggerBUG==1){
						uniLine+=Main.BG_GREEN+"DEV"+Main.BG_BLACK;
                	}else if(triggerBUG==-1){
						uniLine+=Main.BG_RED+"BUG"+Main.BG_BLACK;
                	}else{
						uniLine+=Main.BG_BLACK+" . "+Main.BG_BLACK;
                	}
                    //uniLine=" . "+Main.BG_BLACK;
                }
                i=0;
                triggerBUG=0;
                trigger = 0;
            }
            System.out.print(uniLine+Main.BG_RESET);
            //Printar Linhas de instrução ao lado do universo
            if(y<Main.insLinha.length){
            	System.out.print("  "+Main.BG_BLACK+Main.insLinha[y]+Main.BG_RESET);
            }
            System.out.print("\n");
            uniLine = "";
        }

    }

}

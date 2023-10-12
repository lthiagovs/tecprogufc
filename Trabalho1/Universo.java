

public class Universo {

    private int universoArray[][];
    public int distMaxX;
    public int distMaxY;

    public static Ponto estrelaPosicoes[];


    //TAMANHO DO UNIVERSO (X) BASEADO NA DISTANCIA M�XIMA: 5+D + (D-2)
    //TAMANHO DO UNIVERSO (Y) BASEADO NA DISTANCIA M�XIMA: 2*D+2


    //Verifica qual a maior distancia entre todos os planetas;

    //Constroi o universo baseado no planeta de maior distancia
    public void setTamanhoMax(int distanciaMAX){
        this.distMaxX = 5+distanciaMAX + (distanciaMAX-2);
        this.distMaxY = 2*distanciaMAX+2;

        this.universoArray = new int[this.distMaxY][this.distMaxX];
    }

    //Povoa a matriz universo com espa�os vazios
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
    public void printNorteSul(Planeta planetasLista[], int size){
    	System.out.println(Main.BG_YELLOW+"Informa��o sobre norte/su da estrela:"+Main.BG_RESET);
    	String norte = Main.BG_BLACK+"Planetas ao norte:"+Main.BG_RESET+" ", sul = Main.BG_BLACK+"Planetas ao sul:"+Main.BG_RESET+" ";
    	for(int i = 0;i<size;i++){
    		if(planetasLista[i].y<(this.distMaxY/2)-1){
    			norte+=Main.BG_BLUE+planetasLista[i].Nome+Main.BG_RESET+"  ";
    		}else if(planetasLista[i].y>(this.distMaxY/2)+1){
    			sul+=Main.BG_BLUE+planetasLista[i].Nome+Main.BG_RESET+"  ";
    		}
    	}
    	System.out.println(norte+"\n"+sul);
    }
    
    //Mostra os planetas alinhados
    public void printAlinhamento(Planeta planetasLista[], int size){
    	System.out.println(Main.BG_YELLOW+"Informa��o quanto ao alinhamento:"+Main.BG_RESET);
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
    		if(planetasLista[i].y==aX){
    			vertical+=Main.BG_RESET+"   "+Main.BG_BLUE+planetasLista[i].Nome+Main.BG_RESET;
    		}else if(planetasLista[i].x==aY||planetasLista[i].x==aY-1){
    			horizontal+=Main.BG_RESET+"   "+Main.BG_BLUE+planetasLista[i].Nome+Main.BG_RESET;
    		}else{
    			//diagonal 1
    			for(int x = 0;x<mX-1;x++){
    				if(planetasLista[i].x==x&&planetasLista[i].y==x){
    					triggerDiagonal = true;
    				}
    			}
    			//diagonal 2
    			int x = 0;
    			for(int y = this.distMaxY-1;x<mY;y--){
    				if(planetasLista[i].y==y&&planetasLista[i].x==x){
    					triggerDiagonal = true;
    				}
    				x+=1;
    			}
    			//diagonal 3
    			int y = 0;
    			for(x = this.distMaxX-1;x<mX==false;x--){
    				if(planetasLista[i].y==y&&planetasLista[i].x==x){
    					triggerDiagonal = true;
    				}
    				y+=1;
    			}
    			//diagonal 4
    			y = this.distMaxY-1;
    			for(x = this.distMaxX-1;x<mX==false;x--){
    				if(planetasLista[i].y==y&&planetasLista[i].x==x){
    					triggerDiagonal = true;
    				}
    				y-=1;
    			}
    		}
    		triggerDiagonal = false;
    		if(triggerDiagonal){
    			diagonal+=Main.BG_RESET+"   "+Main.BG_BLUE+planetasLista[i].Nome+Main.BG_RESET;
    		}
    	}
    	System.out.println(horizontal);
    	System.out.println(vertical);
    	System.out.println(diagonal);
    }

    //Retorna a area entre dois planetas
    public int getAreaPlanetas(Planeta p1, Planeta p2){
    	return Math.abs(p1.x-p2.x) * Math.abs(p1.y-p2.y);
    }
    
    //Exibe a area de todos entre todos os planetas
    public void getAreaPlanetas(Planeta planetasLista[], int size){
    	System.out.println(Main.BG_YELLOW+"Informa��o quanto as areas:"+Main.BG_RESET);
    	for(int p = 0;p<size;p++){
    		System.out.print(Main.BG_BLACK+"Planeta:"+Main.BG_RESET+" "+Main.BG_BLUE+planetasLista[p].Nome+Main.BG_RESET+" >> ");
    		for(int p2=0;p2<size;p2++){
    			if(p!=p2){
    				System.out.print(Main.BG_BLUE+planetasLista[p2].Nome+Main.BG_RESET+" "+getAreaPlanetas(planetasLista[p],planetasLista[p2])+Main.BG_RESET+" || ");
    			}
    		}
    		System.out.print("\n");
    	}
    }
    
    //Desenha o universo na tela com seus planetas e respectivas posi��es
    public void printUniverso(Planeta planetasLista[], int size){
        String uniLine = "";
        int i = 0;
        int trigger = 0;
        int triggerBUG = 0;
        //Percorrer todo o universo
        for(int y = 0;y<distMaxY;y++){
            for(int x = 0;x<distMaxX;x++){

                //Verificar se planeta esta na posi��o x e y, se estiver, desenha P
                for(int z = 0;z<size;z++){
                	//OBSERVA SE UM PLANETA OCUPA ESTA POSI��O
                    if(planetasLista[z].x==x&&planetasLista[z].y==y){
                        trigger=1;
                        i = z;
                    //OBSERVA SE A ESTRELA OCUPA ESTA POSI��O
                    }else if(z<6){
                        if(estrelaPosicoes[z].x==x&&estrelaPosicoes[z].y==y){
                            trigger=2;
                        }
                    }
                    //OBSERVA SE EXISTE UM BUG NESTA POSI��O
                    if(z<Main.cometas.size()){
                    	if(Main.cometas.get(z).x ==x&&Main.cometas.get(z).y==y){
                    		if(Main.cometas.get(z).modificador==1){
                    			triggerBUG = 1;
                    		}else{
                    			triggerBUG = -1;
                    		}
                    	}
                    }
                }
                if(trigger==1){
                    uniLine+=Main.BG_BLUE+planetasLista[i].Nome;
                }else if(trigger==2&&trigger!=1) {
                    uniLine+=Main.BG_YELLOW+"   ";
                }else{ //DESENHA A COR DOS BUGS E DEV NA TELA
                	if(triggerBUG==1){
                		uniLine+=Main.BG_GREEN;
                	}else if(triggerBUG==-1){
                		uniLine+=Main.BG_RED;
                	}else{
                		uniLine+=Main.BG_BLACK;
                	}
                    uniLine+=" . "+Main.BG_BLACK;
                }
                i=0;
                triggerBUG=0;
                trigger = 0;
            }
            System.out.print(uniLine+Main.BG_RESET);
            //Printar Linhas de instru��o ao lado do universo
            if(y<Main.insLinha.length){
            	System.out.print("  "+Main.BG_BLACK+Main.insLinha[y]+Main.BG_RESET);
            }
            System.out.print("\n");
            uniLine = "";
        }

        System.out.println("--------- Informa��es ---------");
        for(int y = 0;y<size;y++){
            System.out.println(Main.BG_BLUE+planetasLista[y].Nome+Main.BG_RESET+" \\ Anos: "+planetasLista[y].anoAtual+" \\ Dias: "+planetasLista[y].diaAtual+" \\ Horas: "+planetasLista[y].horaAtual+" \\ Velocidade de Transla��o Aproximada: "+planetasLista[y].getVelTranslacao());
        }
    }

}

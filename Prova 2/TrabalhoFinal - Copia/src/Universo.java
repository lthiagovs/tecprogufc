import javax.swing.*;
import java.awt.*;
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

	public void printUniverso(ArrayList <JButton> pFrames, int planetSize, ArrayList <Planeta> planetas){

		for (JButton pframe:pFrames) {

			pframe.setBackground(Color.black);
			pframe.setText(".");

			//Cometas
			for (Cometa c:Main.cometas) {
				if(pframe.getY()/planetSize==c.y&&pframe.getX()/planetSize==c.x){
					if(c.modificador>0){
						pframe.setBackground(Color.green);
						pframe.setText("DEV");
					}else if(c.modificador<0){
						pframe.setBackground(Color.red);
						pframe.setText("BUG");
					}
				}
			}



			//Planetas
			for (Planeta p:planetas) {
				if(pframe.getY()/planetSize==p.y&&pframe.getX()/planetSize==p.x&&p.deslocamento>0){
					pframe.setText(p.Nome);
					pframe.setBackground(p.color);
				}
			}

			//Estrela
			for(int i = 0;i< estrelaPosicoes.length;i++){
				if(pframe.getY()/planetSize==estrelaPosicoes[i].y&&pframe.getX()/planetSize==estrelaPosicoes[i].x){
					pframe.setBackground(Color.yellow);
					pframe.setText("");
				}
			}

		}

	}


}

import java.util.ArrayList;

public class Cometa extends Ponto{
	
	//Define o quanto o bug/desenvolvimento afeta a velocidade
	public int modificador;
	public int pTipo;
	
	public Cometa(int x, int y, int modificador){
		super(x,y);
		this.modificador = modificador;
		this.pTipo = modificador;
	}

	//Retorna o quadrante do cometa
	public static int getQuadrante(int x, int y){
		if(x<=7&&y<=7){
			return 0;
		}else if(x>=7&&y<=7){
			return 1;
		}else if(x<=7&&y>7){
			return 2;
		}else if(x>7&&y>7){
			return 3;
		}
		return 0;
	}
}

import java.util.ArrayList;

public class Cometa extends Ponto{
	
	//Define o quanto o bug/desenvolvimento afeta a velocidade
	public int modificador;
	
	public Cometa(int x, int y, int modificador){
		super(x,y);
		this.modificador = modificador;
	}

	//Exibe as colisões
	public static void exibirColisao(ArrayList<Cometa> cometas){
		System.out.println(Main.BG_YELLOW+"Lista de Colisao:"+Main.BG_RESET);
		for (int i = 0;i<cometas.size();i++){
			if(cometas.get(i).modificador==0){
				System.out.println("Colisao em: x-"+cometas.get(i).x+" / y-"+cometas.get(i).y);
			}
		}
	}
}

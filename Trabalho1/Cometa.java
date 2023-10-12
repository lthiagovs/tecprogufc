public class Cometa extends Ponto{
	
	//Define o quanto o bug/desenvolvimento afeta a velocidade
	public int modificador;
	
	public Cometa(int x, int y, int modificador){
		super(x,y);
		this.modificador = modificador;
	}
}

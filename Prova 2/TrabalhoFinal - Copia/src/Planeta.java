import java.awt.*;
import java.util.ArrayList;

public class Planeta extends Ponto{

    public int distancia;
    public int deslocamento;
    public int deslocamentoInicial;
    public String Nome;
    public String Nomec;
    public Color color;
    //public int x;
    //public int y;
    
    //Posições originais
    public int stdX;
    public int stdY;
    
    private float dRotacao;
    public int anoAtual;
    public int diaAtual;
    public float horaAtual;
    public int RX;
    public int RY;
    private int direcao = 0;
    //4 cantos do quadrado da orbita
    private Ponto bounds[];

    //Construtor da classe
    public Planeta(int distancia, int deslocamento, String Nome, float dRotacao, Color color, String nomec){
        super(0,0);
        this.distancia = distancia;
        this.deslocamento = deslocamento;
        this.deslocamentoInicial = deslocamento;
        this.bounds = new Ponto[4];
        this.Nome = Nome;
        this.dRotacao = dRotacao;
        this.color = color;
        this.Nomec = nomec;

        //Calculo de descrição de movimento

        //range x e y (px, py)
        this.RX = (4+(this.distancia-1))+this.distancia;
        this.RY = 2+(2*this.distancia);
    }

    //Insere o planeta na sua posição inicial, além de delimitar sua orbita
    public void iniciarPosicao(int tam_universoX, int tam_universoY){
        this.x = ((tam_universoX-1)/2);
        this.y = (tam_universoY/2)-this.distancia-1;
        this.stdX = this.x;
        this.stdY = this.y;

        //((tam_universoX-1)/2) + ((this.RX-1)/2)
        //problemas com a borda esquerda para planetas com orbitas maiores

        //Delimitações das orbitas dos planetas em formato de quadrado
        bounds[0] = new Ponto(((tam_universoX-1)/2) - ((this.RX-1)/2),(tam_universoY/2)-this.distancia-1);
        bounds[1] = new Ponto(((tam_universoX-1)/2) - ((this.RX-1)/2),(tam_universoY/2)+this.distancia);
        bounds[2] = new Ponto(((tam_universoX-1)/2) + ((this.RX-1)/2),(tam_universoY/2)+this.distancia);
        bounds[3] = new Ponto(((tam_universoX-1)/2) + ((this.RX-1)/2),(tam_universoY/2)-this.distancia-1);

    }

    //Busca a maior distancia entre os planetas
    public static int getTamanhoMax(ArrayList<Planeta> planetas, int size){
        int max = 0;
        for(int i = 0; i < size;i++){
            if(planetas.get(i).distancia>max){
                max = planetas.get(i).distancia;
            }
        }
        return max;
    }

    //Verifica se o planeta esta no limite da orbita e retorna sua nova direção
    public int limiteOrbita(){
        int limite = -1;

        for(int i = 0;i<4;i++){
            if(this.x==bounds[i].x&&this.y==bounds[i].y){
                if(i==3) return 0;
                return i+1;
            }
        }
        return limite;
    }

    //Movimenta o planeta
    public void moverPlaneta(){
        int borda = limiteOrbita();
        if(borda!=-1) this.direcao = borda;

        switch (this.direcao) {
            case 0:
                this.x -= 1;
                break;
            case 1:
                this.y += 1;
                break;
            case 2:
                this.x += 1;
                break;
            case 3:
                this.y -= 1;
                break;
            default:
                break;
        }
        for (int i = 0;i<Main.cometas.size();i++) {
            if(this.x == Main.cometas.get(i).x && this.y == Main.cometas.get(i).y && Main.cometas.get(i).modificador!=0 && this.deslocamento>0){
                this.deslocamento+=Main.cometas.get(i).modificador;
                Main.cometas.get(i).modificador=0;
                Colisao c = new Colisao(this,Main.cometas.get(i),this.x,this.y);
                Main.colisoes.add(c);
            }
        }

    }
    
    //Reinicia a orbita de todos os planetas
    public static void resetPlanetas(ArrayList<Planeta> planetas, int tamanho){
    	for(int i = 0;i<tamanho;i++){
            planetas.get(i).diaAtual=0;
            planetas.get(i).horaAtual=0;
            planetas.get(i).anoAtual=0;
            planetas.get(i).x=planetas.get(i).stdX;
            planetas.get(i).y=planetas.get(i).stdY;
            planetas.get(i).direcao = 0;
            planetas.get(i).deslocamento = planetas.get(i).deslocamentoInicial;
    	}
    }
    
    //Retorna a velocidade translação
    public float getVelTranslacao(){
        if(this.deslocamento==0){
            return 0;
        }
    	float resultado = (((this.RX*2 + this.RY*2)-5)/ ((float)this.deslocamento));
    	return resultado;
    }
    
    //Movimenta um conjunto de planetas x vezes
    public static void moverPlanetas(ArrayList<Planeta> planetas, int tamanho, int vezes){
        Planeta.resetPlanetas(planetas, tamanho);
    	for(int x = 0;x<vezes;x++){
            for(int p = 0;p<tamanho;p++){
                planetas.get(p).horaAtual +=  planetas.get(p).dRotacao;
                while(planetas.get(p).horaAtual>=24){
                    planetas.get(p).horaAtual-=24;
                    planetas.get(p).diaAtual+=1;
                }
                while( planetas.get(p).diaAtual>=360){
                    planetas.get(p).anoAtual+=1;
                    planetas.get(p).diaAtual-=360;
                }
                for(int i = 0;i< planetas.get(p).deslocamento;i++) {
                    planetas.get(p).moverPlaneta();
                }
            }
        }
    }



}
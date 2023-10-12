public class Planeta {

    public int distancia;
    public int deslocamento;
    public String Nome;
    public int x;
    public int y;
    
    //Posições originais
    public int stdX;
    public int stdY;
    
    private float dRotacao;
    public int anoAtual;
    public int diaAtual;
    public float horaAtual;
    private int RX;
    private int RY;
    private int direcao = 0;
    //4 cantos do quadrado da orbita
    private Ponto bounds[];


    //DESCRIÇÃO PARA MOVIMENTO DOS PLANETAS NA HORIZONTAL (PX): (4+(dis-1))+dis
    //DESCRIÇÃO PARA MOVIMENTO DOS PLANETAS NA VERTICAL (PY): 2+(2*dis)

    //DESCRIÇÃO DA POSIÇÃO INICIAL X e Y EM RELAÇÃO AO À DISTANCIA MÁXIMA:
    // em y: (tam_UNIVERSO_Y/2)-D
    // em x: ((tam_UNIVERSO_X-1) / 2) + 1

    //DESCRIÇÃO DO MOVIMENTO DOS PLANETAS EM FORMATO DE QUADRADO
    //FORMULAS PARA ENCONTRAR AS BORDAS DOS QUADRADOS EM RELAÇÃO AO TAMANHO DO UNIVERSO
    //((tam_universoX-1)/2)+1 = centroX
    //tam_universoY/2 = centroY
    //---------------------------------------------------
    //horizontal anterior = {centroX - bEC}
    //(range_x_planeta-1)/2 = bEC

    //horizontal posterior = {centroX + bEC}
    //(range_x_planeta-1)/2 = bEC

    //vertical anterior  = {centroY - distanciaPlan}

    //vertical posterior = {(centroY+1) + distanciaPlan}
    //---------------------------------------------------


    //DESCRIÇÃO DAS QUATRO POSIÇÕES FINAIS EM RELAÇÃO À DISTANCIA MÁXIMA: ?

    //Construtor da classe
    public Planeta(int distancia, int deslocamento, String Nome, float dRotação){
        this.distancia = distancia;
        this.deslocamento = deslocamento;
        this.bounds = new Ponto[4];
        this.Nome = Nome;
        this.dRotacao = dRotação;

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
    public static int getTamanhoMax(Planeta planetasLista[], int size){
        int max = 0;
        for(int i = 0; i < size;i++){
            if(planetasLista[i].distancia>max){
                max = planetasLista[i].distancia;
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
    }
    
    //Reinicia a orbita de todos os planetas
    public static void resetPlanetas(Planeta conjuntoPlanetas[], int tamanho){
    	for(int i = 0;i<tamanho;i++){
    		conjuntoPlanetas[i].diaAtual=0;
    		conjuntoPlanetas[i].horaAtual=0;
    		conjuntoPlanetas[i].anoAtual=0;
    		conjuntoPlanetas[i].x=conjuntoPlanetas[i].stdX;
    		conjuntoPlanetas[i].y=conjuntoPlanetas[i].stdY;
    		conjuntoPlanetas[i].direcao = 0;
    	}
    }
    
    //Retorna a velocidade translação
    public float getVelTranslacao(){
    	float resultado = (((this.RX*2 + this.RY*2)-5)/ ((float)this.deslocamento));
    	return resultado;
    }
    
    //Movimenta um conjunto de planetas x vezes
    public static void moverPlanetas(Planeta conjuntoPlanetas[], int tamanho, int vezes){
        Planeta.resetPlanetas(conjuntoPlanetas, tamanho);
    	for(int x = 0;x<vezes;x++){
            for(int p = 0;p<tamanho;p++){
                conjuntoPlanetas[p].horaAtual += conjuntoPlanetas[p].dRotacao;
                while(conjuntoPlanetas[p].horaAtual>=24){
                    conjuntoPlanetas[p].horaAtual-=24;
                    conjuntoPlanetas[p].diaAtual+=1;
                }
                while(conjuntoPlanetas[p].diaAtual>=360){
                    conjuntoPlanetas[p].anoAtual+=1;
                    conjuntoPlanetas[p].diaAtual-=360;
                }
                for(int i = 0;i<conjuntoPlanetas[p].deslocamento;i++) {
                    conjuntoPlanetas[p].moverPlaneta();
                }
            }
        }
    }


}
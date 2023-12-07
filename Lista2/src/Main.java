public class Main {
//LISTA OK
    public int somatorio(int vetor[]){
        int sum = 0;

        for(int i = 1;i<vetor.length-1;i++){
            sum+=vetor[i];
        }

        return sum;
    }

    public void pares(int nInicial, int nFinal){
        for(int i = nInicial;i<=nFinal;i++){
            if(i%2==0){
                System.out.println(i);
            }
        }
    }

    public double media(double v1[], double v2[]){
        double media = 0.0;
        int maxSize;
        for(int i =0;i<v1.length;i++){
            media+=v1[i];
        }
        for(int i =0;i<v2.length;i++){
            media+=v2[i];
        }
        media=media/(v1.length+v2.length);
        return media;
    }

    public double mediaPonderada(double notas[], int pesos[]){
        double media = 0.0;
        int tPesos = 0;
        for(int i = 0;i<notas.length;i++){
            tPesos+=pesos[i];
            media+=(pesos[i]*notas[i]);
        }
        media/=tPesos;
        return media;
    }

    public int contagem(int v1[], int v2[], int num){
        int contagem = 0;
        for(int i = 0;i<v1.length;i++){
            if(v1[i]==num){
                contagem++;
            }
            if(v2[i]==num){
                contagem++;
            }
        }
        return contagem;
    }

    public int[] copia(int v1[]){
        int copia[] = new int[v1.length];
        for(int i = 0;i<v1.length;i++){
            copia[i] = v1[i];
        }
        return copia;
    }

    public void ordenar(int v1[]){
        for(int x = 0;x<v1.length;x++){
            for(int y = 0;y<v1.length;y++){
                if(v1[y]<v1[x]){
                    int aux = v1[y];
                    v1[y] = v1[x];
                    v1[x] = aux;
                }
            }
        }
    }

    public boolean verificarOrdenado(int v1[]){
        for(int i = 1;i<v1.length;i++){
            if(v1[i]<v1[i-1]){
                return false;
            }
        }
        return true;
    }

    public int kMaior(int v1[], int k){
        int v2[] = v1;
        ordenar(v2);
        int num = 1;
        for(int i = v1.length-1;i>=0;i--){
            if(num==k){
                return v1[i];
            }
            num++;
        }
        return 0;
    }

    public int kMenor(int v1[], int k){
        int v2[] = v1;
        ordenar(v2);
        int num = 1;
        for(int i = 0;i<v1.length;i++){
            if(num==k){
                return v1[i];
            }
            num++;
        }
        return 0;
    }

    public boolean igualdade(int v1[], int v2[]){
        for(int i = 0;i<v1.length;i++){
            if(v1[i]!=v2[i]){
                return false;
            }
        }
        return true;
    }

    public int pot(int b, int e){
        int pot = 0;
        for(int i = 0;i<e;i++){
            pot+=b*b;
        }
        return pot;
    }

    public boolean primo(int num){
        for(int i = 2;i<num;i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }

    public int[] ocorrencias(int v1[], int v2[]){
        int nOcorrencias = 0;
        for(int i = 0;i<v1.length;i++){
            for(int x = 0;i<v2.length;x++){
                if(v1[i]==v2[x]){
                    nOcorrencias++;
                }
            }
        }
        int ocorrencias[] = new int[nOcorrencias];
        nOcorrencias = 0;
        for(int i = 0;i<v1.length;i++){
            for(int x = 0;i<v2.length;x++){
                if(v1[i]==v2[x]){
                    ocorrencias[nOcorrencias] = v1[i];
                    nOcorrencias++;
                }
            }
        }
        return ocorrencias;
    }

    public int[] remove(int v1[], int elemento){
        int nRemocao = 0;
        for(int i = 0;i<v1.length;i++){
            if(v1[i]==elemento){
                v1[i] = -1;
                nRemocao++;
            }
        }
        int nVetor[] = new int[v1.length-nRemocao];
        nRemocao = 0;
        for(int i = 0;i<v1.length;i++){
            if(v1[i]!=-1){
                nVetor[nRemocao] = v1[i];
                nRemocao++;
            }
        }
        return nVetor;
    }

    public boolean palindromo(String palavra){
        char pVetor[] = palavra.toCharArray();

        for(int i = 0;i<pVetor.length/2;i++){
            if(pVetor[i]!=pVetor[pVetor.length-1-i]){
                return false;
            }
        }
        return true;

    }

    //17) = primo

    public String inverteString(String palavra){
        char vPalavra[] = palavra.toCharArray();
        char aux;
        for(int i = 0;i<vPalavra.length/2;i++){
            aux = vPalavra[i];
            vPalavra[i] = vPalavra[vPalavra.length-1-i];
            vPalavra[vPalavra.length-1-i] = aux;
        }
        return vPalavra.toString();
    }

    public double jurosCompostos(double valor, double juros, int periodo){
        double vTotal = valor;
        for(int i = 0;i<periodo;i++){
            vTotal*=juros;
        }
        return vTotal;
    }

    public boolean nPerfeito(int num){
        int soma = 0;
        for(int i = 1;i<num;i++){
            if(num%i==0){
                soma+=i;
            }
        }
        if(soma==num){
            return true;
        }
        return false;
    }

    public int[] dVetor(int vetor[]){
        int nVetor[] = vetor;
        do{
            int auxVetor[] = new int[nVetor.length-1];
            for(int i = 0;i<auxVetor.length;i++){
                auxVetor[i] = nVetor[i];
            }
            nVetor = auxVetor;
        }while(nVetor.length<1);
        return nVetor;
    }

    //A)
    public void imprimeElementos(int col){
        String ln = "";
        for(int i = 0;i<col;i++){
            ln+=i+" ";
            System.out.println(ln);
        }
    }

    public static void main(String[] args) {


    }
}
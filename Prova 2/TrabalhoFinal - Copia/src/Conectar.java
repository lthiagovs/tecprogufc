import java.sql.*;
import java.util.ArrayList;

public class Conectar {

    public static final String URL = "jdbc:mysql://da_java.mysql.dbaas.com.br:3306/da_java";
    public static final String USER = "da_java";
    public static final String PASS = "Tecnicas*2023@";

    public static final String NOME = "Luis Thiago Vasconcelos de Farias";
    public static final String MATRICULA = "538220";



    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //AJUSTAR DADOS SALVOS
    public static void gravarDados(Connection con,String filename, ArrayList<Planeta> planetas){
        PreparedStatement stmt;
        String sql = "INSERT INTO da_java.javalar (`nome`, `matricula`, `nome_arquivo`, `bug_python`, `bug_javascript`, `bug_ruby`, `bug_php`, `bug_csharp`, `bug_cmais`, `bug_c`, `dev_python`, `dev_javascript`, `dev_ruby`, `dev_php`, `dev_csharp`, `dev_cmais`, `dev_c`, `v_python`, `v_javascript`, `v_ruby`, `v_php`, `v_csharp`, `v_cmais`, `v_c`, `d_python`, `d_javascript`, `d_ruby`, `d_php`, `d_csharp`, `d_cmais`, `d_c`, `a_python`, `a_javascript`, `a_ruby`, `a_php`, `a_csharp`, `a_cmais`, `a_c`, `bug_q1`, `bug_q2`, `bug_q3`, `bug_q4`, `dev_q1`, `dev_q2`, `dev_q3`, `dev_q4`) VALUES (";
        sql+="\'"+NOME+"\',"+"\'"+MATRICULA+"\',\'"+filename+"\',\'";
        int[] pBUGS = new int[7];
        int[] pDEVS = new int[7];
        int[] Bquadrantes = new int[4];
        int[] Dquadrantes = new int[4];
        int insert = 0;
        //Contagem de BUGS e DEVS de planetas e quadrantes
        for (Colisao c:Main.colisoes) {


            switch (c.p.Nome) {
                case "Py" -> insert = 0;
                case "Js" -> insert = 1;
                case "Rby" -> insert = 2;
                case "PHP" -> insert = 3;
                case "C#" -> insert = 4;
                case "C++" -> insert = 5;
                case "C" -> insert = 6;
                default -> {
                }
            }
            if(c.c.pTipo>0){
                pDEVS[insert]+=1;
                Dquadrantes[Cometa.getQuadrante(c.x,c.y)]+=1;
            }else if(c.c.pTipo<0){
                pBUGS[insert]+=1;
                Bquadrantes[Cometa.getQuadrante(c.x,c.y)]+=1;
            }
        }

        //ESCREVER NO COMANDO SQL

        //BUGS E DEVS DE CADA PLANETA
        for (int pBUG : pBUGS) {
            sql += pBUG + "\',\'";
        }
        for (int pDEV : pDEVS) {
            sql += pDEV + "\',\'";
        }
        // CONCERTAR VELOCIDADE,HORARIO E ETC

        for (Planeta p:planetas) {
            sql += p.getVelTranslacao()+ "\',\'";
        }
        for (Planeta p:planetas) {
            sql += p.diaAtual+ "\',\'";
        }
        for (Planeta p:planetas) {
            sql += p.anoAtual+ "\',\'";
        }
        // QUEBRA GAI
        //for(int i = 0;i<21;i++){
        //    sql += i+ "\',\'";
        //}

        //QUADRANTES
        for(int i = 0;i<4;i++){
            sql+=Bquadrantes[i]+ "\',\'";
        }

        for(int i = 0;i<4;i++){
            sql+=Dquadrantes[i];
            if(i!=3){
                sql+="\',\'";
            }
        }
        sql+="');";
        System.out.println(sql);

        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("OK");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //AJUSTAR INFO
    public static void lerDados(Connection con, ArrayList<Planeta> planetas) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM da_java.javalar;");
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> nomes = new ArrayList<>();
            int[] mortes = new int[7];
            int[] vida = new int[7];
            int[] dQuadrante = new int[4];
            int[] bQuadrante = new int[4];
            int tInstantes = 0;
            double[] mVelocidade = new double[7];
            int tBugs = 0;
            int tDevs = 0;
            double tHoras = 0.0;
            int tAnos = 0;

            //LER DADOS
            while (rs.next()) {
                tInstantes++;
                //NOMES DE ALUNOS
                nomes.add(rs.getString("nome")+" - "+rs.getString("matricula"));

                //INFO SOBRE PLANETAS
                for(int i = 0;i<planetas.size();i++){
                    //CONTAGEM BUGS, DEVS, HORAS E ANOS
                    tDevs+=rs.getInt("dev_"+planetas.get(i).Nomec);
                    tBugs+= rs.getInt("bug_"+planetas.get(i).Nomec);
                    tHoras+= rs.getDouble("d_"+planetas.get(i).Nomec);
                    tAnos+= rs.getInt("a_"+planetas.get(i).Nomec);

                    //PLANETA QUE MAIS MORREU
                    if(planetas.get(i).deslocamentoInicial+rs.getInt("dev_"+planetas.get(i).Nomec)-rs.getInt("bug_"+planetas.get(i).Nomec)<=0){
                        mortes[i]++;
                    }

                    //CONTAGEM DE VIDA DOS PLANETAS
                    vida[i] += planetas.get(i).deslocamentoInicial+rs.getInt("dev_"+planetas.get(i).Nomec)-rs.getInt("bug_"+planetas.get(i).Nomec);

                    //CONTAGEM DA VELOCIDADE (PRECISA DIVIDIR POR INSTANTES)
                    mVelocidade[i] += rs.getDouble("v_"+planetas.get(i).Nomec);
                }

                //INFO SOBRE QUADRANTES
                for(int i = 0;i<4;i++){
                    bQuadrante[i]+=rs.getInt("bug_q"+(i+1));
                    dQuadrante[i]+=rs.getInt("dev_q"+(i+1));
                }


                //System.out.println(var);
            }

            // ------------ TRATAMENTO ------------
            int countAtual = 0;
            int melhorCount = 0;
            int mI = 0;

            //ALUNO COM MAIS INSTANTES
            for(int i = 0;i<nomes.size();i++){

                for(int i2 = 0;i2<nomes.size();i2++){
                    if(nomes.get(i).equals(nomes.get(i2))){
                        countAtual+=1;
                    }
                }

                if(melhorCount<countAtual){
                    melhorCount = countAtual;
                    mI = i;
                }
                countAtual = 0;

            }
            Main.data+=nomes.get(mI);

            //PLANETA QUE MAIS MORREU
            int mMorte = 0;
            int iMorte = 0;
            for(int i = 0;i<7;i++){
                if(mortes[i]>mMorte){
                    mMorte = mortes[i];
                    iMorte = i;
                }
            }
            Main.data+=", "+planetas.get(iMorte).Nome;

            //PLANETA COM MAIS VIDA
            int mVida = 0;
            int iVida = 0;
            for(int i = 0;i<7;i++){
                if(vida[i]>mVida){
                    mVida = vida[i];
                    iVida = i;
                }
            }
            Main.data+=", "+planetas.get(iVida).Nome;

            //QUADRANTE BUGS
            int qBugs = 0;
            int iBugs = 0;
            for(int i = 0;i<4;i++){
                if(bQuadrante[i]>qBugs){
                    qBugs = bQuadrante[i];
                    iBugs = 1;
                }
            }
            Main.data+=", "+bQuadrante[iBugs];

            //QUADRANTE DEVS
            int qDevs = 0;
            int iDevs = 0;
            for(int i = 0;i<4;i++){
                if(dQuadrante[i]>qDevs){
                    qDevs = dQuadrante[i];
                    iDevs = 1;
                }
            }
            Main.data+=", "+bQuadrante[iDevs];

            //INSTANTES
            Main.data+=", "+tInstantes;

            //MEDIA V CADA PLANETA
            Main.data+=", "+planetas.get(0).Nome+" - "+mVelocidade[0];
            Main.data+=", "+planetas.get(1).Nome+" - "+mVelocidade[1];
            Main.data+=", "+planetas.get(2).Nome+" - "+mVelocidade[2];
            Main.data+=", "+planetas.get(3).Nome+" - "+mVelocidade[3];
            Main.data+=", "+planetas.get(4).Nome+" - "+mVelocidade[4];
            Main.data+=", "+planetas.get(5).Nome+" - "+mVelocidade[5];
            Main.data+=", "+planetas.get(6).Nome+" - "+mVelocidade[6];


            //TOTAL BUGS
            Main.data+=", "+tBugs;

            //TOTAL DEVS
            Main.data+=", "+tDevs;

            //TOTAL HORAS
            Main.data+=", "+tHoras;

            //TOTAL ANOS
            Main.data+=", "+tAnos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

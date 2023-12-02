import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main{

    public static ArrayList<Cometa> cometas = new ArrayList<>();
    public static ArrayList<Colisao> colisoes = new ArrayList<>();

    public static int[][] instantes;
    public static int Instante = 0;
    public static boolean isFile = false;
    public static boolean isData = false;
    public static Random random = new Random();
    public static String data = "";

    public static void lerArquivo(String path){
        Instante = 0;
        FileInputStream fis = null;
        int c;
        int qtdInstantes = 0;

        try {
            fis = new FileInputStream(path);
            //Quantidade de instantes
            while((c = fis.read()) != -1) {
                if(c=='\n'){
                    qtdInstantes++;
                }
            }
            instantes = new int[qtdInstantes][10];
            System.out.println(qtdInstantes);
            //System.out.println("Quantidade de instantes: "+qtdInstantes);
            qtdInstantes = -1;
            int infoCount = 0;
            boolean trigger = false;
            String insert = "";
            //fis.reset();
            fis.close();
            fis = new FileInputStream(path);
            while((c = fis.read()) != -1) {

                if(trigger){
                    if ((char)c == '\n') {
                        if(infoCount>=9){
                            instantes[qtdInstantes][infoCount] = Integer.parseInt(insert.replace("-35",""));
                        }
                        infoCount = 0;
                        insert="";
                        qtdInstantes++;
                        //System.out.print("\n");
                    }else if ((char)c == ',') {
                        instantes[qtdInstantes][infoCount] = Integer.parseInt(insert);
                        infoCount++;
                        insert="";
                    } else {
                        insert += c-'0';
                        //System.out.print(c+",");
                    }
                }else{
                    if(c=='V'){
                        c = fis.read();
                        trigger = true;
                    }
                    //System.out.print((char)c);
                }
            }
            instantes[qtdInstantes][infoCount] = Integer.parseInt(insert.replace("-35",""));
            fis.close();

            //System.out.println("\nQuantidade de instantes: "+qtdInstantes);
            //System.out.println("\nTeste: "+(char)instantes[0][0]);

        }catch (FileNotFoundException ex){
            System.out.println("Arquivo de entrada nao encontrado.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void inserirCometa(Universo mUniverso){
        //BUGS - 8
        for(int i = 0;i<instantes[Instante][8];i++){
            int xC, yC, modificadorC = -1;
            xC = random.nextInt(mUniverso.distMaxX-1);
            yC = random.nextInt(mUniverso.distMaxY-1);
            Cometa nCometaC = new Cometa(xC,yC,modificadorC);
            cometas.add(nCometaC);
        }
        //DEVS - 9
        for(int i = 0;i<instantes[Instante][9];i++){
            int xC, yC, modificadorC = 1;
            xC = random.nextInt(mUniverso.distMaxX-1);
            yC = random.nextInt(mUniverso.distMaxY-1);
            Cometa nCometaC = new Cometa(xC,yC,modificadorC);
            cometas.add(nCometaC);
        }
    }
    public static void proxInstante(ArrayList<Planeta> planetas, Universo mUniverso){
        if(Instante<instantes.length){
            int planeta = 1;
            int instanteP;
            cometas.clear();
            inserirCometa(mUniverso);
            for (Planeta p:planetas) {
                instanteP = instantes[Instante][planeta];
                for(int i = 0;i<instanteP;i++) {
                    for (int d = 0; d < p.deslocamento; d++) {
                        p.moverPlaneta();
                    }
                }
                planeta+=1;
            }
            Instante++;
        }

    }


    public static void main(String[] args) {

        // ---------- System ----------

        //Connection con = Conectar.getConnection();


        //Conectar.closeConnection(con);

        //Iniciando planetas
        ArrayList<Planeta> planetas = new ArrayList<>();

        planetas.add(new Planeta(1,4, "Py",24f,Color.blue,"python"));
        planetas.add(new Planeta(2,3, "Js",10f,Color.yellow,"javascript"));
        planetas.add(new Planeta(3,2, "Rby",48f,Color.red,"ruby"));
        planetas.add(new Planeta(4,2, "PHP",60f,Color.cyan,"php"));
        planetas.add(new Planeta(5,1, "C#",4f,Color.magenta,"csharp"));
        planetas.add(new Planeta(6,2, "C++",0.5f,Color.white,"cmais"));
        planetas.add(new Planeta(7,10, "C",0.5f,Color.green,"c"));

        //Iniciando o universo
        Universo mainUniverso = new Universo();
        mainUniverso.setTamanhoMax(Planeta.getTamanhoMax(planetas,planetas.size()));
        mainUniverso.constroiUniverso();


        //Posicionando Planetas
        for (Planeta p:planetas) {
            p.iniciarPosicao(mainUniverso.distMaxX,mainUniverso.distMaxY);
        }

        // --------- Variables ---------
        JFrame mainFrame = new JFrame("Sistema JavaLar");
        JPanel planetsPanel = new JPanel();

        int planetsSize = 40;
        int instante = 0;

        ArrayList<JButton> planetFrames = new ArrayList<JButton>();
        //Add Planet Frames to Array List
        for(int y = 0;y<mainUniverso.distMaxY+1;y++){
            for(int x = 0;x<mainUniverso.distMaxX;x++){
                JButton nPlanet = new JButton();
                nPlanet.setBounds(x*planetsSize,y*planetsSize,planetsSize,planetsSize);
                nPlanet.setBorder(null);
                planetFrames.add(nPlanet);
            }
        }

        //Buttons
        JButton b1 = new JButton("Processar proximo instante");
        JButton b2 = new JButton("Ler novo arquivo de entrada");
        JButton b3 = new JButton("Gravar Relatorio");
        JButton b4 = new JButton("Ler dados de outros participantes");
        JButton b5 = new JButton("Gravar arquivo de saida");
        JLabel l1 = new JLabel("");
        JLabel l2 = new JLabel("");
        
        // --------- Setup ---------
        planetsPanel.setBounds(0,0,40*(mainUniverso.distMaxX+1),40*(mainUniverso.distMaxY+1));
        planetsPanel.setLayout(null);
        planetsPanel.setBackground(Color.white);

        //Add Planet Frames to Panel
        for (JButton pFrame:planetFrames) {
            planetsPanel.add(pFrame);
        }

        //Buttons
        b1.setBounds(planetsPanel.getWidth()+10,0,250,80);
        b1.setBackground(Color.green);
        b2.setBounds(planetsPanel.getWidth()+10,88,250,80);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isFile){
                    proxInstante(planetas,mainUniverso);
                    mainUniverso.printUniverso(planetFrames,planetsSize,planetas);
                    l2.setText("Instante: "+Instante+"/"+instantes.length);
                }else{
                    l1.setText("Nenhum arquivo foi selecionado!");
                }
            }
        });
        b2.setBackground(Color.green);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setDialogTitle("Selecione um arquivo:");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Instantes","csv");
                fileChooser.setFileFilter(filter);
                int fileReturn = fileChooser.showOpenDialog(planetsPanel);
                if(fileReturn == JFileChooser.APPROVE_OPTION){
                    l1.setText("Arquivo lido com sucesso!");
                    File file = fileChooser.getSelectedFile();
                    lerArquivo(file.getAbsolutePath());
                    l2.setText("Instante: "+Instante+"/"+instantes.length);
                    Planeta.resetPlanetas(planetas,planetas.size());
                    cometas.clear();
                    mainUniverso.printUniverso(planetFrames,planetsSize,planetas);
                    isFile = true;
                }else{
                    l1.setText("Arquivo invalido!");
                }
            }
        });
        b3.setBounds(planetsPanel.getWidth()+10,176,250,80);
        b3.setBackground(Color.green);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isFile){
                    Connection con = Conectar.getConnection();
                    Conectar.gravarDados(con,("AE_"+instantes.length),planetas);
                    Conectar.closeConnection(con);
                }else{
                    l1.setText("Nenhum arquivo foi selecionado!");
                }
            }
        });
        b4.setBounds(planetsPanel.getWidth()+10,264,250,80);
        b4.setBackground(Color.green);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = Conectar.getConnection();
                Conectar.lerDados(con,planetas);
                Conectar.closeConnection(con);
                l1.setText("Dados recebidos!");
                isData = true;
            }
        });
        b5.setBounds(planetsPanel.getWidth()+10,352,250,80);
        b5.setBackground(Color.green);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isData){
                    System.out.println(data);
                }else{
                    l1.setText("Nenhum dado recebido!");
                }
            }
        });
        l1.setBounds(planetsPanel.getWidth()+10,400,250,80);
        l2.setBounds(planetsPanel.getWidth()+10,460,250,80);

        mainFrame.setSize(planetsPanel.getWidth()+300,planetsPanel.getHeight());
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

        mainFrame.add(planetsPanel);
        mainFrame.add(b1);
        mainFrame.add(b2);
        mainFrame.add(b3);
        mainFrame.add(b4);
        mainFrame.add(b5);
        mainFrame.add(l1);
        mainFrame.add(l2);

        //Setup de Universo
        Planeta.moverPlanetas(planetas,planetas.size(),instante);
        mainUniverso.printUniverso(planetFrames,planetsSize,planetas);


        //Testes


    }
}
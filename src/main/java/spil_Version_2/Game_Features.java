package spil_Version_2;


import spil_Version_2.cards.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Game_Features {

    private static int spiller1Balance;
    private static int spiller2Balance;
    private static int spiller3Balance;
    private static int spiller4Balance;
    private static int spiller5Balance;
    private static int spiller6Balance;

        private static Integer players=-1;
        private static final Object monitor = new Object();
        private static boolean b =false;
        public Game_Features()
        {

        }
        public static ArrayList<Parent_Card> cards()
        {
//tilføjer kort til array, og gør rækkefølgen ranom
            ArrayList<Parent_Card> cardArray = new ArrayList<Parent_Card>();
            ArrayList<Parent_Card> randomCardArray = new ArrayList<Parent_Card>();
            cardArray.add(new Matador_Legat(40000, true, "Tillykke, du har vundet Matador-Legatet; 40000kr!"));

            //chancekort
            // ryk til start, uden gevinst
            cardArray.add(new RykTilStart_Card(0));
            cardArray.add(new RykTilStart_Card(0));



           //ryk frem eller tilbage
            int[] spacesToMove = {3,-3,-3};
            for (int space : spacesToMove) {
                cardArray.add(new RykFelter_Card(space));
            }




            //modtag eller betal: false=betal, true=modtag
            cardArray.add(new Straf_Eller_Gevinst(200, false, "Betal kr 200 for levering af 2 kasser øl"));
            cardArray.add(new Straf_Eller_Gevinst(3000,false , "Betal 3000 for reparation af deres vogn"));
            cardArray.add(new Straf_Eller_Gevinst(3000,false , "Betal 3000 for reparation af deres vogn"));
            cardArray.add(new Straf_Eller_Gevinst(1000,false , "De har købt 4 nye dæk til Deres vogn, betal kr 1000"));
            cardArray.add(new Straf_Eller_Gevinst(200,false , "De har fået en parkeringsbøde, betal kr 200 i bøde"));
            cardArray.add(new Straf_Eller_Gevinst(1000,false , "Betal deres bilforsikring, kr 1000"));
            cardArray.add(new Straf_Eller_Gevinst(200  ,false , "De har været udenlands og købt for mange smøger, betal kr 200 i told."));
            cardArray.add(new Straf_Eller_Gevinst(2000,false , "Tandlægeregning, betal kr 2000."));
            cardArray.add(new Straf_Eller_Gevinst(500,true , "De har vundet i klasselotteriet. Modtag 500 kr."));
            cardArray.add(new Straf_Eller_Gevinst(500,true , "De har vundet i klasselotteriet. Modtag 500 kr."));
            cardArray.add(new Straf_Eller_Gevinst(1000,true , "De modtager Deres aktieudbytte. Modtag kr 1000 af banken"));
            cardArray.add(new Straf_Eller_Gevinst(1000,true , "De modtager Deres aktieudbytte. Modtag kr 1000 af banken"));
            cardArray.add(new Straf_Eller_Gevinst(1000,true , "De modtager Deres aktieudbytte. Modtag kr 1000 af banken"));
            cardArray.add(new Straf_Eller_Gevinst(3000,true , "Kommunen har eftergivet et kvartals skat. Hæv i banken 3000 kr."));
            cardArray.add(new Straf_Eller_Gevinst(1000,true , "De have en række med elleve rigtige i tipning, modtag kr 1000"));
            cardArray.add(new Straf_Eller_Gevinst(1000,true , "Grundet dyrtiden har De fået gageforhøjelse, modtag kr 1000."));
            cardArray.add(new Straf_Eller_Gevinst(1000,true , "Deres præmieobligation er udtrykket. De modtager 1000 kr af banken."));

            cardArray.add(new Straf_Eller_Gevinst(1000,true , "Deres præmieobligation er udtrykket. De modtager 1000 kr af banken."));
            cardArray.add(new Straf_Eller_Gevinst(1000,true , "De har solg nogle gamle møbler på auktion. Modtag 1000 kr af banken."));
            cardArray.add(new Straf_Eller_Gevinst(200,true , "Værdien af egen avl fra nyttehaven udgør 200 som de modtager af banken"));
            cardArray.add(new Straf_Eller_Gevinst(300,false , "Betal for vognvask og smøring kr 300"));
            cardArray.add(new Straf_Eller_Gevinst(1000,false , "De har kørt frem for “fuldt stop”, Betal 1000 kroner i bøde"));


            cardArray.add(new MovePlayerCard( 11,"Ryk frem til Frederiksberg Allé. Hvis De passere START, indkasser da 4000 kr.\n"));
            cardArray.add(new MovePlayerCard(15 ,"Tag med Mols-Linien, flyt brikken frem og hvis De passerer START indkassér da kr 4000.\n"));
            cardArray.add(new MovePlayerCard( 25,"Ryk frem til Grønningen, hvis De passerer start indkasser da kr 4000\n"));
            cardArray.add(new MovePlayerCard( 32,"Ryk frem til Vimmelskaftet, hvis de passerer start indkasser da kr 4000\n"));
            cardArray.add(new MovePlayerCard( 19,"Ryk frem til Strandvejen. Hvis De passere START, indkasser da 4000 kr.\n"));











            Random rand = new Random();
            while (true) {
                int random;
                if (cardArray.size()==1)
                {
                    random =1;
                }
                else {
                    random = rand.nextInt(1, cardArray.size());
                }
                randomCardArray.add(cardArray.get(random-1));
                cardArray.remove(random-1);
                if (cardArray.size()==0)
                {
                    break;
                }
            }
            return randomCardArray;
        }

        public static Player[] playerstoadd(){
            int pl = playercountadd();
            int spi1 = 30000;
            spiller1Balance = spi1;
            int spi2 = 30000;
            spiller2Balance = spi2;
            int spi3 = 30000;
            spiller3Balance = spi3;
            int spi4 = 30000;
            spiller4Balance = spi4;
            int spi5 = 30000;
            spiller5Balance = spi5;
            int spi6 = 30000;
            spiller6Balance = spi6;
            if (pl == 3) {
                Player[] plA3 = new Player[3];
                plA3[0] = new Player("Spiller 1", spi1 , 0);
                plA3[1] = new Player("Spiller 2", spi2, 0);
                plA3[2] = new Player("Spiller 3", spi3, 0);

                return plA3;
            } else if (pl == 4) {
                Player[] plA4 = new Player[4];
                plA4[0] = new Player("Spiller 1", spi1, 0);
                plA4[1] = new Player("Spiller 2", spi2, 0);
                plA4[2] = new Player("Spiller 3", spi3, 0);
                plA4[3] = new Player("Spiller 4", spi4, 0);
                return plA4;
            } else if (pl == 5) {
                Player[] plA5 = new Player[5];
                plA5[0] = new Player("Spiller 1", spi1, 0);
                plA5[1] = new Player("Spiller 2", spi2, 0);
                plA5[2] = new Player("Spiller 3", spi3, 0);
                plA5[3] = new Player("Spiller 4", spi4, 0);
                plA5[4] = new Player("Spiller 5", spi5, 0);
                return plA5;
            } else {
                Player[] plA6 = new Player[6];
                plA6[0] = new Player("Spiller 1", spi1, 0);
                plA6[1] = new Player("Spiller 2", spi2, 0);
                plA6[2] = new Player("Spiller 3", spi3, 0);
                plA6[3] = new Player("Spiller 4", spi4, 0);
                plA6[4] = new Player("Spiller 5", spi5, 0);
                plA6[5] = new Player("Spiller 6", spi6, 0);
                return plA6;
            }
        }

        public static int playercountadd()  {


            JFrame f = new JFrame();
            f.setLayout(null);
            JRadioButton p3 = new JRadioButton("3 Spillere");
            JRadioButton p4 = new JRadioButton("4 Spillere");
            JRadioButton p5 = new JRadioButton("5 Spillere");
            JRadioButton p6 = new JRadioButton("6 Spillere");
            JButton jb = new JButton("Klik for at starte");
            JLabel L1 = new JLabel("vælg spiller mængde");
            JLabel L2 = new JLabel("MATADOR");
            JButton cm = new JButton("skift til børnemode");
            p3.setBounds(75,60,100,30);
            p4.setBounds(75,85,100,30);
            p5.setBounds(75,110,100,30);
            p6.setBounds(75,135,100,30);
            L1.setBounds(75, 35, 200, 30);
            jb.setBounds(75, 170, 200, 30);
            L2.setBounds(75,5,200,30);
            cm.setBounds(75,210,200,30);
            L1.setFont(new Font("default-normal", Font.PLAIN,12));
            L2.setFont(new Font("defaut-bold-normal", Font.PLAIN,25));
            f.add(p3);f.add(p4);f.add(p5);f.add(p6);f.add(L1);f.add(jb);f.add(cm);f.add(L2);
            f.setBounds(100, 150, 400, 300);
            f.setTitle("Matador");

            f.setVisible(true);

            ButtonGroup bg=new ButtonGroup();
            bg.add(p3);bg.add(p4);bg.add(p5);bg.add(p6);

            cm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame Cmode = new JFrame();
                    Cmode.setLayout(null);
                    JLabel L2 = new JLabel("MATADOR");
                    JTextField s1 = new JTextField(spiller1Balance);
                    JTextField s2 = new JTextField(spiller2Balance);
                    JTextField s3 = new JTextField(spiller3Balance);
                    JTextField s4 = new JTextField(spiller4Balance);
                    JTextField s5 = new JTextField(spiller5Balance);
                    JTextField s6 = new JTextField(spiller6Balance);
                    JLabel vs = new JLabel("juster start penge");
                    JButton st = new JButton("Klik for at Starte");
                    JLabel sp1 = new JLabel("Spiller 1");
                    JLabel sp2 = new JLabel("Spiller 2");
                    JLabel sp3 = new JLabel("Spiller 3");
                    JLabel sp4 = new JLabel("Spiller 4");
                    JLabel sp5 = new JLabel("Spiller 5");
                    JLabel sp6 = new JLabel("Spiller 6");
                    L2.setBounds(75,5,200,30);
                    sp1.setBounds(75,60,50,30);
                    sp2.setBounds(75,90,50,30);
                    sp3.setBounds(75,120,50,30);
                    sp4.setBounds(75,150,50,30);
                    sp5.setBounds(75,180,50,30);
                    sp6.setBounds(75,210,50,30);
                    s1.setBounds(130,60,50,30);
                    s2.setBounds(130,90,50,30);
                    s3.setBounds(130,120,50,30);
                    s4.setBounds(130,150,50,30);
                    s5.setBounds(130,180,50,30);
                    s6.setBounds(130,210,50,30);
                    vs.setBounds(75, 35, 200, 30);
                    st.setBounds(130, 250, 200, 30);
                    Cmode.setBounds(100, 150, 400, 300);
                    Cmode.setSize(400,350);
                    Cmode.setVisible(true);
                    Cmode.add(vs);Cmode.add(st);Cmode.add(L2);
                    if (p3.isSelected()){
                        Cmode.add(s1);Cmode.add(s2);Cmode.add(s3);Cmode.add(sp1);Cmode.add(sp2);Cmode.add(sp3);
                        players = 3;
                        sp1 = spiller1Balance;

                    } else if (p4.isSelected()) {
                        Cmode.add(s1);Cmode.add(s2);Cmode.add(s3);Cmode.add(s4);Cmode.add(sp1);Cmode.add(sp2);Cmode.add(sp3);
                        Cmode.add(sp4);
                        players = 4;
                    } else if (p5.isSelected()) {
                        Cmode.add(s1);Cmode.add(s2);Cmode.add(s3);Cmode.add(s4);Cmode.add(s5);Cmode.add(sp1);Cmode.add(sp2);
                        Cmode.add(sp3);Cmode.add(sp4);Cmode.add(sp5);
                        players = 5;
                    } else if (p6.isSelected()) {
                        Cmode.add(s1);Cmode.add(s2);Cmode.add(s3);Cmode.add(s4);Cmode.add(s5);Cmode.add(s6);Cmode.add(sp1);
                        Cmode.add(sp2);Cmode.add(sp3);Cmode.add(sp4);Cmode.add(sp5);Cmode.add(sp6);
                        players = 6;
                    }
                    st.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cm.setVisible(false);
                            f.setVisible(false);
                            synchronized (monitor) {
                                monitor.notify();
                            }
                        }
                    });

                }
            });

            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Declaration of String class Objects.
                    String qual = " ";


                    // If condition to check if jRadioButton2 is selected.
                    if (p3.isSelected()) {
                        qual = "Gamemode: 3 spillere";
                        players = 3;
                    } else if (p4.isSelected()) {

                        qual = "Gamemode: 4 spillere";
                        players = 4;


                    } else if (p5.isSelected()) {

                        qual = "Gamemode: 5 spillere";
                        players = 5;

                    }else if (p6.isSelected()) {

                        qual = "Gamemode: 6 spillere";
                        players = 6;

                    } else {

                        qual = "NO Button selected";

                    }
                    // MessageDialog to show information selected radio buttons.
                    JOptionPane.showMessageDialog(f, qual);

                    if (players == -1)
                        return;
                    f.setVisible(false);
                    synchronized (monitor) {
                        monitor.notify();
                    }
                }

            });
            try {
                synchronized (monitor) {
                    monitor.wait();
                }
            } catch (InterruptedException i){};


            return players;


        }
        public static boolean makeYesNoButton(String msg)
        {
            boolean selection = Game_Controller.getGui().getUserLeftButtonPressed(msg,"Ja","Nej");
            return selection;
        }
        public void addRemoveButton()

        {

            JButton b = new JButton("Click Here");
            String knapString = Game_Controller.getGui().getUserSelection("Vælg en knap","knap1","knap2");

        }
    }



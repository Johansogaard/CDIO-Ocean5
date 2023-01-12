package spil_Version_2;


import spil_Version_2.cards.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Game_Features {

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

            //Ryk til ny position
            cardArray.add(new MovePlayerCard( 11,"Ryk frem til Frederiksberg Allé. Hvis De passere START, indkasser da 4000 kr.\n"));
            cardArray.add(new MovePlayerCard(15 ,"Tag med Mols-Linien, flyt brikken frem og hvis De passerer START indkassér da kr 4000.\n"));
            cardArray.add(new MovePlayerCard( 24,"Ryk frem til Grønningen, hvis De passerer start indkasser da kr 4000\n"));
            cardArray.add(new MovePlayerCard( 32,"Ryk frem til Vimmelskaftet, hvis de passerer start indkasser da kr 4000\n"));
            cardArray.add(new MovePlayerCard( 19,"Ryk frem til Strandvejen. Hvis De passere START, indkasser da 4000 kr.\n"));
            cardArray.add(new MovePlayerCard( 30," Gå i fængsel, De indkasserer ikke 4000 kr for at passere start.\n"));
            cardArray.add(new MovePlayerCard( 30," Gå i fængsel, De indkasserer ikke 4000 kr for at passere start.\n"));




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
            if (pl == 3) {
                Player[] plA3 = new Player[3];
                plA3[0] = new Player("Spiller 1", 30000, 0);
                plA3[1] = new Player("Spiller 2", 30000, 0);
                plA3[2] = new Player("Spiller 3", 30000, 0);

                return plA3;
            } else if (pl == 4) {
                Player[] plA4 = new Player[4];
                plA4[0] = new Player("Spiller 1", 30000, 0);
                plA4[1] = new Player("Spiller 2", 30000, 0);
                plA4[2] = new Player("Spiller 3", 30000, 0);
                plA4[3] = new Player("Spiller 4", 30000, 0);
                return plA4;
            } else if (pl == 5) {
                Player[] plA5 = new Player[5];
                plA5[0] = new Player("Spiller 1", 30000, 0);
                plA5[1] = new Player("Spiller 2", 30000, 0);
                plA5[2] = new Player("Spiller 3", 30000, 0);
                plA5[3] = new Player("Spiller 4", 30000, 0);
                plA5[4] = new Player("Spiller 5", 30000, 0);
                return plA5;
            } else {
                Player[] plA6 = new Player[6];
                plA6[0] = new Player("Spiller 1", 30000, 0);
                plA6[1] = new Player("Spiller 2", 30000, 0);
                plA6[2] = new Player("Spiller 3", 30000, 0);
                plA6[3] = new Player("Spiller 4", 30000, 0);
                plA6[4] = new Player("Spiller 5", 30000, 0);
                plA6[5] = new Player("Spiller 6", 30000, 0);
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
            p3.setBounds(75,60,100,30);
            p4.setBounds(75,85,100,30);
            p5.setBounds(75,110,100,30);
            p6.setBounds(75,135,100,30);
            L1.setBounds(75, 35, 200, 30);
            jb.setBounds(75, 170, 200, 30);
            L2.setBounds(75,5,200,30);
            L1.setFont(new Font("default-normal", Font.PLAIN,12));
            L2.setFont(new Font("defaut-bold-normal", Font.PLAIN,25));
            f.add(p3);f.add(p4);f.add(p5);f.add(p6);f.add(L1);f.add(jb);f.add(L2);
            f.setBounds(100, 150, 400, 300);
            f.setTitle("Matador");

            f.setVisible(true);

            ButtonGroup bg=new ButtonGroup();
            bg.add(p3);bg.add(p4);bg.add(p5);bg.add(p6);

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



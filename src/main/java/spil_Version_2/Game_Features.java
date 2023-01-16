package spil_Version_2;


import spil_Version_2.cards.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Game_Features {

    private static String customGame = "Brugerdefineret spil: OFF";
    private static Integer players = -1;
    private static boolean fail = false;
    private static final Object monitor = new Object();
    private static boolean b = false;
    private static boolean cM = false;
    public static int[] balance = {30000, 30000, 30000, 30000, 30000, 30000};

    public Game_Features() {

    }

    public static ArrayList<Parent_Card> cards() {
//tilføjer kort til array, og gør rækkefølgen ranom
        ArrayList<Parent_Card> cardArray = new ArrayList<Parent_Card>();
        ArrayList<Parent_Card> randomCardArray = new ArrayList<Parent_Card>();

        cardArray.add(new Matador_Legat(40000, true, "Tillykke, du har vundet Matador-Legatet; 40000kr!"));

        //chancekort
        // ryk til start, uden gevinst
        cardArray.add(new RykTilStart_Card(0));
        cardArray.add(new RykTilStart_Card(0));

        //ryk frem eller tilbage
        int[] spacesToMove = {3, -3, -3};
        for (int space : spacesToMove) {
            cardArray.add(new RykFelter_Card(space));
        }

        //modtag eller betal: false=betal, true=modtag
        cardArray.add(new Straf_Eller_Gevinst(200, false, "Betal kr 200 for levering af 2 kasser øl"));
        cardArray.add(new Straf_Eller_Gevinst(3000, false, "Betal 3000 for reparation af deres vogn"));
        cardArray.add(new Straf_Eller_Gevinst(3000, false, "Betal 3000 for reparation af deres vogn"));
        cardArray.add(new Straf_Eller_Gevinst(1000, false, "De har købt 4 nye dæk til Deres vogn, betal kr 1000"));
        cardArray.add(new Straf_Eller_Gevinst(200, false, "De har fået en parkeringsbøde, betal kr 200 i bøde"));
        cardArray.add(new Straf_Eller_Gevinst(1000, false, "Betal deres bilforsikring, kr 1000"));
        cardArray.add(new Straf_Eller_Gevinst(200, false, "De har været udenlands og købt for mange smøger, betal kr 200 i told."));
        cardArray.add(new Straf_Eller_Gevinst(2000, false, "Tandlægeregning, betal kr 2000."));
        cardArray.add(new Straf_Eller_Gevinst(500, true, "De har vundet i klasselotteriet. Modtag 500 kr."));
        cardArray.add(new Straf_Eller_Gevinst(500, true, "De har vundet i klasselotteriet. Modtag 500 kr."));
        cardArray.add(new Straf_Eller_Gevinst(1000, true, "De modtager Deres aktieudbytte. Modtag kr 1000 af banken"));
        cardArray.add(new Straf_Eller_Gevinst(1000, true, "De modtager Deres aktieudbytte. Modtag kr 1000 af banken"));
        cardArray.add(new Straf_Eller_Gevinst(1000, true, "De modtager Deres aktieudbytte. Modtag kr 1000 af banken"));
        cardArray.add(new Straf_Eller_Gevinst(3000, true, "Kommunen har eftergivet et kvartals skat. Hæv i banken 3000 kr."));
        cardArray.add(new Straf_Eller_Gevinst(1000, true, "De have en række med elleve rigtige i tipning, modtag kr 1000"));
        cardArray.add(new Straf_Eller_Gevinst(1000, true, "Grundet dyrtiden har De fået gageforhøjelse, modtag kr 1000."));
        cardArray.add(new Straf_Eller_Gevinst(1000, true, "Deres præmieobligation er udtrykket. De modtager 1000 kr af banken."));
        cardArray.add(new Straf_Eller_Gevinst(1000, true, "Deres præmieobligation er udtrykket. De modtager 1000 kr af banken."));
        cardArray.add(new Straf_Eller_Gevinst(1000, true, "De har solg nogle gamle møbler på auktion. Modtag 1000 kr af banken."));
        cardArray.add(new Straf_Eller_Gevinst(200, true, "Værdien af egen avl fra nyttehaven udgør 200 som de modtager af banken"));
        cardArray.add(new Straf_Eller_Gevinst(300, false, "Betal for vognvask og smøring kr 300"));
        cardArray.add(new Straf_Eller_Gevinst(1000, false, "De har kørt frem for “fuldt stop”, Betal 1000 kroner i bøde"));

        //Ryk til ny position
        cardArray.add(new MovePlayerCard(11, "Ryk frem til Frederiksberg Allé. Hvis De passere START, indkasser da 4000 kr.\n"));
        cardArray.add(new MovePlayerCard(15, "Tag med Mols-Linien, flyt brikken frem og hvis De passerer START indkassér da kr 4000.\n"));
        cardArray.add(new MovePlayerCard(24, "Ryk frem til Grønningen, hvis De passerer start indkasser da kr 4000\n"));
        cardArray.add(new MovePlayerCard(32, "Ryk frem til Vimmelskaftet, hvis de passerer start indkasser da kr 4000\n"));
        cardArray.add(new MovePlayerCard(19, "Ryk frem til Strandvejen. Hvis De passere START, indkasser da 4000 kr.\n"));
        cardArray.add(new MovePlayerCard(30, " Gå i fængsel, De indkasserer ikke 4000 kr for at passere start.\n"));
        cardArray.add(new MovePlayerCard(30, " Gå i fængsel, De indkasserer ikke 4000 kr for at passere start.\n"));


        cardArray.add(new GiftCard(200, "Det er deres fødselsdag. Modtag af hver medspiller 200 kr."));
        cardArray.add(new GiftCard(500, "De har lagt penge ud til et sammenskudsgilde. Mærkværdigvis betaler alle straks. Modtag fra hver medspiller 500 kr.\n"));
        cardArray.add(new GiftCard(500, "De skal holde familiefest og får et tilskud fra hver medspiller på 500 kr."));

        //fængselkort
        cardArray.add(new PrisonCard("FængselsKort"));
        cardArray.add(new PrisonCard("Fængselskort"));

        Random rand = new Random();
        while (true) {
            int random;
            if (cardArray.size() == 1) {
                random = 1;
            } else {
                random = rand.nextInt(1, cardArray.size());
            }
            randomCardArray.add(cardArray.get(random - 1));
            cardArray.remove(random - 1);
            if (cardArray.size() == 0) {
                break;
            }
        }
        return randomCardArray;
    }

    public static Player[] playerstoadd(UserIO gui) {
        int pl = playercountadd();
        if (cM) {
            customMode();
        }
        if (pl == 3) {
            Player[] plA3 = new Player[3];
            plA3[0] = new Player("Spiller 1", balance[0], gui, 0);
            plA3[1] = new Player("Spiller 2", balance[1], gui, 0);
            plA3[2] = new Player("Spiller 3", balance[2], gui, 0);

            return plA3;
        } else if (pl == 4) {
            Player[] plA4 = new Player[4];
            plA4[0] = new Player("Spiller 1", balance[0], gui, 0);
            plA4[1] = new Player("Spiller 2", balance[1], gui, 0);
            plA4[2] = new Player("Spiller 3", balance[2], gui, 0);
            plA4[3] = new Player("Spiller 4", balance[3], gui, 0);
            return plA4;
        } else if (pl == 5) {
            Player[] plA5 = new Player[5];
            plA5[0] = new Player("Spiller 1", balance[0], gui, 0);
            plA5[1] = new Player("Spiller 2", balance[1], gui, 0);
            plA5[2] = new Player("Spiller 3", balance[2], gui, 0);
            plA5[3] = new Player("Spiller 4", balance[3], gui, 0);
            plA5[4] = new Player("Spiller 5", balance[4], gui, 0);
            return plA5;
        } else {
            Player[] plA6 = new Player[6];
            plA6[0] = new Player("Spiller 1", balance[0], gui, 0);
            plA6[1] = new Player("Spiller 2", balance[1], gui, 0);
            plA6[2] = new Player("Spiller 3", balance[2], gui, 0);
            plA6[3] = new Player("Spiller 4", balance[3], gui, 0);
            plA6[4] = new Player("Spiller 5", balance[4], gui, 0);
            plA6[5] = new Player("Spiller 6", balance[5], gui, 0);
            return plA6;
        }
    }

    public static int playercountadd() {


        JFrame f = new JFrame();
        f.setLayout(null);
        JRadioButton p3 = new JRadioButton("3 Spillere");
        JRadioButton p4 = new JRadioButton("4 Spillere");
        JRadioButton p5 = new JRadioButton("5 Spillere");
        JRadioButton p6 = new JRadioButton("6 Spillere");
        JButton jb = new JButton("Klik for at starte");
        JLabel L1 = new JLabel("vælg spiller mængde");
        JLabel L2 = new JLabel("MATADOR");
        p3.setBounds(75, 60, 100, 30);
        p4.setBounds(75, 85, 100, 30);
        p5.setBounds(75, 110, 100, 30);
        p6.setBounds(75, 135, 100, 30);
        L1.setBounds(75, 35, 200, 30);
        jb.setBounds(75, 170, 200, 30);
        L2.setBounds(75, 5, 200, 30);

        //make childrenmode button
        JButton customMode = new JButton(customGame);
        customMode.setBounds(75, 205, 200, 30);

        //dev mode button
        JButton devJb = new JButton("Dev Mode");
        devJb.setBounds(400, 0, 100, 30);

        L1.setFont(new Font("default-normal", Font.PLAIN, 12));
        L2.setFont(new Font("defaut-bold-normal", Font.PLAIN, 25));
        f.add(p3);
        f.add(p4);
        f.add(p5);
        f.add(p6);
        f.add(L1);
        f.add(jb);
        f.add(L2);
        f.add(devJb);
        f.add(customMode);
        f.setBounds(100, 150, 400, 300);
        f.setTitle("Matador");

        f.setVisible(true);

        ButtonGroup bg = new ButtonGroup();
        bg.add(p3);
        bg.add(p4);
        bg.add(p5);
        bg.add(p6);

        devJb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game_Controller.setDevMode(true);
            }
        });

        customMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cM = !cM;
                // Declaration of String class Objects.
                if (cM) {
                    customGame = "Brugerdefineret spil: ON";
                    customMode.setText(customGame);

                } else {
                    customGame = "Brugerdefineret spil: OFF";
                    customMode.setText(customGame);
                }


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

                } else if (p6.isSelected()) {

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
        } catch (InterruptedException i) {
        }
        ;

        return players;

    }

    private static void customMode() {
        ArrayList<JTextField> txtfield = new ArrayList<>();
        JFrame f = new JFrame();
        f.setLayout(null);
        JLabel L2 = new JLabel("Vælg det beløb spillerne starter med");
        L2.setBounds(75, 5, 250, 30);

        JLabel p1 = new JLabel("Spiller 1 beløb:");
        p1.setBounds(75, 25, 200, 30);
        JTextField t1 = new JTextField();
        t1.setBounds(75, 50, 200, 30);

        JLabel p2 = new JLabel("Spiller 2 beløb:");
        p2.setBounds(75, 70, 200, 30);
        JTextField t2 = new JTextField();
        t2.setBounds(75, 95, 200, 30);

        JLabel p3 = new JLabel("Spiller 3 beløb:");
        p3.setBounds(75, 115, 200, 30);
        JTextField t3 = new JTextField();
        t3.setBounds(75, 140, 200, 30);

        JLabel p4 = new JLabel("Spiller 4 beløb:");
        p4.setBounds(75, 160, 200, 30);
        JTextField t4 = new JTextField();
        t4.setBounds(75, 185, 200, 30);


        JLabel p5 = new JLabel("Spiller 5 beløb:");
        p5.setBounds(75, 205, 200, 30);
        JTextField t5 = new JTextField();
        t5.setBounds(75, 230, 200, 30);


        JLabel p6 = new JLabel("Spiller 6 beløb:");
        p6.setBounds(75, 250, 200, 30);
        JTextField t6 = new JTextField();
        t6.setBounds(75, 275, 200, 30);


        f.add(L2);
        f.add(p1);
        f.add(t1);
        f.add(p2);
        f.add(t2);
        f.add(p3);
        f.add(t3);

        if (players >= 4) {


            f.add(p4);
            f.add(t4);
        }
        if (players >= 5) {

            f.add(p5);
            f.add(t5);
        }
        if (players == 6) {

            f.add(p6);
            f.add(t6);
        }
        //Confirmbutton
        JButton confirm = new JButton("Confirm");
        confirm.setBounds(75, 320, 200, 30);


        f.add(confirm);
        f.setBounds(100, 150, 400, 500);
        f.setTitle("CustomMode");
        f.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fail = false;
                JTextField[] txtF = {t1, t2, t3, t4, t5, t6};
                for (int i = 0; i < players; i++) {
                    txtfield.add(txtF[i]);
                }

                for (int i = 0; i < txtfield.size(); i++) {
                    if (txtfield.get(i).getText() != null) {

                        for (int h = 0; h < txtfield.get(i).getText().length(); h++) {

                            if (!Character.isDigit(txtfield.get(i).getText().charAt(h))) {
                                fail = true;
                            }
                        }
                    } else fail = true;
                }
                if (fail == false) {
                    for (int i = 0; i < txtfield.size(); i++) {
                        balance[i] = Integer.parseInt(txtfield.get(i).getText());
                    }
                    synchronized (monitor) {
                        monitor.notify();
                    }
                    f.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(f, "Der mangler at blive skrevet en balance !Husk kun tal");
                }
            }
        });
        try {
            synchronized (monitor) {
                monitor.wait();
            }
        } catch (InterruptedException i) {
        }

    }
}








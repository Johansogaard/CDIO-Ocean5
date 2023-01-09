package spil_Version_2;

import spil_Version_2.*;
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

            ArrayList<Parent_Card> cardArray = new ArrayList<Parent_Card>();
            ArrayList<Parent_Card> randomCardArray = new ArrayList<Parent_Card>();
            cardArray.add(new RykTilStart_Card());
            cardArray.add(new RykFelterFrem_Card());
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
                plA3[0] = new Player("Spiller 1", 1000, 0);
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
                        qual = "Gamemode: 3 players";
                        players = 3;
                    } else if (p4.isSelected()) {

                        qual = "Gamemode: 4 players";
                        players = 4;


                    } else if (p5.isSelected()) {

                        qual = "Gamemode: 5 players";
                        players = 5;

                    }else if (p6.isSelected()) {

                        qual = "Gamemode: 6 players";
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
            boolean selection = Game_Controller.getGui().getUserLeftButtonPressed(msg,"Yes","No");
            return selection;
        }
        public void addRemoveButton()

        {

            JButton b = new JButton("Click Here");
            String knapString = Game_Controller.getGui().getUserSelection("Vælg en knap","knap1","knap2");

        }
    }



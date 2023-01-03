package spil;

import card.Parent_Card;
import card.RykFelterFrem_Card;
import card.RykTilStart_Card;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GameFeatures {
   private static Integer players=-1;
   private static final Object monitor = new Object();
   private static boolean b =false;
    public GameFeatures()
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

            if (pl == 2) {
                Player[] plA2 = new Player[2];
                plA2[0] = new Player("Spiller1", 20, 0);
                plA2[1] = new Player("Spiller2", 20, 0);
                return plA2;
            } else if (pl == 3) {
                Player[] plA3 = new Player[3];
                plA3[0] = new Player("Spiller1", 18, 0);
                plA3[1] = new Player("Spiller2", 18, 0);
                plA3[2] = new Player("Spiller3", 18, 0);
                return plA3;
            } else {
                Player[] plA4 = new Player[4];
                plA4[0] = new Player("Spiller1", 16, 0);
                plA4[1] = new Player("Spiller2", 16, 0);
                plA4[2] = new Player("Spiller3", 16, 0);
                plA4[3] = new Player("Spiller4", 16, 0);
                return plA4;
            }
    }

    public static int playercountadd()  {


        JFrame f = new JFrame();
        f.setLayout(null);
        JRadioButton p2 = new JRadioButton("2 Players");
        JRadioButton p3 = new JRadioButton("3 Players");
        JRadioButton p4 = new JRadioButton("4 Players");
        JButton jb = new JButton("Click to start");
        JLabel L1 = new JLabel("Selec player amount");
        p2.setBounds(75,25,100,30);
        p3.setBounds(75,50,100,30);
        p4.setBounds(75,75,100,30);
        L1.setBounds(75, 5, 200, 30);
        jb.setBounds(75, 120, 200, 30);
        f.add(p2);f.add(p3);f.add(p4);f.add(L1);f.add(jb);
        f.setBounds(100, 150, 400, 300);
        f.setTitle("Monopoly Junior");

        f.setVisible(true);

        ButtonGroup bg=new ButtonGroup();
        bg.add(p2);bg.add(p3);bg.add(p4);

            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Declaration of String class Objects.
                    String qual = " ";


                    // If condition to check if jRadioButton2 is selected.
                    if (p2.isSelected()) {
                        qual = "Gamemode: 2 players";
                        players = 2;
                    } else if (p3.isSelected()) {

                        qual = "Gamemode: 3 players";
                        players = 3;


                    } else if (p4.isSelected()) {

                        qual = "Gamemode: 4 players";
                        players = 4;

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

}

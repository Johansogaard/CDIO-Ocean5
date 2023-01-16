package spil_Version_2;

import javax.swing.*;
import java.awt.*;


public class Fieldsbox extends Frame {
    Button[] buttons;
    JPanel[] panel;

    public Fieldsbox(Player player, int index) {
        this.setTitle(player.getName());

        JPanel[] panels = new JPanel[player.getGrunde().size()];

        for (int i = 0; i < 5; i++) {
            panels[i] = new JPanel();
            // adding the buttons so that it can be displayed
            add(panels[i]);
        }
        // the buttons in the output will be aligned vertically
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setSize(200, 800);
        setLocation(800, 200 * index);
        setVisible(true);
    }
}



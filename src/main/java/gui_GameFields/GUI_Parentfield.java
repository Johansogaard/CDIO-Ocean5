package gui_GameFields;
import game_Txt.FieldText;
import gui_codebehind.GUI_Center;
import gui_codebehind.SwingComponentFactory;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import spil.GameFeatures;
import spil.Player;
import spil.inGameFunktions.LandOnStreet;

import javax.swing.*;
import java.awt.*;

import static spil.GameController.getGui;


public abstract class GUI_Parentfield extends GUI_Field {
    public static FieldText mt = FieldText.getInstance();
    private static final int TITLEHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;

    public void setRent(int[] rent) {
        this.rent = rent;
    }



    int[] rent;


    private int price = 0;

    GameFeatures gameFeatures = new GameFeatures();
    private Player owner=null;
    private SwingComponentFactory factory;



    public GUI_Parentfield(Color bgColor, Color fgColor, String title, String subText, String description) {
        super(bgColor, fgColor, title, subText, description);
        this.factory = new SwingComponentFactory();
        this.titleLabel = this.makeTitleLabel(this.title);
        this.subTextLabel = this.makeSubTextLabel(this.subText);
        this.layered.add(this.titleLabel, this.factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel, this.factory.createGridBagConstraints(0, 1));

    }




    public void hit(Player player) {

        if (getOwner() == null)
        {
            if(getGui().getUserLeftButtonPressed("Vil du købe grunden", "ja","nej")) {

            if(player.getKonto().getBalance() >= price){
                    setOwner(player);
                    player.buyField(price, getTitle());
                    getGui().showMessage(player.getName()+"har købt"+getTitle());
                    setDescription(getDescription() + "\nOwner:" + getOwner().getName());
                }
            }
    }
        else if(player != getOwner())
        {
            getOwner().getKonto().update(+rent[0]);
            player.getKonto().update(-rent[0]);

        }


    }
    private JLabel makeTitleLabel(String titleStart) {
        JLabel l = this.makeLabel(47);
        l.setText(titleStart);
        l.setFont(new Font(l.getFont().getName(), 1, 10));
        return l;
    }

    private JLabel makeSubTextLabel(String subTextStart) {
        JLabel l = this.makeLabel(14);
        l.setText(subTextStart);
        return l;
    }

    protected void displayOnCenter(GUI_Player[] playerList) {
        super.displayOnCenter(playerList);
        GUI_Center.label[1].setText(this.title.replace("<html><center>", ""));
        GUI_Center.label[2].setText("__________________________");
        GUI_Center.label[3].setText(this.description);
        super.displayCarOnCenter(playerList);
    }

    public String toString() {
        return "GUI_Start [bgColor=" + this.bgColor + ", fgColor=" + this.fgColor + ", title=" + this.title + ", subText=" + this.subText + ", description=" + this.description + "]";
    }
    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getUserLeftButtonPressed(String msg, String trueButton, String falseButton){
        return getGui().getUserLeftButtonPressed(msg, trueButton, falseButton);
}}

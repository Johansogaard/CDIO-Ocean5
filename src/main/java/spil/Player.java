package spil;

import gui_GameFields.GUI_Parentfield;
import gui_GameFields.GUI_Street;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;
import java.util.ArrayList;
import java.util.Arrays;
public class Player {
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    private int pos=0;
    Konto konto = new Konto(0);
    Terninger terninger = new Terninger();
    GUI_Player pl;
    GUI_Field fpos;
    GUI gui;

    ArrayList<String> grunde = new ArrayList<String>();
    public void addGrunde(String grund) {
        this.grunde.add(grund);
    }

    public ArrayList<String> getGrunde() {
        return grunde;

    }




    private boolean jail= false;
    public boolean isJail() {
        return jail;
    }

    public void setJail(boolean jail) {
        this.jail = jail;
    }
    private int t1=0;
    private int t2=0;
    private String name;
    GUI_Parentfield gamefields[];
    public Player(String name, int bal, int postiotion)
    {
        konto.update(bal);
        pos = postiotion;

        this.name = name;

    }
    //Getter
    public void tilføjspillerGui(GUI gui)
    {
        GUI_Player player = new GUI_Player(name,konto.getBalance());
        gui.addPlayer(player);
        GUI_Field field = gui.getFields()[pos];
        field.setCar(player,true);

        fpos = field;
        pl=player;
        this.gui = gui;
    }
    public Konto getKonto(){

        return konto;
    }
    public String getName()
    {
        return name;
    }
    //spiller en runde for den spiller der er kaldt
    public boolean spil(GUI gui, GUI_Parentfield[] fields)
    {
        gamefields = fields;

            if (isJail())
            {
                setJail(false);
                gui.getUserButtonPressed(name + " du er i fængsel og betaler 1M for at komme ud i næste runde", "Okay");
                updatePlayerBalance(-1);

            }
            else if (gui.getUserButtonPressed(name + " Press button to roll dice", "Roll Dice") == "Roll Dice") {
                turn();
                checkIfPassedStart(pos+t1+t2);

                pos=(pos+t1 +t2)%40;
                gui.setDice(t1, t2);
                setCar(pos, gui);
                hitField();

            }

            if (konto.getBalance() <=0) {
                    return true;
            }
            else {
                return false;
            }




    }
    public void hitField()
    {
        displayCard(pos,gui);
        gamefields[pos].hit(this);

    }
    public void checkIfPassedStart(int sumPos)
    {
        if (sumPos >= 40)
        {
            updatePlayerBalance(4000);
        }
        else if (sumPos >= 40 && sumPos < 80) {
            updatePlayerBalance(4000);
        }

    }

    public void turn()
    {
        t1 = terninger.slaEnTerning();
        t2 = terninger.slaEnTerning();

    }

    public void setCar(int tsum,GUI gui)
    {
        fpos.setCar(pl,false);
        GUI_Field felt = gui.getFields()[tsum];
        felt.setCar(pl,true);
        fpos = gui.getFields()[tsum];
    }
    public void displayCard(int pos, GUI gui)
    {
        GUI_Field f = gui.getFields()[pos];
        gui.displayChanceCard(f.getTitle()+"\n"+ f.getDescription());
    }

    public void payRent(int cost, Player owner, String title) {
        if (checkDoubleCost() == 1) {
            gui.getUserButtonPressed(pl.getName() + " landed on " + title + " and needs to pay rent to " + owner.getName(), "Okay");
        } else {
            gui.getUserButtonPressed(pl.getName() + " landed on " + title + " and needs to pay double rent to " + owner.getName()+" because he owns 2 field with this color", "Okay");
        }

        pl.setBalance(cost);
    }

    public void payRant(int rent, Player owner, String title) {
        pl.setBalance(konto.getBalance()-rent);
    }



    public void getRent(int rent)
    {
        pl.setBalance(konto.getBalance()+rent);
    }


    public void buyField(int price , String title)
    {
        updatePlayerBalance(-price);
        konto.updateFieldValue(price);
        addGrunde(title);
    }


    public void injail()
    {
       movePlayer(11);
        setJail(true);
        gui.getUserButtonPressed(name + " du er i fængsel og bliver sprunget over i næste runde", "Okay");
    }
    public void movePlayer(int number) {
        this.pos = number;
        checkIfPassedStart(pos);
        setCar(pos, gui);
    }


    public void showchancecard(String txt){
       gui.displayChanceCard(txt);
       gui.getUserButtonPressed(name +" "+ txt, "Okay");

    }
    public void updatePlayerBalance(int value)
    {
        konto.update(value);
        pl.setBalance(konto.getBalance());
    }

    //chekker om nuværende spiller ejer alle grunde i et sæt med en bestemt farve
    public boolean checkIOwnAll(String color){

       if(getGrunde().contains(BoardCreator.getColorArray(color)))
       {
           return true;
       }

       else return false;

    }


//chekker om owneren har alle grunde i et sæt
   public boolean checkOwnerOwnAll(){
    GUI_Parentfield field = gamefields[getPos()];
       GUI_Street street = (GUI_Street) field;

       if (Arrays.asList(gamefields[getPos()].getOwner().getGrunde()).contains(BoardCreator.getColorArray(street.getColor())))
       {
           return true;
       }

       else return false;

    }
    public int checkDoubleCost()
    {
        if (checkOwnerOwnAll())
        {
            return 2;
        }
        else return 1;

    }
public boolean getUserLeftButtonPressed(String msg, String trueButton, String falseButton){
        return gui.getUserLeftButtonPressed(msg, trueButton, falseButton);
}
    public int sum(){
        t1 = spil.Terninger.slaEnTerning();
        t2 = spil.Terninger.slaEnTerning();
        int i = t1 + t2;
        return i;

    }


}


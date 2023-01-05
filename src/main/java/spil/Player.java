package spil;

import gui_GameFields.GUI_Parentfield;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;

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
        if (sumPos>=24)
        {
            updatePlayerBalance(2);
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



    public void getRent(int cost)
    {
        pl.setBalance(cost);
    }


    public void buyField(int cost, String title)
    {
        gui.getUserButtonPressed(pl.getName() + " bought " + title+"", "Okay");
        updatePlayerBalance(-cost);
        konto.updateFieldValue(cost);

    }
    public void injail()
    {
       movePlayer(11);
        setJail(true);
        gui.getUserButtonPressed(name + " du er i fængsel og bliver sprunget over i næste runde", "Okay");
    }
    public void movePlayer(int number)
    {
        pos = number;
        setCar(pos,gui);

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
    public int checkDoubleCost()
    {

        if (gamefields[pos].getOwner() == gamefields[pos-1].getOwner() ||  gamefields[pos].getOwner() == gamefields[(pos+1)%40].getOwner())
        {
            return 2;
        }
        else return 1;

    }


}


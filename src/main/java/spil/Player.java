package spil;




import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.util.ArrayList;


public class Player {
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    private int pos=0;
    private int fartbølle=0;

    Konto konto = new Konto(0);
    Terninger terninger = new Terninger();
    GUI_Player pl;
    GUI_Field fpos;
    GUI gui;
    LandOnField landOnField =new LandOnField();




    GUI_Car car;


    private boolean jail= false;



    private int t1=0;
    private int t2=0;
    private int jailCounter=0;
    private String name;
    GUI_Field gamefields[];

    ArrayList<String> grunde = new ArrayList<String>();
    public void addGrunde(String grund) {
        this.grunde.add(grund);
    }

    public ArrayList<String> getGrunde() {
        return grunde;

    }
    public Player(String name, int bal, int postiotion)
    {
        konto.update(bal);
        pos = postiotion;

        this.name = name;

    }
    //Getter
    public void tilføjspillerGui(GUI gui,Color carCorlor)
    {
        car = new GUI_Car();
        car.setPrimaryColor(carCorlor);

        GUI_Player player = new GUI_Player(name,konto.getBalance(),car);

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
    public boolean spil(GUI gui, GUI_Field[] fields)
    {
        gamefields = fields;

            if (jail)
            {
                inJail();


            }
            else if (gui.getUserButtonPressed(name + " Klik på knappen for at rulle med terningerne", "Rul terninger") == "Rul terninger") {
                turn();
                checkIfPassedStart(pos+t1+t2);

                pos=(pos+t1 +t2)%40;
                gui.setDice(t1, t2);
                setCar(pos, gui);
                landOnField.hitField(this,gamefields);

                if (t1==t2){
                    fartbølle++;
                    if (fartbølle==3){
                        goToJail();
                    }
                    else{
                    gui.getUserButtonPressed(name + " fik to ens, du fik ekstra tur!", "Rul terninger");
                    spil(gui,fields);

                }

            }
                else{fartbølle=0;}
            }

            if (konto.getBalance() <=0) {
                    return true;
            }
            else {
                return false;
            }




    }

    //tror virker
    public void inJail()
    {
        if(jailCounter==3){
            gui.getUserButtonPressed(name + " fik ikke tre ens 3 ture i træk og skal derfor betale", "gå videre");
            payJail();
        }
        if(gui.getUserLeftButtonPressed(name+ " Betal 1000 kr eller slå to ens terninger for at komme ud", "Betal 1000 kr", "Slå to ens terninger"))
        {
            payJail();
        }

        else
        {
            jailCounter++;
            turn();
            if (t1==t2){

                jail = false;
                spil(this.gui,this.gamefields);

            }
            else {gui.getUserButtonPressed(name + " fik ikke to ens", "gå videre");}
        }


    }
    private void payJail(){
        updatePlayerBalance(-1000);
        jail = false;
        spil(this.gui,this.gamefields);
    }
    public void hitField()
    {
        displayCard();
       // gamefields[pos].hit(this);

    }

    public void checkIfPassedStart(int sumPos)
    {
        if (sumPos >= 40)
        {
            updatePlayerBalance(4000);
        }
    }


    public void turn()
    {
        t1 = terninger.slaEnTerning();
        t2 = terninger.slaEnTerning();


    }
    public void setCar(int tsum, GUI gui) {
        tsum = tsum%40;
        fpos.setCar(pl, false);
        GUI_Field felt = gui.getFields()[tsum];
        felt.setCar(pl, true);
        fpos = gui.getFields()[tsum];
    }


    public void displayCard()
    {
        GUI_Field f = gui.getFields()[pos];
        gui.displayChanceCard(f.getTitle()+"\n"+ f.getDescription());
    }

    public void payRent(int cost, Player owner, String title) {
        gui.getUserButtonPressed(pl.getName() + " landede på " + title + " og skal betale leje til " + owner.getName(), "Okay");
        owner.getKonto().update(cost);
        getKonto().update(-cost);
        owner.setGUIBalance(owner.getKonto().getBalance());
        pl.setBalance(this.konto.getBalance());
    }



    public void setGUIBalance(int balance)
    {
        pl.setBalance(balance);
    }


    public void buyField(int cost, String title)
    {
        gui.getUserButtonPressed(pl.getName() + " købte " + title+"", "Okay");
        updatePlayerBalance(-cost);
        konto.updateFieldValue(cost);
        addGrunde(title);

    }
    public void goToJail()
    {
       movePlayer(10);
        jail=true;
        gui.getUserButtonPressed(name + " du er røget i fængsel får dårlig opførelse", "Okay");
    }
    public void movePlayer(int number) {
        if(number <0){
            number = number + 40;
        }
        this.pos = number%40;
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
    //chekker om owneren har alle grunde i et sæt
    public boolean checkOwnerOwnAll(){
        GUI_Field field = gamefields[getPos()];
        GUI_Ownable ownable = (GUI_Ownable) field;
        ArrayList<String> ownerFields = GameController.getPlayer(ownable.getOwnerName()).getGrunde();
        ArrayList<String> typeFields = BoardCreator.getGroupArray((BoardCreator.getFieldData().get(pos)[11]));
        boolean sandt = false;
        int f=0;
        for (int i = 0;i<typeFields.size();i++)
        {
            String l =typeFields.get(i);
            if(ownerFields.contains(l))
            {
                f++;
            }
            if (f ==typeFields.size())
            {
                sandt = true;
            }
        }

        if (sandt)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public GUI_Car getCar() {
        return car;
    }
}


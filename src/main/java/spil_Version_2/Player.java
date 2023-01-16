package spil_Version_2;




import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.lang.reflect.Array;
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


    public boolean isJail() {
        return jail;
    }

    private boolean jail= false;


    private boolean hasLost = false;
    private int t1=0;
    private int t2=0;
    private int jailCounter=0;
    private String name;
    GUI_Field gamefields[];

    public void setGrunde(ArrayList<String> grunde) {
        this.grunde = grunde;
    }

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

        String choice = gui.getUserButtonPressed(name + " det er din tur hvad vil du gøre", "1. Spil min tur", "2. Hus/Hotel Menu", "3. Sælg Grund", "4. Pantsætning af grunde menu");
        switch (getChoice(choice)){
            case 1:
            {
                runATurn();
            }
            break;
            case 2:
            {
                hotelHouseMenu();
                spil(gui,fields);
            }
            break;
            case 3:
            {
                sellField();
                spil(gui,fields);
            }
            break;
            case 4:
            {
                pawnMenu();
                spil(gui,fields);
            }
            break;
        }
        return hasLost;
    }
    public void sellField()
    {
        ArrayList<String[]> data = Board_Creator.getFieldData();
        ArrayList<String> availableFieldsToSell = new ArrayList<>();

            for (int i = 0; i < this.grunde.size(); i++) {
                int crrIndex = Board_Creator.fieldIndexFromName(grunde.get(i));
                if (Integer.parseInt(data.get(crrIndex)[12]) == 0) {
                    availableFieldsToSell.add(this.grunde.get(i));
                }
                String[] choice = availableFieldsToSell.toArray(new String[availableFieldsToSell.size()]);
                if(choice.length ==0)
                {
                    gui.showMessage("Du har ikke nogle grunde der kan sælges husk at sælge dine huse på grunden før du sælger grund");
                }
                else {
                    String fieldToSell = gui.getUserSelection("Hvilken grund vil de sælge", choice);
                    int fieldIndex = Board_Creator.fieldIndexFromName(fieldToSell);
                    GUI_Field f = Game_Controller.getFields()[fieldIndex];
                    GUI_Ownable o = (GUI_Ownable) f;
                    Aktion aktion = new Aktion();
                    int sellPrice = gui.getUserInteger("Hvad vil du sælge din grund for");
                    aktion.runSellFieldAktion(this,o,sellPrice);

                }

            }

    }
    public void hotelHouseMenu()
    {
        String choice = gui.getUserButtonPressed(this.getName(), "1. Køb hus/hotel", "2. sælg hus/hotel");
        switch (getChoice(choice))
        {
            case 1:
            {
                buyHouse();
            }
            break;
            case 2:
            {
                sellHouse();
            }
            break;
        }
    }
    public void buyHouse()
    {
        String[] fieldcolors = {"blue","red","green","orange","grey","white","yellow","purple"};
        ArrayList<String> colorsYouOwn = new ArrayList<>();
       for(int i=0;i<fieldcolors.length;i++)
       {
           if(checkPlayerOwnsTheColorFields(this,fieldcolors[i]))
           {
               colorsYouOwn.add(fieldcolors[i]);
           }
       }
       if (colorsYouOwn.size()==0)
       {
           gui.getUserButtonPressed("Du har ikke alle felter i en farve og kan derfor ikke købe hus","Okay");
       }
       else {
           ArrayList<String> sameColorFields = new ArrayList<>();

           for(int i = 0;i<colorsYouOwn.size();i++)
           {
               ArrayList<String[]>  f = Board_Creator.getGroupArray(colorsYouOwn.get(i));
               ArrayList<Integer> index = new ArrayList<>();
               for(int k = 0; k<f.size();k++)
               {
                   String titel = f.get(k)[0];
                   int fieldIndex = Board_Creator.fieldIndexFromName(titel);
                  index.add(Integer.parseInt(Board_Creator.getFieldData().get(fieldIndex)[12]));
               }


               int min = index.get(0);
               // loop to find minimum from ArrayList
               for (int p = 1; p < index.size(); p++) {
                   if (index.get(p) < min) {
                       min = index.get(p);
                   }
               }
                //loop that makes an array of the streets with lowest number of houses and checks if the streets has max number of houses
               for(int r = 0;r<f.size();r++)
               {
                   String titel = f.get(r)[0];
                   int fieldIndex = Board_Creator.fieldIndexFromName(titel);
                   if(Integer.parseInt(Board_Creator.getFieldData().get(fieldIndex)[12])==min &&
                           Integer.parseInt(Board_Creator.getFieldData().get(Board_Creator.fieldIndexFromName(titel))[12])<5)
                   {
                       sameColorFields.add(f.get(r)[0]);
                   }
               }

           }
           if (sameColorFields.size()==0)
           {
               gui.getUserButtonPressed("Du har ikke mulighed for at bygge mere på dine grunde lige nu","Okay");
           }
           else {
               String[] choice = sameColorFields.toArray(new String[sameColorFields.size()]);
               String fieldToSetHouse = gui.getUserSelection("Hvilken grund vil de købe Hus/hotel til?", choice);

               GUI_Field field = Game_Controller.getFields()[Board_Creator.fieldIndexFromName(fieldToSetHouse)];
               GUI_Street street = (GUI_Street) field;

               int numberOfHouses = Integer.parseInt(Board_Creator.getFieldData().get(Board_Creator.fieldIndexFromName(fieldToSetHouse))[12]);
               int housePrice =  Integer.parseInt(Board_Creator.getFieldData().get(Board_Creator.fieldIndexFromName(fieldToSetHouse))[4]);

               if (numberOfHouses < 4) {
                   if(payHouseHotel(housePrice))
                   {
                       street.setHouses(1 + numberOfHouses);
                       Board_Creator.setHousesInData(1 + numberOfHouses, Board_Creator.fieldIndexFromName(fieldToSetHouse));
                   }

               } else if (numberOfHouses == 4) {
                   if(payHouseHotel(housePrice*5)) {
                       street.setHouses(0);
                       street.setHotel(true);
                       Board_Creator.setHousesInData(1 + numberOfHouses, Board_Creator.fieldIndexFromName(fieldToSetHouse));
                   }
               }
           }

       }

    }
    private boolean payHouseHotel(int housePrice)
    {
        boolean paidForHouse = false;
        if (this.getKonto().getBalance()>housePrice) {
            getKonto().update(-housePrice);
            pl.setBalance(this.konto.getBalance());
            paidForHouse = true;
        }
        else
        {
            gui.getUserButtonPressed("Du har ikke råd til at købe hus/hotel lige nu","okay");
        }
        return paidForHouse;
    }
    private void sellHouseHotel(int housePrice)
    {
        getKonto().update(housePrice/2);
        pl.setBalance(this.konto.getBalance());

    }



    public void sellHouse()
    {
        String[] fieldcolors = {"blue","red","green","orange","grey","white","yellow","purple"};
        ArrayList<String> colorsYouOwn = new ArrayList<>();
        for(int i=0;i<fieldcolors.length;i++)
        {
            if(checkPlayerOwnsTheColorFields(this,fieldcolors[i]))
            {
                colorsYouOwn.add(fieldcolors[i]);
            }
        }
        if (colorsYouOwn.size()==0)
        {
            gui.getUserButtonPressed("Du har ikke alle felter i en farve og har derfor ingen huse","Okay");
        }
        else {
            ArrayList<String> sameColorFields = new ArrayList<>();

            for(int i = 0;i<colorsYouOwn.size();i++)
            {
                ArrayList<String[]>  f = Board_Creator.getGroupArray(colorsYouOwn.get(i));
                ArrayList<Integer> index = new ArrayList<>();
                for(int k = 0; k<f.size();k++)
                {
                    String titel = f.get(k)[0];
                    int fieldIndex = Board_Creator.fieldIndexFromName(titel);
                    index.add(Integer.parseInt(Board_Creator.getFieldData().get(fieldIndex)[12]));
                }


                int max = index.get(0);
                // loop to find minimum from ArrayList
                for (int p = 1; p < index.size(); p++) {
                    if (index.get(p) > max) {
                        max = index.get(p);
                    }
                }
                //loop that makes an array of the streets with lowest number of houses and checks if the streets has max number of houses
                for(int r = 0;r<f.size();r++)
                {
                    String titel = f.get(r)[0];
                    int fieldIndex = Board_Creator.fieldIndexFromName(titel);
                    if(Integer.parseInt(Board_Creator.getFieldData().get(fieldIndex)[12])==max &&
                            Integer.parseInt(Board_Creator.getFieldData().get(Board_Creator.fieldIndexFromName(titel))[12])>0)
                    {
                        sameColorFields.add(f.get(r)[0]);
                    }
                }

            }
            if (sameColorFields.size()==0)
            {
                gui.getUserButtonPressed("Du har ingen huse/hoteller at sælge","Okay");
            }
            else {
                String[] choice = sameColorFields.toArray(new String[sameColorFields.size()]);
                String fieldToSetHouse = gui.getUserSelection("Hvilken grund vil de sælge deres Hus/hotel?", choice);

                GUI_Field field = Game_Controller.getFields()[Board_Creator.fieldIndexFromName(fieldToSetHouse)];
                GUI_Street street = (GUI_Street) field;

                int numberOfHouses = Integer.parseInt(Board_Creator.getFieldData().get(Board_Creator.fieldIndexFromName(fieldToSetHouse))[12]);
                int housePrice =  Integer.parseInt(Board_Creator.getFieldData().get(Board_Creator.fieldIndexFromName(fieldToSetHouse))[4]);

                if (numberOfHouses < 5) {

                    sellHouseHotel(housePrice);
                        street.setHouses(numberOfHouses-1);
                        Board_Creator.setHousesInData( numberOfHouses-1, Board_Creator.fieldIndexFromName(fieldToSetHouse));


                } else if (numberOfHouses == 5) {
                    sellHouseHotel(housePrice*5);
                        street.setHouses(0);
                        street.setHotel(false);
                        Board_Creator.setHousesInData(0, Board_Creator.fieldIndexFromName(fieldToSetHouse));

                }
            }

        }
    }
    public void pawnMenu()
    {
        String buttonPressed = gui.getUserButtonPressed("Vil du","Pantsæt Grunde","Køb pantsatte grunde tilbage");

        if (buttonPressed.equals("Pantsæt Grunde")) {
            pawnField();
        }
        else {
            unPawnField();
        }

    }
    public void pawnField()
    {
        ArrayList<String[]> data = Board_Creator.getFieldData();
        ArrayList<String> availableFieldsToParwn = new ArrayList<>();
        for (int i = 0; i < this.grunde.size(); i++) {
            int crrIndex = Board_Creator.fieldIndexFromName(grunde.get(i));
            if (Integer.parseInt(data.get(crrIndex)[12]) == 0 && Integer.parseInt(data.get(crrIndex)[13]) == 0) {
                availableFieldsToParwn.add(this.grunde.get(i));
            }
        }
               String[] choice = availableFieldsToParwn.toArray(new String[availableFieldsToParwn.size()]);
               if(choice.length ==0)
               {
                   gui.showMessage("Du har ikke nogle grunde der kan pantsættes husk at sælge dine huse på grunden før du pantsætter");
               }
               else {
                   String fieldToPawn = gui.getUserSelection("Hvilken grund vil de pantsætte", choice);
                   int fieldToPawnIndex = Board_Creator.fieldIndexFromName(fieldToPawn);
                   Board_Creator.setPawnStatusInData(true, fieldToPawnIndex);
                   this.getKonto().update((Integer.parseInt(data.get(fieldToPawnIndex)[3])) / 2);
               }


       }

    public void unPawnField()
    {
        ArrayList<String[]> data = Board_Creator.getFieldData();
        ArrayList<String> availableFieldsToParwn = new ArrayList<>();
        for (int i = 0; i < this.grunde.size(); i++) {
            int crrIndex = Board_Creator.fieldIndexFromName(grunde.get(i));
            if (Integer.parseInt(data.get(crrIndex)[13]) == 1) {
                availableFieldsToParwn.add(this.grunde.get(i));
            }
            String[] choice = availableFieldsToParwn.toArray(new String[availableFieldsToParwn.size()]);
            if(choice.length ==0)
            {
                gui.showMessage("Du har ingen pantsatte grunde");
            }
            else {
                String fieldToPawn = gui.getUserSelection("Hvilken grund vil de købe tilbage", choice);
                int fieldToPawnIndex = Board_Creator.fieldIndexFromName(fieldToPawn);
                Board_Creator.setPawnStatusInData(false, fieldToPawnIndex);
                int costToBuyBack = ((Integer.parseInt(data.get(fieldToPawnIndex)[3]) / 2) / 10) + (Integer.parseInt(data.get(fieldToPawnIndex)[3]) / 2);
                int rounded = ((costToBuyBack + 99) / 100) * 100;
                this.getKonto().update((-rounded));
            }

        }
    }
    private void runATurn()
    {
        if (jail)
        {
            inJail();


        }
        else if (gui.getUserButtonPressed(name + " Klik på knappen for at rulle med terningerne", "Rul terninger") == "Rul terninger") {
            turn();
            simpleTurn();
        }

    }

    public void simpleTurn()
    {
        checkIfPassedStart(pos+t1+t2);

        pos=(pos+t1 +t2)%40;
        setCar(pos, gui);
        landOnField.hitField(this,gamefields);

        if (t1==t2){
            fartbølle++;
            if (fartbølle==3){
                goToJail();
            }
            else{
                gui.getUserButtonPressed(name + " fik to ens, du fik ekstra tur!", "Rul terninger");
                turn();
                simpleTurn();

            }

        }
        else{fartbølle=0;}


        if (konto.getBalance() <0) {
            bankruptcy();

        }
        else {
            hasLost = false;
        }
    }
    private void bankruptcy()
    {
        String choice =gui.getUserSelection(name+" Du er gået bankerot og skylder mere til banken end du har", "1. Pantsæt grund","2. Sælg hus/hoteller","3. Giv op");

        switch (getChoice(choice))
        {
            case 1:
            {
                pawnField();
                if (konto.getBalance()<0)
                {
                    bankruptcy();
                }
            }
            break;
            case 2:
            {
                sellHouse();
                if (konto.getBalance()<0)
                {
                    bankruptcy();
                }
            }
            break;
            case 3:
            {
                hasLost = true;
            }
        }

    }


    public int getChoice(String choice)
    {
        return Integer.parseInt(choice.split("\\.")[0]);
    }

    //tror virker
    public void inJail()
    {
        jailCounter++;
        if(gui.getUserLeftButtonPressed(name+ " Betal 1000 kr eller slå to ens terninger for at komme ud", "Betal 1000 kr", "Slå to ens terninger"))
        {
            payJail();
        }
        else if(jailCounter==3) {
            turn();
            if (t1 == t2) {
                gui.getUserButtonPressed(name + " Slog to ens og er derfor fri fra fængsel", "okay");
                jail = false;
                jailCounter = 0;
                simpleTurn();
            }
            else {
                gui.getUserButtonPressed(name + " fik ikke tre ens 3 ture i træk og skal derfor betale", "okay");
                payJail();
                jail = false;
                jailCounter = 0;
                simpleTurn();
            }
        }
        else{

            turn();
            if (t1==t2){
                gui.getUserButtonPressed(name + " Slog to ens og er derfor fri fra fængsel", "okay");
                jail = false;
                jailCounter=0;
                simpleTurn();

            }
            else {gui.getUserButtonPressed(name + " fik ikke to ens og sidder derfor stadig i fængsel", "okay");}
        }


    }
    private void payJail(){
        updatePlayerBalance(-1000);
        jail = false;
        spil(this.gui,this.gamefields);
        jailCounter=0;
    }
    public void hitField()
    {
        displayCard();
       //gamefields[pos].hit(this);

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
     gui.setDice(t1, t2);


    }
    public int getTerningeSum()
    {
        return t1+t2;
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

    public void getRent(int rent)
    {
        pl.setBalance(konto.getBalance()+rent);
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
        GUI_Field field = Game_Controller.getFields()[getPos()];;
        GUI_Ownable ownable = (GUI_Ownable) field;
        return checkPlayerOwnsTheColorFields(Game_Controller.getPlayer(ownable.getOwnerName()), Board_Creator.getFieldData().get(pos)[11]);
    }
    public boolean checkPlayerOwnsTheColorFields (Player player, String color)
    {
        ArrayList<String> ownerFields = player.getGrunde();
        ArrayList<String[]> typeFields = Board_Creator.getGroupArray(color);
        ArrayList<String[]> data = Board_Creator.getFieldData();

        boolean sandt = false;
        int f=0;
        for (int i = 0;i<typeFields.size();i++)
        {

            String l =typeFields.get(i)[0];
            int index = Board_Creator.fieldIndexFromName(l);
            if(ownerFields.contains(l) && Integer.parseInt(data.get(index)[13])==0)
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

    public int checkDoubleCost()
    {
        if (checkOwnerOwnAll())
        {
            return 2;
        }
        else return 1;

    }


    public GUI_Car getCar() {
        return car;
    }
}


package spil_Version_2;


import gui_fields.*;

import java.awt.*;
import java.util.ArrayList;


public class Player {
    private final Konto konto = new Konto(0);
    private final UserIO userIO;
    private final String name;
    public boolean prisoncard = false;
    private Terninger terninger = new Terninger();
    public GUI_Player pl;
    public GUI_Field fpos;
    public LandOnField landOnField = new LandOnField();
    private GUI_Car car;
    private GUI_Field[] gamefields;
    private ArrayList<String> grunde = new ArrayList<String>();
    private int pos = 0;
    private int fartbølle = 0;
    private boolean jail = false;
    private boolean hasLost = false;
    private int t1 = 0;
    private int t2 = 0;
    private int jailCounter = 0;

    public Player(String name, int bal, int postiotion,UserIO gui) {
        this.userIO = gui;
        konto.update(bal);
        pos = postiotion;

        this.name = name;

    }

    public void setFpos(GUI_Field fpos) {
        this.fpos = fpos;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public boolean isJail() {
        return jail;
    }

    public void addGrunde(String grund) {
        this.grunde.add(grund);
    }

    public ArrayList<String> getGrunde() {
        return grunde;

    }

    public void setGrunde(ArrayList<String> grunde) {
        this.grunde = grunde;
    }

    //Getter
    public void tilføjspillerGui(Color carCorlor) {
        car = new GUI_Car();
        car.setPrimaryColor(carCorlor);

        GUI_Player player = new GUI_Player(name, konto.getBalance(), car);

        userIO.addPlayer(player);
        GUI_Field field = Game_Controller.getFields()[pos];
        field.setCar(player, true);

        fpos = field;
        pl = player;

    }

    public Konto getKonto() {

        return konto;
    }

    public String getName() {
        return name;
    }

    //spiller en runde for den spiller der er kaldt
    public boolean spil(GUI_Field[] fields) {
        gamefields = fields;

        int choice = userIO.getUserButtonPressed(name + " det er din tur hvad vil du gøre", "1. Spil min tur", "2. Hus/Hotel Menu", "3. Sælg Grund", "4. Pantsætning af grunde menu");
        switch (choice) {
            case 1: {
                runATurn();
            }
            break;
            case 2: {
                hotelHouseMenu();
                spil(fields);
            }
            break;
            case 3: {
                sellField();
                spil(fields);
            }
            break;
            case 4: {
                pawnMenu();
                spil(fields);
            }
            break;
        }
        return hasLost;
    }
    //sells the field that is chosen on a aktion if any other players want to buy it
    public void sellField() {
        ArrayList<String[]> data = Board_Creator.getFieldData();
        ArrayList<String> availableFieldsToSell = new ArrayList<>();

        for (int i = 0; i < this.grunde.size(); i++) {
            int crrIndex = Board_Creator.fieldIndexFromName(grunde.get(i));
            if (Integer.parseInt(data.get(crrIndex)[12]) == 0) {
                availableFieldsToSell.add(this.grunde.get(i));
            }
            String[] choice = availableFieldsToSell.toArray(new String[availableFieldsToSell.size()]);
            if (choice.length == 0) {
                userIO.showMessage("Du har ikke nogle grunde der kan sælges husk at sælge dine huse på grunden før du sælger grund");
            } else {
                String fieldToSell = userIO.getUserSelection("Hvilken grund vil de sælge", choice);
                int fieldIndex = Board_Creator.fieldIndexFromName(fieldToSell);
                GUI_Field f = Game_Controller.getFields()[fieldIndex];
                GUI_Ownable o = (GUI_Ownable) f;
                Aktion aktion = new Aktion();
                int sellPrice = userIO.getUserInteger("Hvad vil du sælge din grund for");
                aktion.runSellFieldAktion(this, o, sellPrice, userIO);

            }

        }

    }

    public void hotelHouseMenu() {
        int choice = userIO.getUserButtonPressed(this.getName(), "1. Køb hus/hotel", "2. sælg hus/hotel");
        switch (choice) {
            case 1: {
                buyHouse();
            }
            break;
            case 2: {
                sellHouse();
            }
            break;
        }
    }
    //gives you the fields you can build houses on
    public void buyHouse() {
        String[] fieldcolors = {"blue", "red", "green", "orange", "grey", "white", "yellow", "purple"};
        ArrayList<String> colorsYouOwn = new ArrayList<>();
        for (int i = 0; i < fieldcolors.length; i++) {
            if (checkPlayerOwnsTheColorFields(this, fieldcolors[i])) {
                colorsYouOwn.add(fieldcolors[i]);
            }
        }
        if (colorsYouOwn.size() == 0) {
            userIO.showMessage("Du har ikke alle felter i en farve og kan derfor ikke købe hus");
        } else {
            ArrayList<String> sameColorFields = new ArrayList<>();

            for (int i = 0; i < colorsYouOwn.size(); i++) {
                ArrayList<String[]> f = Board_Creator.getGroupArray(colorsYouOwn.get(i));
                ArrayList<Integer> index = new ArrayList<>();
                for (int k = 0; k < f.size(); k++) {
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
                for (int r = 0; r < f.size(); r++) {
                    String titel = f.get(r)[0];
                    int fieldIndex = Board_Creator.fieldIndexFromName(titel);
                    if (Integer.parseInt(Board_Creator.getFieldData().get(fieldIndex)[12]) == min &&
                            Integer.parseInt(Board_Creator.getFieldData().get(Board_Creator.fieldIndexFromName(titel))[12]) < 5) {
                        sameColorFields.add(f.get(r)[0]);
                    }
                }

            }
            if (sameColorFields.size() == 0) {
                userIO.showMessage("Du har ikke mulighed for at bygge mere på dine grunde lige nu");
            } else {
                String[] choice = sameColorFields.toArray(new String[sameColorFields.size()]);
                String fieldToSetHouse = userIO.getUserSelection("Hvilken grund vil de købe Hus/hotel til?", choice);

                GUI_Field field = Game_Controller.getFields()[Board_Creator.fieldIndexFromName(fieldToSetHouse)];
                GUI_Street street = (GUI_Street) field;

                int numberOfHouses = Integer.parseInt(Board_Creator.getFieldData().get(Board_Creator.fieldIndexFromName(fieldToSetHouse))[12]);
                int housePrice = Integer.parseInt(Board_Creator.getFieldData().get(Board_Creator.fieldIndexFromName(fieldToSetHouse))[4]);

                if (numberOfHouses < 4) {
                    if (payHouseHotel(housePrice)) {
                        street.setHouses(1 + numberOfHouses);
                        Board_Creator.setHousesInData(1 + numberOfHouses, Board_Creator.fieldIndexFromName(fieldToSetHouse));
                    }

                } else if (numberOfHouses == 4) {
                    if (payHouseHotel(housePrice * 5)) {
                        street.setHouses(0);
                        street.setHotel(true);
                        Board_Creator.setHousesInData(1 + numberOfHouses, Board_Creator.fieldIndexFromName(fieldToSetHouse));
                    }
                }
            }

        }

    }
    //checks if you need to pay for a house or a hotel
    private boolean payHouseHotel(int housePrice) {
        boolean paidForHouse = false;
        if (this.getKonto().getBalance() > housePrice) {
            getKonto().update(-housePrice);
            pl.setBalance(this.konto.getBalance());
            paidForHouse = true;
        } else {
            userIO.showMessage("Du har ikke råd til at købe hus/hotel lige nu");
        }
        return paidForHouse;
    }
    //sells the house or hotel
    private void sellHouseHotel(int housePrice) {
        getKonto().update(housePrice / 2);
        pl.setBalance(this.konto.getBalance());

    }

    //gives you the houses and hotels you can sell if you have any
    public void sellHouse() {
        String[] fieldcolors = {"blue", "red", "green", "orange", "grey", "white", "yellow", "purple"};
        ArrayList<String> colorsYouOwn = new ArrayList<>();
        for (int i = 0; i < fieldcolors.length; i++) {
            if (checkPlayerOwnsTheColorFields(this, fieldcolors[i])) {
                colorsYouOwn.add(fieldcolors[i]);
            }
        }
        if (colorsYouOwn.size() == 0) {
            userIO.showMessage("Du har ikke alle felter i en farve og har derfor ingen huse");
        } else {
            ArrayList<String> sameColorFields = new ArrayList<>();

            for (int i = 0; i < colorsYouOwn.size(); i++) {
                ArrayList<String[]> f = Board_Creator.getGroupArray(colorsYouOwn.get(i));
                ArrayList<Integer> index = new ArrayList<>();
                for (int k = 0; k < f.size(); k++) {
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
                for (int r = 0; r < f.size(); r++) {
                    String titel = f.get(r)[0];
                    int fieldIndex = Board_Creator.fieldIndexFromName(titel);
                    if (Integer.parseInt(Board_Creator.getFieldData().get(fieldIndex)[12]) == max &&
                            Integer.parseInt(Board_Creator.getFieldData().get(Board_Creator.fieldIndexFromName(titel))[12]) > 0) {
                        sameColorFields.add(f.get(r)[0]);
                    }
                }

            }
            if (sameColorFields.size() == 0) {
                userIO.showMessage("Du har ingen huse/hoteller at sælge");
            } else {
                String[] choice = sameColorFields.toArray(new String[sameColorFields.size()]);
                String fieldToSetHouse = userIO.getUserSelection("Hvilken grund vil de sælge deres Hus/hotel?", choice);

                GUI_Field field = Game_Controller.getFields()[Board_Creator.fieldIndexFromName(fieldToSetHouse)];
                GUI_Street street = (GUI_Street) field;

                int numberOfHouses = Integer.parseInt(Board_Creator.getFieldData().get(Board_Creator.fieldIndexFromName(fieldToSetHouse))[12]);
                int housePrice = Integer.parseInt(Board_Creator.getFieldData().get(Board_Creator.fieldIndexFromName(fieldToSetHouse))[4]);

                if (numberOfHouses < 5) {

                    sellHouseHotel(housePrice);
                    street.setHouses(numberOfHouses - 1);
                    Board_Creator.setHousesInData(numberOfHouses - 1, Board_Creator.fieldIndexFromName(fieldToSetHouse));


                } else if (numberOfHouses == 5) {
                    sellHouseHotel(housePrice * 5);
                    street.setHouses(0);
                    street.setHotel(false);
                    Board_Creator.setHousesInData(0, Board_Creator.fieldIndexFromName(fieldToSetHouse));

                }
            }

        }
    }

    public void pawnMenu() {
        int buttonPressed = userIO.getUserButtonPressed("Vil du", "1. Pantsæt Grunde", "2. Køb pantsatte grunde tilbage");
        switch (buttonPressed) {
            case 1: {

                pawnField();
            }
            break;
            case 2: {
                unPawnField();
            }
            break;
        }

    }

    //shows you the fields you can pawn and lets you chose one to pawn
    public void pawnField() {
        ArrayList<String[]> data = Board_Creator.getFieldData();
        ArrayList<String> availableFieldsToParwn = new ArrayList<>();
        for (int i = 0; i < this.grunde.size(); i++) {
            int crrIndex = Board_Creator.fieldIndexFromName(grunde.get(i));
            if (Integer.parseInt(data.get(crrIndex)[12]) == 0 && Integer.parseInt(data.get(crrIndex)[13]) == 0) {
                availableFieldsToParwn.add(this.grunde.get(i));
            }
        }
        String[] choice = availableFieldsToParwn.toArray(new String[availableFieldsToParwn.size()]);
        if (choice.length == 0) {
            userIO.showMessage("Du har ikke nogle grunde der kan pantsættes husk at sælge dine huse på grunden før du pantsætter");
        } else {
            String fieldToPawn = userIO.getUserSelection("Hvilken grund vil de pantsætte", choice);
            int fieldToPawnIndex = Board_Creator.fieldIndexFromName(fieldToPawn);
            Board_Creator.setPawnStatusInData(true, fieldToPawnIndex);
            int value = Integer.parseInt(data.get(fieldToPawnIndex)[3])/2;
            this.getKonto().update(value);
            pl.setBalance(getKonto().getBalance());
        }


    }
        //works lige pawnfield just makes you buy back a pawnfield
    public void unPawnField() {
        ArrayList<String[]> data = Board_Creator.getFieldData();
        ArrayList<String> availableFieldsToParwn = new ArrayList<>();
        for (int i = 0; i < this.grunde.size(); i++) {
            int crrIndex = Board_Creator.fieldIndexFromName(grunde.get(i));
            if (Integer.parseInt(data.get(crrIndex)[13]) == 1) {
                availableFieldsToParwn.add(this.grunde.get(i));
            }
            String[] choice = availableFieldsToParwn.toArray(new String[availableFieldsToParwn.size()]);
            if (choice.length == 0) {
                userIO.showMessage("Du har ingen pantsatte grunde");
            } else {
                String fieldToPawn = userIO.getUserSelection("Hvilken grund vil de købe tilbage", choice);
                int fieldToPawnIndex = Board_Creator.fieldIndexFromName(fieldToPawn);
                Board_Creator.setPawnStatusInData(false, fieldToPawnIndex);
                int costToBuyBack = ((Integer.parseInt(data.get(fieldToPawnIndex)[3]) / 2) / 10) + (Integer.parseInt(data.get(fieldToPawnIndex)[3]) / 2);
                int rounded  = ((costToBuyBack + 99) / 100) * 100;
                this.getKonto().update((-rounded));
                pl.setBalance(getKonto().getBalance());
            }

        }
    }
    //runs a full player turn
    private void runATurn() {
        if (jail) {
            inJail();


        } else if (userIO.getUserButtonPressed(name + " Klik på knappen for at rulle med terningerne", "1. Rul terninger") == 1) {
            turn();
            simpleTurn();
        }

    }
    //a smaller part of the turn that you can use if you dont want to role dices
    public void simpleTurn() {
        checkIfPassedStart(pos + t1 + t2);

        pos = (pos + t1 + t2) % 40;
        fpos = userIO.setCar(pos, pl, fpos);
        landOnField.hitField(this, gamefields, userIO);

        if (t1 == t2) {
            fartbølle++;
            if (fartbølle == 3) {
                goToJail();
            } else {
                userIO.getUserButtonPressed(name + " fik to ens, du fik ekstra tur!", "1. Rul terninger");
                turn();
                simpleTurn();

            }

        } else {
            fartbølle = 0;
        }


        if (konto.getBalance() < 0) {
            bankruptcy();

        } else {
            hasLost = false;
        }
    }
    //makes you sell houses/hotels and pawnfields if you go broke or gives you the posibility to give up and all the fields will be back on the market
    private void bankruptcy() {
        int choice = userIO.getUserButtonPressed(name + " Du er gået bankerot og skylder mere til banken end du har", "1. Pantsæt grund", "2. Sælg hus/hoteller", "3. Giv op");

        switch (choice) {
            case 1: {
                pawnField();
                if (konto.getBalance() < 0) {
                    bankruptcy();
                }
            }
            break;
            case 2: {
                sellHouse();
                if (konto.getBalance() < 0) {
                    bankruptcy();
                }
            }
            break;
            case 3: {
                hasLost = true;
                for (int i = 0;i<grunde.size();i++)
                {
                  String resetGrund =  grunde.get(i);
                  int index = Board_Creator.fieldIndexFromName(resetGrund);
                  GUI_Field field = Game_Controller.getFields()[i];
                  GUI_Ownable ownable = (GUI_Ownable) field;
                  ownable.setOwnableLabel("");
                  ownable.setOwnerName("");
                  ownable.setBorder(Color.black);


                }
            }
        }

    }


    //the method makes alle the things in jail happen
    public void inJail() {
        jailCounter++;
        if (userIO.getUserLeftButtonPressed(name + " Betal 1000 kr eller slå to ens terninger for at komme ud", "Betal 1000 kr", "Slå to ens terninger")) {
            payJail();
        } else if (jailCounter == 3) {
            turn();
            if (t1 == t2) {
                userIO.showMessage(name + " Slog to ens og er derfor fri fra fængsel");
                jail = false;
                jailCounter = 0;
                simpleTurn();
            } else {
                userIO.showMessage(name + " fik ikke tre ens 3 ture i træk og skal derfor betale");
                payJail();
                jail = false;
                jailCounter = 0;
                simpleTurn();
            }
        } else {

            turn();
            if (t1 == t2) {
                userIO.showMessage(name + " Slog to ens og er derfor fri fra fængsel");
                jail = false;
                jailCounter = 0;
                simpleTurn();

            } else {
                userIO.showMessage(name + " fik ikke to ens og sidder derfor stadig i fængsel");
            }
        }


    }
    //this will get you out of jail
    private void payJail() {
        updatePlayerBalance(-1000);
        jail = false;
        spil(this.gamefields);
        jailCounter = 0;
    }



    public void checkIfPassedStart(int sumPos) {
        if (sumPos >= 40) {
            updatePlayerBalance(4000);
        }
    }


    public void turn() {
        t1 = Terninger.slaEnTerning();
        t2 = Terninger.slaEnTerning();
        userIO.setDice(t1, t2);


    }

    public int getTerningeSum() {
        return t1 + t2;
    }


    public void displayCard() {
        GUI_Field f = Game_Controller.getFields()[pos];
        userIO.displayChanceCard(f.getTitle(), f.getDescription());
    }

    public void getRent(int rent) {
        pl.setBalance(konto.getBalance() + rent);
    }

    public void payRent(int cost, Player owner, String title) {
        userIO.showMessage(pl.getName() + " landede på " + title + " og skal betale leje til " + owner.getName());
        owner.getKonto().update(cost);
        getKonto().update(-cost);
        owner.setGUIBalance(owner.getKonto().getBalance());
        pl.setBalance(this.konto.getBalance());
    }


    public void setGUIBalance(int balance) {
        pl.setBalance(balance);
    }


    public void buyField(int cost, String title) {
        userIO.showMessage(pl.getName() + " købte " + title);
        updatePlayerBalance(-cost);
        konto.updateFieldValue(cost);
        addGrunde(title);

    }

    public void goToJail() {
        movePlayer(10);
        jail = true;
        userIO.showMessage(name + " du er røget i fængsel får dårlig opførelse");
    }

    public void movePlayer(int number) {
        if (number < 0) {
            number = number + 40;
        }
        this.pos = number % 40;
        checkIfPassedStart(pos);
        fpos = userIO.setCar(pos, pl, fpos);
    }

    public void showchancecard(String txt) {
        userIO.displayChanceCard(txt, "");
        userIO.showMessage(name + " " + txt);

    }

    public void updatePlayerBalance(int value) {
        konto.update(value);
        pl.setBalance(konto.getBalance());
    }

    //chekker om owneren har alle grunde i et sæt
    public boolean checkOwnerOwnAll() {
        GUI_Field field = Game_Controller.getFields()[getPos()];
        GUI_Ownable ownable = (GUI_Ownable) field;
        return checkPlayerOwnsTheColorFields(Game_Controller.getPlayer(ownable.getOwnerName(), userIO), Board_Creator.getFieldData().get(pos)[11]);
    }
    //method checks if the player owns alle the fields in a specific group/color
    public boolean checkPlayerOwnsTheColorFields(Player player, String color) {
        ArrayList<String> ownerFields = player.getGrunde();
        ArrayList<String[]> typeFields = Board_Creator.getGroupArray(color);
        ArrayList<String[]> data = Board_Creator.getFieldData();

        boolean sandt = false;
        int f = 0;
        for (int i = 0; i < typeFields.size(); i++) {

            String l = typeFields.get(i)[0];
            int index = Board_Creator.fieldIndexFromName(l);
            if (ownerFields.contains(l) && Integer.parseInt(data.get(index)[13]) == 0) {
                f++;
            }
            if (f == typeFields.size()) {
                sandt = true;
            }
        }

        return sandt;
    }

    public int checkDoubleCost() {
        if (checkOwnerOwnAll()) {
            return 2;
        } else return 1;

    }


    public GUI_Car getCar() {
        return car;
    }
}


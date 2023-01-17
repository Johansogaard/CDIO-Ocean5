package spil_Version_2;

import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

public abstract class UserIO {


    public abstract int getUserButtonPressed(String s, String... keys);

    public abstract void showMessage(String msg);

    public abstract String getUserSelection(String msg, String[] choice);

    public abstract int getUserInteger(String msg);


    public abstract void setCar(int tsum, GUI_Player pl, GUI_Field fpos);

    public abstract boolean getUserLeftButtonPressed(String msg, String bt1, String bt2);

    public abstract void setDice(int t1, int t2);

    public abstract void displayChanceCard(String title, String description);

    public abstract void addPlayer(GUI_Player player);
}

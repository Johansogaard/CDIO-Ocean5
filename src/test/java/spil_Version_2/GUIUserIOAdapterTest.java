package spil_Version_2;

import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

public class GUIUserIOAdapterTest extends UserIO{
    int choice;
    boolean b;

    public GUIUserIOAdapterTest(int choice, boolean b)
    {
        this.b = b;
        this.choice = choice;
    }
    @Override
    public int getUserButtonPressed(String s, String... keys) {

        return choice;
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public String getUserSelection(String msg, String[] choice) {
        return null;
    }

    @Override
    public int getUserInteger(String msg) {
        return choice;
    }

    @Override
    public GUI_Field setCar(int tsum, GUI_Player pl, GUI_Field fpos) {
        return null;
    }

    @Override
    public boolean getUserLeftButtonPressed(String msg, String bt1, String bt2) {
        return b;
    }

    @Override
    public void setDice(int t1, int t2) {

    }

    @Override
    public void displayChanceCard(String title, String description) {

    }

    @Override
    public void addPlayer(GUI_Player player) {

    }
}

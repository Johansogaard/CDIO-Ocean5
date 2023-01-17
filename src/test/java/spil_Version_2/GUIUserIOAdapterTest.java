package spil_Version_2;

import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

public class GUIUserIOAdapterTest extends UserIO{
    boolean b[];
    int bCounter =-1;
    int choice[];
    int choiceCounter = -1;
    public GUIUserIOAdapterTest(int choice[], boolean b[])
    {
        this.b = b;
        this.choice = choice;
    }
    @Override
    public int getUserButtonPressed(String s, String... keys) {
        choiceCounter++;
        return choice[choiceCounter];

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
        return 0;
    }

    @Override
    public GUI_Field setCar(int tsum, GUI_Player pl, GUI_Field fpos) {
        return null;
    }

    @Override
    public boolean getUserLeftButtonPressed(String msg, String bt1, String bt2) {
        bCounter++;
        return b[bCounter];
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

package spil_Version_2;


import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class GUIUserIOAdapter extends UserIO {
    private final GUI gui;

    public GUIUserIOAdapter(GUI gui) {
        this.gui = gui;
    }

    @Override
    public int getUserButtonPressed(String s, String... keys) {
        String choice = gui.getUserButtonPressed(s, keys);
        return getChoice(choice);
    }
    private int getChoice(String choice) {
        return Integer.parseInt(choice.split("\\.")[0]);
    }
    @Override
    public void showMessage(String msg)
    {
        gui.showMessage(msg);
    }

    @Override
    public String getUserSelection(String msg, String[] choice) {
        return gui.getUserSelection(msg,choice);
    }

    @Override
    public int getUserInteger(String msg) {
        return gui.getUserInteger(msg);
    }

    @Override
    public GUI_Field setCar(int tsum, GUI_Player pl,GUI_Field fpos) {

        tsum = tsum % 40;
        fpos.setCar(pl, false);
        GUI_Field felt = gui.getFields()[tsum];
        felt.setCar(pl, true);
        fpos = gui.getFields()[tsum];
        return fpos;

    }

    @Override
    public boolean getUserLeftButtonPressed(String msg, String bt1, String bt2) {
        return gui.getUserLeftButtonPressed(msg,bt1,bt2);
    }

    @Override
    public void setDice(int t1, int t2) {
        gui.setDice(t1,t2);
    }

    @Override
    public void displayChanceCard(String title, String description) {
        gui.displayChanceCard(title + "\n"+ description);
    }

    @Override
    public void addPlayer(GUI_Player player) {
        gui.addPlayer(player);
    }
}

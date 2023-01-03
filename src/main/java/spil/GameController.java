package spil;

import game_Txt.FieldText;
import gui_GameFields.*;
import gui_main.GUI;

import java.awt.*;

public class GameController {
    final static String file = "src/main/resources/GameText.txt";
    public static FieldText mt = FieldText.getInstance();
    public static int max=-100;
    public static Player vinder;
    public static Boolean hasWon = false;
    public static void main(String[] args) {
        Player[] players = GameFeatures.playerstoadd();

           // System.out.println(readTextFromFile(file,"jailMessage"));
            GUI_Parentfield[] fields = istantiererFelter();
           GUI gui = new GUI(fields, Color.cyan);
           for (int i = 0; i < players.length; i++) {
               players[i].tilføjspillerGui(gui);
           }
           playGame(players,fields,gui);
       }
public static void playGame (Player[] players,GUI_Parentfield[] fields,GUI gui)
{

        for (int i = 0; i < players.length; i=(i+1)%players.length) {
            if(players[i].spil(gui,fields))
            {
                break;
            }


        }



    for (int i = 0; i < players.length; i++)
    {

        if (players[i].getKonto().getBalance() == max)
        {
            if(players[i].getKonto().getFieldvalue() > vinder.getKonto().getFieldvalue())
            {
                vinder = players[i];
            }
        }
        else if (players[i].getKonto().getBalance()>max)
      {
        max = players[i].getKonto().getBalance();
       vinder = players[i];
      }

    }
    gui.getUserButtonPressed(vinder.getName() + " har vundet med flest penge", "Okay");
    System.exit(1);

}

public static GUI_Parentfield[] istantiererFelter()
{
GUI_Start gStart = new GUI_Start();
GUI_Rodovrevej gkBurgerbaren = new GUI_Rodovrevej();
GUI_Hvidovrevej phPizzariaet = new GUI_Hvidovrevej();
GUI_ValbyLanggade gButikken = new GUI_ValbyLanggade();
GUI_Roskildevej isKiosken = new GUI_Roskildevej();
GUI_Chance chanceField1 = new GUI_Chance();
GUI_Jail jail = new GUI_Jail();

jail.setSubText(mt.mp.get("jailS"));
GUI_Museet museet = new GUI_Museet();
GUI_biblo biblioteket = new GUI_biblo();
GUI_Chance chanceField2 = new GUI_Chance();
GUI_SkaterParken skaterParken = new GUI_SkaterParken();
GUI_SwimmingPoolen swimmingPoolen = new GUI_SwimmingPoolen();
GUI_Parkering parkering = new GUI_Parkering();
GUI_Spillehallen spillehallen = new GUI_Spillehallen();
GUI_Biografen biografen=  new GUI_Biografen();
GUI_Chance chanceField3 = new GUI_Chance();
GUI_Legetøjsbutikken legetøjsbutikken = new GUI_Legetøjsbutikken();
GUI_Dyrebutikken dyrebutikken = new GUI_Dyrebutikken();
GUI_GoToJail goToJail = new GUI_GoToJail();
GUI_Bowlinghallen bowlinghallen = new GUI_Bowlinghallen();
GUI_Zoologiskhave zoologiskhave = new GUI_Zoologiskhave();
GUI_Chance chanceField4 = new GUI_Chance();
GUI_Vandlandet vandlandet = new GUI_Vandlandet();
GUI_Strandpromenaden strandpromenaden = new GUI_Strandpromenaden();

GUI_Parentfield[] fields ={gStart,gkBurgerbaren,phPizzariaet,chanceField1,gButikken,
        isKiosken,goToJail,museet,biblioteket,chanceField2, skaterParken,swimmingPoolen, parkering, spillehallen,
        biografen,chanceField3, legetøjsbutikken,dyrebutikken, jail,bowlinghallen,zoologiskhave,chanceField4,vandlandet, strandpromenaden};


return fields;
}

}

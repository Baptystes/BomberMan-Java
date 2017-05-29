import org.newdawn.slick.*;
import java.awt.Font;

public class Texte {

    private TrueTypeFont font;

    Texte ()
    {
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
    }

    public void afficher(int positX, int positY, String texte)
    {
        font.drawString(positX, positY,texte);
    }
}

import org.newdawn.slick.*;
import java.util.*;
public class FlammeBleu extends Bonus{
    public FlammeBleu(int positX, int positY, Terrain terrain)
    {
        super (positX, positY, terrain);
    }

    @Override
    public void prendEffet(Personnage joueur)
    {
        joueur.ajouterTailleFlamme(1);
        System.out.print("Flamme bleue!");
    }


}

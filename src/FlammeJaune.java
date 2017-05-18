import org.newdawn.slick.*;

public class FlammeJaune extends Bonus{

    public FlammeJaune(int positX, int positY, Terrain terrain)
    {
        super (positX, positY, terrain);

        try {
            image = new Image("images/bonus/flamme_jaune.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        bonusSize = 20;

    }

    @Override
    public void prendEffet(Personnage joueur)
    {
        joueur.ajouterTailleFlamme(1);
        System.out.print("Flamme jaune!");
    }
}

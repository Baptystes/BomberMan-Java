import org.newdawn.slick.*;

public class BombeMoins extends Bonus{

    public BombeMoins(int positX, int positY, Terrain terrain)
    {
        super (positX, positY, terrain);

        try {
            image = new Image("images/bonus/bombe_moins.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        bonusSize = 25;

    }

    @Override
    public void prendEffet(Personnage joueur)
    {
        joueur.ajouterNombreBombe(-2);
        System.out.print("Bombe -!");
    }
}


import org.newdawn.slick.*;

public class BombePlus extends Bonus{

    public BombePlus(int positX, int positY, Terrain terrain)
    {
        super (positX, positY, terrain);

        try {
            image = new Image("images/bonus/bombe_plus.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        bonusSize = 20;

    }

    @Override
    public void prendEffet(Personnage joueur)
    {
        joueur.ajouterNombreBombe(2);
        System.out.print("Bombe +!");
    }
}


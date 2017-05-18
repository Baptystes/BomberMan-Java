import org.newdawn.slick.*;

public class FlammeRouge extends Bonus{

    public FlammeRouge(int positX, int positY, Terrain terrain)
    {
        super (positX, positY, terrain);

        try {
            image = new Image("images/bonus/flamme_rouge.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        bonusSize = 20;

    }

    @Override
    public void prendEffet(Personnage joueur)
    {
        joueur.setTailleFlamme(10);
        System.out.print("Flamme rouge!");
    }
}

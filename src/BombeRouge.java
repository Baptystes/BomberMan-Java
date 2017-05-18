import org.newdawn.slick.*;

public class BombeRouge extends Bonus{

    public BombeRouge(int positX, int positY, Terrain terrain)
    {
        super (positX, positY, terrain);

        try {
            image = new Image("images/bonus/bombe_rouge.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        bonusSize = 20;

    }

    @Override
    public void prendEffet(Personnage joueur)
    {
        joueur.setBombeRouge();
        System.out.print("Bombe rouge!");
    }
}


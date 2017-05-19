import org.newdawn.slick.*;

public class FlammeBleu extends Bonus{

    public FlammeBleu(Interface interfaceBM, Terrain terrain, int positX, int positY)
    {
        super (interfaceBM, terrain, positX, positY);

        try {
            image = new Image("images/bonus/flamme_bleue.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        bonusSize = 20;

    }

    @Override
    public void prendEffet(Personnage joueur)
    {
        joueur.ajouterTailleFlamme(-1);
        System.out.print("Flamme bleue!");
    }
}

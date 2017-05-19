import org.newdawn.slick.*;

public class FlammeRouge extends Bonus{

    public FlammeRouge(Interface interfaceBM, Terrain terrain, int positX, int positY)
    {
        super (interfaceBM, terrain, positX, positY);

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

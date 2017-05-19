import org.newdawn.slick.*;

public class FlammeVerte extends Bonus{

    public FlammeVerte(Interface interfaceBM, Terrain terrain, int positX, int positY)
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
        joueur.ajouterTailleFlamme(1);
        joueur.ajoutTempsDetonation(-1000);
        System.out.print("Flamme Verte!");
    }
}

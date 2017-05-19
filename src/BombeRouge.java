import org.newdawn.slick.*;

public class BombeRouge extends Bonus{

    public BombeRouge(Interface interfaceBM, Terrain terrain, int positX, int positY)
    {
        super (interfaceBM, terrain, positX, positY);

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


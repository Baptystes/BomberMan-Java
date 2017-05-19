import org.newdawn.slick.*;

public class Bonus {

    private int positX, positY;
    private Terrain terrain;
    protected Image image;
    protected int bonusSize;
    private Interface interfaceBM;

    private boolean doitDisparaitre;

    public Bonus (Interface interfaceBM, Terrain terrain, int positX, int positY)
    {
        this.positX = positX;
        this.positY = positY;
        this.terrain = terrain;
        doitDisparaitre = false;
        this.interfaceBM = interfaceBM;



    }

    public int getPositX() {return positX;}
    public int getPositY() {return positY;}

    public void prendEffet(Personnage joueur)
    {

    }

    public void afficher (Graphics g)
    {
        int tileSize = terrain.getTileSize();
        int tileBorder = terrain.getTileBorder();

        image.draw(positX * (tileSize + tileBorder) + tileSize / 2 - bonusSize / 2, positY * (tileSize + tileBorder) + tileSize / 2 - bonusSize / 2, bonusSize, bonusSize);
    }

    public void faireDisparaitre()
    {
        doitDisparaitre = true;
    }

    public boolean doitDisparaitre()
    {
        return doitDisparaitre;
    }
}

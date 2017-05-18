import org.newdawn.slick.*;

public class Bonus {

    private int positX, positY;
    private Terrain terrain;

    private boolean doitDisparaitre;

    public Bonus (int positX, int positY, Terrain terrain)
    {
        this.positX = positX;
        this.positY = positY;
        this.terrain = terrain;
        doitDisparaitre = false;
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

        g.setColor(Color.blue);
        g.fillOval(positX * (tileSize + tileBorder) + tileSize / 2 - 7 / 2, positY * (tileSize + tileBorder) + tileSize / 2 - 7 / 2, 7, 7);
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

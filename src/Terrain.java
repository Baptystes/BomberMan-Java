import org.lwjgl.Sys;
import org.newdawn.slick.*;
import java.util.Vector;

public class Terrain {


    private int tileSize, tileBorder, bombSize;

    //private Vector bombes= new Vector();
    public Vector<Bombe> bombes = new Vector<Bombe>(0);

    private int tabMap [] = {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1,
            1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0, 0, 0, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 1,
            1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0, 1, 2, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
            1, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 0, 0, 0, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
            1, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};



    Terrain (int tileSize, int tileBorder)
    {
        this.tileSize = tileSize;
        this.tileBorder = tileBorder;
        bombes.clear();
        bombSize = 6;
    }

    public int getTileSize(){ return tileSize; }
    public int getTileBorder(){ return tileBorder; }
    public int getIdTile (int positX, int positY) { return tabMap[positY * 21 + positX]; }

    public void dessinerMap (Graphics g)
    {

        for (int a=0 ; a<21 ; a++)
        {
            for (int b=0 ; b<17 ; b++)
            {
                int index = b*21 + a;
                if (tabMap[index] == 1)
                    g.setColor(Color.gray);
                else if (tabMap[index] == 2)
                    g.setColor(Color.orange);
                else
                    g.setColor(Color.green);
                g.fillRect(a*(tileSize + tileBorder), b*(tileSize + tileBorder), tileSize, tileSize);
                //System.out.print(tabMap[index] + ", ");
            }
            //System.out.print("\n");

        }

    }


    public void poserBombe (Personnage perso)
    {

        if (perso.peutPoserBombe() == 1)
        {
            bombes.add(new Bombe(perso));
            perso.poseUneBombe();
            System.out.print("\nHeu");
        }


    }

    public void afficherBombes (Graphics g)
    {
        for (int a=0 ; a<bombes.size() ; a++)
        {
            afficherBombe(g, bombes.get(a));
        }
    }

    private void afficherBombe (Graphics g, Bombe bombe)
    {
        g.setColor(Color.black);
        g.fillOval(bombe.getPositX() * (tileSize + tileBorder) + tileSize/2 - bombSize/2, bombe.getPositY() * (tileSize + tileBorder) + tileSize/2 - bombSize/2, bombSize, bombSize);
    }

    public void gestionBombes ()
    {
        for (int a=0 ; a< bombes.size() ; a++)
        {
            if (bombes.get(a).mettreAJour() == 1)
            {
                bombes.get(a).getAuteur().supprimeUneBombe();
                bombes.removeElementAt(a);
                System.out.print(" REMOVE");
            }
        }
    }



}



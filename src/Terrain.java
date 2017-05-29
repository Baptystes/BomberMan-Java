import org.lwjgl.Sys;
import org.newdawn.slick.*;

import java.util.*;

public class Terrain {


    private int bombSize;

    //private Vector bombes= new Vector();
    private Vector<Bombe> bombes = new Vector<Bombe>(0);
    private Vector<Bonus> bonus = new Vector<Bonus>(0);
    private Affichage affichage;

    private Random r = new Random();




    private int tabMapInit [] =  {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1,
            1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0, 1, 0, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 1,
            1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
            1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 0, 1, 0, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
            1, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private int [] tabMap = new int[tabMapInit.length];



    Terrain (Affichage affichage)
    {


        this.affichage = affichage;

        bombes.clear();
        bonus.clear();
    }

    public void reset()
    {
        for (int a=0 ; a<tabMapInit.length ; a++)
            tabMap[a] = tabMapInit[a];
        bombes.clear();
        bonus.clear();
    }

    public int getIdBloc (int positX, int positY) { return tabMap[positY * 21 + positX]; }
    public void setIdBloc (int positX, int positY, int idBloc) { tabMap[positY * 21 + positX] = idBloc;}

    public void dessinerMap (Graphics g)
    {

        for (int a=0 ; a<21 ; a++)
        {
            for (int b=0 ; b<17 ; b++)
            {
                affichage.blocMap (g, a, b, getIdBloc(a, b));
                //System.out.print(tabMap[index] + ", ");
            }
            //System.out.print("\n");

        }

    }


    public void poserBombe (Personnage perso, Personnage deuxiemePerso)
    {

        if (perso.peutPoserBombe() == 1 && detectBombe(perso.getPositX(), perso.getPositY()) == null)
        {
            bombes.add(new Bombe(affichage, perso, deuxiemePerso, this));
            perso.poseUneBombe();
            //System.out.print("\nPose");
        }
    }

    public void afficherBombes (Graphics g)
    {
        Bombe bombe;
        for (int a=0 ; a<bombes.size() ; a++)
        {
            bombe = bombes.get(a);
            bombe.afficher(g);
        }
    }

    public void gestionBombes ()
    {
        for (int a=0 ; a< bombes.size() ; a++)
        {
            if (bombes.get(a).mettreAJour() == 1)
            {
                bombes.get(a).getAuteur().supprimeUneBombe();
                bombes.removeElementAt(a);
                //System.out.print(" REMOVE");
            }
        }
    }

    public void detruireBloc (int positX, int positY)
    {
        setIdBloc(positX, positY, 0);
        if (r.nextInt(100+1)<=20)
            poserBonus(positX, positY, r.nextInt(7));
    }

    public Bombe detectBombe (int positX, int positY)
    {

        for (int a=0 ; a< bombes.size() ; a++)
        {
            if (bombes.get(a).getPositX() == positX && bombes.get(a).getPositY() == positY)
                return bombes.get(a);
        }
        return null;
    }
    public boolean peutTraverser (int positX, int positY)
    {
        if (getIdBloc(positX,positY) == 0 && detectBombe(positX, positY) == null)
            return true;
        else
            return false;
    }



    public void afficherBonus(Graphics g)
    {
        for (int a=0 ; a<bonus.size() ; a++)
        {
                bonus.get(a).afficher(g);
        }
    }

    public void poserBonus (int positX, int positY, int idBonus)
    {
        bonus.add(new Bonus(affichage, this, idBonus, positX, positY));
        System.out.println("\nSpawnBonus:"+idBonus);
    }

    public Bonus detectBonus (int positX, int positY)
    {
        for (int a=0 ; a< bonus.size() ; a++)
        {
            if (bonus.get(a).getPositX() == positX && bonus.get(a).getPositY() == positY)
            {
                bonus.get(a).faireDisparaitre();
                return bonus.get(a);
            }
        }
        return null;
    }

    public void gestionBonus ()
    {
        for (int a=0 ; a<bonus.size() ; a++)
        {
            if (bonus.get(a).doitDisparaitre())
                bonus.removeElementAt(a);

        }
    }

}



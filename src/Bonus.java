import org.newdawn.slick.*;

public class Bonus {

    private int positX, positY;
    private Terrain terrain;
    protected Image image;
    protected int bonusSize;
    protected int idBonus;
    private Affichage affichage;

    private boolean doitDisparaitre;

    public Bonus (Affichage affichage, Terrain terrain, int idBonus, int positX, int positY)
    {
        this.positX = positX;
        this.positY = positY;
        this.terrain = terrain;
        doitDisparaitre = false;
        this.affichage = affichage;

        this.idBonus = idBonus;


    }

    public int getPositX() {return positX;}
    public int getPositY() {return positY;}

    public void prendEffet(Personnage joueur)
    {
        if (idBonus == 4)
        {
            joueur.ajouterNombreBombe(-2);
            System.out.print("Bombe -!");
            Son.playsonbonus();
        }
        else if (idBonus == 5)
        {
            joueur.ajouterNombreBombe(2);
            System.out.print("Bombe +!");
            Son.playsonbonus();
        }
        else if (idBonus == 6)
        {
            joueur.setBombeRouge();
            System.out.print("Bombe rouge!");    ///////////////////
            Son.playsonbonus();
        }
        else if (idBonus == 0)
        {
            joueur.ajouterTailleFlamme(-1);
            System.out.print("Flamme bleue!");
            Son.playsonbonus();
        }
        else if (idBonus == 1)
        {
            joueur.ajouterTailleFlamme(1);
            System.out.print("Flamme jaune!");
            Son.playsonbonus();
        }
        else if (idBonus == 2)
        {
            joueur.setTailleFlamme(10);
            System.out.print("Flamme rouge!");
            Son.playsonbonus();
        }
        else if (idBonus == 3)
        {
            joueur.ajouterTailleFlamme(1);
            joueur.ajoutTempsDetonation(-1000);
            System.out.print("Flamme Verte!");
            Son.playsonbonus();
        }

    }
    public int getId ()
    {
        return idBonus;
    }

    public void afficher (Graphics g)
    {
            int tileSize = affichage.getTileSize();
            int tileBorder = affichage.getTileBorder();
            affichage.bonus (this);
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

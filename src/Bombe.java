import java.awt.*;

public class Bombe {
    private int positX, positY, tempsAvantExplosion, etat;
    private Chronometre chronometre;
    private Personnage auteur;


    Bombe (Personnage perso)
    {
        auteur = perso;
        positX = auteur.getPositX();
        positY = auteur.getPositY();

        tempsAvantExplosion = 0;

        chronometre = new Chronometre();
        chronometre.reDemarrer(3000);
        etat = 1;


    }

    public int getPositX() { return positX; }
    public int getPositY() { return positY; }



    public int mettreAJour ()
    {
        //System.out.println(chronometre.getTempsRestant());
        if (chronometre.checkFinished() == 1)
            return 1;
        else
            return 0;
    }

    public Personnage getAuteur ()
    {
        return auteur;
    }

    //int getPositt { return positY; }
/*
    void poser(Personnage perso)
    {
        positX = perso.getPositX();
        positY = perso.getPositY();
        idJoueur = perso.getIdPerso();
    }*/
}

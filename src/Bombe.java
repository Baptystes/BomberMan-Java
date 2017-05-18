import java.awt.*;

public class Bombe {
    private int positX, positY, tempsAvantExplosion, etat, flammePortee, flammeHaut, flammeBas, flammeGauche, flammeDroite;
    private Chronometre chronometre;
    private Personnage auteur;
    private Terrain terrain;

    Bombe (Personnage perso, Terrain terrain)
    {
        auteur = perso;
        this.terrain = terrain;

        positX = auteur.getPositX();
        positY = auteur.getPositY();

        flammePortee = auteur.getBombe_portee();

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
        {
            exploser();
            return 1;
        }
        else
            return 0;
    }

    public void exploser ()
    {
        etat = 2;
        int stopAvance = 0;
        int idBloc;
        while (stopAvance == 0) // Haut
        {
            if (flammeHaut < flammePortee)
            {
                if ((idBloc = terrain.getIdBloc(positX, positY - flammeHaut -1)) == 0)
                    flammeHaut++;
                else if (idBloc == 2)
                {
                    flammeHaut++;
                    stopAvance++;
                    terrain.detruireBloc(positX, positY - flammeHaut);
                }
                else if (idBloc == 1)
                    stopAvance++;
            }
            else
                stopAvance++;
        }

        stopAvance = 0;
        while (stopAvance == 0) // Bas
        {
            if (flammeBas < flammePortee)
            {
                if ((idBloc = terrain.getIdBloc(positX, positY + flammeBas +1)) == 0)
                    flammeBas++;
                else if (idBloc == 2)
                {
                    flammeBas++;
                    stopAvance++;
                    terrain.detruireBloc(positX, positY + flammeBas);
                }
                else if (idBloc == 1)
                    stopAvance++;
            }
            else
                stopAvance++;
        }

        stopAvance = 0;
        while (stopAvance == 0) // Gauche
        {
            if (flammeGauche < flammePortee)
            {
                if ((idBloc = terrain.getIdBloc(positX - flammeGauche -1, positY)) == 0)
                    flammeGauche++;
                else if (idBloc == 2)
                {
                    flammeGauche++;
                    stopAvance++;
                    terrain.detruireBloc(positX - flammeGauche, positY);
                }
                else if (idBloc == 1)
                    stopAvance++;
            }
            else
                stopAvance++;
        }

        stopAvance = 0;
        while (stopAvance == 0) // Droite
        {
            if (flammeDroite < flammePortee)
            {
                if ((idBloc = terrain.getIdBloc(positX + flammeDroite +1, positY)) == 0)
                    flammeDroite++;
                else if (idBloc == 2)
                {
                    flammeDroite++;
                    stopAvance++;
                    terrain.detruireBloc(positX + flammeDroite, positY);
                }
                else if (idBloc == 1)
                    stopAvance++;
            }
            else
                stopAvance++;
        }


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

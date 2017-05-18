import org.lwjgl.Sys;
import org.newdawn.slick.*;



public class Bombe {
    private int positX, positY, tempsAvantExplosion, etat, flammePortee, flammeHaut, flammeBas, flammeGauche, flammeDroite, bombeSize;
    private boolean traverseBloc;
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
        flammeHaut = 0; flammeBas = 0; flammeDroite = 0; flammeGauche = 0;

        tempsAvantExplosion = auteur.getBombe_tempsAvantExplosion();

        traverseBloc = auteur.getBombeRouge();

        chronometre = new Chronometre();
        chronometre.reDemarrer(tempsAvantExplosion);
        etat = 1;

        bombeSize = 5;




    }

    public int getPositX() { return positX; }
    public int getPositY() { return positY; }
    public int getEtat() { return etat; }



    public int mettreAJour ()
    {
        //System.out.println(chronometre.getTempsRestant());
        if (chronometre.checkFinished() == 1 && etat == 1)
        {
            exploser();
            return 0;

        }
        else if (etat == 2 && chronometre.checkFinished() == 1)
        {
            etat = 0;
            return 1;
        }
        else
            return 0;
    }

    public void exploser ()
    {

        if (etat == 1)
        {
            chronometre.reDemarrer(1000);
            etat = 2;
        }
        int stopAvance = 0;
        int idBloc;
        Bombe bombe;
        while (stopAvance == 0) // Haut
        {
            if (flammeHaut < flammePortee)
            {
                if ((idBloc = terrain.getIdBloc(positX, positY + flammeHaut +1)) == 0)
                {
                    flammeHaut++;
                    if ((bombe = terrain.detectBombe(positX, positY + flammeHaut)) != null)
                        bombe.exploser();
                }
                else if (idBloc == 2)
                {
                    flammeHaut++;
                    if (!traverseBloc)
                        stopAvance++;
                    terrain.detruireBloc(positX, positY + flammeHaut);
                }
                else if (idBloc == 1)
                {
                    stopAvance++;
                }
            }
            else
                stopAvance++;
        }

        stopAvance = 0;
        while (stopAvance == 0) // Bas
        {
            if (flammeBas < flammePortee)
            {
                if ((idBloc = terrain.getIdBloc(positX, positY - flammeBas -1)) == 0)
                {

                    flammeBas++;
                    if ((bombe = terrain.detectBombe(positX, positY - flammeBas)) != null)
                        bombe.exploser();
                }
                else if (idBloc == 2)
                {
                    flammeBas++;
                    if (!traverseBloc)
                        stopAvance++;
                    terrain.detruireBloc(positX, positY - flammeBas);
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
                {
                    flammeGauche++;
                    if ((bombe = terrain.detectBombe(positX - flammeGauche, positY)) != null)
                        bombe.exploser();
                }
                else if (idBloc == 2)
                {
                    flammeGauche++;
                    if (!traverseBloc)
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
                {
                    flammeDroite++;
                    if ((bombe = terrain.detectBombe(positX + flammeDroite, positY)) != null)
                    {
                        bombe.exploser();
                        System.out.print("KSLKJFKSDFLKSJDLF");
                    }

                }
                else if (idBloc == 2)
                {
                    flammeDroite++;
                    if (!traverseBloc)
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


    public void afficher(Graphics g)
    {
        int tileSize = terrain.getTileSize();
        int tileBorder = terrain.getTileBorder();
        if (etat == 1) {
            g.setColor(Color.black);
            g.fillOval(positX * (tileSize + tileBorder) + tileSize / 2 - bombeSize / 2, positY * (tileSize + tileBorder) + tileSize / 2 - bombeSize / 2, bombeSize, bombeSize);

        }
        else if (etat == 2)
        {
            g.setColor(Color.red);
            for (int a=positX-flammeGauche ; a<=positX+flammeDroite ; a++)
            {
                g.fillRect(a*(tileSize+tileBorder), positY*(tileSize+tileBorder) + (tileSize+tileBorder)/2 - 4/2, tileSize, 4);
            }

            for (int a=positY-flammeBas ; a<=positY+flammeHaut ; a++)
            {
                g.fillRect(positX*(tileSize+tileBorder) + (tileSize+tileBorder)/2 - 4/2, a*(tileSize+tileBorder), 4, tileSize);
            }
        }
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

import org.lwjgl.Sys;
import org.newdawn.slick.*;



public class Bombe {
    private int positX, positY, tempsAvantExplosion, etat, flammePortee, flammeHaut, flammeBas, flammeGauche, flammeDroite, bombeSize;
    private boolean traverseBloc;
    private Chronometre chronometre;
    private Personnage auteur, deuxiemePerso;
    private Terrain terrain;
    private Affichage affichage;

    private boolean kick;
    private int direction, offsetX, offsetY;

    Animation animation;

    Bombe(Affichage affichage, Personnage perso, Personnage deuxiemePerso, Terrain terrain) {
        auteur = perso;
        this.deuxiemePerso = deuxiemePerso;
        this.terrain = terrain;
        this.affichage = affichage;
        positX = auteur.getPositX();
        positY = auteur.getPositY();

        flammePortee = auteur.getBombe_portee();
        flammeHaut = 0;
        flammeBas = 0;
        flammeDroite = 0;
        flammeGauche = 0;

        tempsAvantExplosion = auteur.getBombe_tempsAvantExplosion();

        traverseBloc = auteur.getBombeRouge();

        chronometre = new Chronometre();
        chronometre.reDemarrer(tempsAvantExplosion);
        etat = 1;

        bombeSize = 20;

        animation = new Animation(4, 80, true);

        kick = false;
        offsetX=0; offsetY = 0;
        direction = 0;

    }

    public void kick(Personnage joueur)
    {
        kick = true;
        direction = joueur.getDirection();
    }

    public int getPositX() {
        return positX;
    }

    public int getPositY() {
        return positY;
    }

    public int getOffsetX() {return offsetX;}
    public int getOffsetY() {return offsetY;}

    public int getFlammeGauche() {
        return flammeGauche;
    }

    public int getFlammeDroite() {
        return flammeDroite;
    }

    public int getFlammeHaut() {
        return flammeHaut;
    }

    public int getFlammeBas() {
        return flammeBas;
    }

    public int getEtat() {
        return etat;
    }

    public int isBombeRouge() {if (traverseBloc) return 1; else return 0;}

    public Animation getAnimation() {
        return animation;
    }


    public int mettreAJour() {
        //System.out.println(chronometre.getTempsRestant());

        animation.update();
        if (etat == 2) {
            if (detectJoueur(auteur))
            {
                if (auteur.possedeBouclier())
                {
                    auteur.recoitDegats();
                    auteur.fairePerdreBouclier();
                }
                else
                    auteur.perdUneVie(deuxiemePerso);
            }
            if (detectJoueur(deuxiemePerso))
            {
                if (deuxiemePerso.possedeBouclier())
                {
                    deuxiemePerso.fairePerdreBouclier();
                    deuxiemePerso.recoitDegats();
                }
                else
                    deuxiemePerso.perdUneVie(auteur);
            }
        }

        if (chronometre.checkFinished() == 1 && etat == 1) {
            exploser();
            return 0;

        } else if (etat == 2 && chronometre.checkFinished() == 1)
        {
            etat = 0;
            return 1;
        }
        else
        {
            if (kick)
            {
                if (direction == 1)
                    offsetY-=2;
                else if (direction == 2)
                    offsetX+=2;
                else if (direction == 3)
                    offsetY+=2;
                else if (direction == 4)
                    offsetX-=2;


                if (offsetX>(affichage.getTileSize() + affichage.getTileBorder())/2)
                {
                    offsetX = -(affichage.getTileSize() + affichage.getTileBorder())/2;
                    positX++;
                }

                if (offsetY>(affichage.getTileSize() + affichage.getTileBorder())/2)
                {
                    offsetY = -(affichage.getTileSize() + affichage.getTileBorder())/2;
                    positY++;
                }

                if (offsetY<-(affichage.getTileSize() + affichage.getTileBorder())/2)
                {
                    offsetY = (affichage.getTileSize() + affichage.getTileBorder())/2;
                    positY--;
                }

                if (offsetX<-(affichage.getTileSize() + affichage.getTileBorder())/2)
                {
                    offsetX = (affichage.getTileSize() + affichage.getTileBorder())/2;
                    positX--;
                }

                if ((Math.abs(offsetX) < 2 && Math.abs(offsetY) < 2) || true)
                {
                    if (direction == 1)
                    {
                        if (terrain.detectBombe(positX, positY-1) != null || terrain.getIdBloc(positX, positY-1) != 0)
                        {
                            offsetY = 0;
                            kick = false;
                        }
                    }
                    else if (direction == 2)
                    {
                        if (terrain.detectBombe(positX+1, positY) != null || terrain.getIdBloc(positX+1, positY) != 0)
                        {
                            offsetX = 0;
                            kick = false;
                        }
                    }
                    else if (direction == 3)
                    {
                        if (terrain.detectBombe(positX, positY+1) != null || terrain.getIdBloc(positX, positY+1) != 0)
                        {
                            offsetY = 0;
                            kick = false;
                        }
                    }
                    else if (direction == 4)
                    {
                        if (terrain.detectBombe(positX-1, positY) != null || terrain.getIdBloc(positX-1, positY) != 0)
                        {
                            offsetX = 0;
                            kick = false;
                        }
                    }
                }


            }
            return 0;
        }

    }

    public void exploser() {

        if (etat == 1) {
            Son.playsonbombe();
            Son.playsonfeu();
            chronometre.reDemarrer(1000);
            etat = 2;
            kick =false;
        }
        animation.reset();
        int stopAvance = 0;
        int idBloc;
        Bombe bombe;
        while (stopAvance == 0) // Haut
        {
            if (flammeBas < flammePortee) {
                if ((idBloc = terrain.getIdBloc(positX, positY + flammeBas + 1)) == 0) {

                    flammeBas++;
                    if ((bombe = terrain.detectBombe(positX, positY + flammeBas)) != null)
                        bombe.exploser();
                } else if (idBloc == 2) {
                    flammeBas++;
                    if (!traverseBloc)
                        stopAvance++;
                    terrain.detruireBloc(positX, positY + flammeBas);
                } else if (idBloc == 1) {
                    stopAvance++;
                }
            } else
                stopAvance++;
        }

        stopAvance = 0;
        while (stopAvance == 0) // Bas
        {
            if (flammeHaut < flammePortee) {
                if ((idBloc = terrain.getIdBloc(positX, positY - flammeHaut - 1)) == 0) {

                    flammeHaut++;
                    if ((bombe = terrain.detectBombe(positX, positY - flammeHaut)) != null)
                        bombe.exploser();
                } else if (idBloc == 2) {
                    flammeHaut++;
                    if (!traverseBloc)
                        stopAvance++;
                    terrain.detruireBloc(positX, positY - flammeHaut);
                } else if (idBloc == 1)
                    stopAvance++;
            } else
                stopAvance++;
        }

        stopAvance = 0;
        while (stopAvance == 0) // Gauche
        {
            if (flammeGauche < flammePortee) {
                if ((idBloc = terrain.getIdBloc(positX - flammeGauche - 1, positY)) == 0) {
                    flammeGauche++;
                    if ((bombe = terrain.detectBombe(positX - flammeGauche, positY)) != null)
                        bombe.exploser();
                } else if (idBloc == 2) {
                    flammeGauche++;
                    if (!traverseBloc)
                        stopAvance++;
                    terrain.detruireBloc(positX - flammeGauche, positY);
                } else if (idBloc == 1)
                    stopAvance++;
            } else
                stopAvance++;
        }

        stopAvance = 0;
        while (stopAvance == 0) // Droite
        {
            if (flammeDroite < flammePortee) {
                if ((idBloc = terrain.getIdBloc(positX + flammeDroite + 1, positY)) == 0) {
                    flammeDroite++;
                    if ((bombe = terrain.detectBombe(positX + flammeDroite, positY)) != null) {
                        bombe.exploser();
                        System.out.print("KSLKJFKSDFLKSJDLF");
                    }

                } else if (idBloc == 2) {
                    flammeDroite++;
                    if (!traverseBloc)
                        stopAvance++;
                    terrain.detruireBloc(positX + flammeDroite, positY);
                } else if (idBloc == 1)
                    stopAvance++;
            } else
                stopAvance++;
        }


    }

    public Personnage getAuteur() {
        return auteur;
    }


    public void afficher(Graphics g) {

        affichage.bombe(g, this, animation, terrain);

    }

    public boolean detectJoueur(Personnage joueur) {
        int detectJoueur;
        if (((positX + flammeDroite) >= joueur.getPositX() && (positX - flammeGauche) <= joueur.getPositX() && positY == joueur.getPositY()) || ((positY + flammeBas) >= joueur.getPositY() && (positY - flammeHaut) <= joueur.getPositY() && positX == joueur.getPositX())) {
            return true;
        } else {
            return false;
        }

    }
}


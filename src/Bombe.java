import java.awt.*;

public class Bombe {
    private int positX, positY, tempsAvantExplosion, idJoueur;

    Bombe (Personnage perso)
    {
        positX = perso.getPositX();
        positY = perso.getPositY();
        idJoueur = perso.getIdPerso();

        tempsAvantExplosion = 0;
    }

    public int getPositX() { return positX; }
    public int getPositY() { return positY; }
    //int getPositt { return positY; }
/*
    void poser(Personnage perso)
    {
        positX = perso.getPositX();
        positY = perso.getPositY();
        idJoueur = perso.getIdPerso();
    }*/
}

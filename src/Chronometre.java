
public class Chronometre {

    private long tempsInitial, tempsDeFin, etat;

    Chronometre ()
    {
        tempsInitial = 0;
        tempsDeFin = 0;

        etat = 0;
    }

    public void reDemarrer ()
    {
        tempsInitial = java.lang.System.currentTimeMillis() ;
        tempsDeFin = 0;
    }

    public void reDemarrer (long tempsDeFin)
    {
        tempsInitial = java.lang.System.currentTimeMillis() ;
        this.tempsDeFin = tempsInitial + tempsDeFin;
    }

    public long getTempsEnCours ()
    {
        return java.lang.System.currentTimeMillis() - tempsInitial;
    }

    public long checkFinished ()
    {
        if (getTempsRestant()<0)
            return 1;
        else
            return 0;
    }

    public long getTempsRestant ()
    {
        return tempsDeFin -  java.lang.System.currentTimeMillis();
    }


}

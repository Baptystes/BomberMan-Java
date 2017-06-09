import javax.swing.*;


public class Option
{
    private int  vitesse, nombreBombeMax, nombreBombePosee, bombe_portee, bombe_tempsAvantExplosion, nbVies;;

    public Option() {

        this.vitesse = 3;
        this.nombreBombeMax = 3;
        this.nombreBombePosee = 0;
        this.bombe_portee = 3;
        this.bombe_tempsAvantExplosion = 4000;
        nbVies = 3;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getNombreBombeMax() {
        return nombreBombeMax;
    }

    public void setNombreBombeMax(int nombreBombeMax) {
        this.nombreBombeMax = nombreBombeMax;
    }

    public int getNombreBombePosee() {
        return nombreBombePosee;
    }

    public void setNombreBombePosee(int nombreBombePosee) {
        this.nombreBombePosee = nombreBombePosee;
    }

    public int getBombe_portee() {
        return bombe_portee;
    }

    public void setBombe_portee(int bombe_portee) {
        this.bombe_portee = bombe_portee;
    }

    public int getBombe_tempsAvantExplosion() {
        return bombe_tempsAvantExplosion;
    }

    public void setBombe_tempsAvantExplosion(int bombe_tempsAvantExplosion) {
        this.bombe_tempsAvantExplosion = bombe_tempsAvantExplosion;
    }




    public int getNbVies() {
        return nbVies;
    }

    public void setNbVies(int nbVies) {
        this.nbVies = nbVies;
    }

   public void saisieVie()
    {
        String [] nbVies = {"1", "2", "3", "4" , "5"};
        JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
        int vies = (int)jop.showInputDialog(null,
                "Veuillez choisir le nombre de vies de chaque joueur !",
                "Nombre de vies!",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nbVies ,
                nbVies[2]);
        jop2.showMessageDialog(null, "Le nombre de vies de chaque joueur est  " + vies, " ", JOptionPane.INFORMATION_MESSAGE);
       // setNbVies(vies);
        //System.out.println(getNbVies());
    }

}

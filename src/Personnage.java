import net.java.games.input.Component;
import org.newdawn.slick.*;

import javax.swing.plaf.synth.SynthEditorPaneUI;

public class Personnage {


    private int positX, positY, offsetX, offsetY, idPerso, direction, directionEnCours, vitesse, nombreBombeMax, nombreBombePosee, bombe_portee, bombe_tempsAvantExplosion, bombe_traverseBloc, nbVies;
    private boolean possedeBouclier, passeMurail, possedeKick;
    private Terrain terrain;
    private Affichage affichage;
    //Chronometre tempsInvincible;
    Animation animation;
    Animation animationBouclier, animationPasseMurail;
    private int toucheHaut, toucheBas, toucheGauche, toucheDroite, toucheBombe;

    private boolean recoitDegats;

    private boolean doitPerdreBlouclier;

    Personnage (Affichage affichage, Terrain terrain, int idPerso)
    {
        this.terrain = terrain;
        this.affichage = affichage;
        this.idPerso = idPerso;

        animation = new Animation(3, 50, true);

        animationBouclier = new Animation(10, 50, true);
        animationPasseMurail = new Animation(10, 10, true);
        if (idPerso == 1)
        {
            toucheHaut = Input.KEY_Z;
            toucheBas = Input.KEY_S;
            toucheGauche = Input.KEY_Q;
            toucheDroite = Input.KEY_D;
            toucheBombe = Input.KEY_F;
        }
        else if (idPerso == 2)
        {
            toucheHaut = Input.KEY_UP;
            toucheBas = Input.KEY_DOWN;
            toucheGauche = Input.KEY_LEFT;
            toucheDroite = Input.KEY_RIGHT;
            toucheBombe = Input.KEY_RCONTROL;
        }

        offsetX = 0;
        offsetY = 0;

        directionEnCours = 3;

       // tempsInvincible = new Chronometre();
    }

    public int getPositX () { return positX; }
    public int getPositY () { return positY; }

    public int getOffsetX () {return offsetX;}
    public int getOffsetY () {return offsetY;}

    public int getIdJoueur () {return idPerso;}


    public int getNbVies() {
        return nbVies;
    }

    public int getVitesse() {return vitesse;}

    public void setNbVies(int nbVies) {
        this.nbVies = nbVies;
    }
    public void gagneUneVie() {nbVies++;}

    public int getBombe_tempsAvantExplosion () {return bombe_tempsAvantExplosion;}
    public int getDirection () {return direction;}
    public int getDirectionEnCours() {return directionEnCours;}
    public int getNombreBombeMax() {return nombreBombeMax;}

    public Animation getAnimation() { return animation; }

    public Animation getAnimationBouclier() {return animationBouclier;}

    public Animation getAnimationPasseMurail() {return animationPasseMurail;}


    public void deplacer (GameContainer gc)
    {
        if (gc.getInput().isKeyDown(toucheHaut))
        {
            direction = 1;
            directionEnCours = 1;
        }
        else if (gc.getInput().isKeyDown(toucheBas))
        {
            direction = 3;
            directionEnCours = 3;
        }
        else if (gc.getInput().isKeyDown(toucheGauche))
        {
            direction = 4;
            directionEnCours = 4;
        }
        else if (gc.getInput().isKeyDown(toucheDroite))
        {
            direction = 2;
            directionEnCours = 2;
        }
        else
            direction = 0;
        if (direction>0)
           animation.update();
        else
           animation.reset();


        //System.out.println(offsetY);
        if (direction == 1 && (terrain.peutTraverser(this, positX, positY-1) || offsetY>0))
        {
            if (Math.abs(offsetX) < vitesse)
            {
                offsetY-=vitesse;
                offsetX = 0;
            }
            else
            {
              if (offsetX>0)
                       offsetX-=vitesse;
               else if (offsetX<0)
                   offsetX+=vitesse;
            }

        }
        else if (direction == 2 && (terrain.peutTraverser(this, positX+1, positY) || offsetX<0))
        {
            if (Math.abs(offsetY) < vitesse)
            {
                offsetX+=vitesse;
                offsetY = 0;
            }
            else
            {
                if (offsetY>0)
                    offsetY-=vitesse;
                else if (offsetY<0)
                    offsetY+=vitesse;
            }
        }
        else if (direction == 3 && (terrain.peutTraverser(this, positX, positY+1)|| offsetY<0))
        {
            if (Math.abs(offsetX) < vitesse)
            {
                offsetY+=vitesse;
                offsetX = 0;
            }
            else
            {
                if (offsetX>0)
                    offsetX-=vitesse;
                else if (offsetX<0)
                    offsetX+=vitesse;
            }
        }
        else if (direction == 4 && (terrain.peutTraverser(this, positX-1, positY)|| offsetX>0))
        {
            if (Math.abs(offsetY) < vitesse)
            {
                offsetX-=vitesse;
                offsetY = 0;
            }
            else
            {
                if (offsetY>0)
                    offsetY-=vitesse;
                else if (offsetY<0)
                    offsetY+=vitesse;
            }
        }

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


        Bonus bonus;
        if ((bonus = terrain.detectBonus(positX, positY)) != null)
        {
            bonus.prendEffet(this);
        }



    }

    public boolean possedeKick()
    {
        return possedeKick;
    }
    public void donnerKick()
    {
        possedeKick = true;
    }

    public int veutPoserBombe(GameContainer gc)
    {
        if (gc.getInput().isKeyPressed(toucheBombe))
            return 1;
        else
            return 0;
    }

    public void afficher (Graphics g)
    {
        affichage.joueur (g, this);
       /*if (estInvincible())
            g.drawOval(positX * (affichage.getTileSize() + affichage.getTileBorder()) + offsetX + affichage.getTileSize()/2 - (persoSize + 6)/2, positY * (affichage.getTileSize() + affichage.getTileBorder()) + offsetY + affichage.getTileSize()/2 - (persoSize + 6)/2, (persoSize + 6), (persoSize + 6));
    */
    }

    public int peutPoserBombe ()
    {
        if (nombreBombePosee < nombreBombeMax)
            return 1;
        else
            return 0;
    }

    public void setPasseMurail(boolean passeMurail)
    {
        this.passeMurail = passeMurail;
    }

    public void donnerBouclier()
    {
        possedeBouclier = true;
        doitPerdreBlouclier = false;
        System.out.println("perso: "+idPerso + " donne bouclier");
    }
    public void retirerBouclier()
    {
        possedeBouclier = false;
        doitPerdreBlouclier = false;
        System.out.println("perso: "+idPerso + " virer bouclier");
    }
    public boolean doitPerdreBouclier()
    {
        //System.out.println(idPerso + " doit perdre bouclier");
        return doitPerdreBlouclier;
    }
    public boolean possedeBouclier ()
    {
        return possedeBouclier;
    }

    public void fairePerdreBouclier ()
    {
        System.out.println("perso: "+idPerso + " faire perdre bouclier");
        doitPerdreBlouclier = true;
    }



    public void poseUneBombe ()
    {
        nombreBombePosee ++;
    }

    public void supprimeUneBombe ()
    {
        nombreBombePosee --;
    }

    public int getBombe_portee ()
    {
        return bombe_portee;
    }

    public void ajouterTailleFlamme(int offset)
    {
        bombe_portee += offset;
        if (bombe_portee<1)
            bombe_portee = 1;
        else if (bombe_portee>10)
            bombe_portee = 10;
    }

    public void setTailleFlamme (int taille)
    {
        bombe_portee = taille;
    }

    public void setBombeRouge ()
    {
        bombe_traverseBloc = 1;
    }

    public boolean getBombeRouge ()
    {
        return (bombe_traverseBloc == 1);
    }

    public void ajouterNombreBombe (int offset)
    {
        nombreBombeMax+= offset;
        if (nombreBombeMax>7)
            nombreBombeMax = 7;
        else if (nombreBombeMax<2)
            nombreBombeMax = 2;
    }

    public void ajoutTempsDetonation (int offset)
    {
        bombe_tempsAvantExplosion += offset;
        if (bombe_tempsAvantExplosion < 3000)
            bombe_tempsAvantExplosion = 3000;
    }



    public void perdUneVie (Personnage autreJoueur)
    {
        setNbVies(getNbVies() -1);
        if (Math.sqrt( Math.pow(1 - autreJoueur.getPositX(), 2) + Math.sqrt(Math.pow(15 - autreJoueur.getPositY(), 2))) > Math.sqrt( Math.pow(19 - autreJoueur.getPositX(), 2) + Math.sqrt(Math.pow(1 - autreJoueur.getPositY(), 2))))
        {
            respawn(1, 15);
        }
        else
        {
            respawn(19,1);

        }
        if (nbVies>0)
            donnerBouclier();
        else
            direction = 0;
        //recoitDegats();

        /*if (estInvincible() == false)
        {

            System.out.println (" Le joueur " + idPerso+ " a perdu une vie. Son nombre de vie est " +getNbVies());
        }*/

        //tempsInvincible.reDemarrer(3000);
    }

    public void modifierVitesse(double offset)
    {
        vitesse += offset;
        if (vitesse>5)
            vitesse = 5;
        else if (vitesse<1)
            vitesse = 1;
    }


    public boolean possedePasseMurail()
    {
        return passeMurail;
    }

    /*public boolean estInvincible ()
    {
        return possedeBouclierResurection();
    }*/

    public boolean estMort () { if (nbVies<0) nbVies = 0; return (nbVies<=0);}
/*
    public boolean possedeBouclierResurection()
    {
        return tempsInvincible.checkFinished() == 0;
    }*/

    public void reset(int positX, int positY)
    {
        Option option = new Option();
        nbVies = option.getNbVies();
        respawn(positX, positY);

        nombreBombePosee = 0;
    }

    public boolean getRecoitDegats()
    {
        return recoitDegats;
    }

    public void recoitDegats()
    {
        recoitDegats = true;
    }

    public void resetRecoitDegat()
    {
        recoitDegats = false;
    }

    public void respawn(int positX, int positY)
    {
        if (nbVies>0)
        {
            this.positX = positX;
            this.positY = positY;
            offsetX = 0;
            offsetY = 0;
            resetBonus();
            retirerBouclier();
        }
    }



    public void resetBonus()
    {

        Option option = new Option();
        vitesse = option.getVitesse();

        nombreBombeMax = option.getNombreBombeMax();
        bombe_portee = option.getBombe_portee();
        bombe_tempsAvantExplosion = option.getBombe_tempsAvantExplosion();
        bombe_traverseBloc = 0;
        possedeBouclier = false;
        doitPerdreBlouclier = false;
        passeMurail = false;
        possedeKick = false;
    }
}

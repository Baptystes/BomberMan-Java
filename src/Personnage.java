import net.java.games.input.Component;
import org.newdawn.slick.*;

public class Personnage {


    private int positX, positY, offsetX, offsetY, idPerso, direction, vitesse, nombreBombeMax, nombreBombePosee, bombe_portee, bombe_tempsAvantExplosion, bombe_traverseBloc, nbVies, directionEnCours;

    private Terrain terrain;
    private Affichage affichage;
    Chronometre tempsInvincible;
    Animation animation;
    private int toucheHaut, toucheBas, toucheGauche, toucheDroite, toucheBombe;

    Personnage (Affichage affichage, Terrain terrain, int idPerso)
    {
        this.terrain = terrain;
        this.affichage = affichage;
        this.idPerso = idPerso;

        animation = new Animation(3, 100);

        nbVies = 3;

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
            toucheBombe = Input.KEY_NUMPAD0;
        }

        offsetX = 0;
        offsetY = 0;
        vitesse = 3;

        nombreBombeMax = 3;
        nombreBombePosee = 0;
        bombe_portee = 3;
        bombe_tempsAvantExplosion = 4000;

        tempsInvincible = new Chronometre();
    }

    public int getPositX () { return positX; }
    public int getPositY () { return positY; }
    public int getOffsetX () {return offsetX;}
    public int getOffsetY () {return offsetY;}

    public int getIdJoueur () {return idPerso;}
    public int getBombe_tempsAvantExplosion () {return bombe_tempsAvantExplosion;}
    public int getDirection () {return direction;}
    public int getDirectionEnCours() {return directionEnCours;}

    public Animation getAnimation() { return animation; }


    public void spawn (int positX, int positY)
    {
        this.positX = positX;
        this.positY = positY;
    }

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
        if (direction == 1 && (terrain.peutTraverser(positX, positY-1) || offsetY>0))
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
        else if (direction == 2 && (terrain.peutTraverser(positX+1, positY) || offsetX<0))
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
        else if (direction == 3 && (terrain.peutTraverser(positX, positY+1)|| offsetY<0))
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
        else if (direction == 4 && (terrain.peutTraverser(positX-1, positY)|| offsetX>0))
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
        if (offset>0 && bombe_portee<10)
            bombe_portee += offset;
        else if (offset<0 && bombe_portee>0)
            bombe_portee -= offset;
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
        nombreBombeMax+=offset;
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

    public void modifierVie (int offset)
    {

    }

    public void perdUneVie (Personnage autreJoueur)
    {
        offsetX = 0 ;
        offsetY = 0;
        if (Math.sqrt( Math.pow(1 - autreJoueur.getPositX(), 2) + Math.sqrt(Math.pow(15 - autreJoueur.getPositY(), 2))) > Math.sqrt( Math.pow(19 - autreJoueur.getPositX(), 2) + Math.sqrt(Math.pow(1 - autreJoueur.getPositY(), 2))))
        {
            positX = 1 ; positY = 15;
        }
        else
        {
            positX = 19 ; positY = 1;
        }

        tempsInvincible.reDemarrer(3000);
    }

    public boolean estInvincible ()
    {
        return possedeBouclierResurection();
    }

    public boolean possedeBouclierResurection()
    {
        return tempsInvincible.checkFinished() == 0;
    }
}

import net.java.games.input.Component;
import org.newdawn.slick.*;

public class Personnage {


    private int positX, positY, offsetX, offsetY, idPerso, direction, vitesse;

    private int persoSize = 20;
    private Color couleur;
    private Terrain terrain;
    private int toucheHaut, toucheBas, toucheGauche, toucheDroite;

    Personnage (Terrain terrain, int idPerso, Color couleur)
    {
        this.terrain = terrain;
        this.couleur =  couleur;

        this.idPerso = idPerso;
        if (idPerso == 1)
        {
            toucheHaut = Input.KEY_Z;
            toucheBas = Input.KEY_S;
            toucheGauche = Input.KEY_Q;
            toucheDroite = Input.KEY_D;
        }
        else if (idPerso == 2)
        {
            toucheHaut = Input.KEY_UP;
            toucheBas = Input.KEY_DOWN;
            toucheGauche = Input.KEY_LEFT;
            toucheDroite = Input.KEY_RIGHT;
        }

        offsetX = 0;
        offsetY = 0;
        vitesse = 3;
    }

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
        }
        if (gc.getInput().isKeyDown(toucheBas))
        {
            direction = 3;
        }
        if (gc.getInput().isKeyDown(toucheGauche))
        {
            direction = 4;
        }
        if (gc.getInput().isKeyDown(toucheDroite))
        {
            direction = 2;
        }


        System.out.println(offsetY);
        if (direction == 1 && (terrain.getIdTile(positX,positY-1) == 0 || offsetY>0))
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
        else if (direction == 2 && (terrain.getIdTile(positX+1,positY) == 0 || offsetX<0))
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
        else if (direction == 3 && (terrain.getIdTile(positX,positY+1) == 0 || offsetY<0))
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
        else if (direction == 4 && (terrain.getIdTile(positX-1,positY) == 0 || offsetX>0))
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

        if (offsetX>(terrain.getTileSize() + terrain.getTileBorder())/2)
        {
            offsetX = -(terrain.getTileSize() + terrain.getTileBorder())/2;
            positX++;
        }

        if (offsetY>(terrain.getTileSize() + terrain.getTileBorder())/2)
        {
            offsetY = -(terrain.getTileSize() + terrain.getTileBorder())/2;
            positY++;
        }

        if (offsetY<-(terrain.getTileSize() + terrain.getTileBorder())/2)
        {
            offsetY = (terrain.getTileSize() + terrain.getTileBorder())/2;
            positY--;
        }

        if (offsetX<-(terrain.getTileSize() + terrain.getTileBorder())/2)
        {
            offsetX = (terrain.getTileSize() + terrain.getTileBorder())/2;
            positX--;
        }

        direction = 0;


    }
    public void afficher (Graphics g)
    {
        g.setColor(couleur);

        g.fillOval(positX * (terrain.getTileSize() + terrain.getTileBorder()) + offsetX + terrain.getTileSize()/2 - persoSize/2, positY * (terrain.getTileSize() + terrain.getTileBorder()) + offsetY + terrain.getTileSize()/2 - persoSize/2, persoSize, persoSize);
    }




}

import org.newdawn.slick.*;
//import java.awt.Font;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Affichage {
    private int tileSize, tileBorder, colonneLatterale, bombeSize, bonusSize, persoSize;



    private Image imageBombe;
    private Image [] imagesBonus;
    private TrueTypeFont font;

    private Image imageJoueurs, imageFlammes, imageBlocs;



    Affichage (int tileSize, int tileBorder, int colonneLatterale)
    {


        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
    //http://ninjacave.com/slickutil3
        this.tileSize = tileSize;
        this.tileBorder = tileBorder;
        this.colonneLatterale = colonneLatterale;




        persoSize = 20;
        bombeSize = 20;
        bonusSize = 20;

        try {
            imageJoueurs = new Image("images/perso.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }


        try {
            imageBombe = new Image("images/bombeGood.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        try {
            imageFlammes = new Image("images/flammesGood.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        try {
            imageBlocs = new Image("images/blocs.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        //imageBombe.setCenterOfRotation(57,57);
        imagesBonus = new Image [6];

        for (int a=0 ; a<6 ; a++)
        {
            try {
                imagesBonus[a] = new Image ("images/bonus/bonus_"+a+".png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }

    }

    public void joueur(Graphics g, Personnage perso)
    {
        int blop;
        if (perso.getDirection()>0)
            blop = 1;
        else
            blop = 0;
        //imageJoueurs.draw(colonneLatterale + perso.getPositX() * (tileSize + tileBorder) + perso.getOffsetX() + tileSize/2 - persoSize/2, perso.getPositY() * (tileSize + tileBorder) + perso.getOffsetY() +tileSize/2 - persoSize/2, persoSize, persoSize, 0,0,17, 30 );
        imageJoueurs.getSubImage(32 * (1-blop)+32 * perso.getAnimation().getImageEnCours()*blop + 32*(perso.getDirectionEnCours()-1) * perso.getAnimation().getNombreImages(),32*(perso.getIdJoueur()-1), 32, 32).draw(colonneLatterale + perso.getPositX() * (tileSize + tileBorder) + perso.getOffsetX(), perso.getPositY() * (tileSize + tileBorder) + perso.getOffsetY() , tileSize, tileSize);


    }


    public int getTileSize() { return tileSize;}
    public int getTileBorder() { return tileBorder;}


    public void blocMap ( Graphics g, int x, int y, int idBloc)
    {

        imageBlocs.getSubImage(idBloc*32, 0, 32,32).draw(colonneLatterale + x*(tileSize + tileBorder),  y*(tileSize + tileBorder), tileSize, tileSize);
        /*if (idBloc == 1)
            g.setColor(Color.gray);
        else if (idBloc == 2)
            g.setColor(Color.orange);
        else
            g.setColor(Color.green);
        g.fillRect(colonneLatterale + indexToPixel(x, tileSize), indexToPixel(y, tileSize), tileSize, tileSize);*/
    }

    public void bombe(Graphics g, Bombe bombe, Animation animation, Terrain terrain)
    {

        if (bombe.getEtat() == 1) {
            //g.setColor(Color.black);
            imageBombe.getSubImage(24*bombe.getAnimation().getImageEnCours(), 0, 23,31).draw(colonneLatterale + indexToPixel(bombe.getPositX(), bombeSize), indexToPixel(bombe.getPositY(), bombeSize)-5);

        }
        else if (bombe.getEtat() == 2)
        {
            g.setColor(Color.red);
            for (int a=bombe.getPositX()-bombe.getFlammeGauche() ; a<=bombe.getPositX()+bombe.getFlammeDroite() ; a++)
            {
                if (a!=bombe.getPositX() && terrain.detectBombe(a, bombe.getPositY()) == null)
                    imageFlammes.getSubImage(72,24*bombe.getAnimation().getImageEnCours(), 24, 24).draw(colonneLatterale + a*(tileBorder+tileSize),bombe.getPositY()*(tileSize+tileBorder), tileSize, tileSize);
            }

            for (int a=bombe.getPositY()-bombe.getFlammeHaut() ; a<=bombe.getPositY()+bombe.getFlammeBas() ; a++)
            {
                if (a!=bombe.getPositY() && terrain.detectBombe(bombe.getPositX(), a) == null)
                    imageFlammes.getSubImage(120,24*bombe.getAnimation().getImageEnCours(), 24, 24).draw(colonneLatterale + bombe.getPositX()*(tileSize+tileBorder),a*(tileBorder+tileSize), tileSize, tileSize);
                else
                    imageFlammes.getSubImage(0,24*bombe.getAnimation().getImageEnCours(), 24, 24).draw(colonneLatterale + bombe.getPositX()*(tileSize+tileBorder),  bombe.getPositY()*(tileSize+tileBorder), tileSize, tileSize);

            }
        }

    }


    public void bonus (Bonus bonus)
    {
        imagesBonus[bonus.getId()].draw(colonneLatterale + indexToPixel(bonus.getPositX(), bonusSize), indexToPixel(bonus.getPositY(), bonusSize), bonusSize, bonusSize);
    }

    private int indexToPixel (int index, int sizeBloc)
    {
        return index * (tileSize + tileBorder) + tileSize / 2 - sizeBloc / 2;
    }

    public void interfaceJeu(Graphics g, Personnage perso1, Personnage perso2)
    {
        font.drawString(0,0,"Test MAGGLE!");
        //joueur();
        //g.setFont();
        //g.drawString("Test", 0, 0);
    }
}

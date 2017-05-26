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
            imageBombe = new Image("images/bombe/bombe.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
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

    public void joueur(Graphics g, Personnage perso, Animation animation)
    {
        if (perso.getIdJoueur() == 1)
        {
            g.setColor(Color.blue);
        }
        else if (perso.getIdJoueur() == 2)
        {
            g.setColor(Color.red);
        }
        g.fillOval(colonneLatterale + perso.getPositX() * (tileSize + tileBorder) + perso.getOffsetX() + tileSize/2 - persoSize/2, perso.getPositY() * (tileSize + tileBorder) + perso.getOffsetY() +tileSize/2 - persoSize/2, persoSize, persoSize);
    }

    public int getTileSize() { return tileSize;}
    public int getTileBorder() { return tileBorder;}


    public void blocMap ( Graphics g, int x, int y, int idBloc)
    {

        if (idBloc == 1)
            g.setColor(Color.gray);
        else if (idBloc == 2)
            g.setColor(Color.orange);
        else
            g.setColor(Color.green);
        g.fillRect(colonneLatterale + indexToPixel(x, tileSize), indexToPixel(y, tileSize), tileSize, tileSize);
    }

    public void bombe(Graphics g, Bombe bombe, Animation animation)
    {

        if (bombe.getEtat() == 1) {
            //g.setColor(Color.black);
            imageBombe.draw(colonneLatterale + indexToPixel(bombe.getPositX(), bombeSize), indexToPixel(bombe.getPositY(), bombeSize), bombeSize, bombeSize);

        }
        else if (bombe.getEtat() == 2)
        {
            g.setColor(Color.red);
            for (int a=bombe.getPositX()-bombe.getFlammeGauche() ; a<=bombe.getPositX()+bombe.getFlammeDroite() ; a++)
            {
                g.fillRect(colonneLatterale + a*(tileSize+tileBorder), bombe.getPositY()*(tileSize+tileBorder) + (tileSize+tileBorder)/2 - 4/2, tileSize, 4);
            }

            for (int a=bombe.getPositY()-bombe.getFlammeHaut() ; a<=bombe.getPositY()+bombe.getFlammeBas() ; a++)
            {
                g.fillRect(colonneLatterale + bombe.getPositX()*(tileSize+tileBorder) + (tileSize+tileBorder)/2 - 4/2, a*(tileSize+tileBorder), 4, tileSize);
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
        font.drawString(0,0,"Bomberman");
        //g.setFont();
        //g.drawString("Test", 0, 0);
    }
}

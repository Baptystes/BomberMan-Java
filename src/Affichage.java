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

    Texte texte;

    private Image imageBombe;
    private Image [] imagesBonus;

    private Image imageBouclier;

    private Image imageJoueurs, imageFlammes, imageBlocs, imageInterface, imageBonus;


    Affichage (int tileSize, int tileBorder, int colonneLatterale)
    {

    texte = new Texte();

    //http://ninjacave.com/slickutil3
        this.tileSize = tileSize;
        this.tileBorder = tileBorder;
        this.colonneLatterale = colonneLatterale;




        bombeSize = 20;


        try {
            imageJoueurs = new Image("images/perso.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }


        try {
            imageBombe = new Image("images/bombe.png");
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

        try {
            imageInterface = new Image("images/interface.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        //imageBombe.setCenterOfRotation(57,57);
        imagesBonus = new Image [8];

        try {
            imageBonus = new Image("images/bonus.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        try {
            imageBouclier = new Image("images/bouclier.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }



        for (int a=0 ; a<imagesBonus.length ; a++)
        {
           imagesBonus[a] = imageBonus.getSubImage(a*24, 0, 24, 24);
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
        if (perso.possedeBouclier())
        {
            perso.getAnimationBouclier().update();
            imageBouclier.getSubImage(64*perso.getAnimationBouclier().getImageEnCours(),0, 64, 64).draw(colonneLatterale + perso.getPositX() * (tileSize + tileBorder) + perso.getOffsetX(), perso.getPositY() * (tileSize + tileBorder) + perso.getOffsetY() , tileSize, tileSize);
        }

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
            imageBombe.getSubImage(24*bombe.getAnimation().getImageEnCours(), bombe.isBombeRouge()*32, 23,31).draw(colonneLatterale + indexToPixelCentred(bombe.getPositX(), bombeSize), indexToPixelCentred(bombe.getPositY(), bombeSize)-5);

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
        imagesBonus[bonus.getId()].draw(colonneLatterale + indexToPixel(bonus.getPositX()), indexToPixel(bonus.getPositY()), tileSize, tileSize);
    }

    private int indexToPixelCentred (int index, int sizeBloc)
    {
        return index * (tileSize + tileBorder) + tileSize / 2 - sizeBloc / 2;
    }

    private int indexToPixel (int index)
    {
        return index * (tileSize + tileBorder);
    }

    public void interfaceJeu(Graphics g, Personnage perso1, Personnage perso2)
    {
        Personnage [] persos = new Personnage[2];
        persos[0] = perso1 ; persos[1] = perso2;
        for (int a=0 ; a<4 ; a++)
        {
            for (int b=0 ; b < 17 ; b++)
            {
                blocMap(g, -a-1, b, 0);
                blocMap(g, a+21, b, 0);
            }
        }

        imageJoueurs.getSubImage(32*8,0, 32, 32).draw(colonneLatterale + indexToPixel(-3), indexToPixel(1) , 2*tileSize, 2*tileSize);
        imageJoueurs.getSubImage(32*6,32, 32, 32).draw(colonneLatterale + indexToPixel(20+2), indexToPixel(1) , 2*tileSize, 2*tileSize);
        for (int a=0 ; a<2 ; a++)
        {
            imageInterface.getSubImage(64,32, 16, 16).draw(colonneLatterale + indexToPixel(-3 + a*25), indexToPixel(4) , 1*tileSize, 1*tileSize);
            texte.afficher(colonneLatterale + indexToPixel(-2 + a*25)+15, indexToPixel(4),Integer.toString(persos[a].getNbVies()));

            imageInterface.getSubImage(0,32, 16, 16).draw(colonneLatterale + indexToPixel(-3 + a*25), indexToPixel(5) , 1*tileSize, 1*tileSize); // nbBomebes
            texte.afficher(colonneLatterale + indexToPixel(-2 + a*25)+15, indexToPixel(5),Integer.toString(persos[a].getNombreBombeMax()));

            imageInterface.getSubImage(32,48, 16, 16).draw(colonneLatterale + indexToPixel(-3 + a*25), indexToPixel(6) , 1*tileSize, 1*tileSize); // nbBomebes
            texte.afficher(colonneLatterale + indexToPixel(-2 + a*25)+15, indexToPixel(6),Integer.toString(persos[a].getBombe_portee()));;

            imageInterface.getSubImage(16,48, 16, 16).draw(colonneLatterale + indexToPixel(-3 + a*25), indexToPixel(7) , 1*tileSize, 1*tileSize); // nbBomebes
            texte.afficher(colonneLatterale + indexToPixel(-2 + a*25)+15, indexToPixel(7),Integer.toString(persos[a].getBombe_tempsAvantExplosion()/1000) + "s");
        }
        //font.drawString(0,0,"Test MAGGLE!");

        //joueur();
        //g.setFont();
        //g.drawString("Test", 0, 0);
    }

    public Image getImageBonus (int a)
    {
        return imagesBonus[a];
    }
}

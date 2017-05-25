import org.newdawn.slick.*;

/**
 * Created by Baptiste on 24/05/2017.
 */
public class Affichage {
    private int tileSize, tileBorder, bombeSize, bonusSize;

    private int persoSize;

    Image imageBombe;
    Image [] imagesBonus;


    Affichage (int tileSize, int tileBorder)
    {
        this.tileSize = tileSize;
        this.tileBorder = tileBorder;

        persoSize = 20;
        bombeSize = 15;
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
        g.fillOval(perso.getPositX() * (tileSize + tileBorder) + perso.getOffsetX() + tileSize/2 - persoSize/2, perso.getPositY() * (tileSize + tileBorder) + perso.getOffsetY() +tileSize/2 - persoSize/2, persoSize, persoSize);
    }

    public int getTileSize() { return tileSize;}
    public int getTileBorder() { return tileBorder;}

    public void bombe(Graphics g, Bombe bombe, Animation animation)
    {

        if (bombe.getEtat() == 1) {
            //g.setColor(Color.black);
            imageBombe.draw(bombe.getPositX() * (tileSize + tileBorder) + tileSize / 2 - bombeSize / 2, bombe.getPositY() * (tileSize + tileBorder) + tileSize / 2 - bombeSize / 2, bombeSize, bombeSize);

        }
        else if (bombe.getEtat() == 2)
        {
            g.setColor(Color.red);
            for (int a=bombe.getPositX()-bombe.getFlammeGauche() ; a<=bombe.getPositX()+bombe.getFlammeDroite() ; a++)
            {
                g.fillRect(a*(tileSize+tileBorder), bombe.getPositY()*(tileSize+tileBorder) + (tileSize+tileBorder)/2 - 4/2, tileSize, 4);
            }

            for (int a=bombe.getPositY()-bombe.getFlammeHaut() ; a<=bombe.getPositY()+bombe.getFlammeBas() ; a++)
            {
                g.fillRect(bombe.getPositX()*(tileSize+tileBorder) + (tileSize+tileBorder)/2 - 4/2, a*(tileSize+tileBorder), 4, tileSize);
            }
        }

    }


    public void bonus (Bonus bonus)
    {
        imagesBonus[bonus.getId()].draw(bonus.getPositX() * (tileSize + tileBorder) + tileSize / 2 - bonusSize / 2, bonus.getPositY() * (tileSize + tileBorder) + tileSize / 2 - bonusSize / 2, bonusSize, bonusSize);
        //mage.draw(positX * (tileSize + tileBorder) + tileSize / 2 - bonusSize / 2, positY * (tileSize + tileBorder) + tileSize / 2 - bonusSize / 2, bonusSize, bonusSize);
    }
}


import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import javax.swing.JOptionPane;


public class Menu

{
    private GameContainer gc;
    private int choixEnCours;
    private Image imageBoutons;
    private Image imageBg;
    private Image imageWon;
    private Affichage affichage;

    Animation animationPlay;
    Image imagePlay;

    Texte texte;

    public Menu (GameContainer gc, Affichage affichage)

    {
        this.affichage = affichage;

        choixEnCours = 0;
        this.gc = gc;

        try {
            imageBoutons = new Image("images/menu/boutons.png");

        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            imageBg = new Image("images/menu/wallpaper.png");

        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            imageWon = new Image("images/menu/winBomberman.jpg");

        } catch (SlickException e) {
            e.printStackTrace();
        }

        texte = new Texte();
        animationPlay = new Animation(46, 30, false);
        try {
            imagePlay = new Image("images/menu/playColonne.png");

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    


    public void setChoix(int choix) {
        this.choixEnCours = choix;
    }


    public int gestion()
    {


        if (gc.getInput().isKeyPressed(Input.KEY_ENTER))
            return 1;
        else if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
            return 2;
        else
            return 0;
        /*int posX = gc.getInput().getMouseX();
        int posY = gc.getInput().getMouseY();

        boolean sourisDansCadre = false;
        for (int a=0 ; a<3 ; a++)
        {
            //System.out.println(posY);
            //imageBoutons.getSubImage(0, a*84, 259, 84).draw(gc.getWidth()/2-259/2, gc.getHeight()/5*a + 100);
            if (posX>gc.getWidth()/2-259/2 && posX < (gc.getWidth()/2-259/2)+259 && posY>(gc.getHeight()/5*a + 100) && posY < (gc.getHeight()/5*a + 100+84))
            {
                choixEnCours = a;
                sourisDansCadre = true;
            }
        }

        if (gc.getInput().isKeyPressed(Input.KEY_DOWN))
        {
            choixEnCours = (choixEnCours+1)%3;
        }
        else if (gc.getInput().isKeyPressed(Input.KEY_UP))
        {
            choixEnCours--;
            if (choixEnCours<0)
                choixEnCours=2;
        }

        if ((gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && sourisDansCadre) || gc.getInput().isKeyPressed(Input.KEY_ENTER))
            return choixEnCours+1;
        else
            return 0;
*/


    }

    /*
    public void exit()
    {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        if ((posX > 260 && posX < 525) && (posY > 275 && posY < 355))
        {
            if (Mouse.isButtonDown(0))
            {

                System.exit(0);
            }
        }

    }*/
    /*
    public void option()
    {

        int posX = Mouse.getX();
        int posY = Mouse.getY();
        if ((posX > 260 && posX < 525) && (posY > 165 && posY < 250))
        {
            if (Mouse.isButtonDown(0))
            {
                //setEtatDuJeu(2);
            }
        }

    }*/


    public void afficherPrincipal()
    {

        //System.out.println(518*(animationPlay.getImageEnCours()%10) + "  " + 541*(int)(animationPlay.getImageEnCours()/10));
        imageBg.draw(0,0, gc.getWidth(), gc.getHeight());
        animationPlay.update();
        imagePlay.getSubImage(518*(animationPlay.getImageEnCours()%10), 541*(int)(animationPlay.getImageEnCours()/10), 518, 541).draw(gc.getWidth()/2-518/2,gc.getHeight()/2 - 541/2-100);
        /* (int a=0 ; a<3 ; a++)
        {
            imageBoutons.getSubImage(0, a*84, 259, 84).draw(gc.getWidth()/2-259/2, gc.getHeight()/5*a + 100);
        }

        for (int a=0 ; a<2 ; a++)
            imageBoutons.getSubImage(259, 0, 69, 11).draw(gc.getWidth()/2-259/2 - 90 + 360*a, gc.getHeight()/5*choixEnCours +84/2 - 11/2 + 100);*/

    }
/*
    public void afficherOptions()
    {
        imageBg.draw(0,0);
        texte.afficher(gc.getWidth()/2-30, 50, "Options");
        for (int a=0 ; a<7 ; a++)
        {
            affichage.getImageBonus(a).draw(gc.getWidth()/2-150 + a*50, 100, affichage.getTileSize(), affichage.getTileSize());
        }

    }*/
    public void iWon()
    {
        imageWon.draw(0,50);
    }


}



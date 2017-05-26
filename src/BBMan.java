import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;

import java.util.Date;



public class BBMan extends BasicGame {
    private GameContainer gc;
    private Terrain terrain;
    private Personnage perso1, perso2;
    private Interface interfaceBM;
    private int tileSize, tileBorder;
    private Menu menu;


    public BBMan(int tileSize, int tileBorder) {
        super("BomberBat!");
        this.tileSize = tileSize;
        this.tileBorder = tileBorder;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        this.gc = gc;
        interfaceBM = new Interface();
        terrain = new Terrain(interfaceBM, tileSize, tileBorder);

        perso1 = new Personnage(interfaceBM, terrain, 1, Color.red);
        perso1.spawn(1, 15);
        perso2 = new Personnage(interfaceBM, terrain, 2, Color.blue);
        perso2.spawn(19, 1);
        menu = new Menu();



    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        // Menu
        if (menu.getEtatDuJeu()== 0)
        {
            menu.afficherImage(g);
        }
        // partie en cours
        else if (menu.getEtatDuJeu()== 1)
        {
            terrain.dessinerMap(g);
            terrain.afficherBombes(g);
            terrain.afficherBonus(g);
            perso1.afficher(g);
            perso2.afficher(g);
        }
        else if ( menu.getEtatDuJeu()==2)
        {

        }


    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
      menu.play();
      menu.exit();
      menu.option();
        if (gc.getInput().isKeyDown(Input.KEY_ESCAPE))
        {
            menu.setEtatDuJeu(0);
        }


        if(menu.getEtatDuJeu()==1)

        {
            perso1.deplacer(gc);
            perso2.deplacer(gc);

            //Date date = new Date(); date.get
            //System.out.println((date.getTimestamp());
            if (perso1.veutPoserBombe(gc) == 1) {
                terrain.poserBombe(perso1, perso2);
            }
            if (perso2.veutPoserBombe(gc) == 1) {
                terrain.poserBombe(perso2, perso1);
            }
            terrain.gestionBombes();
            terrain.gestionBonus();

            /*if (terrain.partieTerminee() == 1) {
                etatDuJeu = 0;*/
            }
        }
    }




import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class Son {
    public static void playSoundmain() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Musique/test.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static void playsonbombe() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Musique/explosion_bome.mp3").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }
    }

    public static void playsonbonus(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Musique/son_bonus.mp3").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }
    }
}

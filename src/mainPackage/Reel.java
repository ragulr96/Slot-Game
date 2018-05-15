package mainPackage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static mainPackage.Controller.generateRandomValue;

public class Reel extends Thread {

    private ImageView imageSymbol;
    private Symbol reelObject;

    /**
     * constructor
     *
     * @param imageSymbol || image to be stored
     */
    public Reel(ImageView imageSymbol) {
        this.imageSymbol = imageSymbol;
    }

    /**
     * getter method to get the reel object value
     */
    public Symbol getReelObject() {
        return this.reelObject;
    }

    // setting image path for images
    private static Image imageIcon1 = new Image("/Images/cherry.png");
    private static Image imageIcon2 = new Image("/Images/lemon.png");
    private static Image imageIcon3 = new Image("/Images/plum.png");
    private static Image imageIcon4 = new Image("/Images/watermelon.png");
    private static Image imageIcon5 = new Image("/Images/bell.png");
    private static Image imageIcon6 = new Image("/Images/redseven.png");

    // storing the image icons inside Symbol
    private static Symbol ImageSymbol1 = new Symbol(2, imageIcon1);
    private static Symbol ImageSymbol2 = new Symbol(3, imageIcon2);
    private static Symbol ImageSymbol3 = new Symbol(4, imageIcon3);
    private static Symbol ImageSymbol4 = new Symbol(5, imageIcon4);
    private static Symbol ImageSymbol5 = new Symbol(6, imageIcon5);
    private static Symbol ImageSymbol6 = new Symbol(7, imageIcon6);

    // setting up the array
    static List<Symbol> imageReel = Arrays.asList(ImageSymbol1, ImageSymbol2, ImageSymbol3, ImageSymbol4, ImageSymbol5, ImageSymbol6);

    /**
     * method to shuffle imageReel array
     */
    public static List<Symbol> spin() {
        Collections.shuffle(imageReel);
        return imageReel;
    }

    @Override
    public void run() {
        runreel();
    }

    /**
     * method to run the reel
     */
    public void runreel() {

        while (true) {

            // calling the value from generateRandomValue method
            Symbol temp = Reel.imageReel.get(generateRandomValue(6));

            // thread id and value
            System.out.println("Thread Id : " + Thread.currentThread().getId() + " || Value : " + temp.getValue());

            // setting the image
            imageSymbol.setImage(temp.getImage());
            reelObject = temp;
            try {
                Thread.sleep(60);
            } catch (InterruptedException event) {
                event.printStackTrace();
            }

        }
    }
}




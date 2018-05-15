package mainPackage;

import javafx.scene.image.Image;

public interface ISymbol {

    /**
     * getter and setter methods to get the image symbol path and values
     */
    void setImage(Image image);

    Image getImage();

    void setValue(int value);

    int getValue();
}

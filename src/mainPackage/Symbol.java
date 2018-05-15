package mainPackage;

import javafx.scene.image.Image;

public class Symbol implements ISymbol {

    // value of image symbol
    private int value = 0;

    // path for image symbol
    private Image image;

    /**
     * constructor to initialize image path and image value
     *
     * @param value || value of image symbol
     * @param image || image symbol path
     */
    protected Symbol(int value, Image image) {
        setImage(image);
        setValue(value);
    }

    /**
     * getter and setter methods for image path and image value
     */

    @Override
    public void setImage(Image image) {
        this.image = image;

    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public boolean equals(Symbol symbol) {
        return (this.value == symbol.value);
    }
}

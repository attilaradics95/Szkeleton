import javax.swing.*;

public abstract class ElementView {
    //egyes elemek - mezők, visitorok - megjelenítéséért felelős osztályok őse

    //TAGVÁLTOZÓK
    protected String image;
    private int x;
    private int y;

    //FÜGGVÉNYEK

    /**
     * Visszaadja az entitáshoz tartozó képet.
     *
     * @return Az entitáshoz tartozó kép.
     */
    public String getImage(){
        return image;
    }

    /**
     * Beállítja az entitáshoz tartozó képet
     *
     * @param image Az entitáshoz tartozó kép.
     */
    public void setImage(String image){
        this.image = image;
    }

    /**
     * Visszaadja az entitás képét tartalmazó panelt
     *
     * @return Az entitás képét tartalmazó panel
     */
    abstract public JPanel draw( );

    /**
     * Visszaadja az entitáshoz és a tartalmazott visitorhoz tartozó képeket tartalmazó panelt
     *
     * @param visitorView A tartalmazott visitorhoz tartozó megjelenítésért felelős objektum
     * @return Az entitáshoz és a tartalmazott visitorhoz tartozó képeket tartalmazó panel
     */
    abstract public JPanel draw(ElementView visitorView);
}

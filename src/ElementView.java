import javax.swing.*;

public abstract class ElementView {

    //TAGVÁLTOZÓK
    protected String image;
    private int x;
    private int y;

    //FÜGGVÉNYEK
    abstract public JPanel draw( );
    abstract public JPanel draw(ElementView visitorView);
    abstract public String getImage();
}

import javax.swing.*;

public abstract class ElementView {

    //TAGVÁLTOZÓK
    protected String image;
    private int x;
    private int y;

    //FÜGGVÉNYEK
    public String getImage(){
        return image;
    }
    public void setImage(String image){
        this.image = image;
    }
    abstract public JPanel draw( );
    abstract public JPanel draw(ElementView visitorView);
}

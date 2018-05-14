import java.util.HashMap;

public abstract class ATile {

    //attribútumok
    // minden mezőnek van 4 szomszédja, és lehet rajta egy visitor
    protected HashMap<Directions, ATile> neighbors;
    protected Visitor visitor;
    protected ElementView view ;

    /**
     * Konstruktor
     */
    public ATile(){
        neighbors = new HashMap<>();
        visitor = null;
    }

    /** Az adott irányban lévő szomszédját adja vissza
     *
     * @param d Ebben az irányban található szomszédot keressük
     * @return A d irányban található szomszéd
     */
    public ATile getNeighbor(Directions d){
        return neighbors.get(d);
    }

    /** Visszatér a rajta lévő visitorral vagy null értékkel, ha nincs rajta visitor
     *
     * @return Ha van rajta visitor, akkor azzal tér vissza, egyébként null
     */
    public Visitor getVisitor(){
        return visitor;
    }

    /** Beállítja az aktuális mezőre a v Visitor objektumot
     *
     * @param v A visitor, amit be akarunk állítani
     */
    public void setVisitor(Visitor v){
        visitor = v;
    }

    /**
     * Elfogadja a rálépni szándékozó visitort
     *
     * @param v A visitor, amelyik rá akar lépni. Lehet munkás vagy doboz.
     * @param d Az irány, amerre mozog a visitor
     * @param force Az erő, amivel rá akar lépni a visitor
     */
    abstract public void accept(Visitor v, Directions d, int force);

    /**
     * Beállítja az egyik szomszédját
     * @param tile Ezt a szomszédot állítja be
     * @param d Ebben az irányban van a szomszéd
     */
    public void setNeighbor(ATile tile, Directions d){
        neighbors.put(d, tile);
    }

    /** Beállítja egy mező összes szomszédot
     *
     * @param north Északi szomszéd
     * @param east Keleti szomszéd
     * @param south Déli szomszéd
     * @param west Nyugati szomszéd
     */
    public void setNeighbors(ATile north,ATile east, ATile south, ATile west){
            neighbors.put(Directions.NORTH, north);
            neighbors.put(Directions.EAST, east);
            neighbors.put(Directions.SOUTH, south);
            neighbors.put(Directions.WEST, west);
    }

    /**
     * Visszaadja az osztályhoz tartozó grafikus megjelenésért felelős objektumot
     *
     * @return Az osztályhoz tartozó grafikus objektum
     */
    public ElementView getView() {
        return view;
    }
}

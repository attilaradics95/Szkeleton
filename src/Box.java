public class Box extends Visitor {

    //Attribútumok
    private Game game;
    private boolean movable;
    //id meghatározására szolgáló számlálók
    private static int instanceCounter = 0;

    //Függvények

    /**
     * Konstruktor
     *
     */
    public Box() {
        game = Game.getInstance();
        movable = true;
        instanceCounter++;
        id = instanceCounter;
    }

    /**
     * Konstruktor
     *
     * @param id A doboz azonosítója
     */
    public Box(int id) {
        this.view = new BoxView();
        game = Game.getInstance();
        movable = true;
        instanceCounter++;
        this.id = id;
    }

    //Obstacle kivételével - mivel ide úgyse tud menni -  minden pushTo-nál megkérdezzük, hogy mozgatható-e a doboz
    //Ha nem, akkor nem mozdul el. Egyébként a mezőtől függ, mi történik.
    //Ha van visitor a mezőn, amire lépne, akkor meghívja az azután következő mező accept függvényét a szomszédos visitor.
    //Ha átkerül a következő mezőre beállítja magát visitorként. Annak a mezőnek, ahonnan ellépett nullra állítja a visitor tagváltozóját.


    /**
     * A ládák mozgatását végző függvény. Megkérdezzük, hogy mozgatható-e a doboz. Ha nem, akkor nem mozdul el.
     * Ha igen, akkor egyel arrébb kerül a láda.
     * Ha van visitor a mezőn, amire lépne, akkor meghívja az azután következő mező accept függvényét a szomszédos visitor.
     * Ha átkerül a következő mezőre beállítja magát visitorként. Annak a mezőnek, ahonnan ellépett nullra állítja a visitor tagváltozóját.
     *
     * @param next A mező, amire mozgatni akarjuk a ládát.
     * @param d Az irány, amerre mozgatni akarjuk a ládát.
     * @param force Az erő, amivel mozgatni akarjuk a ládát.
     */
    public void pushTo(Tile next, Directions d, int force) {
        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            Visitor visitorOnNext = next.getVisitor();
            if(visitorOnNext != null){
                ATile next1 = next.getNeighbor(d);
                next1.accept(visitorOnNext, d, reducedforce);
                visitorOnNext = next.getVisitor();
            }
            if(visitorOnNext == null){
                currentTile.setVisitor(null);
                currentTile = next;
                next.setVisitor(this);
            }
        }
    }

    /**
     * A ládák mozgatását végző függvény. Megkérdezzük, hogy mozgatható-e a doboz. Ha nem, akkor nem mozdul el.
     * Ha igen, akkor, amikor átlép meghívja a next switchIt() metódusát önmagát átadva paraméterként.
     * Ha van visitor a mezőn, amire lépne, akkor meghívja az azután következő mező accept függvényét a szomszédos visitor.
     * Ha átkerül a következő mezőre beállítja magát visitorként. Annak a mezőnek, ahonnan ellépett nullra állítja a visitor tagváltozóját.
     *
     * @param next A váltó, amire mozgatni akarjuk a ládát.
     * @param d Az irány, amerre mozgatni akarjuk a ládát.
     * @param force Az erő, amivel mozgatni akarjuk a ládát.
     */
    public void pushTo(Switch next, Directions d, int force) {
        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            Visitor visitorOnNext = next.getVisitor();
            if (visitorOnNext != null) {
                ATile next1 = next.getNeighbor(d);
                next1.accept(visitorOnNext, d, reducedforce);
                visitorOnNext = next.getVisitor();
            }

            if (visitorOnNext == null) {
                currentTile.setVisitor(null);
                currentTile = next;
                next.setVisitor(this);
                next.switchIt(this);
            }

        }
        System.out.println("lefutott a box.pushTo");
    }

    /**
     * A ládák mozgatását végző függvény. Megkérdezzük, hogy mozgatható-e a doboz. Ha nem, akkor nem mozdul el.
     * Ha igen, akkor egyel arrébb kerül a láda, és növeli a láda súrlódását.
     * Ha van visitor a mezőn, amire lépne, akkor meghívja az azután következő mező accept függvényét a szomszédos visitor.
     * Ha átkerül a következő mezőre beállítja magát visitorként. Annak a mezőnek, ahonnan ellépett nullra állítja a visitor tagváltozóját.
     *
     * @param next A mézes mező, amire mozgatni akarjuk a ládát.
     * @param d Az irány, amerre mozgatni akarjuk a ládát.
     * @param force Az erő, amivel mozgatni akarjuk a ládát.
     */
    @Override
    public void pushTo(Honey next, Directions d, int force) {
        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            Visitor visitorOnNext = next.getVisitor();
            if(visitorOnNext != null){
                ATile next1 = next.getNeighbor(d);
                next1.accept(visitorOnNext, d, reducedforce);
                visitorOnNext = next.getVisitor();
            }
            if(visitorOnNext == null){
                currentTile.setVisitor(null);
                currentTile = next;
                next.setVisitor(this);
            }
        }
    }

    /**
     * A ládák mozgatását végző függvény. Megkérdezzük, hogy mozgatható-e a doboz. Ha nem, akkor nem mozdul el.
     * Ha igen, akkor megszűnik a doboz.
     *
     * @param next A lyuk, amire mozgatni akarjuk a ládát.
     * @param d Az irány, amerre mozgatni akarjuk a ládát.
     * @param force Az erő, amivel mozgatni akarjuk a ládát.
     */
    public void pushTo(Hole next, Directions d, int force) {
        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            this.die();
        }
    }

    /**
     * A ládák mozgatását végző függvény. Megkérdezzük, hogy mozgatható-e a doboz. Ha nem, akkor nem mozdul el.
     * Ha igen, akkor egyel arrébb kerül a láda, és csökkenti a láda súrlódását.
     * Ha van visitor a mezőn, amire lépne, akkor meghívja az azután következő mező accept függvényét a szomszédos visitor.
     * Ha átkerül a következő mezőre beállítja magát visitorként. Annak a mezőnek, ahonnan ellépett nullra állítja a visitor tagváltozóját.
     *
     * @param next Az olajos mező, amire mozgatni akarjuk a ládát.
     * @param d Az irány, amerre mozgatni akarjuk a ládát.
     * @param force Az erő, amivel mozgatni akarjuk a ládát.
     */
    @Override
    public void pushTo(Oil next, Directions d, int force) {
        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            Visitor visitorOnNext = next.getVisitor();
            if(visitorOnNext != null){
                ATile next1 = next.getNeighbor(d);
                next1.accept(visitorOnNext, d, reducedforce);
                visitorOnNext = next.getVisitor();
            }
            if(visitorOnNext == null){
                currentTile.setVisitor(null);
                currentTile = next;
                next.setVisitor(this);
            }
        }
    }

    /**
     * A ládák mozgatását végző függvény. Megkérdezzük, hogy mozgatható-e a doboz. Ha nem, akkor nem mozdul el.
     * Ha igen és a csapda inaktív, akkor egyel arrébb kerül a láda.
     * Ha igen és a csapda aktív, akkor megszűnik a doboz.
     * Ha van visitor a mezőn, amire lépne, akkor meghívja az azután következő mező accept függvényét a szomszédos visitor.
     * Ha átkerül a következő mezőre beállítja magát visitorként. Annak a mezőnek, ahonnan ellépett nullra állítja a visitor tagváltozóját.
     *
     * @param next A csapda, amire mozgatni akarjuk a ládát.
     * @param d Az irány, amerre mozgatni akarjuk a ládát.
     * @param force Az erő, amivel mozgatni akarjuk a ládát.
     */
    public void pushTo(Trap next, Directions d, int force) {
        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            if (next.isOpened()){
                this.die();
            }
            else{
                Visitor visitorOnNext = next.getVisitor();
                if (visitorOnNext != null) {
                    ATile next1 = next.getNeighbor(d);
                    next1.accept(visitorOnNext, d, reducedforce);
                    visitorOnNext = next.getVisitor();
                }

                if (visitorOnNext == null) {
                    if(next.isOpened()){
                        this.die();
                    }
                    else {
                        currentTile.setVisitor(null);
                        currentTile = next;
                        next.setVisitor(this);
                    }
                }
            }
        }

    }

    /**
     * A ládák mozgatását végző függvény. Megkérdezzük, hogy mozgatható-e a doboz. Ha nem, akkor nem mozdul el.
     * Ha igen, akkor egyel arrébb kerül a láda, és nem mozdítható el onnan.
     *
     * @param next A célmező, amire mozgatni akarjuk a ládát.
     * @param d Az irány, amerre mozgatni akarjuk a ládát.
     * @param force Az erő, amivel mozgatni akarjuk a ládát.
     */
    public void pushTo(Target next, Directions d, int force) {
        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            Visitor visitorOnNext = next.getVisitor();
            if(visitorOnNext != null){
                ATile next1 = next.getNeighbor(d);
                next1.accept(visitorOnNext, d, reducedforce);
                visitorOnNext = next.getVisitor();
            }
            if(visitorOnNext == null){
                currentTile.setVisitor(null);
                currentTile = next;
                next.setVisitor(this);
                this.setUnmovable();
                game.addPoint();
            }
        }
    }

    /**
     * Nem csinál semmit, mert ládát nem lehet akadályra mozgatni.
     *
     * @param next A mézes mező, amire mozgatni akarjuk a ládát.
     * @param d Az irány, amerre mozgatni akarjuk a ládát.
     * @param force Az erő, amivel mozgatni akarjuk a ládát.
     */
    @Override
    public void pushTo(Obstacle next, Directions d, int force) {
    }

    /**
     * Mozgathatatlanná állítja a dobozt
     *
     */
    public void setUnmovable() {
        movable = false;
        game.decreaseBoxes(this);
    }

    /**
     * Megszűnteti a dobozt. (Eliminálja) Csökkenti a mozgatható dobozok számát.
     *
     */
    public void die() {
        currentTile.setVisitor(null);
        currentTile = null;
        game.decreaseBoxes(this);
    }

    /**
     * Kiírja az objektumot
     * @return a kimeneti nyelvvel egyező szimbólum
     */
    @Override
    public String toString() {
        return "B" + id;
    }

    public int getForce() {
        return force;
    }
}

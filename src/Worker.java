public class Worker extends Visitor{

    //region Attribútumok
    private Controller controller;
    private Game game;
    //név kiírására szolgáló számlálók
    private static int instanceCounter = 0;
    //endregion

    //region Metódusok
    /**
     * A Worker konstruktora
     */
    public Worker(){
        controller = Controller.getInstance();
        game = Game.getInstance();
        instanceCounter++;
        id = instanceCounter;
    }

    /**
     * A worker paraméteres konstruktora
     * @param id  a munkés azonosító száma
     */
    public Worker(int id){
        controller = Controller.getInstance();
        game = Game.getInstance();
        instanceCounter++;
        this.id = id;
        //először egyik munkás sincs kiválasztva
        this.view = new WorkerView(false);
    }

    /**
     * egy adott szekvenciában először a controller hívja meg a selectedWorker move-ját
     * így ezzel indul el minden szekvenciában a visitorok mozgatása
     * meghívja a kapott irányban következő mező accept metódusát a workerrel
     * @param d a mozgatás iránya
     */
    public void move(Directions d) {
        ATile next = currentTile.getNeighbor(d);
        next.accept(this, d, this.force);
        System.out.println("lefutott a worker.move");
    }

    /**
     * Minden pushTo-nál megnézzük, hogy van-e a mezőn, amire lépne visitor
     * ha van visitor a mezőn, amire lépne, akkor meghívja az azután következő mező accept függvényét a szomszédos visitorral
     * ha az accept visszatért és még mindig van visitor a mezőn, amire lépne, akkor ha nem ő a kiválasztott raktáros, akkor meghal, mert összenyomják
     * ha ő a selected worker, akkor nem fog elmozdulni a kiindulási helyéről
     * ha átkerül a következő mezőre beállítja magát a visitorának, annak a mezőnek, ahonnan ellépett nullra állítja
    */

    /**
     * Sima Tile paraméterű pushTo, semmi különleges nem történik.
     * @param next a következő mező
     * @param d a tolás iránya
     * @param force a toló visitor ereje
     */
    public void pushTo(Tile next, Directions d, int force) {
        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            currentTile = next;
            next.setVisitor(this);
        }
        else {
            Worker sw = controller.getSelectedworker();
            if (this != sw){
                this.die();
            }
        }
        System.out.println("lefutott a worker.pushTo");
    }

    /**
     * Switch paraméterű pushTo, amikor átlép meghívja önmagát átadva paraméterként a switch switchIt metódusát,
     * ezzel box esetén kinyitja a csapdát.
     * @param next a következő mező
     * @param d a tolás iránya
     * @param force a toló visitor ereje
     */
    public void pushTo(Switch next, Directions d, int force) {
        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            currentTile = next;
            next.setVisitor(this);
            next.switchIt(this);
        }
        else {
            Worker sw = controller.getSelectedworker();
            if (this != sw){
                this.die();
            }
        }

    }

    /**
     * Mézes mező paraméterű pushTo.
     * @param next a következő mező
     * @param d a tolás iránya
     * @param force a toló visitor ereje
     */
    @Override
    public void pushTo(Honey next, Directions d, int force) {
        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            currentTile = next;
            next.setVisitor(this);
        }
        else {
            Worker sw = controller.getSelectedworker();
            if (this != sw){
                this.die();
            }
        }
    }

    /**
     * Hole paramétrű pushTO, a visitor minden esetben beleesik és meghal/megszűnik.
     * @param next a következő mező
     * @param d a tolás iránya
     * @param force a toló visitor ereje
     */

    public void pushTo(Hole next, Directions d, int force) {
        this.die();
    }
    /**
     * Olajos mező paraméterű pushTo.
     * @param next a következő mező
     * @param d a tolás iránya
     * @param force a toló visitor ereje
     */
    @Override
    public void pushTo(Oil next, Directions d, int force) {
        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            currentTile = next;
            next.setVisitor(this);
        }
        else {
            Worker sw = controller.getSelectedworker();
            if (this != sw){
                this.die();
            }
        }
    }

    /**
     * Trap paramétrű pushTO. Megkérdezzük, hogy nyitva van-e,ha igen beleesik és meghal.
     * Ha nem, megpróbál odalépni - úgy viselkedik a Trap csukva, mint egy egyszerű Tile
     * @param next a következő mező
     * @param d a tolás iránya
     * @param force a toló visitor ereje
     */
    public void pushTo(Trap next, Directions d, int force) {
        if (next.isOpened()) {
            this.die();
        } else {
            Visitor visitorOnNext = next.getVisitor();
            if (visitorOnNext != null) {
                ATile next1 = next.getNeighbor(d);
                next1.accept(visitorOnNext, d, force);
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
            } else {
                Worker sw = controller.getSelectedworker();
                if (this != sw) {
                    this.die();
                }
            }
        }
    }

    /**
     * Target paraméterű pushTo.
     * Ugyanúgy viselkedik a raktárossal szemben, mint a sima Tile
     * @param next a következő mező
     * @param d a tolás iránya
     * @param force a toló visitor ereje
     */
    public void pushTo(Target next, Directions d, int force) {
        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            currentTile = next;
            next.setVisitor(this);
        }
        else {
            Worker sw = controller.getSelectedworker();
            if (this != sw){
                this.die();
            }
        }
    }

    /**
     * Obstacle paraméterű pushTo.
     * Ha a kiválasztott raktárost léptetik ide, akkor nem tud elmozdulni ezekre a mezőkre.
     * Ha nem a kiválasztott raktáros az, akkor összenyomódik és meghal
     * @param next a következő mező
     * @param d a tolás iránya
     * @param force a toló visitor ereje
     */
    @Override
    public void pushTo(Obstacle next, Directions d, int force) {
        Worker sw = controller.getSelectedworker();
        if(this != sw){
            this.die();
        }
    }

    //ha meghal az aktuális mező visitorját nullra állítja
    //ezután kiveszi az elérhető munkások listájából magát
    public void die() {
        currentTile.setVisitor(null);
        currentTile = null;
        controller.eliminateWorker(this);
    }

    /**
     * objektum kiíráshoz
     * @return a kimeneti nyelvvel egyező szimbólum
     */
    @Override
    public String toString() {
        return "W" + id;
    }

    /**
     * Kicseréli a munkás alatti mezőt a Honey osztály egy példányára. Átállítja az összes érintett szomszédot.
     */

    public void putHoney() {
        Honey honey = new Honey();
        game.swap(this.currentTile,honey);
    }
    /**
     * Kicseréli a munkás alatti mezőt az Oil osztály egy példányára. Átállítja az összes érintett szomszédot.
     */
    public void putOil() {
        Oil oil = new Oil();
        game.swap(this.currentTile,oil);
    }
    //endregion
}

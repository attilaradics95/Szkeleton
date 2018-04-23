import java.io.IOException;

public class TestCases {

/*
    // @author Bálint
    public void MLL() {

        Directions dir = Directions.EAST;

        Controller controller = Controller.getInstance();

        //elemek létrehozása
        Worker worker = new Worker();
        Box box1 = new Box();
        Box box2 = new Box();
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Tile tile3 = new Tile();
        Tile tile4 = new Tile();

        //beállítjuk a visitorokat a mezőkre
        tile1.setVisitor(worker);
        tile2.setVisitor(box1);
        tile3.setVisitor(box2);
        tile4.setVisitor(null);

        //beállítjuk a mezőket a visitorokra
        worker.setCurrentTile(tile1);
        box1.setCurrentTile(tile2);
        box2.setCurrentTile(tile3);

        //tesztnek megfelelő pálya beállítása
        tile1.setNeighbors(null, tile2, null, null);
        tile2.setNeighbors(null, tile3, null, tile2);
        tile3.setNeighbors(null, tile4, null, tile2);
        tile4.setNeighbors(null, null, null, tile3);

        //munkás hozzáadása a controllerhez
        controller.addWorker(worker);

        //controller segítségével a munkás mozgatása
        controller.moveWorker(dir);
    }

    // @author Bálint
    public void MLMMF() {
        Directions dir = Directions.EAST;

        Controller controller = Controller.getInstance();

        //Elemek létrehozása
        Worker visitor1 = new Worker();
        Box visitor2 = new Box();
        Worker visitor3 = new Worker();
        Worker visitor4 = new Worker();
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Tile tile3 = new Tile();
        Tile tile4 = new Tile();
        Obstacle wall = new Obstacle();

        //beállítjuk a visitorokat a mezőkre
        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);
        tile3.setVisitor(visitor3);
        tile4.setVisitor(visitor4);

        //beállítjuk a mezőket a visitorokra
        visitor1.setCurrentTile(tile1);
        visitor2.setCurrentTile(tile2);
        visitor3.setCurrentTile(tile3);
        visitor4.setCurrentTile(tile4);

        //pálya beállítása
        tile1.setNeighbors(null, tile2, null, null);
        tile2.setNeighbors(null, tile3, null, tile1);
        tile3.setNeighbors(null, tile4, null, tile2);
        tile4.setNeighbors(null, wall, null, tile3);
        wall.setNeighbors(null, null, null, tile4);

        //Első munkás mozgatása
        controller.addWorker(visitor1);
        controller.addWorker(visitor3);
        controller.addWorker(visitor4);
        controller.moveWorker(dir);
    }

    // @author Bálint
    public void WorkerOnColumn() {
        Directions dir = Directions.EAST;

        Controller controller = Controller.getInstance();
        Worker worker = new Worker();
        Obstacle next = new Obstacle();
        Tile tile = new Tile();

        tile.setVisitor(worker);
        worker.setCurrentTile(tile);

        tile.setNeighbors(null, next, null, null);
        next.setNeighbors(null, null, null, tile);

        controller.addWorker(worker);
        controller.moveWorker(dir);
    }

    // @author Bálint
    public void WorkerOnHole() {
        Directions dir = Directions.EAST;

        Controller controller = Controller.getInstance();

        //Elemek létrehozása
        Worker worker1 = new Worker();
        Hole next = new Hole();
        Tile current = new Tile();

        current.setVisitor(worker1);
        worker1.setCurrentTile(current);

        //pálya beállítása
        current.setNeighbors(null, next, null, null);
        next.setNeighbors(null, null, null, current);

        //munkás mozgatása
        controller.addWorker(worker1);
        controller.moveWorker(dir);
    }

    // @author Bálint
    public void WorkerPushesBox() {
        Directions dir = Directions.EAST;

        Controller controller = Controller.getInstance();

        //Elemek létrehozása
        Worker worker = new Worker();
        Tile current = new Tile();
        Tile next = new Tile();
        Tile next1 = new Tile();
        Box box = new Box();

        current.setVisitor(worker);
        next.setVisitor(box);
        next1.setVisitor(null);

        worker.setCurrentTile(current);
        box.setCurrentTile(next);

        //pálya beállítása
        current.setNeighbors(null, next, null, null);
        next.setNeighbors(null, next1, null, current);
        next1.setNeighbors(null, null, null, next);

        //munkás mozgatása
        controller.addWorker(worker);
        controller.moveWorker(dir);
    }

    // @author Bálint
    public void WorkerStandsOnTrap() {
        Directions dir = Directions.EAST;

        Controller controller = Controller.getInstance();

        //Elemek létrehozása
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Switch tile3 = new Switch();
        Trap tile4 = new Trap();
        Worker visitor1 = new Worker();
        Box visitor2 = new Box();
        Worker visitor3 = new Worker();

        //beállítjuk a mezők szomszédjait a tesztesetnek megfelelően
        tile1.setNeighbors(null, tile2,tile4,null);
        tile2.setNeighbors(null, tile3,null,tile1);
        tile3.setNeighbors(null, null,null,tile2);
        tile4.setNeighbors(tile1, null,null,null);

        //beállítjuk, a trapet a switch-hez
        tile3.setTrap(tile4);

        //beállítjuk a csapdát, hogy inaktív állapotban kezdődjön a teszt
        tile4.setOpened(false);

        // beállítjuk a mezők látogatóit a kiindulási állapotnak megfelelően
        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);
        tile4.setVisitor(visitor3);

        //beállítjuk a látogatók aktuális mezőit
        visitor1.setCurrentTile(tile1);
        visitor2.setCurrentTile(tile2);
        visitor3.setCurrentTile(tile3);

        //hozzáadjuk a munkást a controller listájához a munkásokról, hogy irányítani tudjuk
        controller.addWorker(visitor1);
        controller.addWorker(visitor3);

        // a teszteset szerint a munkás keletre tolja a ládát eggyel
        controller.moveWorker(dir);
    }

    // @author Bálint
    public void WorkerStepsOnActiveTrap() {
        Directions dir = Directions.EAST;

        Controller controller = Controller.getInstance();

        //Elemek létrehozása
        Worker worker = new Worker();
        Trap next = new Trap();
        Tile current = new Tile();

        current.setVisitor(worker);
        next.setVisitor(null);
        worker.setCurrentTile(current);

        //pálya beállítása
        current.setNeighbors(null, next, null, null);
        next.setNeighbors(null, null, null, current);

        //csapda aktiválása
        next.setOpened(true);

        //munkás mozgatása
        controller.addWorker(worker);
        controller.moveWorker(dir);
    }

    // @author Bálint
    public void WorkerStepsOnTarget() {
        Directions dir = Directions.EAST;

        Controller controller = Controller.getInstance();

        //Elemek létrehozása
        Worker worker = new Worker();
        Tile current = new Tile();
        Target targetTile = new Target();

        current.setVisitor(worker);
        targetTile.setVisitor(null);
        worker.setCurrentTile(current);

        //pálya beállítása
        current.setNeighbors(null, targetTile, null, null);
        targetTile.setNeighbors(null, null, null, current);

        //munkás mozgatása
        controller.addWorker(worker);
        controller.moveWorker(dir);
    }

    // @author Rozi
    public void WorkerPushesBoxToTarget() {
        Controller controller = Controller.getInstance();
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Target tile3 = new Target();
        Worker visitor1 = new Worker();
        Box visitor2 = new Box();

        //beállítjuk a mezők szomszédjait a tesztesetnek megfelelően
        tile1.setNeighbors(null, tile2,null,null);
        tile2.setNeighbors(null, tile3,null,tile1);
        tile3.setNeighbors(null, null,null,tile2);

        // beállítjuk a mezők látogatóit a kiindulási állapotnak megfelelően
        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);

        //beállítjuk a látogatók aktuális mezőit
        visitor1.setCurrentTile(tile1);
        visitor2.setCurrentTile(tile2);

        //hozzáadjuk a munkást a controller listájához a munkásokról, hogy irányítani tudjuk
        controller.addWorker(visitor1);

        // a teszteset szerint a munkás keletre tolja a ládát eggyel
        controller.moveWorker(Directions.EAST);

    }

    // @author Rozi
    public void WorkerPushesBoxToColumn() {
        Controller controller = Controller.getInstance();
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Obstacle tile3 = new Obstacle();
        Worker visitor1 = new Worker();
        Box visitor2 = new Box();

        //beállítjuk a mezők szomszédjait a tesztesetnek megfelelően
        tile1.setNeighbors(null, tile2,null,null);
        tile2.setNeighbors(null, tile3,null,tile1);
        tile3.setNeighbors(null, null,null,tile2);

        // beállítjuk a mezők látogatóit a kiindulási állapotnak megfelelően
        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);

        //beállítjuk a látogatók aktuális mezőit
        visitor1.setCurrentTile(tile1);
        visitor2.setCurrentTile(tile2);

        //hozzáadjuk a munkást a controller listájához a munkásokról, hogy irányítani tudjuk
        controller.addWorker(visitor1);

        // a teszteset szerint a munkás keletre tolja a ládát eggyel
        controller.moveWorker(Directions.EAST);

    }

    // @author Rozi
    public void SelectWorkerInAction() {
        Controller controller = Controller.getInstance();
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Tile tile3 = new Tile();
        Worker visitor1 = new Worker();
        Worker visitor2 = new Worker();

        //beállítjuk a mezők szomszédjait a tesztesetnek megfelelően
        tile1.setNeighbors(null, tile2,null,null);
        tile2.setNeighbors(null, tile3,null,tile1);
        tile3.setNeighbors(null, null,null,tile2);

        // beállítjuk a mezők látogatóit a kiindulási állapotnak megfelelően
        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);

        //beállítjuk a látogatók aktuális mezőit
        visitor1.setCurrentTile(tile1);
        visitor2.setCurrentTile(tile2);

        //hozzáadjuk a munkást a controller listájához a munkásokról, hogy irányítani tudjuk
        controller.addWorker(visitor1);
        controller.addWorker(visitor2);

        // alapból a visitor1 lenne a kiválasztott munkás, de mi kiválasztjuk a 2-est
        controller.selectWorker(2);
    }

    // @author Rozi
    public void trapOpensWithABoxOnIt() {
        Controller controller = Controller.getInstance();
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Switch tile3 = new Switch();
        Trap tile4 = new Trap();
        Worker visitor1 = new Worker();
        Box visitor2 = new Box();
        Box visitor3 = new Box();

        //beállítjuk a mezők szomszédjait a tesztesetnek megfelelően
        tile1.setNeighbors(null, tile2,tile4,null);
        tile2.setNeighbors(null, tile3,null,tile1);
        tile3.setNeighbors(null, null,null,tile2);
        tile4.setNeighbors(tile1, null,null,null);

        //beállítjuk a kapcsolóhoz a csapdát
        tile3.setTrap(tile4);

        //beállítjuk a csapdát, hogy inaktív állapotban kezdődjön a teszt
        tile4.setOpened(false);

        // beállítjuk a mezők látogatóit a kiindulási állapotnak megfelelően
        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);
        tile4.setVisitor(visitor3);

        //beállítjuk a látogatók aktuális mezőit
        visitor1.setCurrentTile(tile1);
        visitor2.setCurrentTile(tile2);
        visitor3.setCurrentTile(tile4);

        //hozzáadjuk a munkást a controller listájához a munkásokról, hogy irányítani tudjuk
        controller.addWorker(visitor1);

        // a teszteset szerint a munkás keletre tolja a ládát eggyel
        controller.moveWorker(Directions.EAST);

    }

    // @author Klári
    public void WorkerPushesBoxToSwitch() {
        Controller controller = Controller.getInstance();
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Switch tile3 = new Switch();
        Worker visitor1 = new Worker();
        Box visitor2 = new Box();

        //beállítjuk a mezők szomszédjait a tesztesetnek megfelelően
        tile1.setNeighbors(null, tile2,null,null);
        tile2.setNeighbors(null, tile3,null,tile1);
        tile3.setNeighbors(null, null,null,tile2);

        // beállítjuk a mezők látogatóit a kiindulási állapotnak megfelelően
        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);

        //beállítjuk a látogatók aktuális mezőit
        visitor1.setCurrentTile(tile1);
        visitor2.setCurrentTile(tile2);

        //hozzáadjuk a munkást a controller listájához a munkásokról, hogy irányítani tudjuk
        controller.addWorker(visitor1);

        // a teszteset szerint a munkás keletre tolja a ládát eggyel
        controller.moveWorker(Directions.EAST);

    }

    // @author Klári
    public void WorkerPushesBoxFromSwitch() {
        Controller controller = Controller.getInstance();
        Tile tile1 = new Tile();
        Switch tile2 = new Switch();
        Tile tile3 = new Tile();
        Worker visitor1 = new Worker();
        Box visitor2 = new Box();

        //beállítjuk a mezők szomszédjait a tesztesetnek megfelelően
        tile1.setNeighbors(null, tile2, null, null);
        tile2.setNeighbors(null, tile3, null, tile1);
        tile3.setNeighbors(null, null, null, tile2);

        // beállítjuk a mezők látogatóit a kiindulási állapotnak megfelelően
        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);

        //beállítjuk a látogatók aktuális mezőit
        visitor1.setCurrentTile(tile1);
        visitor2.setCurrentTile(tile2);

        //hozzáadjuk a munkást a controller listájához a munkásokról, hogy irányítani tudjuk
        controller.addWorker(visitor1);

        // a teszteset szerint a munkás keletre tolja a ládát eggyel
        controller.moveWorker(Directions.EAST);
    }

    // @author Klári
    public void WorkerPushesAnotherWorkerToTile() {
        Controller controller = Controller.getInstance();
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Tile tile3 = new Tile();
        Worker visitor1 = new Worker();
        Worker visitor2 = new Worker();

        //beállítjuk a mezők szomszédjait a tesztesetnek megfelelően
        tile1.setNeighbors(null, tile2, null, null);
        tile2.setNeighbors(null, tile3, null, tile1);
        tile3.setNeighbors(null, null, null, tile2);

        // beállítjuk a mezők látogatóit a kiindulási állapotnak megfelelően
        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);

        //beállítjuk a látogatók aktuális mezőit
        visitor1.setCurrentTile(tile1);
        visitor2.setCurrentTile(tile2);

        //hozzáadjuk a munkást a controller listájához a munkásokról, hogy irányítani tudjuk
        controller.addWorker(visitor1);
        controller.addWorker(visitor2);

        // a teszteset szerint a munkás keletre tolja a ládát eggyel
        controller.moveWorker(Directions.EAST);
    }

    // @author Klári
    public void WorkerPushesAnotherWorkerToHole() {
        Controller controller = Controller.getInstance();
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Hole tile3 = new Hole();
        Worker visitor1 = new Worker();
        Worker visitor2 = new Worker();

        //beállítjuk a mezők szomszédjait a tesztesetnek megfelelően
        tile1.setNeighbors(null, tile2, null, null);
        tile2.setNeighbors(null, tile3, null, tile1);
        tile3.setNeighbors(null, null, null, tile2);

        // beállítjuk a mezők látogatóit a kiindulási állapotnak megfelelően
        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);

        //beállítjuk a látogatók aktuális mezőit
        visitor1.setCurrentTile(tile1);
        visitor2.setCurrentTile(tile2);

        //hozzáadjuk a munkást a controller listájához a munkásokról, hogy irányítani tudjuk
        controller.addWorker(visitor1);
        controller.addWorker(visitor2);

        // a teszteset szerint a munkás keletre tolja a ládát eggyel
        controller.moveWorker(Directions.EAST);
    }

    // @author Klári
    public void WorkerPushesBoxToHole() {
        Controller controller = Controller.getInstance();
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Hole tile3 = new Hole();
        Worker visitor1 = new Worker();
        Box visitor2 = new Box();

        //beállítjuk a mezők szomszédjait a tesztesetnek megfelelően
        tile1.setNeighbors(null, tile2, null, null);
        tile2.setNeighbors(null, tile3, null, tile1);
        tile3.setNeighbors(null, null, null, tile2);

        // beállítjuk a mezők látogatóit a kiindulási állapotnak megfelelően
        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);

        //beállítjuk a látogatók aktuális mezőit
        visitor1.setCurrentTile(tile1);
        visitor2.setCurrentTile(tile2);

        //hozzáadjuk a munkást a controller listájához a munkásokról, hogy irányítani tudjuk
        controller.addWorker(visitor1);

        // a teszteset szerint a munkás keletre tolja a ládát eggyel
        controller.moveWorker(Directions.EAST);
    }

    // @author Klári
    public void WorkerStepsOnSwitch() {
        Controller controller = Controller.getInstance();
        Tile tile1 = new Tile();
        Switch tile2 = new Switch();
        Worker visitor1 = new Worker();

        //beállítjuk a mezők szomszédjait a tesztesetnek megfelelően
        tile1.setNeighbors(null, tile2, null, null);
        tile2.setNeighbors(null, null, null, tile1);

        // beállítjuk a mezők látogatóit a kiindulási állapotnak megfelelően
        tile1.setVisitor(visitor1);

        //beállítjuk a látogatók aktuális mezőit
        visitor1.setCurrentTile(tile1);

        //hozzáadjuk a munkást a controller listájához a munkásokról, hogy irányítani tudjuk
        controller.addWorker(visitor1);

        // a teszteset szerint a munkás keletre tolja a ládát eggyel
        controller.moveWorker(Directions.EAST);
    }
    */
    private Game game = Game.getInstance();

    //a tesztesethez pálya betöltése után
    // minden tesztesetben kiíjuk a pályát
    //majd feldolgozzuk a bejövő parancsokat
    //és a teszt végén ismét kiírjuk a - megváltozott - pályát
    //ezt végzi el ez a függvény
    private void testing()  {
        //kezdeti pálya kiíratása
        game.showMap();
        System.out.println("Pontszám a teszt kezdetekor: " + game.getPoints());
        //parancsfeldolgozás
        System.out.println("Adja meg a parancsokat!");
        CommandParser cp = new CommandParser();
        try {
            cp.parse(Controller.getInstance(),Game.getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //teszteset végállapotában is kiírjuk a pályát
        game.showMap();
        System.out.println("Pontszám a teszt végén: " + game.getPoints());

    }

    public void MoveWorker(){
        //map betöltése
        game.loadMap("test1.txt");

        testing();

        //map elmentése
        game.saveMap("test1_out.txt");
    }
    public void SelectWorker(){
        //map betöltése
        game.loadMap("test2.txt");

        testing();

        //map elmentése
        game.saveMap("test2_out.txt");
    }
    public void KillWorker(){
        //map betöltése
        game.loadMap("test3.txt");

        testing();

        //map elmentése
        game.saveMap("test3_out.txt");
    }
    public void DestructionofBox(){
        //map betöltése
        game.loadMap("test4.txt");

        testing();

        //map elmentése
        game.saveMap("test4_out.txt");
    }
    public void BoxtoTarget(){
        //map betöltése
        game.loadMap("test5.txt");

        testing();

        //map elmentése
        game.saveMap("test5_out.txt");
    }
    public void GameOver(){
        //map betöltése
        game.loadMap("test6.txt");

        testing();

        //map elmentése
        game.saveMap("test6_out.txt");
    }
    public void Switch(){
        //map betöltése
        game.loadMap("test7.txt");

        testing();

        //map elmentése
        game.saveMap("test7_out.txt");
    }
    public void PutOil(){
        //map betöltése
        game.loadMap("test8.txt");

        testing();

        //map elmentése
        game.saveMap("test8_out.txt");
    }
    public void PutHoney(){
        //map betöltése
        game.loadMap("test9.txt");

        testing();

        //map elmentése
        game.saveMap("test9_out.txt");
    }
    public void WorkerStepsOnActiveTrap(){
        //map betöltése
        game.loadMap("test10.txt");

        testing();

        //map elmentése
        game.saveMap("test10_out.txt");
    }
    public void WorkerStepsOnInactiveTrap(){
        //map betöltése
        game.loadMap("test11.txt");

        testing();

        //map elmentése
        game.saveMap("test11_out.txt");
    }
    public void WorkerStepsOnObstacle(){
        //map betöltése
        game.loadMap("test12.txt");

        testing();

        //map elmentése
        game.saveMap("test12_out.txt");
    }
    public void WorkerStepsOnSwitch(){
        //map betöltése
        game.loadMap("test13.txt");

        testing();

        //map elmentése
        game.saveMap("test13_out.txt");
    }
    public void WorkerStepsOnTarget(){
        //map betöltése
        game.loadMap("test14.txt");

        testing();

        //map elmentése
        game.saveMap("test14_out.txt");
    }
    public void WorkerStepsOnHole(){
        //map betöltése
        game.loadMap("test15.txt");

        testing();

        //map elmentése
        game.saveMap("test15_out.txt");
    }
    public void WorkerPushesWorkerToTile(){
        //map betöltése
        game.loadMap("test16.txt");

        testing();

        //map elmentése
        game.saveMap("test16_out.txt");
    }
    public void WorkerPushesBoxToTile(){
        //map betöltése
        game.loadMap("test17.txt");

        testing();

        //map elmentése
        game.saveMap("test17_out.txt");
    }
    public void WorkerPushesBoxToInactiveTrap(){
        //map betöltése
        game.loadMap("test18.txt");

        testing();

        //map elmentése
        game.saveMap("test18_out.txt");
    }
    public void WorkerPushesBoxToActiveTrap(){
        //map betöltése
        game.loadMap("test19.txt");

        testing();

        //map elmentése
        game.saveMap("test19_out.txt");
    }
    public void WorkerPushesBoxToSwitch(){
        //map betöltése
        game.loadMap("test20.txt");

        testing();

        //map elmentése
        game.saveMap("test20_out.txt");
    }
    public void WorkerPushesBoxToHole(){
        //map betöltése
        game.loadMap("test21.txt");

        testing();

        //map elmentése
        game.saveMap("test21_out.txt");
    }
    public void WorkerPushesBoxToObstacle(){
        //map betöltése
        game.loadMap("test22.txt");

        testing();

        //map elmentése
        game.saveMap("test22_out.txt");
    }
    public void MML(){
        //map betöltése
        game.loadMap("test23.txt");

        testing();

        //map elmentése
        game.saveMap("test23_out.txt");
    }
    public void MLM(){
        //map betöltése
        game.loadMap("test24.txt");

        testing();

        //map elmentése
        game.saveMap("test24_out.txt");
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CommandParser {


    // @author Attila
    // Feldolgoz egy parancsot
    public String parse(Controller controller, Game game) throws IOException {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        String in;
        String array[];
        while (true) {
            in = br.readLine();
            array = in.split(" ");


            //Munkás kiválasztása: pl. select 1
            if (array[0].equals("select")) {
                int id = Integer.parseInt(array[1]);
                if (id > controller.getNumberOfWorkers() || id < 1)
                    return "No such worker!";
                controller.selectWorker(id);
            }


            //Munkás léptetése: pl. step w, step a, step s, step d
            if (array[0].equals("step")) {
                //Leellenőrizzük, hogy a felhasználó létező irányt adott-e meg
                if (array[1] != "w" && array[1] != "a" && array[1] != "s" && array[1] != "d")
                    return "Wrong key!";
                if (array[1] == "w")
                    controller.moveWorker(Directions.NORTH);
                if (array[1] == "a")
                    controller.moveWorker(Directions.WEST);
                if (array[1] == "s")
                    controller.moveWorker(Directions.SOUTH);
                if (array[1] == "d")
                    controller.moveWorker(Directions.EAST);
            }


            //Doboz mozgatása: pl. push 1 a
            if (array[0].equals("push")) {

                //Leellenőrizzük, hogy a felhasználó létező dobozt adott-e meg
                if (Integer.parseInt(array[1]) > controller.boxes.size() || Integer.parseInt(array[1]) < 1)
                    return "No such Box!";

                //Leellenőrizzük, hogy a felhasználó létező irányt adott-e meg
                if (array[2] != "w" && array[2] != "a" && array[2] != "s" && array[2] != "d")
                    return "Wrong key!";

                //Ha létező irányt adott meg, akkor mozgatjuk a dobozt
                if (array[2] == "w") {
                    for (Box b : controller.boxes) {
                        if (b.getId() == Integer.parseInt(array[1]))
                            b.getCurrentTile().getNeighbor(Directions.NORTH).accept(b, Directions.NORTH, b.getForce());
                    }
                }
                if (array[2] == "a") {
                    for (Box b : controller.boxes) {
                        if (b.getId() == Integer.parseInt(array[1]))
                            b.getCurrentTile().getNeighbor(Directions.WEST).accept(b, Directions.WEST, b.getForce());
                    }
                }
                if (array[2] == "s") {
                    for (Box b : controller.boxes) {
                        if (b.getId() == Integer.parseInt(array[1]))
                            b.getCurrentTile().getNeighbor(Directions.SOUTH).accept(b, Directions.SOUTH, b.getForce());
                    }
                }
                if (array[2] == "d") {
                    for (Box b : controller.boxes) {
                        if (b.getId() == Integer.parseInt(array[1]))
                            b.getCurrentTile().getNeighbor(Directions.EAST).accept(b, Directions.EAST, b.getForce());
                    }
                }
            }


            // Kapcsoló állítása: pl. switch 1
            if (array[0].equals("switch")) {
                //Leellenőrizzük, hogy a felhasználó létező kapcsolót adott-e meg
                if (Integer.parseInt(array[1]) < 1)
                    return "No such switch!";
                //Local variable 'dummy' is created to resemble and serve as a substitute for the real instance of Box.
                Box dummy = new Box();
                for (Switch s : controller.switches) {
                    if (s.getId() == Integer.parseInt(array[1]))
                        s.switchIt(dummy);
                }
            }


            //Erő és súrlódás módosítása: pl. force worker 1
            if (array[0].equals("force")) {
                if (!(array[1].equals("worker") || array[1].equals("box"))) {
                    return "'" + array[1] + "' is not a valid type!";
                }
                if (array[1].equals("worker")) {
                    for (Worker w :
                            controller.workers) {
                        if (w.getId() == Integer.parseInt(array[2]))
                            w.setForce(Integer.parseInt(array[3]));
                    }
                }
                if (array[1].equals("box")) {
                    for (Box b :
                            controller.boxes) {
                        if (b.getId() == Integer.parseInt(array[2]))
                            b.setForce(Integer.parseInt(array[3]));
                    }
                }


            }

            //Pálya betöltése: loadmap
            if (array[0].equals("loadmap"))
                game.loadMap(array[1]);

            //Pálya kiírása: showmap
            if (array[0].equals("showmap"))
                game.showMap();

            //Játék befejezése: igiveup
            if (array[0].equals("igiveup"))
                game.endRound();

            //Olaj elhelyezése: putOil
            if (array[0].equals("putOil"))
                controller.getSelectedworker().putOil();

            //Méz elhelyezése: putHoney
            if (array[0].equals("putHoney"))
                controller.getSelectedworker().putHoney();

            else
                return "No such command!";
        }
    }
}

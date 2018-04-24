import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// @author Attila
// A parancsfeldolgozásért felelős osztály
public class CommandParser {

    // @author Attila
    // Feldolgoz egy parancsot
    public void parse(Controller controller, Game game) throws IOException {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        String in = br.readLine();
        String array[];
        while (in != null) {

            array = in.split(" ");


            //Munkás kiválasztása: pl. select 1
            if (array[0].equals("select")) {
                int id = Integer.parseInt(array[1]);
                if (id > controller.getNumberOfWorkers() || id < 1)
                     System.out.println("No such worker!");
                controller.selectWorker(id);
            }


            //Munkás léptetése: pl. step w, step a, step s, step d
            else if (array[0].equals("step")) {
                //Leellenőrizzük, hogy a felhasználó létező irányt adott-e meg
                switch (array[1]){
                    case "w": controller.moveWorker(Directions.NORTH);
                        break;
                    case "a": controller.moveWorker(Directions.WEST);
                        break;
                    case "s": controller.moveWorker(Directions.SOUTH);
                        break;
                    case "d": controller.moveWorker(Directions.EAST);
                        break;
                    default:
                        System.out.println("Wrong key!");
                }
            }


            //Doboz mozgatása: pl. push 1 a
            else if (array[0].equals("push")) {

                //Leellenőrizzük, hogy a felhasználó létező dobozt adott-e meg
                if (Integer.parseInt(array[1]) > game.boxes.size() || Integer.parseInt(array[1]) < 1)
                    System.out.println("No such Box!");

                //Leellenőrizzük, hogy a felhasználó létező irányt adott-e meg
                if (!array[2].equals("w") && !array[2].equals("a") && !array[2].equals("s") && !array[2].equals("d"))
                    System.out.println("Wrong key!");

                //Ha létező irányt adott meg, akkor mozgatjuk a dobozt
                if (array[2] == "w") {
                    for (Box b : game.boxes) {
                        if (b.getId() == Integer.parseInt(array[1]))
                            b.getCurrentTile().getNeighbor(Directions.NORTH).accept(b, Directions.NORTH, b.getForce());
                    }
                }
                if (array[2] == "a") {
                    for (Box b : game.boxes) {
                        if (b.getId() == Integer.parseInt(array[1]))
                            b.getCurrentTile().getNeighbor(Directions.WEST).accept(b, Directions.WEST, b.getForce());
                    }
                }
                if (array[2] == "s") {
                    for (Box b : game.boxes) {
                        if (b.getId() == Integer.parseInt(array[1]))
                            b.getCurrentTile().getNeighbor(Directions.SOUTH).accept(b, Directions.SOUTH, b.getForce());
                    }
                }
                if (array[2] == "d") {
                    for (Box b : game.boxes) {
                        if (b.getId() == Integer.parseInt(array[1]))
                            b.getCurrentTile().getNeighbor(Directions.EAST).accept(b, Directions.EAST, b.getForce());
                    }
                }
            }


            // Kapcsoló állítása: pl. switch 1
            else if (array[0].equals("switch")) {
                //Leellenőrizzük, hogy a felhasználó létező kapcsolót adott-e meg
                if (Integer.parseInt(array[1]) < 1)
                    System.out.println("No such switch!");
                //Local variable 'dummy' is created to resemble and serve as a substitute for the real instance of Box.
                Box dummy = new Box();
                for (Switch s : game.switches) {
                    if (s.getId() == Integer.parseInt(array[1]))
                        s.switchIt(dummy);
                }
            }


            //Erő és súrlódás módosítása: pl. force worker 1
            else if (array[0].equals("force")) {
                if (!(array[1].equals("worker") || array[1].equals("box"))) {
                    System.out.println("'" + array[1] + "' is not a valid type!");
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
                            game.boxes) {
                        if (b.getId() == Integer.parseInt(array[2]))
                            b.setForce(Integer.parseInt(array[3]));
                    }
                }


            }

            //Pálya betöltése: loadmap
            else if (array[0].equals("loadmap"))
                game.loadMap(array[1]);

            //Pálya kiírása: showmap
            else if (array[0].equals("showmap"))
                game.showMap();

            //Olaj elhelyezése: putOil
            else if (array[0].equals("putOil"))
                controller.getSelectedworker().putOil();

            //Méz elhelyezése: putHoney
            else if (array[0].equals("putHoney"))
                controller.getSelectedworker().putHoney();

            //Játék befejezése: igiveup
            else if (in.equals("igiveup")) {
                game.endRound();
                return;
            }

            else if(in.equals("")) {
                game.endRound();
                return;
            }

            else
                System.out.println("No such command!");

            in = br.readLine();
        }


    }
}

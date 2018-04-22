import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CommandParser {

    // @author Attila
    //Parses commands
    public String parse(Controller controller) throws IOException
    {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        String in;
        String array[];
        while (true) {
            in = br.readLine();
            array = in.split(" ");

            //Ez csak példa a tavalyi 3. laborról. A végleges kódban nem lesz benne
            /*if (array[0].equals("cd")) {
                if (array[1].equals(".."))
                    f = f.getParentFile();
                else {
                    String path = f.getAbsolutePath();
                    f = new File(path, array[1]);
                }
            }*/

            //Munkás kiválasztása: pl. select 1
            if (array[0].equals("select")) {
                int id = Integer.parseInt(array[1]);
                if (id > controller.getNumberOfWorkers() || id<=0)
                    throw new ArrayIndexOutOfBoundsException("No such worker!");
                controller.selectWorker(id);
            }

            //Munkás léptetése: pl. step w, step a, step s, step d
            if (array[0].equals("step")) {
                //Leellenőrizzük, hogy a felhasználó létező irányt adott-e meg
                if (array[1] != "w" || array[1] != "a" || array[1] != "s" || array[1] != "d")
                    throw new Exception("Wrong key!");
                if (array[1] == "w")
                    controller.moveWorker(Directions.NORTH);
                if (array[1] == "a")
                    controller.moveWorker(Directions.WEST);
                if (array[1] == "s")
                    controller.moveWorker(Directions.SOUTH);
                if (array[1] == "d")
                    controller.moveWorker(Directions.EAST);
            }

            /*
            //Doboz mozgatása: pl. push 1 a
            if (array[0].equals("push")) {

                //Leellenőrizzük, hogy a felhasználó létező dobozt adott-e meg
                if (Integer.parseInt(array[1]) > game.getNumberOfBoxes() || Integer.parseInt(array[1]) <= 0)
                    throw new Exception("No such Box!");

                //Ha jó számot adott meg, akkor kiválasztjuk a dobozt
                Box box = game.getBox(Integer.parseInt(array[1]));

                //Leellenőrizzük, hogy a felhasználó létező irányt adott-e meg
                if (array[2] != "w" || array[1] != "a" || array[1] != "s" || array[1] != "d")
                    throw new Exception("Wrong key!");

                //Ha létező irányt adott meg, akkor mozgatjuk a dobozt
                if (array[2] == "w")

                if (array[2] == "a")
                if (array[2] == "s")
                if (array[2] == "d")
            }
            */
            
            //Hiányzik: push, igiveup, put, showmap, loadmap, switch, force;
                    return "";
            }
        }
    }

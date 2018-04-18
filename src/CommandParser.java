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

            if (array[0].equals("select")) {
                int id = Integer.parseInt(array[1]);
                if (id > controller.getNumberOfWorkers())
                    throw new ArrayIndexOutOfBoundsException("No such worker!");
                controller.selectWorker(id);
            }

            //Hiányzik: step, push, igiveup, put, showmap, loadmap, switch, force;
                    return "";
            }
        }
    }

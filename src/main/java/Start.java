

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Start {

    private Scanner _input;

    private PrintStream _output;

    private static Map<String, Command> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("DEPEND", new DependCommand());
        COMMANDS.put("INSTALL", new InstallCommand());
        COMMANDS.put("REMOVE", new RemoveCommand());
        COMMANDS.put("LIST", new ListCommand());
    }

    public static void main(String[] args) throws Exception {
        Start parser = new Start(System.out);
        parser.process();
    }
  
    public Start(PrintStream out) {
      	_input = new Scanner(System.in);
    	_output = out;

    }

   public void processLine(String line) {
        String[] arguments = line.split("[ ]+");
        Command cmd = COMMANDS.get(arguments[0]);
        if (cmd == null)
            throw new IllegalArgumentException("Unknown command " + line);

        _output.println(line);
        List<String> args = new LinkedList<String>(Arrays.asList(arguments));
        args.remove(0); 
        Map<String, Object> success = cmd.execute(args);
        success.entrySet().stream().forEach(e -> _output.println("\t" + e.getKey() + " " + e.getValue()));
    }

    public void process() throws IOException {
        String line = _input.nextLine();
        while (line != null && line.length() > 0) {
            if (line.equals("END")) {
                _output.println(line);
                break;

            }
            processLine(line);
            line = _input.nextLine();
        }
    }
}
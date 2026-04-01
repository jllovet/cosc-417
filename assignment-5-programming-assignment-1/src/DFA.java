import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DFA {

    private static final int MAX_STATES = 20;
    private static final int ALPHABET_SIZE = 2;

    // transition[state][symbol] = nextState (0 is rejected)
    private int[][] transition;
    private Set<Integer> acceptingStates;

    public DFA(String path) {
        transition = new int[MAX_STATES][ALPHABET_SIZE];
        acceptingStates = new HashSet<>(); //prevent dupes using Hashset
        if (path != "" & path != null) {
            try {
                buildDFAFromFile(path);
            } catch (Exception e) {
                System.err.println("error building DFA from file, falling back to empty DFA");
            }
        }
    }

    //testing static accepted states - NEED FILE I/O IMPLEMENTATION
    public void addAcceptingState(int state) {
        acceptingStates.add(state);
    }

    public void addTransition(int from, int symbol, int to) {
        if (from < 1 || from > MAX_STATES || to < 1 || to > MAX_STATES)
            throw new IllegalArgumentException(
                "State out of range in transition:" + from + " " + symbol + " " + to);
        if (symbol != 0 && symbol != 1)
            throw new IllegalArgumentException("Symbol must be 0 or 1" + symbol);
        transition[from][symbol] = to;
    }

    private List<String> readDFASpecFromFile(String path) {
        Path p = Paths.get(path);
        try {
            List<String> lines = Files.readAllLines(p);
            System.out.println("GOT LINES");
            return lines;
        } catch (IOException | SecurityException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private void buildDFAFromFile(String path) throws IOException, NumberFormatException{
        List<String> lines = readDFASpecFromFile(path);
        if (lines == null){
            throw new IOException("Unable to read DFA definition from file at " + path);
        }
        String[] acceptingStates = lines.get(1).split(" ");
        for (String state: acceptingStates) {
            try {
                int acceptingState = Integer.parseInt(state);
                addAcceptingState(acceptingState);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Unable to parse accepting state " + state);
            }
        }
        for (String transition: lines.subList(2,lines.size())) {
            try {
                String[] transitionComponents = transition.split("\\s+");
                int startState = Integer.parseInt(transitionComponents[0]);
                int inputSymbol = Integer.parseInt(transitionComponents[1]);
                int targetState = Integer.parseInt(transitionComponents[2]);
                addTransition(startState, inputSymbol, targetState);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Unable to transition " + transition);
            }
        }
    }

    //Simulate over DFA using input string w. (Returns "ACCEPT" or "REJECT")
    public String simulate(String w) {
        // Validate input string characters
        for (char c : w.toCharArray()) {
            if (c != '0' && c != '1')
                throw new IllegalArgumentException(
                    "Input must only contain strings of 0 or 1." + c + "'");
        }

        int currentState = 1; //starting state is always 1
        System.out.println("\n Simulating DFA...");
        System.out.println("Starting state: " + currentState);

        for (char c : w.toCharArray()) {
            int symbol = c - '0';
            int nextState = transition[currentState][symbol];

            System.out.printf("δ(%d, %c) = %d%n", currentState, c, nextState);

            if (nextState == 0) {
                System.out.println("ERROR: No transition defined");
                return "REJECT";
            }
            currentState = nextState;
        }

        System.out.println("Final state is " + currentState);
        return acceptingStates.contains(currentState) ? "ACCEPT" : "REJECT";
    }

    public static void main(String[] args) {
        DFA dfa = new DFA(args[0]);

        try (//Prompt user for input string w
        Scanner scan = new Scanner(System.in)) {
            while (true) {
                System.out.print("\nEnter input string w. (Enter 'quit' to exit)\n");
                String w = scan.nextLine().trim();

                if (w.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting simulation");
                    break;
                }

                try {
                    String result = dfa.simulate(w);
                    System.out.println(result);
                } catch (IllegalArgumentException e) {
                    System.out.println("ERROR" + e.getMessage());
                }
            }
        }
    }
}
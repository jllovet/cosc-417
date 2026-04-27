import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class NFASimulator {

    private static final int EPSILON = -1;

    private int numStates;
    private Set<Integer> acceptingStates;
    private Map<String, Set<Integer>> transitions;

    public NFASimulator(String path) {
        acceptingStates = new HashSet<>();
        transitions = new HashMap<>();
        if (path != null && !path.isEmpty()) {
            try {
                buildNFAFromFile(path);
            } catch (Exception e) {
                System.err.println("Error building NFA from file, falling back to empty NFA");
            }
        }
    }

    public void addAcceptingState(int state) {
        if (state < 1 || state > 20)
            throw new IllegalArgumentException("Accepting state out of range: " + state);
        acceptingStates.add(state);
    }

    public void addTransition(int from, int symbol, int to) {
        if (from < 1 || from > 20 || to < 1 || to > 20)
            throw new IllegalArgumentException("State out of range in transition: " + from + " " + symbol + " " + to);
        if (symbol != 0 && symbol != 1 && symbol != EPSILON)
            throw new IllegalArgumentException("Symbol must be 0, 1, or -1, got: " + symbol);
        String key = from + "," + symbol;
        if (!transitions.containsKey(key)) {
            transitions.put(key, new HashSet<>());
        }
        transitions.get(key).add(to);
    }

    private List<String> readNFASpecFromFile(String path) {
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

    private void buildNFAFromFile(String path) throws IOException, NumberFormatException {
        List<String> lines = readNFASpecFromFile(path);
        if (lines == null) {
            throw new IOException("Unable to read NFA definition from file at " + path);
        }

        // line 0: number of states
        try {
            numStates = Integer.parseInt(lines.get(0).trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Unable to parse number of states: " + lines.get(0));
        }

        // line 1: accepting states
        String[] accepting = lines.get(1).split(" ");
        for (String state : accepting) {
            try {
                int s = Integer.parseInt(state.trim());
                addAcceptingState(s);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Unable to parse accepting state: " + state);
            }
        }

        // lines 2+: transitions
        for (String transition : lines.subList(2, lines.size())) {
            if (transition.trim().isEmpty()) continue;
            try {
                String[] parts = transition.split("\\s+");
                int from   = Integer.parseInt(parts[0]);
                int symbol = Integer.parseInt(parts[1]);
                int to     = Integer.parseInt(parts[2]);
                addTransition(from, symbol, to);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Unable to parse transition: " + transition);
            }
        }
    }

    public String simulate(String w) {
        for (char c : w.toCharArray()) {
            if (c != '0' && c != '1')
                throw new IllegalArgumentException(
                    "Input must only contain strings of 0 or 1, got '" + c + "'");
        }

        Set<Integer> currentStates = new HashSet<>();
        currentStates.add(1);
        currentStates = epsilonClosure(currentStates);

        System.out.println("\nSimulating NFA...");
        System.out.println("Starting epsilon-closure({1}) = " + currentStates);

        for (char c : w.toCharArray()) {
            int symbol = c - '0';

            Set<Integer> moved = move(currentStates, symbol);
            Set<Integer> nextStates = epsilonClosure(moved);

            System.out.println("d(" + currentStates + ", " + c + ") = " + moved + ", epsilon-closure = " + nextStates);

            currentStates = nextStates;

            if (currentStates.isEmpty()) {
                System.out.println("No active states, NFA will reject");
                break;
            }
        }

        System.out.println("Final states: " + currentStates);

        Set<Integer> finalAccepting = new HashSet<>(currentStates);
        finalAccepting.retainAll(acceptingStates);
        return !finalAccepting.isEmpty() ? "ACCEPT" : "REJECT";
    }

    // computes all states reachable via zero or more epsilon transitions
    private Set<Integer> epsilonClosure(Set<Integer> states) {
        Set<Integer> closure = new HashSet<>(states);
        for (int state : states) {
            dfsEpsilon(state, closure);
        }
        return closure;
    }

    // recursive dfs following epsilon transitions, closure acts as visited set
    private void dfsEpsilon(int state, Set<Integer> closure) {
        String key = state + "," + EPSILON;
        Set<Integer> epsilonClosure = transitions.get(key);

        if (epsilonClosure == null) return;

        for (int next : epsilonClosure) {
            if (!closure.contains(next)) {
                closure.add(next);
                dfsEpsilon(next, closure);
            }
        }
    }

    // returns all states reachable by reading one symbol from the current set
    private Set<Integer> move(Set<Integer> states, int symbol) {
        Set<Integer> result = new HashSet<>();
        for (int state : states) {
            String key = state + "," + symbol;
            Set<Integer> reachable = transitions.get(key);
            if (reachable != null) {
                result.addAll(reachable);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String path = args.length > 0 ? args[0] : "nfa.txt";
        NFASimulator nfa = new NFASimulator(path);

        try (Scanner scan = new Scanner(System.in)) {
            while (true) {
                System.out.print("\nEnter input string w. (Enter 'quit' to exit)\n");
                String w = scan.nextLine().trim();

                if (w.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting simulation");
                    break;
                }

                try {
                    String result = nfa.simulate(w);
                    System.out.println(result);
                } catch (IllegalArgumentException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
        }
    }
}
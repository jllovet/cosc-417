import java.util.*;

public class DFA {

    private static final int MAX_STATES = 20;
    private static final int ALPHABET_SIZE = 2;

    // transition[state][symbol] = nextState (0 is rejected)
    private int[][] transition;
    private Set<Integer> acceptingStates;

    public DFA() {
        transition = new int[MAX_STATES][ALPHABET_SIZE];
        acceptingStates = new HashSet<>(); //prevent dupes using Hashset
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
        System.out.println("DFA Simulator");
        System.out.println("Σ: {0, 1}");
        System.out.println("Starting state: 1)");
        System.out.println("Accepting: {3, 11, 17}");
        System.out.println("Transitions:");
        System.out.println("δ(1, 0) = 11");
        System.out.println("δ(1, 1) = 7");
        System.out.println("δ(2, 0) = 4");
        System.out.println("δ(2, 1) = 6");


        DFA dfa = new DFA();

        //Test Accept states - NEED FILE I/O IMPLEMENTATION
        dfa.addAcceptingState(3);
        dfa.addAcceptingState(11);
        dfa.addAcceptingState(17);

        //Test Transitions - NEED FILE I/O IMPLEMENTATION
        dfa.addTransition(1, 0, 11);
        dfa.addTransition(1, 1, 7);
        dfa.addTransition(2, 0, 4);
        dfa.addTransition(2, 1, 6);

        //Prompt user for input string w
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter input string w. (Enter 'quit' to exit)");
            String w = scan.nextLine().trim();

            if (w.equalsIgnoreCase("quit")) {
                System.out.println("Exiting simulation");
                break;
            }

            try {
                String result = dfa.simulate(w);
                System.out.println("\nResult: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR" + e.getMessage());
            }
        }
    }
}
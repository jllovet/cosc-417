import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DFATest {
    @Test
    void acceptsOnAcceptingState() {
        DFA dfa = new DFA();
        dfa.addAcceptingState(11);
        dfa.addTransition(1, 0, 11);
        assertEquals("ACCEPT", dfa.simulate("0"));
    }

    @Test
    void rejectsOnNonAcceptingState() {
        DFA dfa = new DFA();
        // DFA has no accepting states by hypothesis
        dfa.addTransition(1, 1, 7);
        assertEquals("REJECT", dfa.simulate("1"));
    }
}

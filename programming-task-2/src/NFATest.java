import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class NFATest {
    @Test
    void acceptsOnAcceptingState() {
        NFASimulator nfa = new NFASimulator("");
        nfa.addAcceptingState(11);
        nfa.addTransition(1, 0, 11);
        assertEquals("ACCEPT", nfa.simulate("0"));
    }

    @Test
    void rejectsOnNonAcceptingState() {
        NFASimulator nfa = new NFASimulator("");
        // NFA has no accepting states by hypothesis
        nfa.addTransition(1, 1, 7);
        assertEquals("REJECT", nfa.simulate("1"));
    }
    @Test
    void processInputsForExampleNFAFromFile(){
        NFASimulator nfa = new NFASimulator("testdata/nfa.txt");
        assertEquals("ACCEPT", nfa.simulate("0"));
        assertEquals("REJECT", nfa.simulate("01"));
        assertEquals("ACCEPT", nfa.simulate("110"));
        assertEquals("ACCEPT", nfa.simulate("0100"));
        assertEquals("ACCEPT", nfa.simulate("100"));
        assertEquals("ACCEPT", nfa.simulate("011"));
    }
}

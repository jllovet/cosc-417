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

public class DFATest {
    @Test
    void acceptsOnAcceptingState() {
        DFA dfa = new DFA("");
        dfa.addAcceptingState(11);
        dfa.addTransition(1, 0, 11);
        assertEquals("ACCEPT", dfa.simulate("0"));
    }

    @Test
    void rejectsOnNonAcceptingState() {
        DFA dfa = new DFA("");
        // DFA has no accepting states by hypothesis
        dfa.addTransition(1, 1, 7);
        assertEquals("REJECT", dfa.simulate("1"));
    }
    @Test
    void processInputsForExampleDFA1_11(){
        DFA dfa = new DFA("");
        // Manually build DFA
        dfa.addAcceptingState(2);
        dfa.addAcceptingState(4);
        // Root
        dfa.addTransition(1,0,2);
        dfa.addTransition(1,1,4);
        // Left hand side
        dfa.addTransition(2,0,2);
        dfa.addTransition(2,1,3);
        dfa.addTransition(3,0,2);
        dfa.addTransition(3,1,3);
        // Right hand side
        dfa.addTransition(4,0,5);
        dfa.addTransition(4,1,4);
        dfa.addTransition(5,0,5);
        dfa.addTransition(5,1,4);
        assertEquals("ACCEPT", dfa.simulate("0"));
        assertEquals("ACCEPT", dfa.simulate("111"));
        assertEquals("ACCEPT", dfa.simulate("0010"));
        assertEquals("REJECT", dfa.simulate(""));
        assertEquals("REJECT", dfa.simulate("10"));
        assertEquals("REJECT", dfa.simulate("011"));
    }
    @Test
    void processInputsForExampleDFA1_68a(){
        DFA dfa = new DFA("");
        // Manually build DFA
        dfa.addAcceptingState(2);
        dfa.addAcceptingState(3);
        dfa.addTransition(1,0,2);
        dfa.addTransition(1,1,3);
        dfa.addTransition(2,0,1);
        dfa.addTransition(2,1,2);
        dfa.addTransition(3,0,2);
        dfa.addTransition(3,1,1);
        assertEquals("ACCEPT", dfa.simulate("1"));
        assertEquals("ACCEPT", dfa.simulate("01"));
        assertEquals("ACCEPT", dfa.simulate("101"));
        assertEquals("REJECT", dfa.simulate(""));
        assertEquals("REJECT", dfa.simulate("00"));
        assertEquals("REJECT", dfa.simulate("0110"));
    }

    @Test
    void processInputsForExampleDFA1_11FromFile(){
        DFA dfa = new DFA("testdata/1_11_dfa.txt");
        assertEquals("ACCEPT", dfa.simulate("0"));
        assertEquals("ACCEPT", dfa.simulate("111"));
        assertEquals("ACCEPT", dfa.simulate("0010"));
        assertEquals("REJECT", dfa.simulate(""));
        assertEquals("REJECT", dfa.simulate("10"));
        assertEquals("REJECT", dfa.simulate("011"));
    }
    @Test
    void processInputsForExampleDFA1_68aFromFile(){
        DFA dfa = new DFA("testdata/1_68_a_dfa.txt");
        assertEquals("ACCEPT", dfa.simulate("1"));
        assertEquals("ACCEPT", dfa.simulate("01"));
        assertEquals("ACCEPT", dfa.simulate("101"));
        assertEquals("REJECT", dfa.simulate(""));
        assertEquals("REJECT", dfa.simulate("00"));
        assertEquals("REJECT", dfa.simulate("0110"));
    }
}

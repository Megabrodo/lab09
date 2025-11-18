package it.unibo.mvc;

import java.util.List;

//import edu.umd.cs.findbugs.annotations.OverrideMustInvoke;

import java.util.ArrayList;
import java.util.Collections;
/**
 * 
 *
 */

public final class SimpleController implements Controller {

    private final List<String> history = new ArrayList<>();
    private String nextString = "";

    @Override
    public void setNextString(final String s) {
        if (s.isBlank()) {
            throw new IllegalStateException("String should not be null");
        } else {
            this.nextString = s;
        }
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public List<String> getHistory() {
        return Collections.unmodifiableList(this.history);
    }

    @Override
    public void printString() {
        if (nextString.isBlank()) {
            throw new IllegalStateException("String has not been set");
        } else {
            history.add(getNextString());
            System.out.println(getNextString()); // NOPMD Intentional design as we don't use log
        }
    }
}

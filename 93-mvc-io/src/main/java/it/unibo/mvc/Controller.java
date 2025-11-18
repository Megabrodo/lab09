package it.unibo.mvc;

import java.util.List;
/**
 *
 */

public interface Controller {

    /**
     * @param s is set as the to-be-printed string
     */
    void setNextString(String s);

    /**
     * @return the current saved string
     */
    String getNextString();

    /**
     * @return the history of previously printed strings
     */
    List<String> getHistory();

    /**
     * Prints to terminal the current string.
     */
    void printString();
}

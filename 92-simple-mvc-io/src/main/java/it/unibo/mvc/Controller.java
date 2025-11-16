package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String PATH = System.getProperty("user.home")
            + File.separator
            + "output.txt";
    private File file = new File(PATH);

    /**
     * Sets.
     * 
     * @param file as the new controlled file.
     */
    public void setFile(final File file) {
        this.file = file;
    }

    /**
     * Functional method.
     * 
     * @return the current file
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Functional method.
     * 
     * @return the string path of the current file
     */
    public String getPath() {
        return this.file.getPath();
    }

    /**
     * Writes s in the current file and handles the exception.
     * 
     * @param s used to write
     * @throws IOException if something goes wrong
     */
    public void setContents(final String s) throws IOException {
        final PrintStream out = new PrintStream(file, StandardCharsets.UTF_8);
        out.println(s);
        out.close();
    }
}

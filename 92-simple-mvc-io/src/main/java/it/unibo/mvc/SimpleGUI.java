package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
//import static javax.swing.JOptionPane.showMessageDialog;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {
    private static final String TITLE = "Simple MVC";

    private final Controller controller = new Controller();
    private final JFrame frame = new JFrame(TITLE);

    /**
     * Builds a simple example of GUI that writes on the same file.
     */
    public SimpleGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JTextArea area = new JTextArea();
        canvas.add(area, BorderLayout.CENTER);

        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                if (showConfirmDialog(frame, "Save Text?", "Save", YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        controller.setContents(area.getText());
                    } catch (final IOException e) {
                        JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 4, sh / 3);
        frame.setLocationByPlatform(true);
    }

    /**
     * Makes the frame visible.
     */
    public void display() {
        frame.setVisible(true);
    }

    /**
     * Runs the application.
     * 
     * @param args ignored
     */
    public static void main(final String... args) {
        final SimpleGUI gui = new SimpleGUI();
        gui.display();
    }
}

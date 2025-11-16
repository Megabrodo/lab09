package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
private static final String TITLE = "Simple MVC";

    private final Controller controller = new Controller();
    private final JFrame frame = new JFrame(TITLE);

    /**
     * Builds a GUI capable of choosing different files to write in.
     */
    public SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JPanel filechooserpanel = new JPanel();
        filechooserpanel.setLayout(new BoxLayout(filechooserpanel, BoxLayout.LINE_AXIS));

        final JTextArea area = new JTextArea();
        canvas.add(area, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);

        final JTextField url = new JTextField(controller.getPath());
        url.setEditable(false);
        filechooserpanel.add(url);
        final JButton browse = new JButton("Browse...");
        filechooserpanel.add(browse);
        canvas.add(filechooserpanel, BorderLayout.NORTH);

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

        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                final JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(controller.getFile());
                final int choice = chooser.showSaveDialog(frame);
                if (choice == JFileChooser.APPROVE_OPTION) {
                    final File newFile = chooser.getSelectedFile();
                    controller.setFile(newFile);
                    url.setText(controller.getPath());
                } else if (choice == JFileChooser.CANCEL_OPTION) {
                    return; // NOPMD Do nothing by design
                } else {
                    JOptionPane.showMessageDialog(frame, "Error");
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
     * Launchs the application.
     * 
     * @param args ignored
     */
    public static void main(final String... args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser();
        gui.display();
    }
}

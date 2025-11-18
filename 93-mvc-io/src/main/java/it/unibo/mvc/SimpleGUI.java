package it.unibo.mvc;

//import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
//import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final SimpleController controller;

    /**
     * Builds a new SimpleGUI using SimpleController as controller.
     */
    public SimpleGUI() {
        this.controller = new SimpleController();
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JTextField field = new JTextField();
        canvas.add(field, BorderLayout.NORTH);

        final JTextArea area = new JTextArea();
        area.setEditable(false);
        canvas.add(area, BorderLayout.CENTER);

        final JPanel horizontalCanvas = new JPanel();
        horizontalCanvas.setLayout(new BoxLayout(horizontalCanvas, BoxLayout.LINE_AXIS));
        canvas.add(horizontalCanvas, BorderLayout.SOUTH);

        final JButton print = new JButton("Print");
        horizontalCanvas.add(print);

        final JButton history = new JButton("Show history");
        horizontalCanvas.add(history);

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                controller.setNextString(field.getText());
                controller.printString();
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                area.setText(controller.getHistory().toString());
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);

        frame.setLocationByPlatform(true);

        //frame.pack();

        frame.setVisible(true);
    }

    /**
     * @param args ignored
     */
    public static void main(final String... args) {
       new SimpleGUI().display();
    }
}

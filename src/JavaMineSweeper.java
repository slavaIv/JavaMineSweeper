import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import sweeper.Box;

public class JavaMineSweeper extends JFrame {

    private final int IMAGE_SIZE = 50;
    private final int COLS = 15;
    private final int ROWS = 1;

    private JPanel panel;

    public static void main(String[] args) {
        new JavaMineSweeper();
    }

    private JavaMineSweeper() {
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (Box box : Box.values()) {

                    g.drawImage(box.image, box.ordinal() * IMAGE_SIZE, 0, this);
                }

            }
        };

        panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
        add(panel);
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Minesweeper");
        setVisible(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private Image getImage(String name) {
        String fileName = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }

}

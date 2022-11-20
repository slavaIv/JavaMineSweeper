import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputAdapter;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaMineSweeper extends JFrame {

    private final int IMAGE_SIZE = 50;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;

    private Game game;

    private JPanel panel;

    public static void main(String[] args) {
        new JavaMineSweeper();
    }

    private JavaMineSweeper() {
        
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (Coord coord: Ranges.getAllCoords()) {
                    g.drawImage(game.getBoxGame(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }

            }
        };

        // add mouse control functions
        panel.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1:
                        game.pressLeftButton(coord);
                        break;
                    case MouseEvent.BUTTON3:
                        game.pressRightButton(coord);
                        break;
                    case MouseEvent.BUTTON2:
                        game.start();
                        break;
                }
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
        setIconImage(getImage("icon"));
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Minesweeper");
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private Image getImage(String name) {
        String fileName = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }

}

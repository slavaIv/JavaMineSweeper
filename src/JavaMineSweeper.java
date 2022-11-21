import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputAdapter;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaMineSweeper extends JFrame {

    private final int IMAGE_SIZE = 50;
    private int COLS = 9;
    private int ROWS = 9;
    private int BOMBS = 10;

    private Game game;

    private JPanel panel;
    private JLabel label;
    private JMenuBar menuBar;

    public static void main(String[] args) {
        new JavaMineSweeper();
    }

    private JavaMineSweeper() {
        init();
        initLabel();
    }

    private void init() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();

        initMenu();

        setImages();
        initPanel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel(getMessage());
        Font font = new Font("Tahoma", Font.BOLD, 20);
        label.setFont(font);
        add(label, BorderLayout.NORTH);
    }

    private void initMenu() {
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        Font font = new Font("Tahoma", Font.BOLD, 14);
        menu.setFont(font);

        JMenuItem menuBeginner = new JMenuItem("Beginner");
        JMenuItem menuIntermediate = new JMenuItem("Intermediate");
        JMenuItem menuExpert = new JMenuItem("Expert");

        menu.add(menuBeginner);
        menu.add(menuIntermediate);
        menu.add(menuExpert);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        menuBeginner.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                COLS = 9;
                ROWS = 9;
                BOMBS = 10;
                init();
                menu.getPopupMenu().setVisible(false);
            }

        });

        menuIntermediate.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                COLS = 15;
                ROWS = 15;
                BOMBS = 40;
                
                init();
                menu.getPopupMenu().setVisible(false);
            }
            
        });

        menuExpert.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                COLS = 29;
                ROWS = 15;
                BOMBS = 100;
                init();
                menu.getPopupMenu().setVisible(false);
            }
        });
        
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (Coord coord : Ranges.getAllCoords()) {
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
                label.setText(getMessage());

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

    private String getMessage() {
        switch (game.getState()) {
            case BOMBED:
                return "Boom!!! You've lost!";
            case WIN:
                return "Congratulation!! You win!!";
            case PLAYING:
            default:
                if (game.getTotalFlags() == 0) {
                    return "Welcome to JavaMineSweeper!";
                } else {
                    return "Flagged " + game.getTotalFlags() + " of " + game.getTotalBombs() + " bombs.";
                }
        }
    }

}

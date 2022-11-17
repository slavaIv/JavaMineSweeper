import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class JavaMineSweeper extends JFrame {

    private JPanel panel;

    public static void main(String[] args) {
        new JavaMineSweeper();
    }

    private JavaMineSweeper() {
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 300));
        add(panel);
    }
    
    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Minesweeper");
        setVisible(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

}

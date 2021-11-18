import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class UserMenu {
    private JTextArea printOutArea;
    private JPanel panelImg;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JButton editButton;
    private JButton button2;
    private JButton button3;
    private JComboBox comboBox1;
    private JScrollPane contentPane;

    private void createUIComponents() throws IOException, InterruptedException {
        // TODO: place custom component creation code here
        final BufferedImage image = ImageIO.read(new File("background.jpg"));
        panelImg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
        printOutArea = new JTextArea();
        printOutArea.setEditable (false);


        JTextAreaOutputStream out = new JTextAreaOutputStream (printOutArea);
        System.setOut (new PrintStream(out));
        System.out.println("La vaca de to mare");

    }
    public static void UsrMenu() {

        JFrame f = new JFrame();
        f.setContentPane(new UserMenu().panelImg);

        f.setVisible(true);
        f.setSize(1280, 800);
        f.setResizable(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}

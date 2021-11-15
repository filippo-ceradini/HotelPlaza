import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends Canvas {


    public static void login() throws IOException {
        JFrame frame = new JFrame();
        frame.setLocation(40,40);
        final BufferedImage image = ImageIO.read(new File("background.jpg"));

        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
        int centerx = 400;//numbers for centering the login items
        int centery = 300;
        final JLabel label = new JLabel();//Login Label
        JLabel l1 = new JLabel("Username:");
        JLabel l2 = new JLabel("Password:");
        JButton b = new JButton("Login");
        final JPasswordField passw = new JPasswordField();
        final JTextField login = new JTextField();


        l1.setBounds(centerx+20, centery+20, 80, 30);
        l1.setForeground(Color.white);
        l2.setBounds(centerx+20, centery+75, 80, 30);
        l2.setForeground(Color.white);
        login.setBounds(centerx+100, centery+20, 100, 30);
        passw.setBounds(centerx+100, centery+75, 100, 30);
        b.setBounds(centerx+100, centery+120, 80, 30);
        label.setBounds(centerx+20, centery+170, 200, 50);
        label.setForeground(Color.white);



        frame.add(b);
        frame.add(login);
        frame.add(passw);
        frame.add(l1);
        frame.add(label);
        frame.add(l2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.addActionListener(e -> {
            String data = "Username " + login.getText();
            data += ", Password: "
                    + new String(passw.getPassword());
            label.setText(data);
        });


        frame.add(pane);
        frame.setSize(1280, 825);
        frame.setVisible(true);

    }
}

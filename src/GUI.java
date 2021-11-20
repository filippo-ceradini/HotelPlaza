import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends ManageRooms{

    //Creates the login initial page


    public static void login() throws IOException {
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
        JFrame frame = new JFrame();
        final JLabel label = new JLabel();//Login Label
        JLabel header = new JLabel("Crazy Hotel Manager");
        JLabel l1 = new JLabel("Username:");
        JLabel l2 = new JLabel("Password:");
        JButton b = new JButton("Login");
        final JPasswordField passw = new JPasswordField();
        final JTextField login = new JTextField();

        header.setBounds(150,150,1200,50);
        header.setForeground(Color.white);
        header.setFont(new Font("Royal King", Font.PLAIN,60));
        l1.setBounds(centerx + 20, centery + 20, 80, 30);
        l1.setForeground(Color.white);
        l2.setBounds(centerx + 20, centery + 75, 80, 30);
        l2.setForeground(Color.white);
        login.setBounds(centerx + 100, centery + 20, 100, 30);
        passw.setBounds(centerx + 100, centery + 75, 100, 30);
        b.setBounds(centerx + 100, centery + 120, 80, 30);
        label.setBounds(centerx + 20, centery + 170, 200, 50);
        label.setForeground(Color.white);

        frame.add(header);
        frame.add(b);
        frame.add(login);
        frame.add(passw);
        frame.add(l1);
        frame.add(label);
        frame.add(l2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.addActionListener(e -> {
            /*String data = "Username " + login.getText();
            data += ", Password: "
                    + new String(passw.getPassword());
            label.setText(data);*/
            if (login.getText().equals("admin")){
                    System.out.println("cazzone");
                try {
                    GUI.admin();
                    frame.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.out.println("accade dopo");
            }
        });

        //frame.setLayout(new FlowLayout());
        frame.add(pane);
        frame.setSize(1280, 825);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        if (login.getText().equals("admin")){
            try {
                frame.setVisible(false);
                GUI.admin();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void admin() throws IOException {
        JFrame frame = new JFrame();

        //Load Background Image in a Jpanel
        final BufferedImage image = ImageIO.read(new File("background.jpg"));
        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };


        JButton b1 = new JButton("Manage Staff");
        JButton b2 = new JButton("Manage Rooms");
        JButton b3 = new JButton("Back");

        b1.setBounds(150, 120, 200, 30);
        b2.setBounds(150, 180, 200, 30);
        b3.setBounds(150, 240, 200, 30);

        frame.add(b1);
        frame.add(b2);
        frame.add(b3);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b1.addActionListener(e -> {

        });
        b2.addActionListener(e -> {
            ManageRooms.MenuRooms();



        });
        b3.addActionListener(e -> {
            try {
                GUI.login();
                frame.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });



        //frame.setLayout(new FlowLayout());
        frame.add(pane);
        frame.setSize(1280, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}

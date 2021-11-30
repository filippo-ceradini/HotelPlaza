import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GUI {

    //Creates the login initial page


    public static void login() throws IOException {
        FileManager fm = new FileManager();
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
        JButton Guest = new JButton("Guest Login");
        JButton newGuest = new JButton("New Guest");
        final JPasswordField passwordField = new JPasswordField();
        final JTextField login = new JTextField();

        header.setBounds(150, 150, 1200, 50);
        header.setForeground(Color.white);
        header.setFont(new Font("Royal King", Font.PLAIN, 60));
        l1.setBounds(centerx + 20, centery + 20, 80, 30);
        l1.setForeground(Color.white);
        l2.setBounds(centerx + 20, centery + 75, 80, 30);
        l2.setForeground(Color.white);
        login.setBounds(centerx + 100, centery + 20, 100, 30);
        passwordField.setBounds(centerx + 100, centery + 75, 100, 30);
        b.setBounds(centerx + 70, centery + 120, 80, 30);
        Guest.setBounds(centerx + 150, centery + 120, 100, 30);
        newGuest.setBounds(centerx + 150, centery + 160, 100, 30);
        label.setBounds(centerx + 20, centery + 170, 200, 50);
        label.setForeground(Color.white);

        frame.add(header);
        frame.add(b);
        frame.add(Guest);
        frame.add(newGuest);
        frame.add(login);
        frame.add(passwordField);
        frame.add(l1);
        frame.add(label);
        frame.add(l2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.addActionListener(e -> {
            String adminpsw = "admin";
            char[] input = passwordField.getPassword();
            char[] adminps = adminpsw.toCharArray();
            boolean isCorrect = Arrays.equals(adminps, input);

            if (isCorrect && adminpsw.equals(login.getText())) {

                try {
                    GUI.admin();
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, "Welcome venerable Admin");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } else if (!login.getText().equals("admin")) {
                for (Staff f : FileManager.getStaff()
                ) {
                    String username = f.getName() + "." + f.getSurname();
                    if (username.equalsIgnoreCase(login.getText()) && Arrays.equals(f.getPassword().toCharArray(), input)) {
                        UserMenu.MainMenu();
                        frame.dispose();
                        JOptionPane.showMessageDialog(null, "Welcome " + f.getName());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Could not find User\n Try again");
            }

        });
        Guest.addActionListener(e -> {
            int GuestID = 0;
            char[] input = passwordField.getPassword();
            int cnt = 0;
            for (Guest guest : fm.seeUsers()
            ) {
                System.out.println(login.getText() + guest.getLogin() + guest.getPassword() + input);
                if (login.getText().equals(guest.getLogin()) && Arrays.equals(guest.getPassword().toCharArray(), input)) {
                    System.out.println(login.getText() + guest.getLogin() + guest.getPassword().toCharArray() + input);
                    GuestID = guest.getUserID();
                    GuestMenu.GuestMenue(GuestID);
                    cnt++;
                }
            }
            if (cnt == 0) {Diag("No Guest Found");}
        });

        newGuest.addActionListener(e -> {
            GuestGUI.GuestGUI();
        });

        //frame.setLayout(new FlowLayout());
        frame.add(pane);
        frame.setSize(1280, 825);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
        JButton back = new JButton("Back");

        b1.setBounds(150, 120, 200, 30);
        b2.setBounds(150, 180, 200, 30);
        back.setBounds(150, 240, 200, 30);

        frame.add(b1);
        frame.add(b2);
        frame.add(back);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b1.addActionListener(e -> {
            AdManageStaff.manageStaffe();
        });
        b2.addActionListener(e -> {
            AdManageRooms.menuRooms();
        });
        back.addActionListener(e -> {
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

    public static void Diag(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}


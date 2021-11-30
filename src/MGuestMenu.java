import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


public class MGuestMenu extends JFrame {
    private static FileManager l = new FileManager();
    private JTextArea printOutArea;
    private JPanel panelImg;
    private JTextField newGuestSurname;
    private JPasswordField newGuestPassword;
    private JButton AddGuest;
    private JButton goBack;
    private JScrollPane contentPane;
    private JTextField newGuestLogin;
    private JTextField newGuestName;
    private JTextField newGuestID;
    private JTextField editGuestID;
    private JTextField editGuestName;
    private JTextField editGuestSurname;
    private JTextField editGuestLogin;
    private JButton editGuestButton;
    private JButton deleteGuestButton;
    private JTextField delGuestID;
    private JTextField delGuestName;
    private JTextField delGuestSurname;
    private JTextField delGuestLogin;
    private JPasswordField editGuestPassword;
    private JPasswordField delGuestPassword;
    private JButton button1;

    public MGuestMenu() {
        goBack.addActionListener(e -> {UserMenu.MainMenu();this.dispose(); });
        this.setContentPane(panelImg);
        this.setSize(1280, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        AddGuest.addActionListener(e -> {
            Guest newG = new Guest();
            newG.setLogin(newGuestLogin.getText());
            newG.setPassword(newGuestPassword.toString());
            newG.setUserID(l.seeUsers().size()+1);
            newG.setName(newGuestName.getText());
            newG.setSurname(newGuestSurname.getText());
            l.addUser(newG);
            GUI.Diag("New gest Created: \n"+"name: "+newGuestName.getText()+" surname: "+newGuestSurname.getText()+"\n"+"login: "+newGuestLogin.getText()+"\n"+"password: "+newGuestPassword.getText());
            System.out.print("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n");
            for (Guest g:l.seeUsers()
            ) {
                System.out.println(g.toString());
            }
            this.setVisible(true);
            System.out.print("\n"+"\n"+"\n"+"\n"+"\n"+"\n");
            newGuestID.setText("");
            newGuestName.setText("");
            newGuestSurname.setText("");
            newGuestPassword.setText("");
            newGuestLogin.setText("");
        });

        editGuestButton.addActionListener(e -> {
            Guest newG = new Guest();
            editGuestLogin.setText(editGuestName.getText()+"."+editGuestSurname.getText());
            newG.setPassword(editGuestPassword.toString());
            newG.setName(editGuestName.getText());
            newG.setSurname(editGuestSurname.getText());
            newG.setUserID(Integer.parseInt(editGuestID.getText()));

            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to edit? "+newG.toString());
            switch (result) {
                case JOptionPane.YES_OPTION:
                    l.deleteUser(newG);
                    l.addUser(newG);
                    System.out.print("\n"+"\n"+"\n"+"\n"+"\n"+"\n");
                    GUI.Diag("Guest Edited");
                    for (Guest g:l.seeUsers()
                    ) {
                        System.out.println(g.toString());
                    }
                    System.out.print("\n"+"\n"+"\n"+"\n"+"\n"+"\n");
                    break;
                case JOptionPane.NO_OPTION:

                    break;
                case JOptionPane.CANCEL_OPTION:

                    break;
                case JOptionPane.CLOSED_OPTION:
                    break;
            }
            editGuestID.setText("");
            editGuestName.setText("");
            editGuestSurname.setText("");
            editGuestPassword.setText("");
            editGuestLogin.setText("");
        });

        deleteGuestButton.addActionListener(e -> {
            Guest newG = new Guest();
            newG.setLogin(delGuestLogin.getText());
            newG.setPassword(delGuestPassword.toString());
            newG.setName(delGuestName.getText());
            newG.setSurname(delGuestSurname.getText());
            newG.setUserID(Integer.parseInt(delGuestID.getText()));
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete? "+newG.toString());
            switch (result) {
                case JOptionPane.YES_OPTION:
                    l.deleteUser(newG);
                    GUI.Diag("Deleted Guest: " + newG.toString());
                    System.out.print("\n"+"\n"+"\n"+"\n"+"\n"+"\n");
                    for (Guest g:l.seeUsers()
                    ) {
                        System.out.println(g.toString());
                    }
                    System.out.print("\n"+"\n"+"\n"+"\n"+"\n"+"\n");
                    break;
                case JOptionPane.NO_OPTION:

                    break;
                case JOptionPane.CANCEL_OPTION:

                    break;
                case JOptionPane.CLOSED_OPTION:
                    break;
            }
            delGuestID.setText("");
            delGuestName.setText("");
            delGuestSurname.setText("");
            delGuestPassword.setText("");
            delGuestLogin.setText("");
        });

        delGuestID.addActionListener(e -> {
            Guest editguest = new Guest();
            for (Guest g:l.seeUsers()
            ) {
                if (g.getUserID()==Integer.parseInt(delGuestID.getText())) {
                    editguest=g;
                }
            }
            delGuestName.setText(editguest.getName());
            delGuestSurname.setText(editguest.getSurname());
            delGuestLogin.setText(editguest.getLogin());
        });

        newGuestName.addActionListener(e -> {
            newGuestID.setText(String.valueOf(l.seeUsers().size()+1));
        });

        newGuestName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                newGuestID.setText(String.valueOf(l.seeUsers().size()+1));
            }
        });
        newGuestPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                newGuestLogin.setText(newGuestName.getText()+"."+newGuestSurname.getText());
            }
        });


        editGuestID.addActionListener(e -> {
            Guest editguest = new Guest();
            for (Guest g:l.seeUsers()
                 ) {
                if (g.getUserID()==Integer.parseInt(editGuestID.getText())) {
                    editguest=g;
                }
            }
            editGuestID.getText();

        });

        editGuestName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Guest editguest = new Guest();
                for (Guest g:l.seeUsers()
                ) {
                    if (g.getUserID()==Integer.parseInt(editGuestID.getText())) {
                        editguest=g;
                    }
                }
                editGuestName.setText(editguest.getName());
                editGuestSurname.setText(editguest.getSurname());
                editGuestLogin.setText(editguest.getLogin());
            }
        });
        delGuestID.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                Guest editguest = new Guest();
                for (Guest g:l.seeUsers()
                ) {
                    if (g.getUserID()==Integer.parseInt(delGuestID.getText())) {
                        editguest=g;
                    }
                }
                delGuestName.setText(editguest.getName());
                delGuestSurname.setText(editguest.getSurname());
                delGuestLogin.setText(editguest.getLogin());
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });

        newGuestPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGuestLogin.setText(newGuestName.getText()+"."+newGuestSurname.getText());
            }
        });
        editGuestPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editGuestLogin.setText(editGuestName.getText()+"."+editGuestSurname.getText());
            }
        });
        delGuestPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delGuestLogin.setText(delGuestName.getText()+"."+delGuestSurname.getText());
            }
        });
    }

    public String printRooms() {
        String data = "";
        for (int i = 0; i < FileManager.getStaff().size(); i++) {
            data = data + FileManager.getStaff().get(i).toString() + "\n";
        }
        return data;
    }

    public static void menuGuest() {

        JFrame m = new MGuestMenu();
        m.setVisible(true);
        for (Guest g:l.seeUsers()
             ) {
            System.out.println(g.toString());
        }

    }

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


        printOutArea.setEditable(false);
        JTextAreaOutputStream out = new JTextAreaOutputStream(printOutArea);
        System.setOut(new PrintStream(out));

        //printOutArea.setText(Print.testPrintRooms());

    }

    public static void main(String[] args) {
        menuGuest();
    }

}

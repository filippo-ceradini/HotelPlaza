import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


public class ManageRooms extends JFrame{
    private JTextArea printOutArea;
    private JPanel panelImg;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JButton editButton;
    private JButton goBack;
    private JScrollPane contentPane;
    private JTextField textField1;

    public ManageRooms() {
        goBack.addActionListener(e ->this.dispose());
        editButton.addActionListener(e -> {
            Print.test();
        });
        this.setContentPane(panelImg);
        this.setSize(1280, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public String printRooms(){
        String data="";
        for (int i = 0; i < FileManager.getStaff().size(); i++) {
            data=data+FileManager.getStaff().get(i).toString()+"\n";
        }
        return data;
    }

    public static void menuRooms(){

        JFrame m =new ManageRooms();
        m.setVisible(true);

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


        printOutArea.setEditable (false);
        JTextAreaOutputStream out = new JTextAreaOutputStream (printOutArea);
        System.setOut (new PrintStream(out));

        //printOutArea.setText(Print.testPrintRooms());

    }





}

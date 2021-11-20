import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ManageStaff extends JFrame {
    private JButton goBack;
    private JButton viewDataButton;
    private JButton update;
    private JPanel mngPanel;


    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JCheckBox checkBox5;
    private JCheckBox checkBox6;
    private JCheckBox checkBox7;
    private JCheckBox checkBox8;
    private JCheckBox checkBox9;


    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JTextField textField18;
    private JTextField textField19;
    private JTextField textField20;
    private JTextField textField21;
    private JTextField textField22;
    private JTextField textField23;
    private JTextField textField24;
    private JTextField textField25;
    private JTextField textField26;
    private JTextField textField27;
    private JTextField textField28;
    private JTextField textField29;
    private JTextField textField30;
    private JTextField textField31;
    private JTextField textField32;
    private JTextField textField33;
    private JTextField textField34;
    private JTextField textField35;
    private JTextField textField36;


    private final JTextField[] names = {textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8, textField9};
    private final JTextField[] surnames = {textField10, textField11, textField12, textField13, textField14, textField15, textField16, textField17, textField18};
    private final JTextField[] phones = {textField19, textField20, textField21, textField22, textField23, textField24, textField25, textField26, textField27};
    private final JTextField[] salaries = {textField28, textField29, textField30, textField31, textField32, textField33, textField34, textField35, textField36};
    private final JCheckBox[] checkBxes = {checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9};

    public ManageStaff() {

        goBack.addActionListener(e -> this.dispose());

        update.addActionListener(e -> {
            ArrayList<Staff> toWrite = FileManager.getStaff();
            int cnt = 0;
            for (int i = 0; i < 9; i++) {
                if (checkBxes[i].isSelected()) {
                    System.out.println("box" + i + " selezionato");
                    Staff person = new Staff(names[i].getText(), surnames[i].getText(), phones[i].getText(), salaries[i].getText());
                    try {
                        toWrite.set(i, person);
                    } catch (IndexOutOfBoundsException ex) {
                        ex.printStackTrace();
                    }

                    cnt++;
                }

            }
            if (cnt > 0) {FileManager.editStaff(toWrite);}
        });

        viewDataButton.addActionListener(e -> viewData());

        ActionListener listener = e -> {

        };
        checkBox1.addActionListener(listener);
        checkBox2.addActionListener(listener);
        checkBox3.addActionListener(listener);
        checkBox4.addActionListener(listener);
        checkBox5.addActionListener(listener);
        checkBox6.addActionListener(listener);
        checkBox7.addActionListener(listener);
        checkBox8.addActionListener(listener);
        checkBox9.addActionListener(listener);

        this.setContentPane(mngPanel);
        this.setSize(1280, 800);
        this.setResizable(true);
        this.setLocationRelativeTo(null);

    }


    private void viewData() {
        for (int i = 0; i < 9; i++) {
            names[i].setText(FileManager.getStaff().get(i).getName());
            surnames[i].setText(FileManager.getStaff().get(i).getSurname());
            phones[i].setText(FileManager.getStaff().get(i).getPhone());
            salaries[i].setText(FileManager.getStaff().get(i).getSalary());
        }

    }

    public static void manageStaffe(){
        JFrame f = new ManageStaff();
        f.setVisible(true);
    }

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        final BufferedImage image = ImageIO.read(new File("background.jpg"));
        mngPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
    }
}

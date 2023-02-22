import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;


class Login {
    public static void login() {
        // Main Frame
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Get screen size
        frame.setSize(dim.width, dim.height);
        frame.setLocationRelativeTo(null);

        Image img = Toolkit.getDefaultToolkit().getImage("src/img/login.jpg"); // Get image
        frame.setContentPane(new JLabel(new ImageIcon(img))); // Set image to frame
        JPanel panel = new JPanel();
        panel.setSize(320, 320);
        panel.setLocation(dim.width / 2 - panel.getWidth() / 2, dim.height / 2 - panel.getHeight() / 2); // Set location
        
       // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setSize(dim.width, 60);
        titlePanel.setLocation(0, 0);
        Color titleColor = new Color(57, 53, 61);
        titlePanel.setBackground(titleColor);
        JLabel title = new JLabel("Login Page");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setVerticalAlignment(JLabel.CENTER);
        titlePanel.add(title);
        frame.add(titlePanel);


        // Login Panel
        JPanel panel1 = new JPanel();
        panel1.setSize(130,130);
        panel1.setLocation(100,0);
        JLabel label1 = new JLabel();
        label1.setBounds(90, 10, 130, 130);
        label1.setBackground(Color.white);
        BufferedImage img1 = null; // Create buffered image
        try {
            img1 = ImageIO.read(new File("src/img/logo.png")); // Read image
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img1.getScaledInstance(120, 120,Image.SCALE_SMOOTH);   // Resize image
        ImageIcon img2= new ImageIcon(dimg); // Create image icon
        label1.setIcon(img2); // Set image icon to label
        Color color = new Color(119, 94, 138); // Set color
        panel.setBackground(color); 
        panel.add(panel1);
        panel1.add(label1);

        // Username Panel
        JPanel panelU = new JPanel();
        panelU.setSize(115,35);
        panelU.setLocation(10,150);
        JLabel label = new JLabel();
        label.setSize(130,35);
        label.setLocation(10,180);
        BufferedImage img3 = null; // Create buffered image for username
        try {
            img3 = ImageIO.read(new File("src/img/user.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg1 = img3.getScaledInstance(25, 25,Image.SCALE_SMOOTH);   // Resize image
        ImageIcon img4= new ImageIcon(dimg1);
        label.setIcon(img4);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setText("Username");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panelU.add(label);
        panel.add(panelU);

        // Username Text Field
        JTextField textField = new JTextField(20);
        textField.setBounds(140, 150, 160, 35);
        panel.add(textField);
        
        // Password Panel
        JPanel panelP = new JPanel();
        panelP.setSize(115,35);
        panelP.setLocation(10,200);
        JLabel label2 = new JLabel();
        label2.setSize(130,35);
        label2.setLocation(10,230);
        BufferedImage img5 = null; // Create buffered image for username
        try {
            img5 = ImageIO.read(new File("src/img/padlock.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg2 = img5.getScaledInstance(25, 25,Image.SCALE_SMOOTH);   // Resize image
        ImageIcon img6= new ImageIcon(dimg2);
        label2.setIcon(img6);
        label2.setText("Password");
        label2.setHorizontalAlignment(JLabel.LEFT);
        label2.setFont(new Font("Arial", Font.BOLD, 16));
        panelP.add(label2);
        panel.add(panelP);

        // Password Text Field
        JPasswordField passwordField = new JPasswordField(20); // Create password field
        passwordField.setBounds(140, 200, 160, 35); // Set bounds
        panel.add(passwordField);

        JButton button = new JButton("Login");
        button.setBounds(10, 260, 100, 30);
        button.addActionListener(e -> {
            String user = textField.getText();
            String pass = passwordField.getText();
            if (user.equals("admin") && pass.equals("admin")) {
                JOptionPane.showMessageDialog(null, "Login Successful");
                routine.Myroutine();
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed");
            }
        });
        panel.add(button);
        frame.add(panel);
        JButton button2 = new JButton("Cancel");
        button2.setBounds(200, 260, 100, 30);
        panel.add(button2);
        panel.setLayout(null);
        panel.setVisible(true);
        frame.setVisible(true);
        frame.setLayout(null);
    }

}

public class App {
    public static void main(String[] args) {
        Login.login();
        MysqlConnectionJDBC.CreateTable();
    }
}

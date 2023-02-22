import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.*;
// import java.util.*;
import java.text.SimpleDateFormat;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;


class routine {
    static String R1 = "";
    static int R2 = 0;
    static JTextField t1, t2, t3, t4, t5, t6, t7;

    public static void Myroutine() {
        JFrame f = new JFrame("My Routine");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Get screen size
        f.setSize(dim.width, dim.height);
        f.setLocationRelativeTo(null);
        BufferedImage img1 = null; // Create buffered image
        try {
            img1 = ImageIO.read(new File("src/img/daily.jpg")); // Read image
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img1.getScaledInstance(dim.width, dim.height,Image.SCALE_SMOOTH);
        f.setContentPane(new JLabel(new ImageIcon(dimg))); // Set image to frame

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setSize(dim.width, 60);
        titlePanel.setLocation(0, 0);
        Color titleColor = new Color(57, 53, 61);
        titlePanel.setBackground(titleColor);
        JLabel title = new JLabel("MY ROUTINE");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setVerticalAlignment(JLabel.CENTER);
        titlePanel.add(title);
        f.add(titlePanel);


        JPanel p = new JPanel();
        p.setVisible(true);
        p.setSize(550, 330);
        Color color = new Color(86, 76, 97);
        p.setBackground(color);
        p.setLocation(dim.width/2-p.getWidth()/2, dim.height/2-p.getHeight()/2);
        p.setLayout(null);

        Color color1 = new Color(57, 53, 61);
        JLabel l = new JLabel("Date");
        l.setBounds(10, 10, 80, 25);
        l.setBackground(color1);
        l.setForeground(Color.WHITE);
        // l.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel l2 = new JLabel("Time");
        l2.setBounds(10, 40, 80, 25);
        JLabel l3 = new JLabel("Note");
        l3.setBounds(10, 70, 80, 25);
        JLabel l4 = new JLabel("Topic of Study");
        l4.setBounds(250, 10, 120, 25);
        JLabel l5 = new JLabel("How Many Hours");
        l5.setBounds(250, 40, 120, 25);
        JLabel l6 = new JLabel("Remarks");
        l6.setBounds(250, 70, 120, 25);
        JLabel l7 = new JLabel("XYZ");
        p.add(l);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        p.add(l5);
        p.add(l6);
        p.add(l7);

        // // Real Time Date and Time
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        // LocalDateTime now = LocalDateTime.now();
        // String date = sdf.format(now);
        // String time = sdf2.format(now);
        // System.out.println(date);

        JTextField t1 = new JTextField();
        t1.setBounds(70, 10, 160, 25);
        JTextField t2 = new JTextField();
        t2.setBounds(70, 40, 160, 25);
        JTextField t3 = new JTextField();
        t3.setBounds(70, 70, 160, 25);
        JTextField t4 = new JTextField();
        t4.setBounds(370, 10, 160, 25);
        JTextField t5 = new JTextField();
        t5.setBounds(370, 40, 160, 25);
        JTextField t6 = new JTextField();
        t6.setBounds(370, 70, 160, 25);
        JTextField t7 = new JTextField();
        p.add(t1);
        p.add(t2);
        p.add(t3);
        p.add(t4);
        p.add(t5);
        p.add(t6);
        p.add(t7);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("Date");
        model.addColumn("Time");
        model.addColumn("Note");
        model.addColumn("Topic of Study");
        model.addColumn("How Many Hours");
        model.addColumn("Remarks");
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        // table.setBounds(10, 150, 200, 200);
        scrollPane.setBounds(10, 120, 400, 200);
        p.add(scrollPane);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() { // Add listener to table
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() >= 0) {
                    int row = table.getSelectedRow();
                    R1 = (String) table.getValueAt(row, 0).toString();
                    t1.setText(table.getValueAt(row, 0).toString());
                    t2.setText(table.getValueAt(row, 1).toString());
                    t3.setText(table.getValueAt(row, 2).toString());
                    t4.setText(table.getValueAt(row, 3).toString());
                    t5.setText(table.getValueAt(row, 4).toString());
                    t6.setText(table.getValueAt(row, 5).toString());
                }
            }
        });

        JButton b1 = new JButton("Add");
        b1.setBounds(440, 120, 80, 25);
        b1.addActionListener(e -> {

            String s1 = t1.getText();
            String s2 = t2.getText();
            String s3 = t3.getText();
            String s4 = t4.getText();
            String s5 = t5.getText();
            String s6 = t6.getText();
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            t6.setText("");

            MysqlConnectionJDBC.AddElement(s1, s2, s3, s4, s5, s6);
            model.addRow(new Object[] { s1, s2, s3, s4, s5, s6 });

        });
        JButton b2 = new JButton("Search");
        b2.setBounds(440, 150, 80, 25);
        b2.addActionListener(e -> {
            String s1 = t1.getText();
            String s2 = t2.getText();
            String s3 = t3.getText();
            String s4 = t4.getText();
            String s5 = t5.getText();
            String s6 = t6.getText();
            ResultSet rs = MysqlConnectionJDBC.Search(s1, s2, s3, s4, s5, s6);
            if (model.getRowCount() > 0) {
                for (int i = model.getRowCount() - 1; i >=0; i--) {
                    System.out.println("Row " + model.getRowCount() + " is removed");
                    model.removeRow(i);
                }
            }
            try {
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");
                while (rs.next()) {
                    model.addRow(new Object[] { rs.getString("Date"), rs.getString("Time"), rs.getString("Note"),
                            rs.getString("Topic_of_Study"), rs.getString("How_Many_Hours"), rs.getString("Remarks") });
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1);
            }
        });

        JButton b3 = new JButton("Delete");
        b3.setBounds(440, 180, 80, 25);
        b3.addActionListener(e -> {
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            t6.setText("");
            MysqlConnectionJDBC.Delete(R1);
        });

        JButton b4 = new JButton("View All");
        b4.setBounds(440, 210, 80, 25);
        b4.addActionListener(e -> {
            // l1.clear();
            ResultSet rs = MysqlConnectionJDBC.ViewAll();
            if (model.getRowCount() > 0) {
                for (int i = model.getRowCount() - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
            }
            try {
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");
                while (rs.next()) {
                    model.addRow(new Object[] { rs.getString("Date"), rs.getString("Time"), rs.getString("Note"),
                            rs.getString("Topic_of_Study"), rs.getString("How_Many_Hours"), rs.getString("Remarks") });
                }

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1);
            }
        });
        JButton b5 = new JButton("Close");
        b5.setBounds(440, 240, 80, 25);
        b5.addActionListener(e -> {
            f.dispose();
        });
        JButton b6 = new JButton("");
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(b5);
        p.add(b6);

        
        f.setVisible(true);
        f.add(p);
        f.setLayout(null);

    }
}

public class MyRoutine {
    public static void main(String[] args) {
        // TODO code application logic here
        routine.Myroutine();

    }
}

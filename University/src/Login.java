import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import net.miginfocom.swing.*;

public class Login {

    Login() {
        initComponents();
        this2.setVisible(true);
    }

    private int displayUsers(String f, String s, int rolid) throws SQLException {

        String jdbcURL = "jdbc:sqlserver://localhost:1433;database=University;integratedSecurity=true";
        String username = "admin";
        String password = "1q2w3e";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int ok = 0;

        connection = DriverManager.getConnection(jdbcURL, username, password);
        pstmt = connection.prepareStatement("select rolid,username,password from dbo.[User] ");
        resultSet = pstmt.executeQuery();

        int rol = -1;

        while (resultSet.next()) {
            String userna = resultSet.getString("username");
            String pw = resultSet.getString("password");


            if (f.equals(userna) && s.equals(pw)) {
                rol = resultSet.getInt("rolid");
                ok = 1;
            }
        }

        if (ok == 0) {
            JOptionPane.showMessageDialog(null, "Error");
        } else if (ok == 1) {
            //JOptionPane.showMessageDialog(null, "Logged in succesfully");
            if(txt_combo.getSelectedItem()=="Admin"&&rol==0)
            {
                AdminPanel adm=new AdminPanel();
            }
            else if(txt_combo.getSelectedItem()=="Profesor"&&rol==1)
            {
                //profpanel
                ProfessorPanel prof=new ProfessorPanel();
            }
            else if(txt_combo.getSelectedItem()=="Student"&&rol==2)
            {
                StudentPanel student=new StudentPanel();
            }
            else
                JOptionPane.showMessageDialog(null, "Date de logare gresite");
        }
        resultSet.close();
        pstmt.close();
        connection.close();
        return 1;
    }

    private void jButton1ActionPerformed(ActionEvent e) throws SQLException {
        // TODO add your code here
        String passText = new String(txt_password.getPassword());

        int rolid = -1;
        if (txt_combo.getSelectedItem() == "Student") {
            rolid = 2;
        } else if (txt_combo.getSelectedItem() == "Profesor") {
            rolid = 1;
        } else if (txt_combo.getSelectedItem() == "Admin") {
            rolid = 0;
        }
        displayUsers(this.txt_username.getText(), passText, rolid);
    }

    private void jButton3(ActionEvent e) {
        // TODO add your code here
        RegisterForm register=new RegisterForm();
    }

    private void register(ActionEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        this2 = new JFrame();
        jPanel1 = new JPanel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jButton3 = new JButton();
        jButton1 = new JButton();
        txt_username = new JTextField();
        txt_password = new JPasswordField();
        txt_combo = new JComboBox<>();
        jLabel4 = new JLabel();
        label1 = new JLabel();

        //======== this2 ========
        {
            this2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this2.setResizable(false);
            this2.setIconImage(new ImageIcon(getClass().getResource("/Images/unive.jpg")).getImage());
            var this2ContentPane = this2.getContentPane();

            //======== jPanel1 ========
            {
                jPanel1.setLayout(null);

                //---- jLabel2 ----
                jLabel2.setText("Username :");
                jLabel2.setBackground(Color.white);
                jPanel1.add(jLabel2);
                jLabel2.setBounds(20, 280, 70, jLabel2.getPreferredSize().height);

                //---- jLabel3 ----
                jLabel3.setText("Password :");
                jPanel1.add(jLabel3);
                jLabel3.setBounds(20, 315, 65, jLabel3.getPreferredSize().height);

                //---- jButton3 ----
                jButton3.setText("Inregistreaza-te");
                jButton3.setFont(jButton3.getFont().deriveFont(jButton3.getFont().getSize() - 1f));
                jButton3.addActionListener(e -> {
                    jButton3(e);

                });
                jPanel1.add(jButton3);
                jButton3.setBounds(195, 385, 130, 30);

                //---- jButton1 ----
                jButton1.setText("Logheaza-te");
                jButton1.setFont(jButton1.getFont().deriveFont(jButton1.getFont().getSize() - 2f));
                jButton1.addActionListener(e -> {
                    try {
                        jButton1ActionPerformed(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                jPanel1.add(jButton1);
                jButton1.setBounds(30, 385, 130, 30);
                jPanel1.add(txt_username);
                txt_username.setBounds(90, 270, 160, 30);
                jPanel1.add(txt_password);
                txt_password.setBounds(90, 310, 160, 30);

                //---- txt_combo ----
                txt_combo.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Admin",
                    "Student",
                    "Profesor"
                }));
                jPanel1.add(txt_combo);
                txt_combo.setBounds(90, 350, 160, 30);

                //---- jLabel4 ----
                jLabel4.setText("State: ");
                jPanel1.add(jLabel4);
                jLabel4.setBounds(20, 355, 50, jLabel4.getPreferredSize().height);

                //---- label1 ----
                label1.setIcon(new ImageIcon(getClass().getResource("/Images/bk4.jpg")));
                jPanel1.add(label1);
                label1.setBounds(0, 0, 660, 435);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < jPanel1.getComponentCount(); i++) {
                        Rectangle bounds = jPanel1.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = jPanel1.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    jPanel1.setMinimumSize(preferredSize);
                    jPanel1.setPreferredSize(preferredSize);
                }
            }

            GroupLayout this2ContentPaneLayout = new GroupLayout(this2ContentPane);
            this2ContentPane.setLayout(this2ContentPaneLayout);
            this2ContentPaneLayout.setHorizontalGroup(
                this2ContentPaneLayout.createParallelGroup()
                    .addGroup(this2ContentPaneLayout.createSequentialGroup()
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 657, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
            );
            this2ContentPaneLayout.setVerticalGroup(
                this2ContentPaneLayout.createParallelGroup()
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
            );
            this2.pack();
            this2.setLocationRelativeTo(this2.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame this2;
    private JPanel jPanel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JButton jButton3;
    private JButton jButton1;
    private JTextField txt_username;
    private JPasswordField txt_password;
    private JComboBox<String> txt_combo;
    private JLabel jLabel4;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

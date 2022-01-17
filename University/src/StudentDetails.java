import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Sun Dec 19 15:23:25 EET 2021
 */


/**
 * @author unknown
 */
public class StudentDetails {
    private int rolid;
    private String firstname, lastname, group, Email, CNP;
    private Boolean Stat;

    public StudentDetails(String firstname, String lastname, String group, String email, String cnp, Boolean status) {
        this.Stat = status;
        this.firstname = firstname;
        this.lastname = lastname;
        this.group = group;
        this.Email = email;
        this.CNP = cnp;
        initComponents();
        this2.setVisible(true);

    }

    public StudentDetails(String firstname, String lastname, String group, String email, Boolean status, int rol) {
        this.Stat = status;
        this.firstname = firstname;
        this.lastname = lastname;
        this.group = group;
        this.Email = email;
        this.rolid = rol;

        initComponents();
        this2.setVisible(true);
        if (rolid == 2) {
            this.cnp.setText("Subject");
            this.grupa.setVisible(false);
            this.campgrupa.setVisible(false);
        }

    }

    public DefaultTableModel getDlm() {
        return dlm;
    }

    public void setDlm(DefaultTableModel dlm) {
        this.dlm = dlm;
    }

    private DefaultTableModel dlm = new DefaultTableModel();


    private void update(ActionEvent e) throws SQLException {
        // TODO add your code here

        String jdbcURL = "jdbc:sqlserver://localhost:1433;database=University;integratedSecurity=true";
        String username = "admin";
        String password = "1q2w3e";
        Connection connection = null;
        PreparedStatement pstmt = null;

        int changed = 0;
        connection = DriverManager.getConnection(jdbcURL, username, password);

        if (!this.campnume.getText().isEmpty()) {
            pstmt = connection.prepareStatement("update Student set FirstName='" + campnume.getText() + "' where" +
                    " FirstName='" + this.firstname + "'");
            pstmt.executeUpdate();
            changed = 1;
        }
        if (!this.campprenume.getText().isEmpty()) {
            pstmt = connection.prepareStatement("update Student set LastName='" + campprenume.getText() + "' where" +
                    " LastName='" + this.lastname + "'");
            pstmt.executeUpdate();
            changed = 1;

        }
        if (!this.campcnp.getText().isEmpty()) {
            pstmt = connection.prepareStatement("update Student set cnp='" + campcnp.getText() + "' where" +
                    " cnp='" + this.CNP + "'");
            pstmt.executeUpdate();
            changed = 1;
        }
        if (!this.campemail.getText().isEmpty()) {
            pstmt = connection.prepareStatement("update Student set email='" + campemail.getText() + "' where" +
                    " email='" + this.Email + "'");
            pstmt.executeUpdate();
            changed = 1;
        }
        if (!this.campgrupa.getText().isEmpty()) {
            pstmt = connection.prepareStatement("update Student set grupa='" + campgrupa.getText() + "' where" +
                    " grupa='" + this.group + "'");
            pstmt.executeUpdate();
            changed = 1;
        }
        if (this.comboBox1.getSelectedItem() == "Activ") {
            if (campnume.getText().isEmpty()) {
                pstmt = connection.prepareStatement("update Student set status='" + 1 + "' where" +
                        " FirstName='" + this.firstname + "'");
                pstmt.executeUpdate();
                changed=1;
            } else {
                pstmt = connection.prepareStatement("update Student set status='" + 1 + "' where" +
                        " FirstName='" + campnume.getText() + "'");
                pstmt.executeUpdate();
                changed=1;
            }
        }

        if (this.comboBox1.getSelectedItem() == "Inactiv") {
            if (campnume.getText().isEmpty()) {
                pstmt = connection.prepareStatement("update Student set status='" + 0 + "' where" +
                        " FirstName='" + this.firstname + "'");
                pstmt.executeUpdate();
                changed = 1;
            }
            else {
                pstmt = connection.prepareStatement("update Student set status='" + 0 + "' where" +
                        " FirstName='" + campnume.getText() + "'");
                pstmt.executeUpdate();
                changed=1;
            }
        }
        if (changed == 1)
            JOptionPane.showMessageDialog(null, "Edits made succesfully");
        else
            JOptionPane.showMessageDialog(null, "No edits made");
    }

    private void campnume(ActionEvent e) {
        // TODO add your code here
    }

    private void button5(ActionEvent e) {
        // TODO add your code here
        this2.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        this2 = new JFrame();
        panel1 = new JPanel();
        nume = new JLabel();
        prenume = new JLabel();
        cnp = new JLabel();
        email = new JLabel();
        grupa = new JLabel();
        status = new JLabel();
        campnume = new JTextField();
        campprenume = new JTextField();
        campcnp = new JTextField();
        campemail = new JTextField();
        campgrupa = new JTextField();
        comboBox1 = new JComboBox<>();
        button5 = new JButton();
        button1 = new JButton();

        //======== this2 ========
        {
            var this2ContentPane = this2.getContentPane();

            //======== panel1 ========
            {
                panel1.setBorder(new TitledBorder(null, "Date student", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

                //---- nume ----
                nume.setText("Nume:");

                //---- prenume ----
                prenume.setText("Prenume:");

                //---- cnp ----
                cnp.setText("CNP:");

                //---- email ----
                email.setText("E-mail:");

                //---- grupa ----
                grupa.setText("Grupa:");

                //---- status ----
                status.setText("Status:");

                //---- campnume ----
                campnume.addActionListener(e -> campnume(e));

                //---- comboBox1 ----
                comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Activ",
                    "Inactiv"
                }));

                //---- button5 ----
                button5.setText("Iesire");
                button5.setIcon(new ImageIcon(getClass().getResource("/Images/logout.png")));
                button5.addActionListener(e -> button5(e));

                //---- button1 ----
                button1.setText("Update");
                button1.setIcon(new ImageIcon(getClass().getResource("/Images/update icon.png")));
                button1.addActionListener(e -> update(e));

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(prenume, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(campprenume, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(nume, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(campnume, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(cnp, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(campcnp, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(email, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(campemail, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(status, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                            .addComponent(grupa, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(campgrupa, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(0, 256, Short.MAX_VALUE))))
                        .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                            .addContainerGap(146, Short.MAX_VALUE)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                            .addGap(72, 72, 72)
                            .addComponent(button5, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                            .addGap(148, 148, 148))
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(61, 61, 61)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(nume, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(campnume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(campprenume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(prenume, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(campcnp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(cnp, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(email, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(campemail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(status, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(campgrupa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(grupa, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button5, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                            .addGap(15, 15, 15))
                );
            }

            GroupLayout this2ContentPaneLayout = new GroupLayout(this2ContentPane);
            this2ContentPane.setLayout(this2ContentPaneLayout);
            this2ContentPaneLayout.setHorizontalGroup(
                this2ContentPaneLayout.createParallelGroup()
                    .addGroup(this2ContentPaneLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))
            );
            this2ContentPaneLayout.setVerticalGroup(
                this2ContentPaneLayout.createParallelGroup()
                    .addGroup(this2ContentPaneLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(60, Short.MAX_VALUE))
            );
            this2.pack();
            this2.setLocationRelativeTo(this2.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame this2;
    private JPanel panel1;
    private JLabel nume;
    private JLabel prenume;
    private JLabel cnp;
    private JLabel email;
    private JLabel grupa;
    private JLabel status;
    private JTextField campnume;
    private JTextField campprenume;
    private JTextField campcnp;
    private JTextField campemail;
    private JTextField campgrupa;
    private JComboBox<String> comboBox1;
    private JButton button5;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

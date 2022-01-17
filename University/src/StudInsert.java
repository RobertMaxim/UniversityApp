import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Sun Dec 19 03:12:18 EET 2021
 */


/**
 * @author unknown
 */
public class StudInsert {
    public StudInsert() {
        initComponents();
        this2.setVisible(true);
    }

    private void ButonAdauga(ActionEvent e) throws SQLException, ClassNotFoundException {
        // TODO add your code here
        int invalid = 1;
        Student newStudent = new Student();
        if (!nume.getText().isEmpty()) {
            newStudent.setFirstName(nume.getText());
        } else {
            invalid = 0;
        }
        if (!cnp.getText().isEmpty() && cnp.getText().length() == 13 && invalid == 1)
            newStudent.setCnp(cnp.getText());
        else {
            invalid = 0;
        }
        if (!email.getText().isEmpty() && invalid == 1)
            newStudent.setEmail(email.getText());
        else {
            invalid=0;
        }
        if (!prenume.getText().isEmpty()&&invalid==1) {
            newStudent.setLastName(prenume.getText());
        }
        else
            invalid=0;
        if (!grupa.getText().isEmpty()&&invalid==1){
            newStudent.setGrupa(grupa.getText());}
        else
        {
            invalid=0;
        }
        if (status.getSelectedItem() == "Active") {
            newStudent.setStatus(true);
        } else
            newStudent.setStatus(false);
        if (invalid==1) {
            JOptionPane.showMessageDialog(null, "Success");
            newStudent.StudentInsert();

        }
        else
            JOptionPane.showMessageDialog(null, "Error");


    }

    private void clear(ActionEvent e) {
        // TODO add your code here
        nume.setText("");
        prenume.setText("");
        grupa.setText("");
        email.setText("");
        cnp.setText("");
    }

    private void iesirebuton(ActionEvent e) {
        // TODO add your code here
        this2.dispose();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        this2 = new JFrame();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        nume = new JTextField();
        prenume = new JTextField();
        grupa = new JTextField();
        email = new JTextField();
        cnp = new JTextField();
        status = new JComboBox<>();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this2 ========
        {
            var this2ContentPane = this2.getContentPane();

            //======== panel1 ========
            {
                panel1.setBorder(new TitledBorder(null, "Student Insert", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

                //---- label1 ----
                label1.setText("Nume:");
                label1.setHorizontalAlignment(SwingConstants.CENTER);

                //---- label2 ----
                label2.setText("Prenume:");
                label2.setHorizontalAlignment(SwingConstants.CENTER);

                //---- label3 ----
                label3.setText("Grupa:");
                label3.setHorizontalAlignment(SwingConstants.CENTER);

                //---- label4 ----
                label4.setText("E-mail:");
                label4.setHorizontalAlignment(SwingConstants.CENTER);

                //---- label5 ----
                label5.setText("CNP:");
                label5.setHorizontalAlignment(SwingConstants.CENTER);

                //---- label6 ----
                label6.setText("Status:");
                label6.setHorizontalAlignment(SwingConstants.CENTER);

                //---- status ----
                status.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Active",
                    "Inactive"
                }));

                //---- button1 ----
                button1.setText("Adauga");
                button1.setIcon(new ImageIcon(getClass().getResource("/Images/Save-icon.png")));
                button1.addActionListener(e -> {
                    try {
                        ButonAdauga(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });

                //---- button2 ----
                button2.setText("Renunta");
                button2.setIcon(new ImageIcon(getClass().getResource("/Images/delete_16x16.gif")));
                button2.addActionListener(e -> clear(e));

                //---- button3 ----
                button3.setText("Iesire");
                button3.setIcon(new ImageIcon(getClass().getResource("/Images/erase-128.png")));
                button3.addActionListener(e -> iesirebuton(e));

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cnp, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(nume, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addGroup(panel1Layout.createParallelGroup()
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(grupa, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(prenume, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(email, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
                                            .addGap(78, 78, 78)
                                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))))
                                    .addContainerGap(136, Short.MAX_VALUE))
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                                    .addComponent(button3)
                                    .addGap(31, 31, 31))))
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addComponent(nume))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addComponent(prenume, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addComponent(grupa, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button1))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addComponent(email, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button2))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addComponent(cnp, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(48, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                    .addComponent(button3)
                                    .addGap(24, 24, 24))))
                );
            }

            GroupLayout this2ContentPaneLayout = new GroupLayout(this2ContentPane);
            this2ContentPane.setLayout(this2ContentPaneLayout);
            this2ContentPaneLayout.setHorizontalGroup(
                this2ContentPaneLayout.createParallelGroup()
                    .addGroup(this2ContentPaneLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(19, Short.MAX_VALUE))
            );
            this2ContentPaneLayout.setVerticalGroup(
                this2ContentPaneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, this2ContentPaneLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
            );
            this2.pack();
            this2.setLocationRelativeTo(this2.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame this2;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JTextField nume;
    private JTextField prenume;
    private JTextField grupa;
    private JTextField email;
    private JTextField cnp;
    private JComboBox<String> status;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

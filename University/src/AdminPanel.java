import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.border.*;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class AdminPanel {


    AdminPanel() throws SQLException {
        initComponents();

        this2.setVisible(true);
    }

    private void orar(ActionEvent e) {
        // TODO add your code here
        splitPane1.setRightComponent(panel3);
        panel3.setVisible(true);
        table1.getTableHeader().setReorderingAllowed(false);
        //table1.setModel(dlm1);

    }

    private void examene(ActionEvent e) {
        // TODO add your code here
        splitPane1.setRightComponent(PanelExamene);
        panel3.setVisible(true);
        table3.getTableHeader().setReorderingAllowed(false);

    }

    private void cautare(ActionEvent e) {
        // TODO add your code here
        splitPane1.setRightComponent(panel2);
        panel2.setVisible(true);
        searchList.getTableHeader().setReorderingAllowed(false);
        dlm.setColumnCount(4);
        Vector<String> identifiers = new Vector<>();
        identifiers.add("Nume");
        identifiers.add("Prenume");
        identifiers.add("Grupa");
        identifiers.add("Subject");
        dlm.setColumnIdentifiers(identifiers);
        searchList.setModel(dlm);
    }

    DefaultTableModel dlm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };

    private Vector<String> Search() throws SQLException {
        String jdbcURL = "jdbc:sqlserver://localhost:1433;database=University;integratedSecurity=true";
        String username = "admin";
        String password = "1q2w3e";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int ok = 0;

        connection = DriverManager.getConnection(jdbcURL, username, password);
        pstmt = connection.prepareStatement("select FirstName,LastName,grupa from dbo.[Student]");
        resultSet = pstmt.executeQuery();
        Vector<String> values = new Vector<>();
        String searchword = searchBar.getText();
        dlm.setRowCount(0);
        int i = 0;
        while (resultSet.next()) {
            String firstnameSearch = resultSet.getString("FirstName");
            String LastNameSearch = resultSet.getString("LastName");
            String groupSearch = resultSet.getString("grupa");
            int found = 0;
            if (searchword.equals(firstnameSearch)) {
                values.add(firstnameSearch);
                values.add(LastNameSearch);
                values.add(groupSearch);
                values.add("-");
                found = 1;
            } else if (searchword.equals(LastNameSearch)) {
                values.add(firstnameSearch);
                values.add(LastNameSearch);
                values.add(groupSearch);
                values.add("-");
                found = 1;
            }
            if (found == 1) {
                dlm.insertRow(i++, values);
            }
        }
        pstmt = connection.prepareStatement("select FirstName,LastName,Subject from dbo.[Profesor]");
        resultSet = pstmt.executeQuery();
        Vector<String> values2 = new Vector<>();
        while (resultSet.next()) {
            String firstnameSearch = resultSet.getString("FirstName");
            String LastNameSearch = resultSet.getString("LastName");
            String groupSearch = resultSet.getString("Subject");
            int found = 0;
            if (searchword.equals(firstnameSearch)) {
                values2.add(firstnameSearch);
                values2.add(LastNameSearch);
                values2.add("-");
                values2.add(groupSearch);
                found = 1;
            } else if (searchword.equals(LastNameSearch)) {
                values2.add(firstnameSearch);
                values2.add(LastNameSearch);
                values2.add("-");
                values2.add(groupSearch);
                found = 1;
            }
            if (found == 1)
                dlm.insertRow(dlm.getRowCount(), values2);
        }

        return values;
    }


    private void searchButton(ActionEvent e) throws SQLException {

        Search();
        searchList.setModel(dlm);

        searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void button8(ActionEvent e) {
        // TODO add your code here
    }

    private void delete(ActionEvent e) throws SQLException {
        // TODO add your code here
        if (searchList.isRowSelected(searchList.getSelectedRow())) {

            String jdbcURL = "jdbc:sqlserver://localhost:1433;database=University;integratedSecurity=true";
            String username = "admin";
            String password = "1q2w3e";
            Connection connection = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            int ok = 0;
            int row = searchList.getSelectedRow();
            int column = searchList.getSelectedColumn();

            String nume = (String) searchList.getModel().getValueAt(row, 0);
            String prenume = (String) searchList.getModel().getValueAt(row, 1);
            String grupa = (String) searchList.getModel().getValueAt(row, 2);
            String subject=(String) searchList.getModel().getValueAt(row,3);

            connection = DriverManager.getConnection(jdbcURL, username, password);
            pstmt = connection.prepareStatement("DELETE from dbo.[Student]" +
                    "where LastName='" + prenume + "' and FirstName='" + nume + "' and grupa='" + grupa + "'");
            dlm.removeRow(row);

            pstmt.executeUpdate();

            pstmt = connection.prepareStatement("DELETE from dbo.[Profesor]" +
                    "where LastName='" + prenume + "' and FirstName='" + nume + "' and Subject='" + subject + "'");
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Deletion complete");
        }

    }

    private void edit(ActionEvent e) throws SQLException {
        // TODO add your code here
        if (searchList.isRowSelected(searchList.getSelectedRow())) {
            String jdbcURL = "jdbc:sqlserver://localhost:1433;database=University;integratedSecurity=true";
            String username = "admin";
            String password = "1q2w3e";
            Connection connection = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;

            int row = searchList.getSelectedRow();

            connection = DriverManager.getConnection(jdbcURL, username, password);
            pstmt = connection.prepareStatement("select FirstName,LastName,grupa, cnp, email, status from dbo.[Student]");
            resultSet = pstmt.executeQuery();
            String firstName = (String) searchList.getModel().getValueAt(row, 0);
            String lastName = (String) searchList.getModel().getValueAt(row, 1);
            String group = (String) searchList.getModel().getValueAt(row, 2);

            while (resultSet.next()) {
                String firstnameSearch = resultSet.getString("FirstName");
                String LastNameSearch = resultSet.getString("LastName");
                String groupSearch = resultSet.getString("grupa");

                String CNP = resultSet.getString("cnp");
                String email = resultSet.getString("email");
                Boolean status = resultSet.getBoolean("status");
                if (firstnameSearch.equals(firstName) && LastNameSearch.equals(lastName) && groupSearch.equals(group)) {
                    StudentDetails student = new StudentDetails(firstnameSearch, LastNameSearch, groupSearch, email, CNP, status);
                    searchList.repaint();
                }
            }
            pstmt = connection.prepareStatement("select FirstName,LastName,Subject,Email,Active from dbo.[Profesor]");
            resultSet = pstmt.executeQuery();
            String profFirstName=(String) searchList.getModel().getValueAt(row, 0);
            String proflastName = (String) searchList.getModel().getValueAt(row, 1);
            String subject = (String) searchList.getModel().getValueAt(row, 3);

            while (resultSet.next()) {
                String first = resultSet.getString("FirstName");
                String last = resultSet.getString("LastName");
                String subj = resultSet.getString("Subject");

                String email = resultSet.getString("Email");
                Boolean activity = resultSet.getBoolean("Active");
                if (first.equals(profFirstName) && last.equals(proflastName) && subj.equals(subject)) {
                    StudentDetails student = new StudentDetails(first,last,subj,email,activity,2);
                    searchList.repaint();
                }
            }
        }
    }

    private void refresh(ActionEvent e) throws SQLException {
        // TODO add your code here
        searchList.getTableHeader().setReorderingAllowed(false);
        dlm.setColumnCount(4);
        Vector<String> identifiers = new Vector<>();
        identifiers.add("Nume");
        identifiers.add("Prenume");
        identifiers.add("Grupa");
        identifiers.add("Subject");
        dlm.setColumnIdentifiers(identifiers);
        Search();
        searchList.setModel(dlm);

        searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void iesire(ActionEvent e) {
        // TODO add your code here
        this2.dispose();
    }

    private void cantina(ActionEvent e) {
        // TODO add your code here
        splitPane1.setRightComponent(panelCantina);
        panelCantina.setVisible(true);
        table2.getTableHeader().setReorderingAllowed(false);

    }

    private void button2(ActionEvent e) {
        // TODO add your code here
        splitPane1.setRightComponent(panel4);
        panel4.setVisible(true);

    }

    private void Inregistreaza(ActionEvent e) throws SQLException, ClassNotFoundException {
        // TODO add your code here
        User newUser;
        // if(cpw.getText()==pw.getText())
        int rolid = -1;
        if (comboBox1.getSelectedItem() == "Student")
            rolid = 2;
        else
            rolid = 1;
        newUser = new User(this.usern.getText(), this.pw.getText(), rolid);

        if(nume.getText().isEmpty()||usern.getText().isEmpty()||pw.getText().isEmpty()||
           cpw.getText().isEmpty()||emaill.getText().isEmpty()||cnp.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Complete every field!");
            return;
        }
        if(rolid==2&&grp.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Complete every field!");
            return;
        }

        if(!pw.getText().equals(cpw.getText()))
        {
            JOptionPane.showMessageDialog(null,"Passwords do not match");
            return;
        }

        newUser = new User(this.usern.getText(), this.pw.getText(), rolid);
        String[] numee = nume.getText().split(" ", 2);
        String nume = numee[0];
        String prenume = numee[1];
        int edit=0;
        if (comboBox1.getSelectedItem() == "Student") {
            Student stud = new Student(nume, prenume, this.grp.getText(), this.cnpp.getText(), this.emaill.getText(), true);
            stud.StudentInsert();
            edit=1;
        } else if (comboBox1.getSelectedItem() == "Profesor") {
            Profesor prof = new Profesor(nume, prenume, this.cnpp.getText(), this.emaill.getText(), true);
            prof.ProfInsert();
            edit=1;
        }
        if(edit==1)
            JOptionPane.showMessageDialog(null, "Insertion made succesfully");
        return;
    }

    private void cancel(ActionEvent e) {
        // TODO add your code here
        nume.setText("");
        usern.setText("");
        pw.setText("");
        cpw.setText("");
        cnpp.setText("");
        emaill.setText("");
        grp.setText("");

    }

    private void comboBox1(ActionEvent e) {
        // TODO add your code here
        if (comboBox1.getSelectedItem() == "Student") {
            this.cnp.setText("CNP");
            this.group.setVisible(true);
            this.grp.setVisible(true);
        } else {
            this.cnp.setText("Subject");
            this.group.setVisible(false);
            this.grp.setVisible(false);
        }

    }


    private void initComponents() throws SQLException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        this2 = new JFrame();
        splitPane1 = new JSplitPane();
        panel1 = new JPanel();
        orar = new JButton();
        examene = new JButton();
        cantina = new JButton();
        iesire = new JButton();
        cautare = new JButton();
        button2 = new JButton();
        label1 = new JLabel();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        searchList = new JTable();
        searchBar = new JTextField();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        button1 = new JButton();
        panel3 = new JPanel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        label2 = new JLabel();
        panelCantina = new JPanel();
        scrollPane3 = new JScrollPane();
        table2 = new JTable();
        PanelExamene = new JPanel();
        scrollPane4 = new JScrollPane();
        table3 = new JTable();
        panel4 = new JPanel();
        name = new JLabel();
        username = new JLabel();
        password = new JLabel();
        cfpassword = new JLabel();
        email = new JLabel();
        cnp = new JLabel();
        group = new JLabel();
        nume = new JTextField();
        usern = new JTextField();
        pw = new JTextField();
        cpw = new JTextField();
        emaill = new JTextField();
        cnpp = new JTextField();
        grp = new JTextField();
        button3 = new JButton();
        button4 = new JButton();
        email2 = new JLabel();
        comboBox1 = new JComboBox<>();

        //======== this2 ========
        {
            this2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            this2.setIconImage(new ImageIcon(getClass().getResource("/Images/unive.jpg")).getImage());
            this2.setTitle("University");
            this2.setMinimumSize(new Dimension(500, 470));
            var this2ContentPane = this2.getContentPane();

            //======== splitPane1 ========
            {
                splitPane1.setDividerLocation(210);
                splitPane1.setBorder(LineBorder.createBlackLineBorder());
                splitPane1.setDividerSize(4);
                splitPane1.setPreferredSize(new Dimension(500, 350));
                splitPane1.setMinimumSize(new Dimension(500, 350));

                //======== panel1 ========
                {
                    panel1.setBorder(new EtchedBorder());

                    //---- orar ----
                    orar.setText("Orar");
                    orar.addActionListener(e -> orar(e));

                    //---- examene ----
                    examene.setText("Examene");
                    examene.addActionListener(e -> examene(e));

                    //---- cantina ----
                    cantina.setText("Cantina");
                    cantina.addActionListener(e -> cantina(e));

                    //---- iesire ----
                    iesire.setText("Iesire");
                    iesire.addActionListener(e -> iesire(e));

                    //---- cautare ----
                    cautare.setText("Cautare");
                    cautare.addActionListener(e -> {
			orar(e);
			cautare(e);
		});

                    //---- button2 ----
                    button2.setText("Adauga");
                    button2.addActionListener(e -> button2(e));

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addGroup(panel1Layout.createParallelGroup()
                                                .addComponent(orar, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(examene, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cautare, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 3, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 3, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panel1Layout.createParallelGroup()
                                                .addComponent(cantina, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(iesire, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(button2, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(button2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cautare)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orar)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(examene)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantina)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(iesire)
                                .addContainerGap(91, Short.MAX_VALUE))
                    );
                }
                splitPane1.setLeftComponent(panel1);

                //---- label1 ----
                label1.setIcon(new ImageIcon(getClass().getResource("/Images/bk3.jpg")));
                splitPane1.setRightComponent(label1);
            }

            GroupLayout this2ContentPaneLayout = new GroupLayout(this2ContentPane);
            this2ContentPane.setLayout(this2ContentPaneLayout);
            this2ContentPaneLayout.setHorizontalGroup(
                this2ContentPaneLayout.createParallelGroup()
                    .addComponent(splitPane1, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
            );
            this2ContentPaneLayout.setVerticalGroup(
                this2ContentPaneLayout.createParallelGroup()
                    .addComponent(splitPane1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            this2.pack();
            this2.setLocationRelativeTo(this2.getOwner());
        }

        //======== panel2 ========
        {
            panel2.setBorder(new BevelBorder(BevelBorder.LOWERED));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(searchList);
            }

            //---- searchBar ----
            searchBar.setToolTipText("Search");
            searchBar.setName("Search");

            //---- button6 ----
            button6.setText("Edit");
            button6.setIcon(new ImageIcon(getClass().getResource("/Images/edit.png")));
            button6.addActionListener(e -> {
                try {
                    edit(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            //---- button7 ----
            button7.setText("Delete");
            button7.setIcon(new ImageIcon(getClass().getResource("/Images/delete_16x16.gif")));
            button7.addActionListener(e -> {
                try {
                    delete(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            //---- button8 ----
            button8.setText("Search");
            button8.setIcon(new ImageIcon(getClass().getResource("/Images/Search_Icon_16.png")));
            button8.addActionListener(e -> {
                try {
                    searchButton(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                button8(e);
		});

            //---- button1 ----
            button1.setText("Update");
            button1.setIcon(new ImageIcon(getClass().getResource("/Images/update icon.png")));
            button1.addActionListener(e -> {
                try {
                    refresh(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(scrollPane1)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(searchBar, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(button7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 62, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addContainerGap(164, Short.MAX_VALUE)
                        .addComponent(button1)
                        .addGap(41, 41, 41)
                        .addComponent(button6, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                        .addGap(169, 169, 169))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchBar)
                            .addComponent(button8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11)
                        .addComponent(button7)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button1)
                            .addComponent(button6, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
            );
        }

        //======== panel3 ========
        {

            //======== scrollPane2 ========
            {

                //---- table1 ----
                table1.setModel(new DefaultTableModel(
                    new Object[][] {
                        {"08:00-10:00 LFC", "08:00-10:00 SMC", "14:00-16:00 LFC", "12:00-14:00 MIP", null},
                        {"16:00-18:00 BD", "12:00-16:00 SMC", "18:00-20:00 MIP", "14:00-16:00 AG", null},
                        {null, "16:00-18:00 AG", null, null, null},
                    },
                    new String[] {
                        "Luni", "Marti", "Miercuri", "Joi", "Vineri"
                    }
                ));
                scrollPane2.setViewportView(table1);
            }

            //---- label2 ----
            label2.setText("Orar semestrul I 2021-2022");
            label2.setFont(new Font("Segoe UI", Font.PLAIN, 16));

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                        .addContainerGap(216, Short.MAX_VALUE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183))
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE))
            );
        }

        //======== panelCantina ========
        {

            //======== scrollPane3 ========
            {

                //---- table2 ----
                table2.setModel(new DefaultTableModel(
                    new Object[][] {
                        {"Luni", "Supa crema de legume", "3,60"},
                        {null, "Ciorba radauteana", "5,00"},
                        {null, "Mici", "2,60"},
                        {null, "Piure de cartofi cu unt", "2,60"},
                        {"Marti", "Salata boeuf", "3,40"},
                        {null, "Spaghete milaneze", "4,30"},
                        {null, "Supa de rosii cu taitei", "5,00"},
                        {"Miercuri", "Iahnie de fasole", "2,60"},
                        {null, "Cartofi gratinati cu broccoli", "2,90"},
                        {null, "Gulas cu piept de curcan", "6,40"},
                        {"Joi", "Ostropel pulpa de porc", "3,30"},
                        {null, "Gratar cotlet de porc", "3,90"},
                        {null, "Budinca de vanilie", "1,90"},
                        {"Vineri", "Ciorba de burta", "6,20"},
                        {null, "Sarmale cu mamaliguta", "4,80"},
                        {null, "Musaca de vita", "4,20"},
                    },
                    new String[] {
                        "Ziua", "Meniu", "Pret"
                    }
                ));
                scrollPane3.setViewportView(table2);
            }

            GroupLayout panelCantinaLayout = new GroupLayout(panelCantina);
            panelCantina.setLayout(panelCantinaLayout);
            panelCantinaLayout.setHorizontalGroup(
                panelCantinaLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panelCantinaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                        .addContainerGap())
            );
            panelCantinaLayout.setVerticalGroup(
                panelCantinaLayout.createParallelGroup()
                    .addGroup(panelCantinaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        //======== PanelExamene ========
        {

            //======== scrollPane4 ========
            {

                //---- table3 ----
                table3.setModel(new DefaultTableModel(
                    new Object[][] {
                        {"19.01.2023", "MIP"},
                        {"23.01.2023", "LFC"},
                        {"26.01.2023", "MG"},
                        {"29.01.2023", "SMC"},
                        {"03.02.2023", "AG"},
                        {"09.02.2023", "BD"},
                    },
                    new String[] {
                        "Data", "Materie"
                    }
                ));
                scrollPane4.setViewportView(table3);
            }

            GroupLayout PanelExameneLayout = new GroupLayout(PanelExamene);
            PanelExamene.setLayout(PanelExameneLayout);
            PanelExameneLayout.setHorizontalGroup(
                PanelExameneLayout.createParallelGroup()
                    .addComponent(scrollPane4, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
            );
            PanelExameneLayout.setVerticalGroup(
                PanelExameneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, PanelExameneLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
        }

        //======== panel4 ========
        {
            panel4.setBorder(new TitledBorder("Adauga persoana"));

            //---- name ----
            name.setText("Name:");

            //---- username ----
            username.setText("Username:");

            //---- password ----
            password.setText("Password:");

            //---- cfpassword ----
            cfpassword.setText("Confirm Password:");

            //---- email ----
            email.setText("Email:");

            //---- cnp ----
            cnp.setText("CNP:");

            //---- group ----
            group.setText("Group:");

            //---- button3 ----
            button3.setText("Save");
            button3.addActionListener(e -> {
                try {
                    Inregistreaza(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });

            //---- button4 ----
            button4.setText("Cancel");
            button4.addActionListener(e -> cancel(e));

            //---- email2 ----
            email2.setText("State:");

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "Student",
                "Profesor"
            }));
            comboBox1.addActionListener(e -> comboBox1(e));

            GroupLayout panel4Layout = new GroupLayout(panel4);
            panel4.setLayout(panel4Layout);
            panel4Layout.setHorizontalGroup(
                panel4Layout.createParallelGroup()
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(button3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                        .addComponent(button4)
                        .addGap(111, 111, 111))
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addComponent(email2, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel4Layout.createParallelGroup()
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addComponent(group, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(grp, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panel4Layout.createSequentialGroup()
                                            .addComponent(email, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(emaill, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel4Layout.createSequentialGroup()
                                            .addComponent(cfpassword, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cpw, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel4Layout.createSequentialGroup()
                                            .addComponent(password, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(pw, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel4Layout.createSequentialGroup()
                                            .addComponent(username, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(usern, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel4Layout.createSequentialGroup()
                                            .addComponent(name, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(nume, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panel4Layout.createSequentialGroup()
                                        .addComponent(cnp, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cnpp, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panel4Layout.setVerticalGroup(
                panel4Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(email2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(name, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(nume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel4Layout.createParallelGroup()
                            .addComponent(username, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(usern, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel4Layout.createParallelGroup()
                            .addComponent(password, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(pw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel4Layout.createParallelGroup()
                            .addComponent(cfpassword, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(email, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(emaill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(cnp, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cnpp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(group, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(grp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button3)
                            .addComponent(button4))
                        .addGap(9, 9, 9))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame this2;
    private JSplitPane splitPane1;
    private JPanel panel1;
    private JButton orar;
    private JButton examene;
    private JButton cantina;
    private JButton iesire;
    private JButton cautare;
    private JButton button2;
    private JLabel label1;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTable searchList;
    private JTextField searchBar;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button1;
    private JPanel panel3;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JLabel label2;
    private JPanel panelCantina;
    private JScrollPane scrollPane3;
    private JTable table2;
    private JPanel PanelExamene;
    private JScrollPane scrollPane4;
    private JTable table3;
    private JPanel panel4;
    private JLabel name;
    private JLabel username;
    private JLabel password;
    private JLabel cfpassword;
    private JLabel email;
    private JLabel cnp;
    private JLabel group;
    private JTextField nume;
    private JTextField usern;
    private JTextField pw;
    private JTextField cpw;
    private JTextField emaill;
    private JTextField cnpp;
    private JTextField grp;
    private JButton button3;
    private JButton button4;
    private JLabel email2;
    private JComboBox<String> comboBox1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

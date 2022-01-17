import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.*;
/*
 * Created by JFormDesigner on Wed Jan 12 13:31:45 EET 2022
 */



/**
 * @author Robert Maxim
 */
public class StudentPanel extends JFrame {
    public StudentPanel() {
        initComponents();
        this2.setVisible(true);
    }


    private void button8(ActionEvent e) {
        // TODO add your code here
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


    private void cantina(ActionEvent e) {
        // TODO add your code here
        splitPane1.setRightComponent(panelCantina);
        panelCantina.setVisible(true);
        table2.getTableHeader().setReorderingAllowed(false);

    }

    private void iesire(ActionEvent e) {
        // TODO add your code here
        this2.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        searchList = new JTable();
        searchBar = new JTextField();
        button8 = new JButton();
        this2 = new JFrame();
        splitPane1 = new JSplitPane();
        panel1 = new JPanel();
        orar = new JButton();
        examene = new JButton();
        cantina = new JButton();
        iesire = new JButton();
        cautare = new JButton();
        label1 = new JLabel();
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

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(searchBar, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button8)
                                .addGap(0, 62, Short.MAX_VALUE))
                            .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchBar)
                            .addComponent(button8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(66, Short.MAX_VALUE))
            );
        }

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

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(orar, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                        .addComponent(examene, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                        .addComponent(cautare, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cantina, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                        .addComponent(iesire, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
                                .addContainerGap(9, Short.MAX_VALUE))
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(cautare)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orar)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(examene)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantina)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(iesire)
                                .addContainerGap(121, Short.MAX_VALUE))
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
                )
                    {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            //all cells false
                            return false;
                        }
                    }
                );
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
                ){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        //all cells false
                        return false;
                    }
                });
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
                ){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        //all cells false
                        return false;
                    }
                });
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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTable searchList;
    private JTextField searchBar;
    private JButton button8;
    private JFrame this2;
    private JSplitPane splitPane1;
    private JPanel panel1;
    private JButton orar;
    private JButton examene;
    private JButton cantina;
    private JButton iesire;
    private JButton cautare;
    private JLabel label1;
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

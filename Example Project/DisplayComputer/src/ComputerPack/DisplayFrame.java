/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComputerPack;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.RDFNode;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CCS
 */
public class DisplayFrame extends javax.swing.JFrame {

    public ArrayList<String> ListComponent = new ArrayList<String>(); // for name list
    public ArrayList<String> ListNames = new ArrayList<String>(); // for name list

    /**
     * Creates new form DisplayFrame
     */
    public DisplayFrame() {
        initComponents();

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get Component");
            String queryString;
            queryString = "PREFIX ex:<http://localhost/computer#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>" + "SELECT  (str(?name) as ?Component) "
                    + "where { ?x rdfs:subClassOf ex:ComputerParts." + "?x rdfs:label ?name" + " }";

            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from
                                                                                       // OpenOWL class

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();
                String Component = soln.getLiteral("Component").getString();
                // test --
                System.out.println("Computer " + Component.toString());
                ListComponent.add(Component.toString());

                RDFNode x = soln.get("Propertyval");

                String xx = String.valueOf(x);

                java.nio.ByteBuffer xxx = Charset.forName("UTF-8").encode(xx);

                String xs = xxx.toString();

            }
            ComponentList.removeAllItems(); // combobox nameList
            for (int i = 0; i < ListComponent.size(); i++) {

                ComponentList.addItem(ListComponent.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableComputer = new javax.swing.JTable();
        NameList = new javax.swing.JComboBox<>();
        ComponentList = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        KeywordField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Semantic Search: Computer");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        TableComputer.setModel(
                new javax.swing.table.DefaultTableModel(new Object[][] { { null, null, null }, { null, null, null },
                        { null, null, null }, { null, null, null } }, new String[] { "Computer", "CPU.", "RAM." }));
        jScrollPane1.setViewportView(TableComputer);

        NameList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        NameList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameListActionPerformed(evt);
            }
        });

        ComponentList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ComponentList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComponentListActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Component:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Spec:");

        KeywordField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Keyword:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup().addGap(46, 46, 46).addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(KeywordField, javax.swing.GroupLayout.PREFERRED_SIZE, 544,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18).addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                        .createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(ComponentList, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)
                                                .addComponent(NameList, javax.swing.GroupLayout.PREFERRED_SIZE, 545,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22).addComponent(jButton2,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 177,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(KeywordField, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1).addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ComponentList, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(NameList, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                        .addGap(39, 39, 39).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313,
                                javax.swing.GroupLayout.PREFERRED_SIZE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap().addComponent(jPanel2,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap(27, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String selectValue1 = ComponentList.getSelectedItem().toString();
        System.out.println(selectValue1);

        String selectValue2 = NameList.getSelectedItem().toString();
        System.out.println(selectValue2);

        try {
            if ("GPU".equals(selectValue1)) {

                String[] tableColumnsName = { "Computer", "CPU", "RAM" };
                DefaultTableModel aModel = (DefaultTableModel) TableComputer.getModel();
                aModel.setColumnIdentifiers(tableColumnsName);
                aModel.setRowCount(0);
                // the query
                System.out.println("Getting Computer Dtail"); // get
                String queryString;
                queryString = "PREFIX ex:<http://localhost/computer#> "
                        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                        + "SELECT  (str(?nameCom) as ?NameComputer) (str(?cpu) as ?CPU) (str(?ram) as ?RAM) "
                        + "where {?x ex:name \"" + selectValue2 + "\"." + "?y ex:hasGPU ?x." + "?y ex:hasCPU ?c."
                        + "?y ex:hasRAM ?r." + "?y ex:name ?nameCom." + "?c ex:name ?cpu." + "?r ex:name ?ram." + " }";
                System.out.println(queryString);
                com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from
                                                                                           // OpenOWL class

                while (results.hasNext()) {

                    QuerySolution sol = results.nextSolution();
                    RDFNode Name = sol.get("NameComputer");
                    RDFNode CPU = sol.get("CPU");
                    RDFNode RAM = sol.get("RAM");

                    aModel.addRow(new Object[] { Name, CPU, RAM });

                    TableComputer.setModel(aModel);

                }

            }

            if ("CPU".equals(selectValue1)) {

                String[] tableColumnsName = { "Computer", "GPU", "RAM" };
                DefaultTableModel aModel = (DefaultTableModel) TableComputer.getModel();
                aModel.setColumnIdentifiers(tableColumnsName);
                aModel.setRowCount(0);
                // the query
                System.out.println("Getting Computer Dtail"); // get
                String queryString;
                queryString = "PREFIX ex:<http://localhost/computer#> "
                        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                        + "SELECT  (str(?nameCom) as ?NameComputer) (str(?gpu) as ?GPU) (str(?ram) as ?RAM) "
                        + "where {?x ex:name \"" + selectValue2 + "\"." + "?y ex:hasCPU ?x." + "?y ex:hasGPU ?g."
                        + "?y ex:hasRAM ?r." + "?y ex:name ?nameCom." + "?g ex:name ?gpu." + "?r ex:name ?ram." + " }";
                System.out.println(queryString);
                com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from
                                                                                           // OpenOWL class

                while (results.hasNext()) {

                    QuerySolution sol = results.nextSolution();
                    RDFNode Name = sol.get("NameComputer");
                    RDFNode GPU = sol.get("GPU");
                    RDFNode RAM = sol.get("RAM");

                    aModel.addRow(new Object[] { Name, GPU, RAM });

                    TableComputer.setModel(aModel);

                }
            }

            if ("RAM".equals(selectValue1)) {

                String[] tableColumnsName = { "Computer", "GPU", "CPU" };
                DefaultTableModel aModel = (DefaultTableModel) TableComputer.getModel();
                aModel.setColumnIdentifiers(tableColumnsName);
                aModel.setRowCount(0);
                // the query
                System.out.println("Getting Computer Dtail"); // get
                String queryString;
                queryString = "PREFIX ex:<http://localhost/computer#> "
                        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                        + "SELECT  (str(?nameCom) as ?NameComputer) (str(?gpu) as ?GPU) (str(?cpu) as ?CPU) "
                        + "where {?x ex:name \"" + selectValue2 + "\"." + "?y ex:hasRAM ?x." + "?y ex:hasGPU ?g."
                        + "?y ex:hasCPU ?c." + "?y ex:name ?nameCom." + "?g ex:name ?gpu." + "?c ex:name ?cpu." + " }";
                System.out.println(queryString);
                com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from
                                                                                           // OpenOWL class

                while (results.hasNext()) {

                    QuerySolution sol = results.nextSolution();
                    RDFNode Name = sol.get("NameComputer");
                    RDFNode GPU = sol.get("GPU");
                    RDFNode CPU = sol.get("CPU");

                    aModel.addRow(new Object[] { Name, GPU, CPU });

                    TableComputer.setModel(aModel);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }// GEN-LAST:event_jButton2ActionPerformed

    private void NameListActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_NameListActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_NameListActionPerformed

    private void ComponentListActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ComponentListActionPerformed
        // TODO add your handling code here:

        String selectValue = ComponentList.getSelectedItem().toString();
        System.out.println(selectValue);

        ListNames.removeAll(ListNames);

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Getting ListComponentNames"); // get the name list querry
            String queryString;
            queryString = "PREFIX ex:<http://localhost/computer#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "SELECT  (str(?y) as ?NameComponent) " + "where { ?x rdf:type ex:" + selectValue + "."
                    + "?x ex:name ?y." + " }";

            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from
                                                                                       // OpenOWL class

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();
                String NameOfComponent = soln.getLiteral("NameComponent").getString();
                // test --
                System.out.println("Name Component " + NameOfComponent.toString());
                ListNames.add(NameOfComponent.toString());

                RDFNode x = soln.get("Propertyval");

                String xx = String.valueOf(x);

                java.nio.ByteBuffer xxx = Charset.forName("UTF-8").encode(xx);

                String xs = xxx.toString();

            }
            System.out.println(ListNames);
            NameList.removeAllItems(); // combobox nameList

            for (int i = 0; i < ListNames.size(); i++) {

                NameList.addItem(ListNames.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }// GEN-LAST:event_ComponentListActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String KeywordFieldValue = KeywordField.getText();
        System.out.println(KeywordFieldValue);

        String[] tableColumnsName = { "Computer", "CPU", "RAM" };
        DefaultTableModel aModel = (DefaultTableModel) TableComputer.getModel();
        aModel.setColumnIdentifiers(tableColumnsName);
        aModel.setRowCount(0);
        // the query

        System.out.println("Getting Computer Dtail"); // get
        String queryString;
        queryString = "PREFIX ex:<http://localhost/computer#> "
                + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                + "SELECT  (str(?nameCom) as ?NameComputer) (str(?cpu) as ?CPU) (str(?ram) as ?RAM) "
                + "where {?x ex:name ?gpu." + "FILTER regex(?gpu, \"" + KeywordFieldValue + "\")" + "?y ex:hasGPU ?x."
                + "?y ex:hasCPU ?c." + "?y ex:hasRAM ?r." + "?y ex:name ?nameCom." + "?c ex:name ?cpu."
                + "?r ex:name ?ram." + " }";
        System.out.println(queryString);
        com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from OpenOWL
                                                                                   // class

        while (results.hasNext()) {

            QuerySolution sol = results.nextSolution();
            RDFNode Name = sol.get("NameComputer");
            RDFNode CPU = sol.get("CPU");
            RDFNode RAM = sol.get("RAM");

            aModel.addRow(new Object[] { Name, CPU, RAM });

            TableComputer.setModel(aModel);

        }
    }// GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DisplayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DisplayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DisplayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DisplayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DisplayFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComponentList;
    private javax.swing.JTextField KeywordField;
    private javax.swing.JComboBox<String> NameList;
    private javax.swing.JTable TableComputer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}

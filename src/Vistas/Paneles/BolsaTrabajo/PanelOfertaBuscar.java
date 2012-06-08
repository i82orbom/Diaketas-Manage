
package Vistas.Paneles.BolsaTrabajo;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author psylock
 */
public class PanelOfertaBuscar extends javax.swing.JPanel {

    /**
     * Creates new form PanelBeneficiarioBuscar
     */
    public PanelOfertaBuscar() {
        initComponents();
    }

	/* ________ Botones de la vista ________*/
    public JButton getBTEliminar(){
        return BTEliminar;
    }

    public JButton getBTConsultar(){
        return BTConsultar;
    }

	public JButton getBTModificar(){
        return BTModificar;
    }

    public JButton getBTBuscar(){
        return BTBuscar;
    }

    public JComboBox getcbAntiguedad(){
        return cbAntiguedad;
    }

	/* ________ Campos de la vista ________*/
	public int getAntiguedad(){
		return cbAntiguedad.getSelectedIndex();
    }

    public String getTextoSector(){
        return cbSector.getSelectedItem().toString();
    }
    public JComboBox getcbSector(){
        return cbSector;
    }

	public String gettextoCIFEmpresa(){
		return textCIFEmpresa.getText();
	}

    public JTable gettablaBusquedaOferta(){
        return tablaBusquedaOferta;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        tablaBusquedaOferta = new javax.swing.JTable();
        labelSectorBusqueda = new javax.swing.JLabel();
        cbSector = new javax.swing.JComboBox();
        labelAntiguedad = new javax.swing.JLabel();
        cbAntiguedad = new javax.swing.JComboBox();
        BTEliminar = new javax.swing.JButton();
        BTModificar = new javax.swing.JButton();
        BTBuscar = new javax.swing.JButton();
        labelCIFEmpresa = new javax.swing.JLabel();
        textCIFEmpresa = new javax.swing.JTextField();
        BTConsultar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));

        tablaBusquedaOferta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CIF", "Razon Social", "Sector", "Fecha Demanda"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tablaBusquedaOferta);

        labelSectorBusqueda.setText("Sector Busqueda");

        labelAntiguedad.setText("Antiguedad");

        cbAntiguedad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas", "1 mes", "2 meses", "3 meses", "4 meses", "5 meses", "6 meses", "7 meses", "8 meses", "9 meses", "10 meses", "11 meses", "mas de 1 año" }));

        BTEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/borrar.png"))); // NOI18N
        BTEliminar.setToolTipText("Eliminar");

        BTModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/modificar.png"))); // NOI18N
        BTModificar.setToolTipText("Modificar");

        BTBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/buscar.png"))); // NOI18N

        labelCIFEmpresa.setText("Empresa");

        BTConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/consultar.png"))); // NOI18N
        BTConsultar.setToolTipText("Consultar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTConsultar)
                .addGap(18, 18, 18)
                .addComponent(BTModificar)
                .addGap(18, 18, 18)
                .addComponent(BTEliminar)
                .addGap(389, 389, 389))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 948, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(labelCIFEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textCIFEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(labelSectorBusqueda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbSector, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(labelAntiguedad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbAntiguedad, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelAntiguedad)
                        .addComponent(cbAntiguedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelCIFEmpresa)
                        .addComponent(textCIFEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelSectorBusqueda))
                    .addComponent(BTBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTConsultar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTBuscar;
    private javax.swing.JButton BTConsultar;
    private javax.swing.JButton BTEliminar;
    private javax.swing.JButton BTModificar;
    private javax.swing.JComboBox cbAntiguedad;
    private javax.swing.JComboBox cbSector;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelAntiguedad;
    private javax.swing.JLabel labelCIFEmpresa;
    private javax.swing.JLabel labelSectorBusqueda;
    private javax.swing.JTable tablaBusquedaOferta;
    private javax.swing.JTextField textCIFEmpresa;
    // End of variables declaration//GEN-END:variables
}

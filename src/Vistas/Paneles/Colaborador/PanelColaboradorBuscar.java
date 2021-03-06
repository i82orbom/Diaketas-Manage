/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Paneles.Colaborador;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author Alberto
 */
public class PanelColaboradorBuscar extends javax.swing.JPanel {

    /**
     * Creates new form PanelColaboradorBuscar
     */
    public PanelColaboradorBuscar() {
        initComponents();
		labelError.setVisible(false);
		tableBuscarColaborador.setBackground(Color.white);
		tableBuscarColaborador.setForeground(Color.blue);
		cbTipoBuscarColaborador.setBackground(Color.white);
		cbTipoBuscarColaborador.setForeground(Color.black);
		
    }

	public void setTextLabelError (String text) {
		labelError.setText(text);
		labelError.setVisible(true);
	}

	public JButton getBtBuscarColaborador(){
		return btBuscar;
	}
	
	public String getTipoBusquedaColaborador(){
		return cbTipoBuscarColaborador.getSelectedItem().toString();
	}
	
	public String getValorBusquedaColaborador(){
		return JtextBuscarColaborador.getText();
	}
	public JTable getTablaBusqueda() {
        return tableBuscarColaborador;
    }
	
	public JButton getBtConsultarColaborador(){
		return btConsultar;
	}
	
	public JButton getBtEliminarColaborador(){
		return btEliminar;
	}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btBuscar = new javax.swing.JButton();
        jLabel103 = new javax.swing.JLabel();
        cbTipoBuscarColaborador = new javax.swing.JComboBox();
        JtextBuscarColaborador = new javax.swing.JTextField();
        btConsultar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        tableBuscarColaborador = new javax.swing.JTable();
        labelError = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/buscar.png"))); // NOI18N

        jLabel103.setText("Buscar por:");

        cbTipoBuscarColaborador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DNI/NIF/Pasaporte", "Nombre", "Dirección", "Localidad", "Teléfono Fijo", "Movil", "Código Postal" }));

        JtextBuscarColaborador.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JtextBuscarColaborador.setSelectionColor(new java.awt.Color(102, 204, 255));

        btConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/modificar.png"))); // NOI18N

        btEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/borrar.png"))); // NOI18N

        tableBuscarColaborador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Nombre", "Direccion", "Localidad", "Telefono", "Movil", "Código Postal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane.setViewportView(tableBuscarColaborador);

        labelError.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelError.setForeground(new java.awt.Color(255, 51, 51));
        labelError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelError.setText("Error");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelError, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JtextBuscarColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel103)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTipoBuscarColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(469, 469, 469)
                        .addComponent(btConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JtextBuscarColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(cbTipoBuscarColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(labelError)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JtextBuscarColaborador;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btConsultar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JComboBox cbTipoBuscarColaborador;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel labelError;
    private javax.swing.JTable tableBuscarColaborador;
    // End of variables declaration//GEN-END:variables
}

package Interface;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Opcoes extends javax.swing.JFrame {  
    public Opcoes() {
        initComponents();
          // Define o tamanho fixo da janela
        this.setResizable(false);
        // Impede a maximização
        this.setMaximumSize(getSize());
        //Inicializa a Janela no meio da tela
        this.setLocationRelativeTo(null);
        
        setTitle("Atheanaum");//Define um tituo para a Janela
        setIconImage(new ImageIcon(getClass().getResource("/Interface/Pilha_de_livros.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelnome = new javax.swing.JLabel();
        imgpilhadelivros = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelclientes = new javax.swing.JLabel();
        btnlivros = new javax.swing.JButton();
        labellivros = new javax.swing.JLabel();
        btncliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(25, 92, 212));

        labelnome.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelnome.setForeground(new java.awt.Color(255, 255, 255));
        labelnome.setText("Athenaum");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/Pilha_de_livros.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(labelnome, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgpilhadelivros, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(labelnome)
                .addGap(27, 27, 27)
                .addComponent(imgpilhadelivros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelclientes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelclientes.setText("Buscar Clientes");

        btnlivros.setText("Buscar");
        btnlivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlivrosActionPerformed(evt);
            }
        });
        btnlivros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnlivrosKeyPressed(evt);
            }
        });

        labellivros.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labellivros.setText("Buscar Livros");

        btncliente.setText("Buscar");
        btncliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclienteActionPerformed(evt);
            }
        });
        btncliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnclienteKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(labelclientes))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btncliente, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnlivros, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(labellivros)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(labelclientes)
                .addGap(18, 18, 18)
                .addComponent(btncliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(labellivros)
                .addGap(18, 18, 18)
                .addComponent(btnlivros, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclienteActionPerformed
         new Clientes().setVisible(true);
    }//GEN-LAST:event_btnclienteActionPerformed

    private void btnlivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlivrosActionPerformed
         new Livros().setVisible(true);
    }//GEN-LAST:event_btnlivrosActionPerformed

    private void btnclienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnclienteKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    btnlivros.requestFocus(); // Move o foco para o próximo JTextField
                }
         
         else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             new Clientes().setVisible(true);
             dispose();
                }
    }//GEN-LAST:event_btnclienteKeyPressed

    private void btnlivrosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnlivrosKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    btncliente.requestFocus(); // Move o foco para o próximo JTextField
                }
           
           
         else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             new Livros().setVisible(true);
             dispose();
                }
    }//GEN-LAST:event_btnlivrosKeyPressed

    public static void main(String args[]) {
     
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Opcoes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncliente;
    private javax.swing.JButton btnlivros;
    private javax.swing.JLabel imgpilhadelivros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelclientes;
    private javax.swing.JLabel labellivros;
    private javax.swing.JLabel labelnome;
    // End of variables declaration//GEN-END:variables
}

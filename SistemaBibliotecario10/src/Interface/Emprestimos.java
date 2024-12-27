package Interface;
import classes.Emprestimo;
import classes.EmprestimoDAO;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Emprestimos extends javax.swing.JFrame {

    EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    
     public void setClienteId(String clienteId) {
        txtidcliente.setText(clienteId); // Substitua txtClienteId pelo nome do JTextField correspondente
        buscarEmprestimos();
    }
    public Emprestimos() {
        // Iniciar Componentes
        initComponents();
        buscarEmprestimos();
        txtid_emprestimo.requestFocus();
        // Define o tamanho fixo da janela
        this.setResizable(false);
        // Impede a maximização
        this.setMaximumSize(getSize());
        buscarEmprestimos();
        this.setLocationRelativeTo(null);
        
        setTitle("Atheanaum");//Define um tituo para a Janela
        setIconImage(new ImageIcon(getClass().getResource("/Interface/Pilha_de_livros.png")).getImage());
    }

    // Função para exibir os dados na tela
    private void exibir() {
        // Criação da variável responsável pela parte visual da tabela
        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
        // Setando o valor padrão de linhas para 0
        dtm.setNumRows(0);

        // Criando um array que irá armazenar os valores pegos na variável da classe
        List<Emprestimo> emprestimos = emprestimoDAO.listarTodos();
        // Cria um loop para verificar os dados da variável emprestimos
        for (Emprestimo emprestimo : emprestimos) {
            // Adiciona um item em uma linha de um array correspondente com a linha na tabela de empréstimos
            dtm.addRow(new Object[]{
                emprestimo.getIdEmprestimo(), 
                emprestimo.getIdCliente(), 
                emprestimo.getIdLivro(), 
                emprestimo.getDataEmprestimo(), 
                emprestimo.getPrazoEmprestimo()
            });
        }
    }

    // Função para salvar dados na tabela
private void salvar() {
    try {
        // Captura os dados dos campos de texto
        int idCliente = Integer.parseInt(txtidcliente.getText());
        int idLivro = Integer.parseInt(txtidlivro.getText());

        // Converte as strings das datas para java.util.Date
        java.util.Date dataEmprestimo = new SimpleDateFormat("yyyy-MM-dd").parse(txtdata.getText().trim());
        java.util.Date prazoEmprestimo = new SimpleDateFormat("yyyy-MM-dd").parse(txtprazo.getText().trim());

        // Cria o objeto Emprestimo
        Emprestimo emprestimo = new Emprestimo(0, idCliente, idLivro, dataEmprestimo, prazoEmprestimo);

        // Salva no banco de dados
        emprestimoDAO.salvar(emprestimo);

        // Atualiza a interface
        buscarEmprestimos();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Os campos ID Cliente e ID Livro devem conter apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "As datas devem estar no formato yyyy-MM-dd.", "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao salvar o empréstimo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}


    // Função para excluir na tabela
private void excluir() {
    // Verifica se o campo está vazio antes de tentar converter
    String idEmprestimoTexto = txtid_emprestimo.getText().trim();

    if (idEmprestimoTexto.isEmpty()) {
        JOptionPane.showMessageDialog(this, "O campo ID do empréstimo está vazio. Por favor, preencha o campo antes de excluir.", "Erro", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        // Converte o texto para número inteiro
        int idEmprestimo = Integer.parseInt(idEmprestimoTexto);

        // Chama o método de exclusão no DAO
        emprestimoDAO.excluir(idEmprestimo);

        // Atualiza a exibição após a exclusão
        exibir(); 
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "O ID do empréstimo deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void buscarEmprestimos() {
    String clienteId = txtidcliente.getText().trim();

    EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    List<Emprestimo> emprestimos = emprestimoDAO.buscarEmprestimosPorClienteId(clienteId);

    // Atualiza a tabela com os resultados
    DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
    model.setRowCount(0); // Limpa a tabela

    for (Emprestimo emprestimo : emprestimos) {
        model.addRow(new Object[]{
            emprestimo.getIdEmprestimo(),
            emprestimo.getIdCliente(),
            emprestimo.getIdLivro(),
            emprestimo.getDataEmprestimo(),
            emprestimo.getPrazoEmprestimo()
        });
    }
}
    

    public void limpar() {
    txtid_emprestimo.setText(null);
    txtidcliente.setText(null);
    txtidlivro.setText(null);
    txtdata.setValue(null); // Para campos formatados de data
    txtprazo.setValue(null); // Para campos formatados de prazo
}

    private boolean camposVazios() {
    return txtid_emprestimo.getText().trim().isEmpty() ||
           txtidcliente.getText().trim().isEmpty() ||
           txtidlivro.getText().trim().isEmpty() ||
           txtdata.getText().trim().isEmpty() || txtdata.getValue() == null ||
           txtprazo.getText().trim().isEmpty() || txtprazo.getValue() == null;
}

private boolean camposVazios2() {
    return txtid_emprestimo.getText().trim().isEmpty() &&
           txtidcliente.getText().trim().isEmpty() &&
           txtidlivro.getText().trim().isEmpty() &&
           txtdata.getText().trim().isEmpty() && txtdata.getValue() == null &&
           txtprazo.getText().trim().isEmpty() && txtprazo.getValue() == null;
}

    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtid_emprestimo = new javax.swing.JTextField();
        txtidcliente = new javax.swing.JTextField();
        txtidlivro = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblEmprestimos = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btncadastrar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btnremover = new javax.swing.JButton();
        btnvoltar = new javax.swing.JButton();
        txtdata = new javax.swing.JFormattedTextField();
        txtprazo = new javax.swing.JFormattedTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(247, 247, 248));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(25, 93, 212));
        jLabel2.setText("Buscar Emprestimo");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Id_Emprestimo");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Data");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Id Livro");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Id Cliente");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Prazo de Entrega");

        txtid_emprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid_emprestimoActionPerformed(evt);
            }
        });
        txtid_emprestimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtid_emprestimoKeyPressed(evt);
            }
        });

        txtidcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidclienteActionPerformed(evt);
            }
        });
        txtidcliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidclienteKeyPressed(evt);
            }
        });

        txtidlivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidlivroActionPerformed(evt);
            }
        });
        txtidlivro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidlivroKeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(25, 93, 212));

        lblEmprestimos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblEmprestimos.setForeground(new java.awt.Color(255, 255, 255));
        lblEmprestimos.setText("Histórico de emprestimos");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Emprestimo", "ID Cliente", "Titulo", "Data", "Prazo"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(lblEmprestimos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lblEmprestimos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        btncadastrar.setText("Cadastrar");
        btncadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncadastrarActionPerformed(evt);
            }
        });
        btncadastrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btncadastrarKeyPressed(evt);
            }
        });

        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        btnbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnbuscarKeyPressed(evt);
            }
        });

        btnremover.setText("Remover");
        btnremover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremoverActionPerformed(evt);
            }
        });
        btnremover.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnremoverKeyPressed(evt);
            }
        });

        btnvoltar.setText("Voltar");
        btnvoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvoltarActionPerformed(evt);
            }
        });
        btnvoltar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnvoltarKeyPressed(evt);
            }
        });

        try {
            txtdata.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtdata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdataKeyPressed(evt);
            }
        });

        try {
            txtprazo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtprazo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtprazoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnvoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(btncadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnremover, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(txtid_emprestimo, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(txtidcliente, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addComponent(txtidlivro, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .addComponent(txtdata)
                            .addComponent(txtprazo)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtid_emprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtidlivro, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdata, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtprazo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnremover, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnvoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void txtid_emprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid_emprestimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid_emprestimoActionPerformed

    private void txtidclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidclienteActionPerformed

    private void txtidlivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidlivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidlivroActionPerformed

    private void btncadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadastrarActionPerformed
        salvar();
    }//GEN-LAST:event_btncadastrarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        buscarEmprestimos();
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnremoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoverActionPerformed
        excluir();
    }//GEN-LAST:event_btnremoverActionPerformed

    private void btnvoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvoltarActionPerformed
        // TODO add your handling code here:
        new Clientes().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnvoltarActionPerformed

    private void txtid_emprestimoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtid_emprestimoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txtid_emprestimo.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
                    }
                    else{
                       txtidcliente.requestFocus(); // Move o foco para o próximo JTextField 
                    } 
                }
        else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            txtidcliente.requestFocus();
        }
        
        else if(evt.getKeyCode() == KeyEvent.VK_UP){
            txtprazo.requestFocus();
        }
        
    }//GEN-LAST:event_txtid_emprestimoKeyPressed

    private void txtidclienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidclienteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txtidcliente.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
                    }
                    else{
                       txtidlivro.requestFocus(); // Move o foco para o próximo JTextField 
                    } 
                }
        else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            txtidlivro.requestFocus();
        }
        
        else if(evt.getKeyCode() == KeyEvent.VK_UP){
            txtid_emprestimo.requestFocus();
        }
    }//GEN-LAST:event_txtidclienteKeyPressed

    private void txtidlivroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidlivroKeyPressed
        // TODO add your handling code here:
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txtidlivro.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
                    }
                    else{
                       txtdata.requestFocus(); // Move o foco para o próximo JTextField 
                    } 
                }
        else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            txtdata.requestFocus();
        }
        
        else if(evt.getKeyCode() == KeyEvent.VK_UP){
            txtidcliente.requestFocus();
        }
    }//GEN-LAST:event_txtidlivroKeyPressed

    private void txtdataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdataKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txtdata.getText().trim().isEmpty()|| txtdata.getValue() == null){
                        JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
                    }
                    else{
                       txtprazo.requestFocus(); // Move o foco para o próximo JTextField 
                    } 
                }
        else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            txtprazo.requestFocus();
        }
        
        else if(evt.getKeyCode() == KeyEvent.VK_UP){
            txtidlivro.requestFocus();
        }
    }//GEN-LAST:event_txtdataKeyPressed

    private void txtprazoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprazoKeyPressed
        // TODO add your handling code here:
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txtprazo.getText().trim().isEmpty()|| txtprazo.getValue() == null){
                        JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
                    }
                    else{
                       btnbuscar.requestFocus(); // Move o foco para o próximo JTextField 
                    } 
                }
        else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            btnbuscar.requestFocus();
        }
        
        else if(evt.getKeyCode() == KeyEvent.VK_UP){
            txtdata.requestFocus();
        }
    }//GEN-LAST:event_txtprazoKeyPressed

    private void btnbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnbuscarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                    btncadastrar.requestFocus(); // Move o foco para o próximo JTextField
                }
           
           
         else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    btnremover.requestFocus(); // Move o foco para o próximo JTextField
                }
         
         else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    btnvoltar.requestFocus(); // Move o foco para o próximo JTextField
                }
         
         else if(evt.getKeyCode() == KeyEvent.VK_UP){
            txtprazo.requestFocus();
        }
          
         else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(camposVazios())
                    JOptionPane.showMessageDialog(this, "Todos os campos deverão ser preenchidos");
                }
         else {
             exibir();
         }
    }//GEN-LAST:event_btnbuscarKeyPressed

    private void btncadastrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncadastrarKeyPressed
        // TODO add your handling code here:
                         if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                    btnremover.requestFocus(); // Move o foco para o próximo JTextField
                }
           
           
         else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    btnbuscar.requestFocus(); // Move o foco para o próximo JTextField
                }
         
         else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    btnvoltar.requestFocus(); // Move o foco para o próximo JTextField
                }
         
         else if(evt.getKeyCode() == KeyEvent.VK_UP){
            txtprazo.requestFocus();
        }
          
         else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(camposVazios())
                    JOptionPane.showMessageDialog(this, "Todos os campos deverão ser preenchidos");
                }
         else {
             salvar();
         }
    }//GEN-LAST:event_btncadastrarKeyPressed

    private void btnremoverKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnremoverKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                    btnbuscar.requestFocus(); // Move o foco para o próximo JTextField
                }
           
           
         else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    btncadastrar.requestFocus(); // Move o foco para o próximo JTextField
                }
         
         else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    btnvoltar.requestFocus(); // Move o foco para o próximo JTextField
                }
          
         else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(camposVazios())
                    JOptionPane.showMessageDialog(this, "Todos os campos deverão ser preenchidos");
                }
         
         else if(evt.getKeyCode() == KeyEvent.VK_UP){
            txtprazo.requestFocus();
        }
         else {
             excluir();
         }
    }//GEN-LAST:event_btnremoverKeyPressed

    private void btnvoltarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnvoltarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    btnbuscar.requestFocus(); // Move o foco para o próximo JTextField
                }
        
         else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
             new Opcoes().setVisible(true);
             dispose();
         }
    }//GEN-LAST:event_btnvoltarKeyPressed

    public static void main(String args[]) {


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Emprestimos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncadastrar;
    private javax.swing.JButton btnremover;
    private javax.swing.JButton btnvoltar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblEmprestimos;
    private javax.swing.JFormattedTextField txtdata;
    private javax.swing.JTextField txtid_emprestimo;
    private javax.swing.JTextField txtidcliente;
    private javax.swing.JTextField txtidlivro;
    private javax.swing.JFormattedTextField txtprazo;
    // End of variables declaration//GEN-END:variables
}

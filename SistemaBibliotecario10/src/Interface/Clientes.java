package Interface;
import classes.Cliente;
import classes.ClienteDAO;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class Clientes extends javax.swing.JFrame {

    ClienteDAO clientetemp = new ClienteDAO();

    public Clientes() {
        initComponents();//Iniciar componentes
        exibir();//chama a função de exibir no inicio do código
        this.setResizable(false);// Define o tamanho fixo da janela
        this.setMaximumSize(getSize());// Impede a maximização
        this.setLocationRelativeTo(null);//Inicializa a janela no centro da tela
        txtid.requestFocus();//Inicializa selecionando o campo de texto id

        setTitle("Atheanaum");//Define um tituo para a Janela
        setIconImage(new ImageIcon(getClass().getResource("/Interface/Pilha_de_livros.png")).getImage());

    }

    //função de exibir os dados na tela
    private void exibir() {
        //Criação da variavel responsavel pela parte visual da tabela
        //Conexão com a tabela já ciada
        DefaultTableModel dtm = (DefaultTableModel) jTClientes.getModel();
        //Setando o valor padrão de linhas para 0
        dtm.setNumRows(0);
        //Criando um array que irá armazenar os valores pegos na variavel da classe
        List<Cliente> clientes = clientetemp.listarTodos();
        //Cria um loop para veririficar os dados da variavel livros
        for (Cliente cliente : clientes) {
            //Adiciona um item em uma linha de um array correspondente com a linha na tabela do livros
            dtm.addRow(new Object[]{
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getEndereco(),
                cliente.getTelefone(),
                cliente.getNascimento()
            });
        }
    }

    //função de exibir os dados na tela

    private void salvar() {
        //Captura os dados da linha de um respectivo item (capturado com o id
        //As váriaveis com os identificadores txt representam os campos de texto na interface gráfica
        String nome = txtnome.getText();
        String email = txtemail.getText();
        String endereco = txtendereco.getText();
        String telefone = txttelefone.getText();
        String nascimento = txtnascimento.getText();

        //Cria uma variavel para armazenar os valores capturados pelo o método
        Cliente cliente = new Cliente(0, nome, email, endereco, telefone, nascimento);
        clientetemp.salvar(cliente);
        limpar();
        exibir(); // Atualiza a lista na interface
    }

//Função para excluir na tabela
    private void excluir() {
        //Captura o id do cliente capturado pelo campo de texto e convere ele para um número inteiro
        int idCliente = Integer.parseInt(txtid.getText());
        //aplica o método excluir no id livro
        clientetemp.excluir(idCliente);
        limpar();
        exibir(); // Atualiza a lista na interface
    }

    private void preencherCamposPorId() {
        String idText = txtid.getText(); // Obtém o texto do campo txtid
        if (idText == null || idText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Insira um ID válido.");
            return;
        }

        try {
            int idCliente = Integer.parseInt(idText); // Converte o texto para número inteiro
            Cliente cliente = clientetemp.buscarPorId(idCliente); // Busca o cliente pelo ID

            if (cliente != null) {
                // Preenche os campos de texto com os dados do cliente
                txtnome.setText(cliente.getNome());
                txtemail.setText(cliente.getEmail());
                txtendereco.setText(cliente.getEndereco());
                txttelefone.setText(cliente.getTelefone());
                txtnascimento.setText(cliente.getNascimento());
            } else {
                JOptionPane.showMessageDialog(null, "Cliente com ID " + idCliente + " não encontrado.");
                limpar(); // Limpa os campos caso o cliente não seja encontrado
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O ID deve ser um número válido.");
        }
    }

    private void atualizarDados() {
        if (txtid.getText().trim().isEmpty()) { // Verifica se o ID está vazio
            JOptionPane.showMessageDialog(null, "Por favor, insira um ID existente.");
            return;
        }

        int idCliente;
        try {
            idCliente = Integer.parseInt(txtid.getText().trim()); // Converte o ID para inteiro
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O ID deve ser um número válido.");
            return;
        }

        if (verificaSeExistePeloMenosUmCampoVazio()) { // Verifica se há campos vazios
            JOptionPane.showMessageDialog(null, "Preencha pelo menos um campo além do ID.");
            return;
        }

        // Captura os dados dos campos de texto
        String nome = txtnome.getText().trim();
        String email = txtemail.getText().trim();
        String endereco = txtendereco.getText().trim();
        String telefone = txttelefone.getText().trim();
        String nascimento = txtnascimento.getText().trim();

        // Cria o objeto Cliente com os dados capturados
        Cliente cliente = new Cliente(idCliente, nome, email, endereco, telefone, nascimento);

        // Chama o método de atualização no DAO
        clientetemp.atualizar(cliente);

        // Atualiza a tabela exibida na interface
        exibir();
    }

    private void buscar() {
        String id = txtid.getText();
        String nome = txtnome.getText();
        String email = txtemail.getText();
        String endereco = txtendereco.getText();
        String telefone = txttelefone.getText();
        String nascimento = txtnascimento.getText();

        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.buscarClientes(id, nome, email, endereco, telefone, nascimento);

        // Atualiza a tabela com os resultados
        DefaultTableModel model = (DefaultTableModel) jTClientes.getModel();
        model.setRowCount(0); // Limpa a tabela
        for (Cliente cliente : clientes) {
            model.addRow(new Object[]{
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getEndereco(),
                cliente.getTelefone(),
                cliente.getNascimento()
            });
        }
    }

    public void limpar() {
        txtid.setText(null);
        txtnome.setText(null);
        txtemail.setText(null);
        txtendereco.setText(null);
        txttelefone.setText(null);
        txtnascimento.setText(null);
    }

    private boolean verificaSeExistePeloMenosUmCampoVazio() {
        // Substitua textField1, textField2 pelos nomes dos seus JTextFields
        return txtid.getText().trim().isEmpty() || txtnome.getText().trim().isEmpty() || txtemail.getText().trim().isEmpty()
                || txtendereco.getText().trim().isEmpty() || txttelefone.getText().trim().isEmpty() || txtnascimento.getText().trim().isEmpty();
    }

    private boolean verificaSeTodosOsCamposEstaoVazios() {
        // Substitua textField1, textField2 pelos nomes dos seus JTextFields
        return txtid.getText().trim().isEmpty() && txtnome.getText().trim().isEmpty() && txtemail.getText().trim().isEmpty()
                && txtendereco.getText().trim().isEmpty() && txttelefone.getText().trim().isEmpty() && txtnascimento.getText().trim().isEmpty();
    }

    private boolean verificaSeTodosCamposEstaoVaziosSemContarID() {
        // Substitua textField1, textField2 pelos nomes dos seus JTextFields
        return txtnome.getText().trim().isEmpty() && txtemail.getText().trim().isEmpty()
                && txtendereco.getText().trim().isEmpty() && txttelefone.getText().trim().isEmpty() && txtnascimento.getText().trim().isEmpty();
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
        txtid = new javax.swing.JTextField();
        txtnome = new javax.swing.JTextField();
        txtendereco = new javax.swing.JTextField();
        txttelefone = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTClientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btncadastrar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btnremover = new javax.swing.JButton();
        txtemail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnvoltar = new javax.swing.JButton();
        btnatualizar = new javax.swing.JButton();
        Limpar = new javax.swing.JButton();
        txtnascimento = new javax.swing.JTextField();

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
        jLabel2.setText("Buscar Clientes");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Id");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Telefone");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Endereço");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Nome");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Data de nascimento");

        txtid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });
        txtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidKeyPressed(evt);
            }
        });

        txtnome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtnome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomeActionPerformed(evt);
            }
        });
        txtnome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnomeKeyPressed(evt);
            }
        });

        txtendereco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtendereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtenderecoActionPerformed(evt);
            }
        });
        txtendereco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtenderecoKeyPressed(evt);
            }
        });

        txttelefone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txttelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefoneActionPerformed(evt);
            }
        });
        txttelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttelefoneKeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(25, 93, 212));

        jTClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Email", "Endereço", "Telefone", "Nascimento"
            }
        ));
        jTClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTClientes);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Clientes ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(251, 251, 251))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btncadastrar.setText("Cadastrar");
        btncadastrar.setPreferredSize(new java.awt.Dimension(95, 30));
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
        btnbuscar.setPreferredSize(new java.awt.Dimension(95, 30));
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
        btnremover.setPreferredSize(new java.awt.Dimension(95, 30));
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

        txtemail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtemailKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Email");

        btnvoltar.setText("Voltar");
        btnvoltar.setPreferredSize(new java.awt.Dimension(70, 25));
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

        btnatualizar.setText("Atualizar");
        btnatualizar.setPreferredSize(new java.awt.Dimension(95, 30));
        btnatualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnatualizarActionPerformed(evt);
            }
        });
        btnatualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnatualizarKeyPressed(evt);
            }
        });

        Limpar.setText("Limpar");
        Limpar.setPreferredSize(new java.awt.Dimension(70, 25));
        Limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimparActionPerformed(evt);
            }
        });

        txtnascimento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtnascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnascimentoActionPerformed(evt);
            }
        });
        txtnascimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnascimentoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtemail)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnatualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btncadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnremover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnvoltar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Limpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtnascimento)
                            .addComponent(txttelefone)
                            .addComponent(txtendereco)
                            .addComponent(txtnome)
                            .addComponent(txtid))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtendereco, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addComponent(txtnascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnvoltar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Limpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnatualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnremover, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtnomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnomeActionPerformed

    private void txtenderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtenderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtenderecoActionPerformed

    private void txttelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefoneActionPerformed

    private void btncadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadastrarActionPerformed

        salvar();

    }//GEN-LAST:event_btncadastrarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here
        buscar();
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnremoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoverActionPerformed
        excluir();
    }//GEN-LAST:event_btnremoverActionPerformed

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed

    private void txtidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtid.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
            } else {
                preencherCamposPorId();
                txtnome.requestFocus(); // Move o foco para o próximo JTextField 
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txtnome.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtemail.requestFocus();
        }

    }//GEN-LAST:event_txtidKeyPressed

    private void txtnomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnomeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtnome.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
            } else {
                txtendereco.requestFocus(); // Move o foco para o próximo JTextField
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txtendereco.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtid.requestFocus();
        }

    }//GEN-LAST:event_txtnomeKeyPressed

    private void txtenderecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtenderecoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtendereco.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
            } else {
                txttelefone.requestFocus(); // Move o foco para o próximo JTextField
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txttelefone.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtnome.requestFocus();
        }
    }//GEN-LAST:event_txtenderecoKeyPressed

    private void txttelefoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefoneKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txttelefone.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
            } else {
                txtnascimento.requestFocus(); // Move o foco para o próximo JTextField
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txtnascimento.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtendereco.requestFocus();
        }
    }//GEN-LAST:event_txttelefoneKeyPressed

    private void txtemailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtemail.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
            } else {
                btnbuscar.requestFocus(); // Move o foco para o próximo JTextField
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            btnbuscar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtnascimento.requestFocus();
        }
    }//GEN-LAST:event_txtemailKeyPressed

    private void btnbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnbuscarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnatualizar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnremover.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtemail.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (verificaSeExistePeloMenosUmCampoVazio()) {
                JOptionPane.showMessageDialog(this, "Todos os campos deverão ser preenchidos");
            }
        }
    }//GEN-LAST:event_btnbuscarKeyPressed

    private void btncadastrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncadastrarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnremover.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnatualizar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtemail.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (verificaSeExistePeloMenosUmCampoVazio()) {
                JOptionPane.showMessageDialog(this, "Todos os campos deverão ser preenchidos");
            }
        }
    }//GEN-LAST:event_btncadastrarKeyPressed

    private void btnremoverKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnremoverKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnbuscar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btncadastrar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (verificaSeExistePeloMenosUmCampoVazio()) {
                JOptionPane.showMessageDialog(this, "Todos os campos deverão ser preenchidos");
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtemail.requestFocus(); // Move o foco para o próximo JTextField
        }
    }//GEN-LAST:event_btnremoverKeyPressed

    private void btnvoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvoltarActionPerformed
        // TODO add your handling code here:
        new Opcoes().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnvoltarActionPerformed

    private void btnvoltarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnvoltarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnvoltarKeyPressed

    private void jTClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTClientesMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) { // Clique simples
            int selectedRow = jTClientes.getSelectedRow(); // Obtém a linha selecionada
            if (selectedRow != -1) { // Verifica se uma linha foi selecionada
                String clienteId = jTClientes.getValueAt(selectedRow, 0).toString(); // Obtém o ID do cliente

                // Cria uma nova instância de Emprestimos
                Emprestimos emprestimos = new Emprestimos();

                // Envia o ID para o campo de texto na interface Emprestimos
                emprestimos.setClienteId(clienteId);

                // Exibe a interface Emprestimos
                emprestimos.setVisible(true);

                // Fecha a interface atual
                dispose();
            }
        }
    }//GEN-LAST:event_jTClientesMouseClicked

    private void btnatualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnatualizarActionPerformed
        // TODO add your handling code here:
        atualizarDados();
    }//GEN-LAST:event_btnatualizarActionPerformed

    private void LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimparActionPerformed
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_LimparActionPerformed

    private void btnatualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnatualizarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btncadastrar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnbuscar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtemail.requestFocus(); // Move o foco para o próximo JTextField
        }
    }//GEN-LAST:event_btnatualizarKeyPressed

    private void txtnascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnascimentoActionPerformed

    private void txtnascimentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnascimentoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtnascimento.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
            } else {
                txtemail.requestFocus(); // Move o foco para o próximo JTextField
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txtemail.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txttelefone.requestFocus();
        }
    }//GEN-LAST:event_txtnascimentoKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Limpar;
    private javax.swing.JButton btnatualizar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncadastrar;
    private javax.swing.JButton btnremover;
    private javax.swing.JButton btnvoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTClientes;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtendereco;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnascimento;
    private javax.swing.JTextField txtnome;
    private javax.swing.JTextField txttelefone;
    // End of variables declaration//GEN-END:variables
}

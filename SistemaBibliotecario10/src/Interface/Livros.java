package Interface;
import classes.Livro;
import classes.LivroDAO;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.*;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Livros extends javax.swing.JFrame {

    LivroDAO livrotemp = new LivroDAO();

    public Livros() {
        initComponents();//Iniciar Componentes
        LivroDAO livrotemp = new LivroDAO(); //variavel para "armazenar" a classe
        exibir();//Exibe os dados da tabela ao iniciar
        this.setResizable(false);// Define o tamanho fixo da janela
        this.setMaximumSize(getSize());// Impede a maximização
        this.setLocationRelativeTo(null);//Incializa a Janela no centro da tela
        txtid.requestFocus();

        setTitle("Atheanaum");//Define um tituo para a Janela
        setIconImage(new ImageIcon(getClass().getResource("/Interface/Pilha_de_livros.png")).getImage());
    }

    //função de exibir os dados na tela
    private void exibir() {
        //Criação da variavel responsavel pela parte visual da tabela
        //Conexão com a tabela já ciada
        DefaultTableModel dtm = (DefaultTableModel) jTLivros.getModel();
        //Setando o valor padrão de linhas para 0
        dtm.setNumRows(0);

        //Criando um array que irá armazenar os valores pegos na variavel da classe
        List<Livro> livros = livrotemp.listarTodos();
        //Cria um loop para veririficar os dados da variavel livros
        for (Livro livro : livros) {
            //Adiciona um item em uma linha de um array correspondente com a linha na tabela do livros
            dtm.addRow(new Object[]{livro.getIdLivro(), livro.getTitulo(), livro.getAutor(), livro.getGenero(), livro.getDataPublicacao(), livro.getEditora()});
        }
    }

    //Função para salvar dados na tabela
    private void salvar() {
        //Captura os dados da linha de um respectivo item (capturado com o id
        //As váriaveis com os identificadores txt representam os campos de texto na interface gráfica
        String titulo = txttitulo.getText();
        String autor = txtautor.getText();
        String genero = txtgenero.getText();
        String dataPublicacao = txtlancamento.getText();
        String editora = txteditora.getText();

        //Cria uma variavel para armazenar os valores capturados pelo o método
        Livro livro = new Livro(0, titulo, autor, genero, dataPublicacao, editora);
        livrotemp.salvar(livro);
        limpar();
        exibir(); // Atualiza a lista na interface
    }

    //Função para excluir na tabela
    private void excluir() {
        //Captura o id do livro capturado pelo campo de texto e convere ele para um número inteiro
        int idLivro = Integer.parseInt(txtid.getText());
        //aplica o método excluir no id livro
        livrotemp.excluir(idLivro);
        limpar();
        exibir(); // Atualiza a lista na interface
    }
    // Funçao para exibir dados na tabela

    private void preencherCamposPorId() {
        String idText = txtid.getText(); // Obtém o texto do campo txtid
        if (idText == null || idText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Insira um ID válido.");
            return;
        }

        try {
            int idLivro = Integer.parseInt(idText); // Converte o texto para número inteiro
            Livro livro = livrotemp.buscarPorId(idLivro); // Busca o cliente pelo ID

            if (livro != null) {
                // Preenche os campos de texto com os dados do cliente
                txttitulo.setText(livro.getTitulo());
                txtautor.setText(livro.getAutor());
                txtgenero.setText(livro.getGenero());
                txtlancamento.setText(livro.getDataPublicacao());
                txteditora.setText(livro.getEditora());
            } else {
                JOptionPane.showMessageDialog(null, "Livro com ID " + idLivro + " não encontrado.");
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

        int idLivro;
        try {
            idLivro = Integer.parseInt(txtid.getText().trim()); // Converte o ID para inteiro
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O ID deve ser um número válido.");
            return;
        }

        if (camposVazios()) { // Verifica se há campos vazios
            JOptionPane.showMessageDialog(null, "Preencha pelo menos um campo além do ID.");
            return;
        }

        // Captura os dados dos campos de texto
        String titulo = txttitulo.getText().trim();
        String autor = txtautor.getText().trim();
        String genero = txtgenero.getText().trim();
        String lancamento = txtlancamento.getText().trim();
        String editora = txteditora.getText().trim();

        // Cria o objeto Cliente com os dados capturados
        Livro livro = new Livro(idLivro, titulo, autor, genero, lancamento, editora);

        // Chama o método de atualização no DAO
        livrotemp.atualizar(livro);

        // Atualiza a tabela exibida na interface
        exibir();
    }

    //função para limpar os campos de textos
    public void limpar() {
        txtid.setText(null);
        txtautor.setText(null);
        txteditora.setText(null);
        txtgenero.setText(null);
        txtlancamento.setText(null);
        txttitulo.setText(null);
    }
    
    //função para identificar se os campos de textos estão vazios
    private boolean camposVazios() {
        // Substitua textField1, textField2 pelos nomes dos seus JTextFields
        return txtid.getText().trim().isEmpty() || txtautor.getText().trim().isEmpty() || txteditora.getText().trim().isEmpty()
                || txteditora.getText().trim().isEmpty() || txtgenero.getText().trim().isEmpty() || txtlancamento.getText().trim().isEmpty() || txttitulo.getText().trim().isEmpty();
    }
    
    //função para ver se todos os campos de texto estão vazios
    private boolean camposVazios2() {
        // Substitua textField1, textField2 pelos nomes dos seus JTextFields
        return txtid.getText().trim().isEmpty() && txtautor.getText().trim().isEmpty() && txteditora.getText().trim().isEmpty()
                && txteditora.getText().trim().isEmpty() && txtgenero.getText().trim().isEmpty() && txtlancamento.getText().trim().isEmpty() && txttitulo.getText().trim().isEmpty();
    }
    
    //função para filtrar os livros por cada um de seus atributos
    public void buscarLivros() {
        // Obtém os valores dos campos de texto
        String id = txtid.getText().trim();
        String titulo = txttitulo.getText().trim(); // Assumindo que txtnome se refere ao título
        String autor = txtautor.getText().trim(); // Assumindo que txtemail se refere ao autor
        String genero = txtgenero.getText().trim(); // Assumindo que txtendereco se refere ao gênero
        String dataPublicacao = txtlancamento.getText().trim(); // Assumindo que txtnascimento se refere à data de publicação
        String editora = txteditora.getText().trim(); // Assumindo que txttelefone se refere à editora

        // Instancia o DAO e realiza a busca
        LivroDAO livroDAO = new LivroDAO();
        List<Livro> livros = livroDAO.buscarLivros(id, titulo, autor, genero, dataPublicacao, editora);

        // Atualiza a tabela com os resultados
        DefaultTableModel model = (DefaultTableModel) jTLivros.getModel(); // Renomear jTClientes para jTLivros na interface
        model.setRowCount(0); // Limpa a tabela
        for (Livro livro : livros) {
            model.addRow(new Object[]{
                livro.getIdLivro(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getGenero(),
                livro.getDataPublicacao(),
                livro.getEditora()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnremover1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txttitulo = new javax.swing.JTextField();
        txtautor = new javax.swing.JTextField();
        txtgenero = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTLivros = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btncadastrar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btnvoltar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txteditora = new javax.swing.JTextField();
        btnremover = new javax.swing.JButton();
        btnatualizar = new javax.swing.JButton();
        txtlimpar = new javax.swing.JButton();
        txtlancamento = new javax.swing.JTextField();

        btnremover1.setText("Remover");
        btnremover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremover1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(247, 247, 248));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(25, 93, 212));
        jLabel2.setText("Buscar Livros");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("ID");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Gênero");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Autor");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Título");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Data de Lançamento");

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

        txttitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttituloActionPerformed(evt);
            }
        });
        txttitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttituloKeyPressed(evt);
            }
        });

        txtautor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtautorActionPerformed(evt);
            }
        });
        txtautor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtautorKeyPressed(evt);
            }
        });

        txtgenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgeneroActionPerformed(evt);
            }
        });
        txtgenero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtgeneroKeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(25, 93, 212));

        jTLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Título", "Autor", "Gênero", "Lançamento", "Editora"
            }
        ));
        jScrollPane1.setViewportView(jTLivros);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Livros que atendem os critérios");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        btncadastrar.setText("Cadastrar");
        btncadastrar.setPreferredSize(new java.awt.Dimension(90, 30));
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
        btnbuscar.setMaximumSize(new java.awt.Dimension(90, 30));
        btnbuscar.setMinimumSize(new java.awt.Dimension(90, 30));
        btnbuscar.setPreferredSize(new java.awt.Dimension(90, 30));
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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Editora");

        txteditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txteditoraActionPerformed(evt);
            }
        });
        txteditora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txteditoraKeyPressed(evt);
            }
        });

        btnremover.setText("Remover");
        btnremover.setPreferredSize(new java.awt.Dimension(90, 30));
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

        btnatualizar.setText("Atualizar");
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

        txtlimpar.setText("Limpar");
        txtlimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlimparActionPerformed(evt);
            }
        });
        txtlimpar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtlimparKeyPressed(evt);
            }
        });

        txtlancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlancamentoActionPerformed(evt);
            }
        });
        txtlancamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtlancamentoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnvoltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtlimpar))
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnatualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnremover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                        .addComponent(txttitulo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtautor, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtgenero, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txteditora, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtlancamento, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtautor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtgenero, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(13, 13, 13)
                .addComponent(txtlancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txteditora, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnvoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtlimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnremover, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnatualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed

    }//GEN-LAST:event_txtidActionPerformed

    private void txttituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttituloActionPerformed

    private void txtautorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtautorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtautorActionPerformed

    private void txtgeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgeneroActionPerformed

    private void btncadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadastrarActionPerformed
        // TODO add your handling code here:
        salvar();
    }//GEN-LAST:event_btncadastrarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        buscarLivros();
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnvoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvoltarActionPerformed
        new Opcoes().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnvoltarActionPerformed

    private void txteditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txteditoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txteditoraActionPerformed

    private void txtidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtid.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
            } else {
                preencherCamposPorId();
                txttitulo.requestFocus(); // Move o foco para o próximo JTextField 
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txttitulo.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txteditora.requestFocus();
        }
    }//GEN-LAST:event_txtidKeyPressed

    private void txttituloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttituloKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txttitulo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
            } else {
                txtautor.requestFocus(); // Move o foco para o próximo JTextField 
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txtautor.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtid.requestFocus();
        }
    }//GEN-LAST:event_txttituloKeyPressed

    private void txtautorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtautorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtautor.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
            } else {
                txtgenero.requestFocus(); // Move o foco para o próximo JTextField 
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txtgenero.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txttitulo.requestFocus();
        }

    }//GEN-LAST:event_txtautorKeyPressed

    private void txtgeneroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgeneroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtgenero.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
            } else {
                txtlancamento.requestFocus(); // Move o foco para o próximo JTextField 
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txtlancamento.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtautor.requestFocus();
        }
    }//GEN-LAST:event_txtgeneroKeyPressed

    private void txteditoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txteditoraKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txteditora.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
            } else {
                btnbuscar.requestFocus(); // Move o foco para o próximo JTextField 
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            btnbuscar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtlancamento.requestFocus();
        }
    }//GEN-LAST:event_txteditoraKeyPressed


    private void btnremover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremover1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnremover1ActionPerformed

    private void btnremoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnremoverActionPerformed

    private void btnbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnbuscarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnatualizar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnremover.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txteditora.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (camposVazios()) {
                JOptionPane.showMessageDialog(this, "Todos os campos deverão ser preenchidos");
            }
        } else {
            exibir();
        }

    }//GEN-LAST:event_btnbuscarKeyPressed

    private void btncadastrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncadastrarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnremover.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnatualizar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txteditora.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (camposVazios2()) {
                JOptionPane.showMessageDialog(this, "Todos os campos deverão ser preenchidos");
            }
        } else {
            salvar();
        }
    }//GEN-LAST:event_btncadastrarKeyPressed

    private void btnremoverKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnremoverKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnbuscar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btncadastrar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (camposVazios()) {
                JOptionPane.showMessageDialog(this, "Todos os campos deverão ser preenchidos");
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txteditora.requestFocus();
        } else {
            excluir();
        }
    }//GEN-LAST:event_btnremoverKeyPressed

    private void btnvoltarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnvoltarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnbuscar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            new Opcoes().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnvoltarKeyPressed

    private void txtlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlimparActionPerformed
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_txtlimparActionPerformed

    private void txtlimparKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlimparKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlimparKeyPressed

    private void txtlancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlancamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlancamentoActionPerformed

    private void txtlancamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlancamentoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtlancamento.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Por favor preencha os dados corretamente");
            } else {
                txteditora.requestFocus(); // Move o foco para o próximo JTextField
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txteditora.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtgenero.requestFocus();
        }
    }//GEN-LAST:event_txtlancamentoKeyPressed

    private void btnatualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnatualizarActionPerformed
        // TODO add your handling code here:
        atualizarDados();
    }//GEN-LAST:event_btnatualizarActionPerformed

    private void btnatualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnatualizarKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btncadastrar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnbuscar.requestFocus(); // Move o foco para o próximo JTextField
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txteditora.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (camposVazios()) {
                JOptionPane.showMessageDialog(this, "Todos os campos deverão ser preenchidos");
            }
        } else {
            atualizarDados();
        }
    }//GEN-LAST:event_btnatualizarKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Livros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnatualizar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncadastrar;
    private javax.swing.JButton btnremover;
    private javax.swing.JButton btnremover1;
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
    private javax.swing.JTable jTLivros;
    private javax.swing.JTextField txtautor;
    private javax.swing.JTextField txteditora;
    private javax.swing.JTextField txtgenero;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtlancamento;
    private javax.swing.JButton txtlimpar;
    private javax.swing.JTextField txttitulo;
    // End of variables declaration//GEN-END:variables

    private void buscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

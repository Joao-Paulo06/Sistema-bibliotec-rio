    package classes;

    import Interface.Emprestimos; // Importa a classe Interface
    import java.sql.*; // Importa todos os itens da biblioteca SQL
    import java.util.ArrayList;
    import java.util.List;
    import javax.swing.JOptionPane; // Importa as janelas que serão úteis para indicar os tratamentos de erros

    // Classe responsável pela interação com o banco de dados para manipular informações de empréstimos
    public class EmprestimoDAO {

        // Credenciais para conexão com o banco de dados
        private final String url = "jdbc:mysql://localhost:3306/sistema_bibliotecario";
        private final String usuario = "root";
        private final String senha = "";


        private final List<Emprestimo> emprestimos = new ArrayList<>();


        // Método para inserir um empréstimo
        public void salvar(Emprestimo emprestimo) {
            // Cria uma Query para aplicar o comando INSERT no banco de dados
            String query = "INSERT INTO emprestimos (id_cliente, id_livro, data_emprestimo, prazo_emprestimo) VALUES (?, ?, ?, ?)";
            // Bloco try-with-resources para garantir que os recursos sejam fechados automaticamente
            try (Connection conn = DriverManager.getConnection(url, usuario, senha);
                    PreparedStatement pst = conn.prepareStatement(query)) {

                // Configura os parâmetros da consulta com os dados do empréstimo
                pst.setInt(1, emprestimo.getIdCliente());
                pst.setInt(2, emprestimo.getIdLivro());
                pst.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
                pst.setDate(4, new java.sql.Date(emprestimo.getPrazoEmprestimo().getTime()));

                // Executa o comando SQL
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Empréstimo registrado com sucesso."); // Cria uma janela para falar que o empréstimo foi cadastrado com sucesso
            } catch (Exception e) {
                // Mensagem de erro em caso de falha
                JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage()); // Cria uma janela para falar que ocorreu um erro
            }
        }

        // Método para excluir um empréstimo
        public void excluir(int idEmprestimo) {
            // Cria uma Query para executar um comando no SQL para excluir um empréstimo
            String query = "DELETE FROM emprestimos WHERE id_emprestimo = ?";

            // Estrutura try catch para tratamento de erros
            try (Connection conn = DriverManager.getConnection(url, usuario, senha);
                    PreparedStatement pst = conn.prepareStatement(query)) {

                // Exclui o id selecionado (com a query anterior)
                pst.setInt(1, idEmprestimo);
                int linhasAfetadas = pst.executeUpdate();

                // Se as linhas afetadas forem maior que 0, abre a janela para falar que o empréstimo foi excluído com sucesso
                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(null, "Empréstimo excluído com sucesso.");
                } else {
                    // Se as linhas afetadas forem menor que 0, fala que nenhum id foi encontrado
                    JOptionPane.showMessageDialog(null, "Nenhum empréstimo encontrado com o ID informado.");
                }
            } catch (Exception e) {
                // Aponta um erro ao excluir os empréstimos
                JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e.getMessage());
            }
        }

        // Método para exibir empréstimos
        public List<Emprestimo> listarTodos() {
            // Cria um array para armazenar os valores que serão pegos no SQL
            List<Emprestimo> emprestimos = new ArrayList<>();
            // Cria uma query para executar uma consulta que seleciona os empréstimos no banco de dados
            String query = "SELECT * FROM emprestimos";

            // Estrutura try catch para tratamento de erros
            try (Connection conn = DriverManager.getConnection(url, usuario, senha);
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(query)) {

                // Enquanto a lista tiver continuidade, exiba a próxima linha da lista
                while (rs.next()) {
                    Emprestimo emprestimo = new Emprestimo(
                            rs.getInt("id_emprestimo"),
                            rs.getInt("id_cliente"),
                            rs.getInt("id_livro"),
                            rs.getDate("data_emprestimo"),
                            rs.getDate("prazo_emprestimo")
                    );
                    emprestimos.add(emprestimo);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
            }

            return emprestimos;
        }

        public List<Emprestimo> buscarEmprestimosPorClienteId(String clienteId) {
            List<Emprestimo> emprestimos = new ArrayList<>();
            String sql = "SELECT * FROM emprestimos WHERE id_cliente = ?";

            try (Connection conn = DriverManager.getConnection(url, usuario, senha);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, clienteId); // Define o parâmetro da query
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Emprestimo emprestimo = new Emprestimo();
                    emprestimo.setIdEmprestimo(rs.getInt("id_emprestimo"));
                    emprestimo.setIdCliente(rs.getInt("id_cliente"));
                    emprestimo.setIdLivro(rs.getInt("id_livro"));
                    emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
                    emprestimo.setPrazoEmprestimo(rs.getDate("prazo_emprestimo"));

                    emprestimos.add(emprestimo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return emprestimos;
        }

    //     public List<Emprestimo> listarPorCliente(String clienteId) {
    //        List<Emprestimo> resultado = new ArrayList<>();
    //        for (Emprestimo emprestimo : emprestimos) {
    //            if (String.valueOf(emprestimo.getIdCliente()).equals(clienteId)) {
    //                resultado.add(emprestimo);
    //            }
    //        }
    //        return resultado;
    //    }
    }

package classes;

import Interface.Livros;//Importa a classe Interface
import java.sql.*;//Importa todos os itens da bibliioteca sql
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;//Importa as Janelas que serão úteis para indicar os tratamentos de erros

// Classe responsável pela interação com o banco de dados para manipular informações de livros
public class LivroDAO {

    // Credenciais para conexão com o banco de dados

    private final String url = "jdbc:mysql://localhost:3306/sistema_bibliotecario";
    private final String usuario = "root";
    private final String senha = "";

    // Método para inserir um livro
    public void salvar(Livro livro) {
        //Cria uma Query para aplicar o comando INSERT no banco de dados
        String query = "INSERT INTO livros (titulo, autor, genero, data_publicacao, editora) VALUES (?, ?, ?, ?, ?)";
        // Bloco try-with-resources para garantir que os recursos sejam fechados automaticamente
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
                PreparedStatement pst = conn.prepareStatement(query)) {

            // Configura os parâmetros da consulta com os dados do livro
            pst.setString(1, livro.getTitulo());
            pst.setString(2, livro.getAutor());
            pst.setString(3, livro.getGenero());
            pst.setDate(4, Date.valueOf(livro.getDataPublicacao()));
            pst.setString(5, livro.getEditora());

            // Executa o comando SQL
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Livro inserido com sucesso.");//Cria uma Janela para falar que o livro foi cadastrado com sucesso
        } catch (Exception e) {
            //Mensagem de erro em caso de falha
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());//Cria uma Janela para falar que ocorreu um erro
        }
    }

    // Método para excluir um livro
    public void excluir(int idLivro) {
        //Cria uma Query para executar um comando no sql para excluir um livro
        String query = "DELETE FROM livros WHERE id_livro = ?";

        //Estrutura try catch para tratamento de erros
        //Conecta com o banco de dados
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
                //Cria uma variavel para armazenar a query
                PreparedStatement pst = conn.prepareStatement(query)) {

            //Exclui o id selecionado (com a query anterior)
            pst.setInt(1, idLivro);
            int linhasAfetadas = pst.executeUpdate();

            //Se as linhas afetadas forem maior que 0 ele abre a janela para falar que o livro foi excluido com sucesso
            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(null, "Livro excluído com sucesso.");
            } else {
                //Se as linhas afetadas forem menor que 0 falará que nenhum id foi encontrado
                JOptionPane.showMessageDialog(null, "Nenhum livro encontrado com o ID informado.");
            }
        } catch (Exception e) {
            //Aponta um erro ao excluir os livros
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e.getMessage());
        }
    }

    // Método para exibir livros
    public List<Livro> listarTodos() {
        //Cria um array para armazenar os valores que serão pegos no sql
        List<Livro> livros = new ArrayList<>();
        //Cria uma query para executar uma query que selecionará os livros no banco de dados
        String query = "SELECT * FROM livros";

        //Estrutura try catch para tratamento de erros
        //Conecção com o banco de dados
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            //Equanto a lista tiver continuidade, exiba a próxima lina da lista
            while (rs.next()) {
                Livro livro = new Livro(
                        //captura os métodos dos livros da classe LIvros.Java
                        rs.getInt("id_livro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero"),
                        rs.getString("data_publicacao"),
                        rs.getString("editora")
                );
                livros.add(livro);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
            //Abre uma Janela indicando erros
        }

        return livros;
    }
    
    // Método para buscar livros com filtros dinâmicos
public List<Livro> buscarLivros(String id, String titulo, String autor, String genero, String dataPublicacao, String editora) {
    List<Livro> livros = new ArrayList<>();
    String query = "SELECT * FROM livros WHERE "
            + "(? IS NULL OR id_livro = ?) AND "
            + "(? IS NULL OR titulo LIKE ?) AND "
            + "(? IS NULL OR autor LIKE ?) AND "
            + "(? IS NULL OR genero LIKE ?) AND "
            + "(? IS NULL OR data_publicacao = ?) AND "
            + "(? IS NULL OR editora LIKE ?)";

    try (Connection conn = DriverManager.getConnection(url, usuario, senha);
         PreparedStatement pst = conn.prepareStatement(query)) {

        // Define os parâmetros da consulta
        pst.setString(1, id.isEmpty() ? null : id);
        pst.setString(2, id.isEmpty() ? null : id);
        pst.setString(3, titulo.isEmpty() ? null : "%" + titulo + "%");
        pst.setString(4, titulo.isEmpty() ? null : "%" + titulo + "%");
        pst.setString(5, autor.isEmpty() ? null : "%" + autor + "%");
        pst.setString(6, autor.isEmpty() ? null : "%" + autor + "%");
        pst.setString(7, genero.isEmpty() ? null : "%" + genero + "%");
        pst.setString(8, genero.isEmpty() ? null : "%" + genero + "%");
        pst.setString(9, dataPublicacao.isEmpty() ? null : dataPublicacao);
        pst.setString(10, dataPublicacao.isEmpty() ? null : dataPublicacao);
        pst.setString(11, editora.isEmpty() ? null : "%" + editora + "%");
        pst.setString(12, editora.isEmpty() ? null : "%" + editora + "%");

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                livros.add(new Livro(
                        rs.getInt("id_livro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero"),
                        rs.getString("data_publicacao"),
                        rs.getString("editora")
                ));
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar livros: " + e.getMessage());
    }
    return livros;
}
public void atualizar(Livro livro) {
    String query = "UPDATE livros SET titulo = ?, autor = ?, editora = ?, genero = ?, data_publicacao = ? WHERE id_livro = ?";

    try (Connection conn = DriverManager.getConnection(url, usuario, senha);
         PreparedStatement pst = conn.prepareStatement(query)) {

        // Configura os parâmetros da consulta
        pst.setString(1, livro.getTitulo());
        pst.setString(2, livro.getAutor());
        pst.setString(3, livro.getEditora());
        pst.setString(4, livro.getGenero());
        pst.setString(5, livro.getDataPublicacao());
        pst.setInt(6, livro.getIdLivro());

        int linhasAfetadas = pst.executeUpdate(); // Executa a atualização

        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Livro atualizado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum livro encontrado com o ID fornecido.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar livro: " + e.getMessage());
    }
}

    public Livro buscarPorId(int idLivro) {
    Livro livro = null; // Inicializa como nulo caso o cliente não seja encontrado
    String query = "SELECT * FROM livros WHERE id_livro = ?";

    try (Connection conn = DriverManager.getConnection(url, usuario, senha);
         PreparedStatement pst = conn.prepareStatement(query)) {

        pst.setInt(1, idLivro); // Define o parâmetro da consulta

        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                // Se encontrar o cliente, cria um objeto com os dados
                livro = new Livro(
                        rs.getInt("id_livro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero"),
                        rs.getString("data_publicacao"),
                        rs.getString("editora")
                );
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar livro: " + e.getMessage());
    }
    return livro;
}

}

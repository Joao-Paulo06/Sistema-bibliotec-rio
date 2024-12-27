// Classe ClienteDAO
package classes;

import java.sql.*;//importa todos os itens da biblioteca sql
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;//Importa a biblioteca necessária para as fazzer as janelas úteis para os tratamentos de erros

// Classe responsável pela interação com o banco de dados para manipular informações dos clientes
public class ClienteDAO {

    // Credenciais para conexão com o banco de dados

    private final String url = "jdbc:mysql://localhost:3306/sistema_bibliotecario";
    private final String usuario = "root";
    private final String senha = "";

    // Método para inserir um cliente
    public void salvar(Cliente cliente) {
        //Cria uma Query para aplicar o comando INSERT no banco de dados
        String query = "INSERT INTO clientes (nome,email,endereco,telefone,nascimento) VALUES (?, ?, ?, ?, ?)";
        // Bloco try-with-resources para garantir que os recursos sejam fechados automaticamente
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
                PreparedStatement pst = conn.prepareStatement(query)) {

            // Configura os parâmetros da consulta com os dados do Cliente
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEmail());
            pst.setString(3, cliente.getEndereco());
            pst.setString(4, cliente.getTelefone());
            pst.setDate(5, Date.valueOf(cliente.getNascimento()));

            // Executa o comando SQL    
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
        }
    }

    // Método para excluir um cliente
    public void excluir(int idCliente) {
        //Cria uma Query para executar um comando no sql para excluir um livro
        String query = "DELETE FROM clientes WHERE id_cliente = ?";

        //Estrutura try catch para tratamento de erros
        //Conecta com o banco de dados
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
                PreparedStatement pst = conn.prepareStatement(query)) {

            //Exclui o id selecionado (com a query anterior)
            pst.setInt(1, idCliente);
            int linhasAfetadas = pst.executeUpdate();

            //Se as linhas afetadas forem maior que 0 ele abre a janela para falar que o livro foi excluido com sucesso
            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.");
            } else {
                //Se as linhas afetadas forem menor que 0 falará que nenhum id foi encontrado
                JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado com o ID informado.");
            }
        } catch (Exception e) {
            //Mensagem de erro em caso de falha
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    // Método para exibir clientes
    public List<Cliente> listarTodos() {
        //Cria um array para armazenar os valores que serão pegos no sql
        List<Cliente> clientes = new ArrayList<>();
        //Cria uma query para executar uma query que selecionará os livros no banco de dados
        String query = "SELECT * FROM clientes";

        //Estrutura try catch para tratamento de erros
        //Conecção com o banco de dados
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            //Equanto a lista tiver continuidade, exiba a próxima lina da lista
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        //captura os métodos dos livros da classe LIvros.Java
                        rs.getInt("id_cliente"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("nascimento")   
                );
                clientes.add(cliente);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
            //Abre uma Janela indicando erros
        }
        
        
        
        return clientes;
    }
    
    public Cliente buscarPorId(int idCliente) {
    Cliente cliente = null; // Inicializa como nulo caso o cliente não seja encontrado
    String query = "SELECT * FROM clientes WHERE id_cliente = ?";

    try (Connection conn = DriverManager.getConnection(url, usuario, senha);
         PreparedStatement pst = conn.prepareStatement(query)) {

        pst.setInt(1, idCliente); // Define o parâmetro da consulta

        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                // Se encontrar o cliente, cria um objeto com os dados
                cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("nascimento")
                );
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage());
    }
    return cliente; // Retorna o cliente ou null caso não encontre
}

    public void atualizar(Cliente cliente) {
    String query = "UPDATE clientes SET nome = ?, email = ?, endereco = ?, telefone = ?, nascimento = ? WHERE id_cliente = ?";

    try (Connection conn = DriverManager.getConnection(url, usuario, senha);
         PreparedStatement pst = conn.prepareStatement(query)) {

        // Configura os parâmetros da consulta
        pst.setString(1, cliente.getNome());
        pst.setString(2, cliente.getEmail());
        pst.setString(3, cliente.getEndereco());
        pst.setString(4, cliente.getTelefone());
        pst.setString(5, cliente.getNascimento());
        pst.setInt(6, cliente.getIdCliente());

        int linhasAfetadas = pst.executeUpdate(); // Executa a atualização

        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado com o ID fornecido.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage());
    }
}

// Método para buscar livros com filtros dinâmicos
public List<Cliente> buscarClientes(String id, String nome, String email, String endereco, String telefone, String nascimento) {
    List<Cliente> clientes = new ArrayList<>();
    String query = "SELECT * FROM clientes WHERE 1=1";

    // Constrói a query dinamicamente
    if (id != null && !id.isEmpty()) {
        query += " AND id_cliente = ?";
    }
    if (nome != null && !nome.isEmpty()) {
        query += " AND nome LIKE ?";
    }
    if (email != null && !email.isEmpty()) {
        query += " AND email LIKE ?";
    }
    if (endereco != null && !endereco.isEmpty()) {
        query += " AND endereco LIKE ?";
    }
    if (telefone != null && !telefone.isEmpty()) {
        query += " AND telefone LIKE ?";
    }
    if (nascimento != null && !nascimento.isEmpty()) {
        query += " AND nascimento LIKE ?";
    }

    try (Connection conn = DriverManager.getConnection(url, usuario, senha);
         PreparedStatement pst = conn.prepareStatement(query)) {

        // Preenche os parâmetros dinamicamente
        int index = 1;
        if (id != null && !id.isEmpty()) {
            pst.setInt(index++, Integer.parseInt(id));
        }
        if (nome != null && !nome.isEmpty()) {
            pst.setString(index++, "%" + nome + "%");
        }
        if (email != null && !email.isEmpty()) {
            pst.setString(index++, "%" + email + "%");
        }
        if (endereco != null && !endereco.isEmpty()) {
            pst.setString(index++, "%" + endereco + "%");
        }
        if (telefone != null && !telefone.isEmpty()) {
            pst.setString(index++, "%" + telefone + "%");
        }
        if (nascimento != null && !nascimento.isEmpty()) {
            pst.setString(index++, "%" + nascimento + "%");
        }

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("nascimento")
                );
                clientes.add(cliente);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar: " + e.getMessage());
    }

    return clientes;
}


    
    
}
package principal;
import Interface.Login;
//import java.util.List;
//import classes.Livro;
//import classes.LivroDAO;

public class SistemaBibliotecario10 {
    public static void main(String[] args) {
//        LivroDAO livroDAO = new LivroDAO();
//
//        // Criar e salvar um livro
//        Livro livro = new Livro(0, "Título Exemplo", "Autor Exemplo", "Gênero Exemplo", "2023-12-01", "Editora Exemplo");
//        livroDAO.salvar(livro);
//
//        // Listar todos os livros
//        List<Livro> livros = livroDAO.listarTodos();
//        for (Livro l : livros) {
//            System.out.println("ID: " + l.getIdLivro() + ", Título: " + l.getTitulo());
//        }
//
//        // Excluir um livro
//        livroDAO.excluir(1);
//        
           // Inicia a interface de login
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
}
}

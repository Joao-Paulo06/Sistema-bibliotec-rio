package classes;

import java.util.Date;

public class Emprestimo {

    // Atributos da classe
    private int idEmprestimo;
    private int idCliente;
    private int idLivro;
    private Date dataEmprestimo;
    private Date prazoEmprestimo;

    // Construtor padrão
    public Emprestimo() {
    }

    // Construtor com parâmetros
    public Emprestimo(int idEmprestimo, int idCliente, int idLivro, Date dataEmprestimo, Date prazoEmprestimo) {
        this.idEmprestimo = idEmprestimo;
        this.idCliente = idCliente;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.prazoEmprestimo = prazoEmprestimo;
    }

    public Emprestimo(int i, int idCliente, int idLivro, String dataEmprestimo, String prazoEmprestimo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Métodos Getters e Setters
    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getPrazoEmprestimo() {
        return prazoEmprestimo;
    }

    public void setPrazoEmprestimo(Date prazoEmprestimo) {
        this.prazoEmprestimo = prazoEmprestimo;
    }

    // Método para exibir informações do empréstimo
    @Override
    public String toString() {
        return "Empréstimo [ID Empréstimo=" + idEmprestimo + ", ID Cliente=" + idCliente + ", ID Livro=" + idLivro +
               ", Data Empréstimo=" + dataEmprestimo + ", Prazo Empréstimo=" + prazoEmprestimo + "]";
    }
}

package classes;

public class Cliente {

    //Atributos da classe clientes

    private int idCliente;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String nascimento;

    // Construtor padrão
    public Cliente() {
    }

    // Construtor com todos os atributos
    public Cliente(int idCliente, String nome, String email, String telefone, String endereco, String nascimento) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.nascimento = nascimento;
         // Construtor padrão
        // Este construtor permite criar um objeto Livro sem inicializar os atributos imediatamente
    }

// Construtor com parâmetros
// Permite criar um objeto Livro já com os atributos inicializados
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public Object getid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

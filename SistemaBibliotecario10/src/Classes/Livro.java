    package classes;

    public class Livro {

        // Atributos da classe
        private int idLivro;
        private String titulo;
        private String autor;
        private String genero;
        private String dataPublicacao;
        private String editora;

        // Construtor padrão
        // Este construtor permite criar um objeto Livro sem inicializar os atributos imediatamente
        public Livro() {
        }

    // Construtor com parâmetros
    // Permite criar um objeto Livro já com os atributos inicializados
        public Livro(int idLivro, String titulo, String autor, String genero, String dataPublicacao, String editora) {
            this.idLivro = idLivro;
            this.titulo = titulo;
            this.autor = autor;
            this.genero = genero;
            this.dataPublicacao = dataPublicacao;
            this.editora = editora;
        }

        // Métodos Getters e Setters
        // Fornecem acesso controlado aos atributos da classe
        public int getIdLivro() {
            return idLivro;
        }

        public void setIdLivro(int idLivro) {
            this.idLivro = idLivro;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public String getDataPublicacao() {
            return dataPublicacao;
        }

        public void setDataPublicacao(String dataPublicacao) {
            this.dataPublicacao = dataPublicacao;
        }

        public String getEditora() {
            return editora;
        }

        public void setEditora(String editora) {
            this.editora = editora;
        }
    }

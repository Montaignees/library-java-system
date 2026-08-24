package service;
import model.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroService {

    private List<Livro> livros = new ArrayList<>();

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public Livro buscarLivro(String isbn) {
        for (int i = 0; i < livros.size(); i++) {

            Livro livroAtual = livros.get(i);

            if (isbn.equals(livroAtual.getIsbn())) {
                return livroAtual;
            }
        }
        return null;
    }
}

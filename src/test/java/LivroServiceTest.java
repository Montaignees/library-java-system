import model.Livro;
import org.junit.jupiter.api.Test;
import service.LivroService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LivroServiceTest {

    @Test
    void deveCadastrarLivro() {

        LivroService service = new LivroService();

        Livro livro = new Livro(
                "Dom Casmurro",
                "Machado de Assis",
                "123",
                "Romance",
                5,
                5
        );

        service.cadastrarLivro(livro);

        assertEquals(1, service.getLivros().size());
    }

    void deveBuscarLivro(int isbn) {


    }
}
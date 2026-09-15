import model.Emprestimo;
import model.Leitor;
import model.Livro;
import model.Multa;
import service.EmprestimoService;
import service.LeitorService;
import service.LivroService;
import service.MultaService;
import ui.Menu;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        LivroService livroService = new LivroService();
        LeitorService leitorService = new LeitorService();
        EmprestimoService emprestimoService = new EmprestimoService();
        MultaService multaService = new MultaService();

        Livro livro1 = new Livro(
                "Dom Casmurro",
                "Machado de Assis",
                "9788535902778",
                "Romance",
                5,
                5
        );

        Livro livro4 = new Livro(
                "Dom quixote",
                "miguel de cenvantes",
                "9788535902778",
                "Romance",
                5,
                5
        );

        Livro livro2 = new Livro(
                "1984",
                "George Orwell",
                "9780451524935",
                "Ficção",
                3,
                3
        );

        Livro livro3 = new Livro(
                "O Hobbit",
                "J.R.R. Tolkien",
                "9780261102217",
                "Fantasia",
                4,
                4
        );

        livroService.cadastrarLivro(livro1);
        livroService.cadastrarLivro(livro2);
        livroService.cadastrarLivro(livro3);
        livroService.cadastrarLivro(livro4);

        Leitor leitor1 = new Leitor(
                "Lucas",
                "62999999999",
                "lucas@email.com",
                "12345678901"
        );

        Leitor leitor2 = new Leitor(
                "João",
                "62988888888",
                "joao@email.com",
                "23456789012"
        );

        Leitor leitor3 = new Leitor(
                "Maria",
                "62977777777",
                "maria@email.com",
                "34567890123"
        );

        leitorService.cadastrarLeitor(leitor1);
        leitorService.cadastrarLeitor(leitor2);
        leitorService.cadastrarLeitor(leitor3);

        Emprestimo emprestimo = new Emprestimo(
                leitor2,
                livro1,
                1
        );

        emprestimoService.getEmprestimos().add(emprestimo);

        Multa multa = new Multa(
                10.0,
                LocalDate.now(),
                false,
                emprestimo
        );

        multaService.getMultas().add(multa);


        Menu menu = new Menu(
                livroService,
                leitorService,
                emprestimoService,
                multaService
        );

        menu.iniciar();
    }
}
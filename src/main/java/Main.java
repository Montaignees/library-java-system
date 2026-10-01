import model.Leitor;
import model.Livro;
import service.EmprestimoService;
import service.LeitorService;
import service.LivroService;
import service.MultaService;
import ui.Menu;

public class Main {

    public static void main(String[] args) {
        LivroService livroService = new LivroService();
        LeitorService leitorService = new LeitorService();
        EmprestimoService emprestimoService = new EmprestimoService();
        MultaService multaService = new MultaService();

        carregarDadosIniciais(livroService, leitorService, emprestimoService);

        Menu menu = new Menu(
                livroService,
                leitorService,
                emprestimoService,
                multaService
        );

        menu.iniciar();
    }

    private static void carregarDadosIniciais(
            LivroService livroService,
            LeitorService leitorService,
            EmprestimoService emprestimoService) {

        Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis",
                "9788535902778", "Romance", 5, 5);

        Livro livro2 = new Livro("1984", "George Orwell",
                "9780451524935", "Ficção", 3, 3);

        Livro livro3 = new Livro("O Hobbit", "J.R.R. Tolkien",
                "9780261102217", "Fantasia", 4, 4);

        Livro livro4 = new Livro("Dom Quixote", "Miguel de Cervantes",
                "9780060934347", "Romance", 5, 5);

        livroService.cadastrarLivro(livro1);
        livroService.cadastrarLivro(livro2);
        livroService.cadastrarLivro(livro3);
        livroService.cadastrarLivro(livro4);

        Leitor leitor1 = new Leitor("Lucas", "62999999999",
                "lucas@email.com", "82789505047");

        Leitor leitor2 = new Leitor("João", "62988888888",
                "joao@email.com", "69529878001");

        Leitor leitor3 = new Leitor("Maria", "62977777777",
                "maria@email.com", "24689609047");

        leitorService.cadastrarLeitor(leitor1);
        leitorService.cadastrarLeitor(leitor2);
        leitorService.cadastrarLeitor(leitor3);

        emprestimoService.cadastrarEmprestimo(1, livro1, leitor2);
    }
}

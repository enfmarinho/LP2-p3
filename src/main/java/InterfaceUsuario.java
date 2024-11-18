import java.util.Scanner;

public class InterfaceUsuario {
  private boolean pare = false;
  private Input inputUsuario;
  private BancoDados bancoDados;

  private enum Input {
    remover, adicionar, exibir, alterar, concluir
  }

  public InterfaceUsuario(String path) {
    bancoDados = new BancoDados(path);
  }

  public void run() {
    while (!pare) {
      renderize();
      processe_input();
    }
  }

  public void renderize() {
    System.out.println("Comandos disponiveis: (digite o número ou o nome)");
    System.out.println("1-Adicionar, 2-Remover, 3-Exibir, 4-Alterar, 5-Concluir, 6-iniciar, 7-Fechar ou Sair");
  }

  public void processe_input() {
    Scanner scanner = new Scanner(System.in);
    String string_input = scanner.nextLine().trim().toLowerCase();

    if (string_input.equals("adicionar") || string_input.equals("1")) {
      bancoDados.adicionar(ler_tarefa());
    } else if (string_input.equals("remover") || string_input.equals("2")) {
      bancoDados.remover(ler_titulo());
    } else if (string_input.equals("exibir") || string_input.equals("3")) {
      bancoDados.exibir(ler_ordem());
    } else if (string_input.equals("alterar") || string_input.equals("4")) {
      bancoDados.alterar(ler_titulo(), ler_dado_a_alterar(), ler_novo_valor());
    } else if (string_input.equals("concluir") || string_input.equals("5")){
      bancoDados.concluir(ler_titulo());
    } else if (string_input.equals("iniciar") || string_input.equals("6")) {
      bancoDados.iniciar(ler_titulo());
    } else if (string_input.equals("fechar") || string_input.equals("fechar") || string_input.equals("7")) {
      pare = true;
    } else {
      System.out.println("Comando invalido! Tente de novo");
      processe_input();
      return; // Otimizacao de cauda
    }
  }

  private String ler_titulo() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Título: ");
    return scanner.nextLine().trim();
  }

  private Tarefa ler_tarefa() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Título: ");
    String titulo = scanner.nextLine().trim();
    System.out.print("Descrição: ");
    String descricao = scanner.nextLine().trim();
    System.out.print("Prioridade: ");
    int prioridade = scanner.nextInt();
    scanner.nextLine();
    System.out.print("Prazo(dd/mm/aaaa): ");
    String prazo = scanner.nextLine().trim();
    return new Tarefa(titulo, descricao, prioridade, prazo);
  }

  private BancoDados.Ordem ler_ordem() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ordem de exibição (1-Prioridade, 2-Prioridade invertida, 3-Prazo, 4-Prazo invertido): ");
    String alterar = scanner.nextLine().trim().toLowerCase();
    if (alterar.equals("prioridade") || alterar.equals("1")) {
      return BancoDados.Ordem.prioridade;
    } else if (alterar.equals("prioridade invertida") || alterar.equals("2")) {
      return BancoDados.Ordem.prioridadeInvertida;
    } else if (alterar.equals("prazo") || alterar.equals("3")) {
      return BancoDados.Ordem.prazo;
    } else if (alterar.equals("prazo invertido") || alterar.equals("4")) {
      return BancoDados.Ordem.prazoInvertido;
    } else {
      System.out.println("Valor inválido, tente novamente!");
      return ler_ordem();
    }
  }

  private Tarefa.DadosTarefa ler_dado_a_alterar() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Valor a alterar (1-Título, 2-Descrição, 3-Prioridade, 4-Prazo): ");
    String alterar = scanner.nextLine().trim().toLowerCase();
    if (alterar.equals("título") || alterar.equals("titulo") || alterar.equals("1")) {
      return Tarefa.DadosTarefa.titulo;
    } else if (alterar.equals("descrição") || alterar.equals("descricao") || alterar.equals("2")) {
      return Tarefa.DadosTarefa.descricao;
    } else if (alterar.equals("prioridade") || alterar.equals("3")) {
      return Tarefa.DadosTarefa.prioridade;
    } else if (alterar.equals("prazo") || alterar.equals("4")) {
      return Tarefa.DadosTarefa.prazo;
    } else {
      System.out.print("Valor inválido, tente novamente!");
      return ler_dado_a_alterar();
    }
  }

  private String ler_novo_valor() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Novo valor: ");
    return scanner.nextLine().trim();
  }
}

import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;

public class BancoDados {
  private List<Tarefa> tarefas = new LinkedList<Tarefa>();
  String path;

  public enum Ordem {
    prioridade, prazo, prioridadeInvertida, prazoInvertido 
  }

  public BancoDados(String path) { // Carrega os dados
    this.path = path;
    // TODO
  }

  public void close() { // Salva os dados
    // TODO
  }

  public void alterar(String titulo, Tarefa.DadosTarefa dado_alterar, String novo_valor) {
    for (Tarefa tarefa : tarefas) {
      if (tarefa.getTitulo().equals(titulo)) {
        System.out.println("A seguinte tarefa será alterada: ");
        tarefa.imprimir();
        tarefa.alterar(dado_alterar, novo_valor);
        System.out.println("Agora está:");
        tarefa.imprimir();
        System.out.println();
        return;
      }
    }

    System.out.println("Nenhuma tarefa foi alterada, pois não há uma tarefa com esse título!");
  }

  public void remover(String titulo) {
    ListIterator<Tarefa> iterador = tarefas.listIterator(0);
    while(iterador.hasNext()) {
      Tarefa tarefa = iterador.next();
      if (tarefa.getTitulo().equals(titulo)) {
        System.out.println("A seguinte tarefa foi removida");
        tarefa.imprimir();
        System.out.println();
        iterador.remove();
        return;
      }
    }

    System.out.println("Nenhuma tarefa foi removida, pois não há uma tarefa com esse título!");
    System.out.println();
  }

  public void adicionar(Tarefa nova_tarefa) {
    for (Tarefa tarefa : tarefas) {
      if (tarefa.getTitulo().equals(nova_tarefa.getTitulo())){
        System.out.println("Essa tarefa não pode ser adicionada, pois já existe uma tarefa com esse título. Os títulos devem ser únicos!");
        return;
      }
    }
    tarefas.add(nova_tarefa);
  }

  public void exibir(Ordem ordem) { // Exibi os dados na ordem expecificada
    switch (ordem) {
      case prioridade:
        Collections.sort(tarefas, Comparator.comparing(Tarefa::getPrioridade).reversed());
        break;
      case prioridadeInvertida:
        Collections.sort(tarefas, Comparator.comparing(Tarefa::getPrioridade));
        break;
      case prazo:
        Collections.sort(tarefas, Comparator.comparing(Tarefa::getPrazo).reversed());
        break;
      case prazoInvertido:
        Collections.sort(tarefas, Comparator.comparing(Tarefa::getPrazo));
        break;
      default:
        break;
    }

    for (Tarefa tarefa : tarefas) {
      tarefa.imprimir();
      System.out.println();
    }
  }

  public void concluir(String titulo) {
    for (Tarefa tarefa : tarefas) {
      if (tarefa.getTitulo().equals(titulo)) {
        tarefa.concluir();
      }
    }
  }

  public void iniciar(String titulo) {
    for (Tarefa tarefa : tarefas) {
      if (tarefa.getTitulo().equals(titulo)) {
        tarefa.iniciar();
      }
    }
  }
}

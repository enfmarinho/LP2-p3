import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Scanner; 
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;   
import java.io.IOException; 

public class BancoDados {
  private List<Tarefa> tarefas = new LinkedList<Tarefa>();
  String path;

  public enum Ordem {
    prioridade, prazo, prioridadeInvertida, prazoInvertido 
  }

  public BancoDados(String path) { // Carrega os dados
    this.path = path;
    try {
      File dataFile = new File(path);
      Scanner reader = new Scanner(dataFile);
      while (reader.hasNextLine()) {
        if (!lerTarefa(reader)) {
          System.out.println("Ocorreu um erro lendo uma tarefa do banco de dados");
        }
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Arquivo do banco de dados nao foi encontrado. Caminho: " + path);
    }
  }

  private Boolean lerTarefa(Scanner reader) {
    String titulo = reader.nextLine();
    titulo = titulo.substring(titulo.indexOf(' ') + 1);

    String descricao = reader.nextLine();
    descricao = descricao.substring(descricao.indexOf(' ') + 1);

    String prioridade = reader.nextLine();
    prioridade = prioridade.substring(prioridade.indexOf(' ') + 1);

    long prioridadeLong;
    try {
      prioridadeLong = Long.parseLong(prioridade);
    } catch (NumberFormatException e) {
      System.out.println("Prioridade: " + prioridade);
      return false;
    }

    String prazo = reader.nextLine();
    prazo = prazo.substring(prazo.indexOf(' ') + 1);

    String concluido = reader.nextLine();
    concluido = concluido.substring(concluido.indexOf(' ') + 1);

    Boolean concluidoBool = false;
    if (concluido.equals("true")) {
      concluidoBool = true;
    } else if (!concluido.equals("false")) {
      System.out.println("concluido: " + concluido);
      return false;
    }
    
    tarefas.add(new Tarefa(titulo, descricao, prioridadeLong, prazo, concluidoBool));
    return true;
  }

  public void salvarDados() { // Salva os dados
    try {
      FileWriter writer = new FileWriter(path);
      for (Tarefa tarefa : tarefas) {
        writer.write(tarefa.toString());
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("Ocorreu um erro ao tentar salvar o banco de dados em " + path);
      e.printStackTrace();
    }
  }

  public void alterar(String titulo, Tarefa.DadosTarefa dado_alterar, String novo_valor) {
    for (Tarefa tarefa : tarefas) {
      if (tarefa.getTitulo().equals(titulo)) {
        System.out.println("A seguinte tarefa será alterada: ");
        System.out.println(tarefa);
        tarefa.alterar(dado_alterar, novo_valor);
        System.out.println("Agora está:");
        System.out.println(tarefa);
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
        System.out.println(tarefa);
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
      System.out.println(tarefa);
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

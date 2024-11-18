import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Tarefa {
    private long prazo;
    private long prioridade;
    private String titulo;
    private String descricao;
    private boolean concluido = false;

    public enum DadosTarefa{
        prioridade, titulo, descricao, prazo
    }

    public Tarefa(String titulo, String descricao, long prioridade, String prazo){
        this.prioridade = prioridade;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = converter_prazo(prazo);
    }

    public Tarefa(String titulo){
        this.titulo = titulo;
        this.descricao = "";
        this.prioridade = 0;
        this.prazo = 0;
    }

    public void imprimir(){
      System.out.println("Título: " + titulo);
      System.out.println("Descrição: " + descricao);
      System.out.println("Prioridade: " + prioridade);
      System.out.println("Prazo: " + LocalDate.now().plusDays(prazo));
      System.out.println("Concluido: " + concluido);
    }

    public void concluir() {
      concluido = true;
    }

    public void iniciar() {
      concluido = false;
    }

    public void alterar(DadosTarefa dadosAlterar, String novoValor){ // atribui novo_valor a dado_a_alterar
      switch (dadosAlterar) {
        case titulo:
          titulo = novoValor;
          break;
        case descricao:
          descricao = novoValor;
          break;
        case prazo:
          prazo = converter_prazo(novoValor);
          break;
        default:
          alterar(dadosAlterar, Integer.parseInt(novoValor));
          break;
      }
    }

    public void alterar(DadosTarefa dadosAlterar, long novoValor){ // atribui novo_valor a dado_a_alterar
      switch (dadosAlterar) {
        case prioridade:
          prioridade = novoValor;
          break;
        default:
          break;
      }
    }

    public String getTitulo(){
        return titulo;
    }

    public long getPrioridade(){
        return prioridade;
    }

    public long getPrazo(){
        return prazo;
    }

    private long converter_prazo(String prazo) {
      LocalDate dataAtual = LocalDate.now();

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate dataPrazo = LocalDate.parse(prazo, formatter);
        
      return ChronoUnit.DAYS.between(dataAtual, dataPrazo);
    }
}

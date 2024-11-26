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

    public Tarefa(String titulo, String descricao, long prioridade, String prazo, Boolean concluido) {
        this.prioridade = prioridade;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = converter_prazo(prazo);
        this.concluido = concluido;
    }

    public Tarefa(String titulo){
        this.titulo = titulo;
        this.descricao = "";
        this.prioridade = 0;
        this.prazo = 0;
        this.concluido = false;
    }

    public String toString() {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      return "Título: " + titulo + "\n" + 
             "Descrição: " + descricao + "\n" +
             "Prioridade: " + prioridade + "\n" + 
             "Prazo: " + LocalDate.now().plusDays(prazo).format(formatter) + "\n" +
             "Concluido: " + concluido + "\n";
    }

    public void concluir() {
      concluido = true;
    }

    public void iniciar() {
      concluido = false;
    }

    public void alterar(DadosTarefa dados_alterar, String novo_valor){ // atribui novo_valor a dado_a_alterar
      switch (dados_alterar) {
        case titulo:
          titulo = novo_valor;
          break;
        case descricao:
          descricao = novo_valor;
          break;
        case prazo:
          prazo = converter_prazo(novo_valor);
          break;
        default:
          alterar(dados_alterar, Integer.parseInt(novo_valor));
          break;
      }
    }

    public void alterar(DadosTarefa dados_alterar, long novo_valor){ // atribui novo_valor a dado_a_alterar
      switch (dados_alterar) {
        case prioridade:
          prioridade = novo_valor;
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
      LocalDate data_atual = LocalDate.now();

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate data_prazo = LocalDate.parse(prazo, formatter);
        
      return ChronoUnit.DAYS.between(data_atual, data_prazo);
    }
}

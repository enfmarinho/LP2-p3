import java.util.Scanner;

public class main {
    public static void main(String[] args) {
      String path = ".data.txt"; // Default path
      if (args.length > 1) {
        path = args[1];
      }
      InterfaceUsuario interfaceUsuario = new InterfaceUsuario(path);
      interfaceUsuario.run();
    }
}

<<<<<<< HEAD

=======
>>>>>>> eab54c51042a11d85d7a8d53c7c603100daddec9
import Sintatico.Sintatico;

public class Compilador {
    public static void main(String[] args) {
        if (args.length == 0) {
			System.out.println("Modo de usar: java -jar NomePrograma NomeArquivoCodigo");
			return;
		}

		Sintatico sintatico = new Sintatico(args[0]);
        sintatico.analisar();
    }
}

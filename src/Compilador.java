import Lexico.Classe;
import Lexico.Lexico;
import Lexico.Token;
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

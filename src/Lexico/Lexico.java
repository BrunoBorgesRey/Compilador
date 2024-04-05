package Lexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Lexico {
    private String nomeArquivo;
    private Token token;
    private BufferedReader br;
    private char caractere;
    private StringBuilder lexema = new StringBuilder();
    private int linha;
    private int coluna;
    ArrayList<String> palavrasReservadas = new ArrayList<String>(Arrays.asList("and", "array","begin","case","const","div","do","downto", "else", "end","file", "for", "function", "goto", "if","in", "label", "mod", "nil", "not","of", "or", "packed", "procedure", "program","record", "repeat", "set", "then", "to","type", "until", "var", "while", "with","integer", "real", "boolean", "char", "string", "write","writeln","read","true","false"));
    
    public boolean isPalavraReservada(String palavra){
        return palavrasReservadas.contains(palavra);
    }

    public Lexico (String nomeArquivo){
        this.nomeArquivo = nomeArquivo;
        //Abrir Arquivo
        String caminhoArquivo = Paths.get(nomeArquivo).toAbsolutePath().toString();

		try {
            BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo, StandardCharsets.UTF_8));
            this.br = br;
            linha =1;
            coluna=1;
            caractere = proximoChar();
            
		} catch (IOException e) {
			System.err.println("Não foi possível abrir o arquivo ou ler do arquivo: " + nomeArquivo);
			e.printStackTrace();
		}
    }

    private char proximoChar() {
        try{ 
            return (char) br.read();
        }catch (IOException e) {
            System.err.println("Não foi possível ler o arquivo ou ler do arquivo: " + nomeArquivo);
            e.printStackTrace();
        }
        return 0;
    }
    
    public Token nextToken(){
        lexema.setLength(0);
        do{
        if (Character.isLetter(caractere)) {
                token = new Token(linha, coluna);
                lexema.append(caractere);
                caractere = proximoChar();
                coluna++;
                while (Character.isLetterOrDigit(caractere) ) {
                    lexema.append(caractere);
                    caractere = proximoChar();
                    coluna++;
                }
                token.setClasse(Classe.identificador);
                if(isPalavraReservada(lexema.toString())){
                    token.setClasse(Classe.palavraReservada);
                }
                token.setValor(new Valor(lexema.toString()));
                return token;
            
            }else if(Character.isDigit(caractere)) {
                token = new Token(linha, coluna);
                lexema.append(caractere);
                caractere = proximoChar();
                coluna++;
                while (Character.isDigit(caractere)) {
                    lexema.append(caractere);
                    caractere = proximoChar();
                    coluna++;
                }
                token.setClasse(Classe.numeroInteiro);
                token.setValor(new Valor(Integer.parseInt(lexema.toString())));
                return token;
            }else if (caractere == ' ') {
                caractere = proximoChar();
                coluna++;
            }else if (caractere == '{') {
                while (caractere !='}') {
                    
                    if(caractere == '\r'){
                        caractere = proximoChar();
                        caractere = proximoChar();
                        linha++;
                        coluna = 1;
                    }else if(caractere == 65535){
                        return new Token(linha, coluna, Classe.EOF);
                    }else{
                        caractere = proximoChar();
                        coluna++;
                    }
                }
            }else if(caractere == '\r'){
                caractere = proximoChar();
                caractere = proximoChar();
                linha++;
                coluna = 1;
            }else if(caractere == '\t')
            {
                caractere = proximoChar();
                coluna +=4;
            } else if (caractere == '+'){
                token = new Token(linha, coluna, Classe.operadorSoma);
                caractere = proximoChar();
                coluna ++;
                return token;
            }else if (caractere == '-'){
                token = new Token(linha, coluna, Classe.operadorSubtracao);
                caractere = proximoChar();
                coluna ++;
                return token;
            }else if (caractere == '*'){
                token = new Token(linha, coluna, Classe.operadorMultiplicacao);
                caractere = proximoChar();
                coluna ++;
                return token;
            }else if (caractere == '/'){
                token = new Token(linha, coluna, Classe.operadorDivicao);
                caractere = proximoChar();
                coluna ++;
                return token;
            }else if (caractere == '='){
                token = new Token(linha, coluna, Classe.operadorIgual);
                caractere = proximoChar();
                coluna ++;
                return token;
            }else if (caractere == '>'){
                caractere = proximoChar();
                if(caractere == '=')
                {
                    token = new Token(linha, coluna, Classe.operadorMaiorIgual);
                    caractere = proximoChar();
                    coluna ++;
                }
                else{
                    token = new Token(linha, coluna, Classe.operadorMaior);
                }
                coluna ++;
                return token;
            }else if (caractere == '<'){
                caractere = proximoChar();
                if(caractere == '=')
                {
                    token = new Token(linha, coluna, Classe.operadorMenorIgual);
                    caractere = proximoChar();
                    coluna ++;
                }else if(caractere == '>'){
                    token = new Token(linha, coluna, Classe.operadorDiferente);
                    caractere = proximoChar();
                    coluna ++;
                }
                else{
                    token = new Token(linha, coluna, Classe.operadorMenor);
                }
                coluna ++;
                return token;
            }else if(caractere == ','){
                token = new Token(linha, coluna, Classe.virgula);
                caractere = proximoChar();
                coluna ++;
                return token;
            }else if(caractere == ';'){
                token = new Token(linha, coluna, Classe.pontoEVirgula);
                caractere = proximoChar();
                coluna ++;
                return token;
            }else if(caractere == '\''){
                caractere = proximoChar();
                coluna ++;
                while (caractere != '\'') {
                    if(caractere == '\r' || caractere == 65535){
                        System.out.println("Erro Léxico");
                        System.exit(-1);
                    }else{
                        lexema.append(caractere);
                        caractere = proximoChar();
                        coluna ++;
                    }
                    
                }
                token = new Token(linha, coluna, Classe.string);
                token.setValor(new Valor(lexema.toString()));
                caractere = proximoChar();
                coluna ++;
                return token;
                
            }else if(caractere == '.'){
                token = new Token(linha, coluna, Classe.ponto);
                caractere = proximoChar();
                coluna ++;
                return token;
            }else if(caractere == '('){
                token = new Token(linha, coluna, Classe.parenteseEquerdo);
                caractere = proximoChar();
                coluna ++;
                return token;
            }else if(caractere == ')'){
                token = new Token(linha, coluna, Classe.parenteseDireito);
                caractere = proximoChar();
                coluna ++;
                return token;
            }else if(caractere == ':'){
                caractere = proximoChar();
                if(caractere == '=')
                {
                    token = new Token(linha, coluna, Classe.atribuicao);
                    caractere = proximoChar();
                    coluna ++;
                }
                else{
                    token = new Token(linha, coluna, Classe.doisPontos);
                }
                coluna ++;
                return token;
            }
            
            //System.out.println("Caractere"+caractere);
            //enquanto caractere nao for final de arquivo
        }while(caractere != 65535);
        return new Token(linha, coluna, Classe.EOF);
    }
}

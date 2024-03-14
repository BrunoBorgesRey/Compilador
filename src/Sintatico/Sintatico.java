package Sintatico;
import Lexico.Classe;
import Lexico.Lexico;
import Lexico.Token;

public class Sintatico {
    private String nomeArquivo;
    private Lexico lexico;
    private Token token;

    public Sintatico(String nomeArquivo){
        this.nomeArquivo = nomeArquivo;
        lexico = new Lexico(nomeArquivo);
    }

    public void analisar() {
        System.out.println("Analisando" + nomeArquivo);
        token = lexico.nextToken();
        programa();
    }

    //<programa> ::= program <id> {A01} ; <corpo> • {A45}
    private void programa(){
        if(token.getClasse() == Classe.palavraReservada && 
        token.getValor().getValorTexto().equals("program")){
            token = lexico.nextToken();
            if(token.getClasse() == Classe.identificador){
                token = lexico.nextToken();
                // {A01}
                if(token.getClasse() == Classe.pontoEVirgula){
                    token = lexico.nextToken();
                    corpo();
                    if (token.getClasse() == Classe.ponto) {
                        token = lexico.nextToken();
                        // {A45}
                    } else {
                        System.err.println(token.getLinha()+","+ token.getColuna()+
                        "(.) Ponto Final esperado no fila do programa");
                    }
                    
                }else {
                    System.err.println(token.getLinha()+","+ token.getColuna()+
                    "(;) Ponto e Virgula esperado depois do nome do programa");
                }
            }else {
                System.err.println(token.getLinha()+","+ token.getColuna()+
                "Nome do programa principal esperado");
            }
        }else {
            System.err.println(token.getLinha()+","+ token.getColuna()+
            "Palavra reservada 'program' esperado no inicio do programa principal");
        }

    }

    //<corpo> ::= <declara> <rotina> {A44} begin <sentencas> end {A46}
    private void corpo() {
        //declara();
        //rotina();
        //{A44}
        if (token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("begin")) {
            token = lexico.nextToken();
            //sentencas();
            if (token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("end")){
            token = lexico.nextToken();
             //{A46}
        } else {
            System.err.println(token.getLinha()+","+ token.getColuna()+
            "Palavra reservada 'end' esperado no final do programa principal");
        }

        } else {
            System.err.println(token.getLinha()+","+ token.getColuna()+
            "Palavra reservada 'begin' esperado no inicio do programa principal");

        }
    }

    //<declara> ::= var <dvar> <mais_dc> | ε
    private void declara() {
        if (token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("var")){
            token = lexico.nextToken();
            //dvar();
            //mais_dc(); 
        }
        
    }
    //<mais_dc> ::=  ; <cont_dc>
    private void mais_dc() {
        if (token.getClasse() == Classe.pontoEVirgula) {
            token = lexico.nextToken();
            //cont_dc();
        }else {
            System.err.println(token.getLinha()+","+ token.getColuna()+
            "(;) esperado no programa depois de var");

        }
        
    }
    //<cont_dc> ::= <dvar> <mais_dc> | ε
    private void cont_dc() {
        //dvar();
        //mais_dc(); 
    }
    //<dvar> ::= <variaveis> : <tipo_var> {A02}
    private void dvar() {
        //variaveis();
        //tipo_var(); 
        //{A02}
    }
    //<tipo_var> ::= integer
    private void tipo_var(){
        if (token.getClasse() == Classe.numeroInteiro) {
            token = lexico.nextToken();
        }else{
            System.err.println(token.getLinha()+","+ token.getColuna()+
            "NumeroInteiro esperado no programa depois de var");

        }

    }
    //<variaveis> ::= <id> {A03} <mais_var>
    //<mais_var> ::=  ,  <variaveis> | ε







}
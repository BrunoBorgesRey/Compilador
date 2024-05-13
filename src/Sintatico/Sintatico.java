package Sintatico;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Lexico.Classe;
import Lexico.Lexico;
import Lexico.Token;

public class Sintatico {
    private String nomeArquivo;
    private Lexico lexico;
    private Token token;
    
    private TabelaSimbolos tabela = new TabelaSimbolos();
    private String rotulo = "";
    private int contRotulo = 1;
    private int offsetVariavel;
    private String nomeArquivoSaida;
    private String caminhoArquivoSaida;
    private BufferedWriter bw;
    private FileWriter fw;
    private static final int TAMANHO_INTEIRO = 4;
    private List<String> variaveis = new ArrayList<String>();
    private List<String> sectionData = new ArrayList<String>();
    private Registro registro;
   

    public Sintatico(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        lexico = new Lexico(nomeArquivo);
        
        nomeArquivoSaida = "queronemver.asm";
        caminhoArquivoSaida = Paths.get(nomeArquivoSaida).toAbsolutePath().toString();
        bw = null;
        fw = null;
        try {
            fw = new FileWriter(caminhoArquivoSaida, Charset.forName("UTF-8"));
            bw = new BufferedWriter(fw);
        } catch (Exception e) {
            System.err.println("Erro ao criar arquivo de saída");
        }
    }

    public void analisar() {
        System.out.println("Analisando" + nomeArquivo);
        token = lexico.nextToken();
        programa();
    }

    private void escreverCodigo(String instrucoes) {
        try {
            if (rotulo.isEmpty()) {
                bw.write(instrucoes + "\n");
            } else {
                bw.write(rotulo + ": " + instrucoes + "\n");
                rotulo = "";
            }
        } catch (IOException e) {
            System.err.println("Erro escrevendo no arquivo de saída");
        }
    }

    private String criarRotulo(String texto) {
        String retorno = "rotulo" + texto + contRotulo;
        contRotulo++;
        return retorno;
    }

    // <programa> ::= program <id> {A01} ; <corpo> • {A45}
    private void programa() {
        if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("program")) {
            token = lexico.nextToken();
            if (token.getClasse() == Classe.identificador) {
                
                // {A01}
                Registro registro = tabela.add(token.getValor().getValorTexto());
                offsetVariavel = 0;
                registro.setCategoria(Categoria.PROGRAMAPRINCIPAL);
                escreverCodigo(("global main"));
                escreverCodigo("extern printf");
                escreverCodigo("extern scanf");
                escreverCodigo("section .text");
                rotulo = "main";
                // escreverCodigo(rotulo+ ":");
                escreverCodigo("\t   ;Entrada do Programa");
                escreverCodigo("\t   push ebp");
                escreverCodigo("\t   mov ebp, esp");
                System.out.println(tabela);
                
                token = lexico.nextToken();
                if (token.getClasse() == Classe.pontoEVirgula) {
                    token = lexico.nextToken();
                    //Analisa o corpo do programa
                    corpo();
                    if (token.getClasse() == Classe.ponto) {
                        token = lexico.nextToken();
                        // {A45}
                        escreverCodigo("\tleave");
                        escreverCodigo("\tret");
                        if (!sectionData.isEmpty()) {
                            escreverCodigo("\nsection .data\n");
                            for (String mensagem : sectionData) {
                                escreverCodigo(mensagem);
                            }
                        }
                        try {
                            bw.close();
                            fw.close();
                        } catch (IOException e) {
                            System.err.println("Erro ao fechar arquivo de saída");
                        }

                        } else {
                            System.err.println(token.getLinha() + "," + token.getColuna() +
                                    "(.) Ponto Final esperado no fila do programa na regra programa");
                        }

                } else {
                    System.err.println(token.getLinha() + "," + token.getColuna() +
                            "(;) Ponto e Virgula esperado depois do nome do programa na regra programa");
                }
            } else {
                System.err.println(token.getLinha() + "," + token.getColuna() +
                        "Nome do programa principal esperado");
            }
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() +
                    "Palavra reservada 'program' esperado no inicio do programa principal");
        }

    }

    // <corpo> ::= <declara> {A44} begin <sentencas> end {A46}
    private void corpo() {
        declara();
        // {A44}
        if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("begin")) {
            token = lexico.nextToken();
            sentencas();
            if (token.getClasse() == Classe.palavraReservada &&
                    token.getValor().getValorTexto().equals("end")) {
                token = lexico.nextToken();
                // {A46}
            } else {
                System.err.println(token.getLinha() + "," + token.getColuna() +
                        " Palavra reservada 'end' esperado no final do programa principal na regra corpo");
            }
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() +
                    "Palavra reservada 'begin' esperado no inicio do programa principal na regra corpo");
        }
    }
//-------------------DECLARAÇÃO DE VARIÁVEIS--------------
    // <declara> ::= var <dvar> <mais_dc> | ε
    private void declara() {
        if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("var")) {
            token = lexico.nextToken();
            dvar();
            mais_dc();
        }

    }

    // <mais_dc> ::= ; <cont_dc>
    private void mais_dc() {
        if (token.getClasse() == Classe.pontoEVirgula) {
            token = lexico.nextToken();
            cont_dc();
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() +
                    "(;) esperado no programa depois de var na regra mais_dc");
        }

    }

    // <cont_dc> ::= <dvar> <mais_dc> | ε
    private void cont_dc() {
        if (token.getClasse() == Classe.identificador) {
            dvar();
            mais_dc();
        }

    }

    // <dvar> ::= <variaveis> : <tipo_var> {A02}
    private void dvar() {
        variaveis();
        if (token.getClasse() == Classe.doisPontos) {
            token = lexico.nextToken();
            tipo_var();
            // {A02}
            int tamanho = 0;
            for (String var : variaveis) {
                tabela.get(var).setTipo(Tipo.INTEGER);
            }
            escreverCodigo("\tsub esp, " + tamanho + "\n");
            variaveis.clear();
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() +
                    "(:) esperado no programa na regra dvar ");
        }
    }

    // <tipo_var> ::= integer
    private void tipo_var() {
        if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("integer")) {
            token = lexico.nextToken();
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() +
                    "Integer esperado no programa depois de var na regra tipo_var");
        }
    }

    // <variaveis> ::= id {A03} <mais_var>
    private void variaveis() {
        if (token.getClasse() == Classe.identificador) {
            // {A03}
            String variavel = token.getValor().getValorTexto();
            if (tabela.isPresent(variavel)) {
                System.err.println("Variável" + variavel + "ja foi declarado anteriormente");
                System.exit(-1);
            } else {
                tabela.add(variavel);
                tabela.get(variavel).setCategoria(Categoria.VARIAVEL);
                tabela.get(variavel).setOffset(offsetVariavel);
                offsetVariavel += TAMANHO_INTEIRO;
                variaveis.add(variavel);
            }
            System.out.println(tabela);
            
            token = lexico.nextToken();
            
            mais_var();
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() +
                    "identificador esperado no programa depois de var na regra variaveis");
        }
    }

    // <mais_var> ::= , <variaveis> | ε
    private void mais_var() {
        if (token.getClasse() == Classe.virgula) {
            token = lexico.nextToken();
            variaveis();
        }
    }

    // <sentencas> ::= <comando> <mais_sentencas>
    private void sentencas() {
        comando();
        mais_sentencas();
    }

    // <mais_sentencas> ::= ; <cont_sentencas>
    private void mais_sentencas() {
        if (token.getClasse() == Classe.pontoEVirgula) {
            token = lexico.nextToken();
            cont_sentencas();
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() +
                    " (;) esperado na regra mais_sentencas ");
        }
    }

    // <cont_sentencas> ::= <sentencas> | ε
    private void cont_sentencas() {
        if ((token.getClasse() == Classe.palavraReservada &&
         (token.getValor().getValorTexto().equals("for") 
         || token.getValor().getValorTexto().equals("read") 
         || token.getValor().getValorTexto().equals("write") 
         || token.getValor().getValorTexto().equals("writeln")  
         || token.getValor().getValorTexto().equals("repeat") 
         || token.getValor().getValorTexto().equals("while")
         || token.getValor().getValorTexto().equals("if")
         ))|| token.getClasse()== Classe.identificador){
            sentencas();
        }
    }

    // <var_read> ::= id {A08} <mais_var_read>
    private void var_read() {
        // {A08}
        if (token.getClasse() == Classe.identificador) {
            String variavel = token.getValor().getValorTexto();
        if (!tabela.isPresent(variavel)) {
            System.err.println("Variável " + variavel + " não foi declarada");
            System.exit(-1);
        } else {
            registro = tabela.get(variavel);
            if (registro.getCategoria() != Categoria.VARIAVEL) {
                System.err.println("Identificador " + variavel + " não é uma variável");
                System.exit(-1);
            } else {
                escreverCodigo("\tmov edx, ebp");
                escreverCodigo("\tlea eax, [edx - " + registro.getOffset() + "]");
                escreverCodigo("\tpush eax");
                escreverCodigo("\tpush @Integer");
                escreverCodigo("\tcall scanf");
                escreverCodigo("\tadd esp, 8");
            }
        }

            token = lexico.nextToken();
            

            mais_var_read();
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() +
                    "identificador esperado na regra var_read");
        }
    }

    // <mais_var_read> ::= , <var_read> | ε
    private void mais_var_read() {
        if (token.getClasse() == Classe.virgula) {
            token = lexico.nextToken();
            var_read();
        }
    }

    /*
     * <exp_write> ::= id {A09} <mais_exp_write> |
     * string {A59} <mais_exp_write> |
     * <intnum> {A43} <mais_exp_write>
     */
    private void exp_write() {
        if (token.getClasse() == Classe.identificador) {
            // {A09}
            String variavel = token.getValor().getValorTexto();
            if (!tabela.isPresent(variavel)) {
                System.err.println("Variável " + variavel + " não foi declarada");
                System.exit(-1);
            } else {
                Registro registro = tabela.get(variavel);
                if (registro.getCategoria() != Categoria.VARIAVEL) {
                    System.err.println("Identificador " + variavel + " não é uma variável");
                    System.exit(-1);
                } else {
                    escreverCodigo("\tpush dword[ebp - " + registro.getOffset() + "]");
                    escreverCodigo("\tpush @Integer");
                    escreverCodigo("\tcall printf");
                    escreverCodigo("\tadd esp, 8");
                    if (!sectionData.contains("@Integer: db '%d',0")) {
                        sectionData.add("@Integer: db '%d',0");
                    }
                    if (!sectionData.contains("@IntegerLN: db '%d',10,0")) {
                        sectionData.add("@IntegerLN: db '%d',10,0");
                    }
                }
            }
            
            token = lexico.nextToken();
            mais_exp_write();
        } else if (token.getClasse() == Classe.string) {
            token = lexico.nextToken();
            // {A59}
            mais_exp_write();
        } else if (token.getClasse() == Classe.numeroInteiro) {
            token = lexico.nextToken();
            // {A43}
            mais_exp_write();
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() +
                    " Erro na regra exp_write");
        }
    }

    // <mais_exp_write> ::= , <exp_write> | ε
    private void mais_exp_write() {
        if (token.getClasse() == Classe.virgula) {
            token = lexico.nextToken();
            exp_write();
        }
    }

    /*
     * <comando> ::=
     * read ( <var_read> ) |
     * write ( <exp_write> ) |
     * writeln ( <exp_write> ) {A61} |
     * for <id> {A57} := <expressao> {A11} to <expressao> {A12} do begin <sentencas>
     * end {A13} |
     * repeat {A14} <sentencas> until ( <expressao_logica> ) {A15} |
     * while {A16} ( <expressao_logica> ) {A17} do begin <sentencas> end {A18} |
     * if ( <expressao_logica> ) {A19} then begin <sentencas> end {A20} <pfalsa>
     * {A21} |
     * <id> {A49} := <expressao> {A22} | <chamada_procedimento>
     */
    private void comando() {
        // read ( <var_read> )
        if (token.getClasse() == Classe.palavraReservada) {
            if (token.getValor().getValorTexto().equals("read")) {
                token = lexico.nextToken();
                if (token.getClasse() == Classe.parenteseEquerdo) {
                    token = lexico.nextToken();
                    var_read();
                    if (token.getClasse() == Classe.parenteseDireito) {
                        token = lexico.nextToken();
                    } else {
                        System.err.println(token.getLinha() + "," + token.getColuna() + "Erro na regra comando read");
                    }
                } else {
                    System.err.println(token.getLinha() + "," + token.getColuna() + "Erro na regra comando read");
                }
                // write ( <exp_write> ) |
            } else if (token.getValor().getValorTexto().equals("write")) {
                token = lexico.nextToken();
                if (token.getClasse() == Classe.parenteseEquerdo) {
                    token = lexico.nextToken();
                    exp_write();
                    if (token.getClasse() == Classe.parenteseDireito) {
                        token = lexico.nextToken();
                    } else {
                        System.err.println(token.getLinha() + "," + token.getColuna() + "Erro na regra comando write ')' ");
                    }
                } else {
                    System.err.println(token.getLinha() + "," + token.getColuna() + "Erro na regra comando write '(' ");
                }
            } else if (token.getValor().getValorTexto().equals("writeln")) {
                // writeln ( <exp_write> ) {A61} |
                token = lexico.nextToken();
                if (token.getClasse() == Classe.parenteseEquerdo) {
                    token = lexico.nextToken();
                    exp_write();
                    if (token.getClasse() == Classe.parenteseDireito) {
                        // {A61}
                        //Gerar um avanço de Linha
                        String novaLinha = "rotuloStringLN: db '' , 10,0";
                        if (!sectionData.contains(novaLinha)) {
                            sectionData.add(novaLinha);
                        }
                        escreverCodigo("\tpush"+ novaLinha);
                        escreverCodigo("\tcall printf");
                        escreverCodigo("\tadd esp, 4");
                        
                        token = lexico.nextToken();
                    } else {
                        System.err
                                .println(token.getLinha() + "," + token.getColuna() + "Erro na regra comando writeln");
                    }
                } else {
                    System.err.println(token.getLinha() + "," + token.getColuna() + "Erro na regra comando writeln");
                }
            } else if (token.getValor().getValorTexto().equals("for")) {
                // for <id> {A57} := <expressao> {A11} to <expressao> {A12} do begin <sentencas>
                // end {A13}
                token = lexico.nextToken();
                if (token.getClasse() == Classe.identificador) {
                    // {A57}
                    String variavel = token.getValor().getValorTexto();
                    if (!tabela.isPresent(variavel)) {
                        System.err.println("Variável " + variavel + " não foi declarada");
                        System.exit(-1);
                    } else {
                        Registro registro = tabela.get(variavel);
                        if (registro.getCategoria() != Categoria.VARIAVEL) {
                            System.err.println("Identificador " + variavel + " não é uma variável");
                            System.exit(-1);
                        }
                    }
                    token = lexico.nextToken();
                    if (token.getClasse() == Classe.atribuicao) {
                        token = lexico.nextToken();
                        expressao();
                        // {A11}
                        escreverCodigo("\tpop dword[ebp - "+ registro.getOffset()+"]");
                        String rotuloEntrada = criarRotulo("FOR");
                        String rotuloSaida = criarRotulo("FIMFOR");
                        rotulo = rotuloEntrada;
                        if ((token.getClasse() == Classe.palavraReservada &&
                                token.getValor().getValorTexto().equals("to"))) {
                            token = lexico.nextToken();
                            expressao();
                            // {A12}
                            escreverCodigo("\tpush ecx\n"
								  + "\tmov ecx, dword[ebp - " + registro.getOffset() + "]\n"
								  + "\tcmp ecx, dword[esp+4]\n"  //+4 por causa do ecx
								  + "\tjg " + rotuloSaida + "\n"
								  + "\tpop ecx");
                            
                            if ((token.getClasse() == Classe.palavraReservada &&
                                    token.getValor().getValorTexto().equals("do"))) {
                                token = lexico.nextToken();
                                if ((token.getClasse() == Classe.palavraReservada &&
                                        token.getValor().getValorTexto().equals("begin"))) {
                                    token = lexico.nextToken();
                                    sentencas();
                                    if ((token.getClasse() == Classe.palavraReservada &&
                                            token.getValor().getValorTexto().equals("end"))) {
                                        token = lexico.nextToken();
                                        // {A13}
                                        // Gerar as instruções para incrementar a variável id.
                                        escreverCodigo("\tadd dword[ebp - " + registro.getOffset() + "], 1");
                                        // Gerar um desvio para rotuloFor.
                                        escreverCodigo("\tjmp " + rotuloEntrada);
                                        // Gerar o rótulo rotuloFim.
                                        rotulo = rotuloSaida;
                                    } else {
                                        System.err.println(token.getLinha() + "," + token.getColuna() +
                                                "Erro na regra comando for na regra comando end esperado");
                                    }
                                } else {
                                    System.err.println(token.getLinha() + "," + token.getColuna() +
                                            "Erro na regra comando for na regra comando begin esperado");
                                }
                            } else {
                                System.err.println(token.getLinha() + "," + token.getColuna() +
                                        "Erro na regra comando for na regra comando");
                            }
                        } else {
                            System.err.println(token.getLinha() + "," + token.getColuna() +
                                    "Erro na regra comando for na regra comando");
                        }
                    } else {
                        System.err.println(token.getLinha() + "," + token.getColuna() +
                                "Erro na regra comando for na regra comando");
                    }
                } else {
                    System.err.println(token.getLinha() + "," + token.getColuna() +
                            "Erro na regra comando for na regra comando");
                }

        } else if (token.getValor().getValorTexto().equals("repeat")) {
            // repeat {A14} <sentencas> until ( <expressao_logica> ) {A15} |
            // {A14}
            String rotRepeat = criarRotulo("Repeat");
            rotulo = rotRepeat;

            token = lexico.nextToken();
            sentencas();
            if (token.getClasse() == Classe.palavraReservada &&
                    token.getValor().getValorTexto().equals("until")) {
                token = lexico.nextToken();
                if (token.getClasse() == Classe.parenteseEquerdo) {
                    token = lexico.nextToken();
                    expressao_logica();
                    if (token.getClasse() == Classe.parenteseDireito) {
                        token = lexico.nextToken();
                        // {A15}
                        escreverCodigo("\tcmp dword[esp], 0\n");
                        escreverCodigo("\tje"+ rotRepeat);
                    } else {
                        System.err.println(token.getLinha() + "," + token.getColuna() +
                                "Erro na regra comando repeat");
                    }
                } else {
                    System.err.println(token.getLinha() + "," + token.getColuna() +
                            "Erro na regra comando repeat");
                }
            } else {
                System.err.println(token.getLinha() + "," + token.getColuna() +
                        "Erro na regra comando repeat");
            }
        } else if (token.getValor().getValorTexto().equals("while")) {
            // while {A16} ( <expressao_logica> ) {A17} do begin <sentencas> end {A18} |
            token = lexico.nextToken();
            // {A16}
            if (token.getClasse() == Classe.parenteseEquerdo) {
                token = lexico.nextToken();
                expressao_logica();
                if (token.getClasse() == Classe.parenteseDireito) {
                    token = lexico.nextToken();
                    // {A17}
                    if (token.getClasse() == Classe.palavraReservada &&
                            token.getValor().getValorTexto().equals("do")) {
                        token = lexico.nextToken();
                        if (token.getClasse() == Classe.palavraReservada &&
                                token.getValor().getValorTexto().equals("begin")) {
                            token = lexico.nextToken();
                            sentencas();
                            if (token.getClasse() == Classe.palavraReservada &&
                                    token.getValor().getValorTexto().equals("end")) {
                                token = lexico.nextToken();
                                // {A18}
                            } else {
                                System.err.println(token.getLinha() + "," + token.getColuna() +
                                        "Erro na regra comando while");
                            }

                        } else {
                            System.err.println(token.getLinha() + "," + token.getColuna() +
                                    "Erro na regra comando while");
                        }

                    } else {
                        System.err.println(token.getLinha() + "," + token.getColuna() +
                                "Erro na regra comando while");
                    }
                } else {
                    System.err.println(token.getLinha() + "," + token.getColuna() +
                            "Erro na regra comando while");
                }
            } else {
                System.err.println(token.getLinha() + "," + token.getColuna() +
                        "Erro na regra comando while");
            }

        } else if (token.getValor().getValorTexto().equals("if")) {
            // if ( <expressao_logica> ) {A19} then begin <sentencas> end {A20} <pfalsa>
            // {A21}
            token = lexico.nextToken();
            // {A16}
            if (token.getClasse() == Classe.parenteseEquerdo) {
                token = lexico.nextToken();
                expressao_logica();
                if (token.getClasse() == Classe.parenteseDireito) {
                    token = lexico.nextToken();
                    // {A19}
                    if (token.getClasse() == Classe.palavraReservada &&
                            token.getValor().getValorTexto().equals("then")) {
                        token = lexico.nextToken();
                        if (token.getClasse() == Classe.palavraReservada &&
                                token.getValor().getValorTexto().equals("begin")) {
                            token = lexico.nextToken();
                            sentencas();
                            if (token.getClasse() == Classe.palavraReservada &&
                                    token.getValor().getValorTexto().equals("end")) {
                                token = lexico.nextToken();
                                // {A20}
                                pfalsa();
                                // {A21}
                            }else {
                                System.err.println(token.getLinha() + "," + token.getColuna() +
                                        "Erro na regra comando if");
                            }

                        }else {
                            System.err.println(token.getLinha() + "," + token.getColuna() +
                                    "Erro na regra comando if");
                        }

                    }else {
                        System.err.println(token.getLinha() + "," + token.getColuna() +
                                "Erro na regra comando if");
                    }
                }else {
                    System.err.println(token.getLinha() + "," + token.getColuna() +
                            "Erro na regra comando if");
                }
            }else {
                System.err.println(token.getLinha() + "," + token.getColuna() +
                        "Erro na regra comando if");
            }
        } 
    }else if (token.getClasse() == Classe.identificador) {
            // <id> {A49} := <expressao> {A22} | <chamada_procedimento>
            token = lexico.nextToken();
            // {A49}
            if (token.getClasse() == Classe.atribuicao) {
                token = lexico.nextToken();
                expressao();
                // {A22}
            } else {
                System.err.println(token.getLinha() + "," + token.getColuna() +
                        "Erro na regra comando id");
            }

        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() +
                    "Erro na regra comando");
        }
    }

    // <pfalsa> ::= else {A25} begin <sentencas> end | ε
    private void pfalsa() {
        if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("else")) {
            token = lexico.nextToken();
            // {A25}
            if (token.getClasse() == Classe.palavraReservada &&
                    token.getValor().getValorTexto().equals("begin")) {
                token = lexico.nextToken();
                sentencas();
                if (token.getClasse() == Classe.palavraReservada &&
                        token.getValor().getValorTexto().equals("end")) {
                    token = lexico.nextToken();
                } else {
                    System.err.println(token.getLinha() + "," + token.getColuna() +
                            "Erro na regra comando pfalsa falta end");
                }
            } else {
                System.err.println(token.getLinha() + "," + token.getColuna() +
                        "Erro na regra comando pfalsa falta begin");
            }
        }
    }

    // <expressao_logica> ::= <termo_logico> <mais_expr_logica>
    private void expressao_logica() {
        termo_logico();
        mais_expr_logica();
    }

    // <mais_expr_logica> ::= or <termo_logico> <mais_expr_logica> {A26} | ε
    private void mais_expr_logica() {
        if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("or")) {
            token = lexico.nextToken();
            termo_logico();
            mais_expr_logica();
            // {A26}
            // Empilhar 1, caso o valor de expressao_logica ou termo_logico seja 1, e 0
            // (falso), caso seja diferente. Isto pode ser feito da seguinte forma:
            // Crie um novo rótulo, digamos rotSaida
            String rotSaida = criarRotulo("SaidaMEL");
            // Crie um novo rótulo, digamos rotVerdade
            String rotVerdade = criarRotulo("VerdadeMEL");
            // Gere a instrução: cmp dword [ESP + 4], 1
            escreverCodigo("\tcmp dword [ESP + 4], 1");
            // Gere a instrução je para rotVerdade
            escreverCodigo("\tje " + rotVerdade);
            // Gere a instrução: cmp dword [ESP], 1
            escreverCodigo("\tcmp dword [ESP], 1");
            // Gere a instrução je para rotVerdade
            escreverCodigo("\tje " + rotVerdade);
            // Gere a instrução: mov dword [ESP + 4], 0
            escreverCodigo("\tmov dword [ESP + 4], 0");
            // Gere a instrução jmp para rotSaida
            escreverCodigo("\tjmp " + rotSaida);
            // Gere o rótulo rotVerdade
            rotulo = rotVerdade;
            // Gere a instrução: mov dword [ESP + 4], 1
            escreverCodigo("\tmov dword [ESP + 4], 1");
            // Gere o rótulo rotSaida
            rotulo = rotSaida;
            // Gere a instrução: add esp, 4
            escreverCodigo("\tadd esp, 4");

        }

    }

    // <termo_logico> ::= <fator_logico> <mais_termo_logico>
    private void termo_logico() {
        fator_logico();
        mais_termo_logico();
    }

    // <mais_termo_logico> ::= and <fator_logico> <mais_termo_logico> {A27} | ε
    private void mais_termo_logico() {
        if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("and")) {
            token = lexico.nextToken();
            fator_logico();
            mais_termo_logico();
            // {A27}
        }
    }

    /*
     * <fator_logico> ::= <relacional> |
     * ( <expressao_logica> ) |
     * not <fator_logico> {A28} |
     * true {A29} |
     * false {A30}
     */
    private void fator_logico() {
        if (token.getClasse() == Classe.parenteseEquerdo) {
            token = lexico.nextToken();
            expressao_logica();
            if (token.getClasse() == Classe.parenteseDireito) {
                token = lexico.nextToken();
            }else {
                System.err.println(token.getLinha() + "," + token.getColuna() +
                        "Erro na regra comando fator logico");
            }
        } else if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("not")) {
            token = lexico.nextToken();
            fator_logico();
            // {A28}
        } else if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("true")) {
            token = lexico.nextToken();
            // {A29}
        } else if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("false")) {
            token = lexico.nextToken();
            // {A30}
        } else {
            relacional();
        }

    }

    /*
     * <relacional> ::= <expressao> = <expressao> {A31} |
     * <expressao> > <expressao> {A32} |
     * <expressao> >= <expressao> {A33} |
     * <expressao> < <expressao> {A34} |
     * <expressao> <= <expressao> {A35} |
     * <expressao> <> <expressao> {A36}
     */
    private void relacional() {
        expressao();
        if (token.getClasse() == Classe.operadorIgual) {
            token = lexico.nextToken();
            expressao();
            // {A31}
        } else if (token.getClasse() == Classe.operadorMaior) {
            token = lexico.nextToken();
            expressao();
            // {A32}
        } else if (token.getClasse() == Classe.operadorMaiorIgual) {
            token = lexico.nextToken();
            expressao();
            // {A33}
        } else if (token.getClasse() == Classe.operadorMenor) {
            token = lexico.nextToken();
            expressao();
            // {A34}
        } else if (token.getClasse() == Classe.operadorMenorIgual) {
            token = lexico.nextToken();
            expressao();
            // {A35}
        } else if (token.getClasse() == Classe.operadorDiferente) {
            token = lexico.nextToken();
            expressao();
            // {A36}
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() +
                    "Erro na regra relacional");
        }

    }

    // <expressao> ::= <termo> <mais_expressao>
    private void expressao() {
        termo();
        mais_expressao();
    }

    /*
     * <mais_expressao> ::= + <termo> <mais_expressao> {A37} |
     * - <termo> <mais_expressao> {A38} | ε
     */
    private void mais_expressao() {
        if (token.getClasse() == Classe.operadorSoma) {
            token = lexico.nextToken();
            termo();
            mais_expressao();
            // {A37}
        } else if (token.getClasse() == Classe.operadorSubtracao) {
            token = lexico.nextToken();
            termo();
            mais_expressao();
            // {A38}
        }
    }

    // <termo> ::= <fator> <mais_termo>
    private void termo() {
        fator();
        mais_termo();
    }

    /*
     * <mais_termo> ::= * <fator> <mais_termo> {A39} |
     * / <fator> <mais_termo> {A40} | ε
     */
    private void mais_termo() {
        if (token.getClasse() == Classe.operadorMultiplicacao) {
            token = lexico.nextToken();
            fator();
            mais_termo();
            // {A39}
        } else if (token.getClasse() == Classe.operadorDivicao) {
            token = lexico.nextToken();
            fator();
            mais_termo();
            // {A40}
        }
    }

    // <fator> ::= <id> {A55} | <intnum> {A41} | ( <expressao> ) |
    private void fator() {
        if (token.getClasse() == Classe.identificador) {
            token = lexico.nextToken();
            // {A55}
        } else if (token.getClasse() == Classe.numeroInteiro) {
            // {A41}
            escreverCodigo("\tpush "+token.getValor().getValorInteiro());
            token = lexico.nextToken();
        } else if (token.getClasse() == Classe.parenteseEquerdo) {
            token = lexico.nextToken();
            expressao();
            if (token.getClasse() == Classe.parenteseDireito) {
                token = lexico.nextToken();
            }else {
                System.err.println(token.getLinha() + "," + token.getColuna() +
                        "Erro na regra fator");
            }
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() +
                    "Erro na regra fator");
        }
    }

}

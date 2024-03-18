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
            "(;) esperado no programa depois de var na regra mais_dc");
        }
        
    }
    //<cont_dc> ::= <dvar> <mais_dc> | ε
    private void cont_dc() {
        if(token.getClasse() == Classe.identificador){
            //dvar();
            //mais_dc(); 
        }
        
    }
    //<dvar> ::= <variaveis> : <tipo_var> {A02}
    private void dvar() {
        //variaveis();
        if (token.getClasse() == Classe.doisPontos) {
            token = lexico.nextToken();
            //tipo_var(); 
            //{A02}
        }else {
            System.err.println(token.getLinha()+","+ token.getColuna()+
            "(:) esperado no programa na regra dvar ");
        }
    }
    //<tipo_var> ::= integer
    private void tipo_var(){
        if (token.getClasse() == Classe.numeroInteiro) {
            token = lexico.nextToken();
        }else{
            System.err.println(token.getLinha()+","+ token.getColuna()+
            "NumeroInteiro esperado no programa depois de var na regra tipo_var");
        }
    }
    //<variaveis> ::= id {A03} <mais_var>
    private void variaveis(){
        if(token.getClasse() == Classe.identificador){
            token = lexico.nextToken();
            //{A03} 
            mais_var();
        }else{
            System.err.println(token.getLinha()+","+ token.getColuna()+
            "identificador esperado no programa depois de var na regra variaveis");
        }
    }

    //<mais_var> ::=  ,  <variaveis> | ε
    private void mais_var() {
        if (token.getClasse() == Classe.virgula) {
            token = lexico.nextToken();
            variaveis();
        }
    }
    //<sentencas> ::= <comando> <mais_sentencas>
    private void sentencas() {
        comando();
        mais_sentencas();
    }
    //<mais_sentencas> ::= ; <cont_sentencas>
    private void mais_sentencas() {
        if (token.getClasse() == Classe.pontoEVirgula) {
            token = lexico.nextToken();
            cont_sentencas();
        }else{
            System.err.println(token.getLinha()+","+ token.getColuna()+
            "(;) esperado na regra mais_sentencas ");
        }
    }
    //<cont_sentencas> ::= <sentencas> | ε
    private void cont_sentencas(){
        if(token.getClasse() == Classe.identificador){
            sentencas();
        }
    }
    //<var_read> ::= id {A08} <mais_var_read>
    private void var_read(){
        if(token.getClasse() == Classe.identificador){
            token = lexico.nextToken();
            //{A08} 
            mais_var_read();
        }else{
            System.err.println(token.getLinha()+","+ token.getColuna()+
            "identificador esperado na regra var_read");
        }
    }
    //<mais_var_read> ::= , <var_read> | ε
    private void  mais_var_read(){
        if(token.getClasse() == Classe.virgula){
            token = lexico.nextToken();
            var_read();
        }
    }

    /* <exp_write> ::= id {A09} <mais_exp_write> |
                string {A59} <mais_exp_write> |
                <intnum> {A43} <mais_exp_write>*/
    private void  exp_write(){
        if(token.getClasse() == Classe.identificador){
            token = lexico.nextToken();
            //{A09}
            //mais_exp_write();
        }else{
            if (token.getClasse() == Classe.palavraReservada &&
            token.getValor().getValorTexto().equals("string")) {
                token = lexico.nextToken();
                //{A59}
                //mais_exp_write();
            }else{
                if (token.getClasse() == Classe.numeroInteiro) {
                    token = lexico.nextToken();
                    //{A43}
                    //mais_exp_write();
                }else{
                    System.err.println(token.getLinha()+","+ token.getColuna()+
                    "Erro na regra exp_write");
                }
                
            }
        }
    }
    //<mais_exp_write> ::=  ,  <exp_write> | ε
    private void mais_exp_write(){
        if (token.getClasse() == Classe.virgula) {
            token = lexico.nextToken();
            //exp_write();
        }
    }

    /* 
    <comando> ::=
    read ( <var_read> ) |
    write ( <exp_write> ) |
    writeln ( <exp_write> ) {A61} |
    for <id> {A57} := <expressao> {A11} to <expressao> {A12} do begin <sentencas> end {A13} |
    repeat {A14} <sentencas> until ( <expressao_logica> ) {A15} |
    while {A16} ( <expressao_logica> ) {A17} do begin <sentencas> end {A18} |
    if ( <expressao_logica> ) {A19} then begin <sentencas> end {A20} <pfalsa> {A21} |
    <id> {A49} := <expressao> {A22} | <chamada_procedimento>*/
    private void comando() {
        if (token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("read")) {
            token = lexico.nextToken();
            if(token.getClasse() == Classe.parenteseEquerdo){
                token = lexico.nextToken();
                var_read();
                if(token.getClasse() == Classe.parenteseDireito){
                    token = lexico.nextToken();
                }else{
                    System.err.println(token.getLinha()+","+ token.getColuna()+
                    "Erro na regra comando read");
                }
            }

        }else if (token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("write")) {
            token = lexico.nextToken();
            if(token.getClasse() == Classe.parenteseEquerdo){
                token = lexico.nextToken();
                exp_write();
                if(token.getClasse() == Classe.parenteseDireito){
                    token = lexico.nextToken();
                    //{A61}
                }else{
                    System.err.println(token.getLinha()+","+ token.getColuna()+
                    "Erro na regra comando write");
                }
            }
        }else if (token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("writeln")) {
            token = lexico.nextToken();
            if(token.getClasse() == Classe.parenteseEquerdo){
                token = lexico.nextToken();
                exp_write();
                if(token.getClasse() == Classe.parenteseDireito){
                    token = lexico.nextToken();
                }else{
                    System.err.println(token.getLinha()+","+ token.getColuna()+
                    "Erro na regra comando writeln");
                }
            }
        }else if (token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("for")) {
            //for <id> {A57} := <expressao> {A11} to <expressao> {A12} do begin <sentencas> end {A13} |
            token = lexico.nextToken();
            if (token.getClasse() == Classe.identificador){
                token = lexico.nextToken();
                //{A57}
                if (token.getClasse() == Classe.atribuicao) {
                    token = lexico.nextToken();
                    //{A11}
                    if ((token.getClasse() == Classe.palavraReservada &&
                    token.getValor().getValorTexto().equals("to"))) {
                        token = lexico.nextToken();
                        //expressao();
                        //{A12}
                        if ((token.getClasse() == Classe.palavraReservada &&
                        token.getValor().getValorTexto().equals("do"))) {
                            token = lexico.nextToken();
                            if ((token.getClasse() == Classe.palavraReservada &&
                            token.getValor().getValorTexto().equals("begin"))) {
                                token = lexico.nextToken();
                                //sentencas();
                                if ((token.getClasse() == Classe.palavraReservada &&
                                token.getValor().getValorTexto().equals("end"))) {
                                    token = lexico.nextToken();
                                    //{A13}
                                    
                                }
                            }
                        }
                    }
                }
            }
        }else if (token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("repeat")) {
            token = lexico.nextToken();
            //repeat {A14} <sentencas> until ( <expressao_logica> ) {A15} |
            //{A14}
            sentencas();
            if (token.getClasse() == Classe.palavraReservada &&
            token.getValor().getValorTexto().equals("until")) {
                token = lexico.nextToken();
                if(token.getClasse() == Classe.parenteseEquerdo){
                    token = lexico.nextToken();
                    //expressao_logica();
                    if(token.getClasse() == Classe.parenteseDireito){
                        token = lexico.nextToken();
                        //{A15}
                    }else{
                        System.err.println(token.getLinha()+","+ token.getColuna()+
                        "Erro na regra comando repeat");
                    }
                }
            }

        }else if (token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("while")) {
            token = lexico.nextToken();
            //{A16}
            //while {A16} ( <expressao_logica> ) {A17} do begin <sentencas> end {A18} |
            if(token.getClasse() == Classe.parenteseEquerdo){
                token = lexico.nextToken();
                //expressao_logica();
                if(token.getClasse() == Classe.parenteseDireito){
                    token = lexico.nextToken();
                    //{A17}
                    if (token.getClasse() == Classe.palavraReservada &&
                    token.getValor().getValorTexto().equals("do")){
                        token = lexico.nextToken();
                        if (token.getClasse() == Classe.palavraReservada &&
                        token.getValor().getValorTexto().equals("begin")){
                            token = lexico.nextToken();
                            sentencas();
                            if (token.getClasse() == Classe.palavraReservada &&
                            token.getValor().getValorTexto().equals("end")){
                                token = lexico.nextToken();
                                //{A18} 
                                
                            }
                        }
                        
                    }
                }else{
                    System.err.println(token.getLinha()+","+ token.getColuna()+
                    "Erro na regra comando while");
                }
            }
        }else if (token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("if")) {
            token = lexico.nextToken();
            //if ( <expressao_logica> ) {A19} then begin <sentencas> end {A20} <pfalsa> {A21} |
            if(token.getClasse() == Classe.parenteseEquerdo){
                token = lexico.nextToken();
                //expressao_logica();
                if(token.getClasse() == Classe.parenteseDireito){
                    token = lexico.nextToken();
                    //{A19}
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
                            //{A20}
                            pfalsa();
                            //{A21}
                        }
                        }
                }else{
                    System.err.println(token.getLinha()+","+ token.getColuna()+
                    "Erro na regra comando repeat");
                }
            }
        }else if (token.getClasse() == Classe.identificador) {
            //<id> {A49} := <expressao> {A22} | <chamada_procedimento>
            token = lexico.nextToken();
            //{A49}
            if (token.getClasse() == Classe.atribuicao) {
                token = lexico.nextToken();
                //expressao();
                 //{A22}
            }else {
                //chamada_procedimento();
            }
        }
    }

    
    //<pfalsa> ::= else {A25} begin <sentencas> end | ε 
    private void pfalsa(){
        if (token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("else")) {
            token = lexico.nextToken();
            //{A25}
            if (token.getClasse() == Classe.palavraReservada &&
            token.getValor().getValorTexto().equals("begin")) {
                token = lexico.nextToken();
                //sentencas();
                if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("end")) {
                    token = lexico.nextToken();
                }else{
                    System.err.println(token.getLinha()+","+ token.getColuna()+
                    "Erro na regra comando pfalsa");
                }
            }else{
                System.err.println(token.getLinha()+","+ token.getColuna()+
                "Erro na regra comando pfalsa");
            }
        }
        
    }


//<expressao_logica> ::= <termo_logico> <mais_expr_logica>
    private void expressao_logica() {
        token = lexico.nextToken();
        //termo_logico();
        //mais_expr_logica();
    }

    //<mais_expr_logica> ::= or <termo_logico> <mais_expr_logica> {A26} | ε
    private void mais_expr_logica(){
        if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("or")) {
                    token = lexico.nextToken();
                    //termo_logico();
                    //mais_expr_logica();
                    //{A26} 
                }

    }
    //<termo_logico> ::= <fator_logico> <mais_termo_logico>
    private void termo_logico(){
        token = lexico.nextToken();
        //fator_logico();
        //mais_termo_logico();
    }
    //<mais_termo_logico> ::= and <fator_logico> <mais_termo_logico> {A27} | ε
    private void mais_termo_logico(){
        if (token.getClasse() == Classe.palavraReservada &&
                token.getValor().getValorTexto().equals("and")) {
                    token = lexico.nextToken();
                    //fator_logico();
                    //mais_termo_logico();
                    //{A27} 
                }
    }
    /*<fator_logico> ::= <relacional> |
                   ( <expressao_logica> ) |
                   not <fator_logico> {A28} |
                   true {A29} |
                   false {A30}*/
    private void fator_logico(){
        if (token.getClasse() == Classe.parenteseEquerdo){
            token = lexico.nextToken();
            expressao_logica();
            if(token.getClasse() == Classe.parenteseDireito){
                token = lexico.nextToken();
            }
        }else if(token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("not")){
            token = lexico.nextToken();
            fator_logico();
            //{A28}
        }else if(token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("true")){
            token = lexico.nextToken();
            //{A29}
        }else if(token.getClasse() == Classe.palavraReservada &&
        token.getValor().getValorTexto().equals("false")){
            token = lexico.nextToken();
            //{A30}
        }else{
            token = lexico.nextToken();
            //relacional();
        }

    }
    /*<relacional> ::= <expressao> =  <expressao> {A31} |
                 <expressao> >  <expressao> {A32} |
                 <expressao> >= <expressao> {A33} |
                 <expressao> <  <expressao> {A34} |
                 <expressao> <= <expressao> {A35} |
                 <expressao> <> <expressao> {A36}*/
    private void relacional(){

    }
    //<expressao> ::= <termo> <mais_expressao>
    private void expressao(){
        token = lexico.nextToken();
        //termo();
        //mais_expressao();
    }
    /*<mais_expressao> ::= + <termo> <mais_expressao> {A37} |
                     - <termo> <mais_expressao> {A38} | ε */
    private void mais_expressao(){
        if (token.getClasse() == Classe.operadorSoma){
            token = lexico.nextToken();
            //termo();
            mais_expressao();
            //{A37} 
        }else if (token.getClasse() == Classe.operadorSubtracao) {
            token = lexico.nextToken();
            //termo();
            mais_expressao();
            //{A38}
        }
    }
    //<termo> ::= <fator> <mais_termo>
    private void termo(){
        token = lexico.nextToken();
        //fator();
        //mais_termo();
    }

/* <mais_termo> ::= * <fator> <mais_termo> {A39} |
                 / <fator> <mais_termo> {A40} | ε */
    private void mais_termo(){
        if (token.getClasse() == Classe.operadorMultiplicacao) {
            token = lexico.nextToken();
            //fator();
            //mais_termo();
            //{A39}
        }else if(token.getClasse() == Classe.operadorDivicao){
            token = lexico.nextToken();
            //fator();
            //mais_termo();
            //{A40}
        }
    }
    //<fator> ::= <id> {A55} | <intnum> {A41} | ( <expressao> ) | 
    //<id> {A60} <argumentos> {A42}
    private void fator(){
        if (token.getClasse() == Classe.identificador){
            token = lexico.nextToken();
            //{A55} 
        }else if(token.getClasse() == Classe.numeroInteiro){
            token = lexico.nextToken();
            //{A41} 
        }else if(token.getClasse() == Classe.parenteseEquerdo){
            token = lexico.nextToken();
            //expressao();
            if(token.getClasse() == Classe.parenteseDireito){
                token = lexico.nextToken();
            }
        }else{
            System.err.println(token.getLinha()+","+ token.getColuna()+
                    "Erro na regra fator");
        }
    }

    }

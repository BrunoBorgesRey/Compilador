package Lexico;

public enum Classe{
    identificador,
    palavraReservada,
    operadorSoma,
    operadorSubtracao,
    operadorMultiplicacao,
    operadorDivicao, // /
    operadorMaior,
    operadorMenor,
    operadorMaiorIgual, // >=
    operadorMenorIgual, // <=
    operadorIgual, //=
    operadorDiferente, //<>
    //operadorE,// and
    //operadorOu, // or
    //operadorNegacao, // not
    numeroInteiro,
    atribuicao, // :=
    pontoEVirgula, //;
    virgula, //,
    EOF, //final do aruivo
    ponto,
    doisPontos, //:
    parenteseEquerdo, // (
    parenteseDireito, // )
    string
}
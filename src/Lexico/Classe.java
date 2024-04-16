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
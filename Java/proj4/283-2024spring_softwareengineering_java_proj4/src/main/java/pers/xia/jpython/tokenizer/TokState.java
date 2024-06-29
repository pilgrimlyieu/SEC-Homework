package pers.xia.jpython.tokenizer;

public enum TokState
{
    ENDMARKER,
    NAME,
    NUMBER,
    STRING,
    NEWLINE,
    INDENT,
    DEDENT,
    LPAR,   // (
    RPAR,   // )
    LSQB,   // [
    RSQB,   // ]
    COLON,  // :
    COMMA,  // ,
    SEMI,   // ;
    PLUS,   // +
    MINUS,  // -
    STAR,   // *
    SLASH,  // /
    VBAR,   // |
    AMPER,  // &
    LESS,   // <
    GREATER,    // >
    EQUAL,  // =
    DOT,    // .
    PERCENT,    // %
    BACKQUOTE,    
    LBRACE, // {
    RBRACE, // }
    EQEQUAL,    // ==
    NOTEQUAL,   // !=
    LESSEQUAL,  // <=
    GREATEREQUAL,   // >=
    TILDE,  // ~
    CIRCUMFLEX, // ^
    LEFTSHIFT,  // <<
    RIGHTSHIFT, // >>
    DOUBLESTAR, // **
    PLUSEQUAL,  // +=
    MINEQUAL,   // -=
    STAREQUAL,  // *=
    SLASHEQUAL, // /=
    PERCENTEQUAL,   // %=
    AMPEREQUAL, // &=
    VBAREQUAL,  // |=
    CIRCUMFLEXEQUAL,    // ^=
    LEFTSHIFTEQUAL, // <<=
    RIGHTSHIFTEQUAL,    // >>=
    DOUBLESTAREQUAL,    // **=
    DOUBLESLASH,    // \\
    DOUBLESLASHEQUAL,   // \\=
    AT, // @
    ATEQUAL,    // @=
    RARROW, // ->
    ELLIPSIS,   // ...
    OP,    
    AWAIT,  // await
    ASYNC,  // async
    ERRORTOKEN,
    N_TOKENS,
}

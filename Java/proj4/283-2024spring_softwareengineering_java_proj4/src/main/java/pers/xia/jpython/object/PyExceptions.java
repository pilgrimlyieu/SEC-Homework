package pers.xia.jpython.object;

import java.util.Stack;

import pers.xia.jpython.parser.Node;
import pers.xia.jpython.tokenizer.Token;

public class PyExceptions extends RuntimeException
{
    private static final long serialVersionUID = -5566345378602605958L;

    public static enum ErrorType
    {
        AST_ERROR, 
        PARSER_ERROR, 
        SYSTEM_ERROR, 
        SYMTABLE_ERROR,
        SYNTAX_ERROR,
        TYPE_ERROR,
    }

    public Stack<String> map = new Stack<String>();
    public Token tok;

    public PyExceptions()
    {
        super();
    }

    public PyExceptions(String msg)
    {
        super(msg);
        map.push(msg);
    }

    public PyExceptions(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    public PyExceptions(ErrorType type, String msg)
    {
        this(type, msg, 0, 0);
    }
    
    public PyExceptions(ErrorType type, String msg, int lineno, int colOffset)
    {
        super(msg);
        msg += "\nlineno: " + lineno + ", colOffset: " + colOffset; 
        map.push(msg);
    }

    public PyExceptions(String msg, Node n)
    {
        this(null, msg, n);
    }

    public PyExceptions(ErrorType type, String msg, Node n)
    {
        super(msg);
        msg = msg + "\n" + "line: " + n.lineNo + " colOffset: " + n.colOffset
                + "\n" + n.dfaType + " " + n.str;
        map.push(msg);
    }

    public PyExceptions(String msg, Token tok)
    {
        super(msg);
        msg = "line: " + tok.lineNo + "\n"
                + tok.buf.substring(tok.lineStart, tok.lineEnd) + "\n" + msg;
        map.push(msg);
        this.tok = tok;
    }

    public PyExceptions(ErrorType type, String msg, PyObject obj)
    {
        super(msg);
        map.push(msg);
    }
    public void addException(String msg){
        map.push(msg);
    }

    public String toString()
    {
        String msg = "";
        while (!map.empty())
        {
            msg += map.pop() + "\n";
        }
        return msg;
    }

    public void printStackTrace()
    {
        String msg = "";
        while (!map.empty())
        {
            msg += map.pop() + "\n";
        }
        if(msg.length() > 0)
        {
            System.err.println(msg);
        }
        else
        {
            super.printStackTrace();
        }
    }
}

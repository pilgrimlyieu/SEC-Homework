// Autogenerated AST node
package pers.xia.jpython.ast;
import pers.xia.jpython.object.PyObject;
import java.io.DataOutputStream;
import java.io.IOException;

public class Str extends exprType {
    public PyObject s;

    public Str(PyObject s,int lineno, int col_offset) {
        this.s = s;
        this.lineno = lineno;
        this.col_offset = col_offset;
    }

    public Str(String xIsTrue) {

    }

    public String toString() {
        return "Str";
    }

    public void pickle(DataOutputStream ostream) throws IOException {
    }

    public Object accept(VisitorIF visitor) throws Exception {
        return visitor.visitStr(this);
    }

    public void traverse(VisitorIF visitor) throws Exception {
    }

}

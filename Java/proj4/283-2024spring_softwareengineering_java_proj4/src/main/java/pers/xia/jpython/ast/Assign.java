// Autogenerated AST node
package pers.xia.jpython.ast;
import pers.xia.jpython.object.PyObject;
import java.io.DataOutputStream;
import java.io.IOException;

public class Assign extends stmtType {
    public java.util.List<exprType> targets;
    public exprType value;

    public Assign(java.util.List<exprType> targets, exprType value,int
    lineno, int col_offset) {
        this.targets = targets;
        this.value = value;
        this.lineno = lineno;
        this.col_offset = col_offset;
    }

    public String toString() {
        return "Assign";
    }

    public void pickle(DataOutputStream ostream) throws IOException {
    }

    public Object accept(VisitorIF visitor) throws Exception {
        return visitor.visitAssign(this);
    }

    public void traverse(VisitorIF visitor) throws Exception {
    }

}

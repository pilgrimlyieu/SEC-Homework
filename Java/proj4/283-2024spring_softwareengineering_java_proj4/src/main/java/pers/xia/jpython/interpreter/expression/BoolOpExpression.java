package pers.xia.jpython.interpreter.expression;

import pers.xia.jpython.ast.boolopType;
import pers.xia.jpython.interpreter.ProgramState;
import pers.xia.jpython.object.PyBoolean;
import pers.xia.jpython.object.PyObject;

import java.util.List;

public class BoolOpExpression extends Expression{
    List<Expression> values;
    boolopType type;
    public BoolOpExpression(List<Expression> values, boolopType type){
        this.values = values;
        this.type = type;
    }
    @Override
    public PyObject eval(ProgramState programState) {
        // TODO 补充代码返回逻辑运算结果
        boolean res = true;
        for (Expression value : values) {
            boolean valueRes = value.eval(programState).asBoolean();
            if (type == boolopType.And) {
                res = res && valueRes;
            } else if (type == boolopType.Or){
                res = res || valueRes;
            }
        }
        return new PyBoolean(res);
    }
}

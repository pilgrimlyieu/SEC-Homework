package pers.xia.jpython.interpreter.statement;

import pers.xia.jpython.interpreter.ProgramState;
import pers.xia.jpython.interpreter.expression.Expression;
import pers.xia.jpython.object.PyObject;
import java.util.ArrayList;

public class AssignStatement implements Statement {
    private ArrayList<String> variableNames;
    private Expression expression;

    public AssignStatement(String variableName, Expression expression) {
        this.variableNames = new ArrayList<>();
        this.variableNames.add(variableName);
        this.expression = expression;
    }

    public AssignStatement(ArrayList<String> variableNames, Expression expression) {
        this.variableNames = variableNames;
        this.expression = expression;
    }


    @Override
    public void run(ProgramState programState) {
        PyObject expressionValue = expression.eval(programState);
        for (String variableName : variableNames) {
            programState.setVariable(variableName, expressionValue);
        }
    }
}

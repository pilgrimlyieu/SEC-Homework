package test1;

import java.util.ArrayList;

public class Node {
    private ArrayList<Node> children;
    private String text;

    public Node(String text) {
        children = new ArrayList<>();
        this.text = text;
    }

    public void attachChild(Node n) {
        children.add(n);
    }

    public void detachChild(Node n) {
        children.remove(n);
    }

    public void show() {
        showCurrent();
        showChildren();
    }

    private void showCurrent() {
        if (text!=null)
            System.out.println(text);
        else
            System.out.println();
    }

    private void showChildren() {
        if(children.isEmpty())
            return;
        for(Node c: children)
            c.show();
    }
}

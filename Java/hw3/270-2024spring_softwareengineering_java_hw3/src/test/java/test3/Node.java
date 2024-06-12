package test3;

import java.util.ArrayList;
import java.util.Iterator;

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
        Iterator<Node> iter = children.iterator();
        while(iter.next() != null) {
            iter.next().show();
        }
    }
}
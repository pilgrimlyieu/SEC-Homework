package test2;

public class Node {
    private Node children;
    private String text;

    public Node(String text) {
        children = null;
        this.text = text;
    }

    public void attachChild(Node n) {
        children = n;
    }

    public void detachChild(Node n) {
        children = null;
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
        if(children == null)
            return;
        children.show();
    }
}

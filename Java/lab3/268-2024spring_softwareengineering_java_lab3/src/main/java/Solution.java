import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Problem 1
 * */
interface GeometricObject {
    public double getPerimeter();
    public double getArea();
}

class Circle implements GeometricObject {
    // Protected variable
    double radius;
    double pi = 3.14;

    // Constructor
    /** WRITE YOUR CODE HERE **/
    Circle(double radius){
        this.radius = radius;
    }

    // Implement toString method
    public String toString() {
        /**（保留一位小数）
         * output: Circle[radius=2.5]
         * */
        return String.format("Circle[radius=%.1f]", radius);
    }

    // Implement methods defined in the interface GeometricObject
    @Override
    public double getPerimeter() {
        return 2 * pi * radius;
    }
    @Override
    public double getArea() {
        return pi * radius * radius;
    }

}

interface Resizable {
    public void resize(int percent);
}

class ResizableCircle extends Circle implements Resizable {
    // Constructor
    /** WRITE YOUR CODE HERE **/
    ResizableCircle(double radius){
        super(radius);
    }

    // Reimplement toString method
    public String toString() {
        /**（保留一位小数）
         * output: ResizableCircle[Circle[radius=2.5]]
         * */
        return String.format("ResizableCircle[%s]", super.toString());
    }

    // Implement methods defined in the interface Resizable
    @Override
    public void resize(int percent) {
        radius *= percent / 100.0;
    }
}

/**
 * Problem 2
 * */
class MyDate {
    int year;
    int month;
    int day;

    public MyDate(int day, int month, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public boolean isLeapYear(){
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        }
        return false;
    }
    public boolean isValidDate(){
        ArrayList month31 = new ArrayList<Integer>() {{
            add(1);
            add(3);
            add(5);
            add(7);
            add(8);
            add(10);
            add(12);
        }};
        if (month < 0 || month > 12 || day < 0 || day > 31) {
            return false;
        }
        else if (month31.contains(month) && day > 31) {
            return false;
        }
        else if (month == 2 && isLeapYear() && day > 29) {
            return false;
        }
        else if (month == 2 && !isLeapYear() && day > 28) {
            return false;
        }
        else if (!month31.contains(month) && day > 30) {
            return false;
        }
        return true;
    }
    public void setDate(MyDate date){
        year = date.year;
        month = date.month;
        day = date.day;
    }
    public String toStringDate(){
        return String.format("%d %d %d", day, month, year);
    }

}

/**
 * Problem 3
 * */
class Trie {
    // add members if you need! you can also define a inner class here

    class Node {
        char Char;
        Node Parent;
        HashMap<Character, Node> Children;

        public Node(char c, Node parent) {
            Char = c;
            Parent = parent;
            Children = new HashMap<>();
        }

        public Node getChild(char c) {
            return Children.get(c);
        }

        public void addChild(char c) {
            Children.put(c, new Node(c, this));
        }

        public List<String> toStringList() {
            List<String> result = new ArrayList<>();
            if (Children.isEmpty()) {
                // return a list of one char string
                result.add(String.valueOf(Char));
            } else {
                for (Node child : Children.values()) {
                    for (String s : child.toStringList()) {
                        result.add(Char + s);
                    }
                }
            }
            return result;
        }
    }

    Node Root = new Node('.', null);

    public boolean search(String s) {
        Node currentNode = Root;
        for (char c : s.toCharArray()) {
            if (currentNode.getChild(c) == null) {
                return false;
            }
            currentNode = currentNode.getChild(c);
        }
        return true;
    }

    public void add(String s) {
        Node startNode = Root;
        for (char c : s.toCharArray()) {
            if (startNode.getChild(c) == null) {
                startNode.addChild(c);
            }
            startNode = startNode.getChild(c);
        }
    }

    public List<String> serialize() {
        List<String> result = new ArrayList<>();
        for (Node child : Root.Children.values()) {
            for (String s : child.toStringList()) {
                result.add(s);
            }
        }
        return result;
    }
}

/**
 * Problem 4
 * */
public class Solution {
    public String executeMethod(Object victim, String methodName) throws Exception {
        Object result = null;
        Method method = victim.getClass().getMethod(methodName);
        result = method.invoke(victim);
        return result != null ? (String) result : "";
    }

    boolean isWolf(Object animal) throws Exception {
        if (executeMethod(animal, "getCategory").equals("Dog") && executeMethod(animal, "shout").endsWith("Howl!")) {
            return true;
        }
        return false;
    }

    public List<String> handleAnimals(Object[] animals, String dogActivity, String catActivity, String wolfRecipe) throws Exception {
        List<String> result = new ArrayList<>();
        for (Object animal : animals) {
            Field isDead = animal.getClass().getField("isDead");
            if (isDead.getBoolean(animal)) {
                continue;
            }
            Field name = animal.getClass().getField("name");
            String animalName = (String) name.get(animal);
            if (animalName.contains("666")) {
                name.set(animal, animalName.replace("666", "888"));
                executeMethod(animal, "kill");
                if (isWolf(animal)) {
                    executeMethod(animal, wolfRecipe);
                }
                continue;
            }
            if (isWolf(animal)) {
                executeMethod(animal, "kill");
                executeMethod(animal, wolfRecipe);
            } else if (executeMethod(animal, "getCategory").equals("Dog")) {
                result.add(executeMethod(animal, dogActivity));
            } else if (executeMethod(animal, "getCategory").equals("Cat")) {
                result.add(executeMethod(animal, catActivity));
            }
        }
        return result;
    }
}
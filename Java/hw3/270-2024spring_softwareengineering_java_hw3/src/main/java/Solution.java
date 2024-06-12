import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Problem 1
 * */
class Author {
    String name;
    String email;
    char gender;

    Author(String name, String email, char gender){
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public String toString() {
        return String.format("Author[name=%s,email=%s,gender=%c]", name, email, gender);
    }
}

class Book {
    String name;
    Author[] authors;
    double price;
    int qty = 0;

    Book (String name, Author[] authors, double price) {
        this.name = name;
        this.authors = authors;
        this.price = price;
    }
    Book (String name, Author[] authors, double price, int qty) {
        this.name = name;
        this.authors = authors;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getAuthorNames() {
        String[] authorNames = new String[authors.length];
        for (int i = 0; i < authors.length; i++) {
            authorNames[i] = authors[i].getName();
        }
        return String.join(",", authorNames);
    }

    public String toString() {
        String[] authorInfos = new String[authors.length];
        for (int i = 0; i < authors.length; i++) {
            authorInfos[i] = authors[i].toString();
        }
        return String.format("Book[name=%s,authors={%s},price=%.2f,qty=%d]", name, String.join(",", authorInfos), price, qty);
    }
}

/**
 * Problem 2
 * */
class DeathManager{
    public int year = 0;
    Map<String, Integer> deathRecord = new HashMap<>();
    public class Person {
        public String name;
        public int health;
        public int strength;
        public int dexterity;
        public int intelligence;
        public int faith;
        public int mana;
        boolean isDead = false;
        int bornYear;
        ArrayList<Item> items = new ArrayList<>();
        // if you need, you can add other members or methods
        public Person(String name, int health, int strength, int dexterity, int intelligence, int faith, int mana) {
            this.name = name;
            this.health = health;
            this.strength = strength;
            this.dexterity = dexterity;
            this.intelligence = intelligence;
            this.faith = faith;
            this.mana = mana;
            this.bornYear = year;
        }
        public void physicalAttack(Person other){
            if (isDead || other.isDead) return;
            Item item;
            if ((item = hasItem("Blade")) != null) {
                item.activeSkill(other);
            } else if (strength >= other.dexterity) {
                other.beAttacked(this, strength * dexterity);
            }
        }
        public void magicAttack(Person other){
            if (isDead || other.isDead) return;
            Item item;
            if ((item = hasItem("Wand")) != null) {
                item.activeSkill(other);
            } else if (mana >= 20) {
                other.beAttacked(this, intelligence * faith);
                mana -= 20;
            }
        }

        public Item hasItem(String itemName) {
            for (Item item : items) {
                if (item.name.equals(itemName)) {
                    return item;
                }
            }
            return null;
        }

        public void beAttacked(Person attacker, int damage) {
            Item item;
            if (hasItem("Shield") != null) {
                damage -= 20;
            }
            if (hasItem("CalamityRing") != null) {
                damage *= 2;
            }
            if ((item = hasItem("RingOfSacrifice")) != null && (health - damage <= 0)) {
                health = 1;
                removeItem(item);
            } else {
                health -= damage > 0 ? damage : 0;
            }
            boolean kill = detectDeath();
            if (kill && attacker.hasItem("RingOfTheEvilEye") != null) {
                attacker.health += 30;
            }
        }

        public boolean detectDeath() {
            if (health <= 0) {
                isDead = true;
                deathRecord.put(name, year - bornYear);
                return true;
            }
            return false;
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public void removeItem(Item item) {
            items.remove(item);
        }
    }
    public Person newborn(String name,int health, int strength, int dexterity, int intelligence, int faith, int mana){
        return new Person(name, health, strength, dexterity, intelligence, faith, mana);//Do not modify
    }
    public void progressYear() {
        year++;
    }
    public Map<String, Integer> deathRecord() {
        return deathRecord;
    }

    /**
     * Problem 3
     * */
    public void grantItem(Person person, Item item) {
        person.addItem(item);
        item.owner = person;
        if (item instanceof Blade) {
            person.dexterity += 5;
        }
    }

    public void removeItem(Person person, Item item) {
        person.removeItem(item);
        if (item instanceof Blade) {
            item.owner.dexterity -= 5;
        }
        item.owner = null;
    }

    private static class Item {
        public Person owner;
        public String name = "Item";

        public Item(String name) {
            this.name = name;
        }

        void activeSkill(Person other) {
        }
    }

    public static class Blade extends Item {
        public Blade() {
            super("Blade");
        }

        void activeSkill(Person other) {
            if (owner.isDead || other.isDead) return;
            if (owner.strength >= other.dexterity) {
                int damage = owner.strength + 10;
                for (int i = 0; i < 3; i++) {
                    other.beAttacked(owner, damage);
                }
            }
        }
    }

    public static class Shield extends Item {
        public Shield() {
            super("Shield");
        }
    }

    public static class Wand extends Item {
        int times;

        public Wand() {
            super("Wand");
            this.times = 5;
        }

        void activeSkill(Person other) {
            if (owner.isDead || other.isDead) return;
            if (owner.mana >= 100) {
                other.beAttacked(owner, 200);
                owner.mana -= 100;
                if (--times == 0) {
                    owner.removeItem(this);
                }
            }
        }
    }

    public static class RingOfSacrifice extends Item {
        public RingOfSacrifice() {
            super("RingOfSacrifice");
        }
    }

    public static class CalamityRing extends Item {
        public CalamityRing() {
            super("CalamityRing");
        }
    }

    public static class RingOfTheEvilEye extends Item {
        public RingOfTheEvilEye() {
            super("RingOfTheEvilEye");
        }
    }
}

/**
 * Problem 4
 * */
public class Solution {
    public boolean checkClassInfo(String path) {
        try {
            Class<?> c = Class.forName(path);
            Field textF = c.getDeclaredField("text");
            Field childrenF = c.getDeclaredField("children");
            Class<?> textT = textF.getType();
            Class<?> childrenT = childrenF.getType();
            if (textT != String.class || childrenT != ArrayList.class) {
                return false;
            } else if (!Modifier.isPrivate(textF.getModifiers()) || !Modifier.isPrivate(childrenF.getModifiers())) {
                return false;
            }
            Constructor<?> Node = c.getConstructor(String.class);
            Method attachChild = c.getDeclaredMethod("attachChild", c);
            Method detachChild = c.getDeclaredMethod("detachChild", c);
            Method show = c.getDeclaredMethod("show");
            Method showCurrent = c.getDeclaredMethod("showCurrent");
            Method showChildren = c.getDeclaredMethod("showChildren");
            Class<?>[] attachChildPTs = attachChild.getParameterTypes();
            Class<?>[] detachChildPTs = detachChild.getParameterTypes();
            if (attachChildPTs.length != 1 || detachChildPTs.length != 1 || attachChildPTs[0] != c || detachChildPTs[0] != c) {
                return false;
            } else if (!attachChildPTs[0].equals(c) || !detachChildPTs[0].equals(c)) {
                return false;
            } else if (!(Modifier.isPublic(Node.getModifiers()) ||
                       Modifier.isPublic(attachChild.getModifiers()) ||
                       Modifier.isPublic(detachChild.getModifiers()) ||
                       Modifier.isPublic(show.getModifiers()) ||
                       Modifier.isPrivate(showCurrent.getModifiers()) ||
                       Modifier.isPrivate(showChildren.getModifiers()))) {
                return false;
            }
            return true;
        }
        catch (Exception ignored) {
            return false; // error handling, simply returns false
        }
    }
}

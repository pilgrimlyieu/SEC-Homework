public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    public String getCategory() {
        return "Cat";
    }

    public String shout() {
        if (this.isDead) {
            return "Why??? Why you killed those innocent cuties?";
        }
        return this.name + " shouts: Meow!";
    }

    public String backflip() {
        if (this.isDead) {
            return "Why??? Why you killed those innocent cuties?";
        }
        return this.name + " does a backflip!";
    }

    public String sleep() {
        if (this.isDead) {
            return "Why??? Why you killed those innocent cuties?";
        }
        return this.name + " sleeps in a box!";
    }

    public String attack() {
        if (this.isDead) {
            return "Why??? Why you killed those innocent cuties?";
        }
        return this.name + " attacks a mouse!";
    }
}

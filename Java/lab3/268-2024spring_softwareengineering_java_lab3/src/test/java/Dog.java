public class Dog extends Animal{

    public Dog(String name) {
        super(name);
    }

    public String getCategory() {
        return "Dog";
    }

    public String shout() {
        if (this.isDead) {
            return "Why??? Why you killed those innocent cuties?";
        }
        return this.name + " shouts: Woof!";
    }

    public String run() {
        if (this.isDead) {
            return "Why??? Why you killed those innocent cuties?";
        }
        return this.name + " runs through your backyard!";
    }

    public String bite() {
        if (this.isDead) {
            return "Why??? Why you killed those innocent cuties?";
        }
        return this.name + " bites your keyboard!";
    }

    public String sniff() {
        if (this.isDead) {
            return "Why??? Why you killed those innocent cuties?";
        }
        return this.name + " sniffs your handbag!";
    }
}

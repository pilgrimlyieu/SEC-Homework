public class Animal {
    public String name;
    public boolean isDeadMultipleTimes;
    public boolean isDead;

    public Animal() {
        this.name = "";
        this.isDead = false;
        this.isDeadMultipleTimes = false;
    }

    public Animal(String name) {
        this.name = name;
        this.isDead = false;
        this.isDeadMultipleTimes = false;
    }

    public String getCategory() {
        return "Animal";
    }

    public String shout() {
        return "@#$%^&*!";
    }

    public void kill() {
        if (this.isDead) {
            this.isDeadMultipleTimes = true;
        }
        this.isDead = true;
    }
}

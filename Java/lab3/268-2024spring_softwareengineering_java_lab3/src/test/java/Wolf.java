public class Wolf extends Animal {
    public boolean fried = false;
    public boolean steamed = false;
    public boolean boiled = false;
    public boolean grilled = false;
    public boolean baked = false;

    public Wolf(String name) {
        super(name);
    }

    public String getCategory() {
        return "Dog";
    }

    public String shout() {
        return this.name + " shouts: Howl!";
    }

    // Additional methods for cooking the wolf

    public void fry() {
        this.fried = true;
    }

    public void steam() {
        this.steamed = true;
    }

    public void boil() {
        this.boiled = true;
    }

    public void grill() {
        this.grilled = true;
    }

    public void bake() {
        this.baked = true;
    }

    public void cook() {
        this.fried = true;
        this.steamed = true;
        this.boiled = true;
        this.grilled = true;
        this.baked = true;
    }

    public boolean isAllCooked() {
        return fried & steamed & boiled & grilled & baked;
    }
}
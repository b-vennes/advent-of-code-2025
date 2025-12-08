package cafe.branden;

public record Dial(int location, int numbers) {

    public Dial rotateLeft(int amount) {
        int nextLocation = (this.location - amount + this.numbers) % this.numbers;

        return new Dial(nextLocation, this.numbers);
    }

    public Dial rotateRight(int amount) {
        int nextLocation = (this.location + amount) % this.numbers;

        return new Dial(nextLocation, this.numbers);
    }
}

package cafe.branden;

public record Dial(int location, int numbers) {

    public static Dial initial() {
        return new Dial(50, 100);
    }

    public Dial rotateLeft(int amount) {
        return rotateLeftCountingZeroes(amount).dial;
    }

    public CountingZeroes rotateLeftCountingZeroes(int amount) {

        int rotationRawValue = this.location - amount;

        int passedZeroes = rotationRawValue <= 0 ? Math.abs(rotationRawValue) / this.numbers : 0;

        // if we started above 0 and are now negative, we've "crossed" zero
        if (this.location > 0 && rotationRawValue <= 0) {
            passedZeroes++;
        }

        int normalized = rotationRawValue % this.numbers;

        int nextLocation = normalized >= 0
                ?
                // in a valid spot
                normalized
                :
                // need to move left from top spot
                normalized + this.numbers;

        return new CountingZeroes(new Dial(nextLocation, this.numbers), passedZeroes);
    }

    public Dial rotateRight(int amount) {
        return rotateRightCountingZeroes(amount).dial;
    }

    public CountingZeroes rotateRightCountingZeroes(int amount) {
        int rawRotationValue = this.location + amount;

        int passedZero = rawRotationValue / 100;

        int nextLocation = rawRotationValue % this.numbers;

        return new CountingZeroes(new Dial(nextLocation, this.numbers), passedZero);
    }

    public record CountingZeroes(Dial dial, int counted) {}
}

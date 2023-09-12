package App.Exceptions;

public class InputUserDataExeption extends Exception {
    private final int rightCount;
    private final int userCount;

    public InputUserDataExeption(String message, int userCount, int rightCount) {
        super(message);
        this.userCount = userCount;
        this.rightCount = rightCount;
    }

    public int getRightCount() {
        return this.rightCount;
    }

    public int getUserCount() {
        return this.userCount;
    }

}

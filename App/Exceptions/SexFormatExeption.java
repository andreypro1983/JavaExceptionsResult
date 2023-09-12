package App.Exceptions;

public class SexFormatExeption extends Exception {
    private final String userSex;
   
    public SexFormatExeption(String message, String userSex) {
        super(message);
        this.userSex = userSex;
    }

    public String getUserSex() {
        return this.userSex;
    }
}
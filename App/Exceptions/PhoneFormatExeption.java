package App.Exceptions;

public class PhoneFormatExeption extends Exception {
    private final String userPhone;
   
    public PhoneFormatExeption(String message, String userPhone) {
        super(message);
        this.userPhone = userPhone;
    }

    public String getUserPhone() {
        return this.userPhone;
    }
}

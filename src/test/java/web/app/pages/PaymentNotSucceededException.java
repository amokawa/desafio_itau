package web.app.pages;

public class PaymentNotSucceededException extends RuntimeException {
    public PaymentNotSucceededException(String s) {
        super(s);
    }
}

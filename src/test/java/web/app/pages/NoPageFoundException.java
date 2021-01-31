package web.app.pages;

public class NoPageFoundException extends RuntimeException {
    public NoPageFoundException(String page) {
        super("No page found with: " + page);
    }
}

package web.app.pages;

public class PageObjectNotFoundException extends RuntimeException {
    public PageObjectNotFoundException(String page) {
        super("No page found with: " + page);
    }
}

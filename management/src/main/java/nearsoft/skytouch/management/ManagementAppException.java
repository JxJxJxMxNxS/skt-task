package nearsoft.skytouch.management;

public class ManagementAppException extends Exception {
    public ManagementAppException(String s) {
        super(s);
    }

    public ManagementAppException(String s, Throwable throwable) {
        super(s, throwable);
    }
}

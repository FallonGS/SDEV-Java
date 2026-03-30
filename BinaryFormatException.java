public class BinaryFormatException extends Exception {
    public BinaryFormatException() {
        super("String must be binary");
    }

    public BinaryFormatException(String message) {
        super(message);
    }
}

package hanghae.common.exception;

public class NoContentsException extends RuntimeException {

    public NoContentsException(String content) {
        super("no contents with" + content + " found");
    }
}
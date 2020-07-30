package diplomna.error;

public class BaseException extends RuntimeException {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    protected BaseException(int status, String message) {
        super(message);
        this.setStatus(status);
    }
}
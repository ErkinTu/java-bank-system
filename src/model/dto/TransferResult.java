package model.dto;

public class TransferResult {
    private final boolean success;
    private final String message;

    public TransferResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}

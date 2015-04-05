public class EmptySequenceException extends RuntimeException {
    String message;

    public EmptySequenceException(String message) {
	this.message = message;
    }

    public String toString() {
	return message;
    }
} 

package ru.booksapi.exceptions;

/**
 * Exeption templates for Services
 *
 * @author Eugene Ovchinnikov
 */
public class ServiceExeption  extends Exception {

    public ServiceExeption() {
    }

    public ServiceExeption(String message) {
        super(message);
    }

    public ServiceExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceExeption(Throwable cause) {
        super(cause);
    }

    public ServiceExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

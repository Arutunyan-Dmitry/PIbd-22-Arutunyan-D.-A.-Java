package Train;

public class DepotAlreadyHaveException extends Exception {
    public DepotAlreadyHaveException() {
        super("На стоянке уже есть такой транспорт!");
    }
}
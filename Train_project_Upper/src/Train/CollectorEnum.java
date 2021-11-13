package Train;

public enum CollectorEnum {
    single, doubl, triple;

    public static CollectorEnum getCount(int count) {
        return switch (count) {
            case 1 -> single;
            case 2 -> doubl;
            case 3 -> triple;
            default -> null;
        };
    }
}

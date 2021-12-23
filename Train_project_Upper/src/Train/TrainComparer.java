package Train;

import java.util.Comparator;

public class TrainComparer implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle first, Vehicle second) {
        if (!first.getClass().getSimpleName().equals(second.getClass().getSimpleName())) {
            switch (first.getClass().getSimpleName()) {
                case "Train" -> {
                    return 1;
                }
                case "Electric_locomotive" -> {
                    return -1;
                }
            }
        } else {
            switch (first.getClass().getSimpleName()) {
                case "Train" -> {
                    return comparerTrain((Train) first, (Train) second);
                }
                case "Electric_locomotive" -> {
                    return comparerElTrain((Electric_locomotive) first, (Electric_locomotive) second);
                }
            }
        }
        return 1;
    }

    private int comparerTrain(Train first, Train second) {
        if (first.getMaxSpeed() != second.getMaxSpeed()) {
            return Integer.compare(first.getMaxSpeed(), second.getMaxSpeed());
        }
        if (first.getWeight() != second.getWeight()) {
            return Float.compare(first.getWeight(), second.getWeight());
        }
        if (first.getUpperColor() != second.getUpperColor()) {
            return Integer.compare(first.getUpperColor().getRGB(), second.getUpperColor().getRGB());
        }
        if (first.getLowerColor() != second.getLowerColor()) {
            return Integer.compare(first.getLowerColor().getRGB(), second.getLowerColor().getRGB());
        }
        return 0;
    }

    private int comparerElTrain(Electric_locomotive first, Electric_locomotive second) {
        int result = comparerTrain(first, second);
        if (result != 0) {
            return result;
        }

        if (first.getColColor() != second.getColColor()) {
            return Integer.compare(first.getColColor().getRGB(), second.getColColor().getRGB());
        }
        if (first.isBattery() != second.isBattery()) {
            return Boolean.compare(first.isBattery(), second.isBattery());
        }
        if (first.isCollector() != second.isCollector()) {
            return Boolean.compare(first.isCollector(), second.isCollector());
        }
        if (first.getiCollectors() != null && second.getiCollectors() != null
                && !(first.getiCollectors().toString().equals(second.getiCollectors().toString()))) {
            return first.getiCollectors().toString().compareTo(second.getiCollectors().toString());
        }
        if (first.getiCollectors() == null && second.getiCollectors() != null) {
            return 1;
        }
        if (first.getiCollectors() != null && second.getiCollectors() == null) {
            return -1;
        }
        return 0;
    }
}

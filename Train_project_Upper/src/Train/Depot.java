package Train;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Depot<T extends ITransport, G extends ICollectors>  implements Iterator<T>, Iterable<T>{

    private final List<T> places;

    private final int maxCount;

    private final int pictureWidth;

    private final int pictureHeight;

    private final int placeSizeWidth = 230;

    private final int placeSizeHeight = 130;

    private int currentIndex;

    public Depot(int picWidth, int picHeight) {
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        maxCount = width * height;
        places = new ArrayList<>();
        currentIndex = -1;
    }

    public boolean add(T vehicle) throws DepotOverflowException, DepotAlreadyHaveException {
        if (places.size() >= maxCount) {
            throw new DepotOverflowException();
        }
        if (places.contains(vehicle)) {
            throw new DepotAlreadyHaveException();
        }
        places.add(vehicle);
        return true;
    }

    public T delete(int index) throws DepotNotFoundException {
        if (index < -1 || index >= maxCount) {
            throw new DepotNotFoundException(index);
        }
        T vehicle = places.get(index);
        places.remove(index);
        return vehicle;
    }

    public void draw(Graphics g) {
        int width = pictureWidth / placeSizeWidth;
        drawMarking(g);
        for (int i = 0; i < places.size(); i++) {
            places.get(i).SetPosition(i % width * placeSizeWidth + 15,
                    i / width * placeSizeHeight + 15,
                    pictureWidth, pictureHeight);
            places.get(i).DrawTransport(g);
        }
    }

    private void drawMarking(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < pictureWidth / placeSizeWidth; i++) {
            for (int j = 0; j < pictureHeight / placeSizeHeight + 1; ++j) {
                g.drawLine(5 + i * placeSizeWidth, 5 + j * placeSizeHeight, 5 + i *
                        placeSizeWidth + placeSizeWidth / 2, 5 + j * placeSizeHeight);
            }
            g.drawLine(5 + i * placeSizeWidth, 5, 5 + i * placeSizeWidth,
                    5 + (pictureHeight / placeSizeHeight) * placeSizeHeight);
        }
    }

    public T get(int index) {
        if (index > -1 && index < places.size()) {
            return places.get(index);
        }
        return null;
    }

    public void sort() {
        places.sort((Comparator<? super T>) new TrainComparer());
    }


    public void clear() {
        places.clear();
    }

    @Override
    public boolean hasNext() {
        return currentIndex < places.size() - 1;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        currentIndex++;
        return places.get(currentIndex);
    }

    @Override
    public Iterator<T> iterator() {
        currentIndex = -1;
        return this;
    }
}

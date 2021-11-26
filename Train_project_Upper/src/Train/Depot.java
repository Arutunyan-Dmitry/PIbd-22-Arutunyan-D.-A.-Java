package Train;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Depot<T extends ITransport, G extends ICollectors> {

    private final List<T> places;

    private final int maxCount;

    private final int pictureWidth;

    private final int pictureHeight;

    private final int placeSizeWidth = 230;

    private final int placeSizeHeight = 130;

    public Depot(int picWidth, int picHeight) {
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        maxCount = width * height;
        places = new ArrayList<>();
    }

    public int add(T vehicle) {
        if (places.size() < maxCount) {
            places.add(vehicle);
            return places.size();
        }
        return -1;
    }

    public T delete(int index) {
        if (index >= 0 && index < maxCount && places.get(index) != null) {
            T vehicle = places.get(index);
            places.remove(index);
            return vehicle;
        }
        return null;
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
}

package Train;

import java.awt.*;

public class Depot<T extends ITransport, G extends ICollectors> {

    private final Object[] places;

    private final int pictureWidth;

    private final int pictureHeight;

    private final int placeSizeWidth = 230;

    private final int placeSizeHeight = 130;

    public Depot(int picWidth, int picHeight) {
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        places = new Object[width * height];
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    public int add(T vehicle) {
        int width = pictureWidth / placeSizeWidth;
        int height = pictureHeight / placeSizeHeight;

        for (int i = 0; i < places.length; i++) {
            if (CheckFreePlace(i)) {
                vehicle.SetPosition(i % width * placeSizeWidth + 15,
                        height + i / height * placeSizeHeight + 10,
                        pictureWidth, pictureHeight);
                places[i] = vehicle;
                return i;
            }
        }
        return -1;
    }

    public T delete(int index) {
        if (index < 0 || index > places.length) {
            return null;
        }
        if (!CheckFreePlace(index)) {
            T vehicle = (T) places[index];
            places[index] = null;
            return vehicle;
        }
        return null;
    }

    private boolean CheckFreePlace(int indexPlace) {
        return places[indexPlace] == null;
    }

    public boolean equal(Depot<T, G> depot, Depot<T, G> depotCompare) {
        return depot.places.length == depotCompare.places.length; }

    public boolean unequal(Depot<T, G> depot, Depot<T, G> depotCompare) {
            return depot.places.length != depotCompare.places.length; }

    public void draw(Graphics g) {
        drawMarking(g);
        for (Object place : places) {
            if (place != null) {
                T placeT = (T) place;
                placeT.DrawTransport(g);
            }
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
}

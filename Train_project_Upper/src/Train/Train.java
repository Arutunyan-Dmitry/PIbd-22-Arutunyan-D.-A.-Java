package Train;

import java.awt.*;
import java.util.Iterator;

public class Train extends Vehicle implements Comparable<Train>, Iterator<String>, Iterable<String> {
    /**
     * Ширина отрисовки поезда
     */
    protected int TrainWidth = 170;

    /**
     * Высота отрисовки поезда
     */
    protected int TrainHeight = 110;

    protected String separator = ";";

    private int current;

    /**
     * Конструктор
     * @param maxSpeed Максимальная скорость
     * @param weight Вес поезда
     * @param upperColor Верхний цвет поезда
     * @param lowerColor Нижний цвет поезда
     */
    public Train(int maxSpeed, float weight, Color upperColor, Color lowerColor) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        UpperColor = upperColor;
        LowerColor = lowerColor;
        current = -1;
    }

    /**
     * Конструктор с изменением размеров поезда
     * @param maxSpeed Максимальная скорость
     * @param weight Вес поезда
     * @param lowerColor Нижний цвет поезда
     * @param upperColor Верхний цвет поезда
     * @param TrainWidth Ширина отрисовки поезда
     * @param TrainHeight Высота отрисовки поезда
     */
    protected Train(int maxSpeed, float weight, Color lowerColor, Color upperColor, int TrainWidth, int TrainHeight) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        LowerColor = lowerColor;
        UpperColor = upperColor;
        this.TrainWidth = TrainWidth;
        this.TrainHeight = TrainHeight;
    }

    public Train(String info) {
        String[] str = info.split(separator);
        if (str.length == 4) {
            MaxSpeed = Integer.parseInt(str[0]);
            Weight = Float.parseFloat(str[1]);
            LowerColor = Color.decode(str[2]);
            UpperColor = Color.decode(str[3]);
        }
    }

    /**
     * Изменение направления пермещения
     * @param direction Направление
     */
    @Override
    public void MoveTransport(Direction direction) {
        float step = MaxSpeed * 100 / Weight;
        switch (direction) {
            // вправо
            case Right:
                if (_startPosX + step < _pictureWidth - TrainWidth) {
                    _startPosX += step;
                }
                break;
            //влево
            case Left:
                if (_startPosX - step >= 0) {
                    _startPosX -= step;
                }
                break;
            //вверх
            case Up:
                if (_startPosY - step >= 0) {
                    _startPosY -= step;
                }
                break;
            //вниз
            case Down:
                if (_startPosY + step < _pictureHeight - TrainHeight) {
                    _startPosY += step;
                }
                break;
        }
    }

    /**
     * Отрисовка поезда
     * @param g
     */
    @Override
    public void DrawTransport(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        // верхняя часть тепловоза
        g2d.setColor(UpperColor);
        int Upper_points_x[] = {_startPosX + 30, _startPosX + 190, _startPosX + 190, _startPosX + 10};
        int Upper_points_y[] = {_startPosY + 30, _startPosY + 30,_startPosY + 60, _startPosY + 60};
        g2d.fillPolygon(Upper_points_x, Upper_points_y, Upper_points_x.length);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(Upper_points_x, Upper_points_y, Upper_points_x.length);
        // нижняя часть тепловоза
        g2d.setColor(LowerColor);
        g2d.fillRect(_startPosX + 10, _startPosY + 60, 180, 30);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(_startPosX + 10, _startPosY + 60, 180, 30);
        // передний бампер
        g2d.setColor(Color.BLACK);
        int Front_bumper_x[] = {_startPosX + 10, _startPosX + 80, _startPosX + 80, _startPosX};
        int Front_bumper_y[] = {_startPosY + 90, _startPosY + 90, _startPosY + 100, _startPosY + 100};
        g2d.fillPolygon(Front_bumper_x, Front_bumper_y, Front_bumper_y.length);
        // задний бампер
        int Back_bumper_x[] = {_startPosX + 120, _startPosX + 190, _startPosX + 200, _startPosX + 120};
        int Back_bumper_y[] = {_startPosY + 90, _startPosY + 90, _startPosY + 100, _startPosY + 100};
        g2d.fillPolygon(Back_bumper_x, Back_bumper_y, Back_bumper_x.length);
        // колёса
        g2d.setColor(Color.decode("#A9A9A9"));
        g2d.fillOval(_startPosX + 20, _startPosY + 90, 20, 20);
        g2d.fillOval(_startPosX + 60, _startPosY + 90, 20, 20);
        g2d.fillOval(_startPosX + 120, _startPosY + 90, 20, 20);
        g2d.fillOval(_startPosX + 160, _startPosY + 90, 20, 20);
        //дверь
        g2d.setColor(Color.BLACK);
        g2d.fillRect( _startPosX + 70, _startPosY + 45, 20, 30);
        //окна
        g2d.setColor(Color.decode("#ADD8E6"));
        g.fillRect(_startPosX + 30, _startPosY + 40, 10, 15);
        g.fillRect(_startPosX + 50, _startPosY + 40, 10, 15);
        g.fillRect(_startPosX + 170, _startPosY + 40, 10, 15);
        //сцепка
        g2d.setColor(Color.BLACK);
        g.fillRect(_startPosX + 190, _startPosY + 40, 10, 40);
    }

    @Override
    public String toString() {
        return MaxSpeed + separator + Weight + separator + LowerColor.getRGB() + separator + UpperColor.getRGB();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof Train TrainObject)) {
            return false;
        }
        return equals(TrainObject);
    }

    public boolean equals(Train other) {
        if (other == null) {
            return false;
        }
        if (!this.getClass().getSimpleName().equals(other.getClass().getSimpleName())) {
            return false;
        }
        if (MaxSpeed != other.MaxSpeed) {
            return false;
        }
        if (Weight != other.Weight) {
            return false;
        }
        if (UpperColor.getRGB() != other.UpperColor.getRGB()) {
            return false;
        }
        if (LowerColor.getRGB() != other.LowerColor.getRGB()) {
            return false;
        }
        return true;
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (current > 3) {
            current = -1;
            return false;
        }
        return true;
    }

    @Override
    public String next() {
        current++;
        switch (current) {
            case 0 -> {
                return String.valueOf(MaxSpeed);
            }
            case 1 -> {
                return String.valueOf(Weight);
            }
            case 2 -> {
                return String.valueOf(UpperColor.getRGB());
            }
            case 3-> {
                return String.valueOf(LowerColor.getRGB());
            }
        }
        return null;
    }

    @Override
    public int compareTo(Train train) {
        if (MaxSpeed != train.MaxSpeed) {
            return Integer.compare(MaxSpeed, train.MaxSpeed);
        }
        if (Weight != train.Weight) {
            return Float.compare(Weight, train.Weight);
        }
        if (UpperColor.getRGB() != train.UpperColor.getRGB()) {
            return Integer.compare(UpperColor.getRGB(), train.getUpperColor().getRGB());
        }
        if (LowerColor.getRGB() != train.LowerColor.getRGB()) {
            return Integer.compare(LowerColor.getRGB(), train.getLowerColor().getRGB());
        }
        return 0;
    }
}

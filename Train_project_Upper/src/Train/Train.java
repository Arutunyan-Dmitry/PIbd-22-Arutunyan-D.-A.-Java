package Train;

import java.awt.*;

public class Train extends Vehicle {
    /**
     * Ширина отрисовки поезда
     */
    protected int TrainWidth = 170;

    /**
     * Высота отрисовки поезда
     */
    protected int TrainHeight = 110;

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
        // нижняя часть тепловоза
        g2d.setColor(LowerColor);
        g2d.fillRect(_startPosX + 10, _startPosY + 60, 180, 30);
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
}

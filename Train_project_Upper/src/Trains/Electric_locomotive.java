package Trains;
import java.awt.*;

public class Electric_locomotive {
    /**
     * Левая координата отрисовки электровоза
     */
    private int _startPosX;

    /**
     * Правая кооридната отрисовки электровоза
     */
    private int _startPosY;

    /**
     * Ширина окна отрисовки
     */
    private int _pictureWidth;

    /**
     * Высота окна отрисовки
     */
    private int _pictureHeight;

    /**
     * Ширина отрисовки электровоза
     */
    private final int  TrainWidth = 200;

    /**
     * Высота отрисовки электровоза
     */
    private final int TrainHeight = 150;

    /**
     * Максимальная скорость
     */
    public int MaxSpeed;


    public int getMaxSpeed() { return MaxSpeed; }
    public void setMaxSpeed(int maxSpeed) { MaxSpeed = maxSpeed; }

    /**
     * Вес электровоза
     */
    public float Weight;
    public float getWeight() { return Weight; }
    public void setWeight(float weight) { Weight = weight; }

    /**
     * Верхний цвет электровоза
     */
    public Color UpperColor;
    public Color getUpperColor() { return UpperColor; }
    public void setUpperColor(Color upperColor) { UpperColor = upperColor; }

    /**
     * Нижний цвет электровоза
     */
    public Color LowerColor;
    public Color getLowerColor() { return LowerColor; }
    public void setLowerColor(Color lowerColor) { LowerColor = lowerColor; }

    /**
     * Цвет токоприёмника
     */
    public Color ColColor;
    public Color getColColor() { return ColColor; }
    public void setColColor(Color colColor) { ColColor = colColor; }

    /**
     * Признак наличия токоприёмника
     */
    public boolean Collector;
    public boolean isCollector() { return Collector; }
    public void setCollector(boolean collector) { Collector = collector; }

    /**
     * Признак наличия аккумулятора
     */
    public boolean Battery;
    public boolean isBattery() { return Battery; }
    public void setBattery(boolean battery) { Battery = battery; }

    /**
     * Класс токоприёмника
     */
    CollectorDop collectorDop;

    /**
     * Инициализация свойств
     * @param maxSpeed Максимальная скорость
     * @param weight Вес электровоза
     * @param upperColor Верхний цвет электровоза
     * @param lowerColor Нижний цвет электровоза
     * @param colColor Цвет токоприёмника
     * @param collector Признак наличия токоприёмника
     * @param battery Признак наличия аккумулятора
     */
    public void Init(int maxSpeed, float weight, Color upperColor, Color lowerColor,
                     Color colColor, boolean collector, boolean battery, int CollectorNum)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        UpperColor = upperColor;
        LowerColor = lowerColor;
        ColColor = colColor;
        Collector = collector;
        Battery = battery;
        collectorDop = new CollectorDop();
        collectorDop.SetCollectorNum(CollectorNum);
    }

    /**
     * Установка позиции электровоза
     * @param x Координата X
     * @param y Координата Y
     * @param width Ширина картинки
     * @param height Высота картинки
     */
    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    /**
     * Изменение направления пермещения
     * @param direction Направление
     */
    public void MoveTransport(Direction direction)
    {
        float step = MaxSpeed * 100 / Weight;
        switch (direction)
        {
            // вправо
            case Left:
                if (_startPosX - step >= 0)
                {
                    _startPosX -= step;
                }
                break;
            //влево
            case Right:
                if (_startPosX + step < _pictureWidth - TrainWidth)
                {
                    _startPosX += step;
                }
                break;
            //вверх
            case Up:
                if (_startPosY - step >= 0)
                {
                    _startPosY -= step;
                }
                break;
            //вниз
            case Down:
                if (_startPosY + step < _pictureHeight - TrainHeight)
                {
                    _startPosY += step;
                }
                break;
        }
    }

    /**
     * Отрисовка поезда
     * @param g
     */
    public void drawTrain(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLACK);
        // отрисуем токоприёмник
        if (Collector)
        g2d.setStroke(new BasicStroke(2));
        {
            collectorDop.DrawCollectors(g, _startPosX, _startPosY, ColColor);
        }

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
        //аккумулятор
        if (Battery)
        {
            g2d.setColor(Color.red);
            g2d.fillRect(_startPosX + 110, _startPosY + 50, 30, 20);
        }
    }
}


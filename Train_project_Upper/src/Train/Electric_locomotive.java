package Train;

import java.awt.*;

public class Electric_locomotive extends Train {

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
     * Интерфейс токоприёмников
     */
      private ICollectors iCollectors;
      public void setCollectors(ICollectors iCollectors) {this.iCollectors = iCollectors;}

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
    public Electric_locomotive(int maxSpeed, float weight, Color upperColor, Color lowerColor,
                     Color colColor, boolean collector, boolean battery, int CollectorForm, int CollectorNum)
    {
        super(maxSpeed, weight, upperColor, lowerColor, 200, 110);
        MaxSpeed = maxSpeed;
        Weight = weight;
        UpperColor = upperColor;
        LowerColor = lowerColor;
        ColColor = colColor;
        Collector = collector;
        Battery = battery;
        switch (CollectorForm) {
            case 0:
                iCollectors = new CollectorLine(CollectorNum);
                break;
            case 1:
                iCollectors = new CollectorRhombus(CollectorNum);
                break;
            case 2:
                iCollectors = new CollectorRound(CollectorNum);
                break;
        }
    }

    /**
     * Отрисовка эл-тов электровоза
     * @param g
     */
    @Override
    public void DrawTransport(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        super.DrawTransport(g);
        g2d.setColor(Color.BLACK);
        // отрисуем токоприёмник
        g2d.setStroke(new BasicStroke(2));
        if(Collector) {
            if (iCollectors != null) {
                iCollectors.DrawCollectors(g, _startPosX, _startPosY, ColColor);
            }
        }
        //аккумулятор
        if (Battery)
        {
            g2d.setColor(ColColor);
            g2d.fillRect(_startPosX + 110, _startPosY + 50, 30, 20);
        }
    }

    public void SetDopColor(Color color)
    {
        ColColor = color;
    }
}


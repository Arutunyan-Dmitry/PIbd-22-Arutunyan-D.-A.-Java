package Train;

import java.awt.*;
import java.util.Iterator;

public class Electric_locomotive extends Train  {

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
    public ICollectors getiCollectors() { return iCollectors; }
    public void setCollectors(ICollectors iCollectors) {this.iCollectors = iCollectors;}

    private int current;

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
                     Color colColor, boolean collector, boolean battery)
    {
        super(maxSpeed, weight, upperColor, lowerColor, 200, 110);
        MaxSpeed = maxSpeed;
        Weight = weight;
        UpperColor = upperColor;
        LowerColor = lowerColor;
        ColColor = colColor;
        Collector = collector;
        Battery = battery;
        current = -1;
    }

    public Electric_locomotive(String info) {
        super(info);
        String[] str = info.split(separator);
        if (str.length == 8) {
            MaxSpeed = Integer.parseInt(str[0]);
            Weight = Float.parseFloat(str[1]);
            UpperColor = Color.decode(str[2]);
            LowerColor = Color.decode(str[3]);
            ColColor = Color.decode(str[4]);
            Battery = Boolean.parseBoolean(str[5]);
            Collector = Boolean.parseBoolean(str[6]);
            if (str[7].contains("null")) {
                iCollectors = null;
            } else {
                String[] argsCollectors = str[7].split("\\.");
                int digit = Integer.parseInt(argsCollectors[1]);
                switch (argsCollectors[0]) {
                    case "CollectorLine" -> iCollectors = new CollectorLine(digit);
                    case "CollectorRound" -> iCollectors = new CollectorRound(digit);
                    case "CollectorRhombus" -> iCollectors = new CollectorRhombus(digit);
                }
            }
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

    @Override
    public String toString() {
        return super.toString() + separator + ColColor.getRGB() + separator + Battery + separator
        + Collector + separator + iCollectors;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof Electric_locomotive electric_locomotiveObject)) {
            return false;
        }
        return equals(electric_locomotiveObject);
    }

    public boolean equals(Electric_locomotive other) {
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
        if (ColColor.getRGB() != other.ColColor.getRGB()) {
            return false;
        }
        if (Collector != other.Collector) {
            return false;
        }
        if (Battery != other.Battery) {
            return false;
        }
        if (iCollectors != null && other.iCollectors != null && !(iCollectors.toString().equals(other.iCollectors.toString()))) {
            return false;
        }
        if (iCollectors == null ^ other.iCollectors == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hasNext() {
        if (current > 7) {
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
            case 3 -> {
                return String.valueOf(LowerColor.getRGB());
            }
            case 4 -> {
                return String.valueOf(ColColor.getRGB());
            }
            case 5 -> {
                return String.valueOf(Collector);
            }
            case 6 -> {
                return String.valueOf(Battery);
            }
            case 7 -> {
                return String.valueOf(iCollectors);
            }
        }
        return null;
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }

    @Override
    public int compareTo(Train train) {
        Electric_locomotive electric_locomotive = (Electric_locomotive) train;
        if (ColColor.getRGB() != electric_locomotive.ColColor.getRGB()) {
            return Integer.compare(ColColor.getRGB(), electric_locomotive.getColColor().getRGB());
        }
        if (Battery != electric_locomotive.Battery) {
            return Boolean.compare(Battery, electric_locomotive.Battery);
        }
        if (Collector != electric_locomotive.Collector) {
            return Boolean.compare(Collector, electric_locomotive.Collector);
        }
        if (iCollectors == null && electric_locomotive.iCollectors != null) {
            return 1;
        }
        if (iCollectors != null && electric_locomotive.iCollectors == null) {
            return -1;
        }
        return 0;
    }
}


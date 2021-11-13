package Train;

import java.awt.*;

public class CollectorLine implements ICollectors {
    /**
     * Возможное колличество токоприёмников
     */
    private CollectorEnum collectorEnum;

    public CollectorLine(int number) { SetCollectorNum(number); };
    /**
     * Текущее колличество токоприёмников
     */
    @Override
    public void SetCollectorNum(int number) { this.collectorEnum = CollectorEnum.getCount(number);};

    /**
     * Отрисовка токоприёмников
     * @param g
     * @param StartPosX Позиция X
     * @param StartPosY Позиция Y
     * @param ColColor Цвет токоприёмника
     */
    @Override
    public void DrawCollectors(Graphics g, int StartPosX, int StartPosY, Color ColColor) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(ColColor);
        switch (collectorEnum) {
            case single:
                g2d.drawLine(StartPosX + 140, StartPosY, StartPosX + 120, StartPosY + 30);
            case doubl:
                g2d.drawLine(StartPosX + 120, StartPosY, StartPosX + 100, StartPosY + 30);
            case triple:
                g2d.drawLine(StartPosX + 100, StartPosY, StartPosX + 80, StartPosY + 30);
                break;
        }
    }
}

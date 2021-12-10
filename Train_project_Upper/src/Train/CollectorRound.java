package Train;

import java.awt.*;

public class CollectorRound implements ICollectors{
    /**
     * Возможное колличество токоприёмников
     */
    private CollectorEnum collectorEnum;

    public CollectorRound(int number) { SetCollectorNum(number); };
    /**
     * Текущее колличество токоприёмников
     */
    @Override
    public void SetCollectorNum(int number) {
        this.collectorEnum = CollectorEnum.getCount(number);
    };

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
                g2d.drawOval(StartPosX + 105, StartPosY, 30, 30);
            case doubl:
                g2d.drawOval(StartPosX + 85, StartPosY, 30, 30);
            case triple:
                g2d.drawOval(StartPosX + 65, StartPosY, 30, 30);
                break;
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '.' + (collectorEnum.ordinal() + 1);
    }
}

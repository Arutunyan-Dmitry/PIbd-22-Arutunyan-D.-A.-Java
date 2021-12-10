package Train;

import java.awt.*;

public class CollectorRhombus implements ICollectors {
    /**
     * Возможное колличество токоприёмников
     */
    private CollectorEnum collectorEnum;

    public CollectorRhombus(int number) { SetCollectorNum(number); };
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
                int Coll_points_x1[] = {StartPosX + 120, StartPosX + 135, StartPosX + 120, StartPosX + 105};
                int Coll_points_y1[] = {StartPosY,  StartPosY + 15, StartPosY + 30, StartPosY + 15};
                g2d.drawPolygon(Coll_points_x1, Coll_points_y1, Coll_points_x1.length);
            case doubl:
                int Coll_points_x2[] = {StartPosX + 100, StartPosX + 115, StartPosX + 100, StartPosX + 85};
                int Coll_points_y2[] = {StartPosY,  StartPosY + 15, StartPosY + 30, StartPosY + 15};
                g2d.drawPolygon(Coll_points_x2, Coll_points_y2, Coll_points_x2.length);
            case triple:
                int Coll_points_x3[] = {StartPosX + 80, StartPosX + 95, StartPosX + 80, StartPosX + 65};
                int Coll_points_y3[] = {StartPosY,  StartPosY + 15, StartPosY + 30, StartPosY + 15};
                g2d.drawPolygon(Coll_points_x3, Coll_points_y3, Coll_points_x3.length);
                break;
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '.' + (collectorEnum.ordinal() + 1);}
}

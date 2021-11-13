package Trains;

import java.awt.*;

public class CollectorDop {

    /**
     * Правая координата отрисовки
     */
    private int StartPosX;

    /**
     * Левая координата отрисовки
     */
    private int StartPosY;

    /**
     * Возможное колличество токоприёмников
     */
    private CollectorEnum collectorEnum;

    /**
     * Текущее колличество токоприёмников
     */
    public void  SetCollectorNum(int number) {
        collectorEnum = CollectorEnum.getCount(number);
    };

    /**
     * Цвет токоприёмника
     */
    private Color ColColor;

    /**
     * Отрисовка токоприёмников
     * @param g
     * @param startPosX Позиция X
     * @param startPosY Позиция Y
     * @param colColor Цвет токоприёмника
     */
    public void DrawCollectors(Graphics g, int startPosX, int startPosY, Color colColor) {
        StartPosX = startPosX;
        StartPosY = startPosY;
        ColColor = colColor;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(ColColor);
        switch (collectorEnum) {
            case single:
                g2d.drawLine(StartPosX + 100, StartPosY, StartPosX + 80, StartPosY + 30);
            case doubl:
                g2d.drawLine(StartPosX + 120, StartPosY, StartPosX + 100, StartPosY + 30);
            case triple:
                g2d.drawLine(StartPosX + 140, StartPosY, StartPosX + 120, StartPosY + 30);
                break;
        }
    }
}

package Train;

import java.awt.*;

public interface ICollectors {

    /**
     * Установка кол-ва рогов
     * @param number кол-во рогов
     */
    void SetCollectorNum(int number);

    /**
     * Отрисовка рогов
     * @param g
     * @param startPosX Начальная координата Х
     * @param startPosY Начальная координата Y
     * @param colColor Цвет рогов
     */
    void DrawCollectors(Graphics g, int startPosX, int startPosY, Color colColor);

}

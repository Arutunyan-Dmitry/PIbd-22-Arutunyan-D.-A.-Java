package Train;

import java.awt.*;

public abstract class Vehicle implements ITransport {
    /**
     * Левая координата отрисовки транспорта
     */
    protected int _startPosX;

    /**
     * Правая кооридната отрисовки транспорта
     */
    protected int _startPosY;

    /**
     * Ширина окна отрисовки
     */
    protected int _pictureWidth;

    /**
     * Высота окна отрисовки
     */
    protected int _pictureHeight;

    /**
     * Максимальная скорость
     */
    public int MaxSpeed;

    /**
     * Вес транспорта
     */
    public float Weight;

    /**
     * Вес транспорта
     */
    public Color UpperColor;

    /**
     * Нижний цвет кузова
     */
    public Color LowerColor;

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
    public abstract void DrawTransport(Graphics g);
    public abstract void MoveTransport(Direction direction);
}

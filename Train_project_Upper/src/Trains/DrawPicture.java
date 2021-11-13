package Trains;

import javax.swing.*;
import java.awt.*;

public class DrawPicture extends JComponent {

    private Electric_locomotive electric_locomotive;

    /**
     * Отрисовка компонента
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (electric_locomotive != null) {
            electric_locomotive.drawTrain(g);
        }
        super.repaint();
    }

    /**
     * Добавить объект
     * @param ex
     */
    public void setVehicle(Electric_locomotive ex) {
        this.electric_locomotive = ex;
    }

    /**
     * Взять объект
     * @return
     */
    public Electric_locomotive getVehicle() {
        return electric_locomotive;
    }

}

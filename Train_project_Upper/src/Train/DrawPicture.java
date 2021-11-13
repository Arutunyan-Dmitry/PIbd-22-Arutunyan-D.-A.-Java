package Train;

import javax.swing.*;
import java.awt.*;

public class DrawPicture extends JPanel {

    private ITransport iTransport;

    /**
     * Отрисовка компонента
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (iTransport != null) {
            iTransport.DrawTransport(g);
        }
        super.repaint();
    }

    /**
     * Добавить объект
     * @param ex
     */
    public void setVehicle(ITransport ex) {
        this.iTransport = ex;
    }

    /**
     * Взять объект
     * @return
     */
    public ITransport getVehicle() {
        return iTransport;
    }

}

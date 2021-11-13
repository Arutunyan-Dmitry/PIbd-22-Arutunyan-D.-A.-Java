package Train;

import javax.swing.*;
import java.awt.*;

public class DrawDepot extends JPanel {
    private final Depot<ITransport, ICollectors> trainDepot;

    public DrawDepot(Depot<ITransport, ICollectors> trainDepot) {
        this.trainDepot = trainDepot;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (trainDepot != null) {
            trainDepot.draw(g);
        }
        super.repaint();
    }

    public Depot<ITransport, ICollectors> trainDepot() {
        return trainDepot;
    }
}


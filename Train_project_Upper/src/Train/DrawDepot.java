package Train;

import javax.swing.*;
import java.awt.*;

public class DrawDepot extends JPanel {

    private final DepotCollections depotCollections;
    private String selectedItem = null;

    public DrawDepot(DepotCollections depotCollections) {
        this.depotCollections = depotCollections;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (selectedItem != null) {
            if (depotCollections != null) {
                depotCollections.get(selectedItem).draw(g);
            }
        }
        super.repaint();
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
}
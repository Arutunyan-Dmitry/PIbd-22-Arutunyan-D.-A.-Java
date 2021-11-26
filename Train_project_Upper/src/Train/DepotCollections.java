package Train;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DepotCollections {
    private final Map<String, Depot<ITransport, ICollectors>> depotStages;

    private final int pictureWidth;

    private final int pictureHeight;

    public DepotCollections(int pictureWidth, int pictureHeight) {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        depotStages = new HashMap<>();
    }

    public Set<String> keys() {
        return depotStages.keySet();
    }

    public void AddDepot(String name) {
        if (depotStages.containsKey(name)) {
            return;
        }
        depotStages.put(name, new Depot<ITransport, ICollectors>(pictureWidth, pictureHeight));
    }

    public void DeleteDepot(String name) {
        depotStages.remove(name);
    }

    public Depot<ITransport, ICollectors> get(String name) {
        if (depotStages.containsKey(name)) {
            return depotStages.get(name);
        }
        return null;
    }

    public ITransport get(String name, int index) {
        if (depotStages.containsKey(name)) {
            return depotStages.get(name).get(index);
        }
        return null;
    }
}
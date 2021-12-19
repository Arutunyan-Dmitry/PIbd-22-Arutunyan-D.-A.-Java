package Train;

import java.io.*;
import java.security.KeyException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DepotCollections {
    private final Map<String, Depot<ITransport, ICollectors>> depotStages;

    private final int pictureWidth;

    private final int pictureHeight;

    private final String separator = ":";

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

    public void saveData(String filename) throws IOException {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            fileWriter.write("DepotCollection\n");
            for (Map.Entry<String, Depot<ITransport, ICollectors>> level : depotStages.entrySet()) {
                fileWriter.write("Depot" + separator + level.getKey() + '\n');
                ITransport transport;
                for (int i = 0; (transport = level.getValue().get(i)) != null; i++) {
                    if (transport.getClass().getSimpleName().equals("Train")) {
                        fileWriter.write("Train" + separator);
                    } else if (transport.getClass().getSimpleName().equals("Electric_locomotive")) {
                        fileWriter.write("Electric_Locomotive" + separator);
                    }
                    fileWriter.write(transport.toString() + '\n');
                }
            }
        }
    }

    public void loadData(String filename) throws IOException, DepotOverflowException{
        if (!(new File(filename).exists())) {
            throw new FileNotFoundException("Файл " + filename + " не найден");
        }

        try (FileReader fileReader = new FileReader(filename)) {
            Scanner sc = new Scanner(fileReader);
            if (sc.nextLine().contains("DepotCollection")) {
                depotStages.clear();
            } else {
                throw new IllegalArgumentException("Неверный формат файла");
            }

            ITransport transport = null;
            String key = "";
            String line;

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.contains("Depot")) {
                    key = line.split(separator)[1];
                    depotStages.put(key, new Depot<ITransport, ICollectors>(pictureWidth, pictureHeight));
                } else if (line.contains(separator)) {
                    if (line.contains("Train")) {
                        transport = new Train(line.split(separator)[1]);
                    } else if (line.contains("Electric_Locomotive")) {
                        transport = new Electric_locomotive(line.split(separator)[1]);
                    }
                    if (!depotStages.get(key).add(transport)) {
                        throw new DepotOverflowException();
                    }
                }
            }
        }
    }

    public void saveDepot(String filename, String key) throws IOException, KeyException {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        if (!depotStages.containsKey(key)) {
            throw new KeyException();
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            if (depotStages.containsKey(key))
                fileWriter.write("Depot" + separator + key + '\n');
            ITransport transport;
            for (int i = 0; (transport = depotStages.get(key).get(i)) != null; i++) {
                if (transport.getClass().getSimpleName().equals("Train")) {
                    fileWriter.write("Train" + separator);
                } else if (transport.getClass().getSimpleName().equals("Electric_locomotive")) {
                    fileWriter.write("Electric_Locomotive" + separator);
                }
                fileWriter.write(transport.toString() + '\n');
            }
        }
    }

    public void loadDepot(String filename) throws IOException, DepotOverflowException{
        if (!(new File(filename).exists())) {
            throw new FileNotFoundException("Файл " + filename + " не найден");
        }
        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            String key;
            String line;
            line = scanner.nextLine();
            if (line.contains("Depot:")) {
                key = line.split(separator)[1];
                if (depotStages.containsKey(key)) {
                    depotStages.get(key).clear();
                } else {
                    depotStages.put(key, new Depot<ITransport, ICollectors>(pictureWidth, pictureHeight));
                }
            } else {
                throw new IllegalArgumentException("Неверный формат файла");
            }
            ITransport transport = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(separator)) {
                    if (line.contains("Train")) {
                        transport = new Train(line.split(separator)[1]);
                    } else if (line.contains("Electric_Locomotive")) {
                        transport = new Electric_locomotive(line.split(separator)[1]);
                    }
                    if (!depotStages.get(key).add(transport)) {
                        throw new DepotOverflowException();
                    }
                }
            }
        }
    }
}
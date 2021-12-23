package Train;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyException;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DepotForm {
    private JFrame frame;
    private JButton parkTrain;
    private JButton getTrainFromList;
    private JButton putTrainIntoList;
    private JButton addDepot;
    private JButton deleteDepot;
    private JTextField placeTransport;
    private DrawDepot drawDepot;
    private JPanel groupBox;
    private JPanel depotsGroupBox;
    private JLabel placeText;
    private Border borderTake;
    private Border borderDepot;
    private JTextField depotsField;
    private JList<String> listBoxDepots;
    private DepotCollections depotCollections;
    private DefaultListModel<String> depotList;
    private List<Train> listTransport;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu depotFileMenu;
    private JMenuItem saveFile;
    private JMenuItem loadFile;
    private JMenuItem saveDepot;
    private JMenuItem loadDepot;
    private Logger logger;
    private String FatalMessage = "Здравствуй, разработчик!\n\nТребуется твоё вмешательсво, т.к." +
            "во время работы кода возникло исключение уровня FATAL.\n\nИнформация по исключению:\n";
    private JButton sortTransport;

    public DepotForm() {
        initialization();
        frame = new JFrame("Депо");
        frame.setSize(960, 760);
        frame.setState(JFrame.NORMAL);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(depotsGroupBox);
        frame.getContentPane().add(parkTrain);
        frame.getContentPane().add(sortTransport);
        frame.getContentPane().add(groupBox);
        frame.getContentPane().add(drawDepot);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().setBackground(Color.PINK);
        frame.repaint();
    }

    public void initialization() {
        logger = LogManager.getLogger(DepotForm.class);
        PropertyConfigurator.configure("src/log4j2.properties");
        listTransport = new LinkedList<>();
        depotList = new DefaultListModel<>();
        depotCollections = new DepotCollections(690,650);
        drawDepot = new DrawDepot(depotCollections);
        drawDepot.setBackground(Color.LIGHT_GRAY);
        borderTake = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Забрать поезд");
        borderDepot = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Депо");
        sortTransport = new JButton("Сортировать");
        parkTrain = new JButton("Припарковать поезд");
        addDepot = new JButton("Добавить депо");
        deleteDepot = new JButton("Удалить депо");
        placeTransport = new JTextField();
        depotsField = new JTextField();
        listBoxDepots = new JList<>(depotList);
        getTrainFromList = new JButton("Забрать из списка");
        putTrainIntoList = new JButton("Добавить в список");
        placeText = new JLabel("Место: ");
        drawDepot.setBounds(20, 20, 700, 660);

        depotsGroupBox = new JPanel();
        depotsGroupBox.setLayout(null);
        depotsGroupBox.setBackground(Color.PINK);
        depotsGroupBox.setBounds(730, 10, 190, 350);
        depotsGroupBox.add(depotsField);
        depotsGroupBox.add(listBoxDepots);
        depotsGroupBox.add(addDepot);
        depotsGroupBox.add(deleteDepot);
        depotsGroupBox.setBorder(borderDepot);
        depotsField.setBounds(10, 20, 170, 25);
        addDepot.setBounds(10, 50, 170, 25);
        addDepot.addActionListener(e -> {
            AddDepot();
        });
        listBoxDepots.setBounds(10, 80, 170, 230);
        listBoxDepots.addListSelectionListener(e -> {
            changeItemList();
        });
        deleteDepot.setBounds(10, 315, 170, 25);
        deleteDepot.addActionListener(e -> {
            DeleteDepot();
        });

        groupBox = new JPanel();
        groupBox.setLayout(null);
        groupBox.setBackground(Color.PINK);
        groupBox.add(placeText);
        groupBox.add(placeTransport);
        groupBox.add(getTrainFromList);
        groupBox.add(putTrainIntoList);

        sortTransport.setBounds(730, 365, 190, 40);
        sortTransport.addActionListener(e -> { sort(); });
        parkTrain.setBounds(730, 410, 190, 40);
        parkTrain.addActionListener(e -> createTrain());
        groupBox.setBounds(730, 455, 190, 120);
        placeText.setBounds(50, 20, 60, 30);
        placeTransport.setBounds(100, 20, 40, 25);
        putTrainIntoList.setBounds(10, 55, 170, 25);
        putTrainIntoList.addActionListener(e -> {
            PutTrainIntoList();
        });
        getTrainFromList.setBounds(10, 85, 170, 25);
        getTrainFromList.addActionListener(e -> {
            GetTrainFromList();
        });
        groupBox.setBorder(borderTake);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("Файл");
        saveFile = new JMenuItem("Сохранить");
        saveFile.addActionListener(e -> {
           saveFile();
        });
        loadFile = new JMenuItem("Загрузить");
        loadFile.addActionListener(e -> {
           loadFile();
        });
        depotFileMenu = new JMenu("Депо");
        saveDepot = new JMenuItem("Сохранить");
        saveDepot.addActionListener(e -> {
           saveDepot();
        });
        loadDepot = new JMenuItem("Загрузить");
        loadDepot.addActionListener(e -> {
          loadDepot();
        });
        fileMenu.add(saveFile);
        fileMenu.add(loadFile);
        depotFileMenu.add(saveDepot);
        depotFileMenu.add(loadDepot);
        menuBar.add(fileMenu);
        menuBar.add(depotFileMenu);
    }

    private void createTrain() {
        if (listBoxDepots.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Депо не выбрано", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            TrainConfigForm trainConfigForm = new TrainConfigForm(frame);
            ITransport transport = trainConfigForm.getTransport();
            if (transport != null) {
                if (!depotCollections.get(listBoxDepots.getSelectedValue()).add(transport)) {
                    logger.info("Добавлен поезд " + transport);
                    frame.repaint();
                }
            }
        } catch (DepotOverflowException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "переполнение", JOptionPane.ERROR_MESSAGE);
            logger.warn("Попытка поставить поезд в уже заполненное депо");
        } catch (DepotAlreadyHaveException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Совпадение", JOptionPane.ERROR_MESSAGE);
            logger.warn("В депо уже есть такой транспорт");
        } catch (Exception e) {
            logger.fatal(FatalMessage + "Неизвестная неудачная попытка поставить поезд в депо " + e.getMessage());
            JOptionPane.showMessageDialog(frame,e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void PutTrainIntoList() {
        if (listBoxDepots.getSelectedIndex() >= 0) {
            if (!placeTransport.getText().equals("")) {
                try {
                    Train transport = (Train) depotCollections.get(listBoxDepots.getSelectedValue()).delete(Integer.parseInt(placeTransport.getText()));
                    if (transport != null) {
                        listTransport.add(transport);
                        logger.info("Добавили поезд " + transport + " в список");
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Не существующий транспорт", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Не существующий транспорт", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Депо не выбрано", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void GetTrainFromList() {
        if (!listTransport.isEmpty()) {
            int result = JOptionPane.showConfirmDialog(frame, "Забрать " + listTransport.size() + "й поезд из списка?", "Забрать поезд",
                    JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION) {
                FormElTrain formElTrain = new FormElTrain();
                formElTrain.setTrain(listTransport.get(0));
                logger.info("Изъяли поезд " + listTransport.get(0) + " из списка");
                listTransport.remove(0);
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Список пуст");
        }
    }

    private void reloadLevels() {
        int index = listBoxDepots.getSelectedIndex();
        depotList.removeAllElements();
        int i = 0;
        for (String name : depotCollections.keys()) {
            depotList.add(i, name);
            i++;
        }
        int itemsCount = depotList.size();
        if (itemsCount > 0 && (index < 0 || index >= itemsCount)) {
            listBoxDepots.setSelectedIndex(0);
        } else if (index >= 0 && index < itemsCount) {
            listBoxDepots.setSelectedIndex(index);
        }
    }

    private void AddDepot() {
        if (!depotsField.getText().equals("")) {
            depotCollections.AddDepot(depotsField.getText());
            logger.info("Добавили депо " + depotsField.getText());
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Введите название депо", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void DeleteDepot() {
        if (listBoxDepots.getSelectedIndex() >= 0) {
            int result = JOptionPane.showConfirmDialog(frame, "Удалить депо " + listBoxDepots.getSelectedValue() + "?", "Удаление",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                depotCollections.DeleteDepot(listBoxDepots.getSelectedValue());
                logger.info("Удалили депо " + listBoxDepots.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Депо не выбрано", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeItemList() {
        drawDepot.setSelectedItem(listBoxDepots.getSelectedValue());
        if (listBoxDepots.getSelectedValue() != null) {
            logger.info("Перешли в депо " + listBoxDepots.getSelectedValue());
        }
        frame.repaint();
    }

    private void saveFile() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                depotCollections.saveData(fileSaveDialog.getSelectedFile().getPath());
                logger.info("Сохранено в файл " + fileSaveDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл успешно сохранен", "Результат", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e){
                logger.fatal(FatalMessage + "Неизвестная неудачная попытка сохранения файла "  + e.getMessage());
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка при сохранении", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadFile() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                depotCollections.loadData(fileOpenDialog.getSelectedFile().getPath());
                logger.info("Загружено из файла " + fileOpenDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл успешно загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                reloadLevels();
                frame.repaint();
            } catch (FileNotFoundException e) {
                logger.warn("Попытка найти не существующий фаил для загрузки");
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Файл отсутствует", JOptionPane.ERROR_MESSAGE);
            }
            catch (IllegalArgumentException e) {
                logger.warn("Попытка загрузки файла с неверным форматом");
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неверный формат файла", JOptionPane.ERROR_MESSAGE);
            }
            catch (DepotOverflowException e) {
                logger.warn("Попытка загрузки переполненного депо");
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Депо переполнено", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                logger.fatal(FatalMessage + "Неизвестная неудачная попытка загрузки файла "  + e.getMessage());
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка при загрузке", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveDepot() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        if (listBoxDepots.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Выберите депо", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                depotCollections.saveDepot(fileSaveDialog.getSelectedFile().getPath(), listBoxDepots.getSelectedValue());
                logger.info("Депо " + listBoxDepots.getSelectedValue() + " сохранено в файл " + fileSaveDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл успешно сохранен", "Результат", JOptionPane.INFORMATION_MESSAGE);
            } catch (KeyException e) {
                logger.warn("Попытка сохранения несуществующего депо");
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Сохранение несуществующего депо", JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception e) {
                logger.fatal(FatalMessage + "Неизвестная неудачная попытка сохранения депо "  + e.getMessage());
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка при сохранении", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadDepot() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try{
                depotCollections.loadDepot(fileOpenDialog.getSelectedFile().getPath());
                logger.info("Депо " + listBoxDepots.getSelectedValue() + " агружено из файла " + fileOpenDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл успешно загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                reloadLevels();
                frame.repaint();
            } catch (FileNotFoundException e) {
                logger.warn("Попытка найти не существующий фаил для загрузки депо");
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Файл отсутствует", JOptionPane.ERROR_MESSAGE);
            }
            catch (IllegalArgumentException e) {
                logger.warn("Попытка загрузки депо из файла с неверным форматом");
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неверный формат файла", JOptionPane.ERROR_MESSAGE);
            }
            catch (DepotOverflowException e) {
                logger.warn("Попытка загрузки переполненного депо");
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Депо переполнено", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                logger.fatal(FatalMessage + "Неизвестная неудачная попытка загрузки файла " + e.getMessage());
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка при загрузке", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void sort() {
        if (listBoxDepots.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Депо не выбрано", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        depotCollections.get(listBoxDepots.getSelectedValue()).sort();
        frame.repaint();
        logger.info("Транспорт в депо " + listBoxDepots.getSelectedValue() + " отсортирован");
    }
}
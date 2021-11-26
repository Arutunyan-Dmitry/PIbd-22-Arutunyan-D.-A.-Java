package Train;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

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

    public DepotForm() {
        initialization();
        frame = new JFrame("Депо");
        frame.setSize(960, 740);
        frame.setState(JFrame.NORMAL);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(depotsGroupBox);
        frame.getContentPane().add(parkTrain);
        frame.getContentPane().add(groupBox);
        frame.getContentPane().add(drawDepot);
        frame.getContentPane().setBackground(Color.PINK);
        frame.repaint();
    }

    public void initialization() {
        listTransport = new LinkedList<>();
        depotList = new DefaultListModel<>();
        depotCollections = new DepotCollections(690,650);
        drawDepot = new DrawDepot(depotCollections);
        drawDepot.setBackground(Color.LIGHT_GRAY);
        borderTake = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Забрать поезд");
        borderDepot = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Депо");
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
        parkTrain.setBounds(730, 395, 190, 40);
        parkTrain.addActionListener(e -> createTrain());
        groupBox.setBounds(730, 440, 190, 120);
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
    }

    private void createTrain() {
        if (listBoxDepots.getSelectedIndex() >= 0) {
            TrainConfigForm trainConfigForm = new TrainConfigForm(frame);
            ITransport transport = trainConfigForm.getTransport();
            if (transport != null) {
                if (depotCollections.get(listBoxDepots.getSelectedValue()).add(transport) > -1) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Депо переполнено");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Депо не выбрано", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void PutTrainIntoList() {
        if (listBoxDepots.getSelectedIndex() >= 0) {
            if (!placeTransport.getText().equals("")) {
                try {
                    Train transport = (Train) depotCollections.get(listBoxDepots.getSelectedValue()).delete(Integer.parseInt(placeTransport.getText()));
                    if (transport != null) {
                        listTransport.add(transport);
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
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Депо не выбрано", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeItemList() {
        drawDepot.setSelectedItem(listBoxDepots.getSelectedValue());
        frame.repaint();
    }
}
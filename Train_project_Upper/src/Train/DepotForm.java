package Train;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

public class DepotForm {
    private JFrame frame;
    private JButton parkTrain;
    private JButton parkElectric_locomotive;
    private JButton getTrain;
    private JButton compareEquality;
    private JButton compareUnequality;
    private JTextField placeTransport;
    private JTextField newDepotWidth;
    private JTextField newDepotHeight;
    private Depot<ITransport, ICollectors> depot;
    private Depot<ITransport, ICollectors> depotCompare;
    private DrawDepot drawDepot;
    private JPanel groupBox;
    private JPanel equateGroupBox;
    private JLabel placeText;
    private JLabel depotWidthText;
    private JLabel depotHeightText;
    private Border borderTake;
    private Border borderCompare;

    public DepotForm() {
        initialization();
        frame = new JFrame("Депо");
        frame.setSize(960, 480);
        frame.setState(JFrame.NORMAL);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(parkTrain);
        frame.getContentPane().add(parkElectric_locomotive);
        frame.getContentPane().add(groupBox);
        frame.getContentPane().add(equateGroupBox);
        frame.getContentPane().add(drawDepot);
        frame.getContentPane().setBackground(Color.PINK);
        frame.repaint();
    }

    public void initialization() {
        depot = new Depot<ITransport, ICollectors>(700, 430);
        drawDepot = new DrawDepot(depot);
        drawDepot.setBackground(Color.LIGHT_GRAY);
        borderTake = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Забрать поезд");
        borderCompare = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Сравнить 2 депо");
        parkTrain = new JButton("Припарковать поезд");
        parkElectric_locomotive = new JButton("Припарковать электровоз");
        compareEquality = new JButton("==");
        compareUnequality = new JButton("!=");
        placeTransport = new JTextField();
        newDepotWidth = new JTextField();
        newDepotHeight = new JTextField();
        getTrain = new JButton("Забрать");
        placeText = new JLabel("Место: ");
        depotWidthText = new JLabel("Ширина депо: ");
        depotHeightText = new JLabel("Высота депо: ");
        drawDepot.setBounds(20, 20, 700, 400);

        groupBox = new JPanel();
        groupBox.setLayout(null);
        groupBox.setBackground(Color.PINK);
        groupBox.add(placeText);
        groupBox.add(placeTransport);
        groupBox.add(getTrain);
        parkTrain.setBounds(730, 20, 190, 30);
        parkTrain.addActionListener(e -> createTrain());
        parkElectric_locomotive.setBounds(730, 55, 190, 30);
        parkElectric_locomotive.addActionListener(e -> createElectric_locomotive());
        groupBox.setBounds(730, 90, 190, 90);
        placeText.setBounds(50, 20, 60, 30);
        placeTransport.setBounds(100, 20, 40, 25);
        getTrain.setBounds(50, 55, 90, 25);
        getTrain.addActionListener(e -> takeTrain());
        groupBox.setBorder(borderTake);

        equateGroupBox = new JPanel();
        equateGroupBox.setLayout(null);
        equateGroupBox.setBackground(Color.PINK);
        equateGroupBox.setBorder(borderCompare);
        equateGroupBox.add(compareEquality);
        equateGroupBox.add(compareUnequality);
        equateGroupBox.add(newDepotWidth);
        equateGroupBox.add(newDepotHeight);
        equateGroupBox.add(depotWidthText);
        equateGroupBox.add(depotHeightText);
        equateGroupBox.setBounds(730, 185, 190, 115);
        depotWidthText.setBounds(40, 20, 100, 30);
        newDepotWidth.setBounds(130, 20, 30, 25);
        newDepotHeight.setBounds(130, 50, 30, 25);
        depotHeightText.setBounds(40, 50, 100, 30);
        compareUnequality.setBounds(13, 80, 80, 25);
        compareUnequality.addActionListener(e -> compare(compareUnequality.getText()));
        compareEquality.setBounds(98, 80, 80, 25);
        compareEquality.addActionListener(e -> compare(compareEquality.getText()));
    }

    private void createTrain() {
        JColorChooser colorLowerDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorLowerDialog, "Выберите нижний цвет поезда", JOptionPane.PLAIN_MESSAGE);
        if (colorLowerDialog.getColor() != null) {
            JColorChooser colorUpperDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorUpperDialog, "Выберите верхний цвет поезда", JOptionPane.PLAIN_MESSAGE);
            if (colorUpperDialog.getColor() != null) {
                ITransport transport = new Train(100, 1000, colorUpperDialog.getColor(), colorLowerDialog.getColor());
                if (depot.add(transport) != -1) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Депо переполнено");
                }
            }
        }
    }

    private void createElectric_locomotive() {
        JColorChooser colorLowerDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorLowerDialog, "Выберите нижний цвет поезда", JOptionPane.PLAIN_MESSAGE);
        if (colorLowerDialog.getColor() != null) {
            JColorChooser colorUpperDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorUpperDialog, "Выберите верхний цвет поезда", JOptionPane.PLAIN_MESSAGE);
            if (colorUpperDialog.getColor() != null) {
                JColorChooser colorDopDialog = new JColorChooser();
                JOptionPane.showMessageDialog(frame, colorDopDialog, "Выберите дополнительный цвет поезда", JOptionPane.PLAIN_MESSAGE);
                if (colorDopDialog.getColor() != null) {
                    Random rnd = new Random();
                    ITransport transport = new Electric_locomotive(100, 1000, colorUpperDialog.getColor(), colorLowerDialog.getColor(),
                            colorDopDialog.getColor(), true, true, rnd.nextInt(3),rnd.nextInt(3) + 1);
                    if (depot.add(transport) != -1) {
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Депо переполнено");
                    }
                }
            }
        }
    }

    private void takeTrain() {
        if (!placeTransport.getText().equals("")) {
            try {
                ITransport transport = depot.delete(Integer.parseInt(placeTransport.getText()));
                if (transport != null) {
                    FormElTrain formElTrain = new FormElTrain();
                    formElTrain.setTrain(transport);
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Транспорта не существует");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Транспорта не существует");
            }
        }
    }

   private void compare(String comparison) {
        if (!newDepotWidth.getText().equals("") && !newDepotHeight.getText().equals("")) {
            try {
                int width = Integer.parseInt(newDepotWidth.getText());
                int height = Integer.parseInt(newDepotHeight.getText());
                depotCompare = new Depot<ITransport, ICollectors>(width, height);
            }  catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Проверьте введённые данные");
                return;
            }
            if (comparison.equals("==")) {
                if (depot.equal(depot, depotCompare)) {
                    JOptionPane.showMessageDialog(frame, "Проверяемое депо равно по кол-ву мест старому");
                } else {
                    JOptionPane.showMessageDialog(frame, "Проверяемое депо не равно по кол-ву мест старому");
                }
            }
            if (comparison.equals("!=")) {
                if (depot.unequal(depot, depotCompare)) {
                    JOptionPane.showMessageDialog(frame, "Проверяемое депо не равно по кол-ву мест старому");
                } else {
                    JOptionPane.showMessageDialog(frame, "Проверяемое депо равно по кол-ву мест старому");
                }
            }
        }
    }
}
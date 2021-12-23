package Train;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class TrainConfigForm extends JDialog {

    private ITransport transport;
    private Color transportColor;
    private ICollectors collectors;
    private DrawPicture drawPicture;

    public TrainConfigForm(Frame frame) {
        super(frame, true);
        setSize(810, 390);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#87CEFA"));

        //Выбор поезда
        JPanel typeOfTrainPanel = new JPanel();
        typeOfTrainPanel.setBackground(Color.decode("#87CEFA"));
        typeOfTrainPanel.setLayout(null);
        Border typeBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Тип поезда");
        typeOfTrainPanel.setBorder(typeBorder);
        typeOfTrainPanel.setBounds(10, 10, 150, 200);
        JLabel trainLabel = new JLabel("Поезд");
        trainLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        trainLabel.setBounds(15, 50, 120, 40);
        trainLabel.setOpaque(true);
        trainLabel.setBackground(Color.decode("#C0C0C0"));
        trainLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        trainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel electricLocomotiveLabel = new JLabel("Электровоз");
        electricLocomotiveLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        electricLocomotiveLabel.setBounds(15, 120, 120, 40);
        electricLocomotiveLabel.setOpaque(true);
        electricLocomotiveLabel.setBackground(Color.decode("#C0C0C0"));
        electricLocomotiveLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        electricLocomotiveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        typeOfTrainPanel.add(trainLabel);
        typeOfTrainPanel.add(electricLocomotiveLabel);
        add(typeOfTrainPanel);

        //Параметры поезда
        JPanel panelOptions = new JPanel();
        panelOptions.setLayout(null);
        typeBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Параметры");
        panelOptions.setBorder(typeBorder);
        panelOptions.setBackground(Color.decode("#87CEFA"));
        panelOptions.setBounds(10, 210, 450, 130);

        JLabel labelMaxSpeed = new JLabel("Макс скорость");
        labelMaxSpeed.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        labelMaxSpeed.setBounds(50, 25, 150, 15);

        JLabel labelWight = new JLabel("Вес поезда");
        labelWight.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        labelWight.setBounds(230, 25, 150, 15);

        JSpinner spinnerMaxSpeed = new JSpinner(new SpinnerNumberModel(100, 1, 1000, 1));
        spinnerMaxSpeed.setBounds(130, 45, 50, 25);
        JSpinner spinnerWeight = new JSpinner(new SpinnerNumberModel(1000, 1, 10000, 1));
        spinnerWeight.setBounds(310, 45, 50, 25);

        JCheckBox checkBoxCollector = new JCheckBox("Токоприёмник");
        checkBoxCollector.setBackground(Color.decode("#87CEFA"));
        checkBoxCollector.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        checkBoxCollector.setBounds(50, 90, 150, 20);

        JCheckBox checkBoxBattery = new JCheckBox("Аккумулятор");
        checkBoxBattery.setBackground(Color.decode("#87CEFA"));
        checkBoxBattery.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        checkBoxBattery.setBounds(230, 90, 150, 20);

        panelOptions.add(spinnerMaxSpeed);
        panelOptions.add(spinnerWeight);
        panelOptions.add(labelMaxSpeed);
        panelOptions.add(labelWight);
        panelOptions.add(checkBoxCollector);
        panelOptions.add(checkBoxBattery);
        add(panelOptions);

        //Панель отрисовки
        drawPicture = new DrawPicture();
        getContentPane().add(drawPicture);
        drawPicture.setBounds(165, 20, 291, 189);
        drawPicture.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        //Панель выбора цвета
        JPanel panelColors = new JPanel();
        panelColors.setLayout(null);
        typeBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Цвет");
        panelColors.setBackground(Color.decode("#87CEFA"));
        panelColors.setBorder(typeBorder);
        panelColors.setBounds(460, 10, 320, 170);

        JLabel labelLowerColor = new JLabel("Нижний цвет");
        labelLowerColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelLowerColor.setBounds(10, 25, 100, 30);
        labelLowerColor.setOpaque(true);
        labelLowerColor.setBackground(Color.decode("#C0C0C0"));
        labelLowerColor.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelUpperColor = new JLabel("Верхний цвет");
        labelUpperColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelUpperColor.setBounds(110, 25, 100, 30);
        labelUpperColor.setOpaque(true);
        labelUpperColor.setBackground(Color.decode("#C0C0C0"));
        labelUpperColor.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelDopColor = new JLabel("Доп. цвет");
        labelDopColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelDopColor.setBounds(210, 25, 100, 30);
        labelDopColor.setOpaque(true);
        labelDopColor.setBackground(Color.decode("#C0C0C0"));
        labelDopColor.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelPink = new JPanel();
        panelPink.setBackground(new Color(238, 5, 255));
        panelPink.setBounds(10, 60, 48, 48);

        JPanel panelRed = new JPanel();
        panelRed.setBackground(new Color(255, 0, 0));
        panelRed.setBounds(73, 60, 48, 48);

        JPanel panelOrange= new JPanel();
        panelOrange.setBackground(new Color(255, 102, 0));
        panelOrange.setBounds(136, 60, 48, 48);

        JPanel panelYellow = new JPanel();
        panelYellow.setBackground(new Color(255, 255, 0));
        panelYellow.setBounds(199, 60, 48, 48);

        JPanel panelBrightGreen = new JPanel();
        panelBrightGreen.setBackground(new Color(55, 255, 0));
        panelBrightGreen.setBounds(262, 60, 48, 48);

        JPanel panelGreen = new JPanel();
        panelGreen.setBackground(new Color(19, 133, 20));
        panelGreen.setBounds(10, 115, 48, 48);

        JPanel panelCyan = new JPanel();
        panelCyan.setBackground(new Color(0, 255, 255));
        panelCyan.setBounds(73, 115, 48, 48);

        JPanel panelLightBlue = new JPanel();
        panelLightBlue.setBackground(new Color(5, 155, 255));
        panelLightBlue.setBounds(136, 115, 48, 48);

        JPanel panelBlue= new JPanel();
        panelBlue.setBackground(new Color(0, 0, 255));
        panelBlue.setBounds(199, 115, 48, 48);

        JPanel panelViolet = new JPanel();
        panelViolet.setBackground(new Color(134, 5, 255));
        panelViolet.setBounds(262, 115, 48, 48);

        add(panelColors);
        panelColors.add(labelUpperColor);
        panelColors.add(labelLowerColor);
        panelColors.add(labelDopColor);
        panelColors.add(panelPink);
        panelColors.add(panelRed);
        panelColors.add(panelOrange);
        panelColors.add(panelYellow);
        panelColors.add(panelBrightGreen);
        panelColors.add(panelGreen);
        panelColors.add(panelCyan);
        panelColors.add(panelLightBlue);
        panelColors.add(panelBlue);
        panelColors.add(panelViolet);

        //Панель выбора дополнения
        JPanel panelCollectors = new JPanel();
        panelCollectors.setLayout(null);
        typeBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Токоприёмники");
        panelCollectors.setBackground(Color.decode("#87CEFA"));
        panelCollectors.setBorder(typeBorder);
        panelCollectors.setBounds(460, 180, 320, 125);

        PanelRhombus panelRhombus = new PanelRhombus();
        panelRhombus.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelRhombus.setBackground(Color.decode("#C0C0C0"));
        panelRhombus.setBounds(30, 30, 40, 40);

        PanelRound panelRound = new PanelRound();
        panelRound.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelRound.setBackground(Color.decode("#C0C0C0"));
        panelRound.setBounds(140, 30, 40, 40);

        PanelLine panelLine = new PanelLine();
        panelLine.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelLine.setBackground(Color.decode("#C0C0C0"));
        panelLine.setBounds(240, 30, 40, 40);

        JLabel labelTrackCount = new JLabel("Количество токоприёмников");
        JSpinner spinnerCollectorsCount = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
        labelTrackCount.setBounds(20, 88, 240, 15);
        labelTrackCount.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        spinnerCollectorsCount.setBounds(255, 85, 40, 25);

        panelCollectors.add(panelRhombus);
        panelCollectors.add(panelRound);
        panelCollectors.add(panelLine);
        panelCollectors.add(labelTrackCount);
        panelCollectors.add(spinnerCollectorsCount);
        add(panelCollectors);

        JButton buttonAdd = new JButton("Добавить");
        JButton buttonClear = new JButton("Отмена");
        add(buttonAdd);
        add(buttonClear);
        buttonAdd.setBounds(460, 310, 155, 30);
        buttonClear.setBounds(625, 310, 155, 30);
        buttonAdd.addActionListener(e -> dispose());
        buttonClear.addActionListener(e -> {
            drawPicture.setVehicle(null);
            dispose();
        });

        MouseAdapter listenerTransportType = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JLabel label = (JLabel) e.getSource();
                switch (label.getText()) {
                    case "Поезд" -> transport = new Train((int) spinnerMaxSpeed.getValue(), (int) spinnerWeight.getValue(), Color.WHITE, Color.LIGHT_GRAY);
                    case "Электровоз" -> transport = new Electric_locomotive((int) spinnerMaxSpeed.getValue(), (int) spinnerWeight.getValue(), Color.WHITE, Color.LIGHT_GRAY,
                            Color.GRAY, checkBoxCollector.isSelected(), checkBoxBattery.isSelected());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getX() + ((JComponent) e.getSource()).getX() + typeOfTrainPanel.getX() >= drawPicture.getX() &&
                        e.getX() + ((JComponent) e.getSource()).getX() + typeOfTrainPanel.getX() <= drawPicture.getX() + drawPicture.getWidth() &&
                        e.getY() + ((JComponent) e.getSource()).getY() + typeOfTrainPanel.getY() >= drawPicture.getY() &&
                        e.getY() + ((JComponent) e.getSource()).getY() + typeOfTrainPanel.getY() <= drawPicture.getY() + drawPicture.getHeight()) {
                    transport.SetPosition(40, 30, drawPicture.getWidth(), drawPicture.getHeight());
                    drawPicture.setVehicle(transport);
                    repaint();
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                transport = null;
            }
        };

        MouseAdapter listenerColor = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JPanel panelColor = (JPanel) e.getSource();
                transportColor = panelColor.getBackground();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (drawPicture.getVehicle() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() >= labelLowerColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelLowerColor.getX() + labelLowerColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelLowerColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelLowerColor.getY() + labelLowerColor.getHeight()) {
                        drawPicture.getVehicle().SetLowerColor(transportColor);
                        repaint();
                    }
                    if (e.getX() + ((JComponent) e.getSource()).getX() >= labelUpperColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelUpperColor.getX() + labelUpperColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelUpperColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelUpperColor.getY() + labelUpperColor.getHeight()) {
                        drawPicture.getVehicle().SetUpperColor(transportColor);
                        repaint();
                    } else if (e.getX() + ((JComponent) e.getSource()).getX() >= labelDopColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelDopColor.getX() + labelDopColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelDopColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelDopColor.getY() + labelDopColor.getHeight() &&
                            drawPicture.getVehicle() instanceof Electric_locomotive) {
                        Electric_locomotive electric_locomotive = (Electric_locomotive) drawPicture.getVehicle();
                        electric_locomotive.SetDopColor(transportColor);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                transportColor = null;
            }
        };

        MouseListener listenerColorLabel = new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel labelColor = (JLabel) e.getSource();
                switch (labelColor.getText()) {
                    case "Верхний цвет", "Нижний цвет" -> {
                        if (drawPicture.getVehicle() != null) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                    case "Доп. цвет" -> {
                        if (drawPicture.getVehicle() instanceof Electric_locomotive) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (transportColor != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        };

        MouseAdapter listenerCollectors = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JPanel panelCollectors = (JPanel) e.getSource();
                switch (panelCollectors.getClass().toString()) {
                    case "class Train.PanelRhombus" -> collectors = new CollectorRhombus(4 - (int) spinnerCollectorsCount.getValue());
                    case "class Train.PanelRound" -> collectors = new CollectorRound(4 - (int) spinnerCollectorsCount.getValue());
                    case "class Train.PanelLine" -> collectors = new CollectorLine(4 - (int) spinnerCollectorsCount.getValue());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (drawPicture.getVehicle() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() + panelCollectors.getX() >= drawPicture.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getAlignmentX() + panelCollectors.getX() <= drawPicture.getX() + drawPicture.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() + panelCollectors.getY() >= drawPicture.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() + panelCollectors.getY() <= drawPicture.getY() + drawPicture.getHeight() &&
                            drawPicture.getVehicle() instanceof Electric_locomotive) {
                        Electric_locomotive electric_locomotive = (Electric_locomotive) drawPicture.getVehicle();
                        electric_locomotive.setCollectors(collectors);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                collectors = null;
            }
        };

        MouseListener listenerPanelDrawTransport = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (transport != null || (drawPicture.getVehicle() instanceof Electric_locomotive && collectors != null)) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (transport != null || collectors != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        };

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                drawPicture.setVehicle(null);
            }
        });

        labelUpperColor.addMouseListener(listenerColorLabel);
        labelLowerColor.addMouseListener(listenerColorLabel);
        labelDopColor.addMouseListener(listenerColorLabel);
        addMouseListener(listenerPanelDrawTransport);
        trainLabel.addMouseListener(listenerTransportType);
        electricLocomotiveLabel.addMouseListener(listenerTransportType);
        panelRound.addMouseListener(listenerCollectors);
        panelRhombus.addMouseListener(listenerCollectors);
        panelLine.addMouseListener(listenerCollectors);

        panelPink.addMouseListener(listenerColor);
        panelRed.addMouseListener(listenerColor);
        panelOrange.addMouseListener(listenerColor);
        panelYellow.addMouseListener(listenerColor);
        panelBrightGreen.addMouseListener(listenerColor);
        panelGreen.addMouseListener(listenerColor);
        panelCyan.addMouseListener(listenerColor);
        panelLightBlue.addMouseListener(listenerColor);
        panelBlue.addMouseListener(listenerColor);
        panelViolet.addMouseListener(listenerColor);

        setVisible(true);

    }
    public ITransport getTransport() {
        return drawPicture.getVehicle();
    }
}

class PanelRhombus extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolygon(new int[] {20, 35, 20, 5}, new int[] {5,  20, 35, 20}, 4);
        super.repaint();
    }
}

class PanelRound extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(5,5,30,30);
        super.repaint();
    }
}

class PanelLine extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(5,35, 35,5);
        super.repaint();
    }
}

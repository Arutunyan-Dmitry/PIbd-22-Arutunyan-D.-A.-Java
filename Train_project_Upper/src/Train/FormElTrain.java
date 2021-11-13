package Train;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FormElTrain extends JFrame{

    private JButton CreateTrainButton;
    private JButton buttonUp;
    private JButton buttonLeft;
    private JButton buttonDown;
    private JButton buttonRight;

    private JPanel MainPanel;
    private JPanel ButtonPanel;
    private JPanel UpperPanel;
    private JPanel BottomPanel;
    private JPanel Spacer;
    private JButton CreateElectricTrainButton;

    private ITransport train;
    private DrawPicture draw;

    public FormElTrain() {
        JFrame frame = new JFrame("Electric locomotive");
        frame.setSize(new Dimension(1000, 700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UpperPanel.setBackground(Color.GRAY);
        BottomPanel.setBackground(Color.GRAY);
        ButtonPanel.setBackground(Color.GRAY);
        Spacer.setBackground(Color.GRAY);

        draw = new DrawPicture();

        // Отработка нажатия кнопки создания электровоза
        CreateTrainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rnd = new Random();
                frame.remove(draw);
                train = new Train(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.decode("#F4A460"), Color.decode("#FFFF00"));
                train.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10, MainPanel.getWidth(), MainPanel.getHeight()  - BottomPanel.getHeight() - UpperPanel.getHeight());
                draw.setVehicle(train);
                frame.add(draw);
                MainPanel.add(draw);
                frame.repaint();
                frame.setVisible(true);
            }
        });

        CreateElectricTrainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rnd = new Random();
                frame.remove(draw);
                train = new Electric_locomotive(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.decode("#F4A460"), Color.decode("#FFFF00"), Color.decode("#FF1493"), true, true, rnd.nextInt(3),rnd.nextInt(3) + 1);
                train.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10, MainPanel.getWidth(), MainPanel.getHeight()  - BottomPanel.getHeight() - UpperPanel.getHeight());
                draw.setVehicle(train);
                frame.add(draw);
                MainPanel.add(draw);
                frame.repaint();
                frame.setVisible(true);
            }
        });

        // Отработка нажатия кнопок перемещения электровоза
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object name = e.getSource();
                if(name == buttonLeft) train.MoveTransport(Direction.Left);
                if(name == buttonRight) train.MoveTransport(Direction.Right);
                if(name == buttonUp) train.MoveTransport(Direction.Up);
                if(name == buttonDown) train.MoveTransport(Direction.Down);
            }
        };
        buttonLeft.addActionListener(listener);
        buttonDown.addActionListener(listener);
        buttonRight.addActionListener(listener);
        buttonUp.addActionListener(listener);

        frame.add(MainPanel);

        frame.setLocationRelativeTo(null);
        frame.setState(JFrame.NORMAL);
        frame.setVisible(true);
    }
}

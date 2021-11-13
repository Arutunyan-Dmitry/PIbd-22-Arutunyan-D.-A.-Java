package Trains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FormElTrain {

    private JButton CreateButton;
    private JButton buttonUp;
    private JButton buttonLeft;
    private JButton buttonDown;
    private JButton buttonRight;

    private Electric_locomotive electric_locomotive;
    private DrawPicture draw;

    private JPanel MainPanel;
    private JPanel ButtonPanel;
    private JPanel UpperPanel;
    private JPanel BottomPanel;
    private JPanel Spacer;

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
        CreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rnd = new Random();
                frame.remove(draw);
                electric_locomotive = new Electric_locomotive();
                electric_locomotive.Init(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.decode("#F4A460"), Color.decode("#FFFF00"), Color.decode("#FF1493"), true, true, rnd.nextInt(3) + 1);
                electric_locomotive.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10, MainPanel.getWidth(), MainPanel.getHeight()  - BottomPanel.getHeight());
                draw.setVehicle(electric_locomotive);
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
                if(name == buttonLeft) electric_locomotive.MoveTransport(Direction.Left);
                if(name == buttonRight) electric_locomotive.MoveTransport(Direction.Right);
                if(name == buttonUp) electric_locomotive.MoveTransport(Direction.Up);
                if(name == buttonDown) electric_locomotive.MoveTransport(Direction.Down);
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

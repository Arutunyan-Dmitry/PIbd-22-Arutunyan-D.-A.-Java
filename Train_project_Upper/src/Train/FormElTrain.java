package Train;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FormElTrain extends JFrame{

    private JButton buttonUp;
    private JButton buttonLeft;
    private JButton buttonDown;
    private JButton buttonRight;

    private JPanel MainPanel;
    private JPanel ButtonPanel;
    private JPanel BottomPanel;
    private JPanel Spacer;

    private ITransport train;
    private DrawPicture draw;

    public FormElTrain() {
        JFrame frame = new JFrame("Electric locomotive");
        frame.setSize(new Dimension(750, 820));
        frame.setResizable(false);

        BottomPanel.setBackground(Color.GRAY);
        ButtonPanel.setBackground(Color.GRAY);
        Spacer.setBackground(Color.GRAY);

        draw = new DrawPicture();

        frame.remove(draw);
        MainPanel.add(draw);
        frame.repaint();
        frame.setVisible(true);

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

    public void setTrain(ITransport train){
        this.train = train;
        draw.setVehicle(train);
    }
}

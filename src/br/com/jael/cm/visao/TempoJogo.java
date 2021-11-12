package br.com.jael.cm.visao;

import static javax.swing.SwingConstants.RIGHT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.*;

class TempoJogo extends JPanel {

    private static final int N = 60;
    private final ClockListener cl = new ClockListener();
    private final Timer t = new Timer(1000, cl);
    private final JTextField tf = new JTextField(8);

    public TempoJogo() {

        setSize(1280, 50);
        setLayout(new BorderLayout());
        t.setInitialDelay(0);
        tf.setEnabled(true);
        tf.setHorizontalAlignment(RIGHT);
        tf.setEditable(false);
        t.start();
    }

    private class ClockListener implements ActionListener {

        private int hours;
        private int minutes;
        private int seconds;
        private String hour;
        private String minute;
        private String second;

        @Override
        public void actionPerformed(ActionEvent e) {
            NumberFormat formatter = new DecimalFormat("00");
            if (seconds == N) {
                seconds = 00;
                minutes++;
            }

            if (minutes == N) {
                minutes = 00;
                hours++;
            }
            hour = formatter.format(hours);
            minute = formatter.format(minutes);
            second = formatter.format(seconds);
            tf.setText(String.valueOf(hour + ":" + minute + ":" + second));
            seconds++;
        }
    }
}
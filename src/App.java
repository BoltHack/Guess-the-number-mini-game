import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class App extends JFrame {

    private JTextField inputField;
    private JLabel messageLabel;
    private JButton guessButton;
    private JButton oneMoreTimeButton;
    private int secretNumber;
    private final byte min = 1;
    private final byte max = 10;

    public App() {
        URL iconURL = getClass().getResource("/icons/Icon_MD_Cyn.png");
        if (iconURL == null) {
            System.out.println("Icon not found!");
        } else {
            ImageIcon icon = new ImageIcon(iconURL);
            setIconImage(icon.getImage());
        }

        setLayout(new BorderLayout());
        setSize(400, 200);
        getContentPane().setBackground(new Color(0x16171d));
        setLocationRelativeTo(null);
        setTitle("Угадай число");

        messageLabel = new JLabel("Какое число я загадал?");
        messageLabel.setForeground(new Color(0xffffff));
        messageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        inputField = new JTextField();
        inputField.setText("Введите текст");
        inputField.setBackground(new Color(0x242632));
        inputField.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputField.setHorizontalAlignment(SwingConstants.CENTER);
        inputField.setForeground(new Color(0xffffff));
        inputField.setCaretColor(new Color(0xffffff));

        guessButton = new JButton("Угадать");
        guessButton.setContentAreaFilled(false);
        guessButton.setFocusPainted(false);
        guessButton.setBackground(new Color(0x16171d));
        guessButton.setForeground(new Color(0xffffff));
        guessButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        oneMoreTimeButton = new JButton("Попробовать ещё раз");
        oneMoreTimeButton.setContentAreaFilled(false);
        oneMoreTimeButton.setFocusPainted(false);
        oneMoreTimeButton.setBackground(new Color(0x16171d));
        oneMoreTimeButton.setForeground(new Color(0xffffff));
        oneMoreTimeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(messageLabel, BorderLayout.NORTH);
        add(inputField, BorderLayout.CENTER);

        guessButton.addActionListener(e -> checkGuess());
        oneMoreTimeButton.addActionListener(e -> Play());

        Play();

        setVisible(true);
    }

    private void Play() {
        secretNumber = min + (int)(Math.random() * (max - min + 1));
        messageLabel.setText("Какое число от " + min + " до " + max + " я загадал?");

        add(guessButton, BorderLayout.SOUTH);
        guessButton.setVisible(true);
        oneMoreTimeButton.setVisible(false);

        guessButton.setEnabled(true);
        inputField.setText("");
        inputField.setEnabled(true);
        inputField.grabFocus();
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(inputField.getText());

            if (secretNumber == guess) {
                messageLabel.setText("<html><div style='max-width: 100%; width: 100%; text-align: center;'>Бинго!<br>" +
                        "Число <b>" + guess + "</b> — это именно то, что я загадал!<br>Можешь взять пару печенек со стола!</div></html>");

                guessButton.setVisible(false);
                oneMoreTimeButton.setVisible(true);
                add(oneMoreTimeButton, BorderLayout.SOUTH);

                inputField.setEnabled(false);
            } else {
                guessButton.setVisible(false);
                oneMoreTimeButton.setVisible(true);
                add(oneMoreTimeButton, BorderLayout.SOUTH);

                inputField.setEnabled(false);
                messageLabel.setText("<html><div style='max-width: 100%; width: 100%; text-align: center;'>Не угадал :)<br>" +
                        "Ты назвал <b>" + guess + "</b>, а я загадывал <b>" + secretNumber + "</b>.<br>" +
                        "Может попробуешь ещё разок? Авось повезёт.</div></html>");
            }
            inputField.setText("");
        } catch (Exception e) {
            messageLabel.setText("<html><div style='max-width: 100%; width: 100%; text-align: center;'>Окак<br>" +
                    "Похоже, это не число.<br>" +
                    "Попробуй ввести что-то от " + min + " до " + max + "!</div></html>");
        }
    }

}
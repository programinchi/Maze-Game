import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ButtonPanel extends JPanel{
    
    private JLabel gambarLabel;
    private ImageIcon[] gambarIkon;
    private int indeksGambar = 0;
    private Timer timer;
    private JFrame frame;

    public ButtonPanel(JFrame frame, String[] jalurGambar) {
        this.frame = frame;
        setLayout(null);
        setOpaque(false);
        setBackground(new Color(0,0,0,0));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setBounds(0,0,0,0);
        panel.setBackground(new Color(0,0,0,0));

        gambarIkon = new ImageIcon[jalurGambar.length];

        for (int i = 0; i < jalurGambar.length; i++) {
            try {
                BufferedImage img = ImageIO.read(new File(jalurGambar[i]));
                gambarIkon[i] = new ImageIcon(img);
            } catch (IOException e) {
                e.printStackTrace();
                gambarIkon[i] = null;
            }

            JButton button = new JButton(gambarIkon[i]);
            button.setBounds(0, i * 70, 250, 70); // Pastikan tombol memiliki ukuran
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            panel.add(button);

            // if (i==2){
            //     button.addActionListener(e -> {
            //         SwingUtilities.invokeLater(() -> new StoryLine());
            //     });
            // }
        }
        add(panel);

        gambarLabel = new JLabel(gambarIkon[0]);
        gambarLabel.setBounds(50, 150, gambarIkon[0].getIconWidth(), gambarIkon[0].getIconHeight());
        add(gambarLabel);

        timer = new Timer(250, e -> {
            indeksGambar = (indeksGambar + 1) % gambarIkon.length;
            gambarLabel.setIcon(gambarIkon[indeksGambar]);

            gambarLabel.setBounds(
                gambarLabel.getX(), gambarLabel.getY(), 
                gambarIkon[indeksGambar].getIconWidth(), 
                gambarIkon[indeksGambar].getIconHeight()
            );
        });
        timer.start();

        gambarLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                SwingUtilities.invokeLater(() -> new Story());
            }
        });
    }
    

    
}
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GameMenu extends JFrame {
    private ImageIcon animatedGif; // GIF untuk background
    private JLabel gifLabel;

    // Koordinat tombol Play dalam GIF (ubah sesuai posisi tombol Play dalam gambarmu)
    private final int playButtonX = 450;  // Posisi X tombol
    private final int playButtonY = 300;  // Posisi Y tombol
    private final int playButtonWidth = 140;  // Lebar tombol
    private final int playButtonHeight = 140; // Tinggi tombol

    public GameMenu() {
        setTitle("Game Menu");
        setSize(1024, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        animatedGif = new ImageIcon("/Users/dheavandawijaya/Downloads/Tick..Tick.. BOOM!.gif"); 
        gifLabel = new JLabel(animatedGif);
        gifLabel.setLayout(null);
        add(gifLabel, BorderLayout.CENTER);

        gifLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                
                if (x >= playButtonX && x <= playButtonX + playButtonWidth &&
                    y >= playButtonY && y <= playButtonY + playButtonHeight) {
                    
                    startGame();
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startGame() {
        dispose(); // Tutup menu utama
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Maze Game");
            MazeGame game = new MazeGame();
            frame.add(game);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setResizable(false);
            game.requestFocusInWindow();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameMenu::new);
    }
}

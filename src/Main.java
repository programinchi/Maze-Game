import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tick..Tick..BOOM!!");
            frame.setSize(1280, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLayout(null);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, 0, 1280, 800);

            ImageIcon background = new ImageIcon("C:\\Users\\Jennifer\\Downloads\\Maze-Game\\Gambar\\Main Menu\\bg.jpg");
            JLabel label = new JLabel(background);
            label.setBounds(0, 0, 1280, 800);
            layeredPane.add(label, Integer.valueOf(0)); // Layer paling bawah
            

            String[] jalurGambar = {
                "C:\\Users\\Jennifer\\Downloads\\Maze-Game\\Gambar\\Main Menu\\small button.png",
                "C:\\Users\\Jennifer\\Downloads\\Maze-Game\\Gambar\\Main Menu\\medium button.png",
                "C:\\Users\\Jennifer\\Downloads\\Maze-Game\\Gambar\\Main Menu\\normal button.png",
                "C:\\Users\\Jennifer\\Downloads\\Maze-Game\\Gambar\\Main Menu\\medium button.png",
            };

            ButtonPanel buttonPanel = new ButtonPanel(frame, jalurGambar);
            buttonPanel.setBounds(490, 350, 400, 400);

            layeredPane.add(buttonPanel, Integer.valueOf(1)); // Layer lebih atas dari background

            frame.setContentPane(layeredPane);
            frame.setVisible(true);

        });
    }
}

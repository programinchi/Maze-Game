import javax.swing.*;

public class Menu {
    private JLabel gambarLabel;
    private ImageIcon[] gambarIkon;
    private int indeksGambar = 0;
    private final Timer timer;

    public Menu(String[] jalurGambar) {
        // Membuat frame
        JFrame frame = new JFrame("Tick..Tick..BOOM!!");
        frame.setSize(1280, 800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Supaya bisa atur posisi manual

        // Menggunakan JLayeredPane agar tombol ada di depan
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1280, 800);
        //layeredPane.add(labelImage, JLayeredPane.DEFAULT_LAYER);

        // Load gambar animasi tombol
        gambarIkon = new ImageIcon[jalurGambar.length];
        for (int i = 0; i < jalurGambar.length; i++) {
            gambarIkon[i] = new ImageIcon(jalurGambar[i]);
        }

        // Label untuk tombol play
        gambarLabel = new JLabel(gambarIkon[0]);
        gambarLabel.setBounds(550, 300, gambarIkon[0].getIconWidth(), gambarIkon[0].getIconHeight());
        gambarLabel.setOpaque(true);
        layeredPane.add(gambarLabel, JLayeredPane.PALETTE_LAYER); // PALETTE_LAYER agar ada di atas background

        // Animasi tombol (kedip)
        timer = new Timer(250, e -> {
            indeksGambar = (indeksGambar + 1) % gambarIkon.length;
            gambarLabel.setIcon(gambarIkon[indeksGambar]);
        });
        timer.start();

        // Menambahkan event klik tombol play
        gambarLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startGame();
            }
        });

        // Menambahkan layeredPane ke frame
        frame.setContentPane(layeredPane);
        frame.setVisible(true);
    }

    private void startGame() {
        JOptionPane.showMessageDialog(null, "Game dimulai!");
    }
}
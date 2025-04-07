import javax.swing.*;

public class StoryLine extends BaseFrame{
    private ImageIcon polisi;
    private JLabel tombolNext;

    //ukuran dan posisi next button
    // private final int nextButtonX = 1097;  // Posisi X tombol
    // private final int nextButtonY = 611;  // Posisi Y tombol
    // private final int nextWidth = 136;  // Lebar tombol
    // private final int nextHeight = 150; // Tinggi tombol

    public StoryLine(){
    polisi = new ImageIcon("C:\\Users\\Jennifer\\Downloads\\Maze-Game\\Gambar\\Story\\polisi.jpg");
    JLabel gambarPolisi = new JLabel(polisi);
    gambarPolisi.setBounds(0,0,1280,800);
    add(gambarPolisi);
    
    // tombolNext = new JLabel();
    // tombolNext.setBounds(nextButtonX, nextButtonY, nextWidth, nextHeight);
    // tombolNext.addMouseListener(new MouseAdapter(){
    //     @Override
    //     public void mouseClicked(MouseEvent e) {
    //         int x = e.getX();
    //         int y = e.getY();

    //         if(x >= nextButtonX && x <= nextButtonX + nextWidth && y >= nextButtonY && y <= nextButtonY + nextHeight){
    //             StoryLine.this.dispose(); //tutup story
    //             SwingUtilities.invokeLater(() -> new MazeGame());
    //         }
    //     }
    // });

    // add(tombolNext);
    // setVisible(true);
    }

    // public void startGame() {
        // Window window = SwingUtilities.getWindowAncestor(this);
        // if (window instanceof JFrame) {
        //     ((JFrame) window).dispose();
        // }
    //         SwingUtilities.invokeLater(() -> {
    //         JFrame frame = new JFrame("Tick..Tick..BOOM!!");
    //         MazeGame game = new MazeGame();
    //         frame.add(game);
    //         frame.pack();
    //         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //         frame.setVisible(true);
    //         frame.setResizable(false);
    //         game.requestFocusInWindow();
    //     });
    // }
}

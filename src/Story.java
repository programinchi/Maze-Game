import java.awt.event.*;
import javax.swing.*;

public class Story extends BaseFrame{
    private final ImageIcon story1 = new ImageIcon("C:\\Users\\Jennifer\\Downloads\\Maze-Game\\Gambar\\Story\\polisi.jpg");

    // ukuran dan posisi next button
    private final int nextButtonX = 1083;  // Posisi X tombol
    private final int nextButtonY = 574; // Posisi Y tombol
    private final int nextWidth = 136;  // Lebar tombol
    private final int nextHeight = 150; // Tinggi tombol

    public Story(){
        JLabel polisiStory1 = new JLabel(story1);
        polisiStory1.setBounds(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        getContentPane().add(polisiStory1);

        //tombol next
        polisiStory1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if(x >= nextButtonX && x <= nextButtonX + nextWidth && y >= nextButtonY && y <= nextButtonY + nextHeight){
                    SwingUtilities.invokeLater(() -> {
                        JFrame mazeFrame = new JFrame();
                        MazeGame game = new MazeGame();
                        mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mazeFrame.setContentPane(game);
                        mazeFrame.pack();
                        mazeFrame.setLocationRelativeTo(null);
                        mazeFrame.setVisible(true);
                    }); 
                    
                    Story.this.dispose();  // Hapus frame story
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);        
    }
}

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.*;

public class MazeGame extends JPanel implements KeyListener {
    private static final int TILE_SIZE = 32;
    private static final int WIDTH = 40;
    private static final int HEIGHT = 25;
    
    private int playerX = 1, playerY = 1;
    private int exitX, exitY;
    private int[][] maze;
    private boolean gameOver = false;
    
    private int suitcase1X, suitcase1Y;
    private int suitcase2X, suitcase2Y;
    private boolean suitcase1Opened = false;
    private boolean suitcase2Opened = false;
    
    private String suitcase1Content;
    private String suitcase2Content;
    
    public MazeGame() {
        setPreferredSize(new Dimension(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE));
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        
        generateMaze();
        placeSuitcases();
    }

    private void generateMaze() {
        maze = new int[HEIGHT][WIDTH];
        Random rand = new Random();
        
        // Generate walls and paths randomly
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                maze[y][x] = (rand.nextInt(4) == 0) ? 1 : 0;
            }
        }

        // Ensure start and exit are open
        maze[1][1] = 0;
        exitX = WIDTH - 2;
        exitY = HEIGHT - 2;
        maze[exitY][exitX] = 0;

        System.out.println("Maze generated!");
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                System.out.print(maze[y][x] + " ");
            }
            System.out.println();
        }
    }

    private void placeSuitcases() {
        Random rand = new Random();
        do {
            suitcase1X = rand.nextInt(WIDTH - 2) + 1;
            suitcase1Y = rand.nextInt(HEIGHT - 2) + 1;
        } while (maze[suitcase1Y][suitcase1X] == 1 || (suitcase1X == 1 && suitcase1Y == 1));

        do {
            suitcase2X = rand.nextInt(WIDTH - 2) + 1;
            suitcase2Y = rand.nextInt(HEIGHT - 2) + 1;
        } while ((suitcase2X == suitcase1X && suitcase2Y == suitcase1Y) || maze[suitcase2Y][suitcase2X] == 1);

        // Randomly assign contents
        if (rand.nextBoolean()) {
            suitcase1Content = "2 stones";
            suitcase2Content = "a bomb";
        } else {
            suitcase1Content = "a bomb";
            suitcase2Content = "2 stones";
        }

        System.out.println("Suitcase 1 at: " + suitcase1X + ", " + suitcase1Y + " - Contains: " + suitcase1Content);
        System.out.println("Suitcase 2 at: " + suitcase2X + ", " + suitcase2Y + " - Contains: " + suitcase2Content);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw maze
        g.setColor(Color.GRAY);
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (maze[y][x] == 1) {
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        // Draw exit
        g.setColor(Color.RED);
        g.fillRect(exitX * TILE_SIZE, exitY * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Draw suitcases
        g.setColor(Color.GREEN);
        if (!suitcase1Opened) g.fillRect(suitcase1X * TILE_SIZE, suitcase1Y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        if (!suitcase2Opened) g.fillRect(suitcase2X * TILE_SIZE, suitcase2Y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Draw player
        g.setColor(Color.BLUE);
        g.fillOval(playerX * TILE_SIZE, playerY * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Apply lighting effect
        applyLightingEffect(g);
    }

    private void applyLightingEffect(Graphics g) {
        BufferedImage darkness = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = darkness.createGraphics();

        // Dark overlay
        g2d.setColor(new Color(0, 0, 0, 250));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Light effect around player
        // Pusatkan efek cahaya di tengah pemain
        int centerX = playerX * TILE_SIZE + TILE_SIZE / 2;
        int centerY = playerY * TILE_SIZE + TILE_SIZE / 2;
        int radius = 100; // Ukuran radius cahaya

        // Light effect around player dengan opacity lebih rendah
        RadialGradientPaint lightEffect = new RadialGradientPaint(
            new Point(centerX, centerY),
            radius,
            new float[]{0f, 0.7f, 1f},
            new Color[]{new Color(255, 255, 200, 60), new Color(255, 255, 100, 60), new Color(0, 0, 0, 0)}
        );
        g2d.setPaint(lightEffect);
        g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // Clear center light circle agar penglihatan lebih jelas
        g2d.setComposite(AlphaComposite.Clear);
        int clearRadius = 15;
        g2d.fillOval(centerX - clearRadius, centerY - clearRadius, clearRadius * 2, clearRadius * 2);

        g2d.dispose();
        g.drawImage(darkness, 0, 0, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver) return;

        int newX = playerX, newY = playerY;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: newY--; break;
            case KeyEvent.VK_DOWN: newY++; break;
            case KeyEvent.VK_LEFT: newX--; break;
            case KeyEvent.VK_RIGHT: newX++; break;
        }

        // Ensure player doesn't walk through walls
        if (maze[newY][newX] == 0) {
            playerX = newX;
            playerY = newY;
            System.out.println("Player moved to: " + playerX + ", " + playerY);
            repaint();
        }

        checkSuitcase();
    }

    private void checkSuitcase() {
        if (playerX == suitcase1X && playerY == suitcase1Y && !suitcase1Opened) {
            suitcase1Opened = true;
            JOptionPane.showMessageDialog(this, "You found a suitcase! It contains: " + suitcase1Content);
            if (suitcase1Content.equals("a bomb")) endGame();
        } else if (playerX == suitcase2X && playerY == suitcase2Y && !suitcase2Opened) {
            suitcase2Opened = true;
            JOptionPane.showMessageDialog(this, "You found a suitcase! It contains: " + suitcase2Content);
            if (suitcase2Content.equals("a bomb")) endGame();
        }

        if (playerX == exitX && playerY == exitY) {
            JOptionPane.showMessageDialog(this, "You Win!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    private void endGame() {
        gameOver = true;
        JOptionPane.showMessageDialog(this, "BOOM! You lost!", "Game Over", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Maze Game");
            MazeGame game = new MazeGame();
            frame.add(game);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            game.requestFocusInWindow();
        });
    }
}

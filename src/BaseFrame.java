import javax.swing.JFrame;

public class BaseFrame extends JFrame {
    protected static final int DEFAULT_WIDTH = 1280;
    protected static final int DEFAULT_HEIGHT = 800;
    protected static final String DEFAULT_TITLE = "Tick..Tick..BOOM!!";

    public BaseFrame() {
        setTitle(DEFAULT_TITLE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null); // Make the frame layout null to allow manual positioning
    }
}

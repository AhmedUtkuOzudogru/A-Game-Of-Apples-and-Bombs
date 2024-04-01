import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu extends JFrame {
    protected String name;
    protected int speed;
    protected JButton button;
    protected JLabel nameJLabel;
    protected JLabel speedJLabel;
    protected JTextField nameJTextField;
    protected JTextField speedJTextField;

    public menu() {
        createComponents();
        setTitle("menu");
        this.setLocationRelativeTo (null);
        ImageIcon menuIcon= new ImageIcon("images.png");
        setIconImage(menuIcon.getImage());
        setSize(400, 300);
        setLayout(new GridLayout(3, 1));
    }
    private void createComponents() {

        this.button = new JButton("Start");
        this.nameJLabel = new JLabel("Name");
        this.speedJLabel = new JLabel("Speed");
        nameJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        speedJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel nameJPanel = new JPanel(new GridLayout(1, 2));
        JPanel speedJPanel = new JPanel(new GridLayout(1, 2));
        nameJTextField = new JTextField();
        speedJTextField = new JTextField();
        nameJPanel.add(nameJLabel);
        nameJPanel.add(nameJTextField);
        speedJPanel.add(speedJLabel);
        speedJPanel.add(speedJTextField);
        add(nameJPanel);
        add(speedJPanel);
        add(button);
        button.addActionListener(new StartListener());
    }

    /**
     * Innerclass that implements ActionListener Interface
     */
    public class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (nameJTextField.getText().trim().isEmpty() || speedJTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(menu.this, "Please fill in all fields.");
                return;
            }
            String name = nameJTextField.getText().trim();
            try {
                int speed = Integer.parseInt(speedJTextField.getText().trim());
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(menu.this, "Name cannot be empty!");
                } else if (speed < 0 || speed > 20) {
                    JOptionPane.showMessageDialog(menu.this, "Speed has to be an integer between 0 and 10");
                } else {
                    dispose(); // Close the frame
                    new GameFrame(name, speed).setVisible(true);
                    
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(menu.this, "Speed Has to be integer ");
            }
        }

    }

}

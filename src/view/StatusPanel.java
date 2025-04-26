package view;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private final JLabel statusLabel;
    
    public StatusPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(new Color(240, 240, 240));
        
        statusLabel = new JLabel(" ");
        add(statusLabel);
        setBorder();
    }
    
    public void showSuccessMessage(String message) {
        statusLabel.setText(message);
        statusLabel.setForeground(new Color(0, 150, 0));
    }
    
    public void showErrorMessage(String message) {
        statusLabel.setText(message);
        statusLabel.setForeground(new Color(200, 0, 0));
    }

    public void setBorder() {
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
    }
}

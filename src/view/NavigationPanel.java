package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class NavigationPanel extends JPanel {
    private final JButton clientsButton;
    private final JButton banksButton;
    private final JButton saveButton;
    
    public NavigationPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(51, 102, 204));
        setPreferredSize(new Dimension(180, 0));
        setBorder(new EmptyBorder(20, 10, 20, 10));
        
        // Создание кнопок навигации
        clientsButton = createNavButton("Список Клиентов");
        banksButton = createNavButton("Список Банков");
        saveButton = createNavButton("Сохранить данные");
        
        // Добавление кнопок на панель с промежутками
        add(clientsButton);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(banksButton);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(saveButton);
        add(Box.createVerticalGlue());
    }
    
    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(160, 40));
        button.setBackground(new Color(240, 240, 240));
        button.setForeground(new Color(0, 51, 153));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return button;
    }
    
    public void addClientsButtonListener(ActionListener listener) {
        clientsButton.addActionListener(listener);
    }
    
    public void addBanksButtonListener(ActionListener listener) {
        banksButton.addActionListener(listener);
    }
    
    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }
}

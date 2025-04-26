package view;

import model.Client;
import service.PaymentMethod.PaymentMethod;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionPanel extends JPanel {
    private final JComboBox<Client> senderComboBox;
    private final JComboBox<Client> receiverComboBox;
    private final JTextField amountField;
    private final JComboBox<PaymentMethod> methodComboBox;
    private final JButton transferButton;

    // Карты для сохранения выбранных клиентов по ИНН
    private final Map<String, Client> clientsMap = new HashMap<>();
    private String lastSelectedSenderInn = null;
    private String lastSelectedReceiverInn = null;

    public TransactionPanel(List<Client> clients, List<PaymentMethod> paymentMethods) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(220, 220, 220)),
                new EmptyBorder(20, 15, 20, 15)
        ));
        setPreferredSize(new Dimension(250, 0));

        // Заполняем карту клиентов
        for (Client client : clients) {
            clientsMap.put(client.getInn(), client);
        }

        // Заголовок панели
        JLabel titleLabel = new JLabel("Перевод средств");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(51, 102, 204));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));

        // Компоненты для отправителя
        JLabel senderLabel = new JLabel("Отправитель:");
        senderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(senderLabel);
        add(Box.createRigidArea(new Dimension(0, 5)));

        senderComboBox = new JComboBox<>();
        for (Client client : clients) {
            senderComboBox.addItem(client);
        }
        senderComboBox.setRenderer(new ClientListCellRenderer());
        senderComboBox.setMaximumSize(new Dimension(230, 30));
        senderComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        senderComboBox.addActionListener(e -> {
            Client selectedClient = (Client) senderComboBox.getSelectedItem();
            if (selectedClient != null) {
                lastSelectedSenderInn = selectedClient.getInn();
            }
        });
        add(senderComboBox);
        add(Box.createRigidArea(new Dimension(0, 15)));

        // Компоненты для получателя
        JLabel receiverLabel = new JLabel("Получатель:");
        receiverLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(receiverLabel);
        add(Box.createRigidArea(new Dimension(0, 5)));

        receiverComboBox = new JComboBox<>();
        for (Client client : clients) {
            receiverComboBox.addItem(client);
        }
        receiverComboBox.setRenderer(new ClientListCellRenderer());
        receiverComboBox.setMaximumSize(new Dimension(230, 30));
        receiverComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        receiverComboBox.addActionListener(e -> {
            Client selectedClient = (Client) receiverComboBox.getSelectedItem();
            if (selectedClient != null) {
                lastSelectedReceiverInn = selectedClient.getInn();
            }
        });

        // Выбираем разных клиентов по умолчанию, если возможно
        if (receiverComboBox.getItemCount() > 1) {
            receiverComboBox.setSelectedIndex(1);
        }

        add(receiverComboBox);
        add(Box.createRigidArea(new Dimension(0, 15)));

        // Компоненты для суммы
        JLabel amountLabel = new JLabel("Сумма перевода:");
        amountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(amountLabel);
        add(Box.createRigidArea(new Dimension(0, 5)));

        amountField = new JTextField();
        amountField.setMaximumSize(new Dimension(230, 30));
        amountField.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(amountField);
        add(Box.createRigidArea(new Dimension(0, 25)));

        // Компоненты для способа оплаты
        JLabel methodLabel = new JLabel("Способ оплаты:");
        methodLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(methodLabel);
        add(Box.createRigidArea(new Dimension(0, 5)));

        methodComboBox = new JComboBox<>();
        for (PaymentMethod method : paymentMethods) {
            methodComboBox.addItem(method);
        }
        methodComboBox.setRenderer(new PaymentMethodListCellRenderer());
        methodComboBox.setMaximumSize(new Dimension(230, 30));
        methodComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(methodComboBox);
        add(Box.createRigidArea(new Dimension(0, 25)));

        // Кнопка перевода
        transferButton = new JButton("Выполнить перевод");
        transferButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        transferButton.setBackground(new Color(51, 102, 204));
        transferButton.setForeground(Color.WHITE);
        transferButton.setFocusPainted(false);
        transferButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        add(transferButton);
        add(Box.createVerticalGlue());
    }

    public Client getSelectedSender() {
        return (Client) senderComboBox.getSelectedItem();
    }

    public Client getSelectedReceiver() {
        return (Client) receiverComboBox.getSelectedItem();
    }

    public String getAmount() {
        return amountField.getText();
    }

    public PaymentMethod getSelectedPaymentMethod() {
        return (PaymentMethod) methodComboBox.getSelectedItem();
    }

    public void addTransferButtonListener(ActionListener listener) {
        transferButton.addActionListener(listener);
    }

    public void updateClientsList(List<Client> clients) {

        // Очищаем и обновляем карту клиентов
        clientsMap.clear();
        for (Client client : clients) {
            clientsMap.put(client.getInn(), client);
        }

        // Обновляем списки
        senderComboBox.removeAllItems();
        receiverComboBox.removeAllItems();

        for (Client client : clients) {
            senderComboBox.addItem(client);
            receiverComboBox.addItem(client);
        }

        // Если не удалось восстановить, используем первый элемент для отправителя
        // и второй (если есть) для получателя
        if (senderComboBox.getSelectedItem() == null && senderComboBox.getItemCount() > 0) {
            senderComboBox.setSelectedIndex(0);
        }

        if (receiverComboBox.getSelectedItem() == null) {
            if (receiverComboBox.getItemCount() > 1) {
                receiverComboBox.setSelectedIndex(1);
            } else if (receiverComboBox.getItemCount() > 0) {
                receiverComboBox.setSelectedIndex(0);
            }
        }
    }

    // Метод для выбора клиента в комбобоксе по ИНН
    private void selectClientInComboBox(JComboBox<Client> comboBox, String inn) {
        if (inn == null) return;

        for (int i = 0; i < comboBox.getItemCount(); i++) {
            Client client = comboBox.getItemAt(i);
            if (client.getInn().equals(inn)) {
                comboBox.setSelectedIndex(i);
                return;
            }
        }
    }

    // Очистка поля суммы после перевода
    public void clearAmountField() {
        amountField.setText("");
    }

    // Renderer для комбобокса клиентов
    private static class ClientListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Client) {
                Client client = (Client) value;
                setText(client.getFirstName() + " " + client.getLastName() + " (" + formatBalance(client.getBalance()) + ")");
            }

            return this;
        }

        private String formatBalance(double balance) {
            return String.format("%.2f", balance);
        }
    }

    // Renderer для комбобокса методов оплаты
    private static class PaymentMethodListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof PaymentMethod) {
                PaymentMethod method = (PaymentMethod) value;
                setText(method.getDescription());
            }

            return this;
        }
    }
}
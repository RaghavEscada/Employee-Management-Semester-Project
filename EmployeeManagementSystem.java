import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeManagementSystem extends JFrame {
    private JTextField idField, nameField;
    private JComboBox<String> positionComboBox;
    private JButton addButton, updateButton, deleteButton;
    private DefaultListModel<String> employeeListModel;
    private JList<String> employeeList;

    public EmployeeManagementSystem() {
        setTitle("Employee Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        idField = new JTextField(10);
        nameField = new JTextField(10);
        positionComboBox = new JComboBox<>(new String[]{"CEO", "CFO", "Manager", "Employee", "Non-Technical"});
        addButton = new JButton("Add Employee");
        updateButton = new JButton("Update Employee");
        deleteButton = new JButton("Delete Employee");

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Position:"));
        inputPanel.add(positionComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        employeeListModel = new DefaultListModel<>();
        employeeList = new JList<>(employeeListModel);

        JScrollPane listScrollPane = new JScrollPane(employeeList);

        add(inputPanel, BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });
    }

    private void addEmployee() {
        String id = idField.getText();
        String name = nameField.getText();
        String position = (String) positionComboBox.getSelectedItem();

        if (!id.isEmpty() && !name.isEmpty() && position != null && !position.isEmpty()) {
            String employeeInfo = id + " | " + name + " | " + position;
            employeeListModel.addElement(employeeInfo);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        }
    }

    private void updateEmployee() {
        int selectedIndex = employeeList.getSelectedIndex();
        if (selectedIndex != -1) {
            String id = idField.getText();
            String name = nameField.getText();
            String position = (String) positionComboBox.getSelectedItem();

            if (!id.isEmpty() && !name.isEmpty() && position != null && !position.isEmpty()) {
                String employeeInfo = id + " | " + name + " | " + position;
                employeeListModel.setElementAt(employeeInfo, selectedIndex);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to update.");
        }
    }

    private void deleteEmployee() {
        int selectedIndex = employeeList.getSelectedIndex();
        if (selectedIndex != -1) {
            employeeListModel.remove(selectedIndex);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        positionComboBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeManagementSystem().setVisible(true);
            }
        });
    }
}

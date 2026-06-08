import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentGradeTrackerGUI extends JFrame {

private JTextField nameField;
private JTextField gradeField;

private JTable table;
private DefaultTableModel model;

private JLabel totalStudentsLabel;
private JLabel averageLabel;
private JLabel highestLabel;
private JLabel lowestLabel;

private ArrayList<String> studentNames = new ArrayList<>();
private ArrayList<Double> studentGrades = new ArrayList<>();

public StudentGradeTrackerGUI() {

    setTitle("Student Grade Tracker Dashboard");
    setSize(950, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout(15, 15));

    Color bg = new Color(245, 247, 250);

    JPanel header = new JPanel();
    header.setBackground(new Color(33, 150, 243));

    JLabel title = new JLabel("Student Grade Tracker Dashboard");
    title.setForeground(Color.WHITE);
    title.setFont(new Font("Segoe UI", Font.BOLD, 28));

    header.add(title);

    add(header, BorderLayout.NORTH);

    JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 15));
    statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    totalStudentsLabel = createCard(statsPanel, "Students", "0");
    averageLabel = createCard(statsPanel, "Average", "0");
    highestLabel = createCard(statsPanel, "Highest", "0");
    lowestLabel = createCard(statsPanel, "Lowest", "0");

    add(statsPanel, BorderLayout.SOUTH);

    JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
    centerPanel.setBackground(bg);

    JPanel inputPanel = new JPanel(new GridLayout(5, 1, 10, 10));
    inputPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));

    nameField = new JTextField();
    gradeField = new JTextField();

    JButton addButton = new JButton("Add Student");
    JButton deleteButton = new JButton("Delete Selected");

    inputPanel.add(new JLabel("Student Name"));
    inputPanel.add(nameField);

    inputPanel.add(new JLabel("Grade"));
    inputPanel.add(gradeField);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addButton);
    buttonPanel.add(deleteButton);

    inputPanel.add(buttonPanel);

    centerPanel.add(inputPanel, BorderLayout.WEST);

    model = new DefaultTableModel(
            new Object[]{"ID", "Student Name", "Grade"}, 0);

    table = new JTable(model);
    table.setRowHeight(25);

    JScrollPane scrollPane = new JScrollPane(table);

    centerPanel.add(scrollPane, BorderLayout.CENTER);

    add(centerPanel, BorderLayout.CENTER);

    addButton.addActionListener(e -> addStudent());

    deleteButton.addActionListener(e -> deleteStudent());

    getContentPane().setBackground(bg);

    setVisible(true);
}

private JLabel createCard(JPanel panel, String title, String value) {

    JPanel card = new JPanel(new BorderLayout());
    card.setBackground(Color.WHITE);

    JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

    JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
    valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));

    card.add(titleLabel, BorderLayout.NORTH);
    card.add(valueLabel, BorderLayout.CENTER);

    panel.add(card);

    return valueLabel;
}

private void addStudent() {

    try {

        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter student name");
            return;
        }

        double grade =
                Double.parseDouble(gradeField.getText().trim());

        studentNames.add(name);
        studentGrades.add(grade);

        model.addRow(new Object[]{
                model.getRowCount() + 1,
                name,
                grade
        });

        updateStatistics();

        nameField.setText("");
        gradeField.setText("");

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(this,
                "Enter valid grade");
    }
}

private void deleteStudent() {

    int row = table.getSelectedRow();

    if (row == -1) {

        JOptionPane.showMessageDialog(this,
                "Select a student first");
        return;
    }

    studentNames.remove(row);
    studentGrades.remove(row);

    model.removeRow(row);

    for (int i = 0; i < model.getRowCount(); i++) {
        model.setValueAt(i + 1, i, 0);
    }

    updateStatistics();
}

private void updateStatistics() {

    if (studentGrades.isEmpty()) {

        totalStudentsLabel.setText("0");
        averageLabel.setText("0");
        highestLabel.setText("0");
        lowestLabel.setText("0");
        return;
    }

    double total = 0;
    double highest = studentGrades.get(0);
    double lowest = studentGrades.get(0);

    for (double grade : studentGrades) {

        total += grade;

        if (grade > highest)
            highest = grade;

        if (grade < lowest)
            lowest = grade;
    }

    double average = total / studentGrades.size();

    totalStudentsLabel.setText(
            String.valueOf(studentGrades.size()));

    averageLabel.setText(
            String.format("%.2f", average));

    highestLabel.setText(
            String.valueOf(highest));

    lowestLabel.setText(
            String.valueOf(lowest));
}

public static void main(String[] args) {

    SwingUtilities.invokeLater(
            () -> new StudentGradeTrackerGUI());
}


}

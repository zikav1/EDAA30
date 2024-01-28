package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Map;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BookReaderController {

    public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 500, 500));
    }

    /**
     * @param counter
     * @param title
     * @param width
     * @param height
     */
    private void createWindow(GeneralWordCounter counter, String title,
            int width, int height) {

        // Creating a frame
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating list and removing the characters displayed as digits
        List<Map.Entry<String, Integer>> l = counter.getWordList();

        SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<>(l);
        JList<Map.Entry<String, Integer>> list = new JList<>(listModel);

        // Displaying the list inside of the scroll component
        JScrollPane scroll = new JScrollPane(list);

        // JPanel object.
        JPanel panel = new JPanel();

        // JButton Buttons.
        JButton alphabetic = new JButton("Alphabetic");
        JButton frequency = new JButton("Frequency");
        JButton serachButton = new JButton("Search");

        // JTextField
        JTextField textField = new JTextField(10);

        panel.add(alphabetic);
        panel.add(frequency);
        panel.add(textField);
        panel.add(serachButton);

        // Adding the scroll and panel (which holds my buttons)to the pane
        // and placing them at the center of the screen and south.
        Container pane = frame.getContentPane();
        pane.add(scroll, BorderLayout.CENTER);
        pane.add(panel, BorderLayout.SOUTH);

        alphabetic.addActionListener(e -> {
            l.removeIf(entry -> Character.isDigit(entry.getKey().charAt(0)));
            listModel.sort((w1, w2) -> w1.getKey().compareTo(w2.getKey()));
        });

        frequency.addActionListener(e -> {
            listModel.sort((f1, f2) -> Integer.compare(f2.getValue(), f1.getValue()));
        });

        serachButton.addActionListener(e -> {
            String textBox = textField.getText();
            for (int i = 0; i < listModel.getSize(); i++) {
                if (textBox.equals(listModel.getElementAt(i).getKey())) {
                    list.ensureIndexIsVisible(i);
                    break;
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
        frame.setSize(width, height);

    }

}

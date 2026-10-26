import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class B6_SwingEventHandling extends JFrame {
    private JTextArea eventLog;
    private JLabel wordCountLabel;
    private StringBuilder typedCharacters = new StringBuilder();
    private int buttonClicks = 0;

    public B6_SwingEventHandling() {
        setTitle("Swing Event Handling Demo");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        eventLog = new JTextArea();
        eventLog.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(eventLog);
        add(scrollPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton stopButton = new JButton("Stop");
        topPanel.add(startButton);
        topPanel.add(pauseButton);
        topPanel.add(stopButton);
        add(topPanel, BorderLayout.NORTH);

        wordCountLabel = new JLabel("Typed characters: 0 | Word count: 0");
        add(wordCountLabel, BorderLayout.SOUTH);

        ActionListener buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonClicks++;
                log("ActionEvent: " + e.getActionCommand()
                        + " clicked (Count = " + buttonClicks + ")");
            }
        };
        startButton.addActionListener(buttonListener);
        pauseButton.addActionListener(buttonListener);
        stopButton.addActionListener(buttonListener);

        eventLog.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!Character.isISOControl(ch)) {
                    typedCharacters.append(ch);
                }
                updateWordCount();
                log("KeyEvent: Typed '" + printable(ch) + "' (code = " + (int) ch + ")");
            }

            public void keyPressed(KeyEvent e) {
                log("KeyEvent: Pressed " + KeyEvent.getKeyText(e.getKeyCode())
                        + " (code = " + e.getKeyCode() + ")");
            }

            public void keyReleased(KeyEvent e) {
                log("KeyEvent: Released " + KeyEvent.getKeyText(e.getKeyCode())
                        + " (code = " + e.getKeyCode() + ")");
            }
        });

        MouseAdapter mouseHandler = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                log("MouseEvent: Clicked at (" + e.getX() + ", " + e.getY()
                        + ") with button " + e.getButton()
                        + ", click count " + e.getClickCount());
                if (e.getClickCount() == 2) {
                    log("Double click detected by MouseListener.");
                }
            }

            public void mouseDragged(MouseEvent e) {
                log("MouseEvent: Dragged at (" + e.getX() + ", " + e.getY() + ")");
            }

            public void mouseMoved(MouseEvent e) {
                // Mouse movement is available here; logging every move can fill the log very quickly.
            }
        };
        eventLog.addMouseListener(mouseHandler);
        eventLog.addMouseMotionListener(mouseHandler);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(newItem);
        fileMenu.add(exitItem);

        JMenu editMenu = new JMenu("Edit");
        JCheckBoxMenuItem editableItem = new JCheckBoxMenuItem("Editable", true);
        JRadioButtonMenuItem smallItem = new JRadioButtonMenuItem("Small");
        JRadioButtonMenuItem largeItem = new JRadioButtonMenuItem("Large");
        ButtonGroup fontGroup = new ButtonGroup();
        fontGroup.add(smallItem);
        fontGroup.add(largeItem);
        smallItem.setSelected(true);
        editMenu.add(editableItem);
        editMenu.addSeparator();
        editMenu.add(smallItem);
        editMenu.add(largeItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        newItem.addActionListener(e -> {
            eventLog.setText("");
            typedCharacters.setLength(0);
            updateWordCount();
            log("File > New selected.");
        });
        exitItem.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
        aboutItem.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Swing event handling example"));

        editableItem.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                eventLog.setEditable(editableItem.isSelected());
                log("ItemEvent: Editable " + (editableItem.isSelected() ? "checked" : "unchecked"));
            }
        });

        smallItem.addItemListener(e -> {
            if (smallItem.isSelected()) {
                eventLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
                log("ItemEvent: Small font selected.");
            }
        });
        largeItem.addItemListener(e -> {
            if (largeItem.isSelected()) {
                eventLog.setFont(new Font("Monospaced", Font.PLAIN, 18));
                log("ItemEvent: Large font selected.");
            }
        });

        JPopupMenu popup = new JPopupMenu();
        JMenuItem clearItem = new JMenuItem("Clear Log");
        JMenuItem copyItem = new JMenuItem("Copy");
        popup.add(clearItem);
        popup.add(copyItem);
        eventLog.setComponentPopupMenu(popup);
        clearItem.addActionListener(e -> eventLog.setText(""));
        copyItem.addActionListener(e -> eventLog.copy());

        log("Ready. Click a button, type in the log area, or use the menus.");
    }

    private String printable(char ch) {
        if (ch == '\n') return "\\n";
        if (ch == '\t') return "\\t";
        return String.valueOf(ch);
    }

    private void updateWordCount() {
        String text = typedCharacters.toString().trim();
        int words = text.isEmpty() ? 0 : text.split("\\s+").length;
        wordCountLabel.setText("Typed characters: " + typedCharacters.length()
                + " | Word count: " + words);
    }

    private void log(String message) {
        eventLog.append(message + "\n");
        eventLog.setCaretPosition(eventLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new B6_SwingEventHandling().setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Unable to open application: " + e.getMessage());
                }
            }
        });
    }
}
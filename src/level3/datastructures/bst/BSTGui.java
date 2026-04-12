package level3.datastructures.bst;

import javax.swing.*;
import java.awt.*;

public class BSTGui extends JFrame {

    BinarySearchTree bst = new BinarySearchTree();

    JTextField input;
    JTextArea output;

    private final Color BG = new Color(245, 247, 250);          // soft background
    private final Color CARD = new Color(255, 255, 255);        // white panel
    private final Color PRIMARY = new Color(63, 81, 181);       // indigo
    private final Color DANGER = new Color(229, 57, 53);        // red
    private final Color SUCCESS = new Color(67, 160, 71);       // green
    private final Color TEXT_DARK = new Color(33, 33, 33);

    public BSTGui() {

        setTitle("BST Visual Tool");
        setSize(520, 420);
        setLocation(400, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(BG);

        // TITLE
        JLabel title = new JLabel("Binary Search Tree Visualizer");
        title.setBounds(110, 10, 400, 30);
        title.setForeground(TEXT_DARK);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title);

        // INPUT
        input = new JTextField();
        input.setBounds(140, 55, 200, 32);
        input.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(input);

        // BUTTONS
        JButton insert = createButton("Insert", SUCCESS, 40, 100);
        JButton delete = createButton("Delete", DANGER, 185, 100);
        JButton traverse = createButton("Traverse", PRIMARY, 330, 100);

        add(insert);
        add(delete);
        add(traverse);

        // OUTPUT AREA
        output = new JTextArea();
        output.setFont(new Font("Consolas", Font.PLAIN, 13));
        output.setBackground(CARD);
        output.setForeground(TEXT_DARK);
        output.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(output);
        scroll.setBounds(40, 150, 430, 200);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        add(scroll);

        //LOGIC

        insert.addActionListener(e -> {
            try {
                String text = input.getText().trim();
                if (text.isEmpty()) {
                    output.setText("Please enter a value!");
                    return;
                }

                int value = Integer.parseInt(text);
                bst.root = bst.insert(bst.root, value);

                output.setText("Inserted: " + value);
                input.setText("");

            } catch (NumberFormatException ex) {
                output.setText("Invalid input! Enter a number.");
            }
        });

        delete.addActionListener(e -> {
            try {
                String text = input.getText().trim();
                if (text.isEmpty()) {
                    output.setText("Please enter a value!");
                    return;
                }

                int value = Integer.parseInt(text);
                bst.root = bst.delete(bst.root, value);

                output.setText("Deleted: " + value);
                input.setText("");

            } catch (NumberFormatException ex) {
                output.setText("Invalid input! Enter a number.");
            }
        });

        traverse.addActionListener(e -> {
            if (bst.root == null) {
                output.setText("Tree is empty!");
                return;
            }

            StringBuilder result = new StringBuilder();

            result.append("Inorder: ");
            captureInorder(bst.root, result);

            result.append("\nPreorder: ");
            capturePreorder(bst.root, result);

            result.append("\nPostorder: ");
            capturePostorder(bst.root, result);

            output.setText(result.toString());
        });

        setVisible(true);
    }

    // BUTTON FACTORY
    private JButton createButton(String text, Color bg, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 120, 32);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    //TRAVERSALS
    private void captureInorder(TreeNode root, StringBuilder sb) {
        if (root != null) {
            captureInorder(root.left, sb);
            sb.append(root.value).append(" ");
            captureInorder(root.right, sb);
        }
    }

    private void capturePreorder(TreeNode root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.value).append(" ");
            capturePreorder(root.left, sb);
            capturePreorder(root.right, sb);
        }
    }

    private void capturePostorder(TreeNode root, StringBuilder sb) {
        if (root != null) {
            capturePostorder(root.left, sb);
            capturePostorder(root.right, sb);
            sb.append(root.value).append(" ");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BSTGui::new);
    }
}
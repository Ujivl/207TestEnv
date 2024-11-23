import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * A child class that inherits JPanel, it will hold all the post-it notes and take care adding/removing/editing
 */
class SquarePanel extends JPanel {
    private ArrayList<PostNote> postNotes; // List to hold all post-it notes on the panel

    public SquarePanel(ArrayList<PostNote> postNotes) {
        this.postNotes = postNotes;
        setLayout(null); // Disable layout manager for manual positioning
        setBackground(Color.WHITE); // Set background color of the panel

        // Add a mouse listener to detect right-clicks on the panel
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    showContextMenu(e.getPoint());
                }
            }
        });
    }

    /**
     * Displays a context menu at the clicked position with an option to create a new post-it note.
     * @param point The position where the context menu should be displayed
     */
    private void showContextMenu(Point point) {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem createNoteItem = new JMenuItem("Create New Post-it Note");
        createNoteItem.addActionListener(event -> createPostNoteAt(point));

        menu.add(createNoteItem);
        menu.show(this, point.x, point.y);
    }

    /**
     * Creates a new post-it note at the specified position.
     * @param point The position for the new post-it note
     */
    private void createPostNoteAt(Point point) {
        PostNote newPostNote = new PostNote(point.x, point.y, 100, 100, Color.YELLOW, this);
        postNotes.add(newPostNote);
        addPostNote(newPostNote);
        repaint();
    }

    // TODO: DELETE POST IT NOTE IMPLEMENTATION
/**
    public void removePostNote(PostNote postNote) {// Remove post-it notes label and text field from the panel
        remove(postNote.getLabel());
        remove(postNote.getTextField());
        postNotes.remove(postNote); // Remove post-it note from the list of post-it notes

            // Disconnect any child post-it notes from this one
            for (PostNote s : postNotes) {
                if (s.getParentPostIt() == postNote) {
                    s.setParentPostIt(null);
                }
            }
        repaint(); // Refresh the panel display
        }
*/

    /**
     * Adds a new Post-it note and its components (label and text field) to the panel.
     * @param postNote Post-it note to add to the panel
     */
    public void addPostNote(PostNote postNote) {
        add(postNote.getLabel());
        add(postNote.getTextField());
        repaint();
    }

    /**
     * Paints all post-it notes on the panel.
     * @param g Graphics object for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Calls JPanel's paintComponent
        for (PostNote postNote : postNotes) {
            postNote.draw(g);
        }
    }
}

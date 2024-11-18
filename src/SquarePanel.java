import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * A child class that inherits Jpanel, it will hold all the post-it notes and take care adding/removing/editing
 */
class SquarePanel extends JPanel {
    private ArrayList<PostNote> postNotes; // List to hold all post it notes on the panel

    public SquarePanel(ArrayList<PostNote> postNotes) {
        this.postNotes = postNotes;
        setLayout(null); // Sets Swing layout to avoid an object heirarchy, still dont really know how this works
        setBackground(Color.WHITE); // Set background color of the panel
    }

    /**
     * Adds a new Post-it note and its components (label and text field) to the panel.
     * @param postNote postit note to add to the panel
     */
    public void addPostNote(PostNote postNote) {
        add(postNote.getLabel()); // This will hold the actual post it note shape
        add(postNote.getTextField()); // This is the text field u can write on
        repaint(); // Refresh the panel to display the new post it note
    }

    /**
     * Removes a post-it notes components from the panel and disconnects all of its connecting lines
     * also this function cannot be run if the post-it note is the initial one.
     * @param postNote to remove
     */
    public void removePostNote(PostNote postNote) {
        // Remove post-it notes label and text field from the panel
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

    /**
     * Creates a new post-it note connected to the specified parent post-it note.
     * @param parentPostNote The parent post-it note to which the new one will be connected to
     */
    public void createConnectedPostNote(PostNote parentPostNote) {
        // right now i made it so that it creates a new one to the parent post-it notes right, we can change the location
        PostNote newPostNote = new PostNote(parentPostNote.x + 150, parentPostNote.y, 100, 100, Color.ORANGE, this);
        newPostNote.setParentPostIt(parentPostNote); // Set parent
        postNotes.add(newPostNote); // Add to the list of post-it notes
        addPostNote(newPostNote); // Display the new post-it note on the panel
        repaint(); // Refresh the panel display
    }

    /**
     * Paints all post-it notes and connection lines between them.
     * @param g Graphics object for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Calls JPanel's paintComponent to paint using g.
        // loops through each post-it note and first paints them, then paints the connection line between them and their
        // parent post-it note, if the post-it note even has a parent.
        for (PostNote postNote : postNotes) {
            postNote.draw(g);
            if (postNote.getParentPostIt() != null) {
                drawConnectionLine(g, postNote, postNote.getParentPostIt());
            }
        }
    }

    /**
     * Draws a line connecting a postit note to its parent post it note.
     * @param g Graphics object for drawing
     * @param postNote The child post it note
     * @param parentPostNote The parent post it note
     */
    private void drawConnectionLine(Graphics g, PostNote postNote, PostNote parentPostNote) {
        g.setColor(Color.BLACK);  // Makes the line color black
        // Draws a line between 2 points, the first point being the centre of the post-it note and the second point
        // being the centre of the parent post-it note
        g.drawLine(postNote.getCenterX(), postNote.getCenterY(), parentPostNote.getCenterX(), parentPostNote.getCenterY());
    }
}

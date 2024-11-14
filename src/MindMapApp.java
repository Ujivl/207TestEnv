import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A child class of JFrame that holds the SquarePanel, which can house all the post-it notes.
 */
public class MindMapApp extends JFrame {
    private ArrayList<PostNote> postNotes = new ArrayList<>(); // array list to hold squares
    private PostNote initialPostNote;  // Creates the original first post-it note that cant be deleted

    public MindMapApp() {
        setTitle("Mind Map");  // Sets the title
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When the x is pressed, the program closes out
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Makes the screen maximized (or else moving post-it doesnt work)

        SquarePanel panel = new SquarePanel(postNotes); // creates the SquarePanel object
        add(panel);  // Adds the panel to the Frame (dont need to use Frame.add since the class inherits it)

        // Create and add the initial square
        initialPostNote = new PostNote(100, 100, 100, 100, Color.ORANGE, panel);
        postNotes.add(initialPostNote);
        panel.addPostNote(initialPostNote);

        setVisible(true); // Display the frame
    }

    /**
     * getter for the initial post it note, used to make sure its not deleted.
     * @return the initial Square object
     */
    public PostNote getInitialPostNote() {
        return initialPostNote;
    }
}

package dsw.gerudok.app.gui.swing.view.editView.view;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.view.editView.controller.*;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.elements.Slot;

import javax.swing.*;
import java.awt.*;

public class EditDialog extends JDialog {
    private Project project;
    private Slot slot;
    private JLabel imageLbl;
    private String imagePath;
    private JTextPane editorPane;


    public EditDialog(Frame owner, boolean modal,Project project, Slot slot) {
        super(owner, modal);
        this.project = project;
        this.slot = slot;
        initialise();
        if(slot.getFileType() == -1){
            initialiseNoneType();
        }else if(slot.getFileType() == 0){
            initialiseTextType();
        }else if(slot.getFileType() == 1){
            initialiseImageType();
        }
        imagePath = "";
    }

    private void initialise(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 4, screenHeight / 4);
        setLocationRelativeTo(null);
        this.setTitle("Sadržaj slota");

    }

    public void initialiseNoneType(){

        this.getContentPane().removeAll();

        JLabel selectLbl = new JLabel("Izaberite tip sadržaja slota:",SwingConstants.CENTER);
        selectLbl.setFont(new Font("Arial Black", Font.BOLD, 22));
        JButton imageButton = new JButton(new ImageButton(this));
        JButton textButton = new JButton(new TextButton(this));
        textButton.setBackground(Color.WHITE);
        imageButton.setBackground(Color.WHITE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridLayout(1,2));
        mainPanel.add(selectLbl, BorderLayout.NORTH);
        centerPanel.add(textButton);
        centerPanel.add(imageButton);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        this.add(mainPanel);

        this.repaint();
        this.setVisible(true);
    }

    public void initialiseTextType() {

        this.getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel northPanel = new JPanel();
        JButton boldButton = new JButton(new BoldButton(this));
        JButton italicButton = new JButton(new ItalicButton(this));
        JButton underlineButton = new JButton(new UnderlineButton(this));
        JButton saveButton = new JButton(new SaveText(this));
        northPanel.add(boldButton);
        northPanel.add(italicButton);
        northPanel.add(underlineButton);
        northPanel.add(saveButton);
        editorPane = new JTextPane();
        editorPane.setContentType("text/html");
        if(!slot.getSlotFilePath().isEmpty()) {
            editorPane.setText(AppCore.getInstance().getiSerializer().getTextFromFile(slot.getSlotFilePath()));
        }
        JScrollPane jScrollPane = new JScrollPane(editorPane);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(jScrollPane, BorderLayout.CENTER);

        this.add(mainPanel);

        this.setVisible(true);
        this.repaint();

    }

    public void initialiseImageType(){

        this.getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel northPanel = new JPanel();
        JButton uploadButton = new JButton(new UploadImage(this));
        JButton saveButton = new JButton((new SaveImage(this)));
        northPanel.add(uploadButton);
        northPanel.add(saveButton);
        imageLbl = new JLabel("",SwingConstants.CENTER);
        if(!slot.getSlotFilePath().isEmpty()){
            ImageIcon imageIcon = new ImageIcon(slot.getSlotFilePath());
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(400, 150,  java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            imageLbl.setIcon(imageIcon);
        }
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(imageLbl, BorderLayout.CENTER);

        this.add(mainPanel);

        this.setVisible(true);
        this.repaint();

    }

    public Slot getSlot() {
        return slot;
    }

    public JLabel getImageLbl() {
        return imageLbl;
    }

    public void setImageLbl(JLabel imageLbl) {
        this.imageLbl = imageLbl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Project getProject() {
        return project;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public JTextPane getEditorPane() {
        return editorPane;
    }
}

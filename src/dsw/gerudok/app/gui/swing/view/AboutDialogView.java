package dsw.gerudok.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class AboutDialogView extends JDialog {

    JLabel imePrezime1Lbl;
    JLabel imePrezime2Lbl;
    JLabel picture1Lbl;
    JLabel picture2Lbl;
    JPanel mainPanel;
    ImageIcon img1 = null;
    ImageIcon img2 = null;

    public AboutDialogView(Frame owner, boolean modal) {
        super(owner, modal);
        initialise();
        addElements();
    }



    private void initialise() {
        imePrezime1Lbl = new JLabel("Igor Todorović RN 50/2019",SwingConstants.CENTER);
        imePrezime1Lbl.setFont(new Font("Arial Black", Font.BOLD, 22));
        imePrezime2Lbl = new JLabel("Miloš Čečulović RN 28/2019",SwingConstants.CENTER);
        imePrezime2Lbl.setFont(new Font("Arial Black", Font.BOLD, 22));
        picture1Lbl = new JLabel("",SwingConstants.CENTER);
        picture2Lbl = new JLabel("",SwingConstants.CENTER);
        mainPanel = new JPanel(new GridLayout(2,2));

    }


    private void addElements() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setTitle("About");

        img1 = new ImageIcon(this.getClass().getResource("images/img1.png"));
        img2 = new ImageIcon(this.getClass().getResource("images/img2.png"));
        picture1Lbl.setIcon(img1);
        picture2Lbl.setIcon(img2);

        mainPanel.add(imePrezime1Lbl);
        mainPanel.add(picture1Lbl);
        mainPanel.add(imePrezime2Lbl);
        mainPanel.add(picture2Lbl);
        mainPanel.setBackground(Color.lightGray);
        this.add(mainPanel,BorderLayout.CENTER);

        setVisible(true);
    }
}

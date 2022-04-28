package dsw.gerudok.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class AboutDialogView extends JDialog {

    JLabel imePrezimeLbl;
    JLabel pictureLbl;

    JPanel mainPanel;
    ImageIcon img1 = null;

    public AboutDialogView(Frame owner, boolean modal) {
        super(owner, modal);
        initialise();
        addElements();
    }



    private void initialise() {
        imePrezimeLbl = new JLabel("Miloš Čečulović RN 28/2019",SwingConstants.CENTER);
        pictureLbl = new JLabel("",SwingConstants.CENTER);
        mainPanel = new JPanel(new GridLayout(1,1));

    }


    private void addElements() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setTitle("About");
        img1 = new ImageIcon(this.getClass().getResource("images/img.png"));
        pictureLbl.setIcon(img1);

        mainPanel.add(imePrezimeLbl);
        mainPanel.add(pictureLbl);
        mainPanel.setBackground(Color.lightGray);
        this.add(mainPanel,BorderLayout.CENTER);

        setVisible(true);
    }
}

package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.gui.swing.view.AboutDialogView;
import dsw.gerudok.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutAction extends AbstractImageAction {

    public AboutAction() {

        putValue(SMALL_ICON, loadIcon("images/about.png"));
        putValue(NAME, "About");
        putValue(SHORT_DESCRIPTION, "About");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AboutDialogView aboutDialogView = new AboutDialogView(MainFrame.getInstance(), true);
    }


}

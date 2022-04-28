package dsw.gerudok.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShareDialogView extends JOptionPane {

    private JComboBox izborProjektaCb;
    private List<String> projectList;
    public ShareDialogView(List<String> projectList){
        this.projectList = projectList;
        String[] list = new String[projectList.size()];
        list = projectList.toArray(list);
        izborProjektaCb = new JComboBox( list);
        izborProjektaCb.setEditable(false);
    }
    public int showDialog(){
        int n = JOptionPane.showConfirmDialog(null,getPanel(),"Deljenje dokumenta", OK_CANCEL_OPTION, QUESTION_MESSAGE);
        if(n == JOptionPane.CANCEL_OPTION){
            return  2;
        }else if(n == JOptionPane.OK_OPTION){
            return  1;
        }else {
            return  2;
        }
    }

    private JPanel getPanel(){
        JLabel jLabel = new JLabel("Izaberite projekat sa kojim želite da delite dokument:", SwingConstants.CENTER);
        JPanel jPanel = new JPanel();
        jPanel.add(jLabel, BorderLayout.NORTH);
        jPanel.add(izborProjektaCb, BorderLayout.CENTER);
        return  jPanel;
    }


    public JComboBox getIzborProjektaCb() {
        return izborProjektaCb;
    }
}

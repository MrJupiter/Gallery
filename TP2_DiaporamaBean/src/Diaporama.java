import Beans.BeanFolderPath;
import Beans.ImageBean;
import Beans.BeanTotalImages;
import Beans.BeanInformations;

import javax.swing.*;
import java.awt.event.*;

public class Diaporama{
    private JTextField textField1;
    private JButton choisirUneRépertoireButton;
    private JButton suivantButton;
    private JButton premierButton;
    private JPanel centralPanel;
    private JButton précédentButton;
    private JButton dernierButton;
    private JCheckBox affichageEnBoucleCheckBox;
    private JCheckBox modeDiaporamaCheckBox;
    private JSlider slider1;
    private JPanel controlPanel;
    private JPanel imagePanel;
    private JPanel repositoryPanel;
    private ImageBean imageBean1;
    private BeanInformations imageInformationsBean1;
    private BeanTotalImages beanTotalImages1;

    //private BeanFolderPath beanFolderPath = new BeanFolderPath();

    public Diaporama() {

        JFrame _frame = new JFrame("Diaporama d'images");

        _frame.add(centralPanel);

        _frame.setBounds(300,120, 1200, 800);
        _frame.setVisible(true);
        _frame.setResizable(false);
        ImageIcon icon = new ImageIcon(getClass().getResource("ensicaen.jpg"));
        _frame.setIconImage(icon.getImage());
        _frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        _frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Do you really want to quit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if(response == JOptionPane.YES_OPTION)
                    e.getWindow().dispose();
            }
        });

        imageBean1.addPropertyChangeListener(imageInformationsBean1);
        //beanFolderPath.addPropertyChangeListener(beanTotalImages1);

        suivantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageBean1.next();
            }
        });
        précédentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageBean1.previous();
            }
        });
        dernierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageBean1.last();
            }
        });
        premierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageBean1.first();
            }
        });

        // Display images when choosing the absolute path of the images' folder using a JFileChooser
        choisirUneRépertoireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Connect and bind two beans: ImageBean with BeanTotalImages: when changing the path of the folder notify the object BeanTotalImages
                // so the amount of images can be dynamically changed
                //imageBean1.removePropertyChangeListener(imageInformationsBean1);
                //imageBean1.addPropertyChangeListener(beanTotalImages1);

                JFileChooser repository = new JFileChooser();
                try {
                    repository.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    repository.showOpenDialog(_frame);
                    repository.setBounds(600, 400, 600, 420);
                    textField1.setText(repository.getSelectedFile().getAbsolutePath());

                    imageBean1.setImageFolderPath(textField1.getText());
                    //beanFolderPath.setFolderPath(textField1.getText());

                    beanTotalImages1.setImageFolderPath(textField1.getText());
                }catch (Exception ex){
                    System.out.println("Error Message: " + ex.getMessage());
                }
                //imageBean1.removePropertyChangeListener(beanTotalImages1);
            }
        });

        // Display images when typing the absolute path of the images' folder and pressing the "Enter" key
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    // Connect and bind two beans: ImageBean with BeanTotalImages: when changing the path of the folder notify the object BeanTotalImages
                    // so the amount of images can be dynamically changed

                    //imageBean1.removePropertyChangeListener(imageInformationsBean1);
                    //imageBean1.addPropertyChangeListener(beanTotalImages1);
                    try {
                        imageBean1.setImageFolderPath(textField1.getText());
                        //beanFolderPath.setFolderPath(textField1.getText());
                        beanTotalImages1.setImageFolderPath(textField1.getText());
                    }catch(Exception ex){
                        System.out.println("Error Message: " + ex.getMessage());
                    }
                    //imageBean1.removePropertyChangeListener(beanTotalImages1);
                }
            }
        });

        affichageEnBoucleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageBean1.setLoop(affichageEnBoucleCheckBox.isSelected());
            }
        });

        modeDiaporamaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider1.setEnabled(!modeDiaporamaCheckBox.isSelected());
                imageBean1.enableDiaporama(modeDiaporamaCheckBox.isSelected(),slider1.getValue());
            }
        });

    }

    public static void main(String [] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Diaporama();
            }
        });
    }


}

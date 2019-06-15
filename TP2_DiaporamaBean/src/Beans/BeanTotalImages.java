package Beans;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.Serializable;

public class BeanTotalImages extends JLabel implements Serializable, PropertyChangeListener {

    private String _imageFolderPath;
    private int _counter = 0;

    public BeanTotalImages(){
        super();
        setText("Nombre d'images: ");
    }

    public void setImageFolderPath(String imagePath){
        _imageFolderPath = imagePath;
        loadImagesNumber();
    }

    private void loadImagesNumber() {
        File[] files = new File(_imageFolderPath).listFiles();
        _counter = 0;

        for (File file : files) {
            if (file.isFile()) {
                if(file.getName().endsWith(".jpg") ||  file.getName().endsWith(".png") || file.getName().endsWith(".gif") || file.getName().endsWith(".jpeg")){
                    _counter++;
                }
            }
        }
        setText("Nombre d'images: " + ((_counter==0)?"":_counter));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        _imageFolderPath = evt.getNewValue().toString();
        loadImagesNumber();
    }

}

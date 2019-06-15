package Beans;

import changeEvent.ImageChangedEvent;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class BeanInformations extends JLabel implements Serializable, PropertyChangeListener {

    private String _imagePath;

    public BeanInformations() {
        super();
        setText("Photo No \"unknown\". \t\t  \"unknown image name\" \t \"unknown width\"x\"unknown height\" : Choose a folder path to display images! ");
    }

    public String getImagePath() {
        return _imagePath;
    }

    public void setImagePath(String imagePath) {
        _imagePath = imagePath;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ImageChangedEvent event = (ImageChangedEvent) evt;
        setText("Photo No " + (event.getIndex() + 1) + ". \t\t  " + event.getName() + " \t" + event.getWidth() + " x " + event.getHeight());
    }
}
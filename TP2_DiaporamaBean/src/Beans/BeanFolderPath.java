package Beans;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class BeanFolderPath extends JTextField implements Serializable {

    private String _folderPath;
    private PropertyChangeSupport _pathChanged = new PropertyChangeSupport(this);

    public BeanFolderPath(){
        super();
    }
    public BeanFolderPath(int cols)
    {
        super(cols);
    }
    public void setFolderPath(String folderPath) {
        _pathChanged.firePropertyChange("pathChanged", _folderPath, folderPath);
        _folderPath = folderPath;
    }

    public String getFolderPath() {
        return _folderPath;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        _pathChanged.addPropertyChangeListener(listener);
    }
}

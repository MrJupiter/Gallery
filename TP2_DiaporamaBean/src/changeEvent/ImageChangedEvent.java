package changeEvent;

import java.beans.PropertyChangeEvent;

public class ImageChangedEvent extends PropertyChangeEvent {

    private int _width;
    private int _height;
    private int _index;
    private String _name;

    public ImageChangedEvent(Object source,String propertyName, int index, String name, int width, int height) {
        super(source, propertyName, null, null);
        _index = index;
        _name = name;
        _width = width;
        _height = height;
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public String getName() {
        return _name;
    }

    public int getIndex() {
        return _index;
    }
}
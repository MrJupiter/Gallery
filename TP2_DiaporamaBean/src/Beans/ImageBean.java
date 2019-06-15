package Beans;

import Threads.DiapoThread;
import changeEvent.ImageChangedEvent;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageBean extends JPanel implements Serializable {

    private Image _currentImage = null;
    private String _imageFolderPath = "";
    private List<Image> _images = new ArrayList<>();
    private List<String> _imagesName = new ArrayList<>();
    private int _index = 0;
    private boolean _diaporama = false;
    private boolean _loop = false;
    private int _clock = 1;   // 1 second
    private int _currentImagewidth;
    private int _currentImageheight;
    private String _currentImageName;

    private PropertyChangeSupport _pathChanged = new PropertyChangeSupport(this);

    public ImageBean(){
        super();
    }

    public void enableDiaporama(boolean toggle, int clock) {
        _diaporama = toggle;
        _clock = clock;
        if (_diaporama) {
            DiapoThread diaporamaThread = new DiapoThread(this, _clock);
            diaporamaThread.start();
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        _pathChanged.addPropertyChangeListener(listener);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(_currentImage, 0, 0, getWidth(), getHeight(), this);
    }

    public void next(){
        if(_index < _images.size() && _index >= 0){
            if(_index == _images.size() - 1)
                setIndex((!_loop)?_index:0);
            else
                setIndex(_index+1);
            setCurrentImage(_images.get(_index));
        }
        repaint();
    }

    public void previous(){
        if(_index >= 0 && _index < _images.size()){
            if(_index == 0)
                setIndex((!_loop)?_index:(_images.size() - 1));
            else
                setIndex(_index-1);
            setCurrentImage(_images.get(_index));
        }
        repaint();
    }

    public void first(){
        if(_images.size() > 0){
            setIndex(0);
            setCurrentImage(_images.get(_index));
        }
        repaint();
    }

    public void last(){
        if(_images.size() > 1){
            setIndex(_images.size() - 1);
            setCurrentImage(_images.get(_index));
        }
        repaint();
    }

    // Getters:
    public Image getCurrentImage() {
        return _currentImage;
    }
    public int getClock() {
        return _clock;
    }
    public int getIndex() {
        return _index;
    }
    public List<Image> getImages() {
        return _images;
    }
    public String getImageFolderPath() {
        return _imageFolderPath;
    }
    public boolean isDiaporama() {
        return _diaporama;
    }
    public boolean isLoop() {
        return _loop;
    }
    public String getCurrentImageName() {
        return _currentImageName;
    }
    public int getCurrentImageheight() {
        return _currentImageheight;
    }
    public int getCurrentImagewidth() {
        return _currentImagewidth;
    }

    // Setters:
    public void setCurrentImage(Image currentImage) {
        _currentImage = currentImage;
        setCurrentImagewidth(_currentImage.getWidth(this));
        setCurrentImageheight(_currentImage.getHeight(this));
        setCurrentImageName(_imagesName.get(_images.indexOf(_currentImage)));
        try {
            _pathChanged.firePropertyChange(new ImageChangedEvent(this, "imageInformations", _index, _currentImageName, _currentImagewidth, _currentImageheight));
        }catch (Exception e){
            System.out.println("Error Message: " + e.getMessage());
        }
        repaint();
    }
    public void setImageFolderPath(String imagePath){
        //_pathChanged.firePropertyChange("imagesFolderPath", _imageFolderPath, imagePath);
        _imageFolderPath = imagePath;
        loadImages();
    }
    public void setIndex(int index) {
        _index = index;
    }
    public void setCurrentImageName(String currentImageName) {
        _currentImageName = currentImageName;
    }
    public void setCurrentImageheight(int currentImageheight) {
        _currentImageheight = currentImageheight;
    }
    public void setCurrentImagewidth(int currentImagewidth) {
        _currentImagewidth = currentImagewidth;
    }
    public void setClock(int clock) {
        _clock = clock;
        repaint();
    }
    public void setLoop(boolean loop) {
        _loop = loop;
    }
    public void setDiaporama(boolean _diaporama) {
        this._diaporama = _diaporama;
    }
    public void setImages(List<Image> _images) {
        this._images = _images;
    }

    // Private methods:
    private void loadImages() {
        File[] files = new File(_imageFolderPath).listFiles();
        _images.clear();
        _imagesName.clear();
        for (File file : files) {
            if (file.isFile()) {
                if(file.getName().endsWith(".jpg") ||  file.getName().endsWith(".png") || file.getName().endsWith(".gif") || file.getName().endsWith(".jpeg")){
                    _imagesName.add(file.getName());
                    _images.add(Toolkit.getDefaultToolkit().createImage(_imageFolderPath + "\\" + file.getName()));
                }
            }
        }
        first();
    }
}


package Threads;

import Beans.ImageBean;

public class DiapoThread extends Thread {

    private ImageBean _imageBean;
    private int _clock ;
    public DiapoThread(ImageBean imageBean, int clock) {
        super();
        _imageBean = imageBean;
        _clock = clock;
    }

    @Override
    public void run() {
        while (true) {
            if (_imageBean.isDiaporama()) {
                try {
                    Thread.sleep(_clock * 100);
                    _imageBean.next();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
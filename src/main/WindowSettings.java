package main;

public class WindowSettings {

    private int windowWidth;
    private int windowHeight;

    public WindowSettings(){
        //Edit these variables according to your needs. 
        this.windowHeight = 800;
        this.windowWidth = 800;
    }

    public int getWindowHeight(){
        return windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }
}
package main.CarModel;
import java.awt.*;

public class Saab95 extends Cars {
    private float turbo; // Set to 1 when off
    
    public Saab95(float x,float y){
        super(2, Color.red, 125, "Saab95", 100, x, y);
	    turbo = 1f;
    }

    public void setTurboOn(){
        turbo = 1.3f;
    }
    
    public void setTurboOff(){
        turbo = 1f;
    }
    
    @Override
    public float getSpeedFactor(){
        return getEnginePower() * 0.01f * turbo;
    }

    public float getTurbo(){
        return turbo;
    }
}
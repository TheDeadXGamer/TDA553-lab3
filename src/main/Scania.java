package main;
import java.awt.*;

public class Scania extends Cars{

    private float trailerAngle;

    public Scania(){
        super(2, Color.white, 100, "Scania",500); 
	    trailerAngle = 0;
    }
    
    
    public void RaiseTrailer(float x) {
        if(getCurrentSpeed() == 0) {
            trailerAngle = Math.min(70, trailerAngle += x);
        }
    }

    
    public void LowerTrailer(float x) {
        if(getCurrentSpeed() == 0) {
            trailerAngle = Math.max(0, trailerAngle -= x);
        }
    }

    @Override
    float getSpeedFactor(){
        if(trailerAngle == 0) {
            return getEnginePower() * 0.01f;
        }
        else {
            return 0;
        }
        
    }

    public float GetTrailerAngle() {
        return trailerAngle;
    }
}

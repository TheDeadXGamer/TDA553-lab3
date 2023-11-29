package main.CarModel;
import java.awt.*;

public class Scania extends Cars{

    private float trailerAngle;
    public static float maxAngle = 70f;
    public static float minAngle = 0f;

    public Scania(float x,float y){
        super(2, Color.white, 100, "Scania",500,x,y); 
	    trailerAngle = 0;
    }
    
    
    public void RaiseTrailer(float x) {
        if(getCurrentSpeed() == 0) {
            trailerAngle = Math.min(maxAngle, trailerAngle + x);
        }
    }

    public void LowerTrailer(float x) {
        if(getCurrentSpeed() == 0) {
            trailerAngle = Math.max(minAngle, trailerAngle - x);
        }
    }

    @Override
    float getSpeedFactor(){
        if(trailerAngle == minAngle) {
            return getEnginePower() * 0.01f;
        }
        else {
            return 0;
        }
        
    }

    public float getTrailerAngle() {
        return trailerAngle;
    }
}

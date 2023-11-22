package main;
import java.awt.*;

public class Volvo240 extends Cars{

    private final float trimFactor = 1.25f;
    
    public Volvo240(){
        super(4, Color.black, 100, "Volvo240", 100);
    }
    
    @Override
    public float getSpeedFactor(){
        return getEnginePower() * 0.01f * trimFactor;
    }

    public float getTrimFactor(){
        return trimFactor;
    }
}

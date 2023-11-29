import java.awt.Color;
import org.junit.*;

import main.CarModel.Saab95;
import main.CarModel.Volvo240;

public class CarsTest {

    Saab95 saab = new Saab95(0,0);
    Volvo240 volvo = new Volvo240(0,0);

    @Test
    public void TestNrDoors() {
        Assert.assertTrue((saab.getNrDoors() == 2) && (volvo.getNrDoors() == 4));
    }

    @Test
    public void TestEnginePower() {
        Assert.assertTrue((saab.getEnginePower() == 125) && (volvo.getEnginePower() == 100));
    }

    @Test
    public void TestCurrentSpeed() {
        Assert.assertTrue((saab.getCurrentSpeed() == 0) && (volvo.getCurrentSpeed() == 0));
    }

    @Test
    public void TestColor() {
        Assert.assertTrue((saab.getColor() == Color.red) && (volvo.getColor() == Color.black));
    }

    @Test
    public void TestSetColor() {
        volvo.setColor(Color.blue);
        saab.setColor(Color.blue);

        Assert.assertTrue((saab.getColor() == Color.blue) && (volvo.getColor() == Color.blue));
    }
    
    @Test
    public void TestModelName(){
        Assert.assertTrue((saab.getModelName() == "Saab95") && (volvo.getModelName() == "Volvo240"));
    }

    @Test
    public void TestStartEngine() {
        volvo.startEngine();
        saab.startEngine();
        volvo.gas(1);
        saab.gas(1);

        Assert.assertTrue((saab.getCurrentSpeed() != 0) && (volvo.getCurrentSpeed() != 0));
    }
    
    @Test
    public void TestStopEngine() {
        volvo.startEngine();
        saab.startEngine();

        volvo.gas(1);
        saab.gas(1);

        volvo.stopEngine();
        saab.stopEngine();

        Assert.assertTrue((saab.getCurrentSpeed() == 0f) && (volvo.getCurrentSpeed() == 0f));
    }

    @Test
    public void TestSpeedFactor() {
        float saabSpeedFactor = saab.getEnginePower() * 0.01f * saab.getTurbo();
        float volvoSpeedFactor = volvo.getEnginePower() * 0.01f * volvo.getTRIMFACTOR();

        Assert.assertTrue((saab.getSpeedFactor() == saabSpeedFactor) && (volvo.getSpeedFactor()) == volvoSpeedFactor);
    }


    @Test
    public void TestGasAndBrakeAndTurbo() {
        Boolean turboGasTest = false;
        Boolean brakeTest = false;
        float oldSpeedSaab;
        float oldSpeedVolvo;

        volvo.startEngine();
        saab.startEngine();

        saab.setTurboOn();
        volvo.gas(1);
        saab.gas(1);

        oldSpeedSaab = saab.getCurrentSpeed();
        oldSpeedVolvo = volvo.getCurrentSpeed();
        
        if ((oldSpeedSaab > oldSpeedVolvo)) {
            turboGasTest = true;
        }
        
        volvo.brake(0.1f);
        saab.brake(0.1f);

        if ((saab.getCurrentSpeed() < oldSpeedSaab) && (volvo.getCurrentSpeed() < oldSpeedVolvo)) {
            brakeTest = true;
        }

        Assert.assertTrue(turboGasTest && brakeTest);
    }

    @Test
    public void gasNotDecreaseSpeed() {
        saab.startEngine();
        volvo.startEngine();

        float oldSpeedSaab = saab.getCurrentSpeed();
        float oldSpeedVolvo = volvo.getCurrentSpeed();
        saab.gas(1);
        volvo.gas(1);
        
        Assert.assertTrue(!(oldSpeedSaab > saab.getCurrentSpeed()) && !(oldSpeedVolvo > volvo.getCurrentSpeed()));

    }
    
    @Test
    public void brakeNotIncreaseSpeed() {
        saab.startEngine();
        volvo.startEngine();

        float oldSpeedSaab = saab.getCurrentSpeed();
        float oldSpeedVolvo = volvo.getCurrentSpeed();
        saab.brake(1);
        volvo.brake(1);

        Assert.assertTrue(!(oldSpeedSaab < saab.getCurrentSpeed()) && !(oldSpeedVolvo < volvo.getCurrentSpeed()));

    }

    @Test
    public void speedAboveZero() {
        saab.startEngine();
        volvo.startEngine();

        for (int index = 0; index < 600; index++) {
            saab.brake(1);
            volvo.brake(1);
        }

        Assert.assertTrue(saab.getCurrentSpeed() >= 0 && volvo.getCurrentSpeed() >= 0);
    }
    
    @Test
    public void speedLessThanEnginepower() {
        saab.startEngine();
        volvo.startEngine();

        for (int index = 0; index < 600; index++) {
            saab.gas(1);
            volvo.gas(1);
        }

        Assert.assertTrue(saab.getCurrentSpeed() <= saab.getEnginePower() && volvo.getCurrentSpeed() <= volvo.getEnginePower());
    }


    @Test
    public void testMoveForward() {
        volvo.startEngine();
        saab.startEngine();
        volvo.gas(1);
        saab.gas(1);
        volvo.move();
        saab.move();
        
        Assert.assertTrue(volvo.getX() != 0 && saab.getX() != 0);
    }

    @Test
    public void testTurnRight() {
        volvo.startEngine();
        saab.startEngine();
        volvo.turnRight();
        saab.turnRight();
        volvo.gas(1);
        saab.gas(1);
        volvo.move();
        saab.move();
        
        Assert.assertTrue(volvo.getY() != 0 && saab.getY() != 0);
        
    }

    @Test
    public void testTurnLeft() {
        volvo.startEngine();
        saab.startEngine();
        volvo.turnLeft();
        saab.turnLeft();
        volvo.gas(1);
        saab.gas(1);
        volvo.move();
        saab.move();
        
        Assert.assertTrue(volvo.getY() != 0 && saab.getY() != 0);
        
    }

    @Test
    public void testMoveForwardAndBack() {
        volvo.startEngine();
        saab.startEngine();
        volvo.move();
        saab.move();
        volvo.turnLeft();
        volvo.turnLeft();
        saab.turnRight();
        saab.turnRight();

        volvo.move();
        saab.move();
        
        Assert.assertTrue(volvo.getY() == 0f && saab.getY() == 0f);
    }



    
}

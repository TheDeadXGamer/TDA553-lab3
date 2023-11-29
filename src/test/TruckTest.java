import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;

import main.CarModel.Cars;
import main.CarModel.Saab95;
import main.CarModel.Scania;
import main.CarModel.TransportTruck;
import main.CarModel.Volvo240;

public class TruckTest {

    
    
    Scania myScania = new Scania(0,0);
    TransportTruck myTruck = new TransportTruck(4,0,0);
    
    @Test
    public void TrailerDriveTest() {
        myScania.startEngine();
        myScania.brake(1);
        myScania.RaiseTrailer(15);
        myScania.gas(1);
        
        Assert.assertEquals(0, myScania.getCurrentSpeed(), 0);
    }

    @Test
    public void TrailerWhileDrivingTest() {
        // You should not be able to raise the trailer while the car is moving.
        // this test is built on the fact that the car starts moving once the engine is turned on
        myScania.startEngine();
        myScania.gas(1);
        myScania.RaiseTrailer(10);
        Assert.assertEquals(0, myScania.getTrailerAngle(), 0);
    }

    @Test
    public void trailerAngleAndRaiseTrailerTest() {
        myScania.startEngine();
        myScania.brake(1);
        myScania.RaiseTrailer(18);
        
        Assert.assertEquals(18, myScania.getTrailerAngle(), 0);
    }

    @Test
    public void LowerTrailerTest() {
        float expectedDifference = 5;

        myScania.startEngine();
        myScania.brake(1);
        myScania.RaiseTrailer(18);

        float beforeLowering = myScania.getTrailerAngle(); 

        myScania.LowerTrailer(expectedDifference);

        float afterLowering = myScania.getTrailerAngle();
        float difference = beforeLowering - afterLowering;

        Assert.assertEquals(expectedDifference, difference, 0);
    }

    @Test
    public void LoadCars() {
        Saab95 saab1 = new Saab95(0,0);
        Saab95 saab2 = new Saab95(0,0);
        Volvo240 volvo1 = new Volvo240(0,0);
        Volvo240 volvo2 = new Volvo240(0,0);

        myTruck.LowerBed();


        myTruck.loadCar(saab1);
        myTruck.loadCar(volvo1);
        myTruck.loadCar(saab2);
        myTruck.loadCar(volvo2);

        ArrayList<Cars> expectedList = new ArrayList<>(Arrays.asList(saab1, volvo1, saab2, volvo2));

        Assert.assertEquals(expectedList, myTruck.getLoad());

    }

    @Test
    public void MaxNrOfCars() {
        Saab95 saab1 = new Saab95(0,0);
        Saab95 saab2 = new Saab95(0,0);
        Volvo240 volvo1 = new Volvo240(0,0);
        Volvo240 volvo2 = new Volvo240(0,0);

        TransportTruck myTruck = new TransportTruck(3,0,0);
        myTruck.LowerBed();

        myTruck.loadCar(saab1);
        myTruck.loadCar(volvo1);
        myTruck.loadCar(saab2);
        myTruck.loadCar(volvo2);

        Assert.assertFalse(myTruck.getLoad().contains(volvo2));
    }

    @Test
    public void UnloadCar() {
        Saab95 saab1 = new Saab95(0,0);
        Saab95 saab2 = new Saab95(0,0);
        Volvo240 volvo1 = new Volvo240(0,0);
        Volvo240 volvo2 = new Volvo240(0,0);

        TransportTruck myTruck = new TransportTruck(4,0,0);
        myTruck.LowerBed();
        myTruck.loadCar(saab1);
        myTruck.loadCar(volvo1);
        myTruck.loadCar(saab2);
        myTruck.loadCar(volvo2);

        myTruck.unloadCar();

        Assert.assertFalse(myTruck.getLoad().contains(volvo2));
    }

    @Test
    public void MoveLoad() {
        Saab95 saab1 = new Saab95(0,0);
        Saab95 saab2 = new Saab95(0,0);
        Volvo240 volvo1 = new Volvo240(0,0);
        Volvo240 volvo2 = new Volvo240(0,0);

        myTruck.LowerBed();


        myTruck.loadCar(saab1);
        myTruck.loadCar(volvo1);
        myTruck.loadCar(saab2);
        myTruck.loadCar(volvo2);

        myTruck.RaiseBed();
        myTruck.gas(1);
        myTruck.move();

        float loadPositionY = myTruck.getLoad().get(0).getY();

        
        // Car only moves in the Y axis so we only need to test this
        Assert.assertEquals(loadPositionY, myTruck.getY(), 0);
    }
}

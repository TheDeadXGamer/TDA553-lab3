package main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    WindowSettings windowSettings = new WindowSettings();

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Cars> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Cars car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.drawPanel.moveCar(x, y);

                if(isTouchingSurface(car)){
                    car.turnLeft();
                    car.turnLeft();
                }
                
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        float gas = ((float) amount) / 100;
        for (Cars car : cars) {
            car.gas(gas);
        }
    }

     // Calls the gas method for each car once
    void brake(int amount) {
        float gas = ((float) amount) / 100;
        for (Cars car : cars) {
            car.brake(gas);
        }
    }

    void startCars(){
        for (Cars car : cars){
            car.startEngine();
        }
    }

    void stopCars(){
        for (Cars car : cars){
            car.stopEngine();
        }
    }

    private boolean isTouchingSurface(Cars car){
        int _carWidth = 100;
        int _carHeight = 60;
        boolean _isTouchingRightSide = car.getX() > windowSettings.getWindowWidth() - _carWidth;
        boolean _isTouchingLeftSide = car.getX() < 0;
        boolean _isTouchingBottom = car.getY() > windowSettings.getWindowHeight() - _carHeight;
        boolean _isTouchingTop = car.getY() < 0;

        boolean _isTouchingSide = _isTouchingLeftSide || _isTouchingRightSide;
        boolean _isTouchingOpposites = _isTouchingBottom || _isTouchingTop;

        return (_isTouchingSide||_isTouchingOpposites) ? true : false;
    }
}

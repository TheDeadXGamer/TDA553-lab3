package main.CarModel;
import java.awt.*;

import main.Movable;

public class Cars implements Movable{

    public enum Direction {
    NORTH,
    EAST,
    WEST,
    SOUTH
    }
    
    private int carSize; //Size of car
    private int nrDoors; // Number of doors on the car
    private int enginePower; // Engine power of the car
    private float currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private Direction facingDirection;
    private CarPosition position;
    private boolean carIsOn;
    
    public Cars(int nrDoors,Color color,int enginePower,String modelName, int carSize, float x, float y){
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.carSize = carSize;
        facingDirection = Direction.EAST;
        position = new CarPosition(x,y);
        stopEngine();
    }
    
    public int getNrDoors(){
        return nrDoors;
    }
    public int getEnginePower(){
        return enginePower;
    }

    public float getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public String getModelName(){
        return modelName;
    }

    public int getCarSize() {
        return carSize;
    }

    public void setColor(Color clr){
	    color = clr;
    }

    public void startEngine(){
        carIsOn = true;
    }

    public void stopEngine(){
	    currentSpeed = 0f;
        carIsOn = false;
    }
    
    float getSpeedFactor(){
        return enginePower * 0.01f;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    private void incrementSpeed(float amount){
	    currentSpeed = Math.min(getCurrentSpeed() + getSpeedFactor() * amount,enginePower);
    }

    private void decrementSpeed(float amount){
        currentSpeed = Math.max(getCurrentSpeed() - getSpeedFactor() * amount,0);
    }

    public void gas(float amount){
        if(amount <= 1 && amount >= 0 && carIsOn) {
            incrementSpeed(amount);
        }
    }
    
    public void brake(float amount){
        if(amount <= 1 && amount >= 0 && carIsOn) {
            decrementSpeed(amount);
        }
    }

    @Override
    public void turnLeft(){
        switch (facingDirection) {
            case NORTH:
                facingDirection = Direction.WEST;
                break;
            case EAST:
                facingDirection = Direction.NORTH;
                break;
            case SOUTH:
                facingDirection = Direction.EAST;
                break;
            case WEST:
                facingDirection = Direction.SOUTH;
                break;
        }
    }

    @Override
    public void turnRight(){
        switch (facingDirection) {
            case NORTH:
                facingDirection = Direction.EAST;
                break;
            case EAST:
                facingDirection = Direction.SOUTH;
                break;
            case SOUTH:
                facingDirection = Direction.WEST;
                break;
            case WEST:
                facingDirection = Direction.NORTH;
                break;
        }
    }
    
    @Override
    public void move(){
        switch (facingDirection) {
            case NORTH:
                position.setPosition(getX(), getY() + currentSpeed);
                break;
            case WEST:
                position.setPosition(getX() - currentSpeed, getY());
                break;
            case EAST:
                position.setPosition(getX() + currentSpeed, getY());
                break;
            case SOUTH:
                position.setPosition(getX(), getY() - currentSpeed);
                break;
        }
    }

    public CarPosition getPosition(){
        return position;
    }

    void setPosition(float x, float y) {
        position.setPosition(x, y);
    }

    public float getY(){
        return position.getY();
    }

    public float getX() {
        return position.getX();
    }
}
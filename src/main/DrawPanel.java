package main;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    private final int arraySize = 3;

    // Just a single image
    private Image volvoImage;
    private Image saabImage;
    private Image scaniaImage;
    // To keep track of a singel cars position
    private Point volvoPosition = new Point(0,0);
    private Point saabPosition = new Point(0,CarController.getDistanceConstantY());
    private Point scaniaPosition = new Point(0,2*CarController.getDistanceConstantY());
    
    private ArrayList<Image> images = new ArrayList<>(arraySize);
    private ArrayList<Point> points = new ArrayList<>(arraySize);
    
    public void addAllPositions(Point...points){
        for(Point point : points){
            this.points.add(point);
        }
    }

    public void addAllImages(Image...images){
        for(Image image : images){
            this.images.add(image);
        }
    }
    
    void moveCar(int index, int x, int y){
        points.set(index,new Point(x,y));
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            volvoImage = ImageIO.read(new File("src\\pics\\Volvo240.jpg"));
            saabImage = ImageIO.read(new File("src\\pics\\Saab95.jpg"));
            scaniaImage = ImageIO.read(new File("src\\pics\\Scania.jpg"));

            addAllImages(volvoImage,saabImage,scaniaImage);
            addAllPositions(volvoPosition,saabPosition,scaniaPosition);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }//Constructor

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int k = 0; k < arraySize; k++){
            g.drawImage(images.get(k), points.get(k).x, points.get(k).y, null);
        }
    }
}

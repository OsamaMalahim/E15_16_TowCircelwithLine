package com.example.e15_16_towcircelwithline;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();

        Circle circle1 = new Circle(40,40,10);
        circle1.setFill(Color.WHITE);
        circle1.setStroke(Color.BLACK);

        Circle circle2 = new Circle(120,150,10);
        circle2.setFill(Color.WHITE);
        circle2.setStroke(Color.BLACK);

        Line line = new Line(40,40,120,150);
        int  distance = (int) findDistance(line);

        Text text = new Text(String.valueOf(distance));
        text.setX(middleLinex(line) );
        text.setY(middleLineY(line));

        pane.getChildren().addAll(circle1,circle2,line,text);

        pane.setOnMouseDragged(mouseEvent -> {
            if (circle1.contains(mouseEvent.getX(),mouseEvent.getY())){
                circle1.setCenterX(mouseEvent.getX());
                circle1.setCenterY(mouseEvent.getY());

                line.setStartX(mouseEvent.getX());
                line.setStartY(mouseEvent.getY());

                text.setText(String.valueOf(findDistance(line)));
                text.setX(middleLinex(line) );
                text.setY(middleLineY(line));

            }

        });

        Scene scene = new Scene(pane,400,300);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private double middleLineY(Line line) {
        double diffX = line.getEndX() - line.getStartX();
        double diffY = line.getEndY() - line.getStartY();
        double distance = Math.sqrt((diffY * diffX) + (diffX * diffX));
        //find angle
        double pheta = Math.asin(diffX / diffY);
        double xLength = Math.sin(pheta) * distance/2.0;
        double yLength = Math.cos(pheta) * distance/2.0;
        System.out.println("yLength = " + yLength);
        System.out.println("xLength = " + xLength);
        return yLength + line.getStartY();
    }

    private double middleLinex(Line line) {
        double diffX = line.getEndX() - line.getStartX();
        double diffY = line.getEndY() - line.getStartY();
        double distance = Math.sqrt((diffY * diffX) + (diffX * diffX));
        //find angle
        double pheta = Math.asin(diffX / diffY);
        double xLength = Math.sin(pheta) * distance/2.0;
        double yLength = Math.cos(pheta) * distance/2.0;
        System.out.println("yLength = " + yLength);
        System.out.println("xLength = " + xLength);
        return xLength + line.getStartX();
    }

    private double findDistance(Line line) {
        double diffX = line.getEndX() - line.getStartX();
        double diffY = line.getEndY() - line.getStartY();
        double distance = Math.sqrt((diffX * diffX) + (diffY * diffY));
        return distance;
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.example;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent; 
import javafx.event.EventHandler;
import javafx.geometry.*;

public class App extends Application {

    Shape inter;
    Point2D pc;
    double initX, initY, mx, my;

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Rectangle p1 = new Rectangle();
        Rectangle p2 = new Rectangle();
        p1.setX(150.0f); 
        p1.setY(75.0f); 
        p1.setWidth(300.0f); 
        p1.setHeight(150.0f);
        p1.setFill(Color.BLACK);

        p2.setX(150.0f); 
        p2.setY(75.0f); 
        p2.setWidth(300.0f); 
        p2.setHeight(150.0f);
        p2.setFill(Color.BLACK);

        p2.setFill(Color.BLUE);

        p1.setTranslateX(400);
        p1.setTranslateY(250);

        p2.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                initX = p2.getTranslateX();
                initY = p2.getTranslateX();
                pc = new Point2D(me.getSceneX(), me.getSceneY());
            }
        });

        p2.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                double dragX = me.getSceneX() - pc.getX();
                double dragY = me.getSceneY() - pc.getY();
                double newXPosition = initX + dragX;
                double newYPosition = initY + dragY;
                p2.setTranslateX(newXPosition);
                p2.setTranslateY(newYPosition);
                System.out.println("no intersection");
                if (Shape.intersect(p2, p1).getBoundsInLocal().isEmpty() == false) {
                    p1.setTranslateX(p2.getTranslateX() + mx);
                    p1.setTranslateY(p2.getTranslateY() + my);
                    System.out.println("collision");

                } else {
                    mx = p1.getTranslateX() - p2.getTranslateX();
                    my = p1.getTranslateY() - p2.getTranslateY();
                }

            }
        });
        root.getChildren().addAll(p1, p2);

        final Scene scene = new Scene(root, 1200, 850);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
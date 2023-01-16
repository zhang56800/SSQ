package com.dematic.ssq;

import java.awt.*;
import java.awt.event.MouseEvent;
 
public class Mouse {
    public static void main(String[] args) {
        int i=10000;
        try {
            Thread.sleep(i);
            Robot robot = new Robot();
            int n = 10000;
            while (n-- > 0){
                robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
package com.testPatterns;

import com.entities.tiles.Box;
import com.entities.Rectangle;

public class BuilderTest {
    public static void main(String[] args){
        testBuilder();
    }

    public static void testBuilder(){
        var boxBuilder = new Box.BoxBuilder(new Rectangle(50f,50f)).build();
        System.out.println("Rectangle: " + boxBuilder.getRectangle().toString() + ", dimension: " + boxBuilder.getDimensions()+"\n");


        var boxWithConstructor = new Box(new Rectangle(), 0.1f);
        System.out.println("Rectangle: " + boxWithConstructor.getRectangle().toString() + ", dimension: " + boxWithConstructor.getDimensions()+"\n");


        var boxWithDimension = new Box.BoxBuilder(new Rectangle(100f, 100f))
                .dimension(0.1f)
                .build();
        System.out.println("Rectangle: " + boxWithDimension.getRectangle().toString() + ", dimension: " + boxWithDimension.getDimensions()+"\n");
    }
}

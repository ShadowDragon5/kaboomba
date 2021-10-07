package com.testPatterns;

import com.entities.Box;
import com.entities.Position;

public class BuilderTest {
    public static void main(String[] args){
        testBuilder();
    }

    public static void testBuilder(){
        var boxBuilder = new Box.BoxBuilder(new Position(50f,50f)).build();
        System.out.println("Position: " + boxBuilder.getPosition().toString() + ", dimension: " + boxBuilder.getDimensions()+"\n");


        var boxWithConstructor = new Box(new Position(), 0.1f);
        System.out.println("Position: " + boxWithConstructor.getPosition().toString() + ", dimension: " + boxWithConstructor.getDimensions()+"\n");


        var boxWithDimension = new Box.BoxBuilder(new Position(100f, 100f))
                .dimension(0.1f)
                .build();
        System.out.println("Position: " + boxWithDimension.getPosition().toString() + ", dimension: " + boxWithDimension.getDimensions()+"\n");
    }
}

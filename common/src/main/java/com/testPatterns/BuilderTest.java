package com.testPatterns;

import com.entities.Box;
import com.entities.Position;

public class BuilderTest {
    public static void main(String[] args){
        testBuilder();
    }

    public static void testBuilder(){
        var box = new Box.BoxBuilder(new Position()).build();
        var boxWithDimension = new Box.BoxBuilder(new Position()).dimension((float) 0.1).build();
    }
}

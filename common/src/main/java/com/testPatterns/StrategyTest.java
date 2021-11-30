package com.testPatterns;

import com.entities.tiles.Box;
import com.entities.Rectangle;

public class StrategyTest {
    public static void main(String[] args){
        testStrategy();
    }

    public static void testStrategy(){
        for (int i = 0; i < 15; i++) {
            var box = new Box.BoxBuilder(new Rectangle()).build();
            box.explode();
        }
    }
}

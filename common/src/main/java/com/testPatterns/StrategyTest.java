package com.testPatterns;

import com.entities.Box;
import com.entities.Position;

public class StrategyTest {
    public static void main(String[] args){
        testStrategy();
    }

    public static void testStrategy(){
        for (int i = 0; i < 15; i++) {
            var box = new Box.BoxBuilder(new Position()).build();
            box.explode();
        }
    }
}

package com;

import com.entities.GameMap;
import com.entities.GameObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gsonParsers.CustomJsonAdapter;

public class Test {
    public static void main(String[] args){

        //Bad
        System.out.println(0.05f - 0.001f);
        System.out.println(0.9f - 0.1f);

        System.out.println("==================");

        //Good
        System.out.println((0.05f*1000 - 0.001f*1000)/1000);
        System.out.println((0.9f*1000-0.1f*1000)/1000);

        System.out.println(1.5f * 0.1f);
        System.out.println(0.1f/2);

    }
}

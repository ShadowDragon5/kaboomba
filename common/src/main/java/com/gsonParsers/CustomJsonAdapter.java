package com.gsonParsers;


import com.google.gson.*;

import java.lang.reflect.Type;

public class CustomJsonAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private String classPath;

    public CustomJsonAdapter(String classPath) {
        this.classPath = classPath;
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("classPath", new JsonPrimitive(src.getClass().getName()));
        result.add("properties", context.serialize(src, src.getClass()));

        return result;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String packagePath = jsonObject.get("classPath").getAsString();
        JsonElement element = jsonObject.get("properties");
        try {
            return context.deserialize(element, Class.forName(packagePath));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + packagePath, cnfe);
        }
    }


//    public String getClassPathByType(String type) {
//        switch (type){
//            case "BlueBomb":
//            case "BlueBombExplosion":
//                return "com.entities.bomb.";
//        }
//    }
}

package com.gsonParsers;


import com.google.gson.*;

import java.lang.reflect.Type;

public class CustomJsonAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private String classPath;

    public CustomJsonAdapter(String classPath) {
        this.classPath = classPath;
    }

    public CustomJsonAdapter() {
         this.classPath = "com.entities.";
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));

        return result;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            return context.deserialize(element, Class.forName(classPath + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }
}

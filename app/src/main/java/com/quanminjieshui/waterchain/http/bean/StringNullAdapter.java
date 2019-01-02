package com.quanminjieshui.waterchain.http.bean;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class StringNullAdapter implements JsonDeserializer<BaseEntity> {

    @Override
    public BaseEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
        	JsonObject obj = json.getAsJsonObject();
        	JsonElement e=obj.get("data");
        	if(!e.isJsonObject()&&"".equals(e.getAsString()))
            	obj.remove("data");
        	return new Gson().fromJson(obj, typeOfT);
        } catch (JsonParseException ignore) {
            return null;
        }
    }
}

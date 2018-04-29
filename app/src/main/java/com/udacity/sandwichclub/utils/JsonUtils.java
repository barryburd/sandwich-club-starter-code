package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject sandwichJSONObject = new JSONObject(json);
            JSONObject nameJSONObject = sandwichJSONObject.getJSONObject(NAME);
            sandwich.setMainName(nameJSONObject.getString(MAIN_NAME));
            sandwich.setAlsoKnownAs(getStrings(nameJSONObject, ALSO_KNOWN_AS));
            sandwich.setPlaceOfOrigin(sandwichJSONObject.getString(PLACE_OF_ORIGIN));
            sandwich.setDescription(sandwichJSONObject.getString(DESCRIPTION));
            sandwich.setImage(sandwichJSONObject.getString(IMAGE));
            sandwich.setIngredients(getStrings(sandwichJSONObject, INGREDIENTS));

            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> getStrings(JSONObject jsonObject, String name) {
        List<String> list = new ArrayList<>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(name);
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            // List will be empty
        }

        return list;
    }
}

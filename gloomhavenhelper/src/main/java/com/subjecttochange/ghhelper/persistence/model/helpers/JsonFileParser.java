package com.subjecttochange.ghhelper.persistence.model.helpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonFileParser {


    private JsonObject json;


    /**
     * defaults to monsterseed file
     */
    public JsonFileParser() {
        this("monsterseed.json");
    }


    /**
     * opens given full filename
     * @param filename file name requiring file extension
     */
    public JsonFileParser(String filename) {
        json = getFileContents(filename);

    }

    public List<Monster> getMonsters() {
        List<Monster> monsters = new ArrayList<>();
        //TODO Pull list of monster names from json to loop through
        String monsterName = "Ancient Artillery";

        JsonArray jsonLevelsArray = getLevels(monsterName);
        for (int i = 0; i < jsonLevelsArray.size(); i++) {
            monsters.add(Monster.create(jsonLevelsArray.get(i).getAsJsonObject(), monsterName));
        }

        return monsters;
    }

    private JsonArray getLevels(String monsterName) {
        return json.getAsJsonObject("monsters")
                .getAsJsonObject(monsterName)
                .getAsJsonArray("level");
    }


    /**
     * for testing, will be deleted.
     * @param args
     */
    public static void main(String[] args) {
        //TODO Delete main
        JsonFileParser test = new JsonFileParser();

        test.getMonsters();
    }

    private static JsonObject getFileContents(String filename) {
        StringBuilder contents = new StringBuilder();
        try (Scanner input = new Scanner(ResourceUtils.getFile("classpath:"+filename))) {
            while (input.hasNext()) {
                contents.append(input.nextLine());
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return new JsonParser().parse(contents.toString()).getAsJsonObject();
    }


}

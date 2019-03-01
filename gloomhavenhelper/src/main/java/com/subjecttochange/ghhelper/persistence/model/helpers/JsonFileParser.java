package com.subjecttochange.ghhelper.persistence.model.helpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import org.springframework.util.ResourceUtils;

import java.util.*;

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
        for (Map.Entry<String, JsonElement> monsterName : getMonsterNameSet()) {
            JsonArray jsonLevelsArray = getLevels(monsterName.getKey());
            //TODO line below grabs only level 0. replace with commented out loop for all levels
            monsters.add(Monster.create(jsonLevelsArray.get(0).getAsJsonObject(), monsterName.getKey()));

//            for (int i = 0; i < jsonLevelsArray.size(); i++) {
//                monsters.add(Monster.create(jsonLevelsArray.get(i).getAsJsonObject(), monsterName.getKey()));
//            }
        }
        return monsters;
    }

    private Set<Map.Entry<String, JsonElement>> getMonsterNameSet() {
        return json.getAsJsonObject("monsters").entrySet();
    }

    private JsonArray getLevels(String monsterName) {
        return json.getAsJsonObject("monsters")
                .getAsJsonObject(monsterName)
                .getAsJsonArray("level");
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
package com.subjecttochange.ghhelper.persistence.model.helpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Action;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.ActionDeck;
import lombok.ToString;
import org.springframework.util.ResourceUtils;

import java.util.*;

@ToString
public class JsonFileParser {

    private final JsonObject json;

    /**
     * defaults to monsterseed file
     */
    public JsonFileParser() {
        this("monsterseed.json");
    }


    /**
     * opens given full filename
     *
     * @param filename file name requiring file extension
     */
    public JsonFileParser(String filename) {
        json = getFileContents(filename);
    }

    public List<Monster> getMonsters() {
        List<Monster> monsters = new ArrayList<>();
        for (Map.Entry<String, JsonElement> monsterName : getMonsterNameSet()) {
            JsonArray jsonLevels = getInfo(monsterName.getKey(), "level");

            for (int i = 0; i < jsonLevels.size(); i++) {
                monsters.add(Monster.create(jsonLevels.get(i).getAsJsonObject(), monsterName.getKey()));
            }
        }
        return monsters;
    }

    public List<ActionDeck> getDecks() {
        List<ActionDeck> decks = new ArrayList<>();

        for (Map.Entry<String, JsonElement> monsterName : getMonsterNameSet()) {
            List<Action> monsterActions = new ArrayList<>();

            JsonArray jsonActions = getInfo(monsterName.getKey(), "actions");

            for(JsonElement actionInfo : jsonActions) {
                JsonArray action = actionInfo.getAsJsonArray();

                boolean shuffleable = action.get(0).getAsBoolean();
                int initiative = action.get(1).getAsInt();

                List<String> cardContent = new ArrayList<>();
                for (int i = 2; i < action.size(); i++) {
                    cardContent.add(action.get(i).getAsString());
                }
                monsterActions.add(Action.create(shuffleable, initiative, cardContent));
            }
            decks.add(ActionDeck.createDeck(monsterName.getKey(), monsterActions));
        }

        return decks;
    }

    private Set<Map.Entry<String, JsonElement>> getMonsterNameSet() {
        return json.getAsJsonObject("monsters").entrySet();
    }

    private JsonArray getInfo(String monsterName, String info) {
        return json.getAsJsonObject("monsters")
                .getAsJsonObject(monsterName)
                .getAsJsonArray(info);
    }


    private static JsonObject getFileContents(String filename) {
        StringBuilder contents = new StringBuilder();
        try (Scanner input = new Scanner(ResourceUtils.getFile("classpath:" + filename))) {
            while (input.hasNext()) {
                contents.append(input.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonParser().parse(contents.toString()).getAsJsonObject();
    }


}

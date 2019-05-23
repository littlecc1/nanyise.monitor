package top.wello.health.service;

import org.springframework.stereotype.Component;
import top.wello.health.Request.FoodSuggestResult;
import top.wello.health.Request.SuggestRequest;
import top.wello.health.dao.FoodDO;
import top.wello.health.dao.FoodGroupDO;
import top.wello.health.util.FoodParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Component
public class FoodSuggestService {

    FoodGroupDO breakfast;
    FoodGroupDO breakfastDrink;
    FoodGroupDO condiment;
    FoodGroupDO drink;
    FoodGroupDO fish;
    FoodGroupDO meat;
    FoodGroupDO oil;
    FoodGroupDO staple;
    FoodGroupDO vegetable;
    FoodDO riceDTO;
    FoodDO noodleDTO;
    FoodDO oilDTO;

    private static final String pre = "foods/";

    {
        try {
            breakfast = new FoodGroupDO("breakfast", FoodGroupDO.TYPE_BREAKFAST, FoodParser.parseFoods(pre + "breakfast.txt"));
            breakfastDrink = new FoodGroupDO("breakfastDrink", FoodGroupDO.TYPE_BREAKFAST, FoodParser.parseFoods(pre + "breakfast_drink.txt"));
            condiment = new FoodGroupDO("condiment", FoodGroupDO.TYPE_CONDIMENT, FoodParser.parseFoods(pre + "condiment.txt"));
            drink = new FoodGroupDO("drink", FoodGroupDO.TYPE_DRINK, FoodParser.parseFoods(pre + "drink.txt"));
            fish = new FoodGroupDO("fish", FoodGroupDO.TYPE_FISH, FoodParser.parseFoods(pre + "fish.txt"));
            meat = new FoodGroupDO("meat", FoodGroupDO.TYPE_MEAT, FoodParser.parseFoods(pre + "meat.txt"));
            oil = new FoodGroupDO("oil", FoodGroupDO.TYPE_OIL, FoodParser.parseFoods(pre + "oil.txt"));
            staple = new FoodGroupDO("staple", FoodGroupDO.TYPE_STAPLE, FoodParser.parseFoods(pre + "staple.txt"));
            vegetable = new FoodGroupDO("vegetable", FoodGroupDO.TYPE_VEGETABLE, FoodParser.parseFoods(pre + "vegetable.txt"));
            riceDTO = staple.get(150818220000055L);
            noodleDTO = staple.get(150818220000011L);
            oilDTO = oil.get(150821213400660L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public HashMap<String, List<FoodSuggestResult>> suggest(int heat) {
        SuggestRequest suggestRequest = new SuggestRequest();
        suggestRequest.setHeat(heat);
        return suggest(suggestRequest);
    }

    public HashMap<String, List<FoodSuggestResult>> suggest(SuggestRequest request) {
        int heat = request.getHeat();
        if (request.getHeat() > 1500 && request.getHeat() < 6000) {
            HashMap<String, List<FoodSuggestResult>> res = new HashMap<>();
            res.put("breakfast", getBreakfast((int) (heat * 0.25)));
            res.put("lunch", getLunch((int) (heat * 0.4)));
            res.put("dinner", getDinner((int) (heat * 0.35)));
            return res;
        } else {
            return null;
        }
    }

    private List<FoodSuggestResult> getBreakfast(int heat) {
        List<FoodSuggestResult> result = new ArrayList<>();
        FoodDO drink = breakfastDrink.random();
        heat -= (int) drink.getHeat();
        result.add(FoodSuggestResult.generate(drink));
        while (heat > 200) {
            FoodDO bre = breakfast.random();
            if (bre == null) {
                break;
            }
            int h = (int) bre.getHeat();
            if (heat - h >= -200) {
                heat -= h;
                result.add(FoodSuggestResult.generate(bre));
            } else {
                break;
            }
        }
        return result;
    }

    private List<FoodSuggestResult> getLunch(int heat) {
        boolean isRice = new Random().nextBoolean();
        List<FoodSuggestResult> result = new ArrayList<>();
        if (isRice) {
            result.add(FoodSuggestResult.generate(riceDTO));
            heat -= riceDTO.getHeat();
            do {
                List<FoodDO> foods = new ArrayList<>();
                FoodSuggestResult r = getMeal();
                if (heat - r.getHeat() < -200) {
                    break;
                }
                result.add(r);
                heat -= r.getHeat();
            } while (heat > 200);
        } else {
            List<FoodDO> noo = new ArrayList<>();
            noo.add(noodleDTO);
            noo.add(oilDTO);
            noo.add(meat.random());
            noo.add(vegetable.random());
            FoodSuggestResult re = FoodSuggestResult.generate(noo);
            result.add(re);
            heat -= re.getHeat();
            while (heat > 400) {
                FoodSuggestResult meal = getMeal();
                if (heat - meal.getHeat() < -200) {
                    break;
                }
                result.add(meal);
                heat -= meal.getHeat();
            }
        }
        return result;
    }

    private FoodSuggestResult getMeal() {
        List<FoodDO> foods = new ArrayList<>();
        foods.add(oilDTO);
        foods.add(vegetable.random());
        foods.add(meat.random());
        FoodSuggestResult result = FoodSuggestResult.generate(foods);
        return result;
    }

    private List<FoodSuggestResult> getDinner(int heat) {
        return getLunch(heat);
    }

}

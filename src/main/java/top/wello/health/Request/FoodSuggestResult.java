package top.wello.health.Request;

import top.wello.health.dao.FoodDO;

import java.util.List;

public class FoodSuggestResult {

    private String name;
    private int heat;

    public FoodSuggestResult(String name, int heat) {
        this.name = name;
        this.heat = heat;
    }

    public static FoodSuggestResult generate(List<FoodDO> foods) {
        if (foods == null || foods.size() == 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        int heat = 0;
        for (FoodDO food: foods) {
            builder.append(food.getName() + food.getWeight() + "g  ");
            heat += (int) food.getHeat();
        }
        return new FoodSuggestResult(builder.toString(), heat);
    }

    public static FoodSuggestResult generate(FoodDO food) {
        int heat = (int) food.getHeat();;
        String name = food.getName() + food.getWeight() + "g  ";
        return new FoodSuggestResult(name, heat);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    @Override
    public String toString() {
        return name + "  :" + heat + "kcal";
    }
}

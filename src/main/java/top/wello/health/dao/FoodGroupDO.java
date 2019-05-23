package top.wello.health.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FoodGroupDO extends BaseDO{

    public static final int TYPE_BREAKFAST = 1;
    public static final int TYPE_DRINK = 2;
    public static final int TYPE_OIL = 3;
    public static final int TYPE_CONDIMENT = 4;
    public static final int TYPE_STAPLE = 5;
    public static final int TYPE_VEGETABLE = 6;
    public static final int TYPE_MEAT = 7;
    public static final int TYPE_FISH = 8;


    private String typeName;
    private int type;
    private List<FoodDO> foods;
    private Set<Integer> used = new HashSet<>();
    Random random = new Random();

    public FoodGroupDO(String typeName, int type, List<FoodDO> foods) {
        this.typeName = typeName;
        this.type = type;
        this.foods = foods;
    }

    public FoodDO get(long id) {
        for (FoodDO food: foods) {
            if (food.getId() == id) {
                return food;
            }
        }
        return null;
    }

    public FoodDO random() {
        int max = foods.size() - 1;
        int min = 0;
        int idx;
//        if (foods.size() == used.size()) {
//            return null;
//        }
//        do {
        idx = random.nextInt(max) % (max - min + 1) + min;
//        } while (used.contains(idx));
        used.add(idx);
        return foods.get(idx);
    }

    @Override
    public String toString() {
        return typeName + " " + (foods == null ? 0 : foods.size()) + " foods";
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<FoodDO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodDO> foods) {
        this.foods = foods;
    }
}

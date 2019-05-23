package top.wello.health.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodDAO {

    FoodDTO getFood(long id);
    List<FoodDTO> getFoodsByType(long type);
    List<FoodDTO> getAllFoods();

    void addFood(FoodDTO food);
    void deleteFood(FoodDTO food);
    void deleteFoodByType(long type);

    FoodDirDTO getFoodDir(long id);
    List<FoodDirDTO> getAllFoodDir();

    void addFoodDir(FoodDirDTO foodDir);
    void deleteFoodDir(FoodDirDTO foodDir);

}

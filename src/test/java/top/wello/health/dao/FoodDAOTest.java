package top.wello.health.dao;

import com.alibaba.fastjson.JSONArray;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodDAOTest {

    @Autowired
    FoodDAO foodDAO;

    @Test
    public void testAddFood() {
//        FoodDTO food = new FoodDTO(1, "100", 0, 0, "protein", "fat", "d", 100, "test");
//        foodDAO.deleteFood(food);
//        foodDAO.addFood(food);
//        List<FoodDTO> foods = foodDAO.getAllFoods();
//        System.out.println(foodDAO.getAllFoods());
//        FoodDirDTO foodDir = new FoodDirDTO(1, "h", "t");
//        foodDAO.addFoodDir(foodDir);
//        System.out.println(foodDAO.getAllFoodDir());
        File dir = new File("/Users/maweihao/毕设/data");
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".txt")) {
                System.out.println(file.getName());
//                addFood(file);
            }
        }

    }

//    @Test
    public void addFood(File file) {

//        String food = "/Users/maweihao/毕设/data/food_dir.txt";
//        File file = new File(food);
        StringBuilder builder = new StringBuilder();
        String content = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null) {
                builder.append(str);
            }
            content = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<FoodDTO> array = JSONArray.parseArray(content, FoodDTO.class);
        for (FoodDTO foodDTO : array) {
            System.out.println(foodDTO);
            foodDAO.addFood(foodDTO);
        }
        Assert.assertNotNull(array);
    }

}
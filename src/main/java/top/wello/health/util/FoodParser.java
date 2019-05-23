package top.wello.health.util;

import com.alibaba.fastjson.JSONArray;
import org.springframework.core.io.ClassPathResource;
import top.wello.health.dao.FoodDO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;

public class FoodParser {

    public static final int BUFFER_LENGTH = 512;

    public static List<FoodDO> parseFoods(File file) {
        List<FoodDO> list = null;
        StringBuilder builder = new StringBuilder();
        try {
//            File file = new File(path);
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null) {
                builder.append(str);
            }
            list = JSONArray.parseArray(builder.toString(), FoodDO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<FoodDO> parseFoods(String path) {
        List<FoodDO> list = null;
        StringBuilder builder = new StringBuilder();
        try {
            ClassPathResource resource = new ClassPathResource(path);
            InputStream in = resource.getInputStream();
            byte[] buffer = new byte[BUFFER_LENGTH];
            int len;
            while ((len = in.read(buffer)) != -1) {//-1表示读取结束
                builder.append(new String(buffer, 0, len));
            }
            list = JSONArray.parseArray(builder.toString(), FoodDO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

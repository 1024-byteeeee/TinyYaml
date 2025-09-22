package top.byteeeee.yaml;

import top.byteeeee.yaml.exception.YamlParseException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main (String[]args){
        String yamlContent =
            "name: " + "1024_byteeeee\n" +
            "age: 80\n" +
            "hobbies:\n" +
            "  - Minecraft\n" +
            "  - CS\n" +
            "address:\n" +
            "  city: Night City\n" +
            "  zip: 2077\n" +
            "favoriteFoods:\n" +
            "  - Pizza\n" +
            "  - Sushi\n" +
            "  - Burger\n" +
            "emptyList: []\n" +
            "mixedList:\n" +
            "  - Apple\n" +
            "  - 123\n" +
            "  - true";

        try {
            // 解析YAML
            Map<String, Object> data = TinyYamlParser.parse(yamlContent);
            System.out.println("Parsed data: " + data);

            // 获取嵌套值
            String city = (String) TinyYamlParser.getNestedValue(data, "address.city");
            System.out.println("City: " + city);

            // 测试正常情况
            List<String> hobbies = TinyYamlParser.getNestedStringList(data, "hobbies");
            System.out.println("Hobbies: " + hobbies);

            // 测试另一个列表
            List<String> favoriteFoods = TinyYamlParser.getNestedStringList(data, "favoriteFoods");
            System.out.println("Favorite Foods: " + favoriteFoods);

            // 测试空列表
            List<String> emptyList = TinyYamlParser.getNestedStringList(data, "emptyList");
            System.out.println("Empty List: " + emptyList);

            // 测试混合类型列表
            List<String> mixedList = TinyYamlParser.getNestedStringList(data, "mixedList");
            System.out.println("Mixed List (only strings): " + mixedList);

            // 测试不存在的路径
            List<String> nonExistent = TinyYamlParser.getNestedStringList(data, "nonexistent.path");
            System.out.println("Non-existent path: " + nonExistent);

            // 测试非列表路径
            List<String> notAList = TinyYamlParser.getNestedStringList(data, "name");
            System.out.println("Not a list: " + notAList);

            // 生成YAML
            String generatedYaml = TinyYamlParser.dump(data);
            System.out.println("\nGenerated YAML:\n" + generatedYaml);

        } catch (IOException | YamlParseException ignored) {}
    }
}

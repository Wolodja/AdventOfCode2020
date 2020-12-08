package day7;

import java.util.*;

public class Task2 {

    public void countAllBagsInside(){
        BagsRulesReader bagsRulesReader = new BagsRulesReader();
        List<String> allBagsRules = bagsRulesReader.readBagsRules("day7.txt");
        Map<String, Map<String, Integer>> rulesMap = transformRules(allBagsRules);

        Map<String, Integer> allColors = findAllContainingBaggs(rulesMap, "shiny gold");
        Map<String, Integer> newColors = new HashMap<>(allColors);
        while(!newColors.isEmpty()){
            Map<String, Integer> newColorsIteration = new HashMap<>(newColors);
            newColors.keySet().removeAll(newColors.keySet());
            newColorsIteration.forEach((key,value) -> {
                Map<String, Integer> foundColors = findAllContainingBaggs(rulesMap, key);
                foundColors.forEach((cc, vv) ->{
                    if(!allColors.keySet().contains(cc)){
                        newColors.put(cc, vv);
                        allColors.put(cc, vv);
                    }
                });
            });

        }
        System.out.println(allColors.size());
    }

    public static Map<String, Map<String, Integer>> transformRules(List<String> allBagsRules) {
        Map<String, Map<String, Integer>> rulesMap = new HashMap<>();
        allBagsRules.forEach(rule -> {
            String[] ruleWords = rule.split(" bags contain");
            String key = ruleWords[0];
            Map<String, Integer> values = new HashMap<>();
            String[] valuesRules = ruleWords[1].replaceAll("\\.", "").split(",");
            for(int i =0; i<valuesRules.length; i++){
                String[] words = valuesRules[i].split(" ");
                if(!words[1].equals("no")){
                    String valueKey = words[2] + " " + words[3];
                    String valueValue = words[1];
                    values.put(valueKey, Integer.valueOf(valueValue));
                }
            }
            rulesMap.put(key, values);
        });
        return rulesMap;
    }


    private Map<String, Integer> findAllContainingBaggs(Map<String, Map<String, Integer>> rulesMap, String shiny_gold) {
        Map<String, Integer> colors = new HashMap<>();
        rulesMap.forEach((key,value) -> {
            if(key.equals(shiny_gold)){
                colors.putAll(value);
            }
        });
        return colors;
    }

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.countAllBagsInside();
    }
}

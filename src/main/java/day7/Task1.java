package day7;

import java.util.*;

public class Task1 {

    public void countAvaliableOptions(){
        BagsRulesReader bagsRulesReader = new BagsRulesReader();
        List<String> allBagsRules = bagsRulesReader.readBagsRules("day7.txt");
        Map<String, List<String>> rulesMap = transformRules(allBagsRules);
        Set<String> allColors = findAllContainingBaggs(rulesMap, "shiny gold");
        Set<String> newColors = new HashSet<>(allColors);
        while(!newColors.isEmpty()){
            Set<String> newColorsIteration = new HashSet<>(newColors);
            newColors.removeAll(newColors);
            newColorsIteration.forEach(c -> {
                Set<String> foundColors = findAllContainingBaggs(rulesMap, c);
                foundColors.forEach(cc ->{
                    if(!allColors.contains(cc)){
                        newColors.add(cc);
                        allColors.add(cc);
                    }
                });
            });

        }
        System.out.println(allColors.size());
    }

    private Map<String, List<String>> transformRules(List<String> allBagsRules) {
        Map<String, List<String>> rulesMap = new HashMap<>();
        allBagsRules.forEach(rule -> {
            String[] ruleWords = rule.split(" bags contain");
            String key = ruleWords[0];
            List<String> values = new ArrayList<>();
            String[] valuesRules = ruleWords[1].replaceAll("\\.", "").split(",");
            for(int i =0; i<valuesRules.length; i++){
                String[] words = valuesRules[i].split(" ");
                if(words[1] != "no"){
                    String value = words[2] + " " + words[3];
                    values.add(value);
                }
            }
            rulesMap.put(key, values);
        });
        return rulesMap;
    }

    private Set<String> findAllContainingBaggs(Map<String, List<String>> rulesMap, String shiny_gold) {
        Set<String> colors = new HashSet<>();
        rulesMap.forEach((key,value) -> {
            if(value.contains(shiny_gold)){
                colors.add(key);
            }
        });
        return colors;
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.countAvaliableOptions();
    }

}

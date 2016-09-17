package project.service.applied;

import com.fasterxml.jackson.databind.ObjectMapper;
import project.model.Formula;
import project.model.Ingredient;
import project.model.Product;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Green-L on 13.09.2016.
 */
public class FormulaTree {

    private static ObjectMapper mapper = new ObjectMapper();

    private List<Formula> formulas = null;
    private List<Product> products = new ArrayList<>();
    private Map<Integer, Object> tree = new HashMap<>();

    public FormulaTree(String fileName) {
        // Get info from json
        try {
            formulas = mapper.readValue(new File(fileName),
                    mapper.getTypeFactory().constructCollectionType(List.class, Formula.class));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Sort products by frequency
        Map<String, Integer> productFrequency = new HashMap<>();
        for (int i = 0; i < formulas.size(); i++) {
            for (Ingredient elem: formulas.get(i).getIngredients()) {
                String prod = elem.getName();
                Integer oldCount = productFrequency.get(prod);
                int count = oldCount == null? 1: oldCount + 1;
                productFrequency.put(prod, count);
            }
        }
        List<String> productSorted = productFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        Map<String, Integer> productId = new HashMap<>();
        for (int i = 0; i < productSorted.size(); i++) {
            products.add(new Product(i, productSorted.get(i)));
            productId.put(productSorted.get(i), i);
        }

        for (int i = 0; i < formulas.size(); i++) {
            Map<Integer, Object> currentNode = tree;
            for (Integer elem: getProductIdList(formulas.get(i), productId)) {
                if (currentNode.get(elem) == null) {
                    currentNode.put(elem, new HashMap<Integer, Object>());
                }
                currentNode = (Map<Integer, Object>) currentNode.get(elem);
            }
            if (currentNode.get(null) == null) {
                currentNode.put(null, new ArrayList<>(Arrays.asList(i)));
            } else {
                ((List)currentNode.get(null)).add(i);
            }
        }
    }

    public List<Formula> getFormulas(List<Integer> products) {
        List<Formula> result = new ArrayList<>();
        getFormulas(products, tree, result);
        return result;
    }

    public void getFormulas(List<Integer> products, Map<Integer, Object> node, List<Formula> result) {
        List<Integer> data = (List<Integer>) node.get(null);
        if (data != null) {
            for (Integer index: data) {
                result.add(formulas.get(index));
            }
        }
        if (products == null)
            return;
        for (int i = 0; i < products.size(); i++) {
            Map<Integer, Object> elem = (Map<Integer, Object>) node.get(products.get(i));
            if (elem == null)
                continue;
            if (i == products.size() - 1)
                getFormulas(null, elem, result);
            else
                getFormulas(products.subList(i + 1, products.size()), elem, result);
        }
    }

    private List<Integer> getProductIdList(Formula formula, Map<String, Integer> productId) {
        List<Integer> result = new ArrayList<>();
        for (Ingredient elem: formula.getIngredients()) {
            result.add(productId.get(elem.getName()));
        }
        Collections.sort(result);
        return result;
    }

    public List<Product> getProducts() {
        return products;
    }
}
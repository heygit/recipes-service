package project.service.applied;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
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

    // TODO: REPLACE ID WITH OBJECTS

    private List<Formula> formulas;
    private List<Product> products;
    private Map<Integer, Object> tree = new HashMap<>();
    private Map<String, List<Formula>> categories = new HashMap<>();
    private List<String> titles;
    private List<String> prepositions;

    public FormulaTree(String fileName, List<String> prepositions) {
        this.prepositions = prepositions;

        // Get info from json
        try {
            formulas = mapper.readValue(new File(fileName),
                    mapper.getTypeFactory().constructCollectionType(List.class, Formula.class));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Fill in titles
        titles = new ArrayList<>(formulas.size());
        formulas.forEach(elem -> titles.add(elem.getTitle().toLowerCase()));

        // Fill in categories
        formulas.forEach(elem -> {
            String category = elem.getCategory();
            List<Formula> recipes = categories.get(category);
            if (recipes == null) {
                List<Formula> list = new ArrayList<>();
                list.add(elem);
                categories.put(category, list);
            } else {
                recipes.add(elem);
            }
        });

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
        products = new ArrayList<>(formulas.size());
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

    /**
     * Search by part of title
     * @param name search values comma separated
     * @return
     */
    public List<Formula> search(String name) {
        List<Formula> result = new ArrayList<>();
        List<String> searchValues = getSearchValues(name);
        for (int i = 0; i < titles.size(); i++) {
            boolean found = false;
            for (String search: searchValues) {
                found = false;
                for (String elem: titles.get(i).split(" ")) {
                    if (StringUtils.getLevenshteinDistance(search, elem) > 2)
                        continue;
                    found = true;
                    break;
                }
                if (!found)
                    break;
            }
            if (found)
                result.add(formulas.get(i));
        }
        return result;
    }

    /**
     * Make search value from request param
     * @param name
     * @return
     */
    private List<String> getSearchValues(String name) {
        List<String> result = new ArrayList<>();
        for (String elem: name.split(",|-")) {
            if (prepositions.contains(elem))
                continue;
            result.add(elem.toLowerCase());
        }
        return result;
    }


    /**
     * Get formulas by products
     * @param prods - products separated by comma
     * @return
     */
    public List<Formula> getFormulas(String prods) {
        String[] array = prods.split(",");
        List<Integer> products = new LinkedList<>();
        for (String elem: array)
            products.add(Integer.valueOf(elem));
        Collections.sort(products);
        List<Formula> result = new ArrayList<>();
        getFormulas(products, tree, result);
        return result;
    }

    private void getFormulas(List<Integer> products, Map<Integer, Object> node, List<Formula> result) {
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

    /**
     * Gte formulas by category paged
     * @param category
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public List<Formula> getFormulasPaged(String category, int pageSize, int pageNumber) {
        List<Formula> data = categories.get(category);
        int offset = (pageSize - 1) * pageNumber;
        if (offset >= data.size())
            return null;
        int limit = Math.min(offset + pageSize, data.size());
        return data.subList(offset, limit);
    }


    public List<Product> getProducts() {
        return products;
    }

    private List<Integer> getProductIdList(Formula formula, Map<String, Integer> productId) {
        List<Integer> result = new ArrayList<>();
        for (Ingredient elem: formula.getIngredients()) {
            result.add(productId.get(elem.getName()));
        }
        Collections.sort(result);
        return result;
    }

}
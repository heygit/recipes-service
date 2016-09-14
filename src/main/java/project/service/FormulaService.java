package project.service;

import project.model.Formula;
import project.model.Product;

import java.util.List;

/**
 * Created by Green-L on 13.09.2016.
 */
public class FormulaService {

    private static final String FILE_NAME_EN = "enformulas.json";
    private static final String FILE_NAME_RU = "ruformulas.json";

    private static FormulaTree enFormulas;
    private static FormulaTree ruFormulas;

    public static void init() {
        enFormulas = new FormulaTree(FILE_NAME_EN);
//        ruFormulas = new FormulaTree(FILE_NAME_RU);
    }

    public static List<Formula> getEnFormulas(List<Integer> products) {
        return enFormulas.getFormulas(products);
    }

    public static List<Formula> getRuFormulas(List<Integer> products) {
        return ruFormulas.getFormulas(products);
    }

    public static List<Product> getEnProducts() {
        return enFormulas.getProducts();
    }

    public static List<Product> getRuProducts() {
        return ruFormulas.getProducts();
    }
}

package project.service.applied;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.model.Formula;
import project.model.Product;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Green-L on 13.09.2016.
 */
public class FormulaService {

    private static Logger log = LoggerFactory.getLogger(FormulaService.class.getName());

    private static final String FILE_NAME_EN = "enformulas.json";
    private static final String FILE_NAME_RU = "ruformulas.json";

    private static final List<String> PREPOSITIONS_RU = Arrays.asList("c", "без", "в", "к", "у",
            "на", "о", "об", "под");
    private static final List<String> PREPOSITIONS_EN = Arrays.asList("in", "of", "out", "a",
            "an", "the", "on");

    private static final int PAGE_SIZE = 30;

    private static FormulaTree enFormulas;
    private static FormulaTree ruFormulas;

    public static void init() {
        log.info("English recipes initialization started");
        enFormulas = new FormulaTree(FILE_NAME_EN, PREPOSITIONS_EN);
        log.info("English recipes initialization finished");

        log.info("Russian recipes initialization started");
        ruFormulas = new FormulaTree(FILE_NAME_RU, PREPOSITIONS_RU);
        log.info("Russian recipes initialization finished");
    }

    public static List<Formula> searchEnFormulas(String name) {
        return enFormulas.search(name);
    }

    public static List<Formula> searchRuFormulas(String name) {
        return ruFormulas.search(name);
    }

    public static List<Formula> getEnFormulas(String products) {
        return enFormulas.getFormulas(products);
    }

    public static List<Formula> getRuFormulas(String products) {
        return ruFormulas.getFormulas(products);
    }

    public static List<Formula> getEnFormulasPaged(String category, int pageNumber) {
        return enFormulas.getFormulasPaged(category, PAGE_SIZE, pageNumber);
    }

    public static List<Formula> getRuFormulasPaged(String category, int pageNumber) {
        return enFormulas.getFormulasPaged(category, PAGE_SIZE, pageNumber);
    }

    public static List<Product> getEnProducts() {
        return enFormulas.getProducts();
    }

    public static List<Product> getRuProducts() {
        return ruFormulas.getProducts();
    }
}

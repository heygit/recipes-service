package project.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.model.Formula;
import project.service.applied.FormulaService;
import project.service.utils.MemoryUsageService;

import java.util.List;


@Controller
@RequestMapping("")
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Value("${adminKey}")
    private String adminKey;

    @RequestMapping(value = "retrieve/ru", method = RequestMethod.GET)
    @ResponseBody
    public Object retrieveRu(@RequestParam(value = "products") String products) {
        return FormulaService.getRuFormulas(products);
    }

    @RequestMapping(value = "retrieve/en", method = RequestMethod.GET)
    @ResponseBody
    public Object retrieveEn(@RequestParam(value = "products") String products) {
        return FormulaService.getEnFormulas(products);
    }

    @RequestMapping(value = "search/ru", method = RequestMethod.GET)
    @ResponseBody
    public Object searchRu(@RequestParam(value = "title") String title) {
        return FormulaService.searchRuFormulas(title);
    }

    @RequestMapping(value = "search/en", method = RequestMethod.GET)
    @ResponseBody
    public Object searchEn(@RequestParam(value = "title") String title) {
        return FormulaService.searchEnFormulas(title);
    }

    @RequestMapping(value = "prods/ru", method = RequestMethod.GET)
    @ResponseBody
    public Object getRu() {
        return FormulaService.getRuProducts();
    }

    @RequestMapping(value = "prods/en", method = RequestMethod.GET)
    @ResponseBody
    public Object getEn() {
        return FormulaService.getEnProducts();
    }

    @RequestMapping(value = "get/ru", method = RequestMethod.GET)
    @ResponseBody
    public Object getRu(@RequestParam(value = "category") String category,
                        @RequestParam(value = "page", required = false, defaultValue ="1") int page) {
        return FormulaService.getRuFormulasPaged(category, page);
    }

    @RequestMapping(value = "get/en", method = RequestMethod.GET)
    @ResponseBody
    public Object getEn(@RequestParam(value = "category") String category,
                        @RequestParam(value = "page", required = false, defaultValue ="1") int page) {
        return FormulaService.getEnFormulasPaged(category, page);
    }

    @RequestMapping(value = "heap", method = RequestMethod.GET)
    @ResponseBody
    public void heap(@RequestParam(value = "key", required = false) String key) {
        if (StringUtils.equals(key, adminKey))
            MemoryUsageService.logMemoryUsage();
    }

}

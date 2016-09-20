package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.model.Formula;
import project.service.applied.FormulaService;
import project.service.utils.MemoryUsageService;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@Controller
@RequestMapping("")
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "check", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@RequestParam(value = "search", required = false) String search) {
        if (search == null)
            return "done";

        String[] array = search.split(",");
        List<Integer> products = new LinkedList<>();
        for (String elem: array)
            products.add(Integer.valueOf(elem));
        Collections.sort(products);
        log.info("Started retrieving recipes for " + search);
        List<Formula> result = FormulaService.getEnFormulas(products);
        log.info("Finished retrieving recipes for " + search);
        return result;
    }

    @RequestMapping(value = "name", method = RequestMethod.GET)
    @ResponseBody
    public Object name(@RequestParam(value = "search", required = false) String search) {
        log.info("Search " + search + " started");
        List<Formula> result = FormulaService.searchEnFormulas(search);
        log.info("Search " + search + " finished");
        return result;
    }

    @RequestMapping(value = "heap", method = RequestMethod.GET)
    @ResponseBody
    public Object heap() {
        MemoryUsageService.logMemoryUsage();
        return "done";
    }

}

package project.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.model.Formula;
import project.service.FormulaService;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@Controller
@Scope("prototype")
@RequestMapping("")
public class MainController {

    @RequestMapping(value = "check", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@RequestParam(value = "search", required = false) String search) {
        if (search == null)
            return "done";

        String[] array = search.split(",");
        List<Integer> products = new LinkedList<>();
        for (String elem: array) {
            products.add(Integer.valueOf(elem));
        }
        Collections.sort(products);

        long timeBefore = System.currentTimeMillis();
        List<Formula> result = FormulaService.getEnFormulas(products);
        long timeAfter = System.currentTimeMillis();

        long spent = (timeAfter - timeBefore) / 1000;

        return spent + "seconds spent \n" + result;
    }

    @RequestMapping(value = "heap", method = RequestMethod.GET)
    @ResponseBody
    public Object heap(@RequestParam(value = "search", required = false) String search) {
        int mb = 1024*1024;

        Runtime runtime = Runtime.getRuntime();

        String result = "##### Heap utilization statistics [MB] #####";

        result += "\nUsed Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / mb;

        result += "\nFree Memory:" + runtime.freeMemory() / mb;

        result += "\nTotal Memory:" + runtime.totalMemory() / mb;

        result += "\nMax Memory:" + runtime.maxMemory() / mb;
        return result;
    }

}

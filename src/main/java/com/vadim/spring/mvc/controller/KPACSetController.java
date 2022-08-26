package com.vadim.spring.mvc.controller;

import com.vadim.spring.mvc.dao.kpac.KPACDaoImplementation;
import com.vadim.spring.mvc.dao.kpac_set.KPACSetDaoImplementation;
import com.vadim.spring.mvc.entity.KPAC;
import com.vadim.spring.mvc.entity.KPACSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class KPACSetController {

    @Autowired
    private KPACSetDaoImplementation kpacSetDaoImplementation;
    @Autowired
    private KPACDaoImplementation kpacDaoImplementation;

    @GetMapping("/set")
    public String showAllKpacs(Model model) {
        List<KPACSet> kpacList = kpacSetDaoImplementation.getAll();
        model.addAttribute("set", kpacList);

        return "set";
    }

    @RequestMapping("/add-new-set")
    public String addNewKpac(Model model) {
        KPACSet kpacSet = new KPACSet();
        model.addAttribute("set", kpacSet);
        return "addSet";
    }

    @RequestMapping("/save-set")
    public String saveKpac(@ModelAttribute("set") KPACSet kpacSet) {
        kpacSetDaoImplementation.save(kpacSet);
        return "redirect:/set";
    }

    @RequestMapping("setDelete/{id}")
    public String deleteKpac(@PathVariable int id) {
        kpacSetDaoImplementation.delete(id);
        return "redirect:/set";
    }

    @RequestMapping("/sort-set-by-id")
    public String sortKpacById(Model model) {
        List<KPACSet> kpacList = kpacSetDaoImplementation.sortBy("id");
        model.addAttribute("set", kpacList);

        return "set";
    }

    @RequestMapping("/sort-set-by-title")
    public String sortKpacByTitle(Model model) {
        List<KPACSet> kpacList = kpacSetDaoImplementation.sortBy("Title");
        model.addAttribute("set", kpacList);

        return "set";
    }

    @RequestMapping("/filter-set-by")
    public String filterKpacByDate(HttpServletRequest request, Model model) {
        String filter = request.getParameter("filterValue");
        String checkbox = request.getParameter("filterBy");

        List<KPACSet> kpacList = kpacSetDaoImplementation.filterBy(checkbox, filter);
        model.addAttribute("set", kpacList);
        return "set";
    }

    @RequestMapping("/set/{id}")
    public String showSet(@PathVariable int id, Model model){
        List<KPAC> kpacs = kpacDaoImplementation.getAllBySetId(id);
        model.addAttribute("kpacs", kpacs);
        return "setDetails";
    }


}

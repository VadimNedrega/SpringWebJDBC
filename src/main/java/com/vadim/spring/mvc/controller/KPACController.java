package com.vadim.spring.mvc.controller;

import com.vadim.spring.mvc.dao.kpac.KPACDaoImplementation;
import com.vadim.spring.mvc.dao.kpac_set.KPACSetDaoImplementation;
import com.vadim.spring.mvc.entity.KPAC;
import com.vadim.spring.mvc.entity.KPACSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class KPACController {

    @Autowired
    private KPACDaoImplementation kpacDaoImplementation;
    @Autowired
    private KPACSetDaoImplementation kpacSetDaoImplementation;

    @RequestMapping("/kpacs")
    public String showAllKpacs(Model model) {
        List<KPAC> kpacList = kpacDaoImplementation.getAll();
        model.addAttribute("kpacs", kpacList);

        return "kpacs";
    }

    @RequestMapping("/add-new-kpac")
    public String addNewKpac(Model model) {
        KPAC kpac = new KPAC();
        model.addAttribute("kpac", kpac);
        return "addKPAC";
    }

    @RequestMapping("/save-kpac")
    public String saveKpac(@ModelAttribute("kpac") KPAC kpac) {
        kpacDaoImplementation.save(kpac);
        return "redirect:/kpacs";
    }

    @RequestMapping("kpacs/{id}")
    public String deleteKpac(@PathVariable int id) {
        kpacDaoImplementation.delete(id);
        return "redirect:/kpacs";
    }

    @RequestMapping("/sort-kpac-by-id")
    public String sortKpacById(Model model) {
        List<KPAC> kpacList = kpacDaoImplementation.sortBy("id");
        model.addAttribute("kpacs", kpacList);

        return "kpacs";
    }

    @RequestMapping("/sort-kpac-by-title")
    public String sortKpacByTitle(Model model) {
        List<KPAC> kpacList = kpacDaoImplementation.sortBy("Title");
        model.addAttribute("kpacs", kpacList);

        return "kpacs";
    }

    @RequestMapping("/sort-kpac-by-description")
    public String sortKpacByDescription(Model model) {
        List<KPAC> kpacList = kpacDaoImplementation.sortBy("Description");
        model.addAttribute("kpacs", kpacList);

        return "kpacs";
    }

    @RequestMapping("/sort-kpac-by-date")
    public String sortKpacByDate(Model model) {
        List<KPAC> kpacList = kpacDaoImplementation.sortBy("Creation_date");
        model.addAttribute("kpacs", kpacList);

        return "kpacs";
    }

    @RequestMapping("/filter-kpac-by")
    public String filterKpacByDate(HttpServletRequest request, Model model) {
        String filter = request.getParameter("filterValue");
        String checkbox = request.getParameter("filterBy");

        List<KPAC> kpacList = kpacDaoImplementation.filterBy(checkbox, filter);
        model.addAttribute("kpacs", kpacList);
        return "kpacs";
    }

    @RequestMapping("/kpacs/{kpacId}/addToSet")
    public String addToSet(Model model, @PathVariable int kpacId) {
        KPAC kpac = kpacDaoImplementation.get(kpacId);
        List<KPACSet> list = kpacSetDaoImplementation.getAll();
        model.addAttribute("set", list);
        model.addAttribute("kpac", kpac);

        return "chooseSet";
    }

    @RequestMapping("/kpacs/{kpacId}/addToSet/{id}")
    public String addToSetBySetId(@PathVariable int id, @PathVariable int kpacId, Model model) {
        KPAC kpac = kpacDaoImplementation.get(kpacId);
        KPAC oldKpac = kpacDaoImplementation.get(kpacId);

        List<KPAC> list = kpacDaoImplementation.getAll();
        kpac.setKpacSet(kpacSetDaoImplementation.get(id).getId());

        for (KPAC target: list){
            if (kpac.equals(target)) return "redirect:/kpacs";
        }

        kpacDaoImplementation.save(kpac);

        if (oldKpac.getKpacSet() == null || oldKpac.getKpacSet() == 0) kpacDaoImplementation.delete(kpacId);

        model.addAttribute("set", list);
        return "redirect:/kpacs";
    }


}

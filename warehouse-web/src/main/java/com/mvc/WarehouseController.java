package com.mvc;

import com.dto.Warehouse;
import com.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(value = "/warehouses", method = RequestMethod.GET)
    public String showWarehouses(ModelMap model) {
        model.addAttribute("warehouses", warehouseService.getWarehouses());
        model.addAttribute("warehouse", new Warehouse());
        return "result";
    }

    @RequestMapping(value = "/addwarehouse", method = RequestMethod.POST)
    public String addWarehouse(@ModelAttribute("warehouse") Warehouse warehouse, ModelMap model) {
        warehouseService.saveWarehouse(warehouse);

        return "redirect:warehouses";
    }
}

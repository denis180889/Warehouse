package com.mvc;

import com.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(value = "/warehouse", method = RequestMethod.GET)
    public ModelAndView warehouse() {

        return new ModelAndView("result", "warehouses", warehouseService.getWarehouses());
    }
}

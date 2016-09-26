package com.mvc;

import com.dto.Warehouse;
import com.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/warehouses", method = RequestMethod.GET)
    public String showWarehouses(ModelMap model) {
        model.addAttribute("warehouses", warehouseService.getWarehouses());
        model.addAttribute("warehouse", new Warehouse());

        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals("ROLE_ADMIN");
            if (hasRole) {
                break;
            }
        }
        model.addAttribute("admin", hasRole);

        return "result";
    }

    @RequestMapping(value = "/addwarehouse", method = RequestMethod.POST)
    public String addWarehouse(@ModelAttribute("warehouse") Warehouse warehouse, ModelMap model) {
        warehouseService.saveWarehouse(warehouse);

        return "redirect:warehouses";
    }
}

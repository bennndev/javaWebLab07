package com.javaWeb.lab07.controller;

import com.javaWeb.lab07.model.Dashboard;
import com.javaWeb.lab07.repository.DashboardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final DashboardRepository dashboardRepository;

    public DashboardController(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @GetMapping("/dashboards")
    public String listDashboards(Model model) {
        List<Dashboard> dashboards = dashboardRepository.findAll();
        model.addAttribute("dashboards", dashboards);
        return "dashboards"; // Plantilla Thymeleaf
    }
}

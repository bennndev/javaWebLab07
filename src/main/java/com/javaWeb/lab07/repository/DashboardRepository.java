package com.javaWeb.lab07.repository;

import com.javaWeb.lab07.model.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
}

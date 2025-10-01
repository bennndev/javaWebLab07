package com.javaWeb.lab07.controller;

import com.javaWeb.lab07.model.Habit;
import com.javaWeb.lab07.model.User;
import com.javaWeb.lab07.repository.HabitRepository;
import com.javaWeb.lab07.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard/habits")
public class HabitController {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitController(HabitRepository habitRepository, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listHabits(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        List<Habit> habits = habitRepository.findByUser(user);
        model.addAttribute("habits", habits);
        return "habits/list";
    }

    @GetMapping("/new")
    public String newHabitForm(Model model) {
        model.addAttribute("habit", new Habit());
        return "habits/form";
    }

    @PostMapping
    public String saveHabit(@ModelAttribute Habit habit, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        habit.setUser(user);
        habitRepository.save(habit);
        return "redirect:/dashboard/habits";
    }

    @GetMapping("/edit/{id}")
    public String editHabit(@PathVariable Long id, Model model) {
        Habit habit = habitRepository.findById(id).orElseThrow();
        model.addAttribute("habit", habit);
        return "habits/form";
    }

    @PostMapping("/update/{id}")
    public String updateHabit(@PathVariable Long id, @ModelAttribute Habit habitData) {
        Habit habit = habitRepository.findById(id).orElseThrow();
        habit.setName(habitData.getName());
        habit.setDescription(habitData.getDescription());
        habit.setStatus(habitData.getStatus());
        habit.setFrequencyPerWeek(habitData.getFrequencyPerWeek());
        habit.setStartDate(habitData.getStartDate());
        habit.setEndDate(habitData.getEndDate());
        habitRepository.save(habit);
        return "redirect:/dashboard/habits";
    }

    @GetMapping("/delete/{id}")
    public String deleteHabit(@PathVariable Long id) {
        habitRepository.deleteById(id);
        return "redirect:/dashboard/habits";
    }
}

package com.example.kindergarden.controllers;

import com.example.kindergarden.base.Schedule;
import com.example.kindergarden.services.ServiceCalendar;
import com.example.kindergarden.services.ServiceSession;
import com.example.kindergarden.services.ServiceSchedule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class SchedulerController {
    private ServiceCalendar serviceCalendar = new ServiceCalendar();
    private ServiceSchedule serviceSchedule = new ServiceSchedule();
    private ArrayList<Schedule> lastDeletedSchedules = new ArrayList<>();

    private String successMessage = "";
    private int numberOfDeletedSchedules = 0;
    private int viewCalendarFor = 0;

    @GetMapping("/index")
    public String index(Model model) {
        if(ServiceSession.isSomeoneLoggedIn()) {
            model.addAttribute("monthAndYear_TXT", serviceCalendar.getMonthAndYear());
            model.addAttribute("daysInThisMonth", serviceCalendar.getDaysInThisMonth());
            model.addAttribute("days", serviceCalendar.getDays());
            model.addAttribute("today", serviceCalendar.getToday());
            model.addAttribute("dateArray", serviceCalendar.getDateArray());
            model.addAttribute("daysToAdd", serviceCalendar.daysToBypass());
            model.addAttribute("schedules", serviceSchedule.getSchedules(serviceCalendar.getMonthAsString(), serviceCalendar.getYearAsString(), viewCalendarFor));
            model.addAttribute("schedulesPerDay", serviceSchedule.getCountSchedulesPerDay(serviceCalendar));
            model.addAttribute("toIndex", serviceSchedule.getToIndexArray(serviceCalendar));
            model.addAttribute("employees", serviceSchedule.getEmployees());
            model.addAttribute("success_TXT", successMessage);
            model.addAttribute("numberOfDeletedSchedules", numberOfDeletedSchedules);
            model.addAttribute("viewCalendarFor", viewCalendarFor);
            model.addAttribute("countSchedulesPerDayForPerson", serviceSchedule.getGetCountSchedulesPerDayForPerson(serviceCalendar, viewCalendarFor));
            successMessage = "";
            numberOfDeletedSchedules = 0;
            return "index";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(value = "/index", params = "month_chooser=Forrige")
    public String goToPreviousMonth() {
        serviceCalendar.decrement();
        emptyDeletedSchedules();
        return "redirect:/index";
    }

    @PostMapping(value = "/index", params = "month_chooser=Næste")
    public String goToNextMonth() {
        serviceCalendar.increment();
        emptyDeletedSchedules();
        return "redirect:/index";
    }

    @PostMapping(value = "/index", params = "go_to=Gå til idag")
    public String goToToday() {
        serviceCalendar.goToToday();
        emptyDeletedSchedules();
        return "redirect:/index";
    }

    @PostMapping(value = "/index", params = "saveNewSchedule=Opret")
    public String createNewSchedule(@RequestParam("fromTime") String fromTime, @RequestParam("toTime") String toTime, @RequestParam("date") String date ,@RequestParam("employee") int employeeKey) {
        serviceSchedule.add(new Schedule(date, fromTime, toTime, employeeKey));
        successMessage = "Den nye vagt er blevet tilføjet og gemt. Kalenderen er opdateret!";
        emptyDeletedSchedules();
        return "redirect:/index";
    }

    @PostMapping(value = "/index", params = "deleteSingleShcedule=Slet")
    public String deleteSingleSchedule(@RequestParam("deleteSingleScheduleID") String ID) {
        emptyDeletedSchedules();
        lastDeletedSchedules.add(serviceSchedule.remove(Integer.parseInt(ID)));
        successMessage = "Vagten blev fjernet. Kalenderen er opdateret!";
        numberOfDeletedSchedules = 1;
        return "redirect:/index";
    }

    @PostMapping(value = "/index", params = "restoreDeletedSchedules=Fortryd")
    public String restoreDeletedSchedules() {
        for(Schedule schedule: lastDeletedSchedules) {
            serviceSchedule.add(new Schedule(schedule.getDate(), schedule.getFromTime(), schedule.getToTime(), schedule.getEmployeeKey()));
            //System.out.println(serviceCalendar.getYearAsString()+"-"+serviceCalendar.getMonthAsString()+"-"+schedule.getDate());
        }
        numberOfDeletedSchedules = 1;
        successMessage = (numberOfDeletedSchedules == 1 ? numberOfDeletedSchedules + " vagt " : numberOfDeletedSchedules + " vagter ") + "blev gendannet. Kalenderen er opdateret!";
        numberOfDeletedSchedules = 0;
        return "redirect:/index";
    }

    private void emptyDeletedSchedules() {
        lastDeletedSchedules = new ArrayList<>();
    }

    @PostMapping(value = "/index", params = "changeCalendar=Yes")
    public String changeCalendarFor(@RequestParam("showCalendar") String id) {
        viewCalendarFor = Integer.parseInt(id);
        return "redirect:/";
    }
}

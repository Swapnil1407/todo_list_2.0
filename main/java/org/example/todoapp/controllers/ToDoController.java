package org.example.todoapp.controllers;

import org.example.todoapp.entities.ToDo;
import org.example.todoapp.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping({ "/viewToDoList"})
    public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("list", toDoService.getAllToDoItems());
        model.addAttribute("message", message);
        return "ViewToDoList";
    }

    @GetMapping("/updateToDoStatus/{id}")
    public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if(toDoService.updateStatus(id)) {
            redirectAttributes.addFlashAttribute("message", "Update Success");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Update Failure");
        return "redirect:/viewToDoList";
    }

    @GetMapping("/addToDoItem")
    public String addToDoItem(Model model) {
        model.addAttribute("todo", new ToDo());
        return "AddToDoItem";
    }

    @PostMapping("/saveToDoItem")
    public String saveToDoItem(ToDo toDo, RedirectAttributes redirectAttributes) {
        if (toDoService.saveOrUpdateToDoItem(toDo)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/addToDoItem";
    }

    @GetMapping("/editToDoItem/{id}")
    public String editToDoItem(@PathVariable Long id, Model model) {
        model.addAttribute("todo", toDoService.getToDoItemById(id));
        return "EditToDoItem";
    }


    @PostMapping("/editSaveToDoItem")
    public String editSaveToDoItem(ToDo toDo, RedirectAttributes redirectAttributes) {
        if (toDoService.saveOrUpdateToDoItem(toDo)) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/editSaveToDoItem"+toDo.getId();
    }

    @GetMapping("/deleteToDoItem/{id}")
    public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if(toDoService.deleteToDoItem(id)) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Delete Failure");
        return "redirect:/viewToDoList";
    }
}


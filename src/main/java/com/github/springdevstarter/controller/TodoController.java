package com.github.springdevstarter.controller;

import com.github.springdevstarter.model.Todo;
import com.github.springdevstarter.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showTodoList(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        model.addAttribute("newtodo", new Todo());
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTodo(@ModelAttribute("newtodo") Todo newtodo) {
        try {
            todoService.addTodo(newtodo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String delTodo(@PathVariable("id") Integer id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }

}

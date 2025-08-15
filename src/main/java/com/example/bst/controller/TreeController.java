package com.example.bst.controller;

import com.example.bst.model.TreeRecord;
import com.example.bst.service.BinarySearchTreeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class TreeController {

    private final BinarySearchTreeService service;

    public TreeController(BinarySearchTreeService service) {
        this.service = service;
    }


    @GetMapping("/")
    public String index() {
        return "index.html";
    }


    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enter-numbers.html";
    }


    @GetMapping("/previous-trees-page")
    public String previousTreesPage() {
        return "previous-trees.html";
    }


    @PostMapping("/process-numbers")
    @ResponseBody
    public Map<String, Object> processNumbers(@RequestParam String numbers) {
        return service.createAndStoreTree(numbers);
    }


    @GetMapping("/previous-trees")
    @ResponseBody
    public List<TreeRecord> previousTrees() {
        return service.getAllTrees();
    }
}
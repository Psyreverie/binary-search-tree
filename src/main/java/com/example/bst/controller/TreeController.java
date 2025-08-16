package com.example.bst.controller;

import com.example.bst.model.TreeRecord;
import com.example.bst.service.BinarySearchTreeService;
import com.example.bst.repository.TreeRecordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class TreeController {

    private final BinarySearchTreeService service;
    private final TreeRecordRepository repository;

    public TreeController(BinarySearchTreeService service, TreeRecordRepository repository) {
        this.service = service;
        this.repository = repository;
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


    @DeleteMapping("/api/previous-trees/{id}")
    @ResponseBody
    public Map<String, Object> deleteTree(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return Map.of("success", true, "message", "Tree deleted successfully");
        } catch (Exception e) {
            return Map.of("success", false, "message", "Failed to delete tree: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/previous-trees")
    @ResponseBody
    public Map<String, Object> clearAllTrees() {
        try {
            repository.deleteAll();
            return Map.of("success", true, "message", "All trees cleared successfully");
        } catch (Exception e) {
            return Map.of("success", false, "message", "Failed to clear trees: " + e.getMessage());
        }
    }
}
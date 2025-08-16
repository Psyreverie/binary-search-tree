package com.example.bst.service;

import com.example.bst.model.TreeNode;
import com.example.bst.model.TreeRecord;
import com.example.bst.repository.TreeRecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BinarySearchTreeService {

    private final TreeRecordRepository repository;
    private final ObjectMapper objectMapper;

    public BinarySearchTreeService(TreeRecordRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public Map<String, Object> createAndStoreTree(String numbersInput) {
        try {
            List<Integer> numbers = parseInput(numbersInput);
            String treeJson = buildTreeJson(numbers);
            TreeRecord saved = saveTree(numbers, treeJson);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("tree", treeJson);
            response.put("id", saved.getId());
            response.put("inputNumbers", numbers);
            return response;
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("error", e.getMessage());
            return response;
        }
    }

    public String buildTreeJson(List<Integer> numbers) throws Exception {
        TreeNode root = null;
        for (int num : numbers) {
            root = TreeNode.insert(root, num);
        }
        return objectMapper.writeValueAsString(root);
    }

    public TreeRecord saveTree(List<Integer> numbers, String treeJson) {
        TreeRecord record = new TreeRecord(numbers.toString(), treeJson);
        return repository.save(record);
    }

    public List<TreeRecord> getAllTrees() {
        return repository.findAll();
    }

    public void deleteTree(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllTrees() {
        repository.deleteAll();
    }

    public List<Integer> parseInput(String input) {
        return Arrays.stream(input.split("[,\\s]+"))
                .filter(s -> !s.isEmpty())
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
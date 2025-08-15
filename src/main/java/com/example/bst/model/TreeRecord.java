package com.example.bst.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tree_records")
public class TreeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "input_numbers", columnDefinition = "TEXT")
    private String inputNumbers;

    @Column(name = "tree_json", columnDefinition = "TEXT")
    private String treeJson;


    public TreeRecord() {}

    public TreeRecord(String inputNumbers, String treeJson) {
        this.inputNumbers = inputNumbers;
        this.treeJson = treeJson;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getInputNumbers() { return inputNumbers; }
    public void setInputNumbers(String inputNumbers) { this.inputNumbers = inputNumbers; }

    public String getTreeJson() { return treeJson; }
    public void setTreeJson(String treeJson) { this.treeJson = treeJson; }
}

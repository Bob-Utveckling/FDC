/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamshadit.file_similarity;

/**
 *
 * @author Bamshad
 */
public class FileObj {
    String fileName;
    String filePath;
    int name_ranking_for_chars;
    int name_ranking_for_ordered_chars;
    int path_ranking;
    long size_ranking;

    public FileObj(String fileName, String filePath, int name_ranking_for_chars, int name_ranking_for_ordered_chars, int path_ranking, long size_ranking) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.name_ranking_for_chars = name_ranking_for_chars;
        this.name_ranking_for_ordered_chars = name_ranking_for_ordered_chars;
        this.path_ranking = path_ranking;
        this.size_ranking = size_ranking;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getName_ranking_for_chars() {
        return name_ranking_for_chars;
    }

    public void setName_ranking_for_chars(int name_ranking_for_chars) {
        this.name_ranking_for_chars = name_ranking_for_chars;
    }

    public int getName_ranking_for_ordered_chars() {
        return name_ranking_for_ordered_chars;
    }

    public void setName_ranking_for_ordered_chars(int name_ranking_for_ordered_chars) {
        this.name_ranking_for_ordered_chars = name_ranking_for_ordered_chars;
    }

    public int getPath_ranking() {
        return path_ranking;
    }

    public void setPath_ranking(int path_ranking) {
        this.path_ranking = path_ranking;
    }

    public long getSize_ranking() {
        return size_ranking;
    }

    public void setSize_ranking(long size_ranking) {
        this.size_ranking = size_ranking;
    }



    
}

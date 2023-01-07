/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamshadit.check.in_2_folders;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Bamshad
 */

public class DupPathFiles {
    //REMOVED STATELESS ANNOTATION
    List<String> onpath1 = new ArrayList<>();
    List<String> onpath2 = new ArrayList<>();
    List<String> onpath3 = new ArrayList<>();
    List<String> onpath4 = new ArrayList<>();
    List<String> onpath5 = new ArrayList<>();
    List<String> onpath6 = new ArrayList<>();
    List<String> onpath7 = new ArrayList<>();
    List<String> onpath8 = new ArrayList<>();
    List<String> onpath9 = new ArrayList<>();
    List<String> onpath10 = new ArrayList<>();

    public DupPathFiles() {        
    }
    

    public DupPathFiles(List<String> onpath1) {
        this.onpath1 = onpath1;
    }

    public DupPathFiles(List<String> onpath1, List<String> onpath2) {
        this.onpath1 = onpath1;
        this.onpath2 = onpath2;
    }
    
    //Constructors with more numbers of paths can be added here
    
    
    public List<String> getPath1() {
        return onpath1;
    }

    public void setPath1(List<String> path1) {
        this.onpath1 = path1;
    }

    public List<String> getPath2() {
        return onpath2;
    }

    public void setPath2(List<String> path2) {
        this.onpath2 = path2;
    }

    public List<String> getPath3() {
        return onpath3;
    }

    public void setPath3(List<String> path3) {
        this.onpath3 = path3;
    }

    public List<String> getPath4() {
        return onpath4;
    }

    public void setPath4(List<String> path4) {
        this.onpath4 = path4;
    }

    public List<String> getPath5() {
        return onpath5;
    }

    public void setPath5(List<String> path5) {
        this.onpath5 = path5;
    }

    public List<String> getPath6() {
        return onpath6;
    }

    public void setPath6(List<String> path6) {
        this.onpath6 = path6;
    }

    public List<String> getPath7() {
        return onpath7;
    }

    public void setPath7(List<String> path7) {
        this.onpath7 = path7;
    }

    public List<String> getPath8() {
        return onpath8;
    }

    public void setPath8(List<String> path8) {
        this.onpath8 = path8;
    }

    public List<String> getPath9() {
        return onpath9;
    }

    public void setPath9(List<String> path9) {
        this.onpath9 = path9;
    }

    public List<String> getPath10() {
        return onpath10;
    }

    public void setPath10(List<String> path10) {
        this.onpath10 = path10;
    }
   
}


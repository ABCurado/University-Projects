/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.networkExplorer.domain;

/**
 *
 * @author Diogo Azevedo
 */
public class SpreadSheetInfo {
    private String name;
    
    public SpreadSheetInfo(String name){
        this.name=name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}

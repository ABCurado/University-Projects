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
public class ExtensionInfo {

    private boolean active;
    private String name;
    private String version;
    private String description;

    public ExtensionInfo(boolean active, String name, String version, String description) {
        this.active = active;
        this.name = name;
        this.version = version;
        this.description = description;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}

package com.github.meteoorkip.util;

public class Tree {

    /**
     * The name of this Tree node.
     */
    private String name = "undefined";

    /**
     * Returns the name of this Tree node.
     *
     * @return The name of this Tree node.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this Tree node.
     *
     * @param nodeName The new name.
     */
    public void setName(String nodeName) {
        this.name = nodeName;
    }
}

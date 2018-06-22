package com.github.meteoorkip.util;

import java.util.List;

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

    /**
     * Returns a list of children of this Tree node.
     *
     * @return The list of children.
     */
    public List<Tree> getChildren() {
        return null;
    }
}

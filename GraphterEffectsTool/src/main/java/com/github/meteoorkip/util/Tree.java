package com.github.meteoorkip.util;

import java.util.LinkedList;
import java.util.List;

public class Tree {

    /**
     * The name of this Tree node.
     */
    private String name = "undefined";
    /**
     * List of children of this Tree node.
     */
    private List<Tree> children = new LinkedList<>();

    /**
     * Returns an XML attribute of this Tree node.
     *
     * @param key The attribute key.
     * @return The attribute value assigned to this key, or null if no such key is present.
     */
    public String getAttribute(String key) {
        //TODO: implement
        return null;
    }

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
     * @return The list of children.
     */
    public List<Tree> getChildren() {
        return new LinkedList<>(this.children);
    }

    /**
     * Adds a child to this Tree node.
     *
     * @param item The child to be added.
     */
    public void addChild(Tree item) {
        this.children.add(item);
    }
}

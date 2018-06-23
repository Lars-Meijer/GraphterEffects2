package com.github.meteoorkip.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
     * Map containing attribute key-value pairs. Access this with <code>getAttribute(key)</code>
     */
    private Map<String, String> attributes = new HashMap<>();

    /**
     * Returns an XML attribute of this Tree node.
     *
     * @param key The attribute key.
     * @return The attribute value assigned to this key, or null if no such key is present.
     */
    public String getAttribute(String key) {
        return attributes.get(key);
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
    void setName(String nodeName) {
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
    void addChild(Tree item) {
        this.children.add(item);
    }

    /**
     * Adds a new attribute key-value pair to this Tree node.
     *
     * @param key   The attribure key.
     * @param value The attribute value.
     */
    void addAttribute(String key, String value) {
        this.attributes.put(key, value);
    }

}

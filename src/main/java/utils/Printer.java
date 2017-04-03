package utils;

import org.graphstream.graph.Element;
import org.graphstream.graph.Graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Class used to print textual representations into the console.
 */
public final class Printer {

    /**
     * Prints a user-friendly text representation of a Graph into the console.
     *
     * @param g Graph to be printed.
     */
    public static void pprint(Graph g) {

        StringBuilder builder = new StringBuilder();
        builder.append(g.toString());
        builder.append("\t{");


        String attrstring = "";
        for (String key : g.getAttributeKeySet()) {
            attrstring += "\"" + key + "\":\"" + g.getAttribute(key) + "\",";
        }
        if (attrstring.endsWith(",")) {
            attrstring = attrstring.substring(0, attrstring.length() - 1);
        }
        attrstring = attrstring + "}";
        builder.append(attrstring).append("\n");

        List<Element> nodeset = new LinkedList<>(g.getNodeSet());
        Collections.sort(nodeset, (o1, o2) -> StringUtils.compareStrings(o1.getId(), o2.getId()));
        builder.append("NODES\n");
        addfromlist(builder, nodeset, false, false);
        List<Element> edgeset = new LinkedList<>(g.getEdgeSet());
        Collections.sort(edgeset, (o1, o2) -> StringUtils.compareStrings(o1.getId(), o2.getId()));
        builder.append("EDGES\n");
        addfromlist(builder, edgeset, false, false);
        System.out.println(builder.toString());
    }


    /**
     * Adds a String representation of elements of a graph to a Stringbuilder.
     *
     * @param builder     Builder to add the String representation to.
     * @param list        List of elements of which the String representation needs to be added.
     * @param keyquotes   True if quotes are added around keys of a key-value pair.
     * @param valuequotes True if quotes are added around values of a key-value pair.
     */
    private static void addfromlist(StringBuilder builder, List<Element> list, boolean keyquotes, boolean valuequotes) {
        String keyq = "";
        String valq = "";
        if (keyquotes) {
            keyq = "\"";
        }
        if (valuequotes) {
            valq = "\"";
        }
        for (Element e : list) {
            builder.append("\t").append(e);
            builder.append("\t{");
            String attrstring = "";
            for (String key : e.getAttributeKeySet()) {
                attrstring += keyq + key + keyq + ":" + valq + StringUtils.ObjectToString(e.getAttribute(key)) + valq + ",";
            }
            if (attrstring.endsWith(",")) {
                attrstring = attrstring.substring(0, attrstring.length() - 1);
            }
            attrstring = attrstring + "}";
            builder.append(attrstring).append("\n");
        }

    }

}

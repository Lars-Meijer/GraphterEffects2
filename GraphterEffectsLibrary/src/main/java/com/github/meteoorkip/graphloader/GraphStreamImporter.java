package com.github.meteoorkip.graphloader;

import org.graphstream.graph.Graph;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Class used to import {@link Graph} objects from formats natively supported by GraphStream.
 */
@SuppressWarnings("WeakerAccess")
final class GraphStreamImporter {

    /**
     * {@link List} of file extensions accepted by this importer.
     */
    private static final List<String> acceptslist = Arrays.asList("dgs", "dot", "gml", "tlp", "net", "graphml", "net");

    /**
     * Returns whether an extension is accepted by this importer.
     *
     * @param ext File extension to verify.
     * @return <tt>true</tt> if the file extension is accepted.
     */
    public static boolean acceptsExtension(String ext) {
        return acceptslist.contains(ext.toLowerCase());
    }

    /**
     * Reads a {@link Graph} from a {@link File}.
     *
     * @param file {@link File} to read from.
     * @return A {@link Graph} containing the graph represented in the file.
     * @throws IOException Thrown when the file could not be read.
     */
    public static Graph read(File file) throws IOException {
        try {
            return read(file, false);
        } catch (IdAlreadyInUseException e) {
            return read(file, true);
        }
    }

    /**
     * Reads a {@link File} in some graph format into a GraphStream graph Object.
     *
     * @param file {@link File} to read into a GraphsStream Graph Object.
     * @return A {@link Graph} containing the graph represented in the file.
     * @throws IOException Thrown when the file could not be read.
     */
    static Graph read(File file, boolean multigraph) throws IOException {
        Graph g;
        if (multigraph) {
            g = new MultiGraph(file.getName());
        } else {
            g = new SingleGraph(file.getName());
        }
        FileSource fs = FileSourceFactory.sourceFor(file.getAbsolutePath());
        fs.addSink(g);
        fs.readAll(file.getAbsolutePath());
        fs.removeSink(g);
        return g;
    }

    /**
     * Returns an {@link Iterator} iterating over all accepted extensions.
     *
     * @return An {@link Iterator} over all accepted extensions.
     */
    public static Iterator<String> accepted() {
        return acceptslist.iterator();
    }
}

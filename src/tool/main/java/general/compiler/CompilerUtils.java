package general.compiler;

import general.files.DocumentModel;
import general.files.IOManager;
import org.dom4j.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CompilerUtils {

    /**
     * Save a vis document in the temp folder.
     * @param filename filename of the vis file
     * @param code the graafvis code
     */
    public static Path saveAsTempScript(String filename, String code){
        new File("/temp/compiler").mkdirs();
        Path tempFilePath = Paths.get("/temp/compiler",filename);
        try {
            IOManager.saveVIS(tempFilePath,code);
            return tempFilePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Save the generated SVG in the temp folder.
     * @param filename filename of the SVG
     * @param svgDocument generated SVG
     */
    public static void saveGeneratedSVG(String filename, Document svgDocument){
        int counter = DocumentModel.getInstance().generateSVGCounter(filename);
        if (counter != 0){
            filename += "(" + counter + ")";
        }
        svgDocument.setName(filename);

        String svgxml = svgDocument.asXML();
        List<String> svgxmltext = new ArrayList<>();
        svgxmltext.add(svgxml);

        new File("temp/compiled/").mkdirs();

        Path file = Paths.get("temp/compiled/",svgDocument.getName() + ".svg");
        try {
            Files.write(file, svgxmltext, Charset.forName("UTF-8"));
            DocumentModel.getInstance().addGeneratedSVG(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
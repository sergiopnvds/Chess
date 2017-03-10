import java.io.*;
import java.util.Properties;

/**
 *
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class EjemploProperties {
    /**
     * Main program.
     *
     * @param args program args.
     * @throws IOException if there is some file errors.
     */
    public static void main(String[] args)
            throws IOException {
        File file = new File(args[0]);

        // write
        Properties properties1 = new Properties();
        properties1.put("KEY", "VALUE");
        Writer writer = new FileWriter(file);
        properties1.store(writer, "configuration example");
        writer.close();

        // read
        Properties properties2 = new Properties();
        Reader reader = new FileReader(file);
        properties2.load(reader);
        contenido(properties2, "KEY");
        contenido(properties2, "OTHER");
    }

    private static void contenido(Properties properties, String key) {
        String value = properties.getProperty(key);
        if (value == null)
            System.out.printf("%s: no existe%n", key);
        else
            System.out.printf("%s= %s%n", key, value);
    }
}

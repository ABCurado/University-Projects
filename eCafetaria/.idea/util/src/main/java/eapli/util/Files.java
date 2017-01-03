/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.util;

/**
 *
 * @author Paulo Gandra Sousa
 */
public final class Files {

    public static String currentDirectory() {
        return new java.io.File(".").getAbsolutePath();
    }

    public static String ensureExtension(final String filename,
            final String extension) {
        if (!filename.endsWith(extension)) {
            return filename + extension;
        } else {
            return filename;
        }
    }

    private Files() {
        // to make sure this is an utility class
    }
}

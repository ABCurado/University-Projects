/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.filesystem.impl;

import eapli.framework.filesystem.FileSystem;
import java.io.IOException;

/**
 * TODO javadoc
 *
 * @author nervousdev
 */
public abstract class CSVEncoder<T> extends FileSystem<T> {

    private static final String FILE_EXTENSION = ".csv";
    /**
     * CSV final output.
     */
    private String csv;

    /**
     * Set of rules for the Encoder to follow.
     */
    private final String[] rules = new String[]{"\"", ",", "\n"};

    public CSVEncoder() {
        this.csv = "";
    }

    /**
     * Returns the quote rule.
     *
     * @return Quote rule.
     */
    private String getQuote() {
        return this.rules[0];
    }

    /**
     * Returns the separator rule.
     *
     * @return Separator rule.
     */
    private String getSeparator() {
        return this.rules[1];
    }

    /**
     * Returns the end line rule.
     *
     * @return End Line rule.
     */
    private String getEndLine() {
        return this.rules[2];
    }

    /**
     * Appends fields to the CSV file.
     *
     * @param fields Fields.
     */
    protected void append(String... fields) {
        String line = "";

        for (String field : fields) {
            line += this.getQuote() + field + this.getQuote() + this.
                    getSeparator();
        }

        // Clear the last separator at the end.
        if (!this.getSeparator().equals("")) {
            line = line.substring(0, line.length() - this.getSeparator().
                    length());
        }

        this.csv += line + this.getEndLine();
    }

    /**
     * Encodes the CSV file, giving its output.
     *
     * @return Encoded output.
     */
    private String encode() {
        return this.csv;
    }

    @Override
    public boolean saveFile(String fileName) throws IOException {
        return super.
                saveFile(fileName + FILE_EXTENSION, encode(), FILE_EXTENSION);
    }
}

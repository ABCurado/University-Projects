/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.FindWorkbook.ui;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Mateus
 */
public class FindWorkbookController {

	/**
	 * Find workbooks files
	 *
	 * @param startingDirectory start directory
	 * @param pattern extention of the file
	 * @return list file
	 * @throws java.lang.NullPointerException exception
	 */
	public List<File> findWorkbook(final File startingDirectory,
								   final String pattern)
		throws java.lang.NullPointerException {
		List<File> listingFiles = new ArrayList<>();
		try {
			if (startingDirectory.isDirectory()) {
				File[] sub = startingDirectory.listFiles(new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						return pathname.isDirectory() || pathname.getName().
							matches(pattern);
					}
				});
				for (File fileDirectory : sub) {
					if (fileDirectory.isDirectory()) {
						// add on list<File> a sub directory of the main directory,
						// using an recursive method to find again on the others subdirectories
						listingFiles.
							addAll(findWorkbook(fileDirectory, pattern));
					} else {
						// add on list<File>, a valid workbook file
						listingFiles.add(fileDirectory);
					}
				}
			}
		} catch (NullPointerException e) {
			e.getMessage();
		}
		return listingFiles;
	}

}

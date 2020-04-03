package org.opengis.cite.geotiff11.util;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryUtil {

	
	public static String getDirectory()
	{
		try {
			return Paths.get(new File(DirectoryUtil.class.getProtectionDomain().getCodeSource().getLocation()
				    .toURI()).getPath(), "../..").toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return System.getProperty("user.dir");
		}
	}
	
	public static String getDirectory(String local)
	{
		return Paths.get(getDirectory(), local).toString();
	
	}
}

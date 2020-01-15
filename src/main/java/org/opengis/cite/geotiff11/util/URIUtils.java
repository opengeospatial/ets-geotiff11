package org.opengis.cite.geotiff11.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.util.logging.Level;

import javax.ws.rs.core.HttpHeaders;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.SystemUtils;
import org.opengis.cite.geotiff11.SyncPipe;
import org.opengis.cite.geotiff11.util.URIUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Provides a collection of utility methods for manipulating or resolving URI
 * references.
 */
public class URIUtils {

    private static final String FIXUP_BASE_URI = "http://apache.org/xml/features/xinclude/fixup-base-uris";
	private static final String EXE = "exe";
	private static final String TIFFDUMP = "tiffdump";
    
    /**
     * Parses the content of the given URI as an XML document and returns a new
     * DOM Document object. Entity reference nodes will not be expanded. XML
     * inclusions (xi:include elements) will be processed if present.
     * 
     * @param uriRef
     *            An absolute URI specifying the location of an XML resource.
     * @return A DOM Document node representing an XML resource.
     * @throws SAXException
     *             If the resource cannot be parsed.
     * @throws IOException
     *             If the resource is not accessible.
     */
    public static Document parseURI(URI uriRef) throws SAXException,
            IOException {
        if ((null == uriRef) || !uriRef.isAbsolute()) {
            throw new IllegalArgumentException(
                    "Absolute URI is required, but received " + uriRef);
        }
        DocumentBuilderFactory docFactory = DocumentBuilderFactory
                .newInstance();
        docFactory.setNamespaceAware(true);
        docFactory.setExpandEntityReferences(false);
        docFactory.setXIncludeAware(true);
        Document doc = null;
        try {
            // XInclude processor will not add xml:base attributes
            docFactory.setFeature(FIXUP_BASE_URI, false);
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(uriRef.toString());
        } catch (ParserConfigurationException x) {
            TestSuiteLogger.log(Level.WARNING,
                    "Failed to create DocumentBuilder." + x);
        }
        if (null != doc) {
            doc.setDocumentURI(uriRef.toString());
        }
        return doc;
    }

	/**
	 * Parses the content of the given URI as a GeoTIFF document. Execute
	 * listgeo command lines to spit out the metadata into a local file.
	 *
	 * @param uriRef In order to extract the file path where the exe is located
	 * @param geoTiffFile To extract the metadata
	 * @param tiffFile To extract the metadata
	 * @return String of file location
	 * @throws SAXException
	 * @throws IOException
	 */
	public static boolean parseGeoTiff(URI uriRef, String geoTiffFile, String tiffFile)
			throws SAXException, IOException {
		if ((null == uriRef)) {
			throw new IllegalArgumentException("Absolute URI is required, but received " + uriRef);
		}

		// Start running commands
		String geotiffFilePath = uriRef.getPath().substring(1, uriRef.getPath().length());

		if (!readMetaData(geotiffFilePath, geoTiffFile, tiffFile))
			return false;

		return true;
	}

	/**
	 * Create the metadata commands to read the geotiff file
	 * 
	 * @param exeCommand
	 * @param geotiffFilePath
	 * @param fileOutput
	 * @return
	 */
	private static String initMetadataComm(String exeCommand, String geotiffFilePath, String fileOutput, String option) {
		String location = URIUtils.class.getResource("/tmp").getPath();
		return exeCommand + " " + option + " " + geotiffFilePath + " > " + location.substring(1) + "\\" + fileOutput;
	}

	private static void runLinuxcommands(String url, String geotiffFilePath)
	{
		// TODO: Add Linux option?
//		
//		url = url.substring(url.lastIndexOf(":") + 1);
//		System.out.println("Linux parser path: " + url);
//
//		String location = URIUtils.class.getResource("/tmp").getPath();
//
//		try {
//			Runtime.getRuntime().exec("chmod +x " + url + "/parse.sh" + " && " 
//			+ url + "/parse.sh" + " && " + url + "/listgeo " + geotiffFilePath + " > " + location + "/geotiffMeta.txt");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		//Runtime.getRuntime().exec("tiffdump " + geotiffFilePath + " > " + location + "/tiffMeta.txt");

	}
	/**
	 * Run the commands to read the metadata of both the tiff part and the
	 * geotiff part
	 * 
	 * @param geotiffFilePath
	 * @param geoTiffFile
	 * @param tiffFile
	 * @return
	 */
	private static boolean readMetaData(String geotiffFilePath, String geoTiffFile, String tiffFile) {
		String url;
		
		if (SystemUtils.IS_OS_LINUX) { // TODO: Linux??
						
			//check for 32 bit or 64 bit linux machine
			String s = System.getProperty("os.arch");
			System.out.println("32 bit or 64 bit? " + s);
			
			if (s != null && (s.equals("x86_64") || s.equals("amd64") || s.endsWith("64"))) 
			{
			    url = URIUtils.class.getResource("/exe/64bit").getPath();
			    System.out.println("url path before envt compatibility is: " + url);
			    runLinuxcommands(url, geotiffFilePath);
			}
			else
			{
			    url = URIUtils.class.getResource("/exe/32bit").getPath();
			    System.out.println("url path before envt compatibility is: " + url);
			    runLinuxcommands(url, geotiffFilePath);
			}
			return true;
		} else if (SystemUtils.IS_OS_WINDOWS) {
			url = URIUtils.class.getResource("/" + EXE).getPath();
			System.out.println("url path before envt compatibility is: " + url);

			url = url.replace("/", "\\");
			url = url.substring(1);
			System.out.println("Windows parser path: " + url);

			Process p = startCmd();
			if (p == null)
				return false;

			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();

			// the try-with-resources statement will close the resource after
			// use
			try (PrintWriter stdin = new PrintWriter(p.getOutputStream())) {
				stdin.println("cd " + url);
				stdin.println(initMetadataComm(TIFFDUMP, geotiffFilePath, tiffFile, "-m 10000"));
				stdin.flush();
			}

			int returnCode;
			try {
				returnCode = p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			// System.out.println("Return code = " + returnCode);
			return true;
		}
		return false;
	}

	/**
	 * For the Windows commands
	 * 
	 * @return
	 */
	private static Process startCmd() {
		String[] command = { "cmd", };
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			return p;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
    
    /**
     * Dereferences the given URI and stores the resulting resource
     * representation in a local file. The file will be located in the default
     * temporary file directory.
     * 
     * @param uriRef
     *            An absolute URI specifying the location of some resource.
     * @return A File containing the content of the resource; it may be empty if
     *         resolution failed for any reason.
     * @throws IOException
     *             If an IO error occurred.
     */
    public static File dereferenceURI(URI uriRef) throws IOException {
        if ((null == uriRef) || !uriRef.isAbsolute()) {
            throw new IllegalArgumentException(
                    "Absolute URI is required, but received " + uriRef);
        }
        if (uriRef.getScheme().equalsIgnoreCase("file")) {
            return new File(uriRef);
        }
        Client client = Client.create();
        WebResource webRes = client.resource(uriRef);
        ClientResponse rsp = webRes.get(ClientResponse.class);
        String suffix = null;
        if (rsp.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE).endsWith("tif")) {
            suffix = ".tif";
        }
        File destFile = File.createTempFile("entity-", suffix);
        if (rsp.hasEntity()) {
            InputStream is = rsp.getEntityInputStream();
            OutputStream os = new FileOutputStream(destFile);
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.flush();
            os.close();
        }
        TestSuiteLogger.log(Level.FINE, "Wrote " + destFile.length()
                + " bytes to file at " + destFile.getAbsolutePath());
        return destFile;
    }

    /**
     * Constructs an absolute URI from the given URI reference and a base URI.
     * 
     * @see <a href="http://tools.ietf.org/html/rfc3986#section-5.2">RFC 3986,
     *      5.2</a>
     * 
     * @param baseURI
     *            The base URI; if present, it must be an absolute URI.
     * @param uriRef
     *            A URI reference that may be relative to the given base URI.
     * @return The resulting URI.
     * 
     */
    public static URI resolveRelativeURI(String baseURI, String uriRef) {
        URI uri = (null != baseURI) ? URI.create(baseURI) : URI.create("");
        if (null != baseURI && null == uri.getScheme()) {
            throw new IllegalArgumentException(
                    "Base URI has no scheme component: " + baseURI);
        }
        return uri.resolve(uriRef);
    }
}

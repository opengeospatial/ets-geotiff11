package org.opengis.cite.geotiff11.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;
import org.opengis.cite.geotiff11.SuiteAttribute;
import org.opengis.cite.geotiff11.SyncPipe;
import org.testng.ISuite;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;

/**
 * Provides a collection of utility methods for manipulating or resolving URI references.
 */
public class URIUtils {

	private static final String FIXUP_BASE_URI = "http://apache.org/xml/features/xinclude/fixup-base-uris";

	private static final String EXE = "exe";

	private static final String TIFFDUMP = "tiffdump";

	/**
	 * Parses the content of the given URI as an XML document and returns a new DOM
	 * Document object. Entity reference nodes will not be expanded. XML inclusions
	 * (xi:include elements) will be processed if present.
	 * @param uriRef An absolute URI specifying the location of an XML resource.
	 * @return A DOM Document node representing an XML resource.
	 * @throws org.xml.sax.SAXException If the resource cannot be parsed.
	 * @throws java.io.IOException If the resource is not accessible.
	 */
	public static Document parseURI(URI uriRef) throws SAXException, IOException {
		if ((null == uriRef) || !uriRef.isAbsolute()) {
			throw new IllegalArgumentException("Absolute URI is required, but received " + uriRef);
		}
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		docFactory.setNamespaceAware(true);
		docFactory.setExpandEntityReferences(false);
		docFactory.setXIncludeAware(true);
		Document doc = null;
		try {
			// XInclude processor will not add xml:base attributes
			docFactory.setFeature(FIXUP_BASE_URI, false);
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(uriRef.toString());
		}
		catch (ParserConfigurationException x) {
			TestSuiteLogger.log(Level.WARNING, "Failed to create DocumentBuilder." + x);
		}
		if (null != doc) {
			doc.setDocumentURI(uriRef.toString());
		}
		return doc;
	}

	/**
	 * Parses the content of the given URI as a GeoTIFF document. Execute tiffdump command
	 * lines to spit out the metadata into a local file.
	 * @param uriRef In order to extract the file path where the exe is located
	 * @param geoTiffFile To extract the metadata
	 * @return String of file location
	 * @throws org.xml.sax.SAXException
	 * @throws java.io.IOException
	 * @param suite a {@link org.testng.ISuite} object
	 */
	public static boolean parseGeoTiff(ISuite suite, URI uriRef, String geoTiffFile) throws SAXException, IOException {
		// TODO: multiple files capability?

		if ((null == uriRef)) {
			throw new IllegalArgumentException("Absolute URI is required, but received " + uriRef);
		}

		// Start running commands
		// String geotiffFilePath = uriRef.getPath().substring(1,
		// uriRef.getPath().length());
		String geotiffFilePath = prepPath(uriRef.getPath());

		// System.out.println("GeoTIFF path: " + geotiffFilePath);

		if (!readMetaData(suite, geotiffFilePath, geoTiffFile))
			return false;

		return true;
	}

	/**
	 * Create the metadata commands to read the geotiff file
	 * @param exeCommand
	 * @param geotiffFilePath
	 * @param fileOutput
	 * @return
	 */
	private static String initMetadataComm(String exeCommand, String outLocation, String geotiffFilePath,
			String option) {
		// String location = prepPath(URIUtils.class.getResource("/tmp").getPath());
		return exeCommand + " " + option + " " + geotiffFilePath + " > " + outLocation;// Paths.get(location,
																						// fileOutput);
	}

	/**
	 * Run the commands to read the TiffDump output on Windows/Linux
	 * @param geotiffFilePath
	 * @param geoTiffFile
	 * @return
	 */
	private static boolean readMetaData(ISuite suite, String geotiffFilePath, String geoTiffFile) {
		Process p = startCmd();
		if (p == null)
			return false;

		new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
		new Thread(new SyncPipe(p.getInputStream(), System.out)).start();

		try {
			File outTempFile = File.createTempFile("tiffMeta", ".txt");
			InputStream input;

			if (SystemUtils.IS_OS_WINDOWS) {

				// WINDOWS

				File exeTempFile;
				String exe;

				exeTempFile = File.createTempFile("tiffdump", ".exe");

				input = URIUtils.class.getResourceAsStream("/exe/tiffdump.exe");

				FileOutputStream output = new FileOutputStream(exeTempFile);

				byte[] buffer = new byte[4096];
				int bytesRead = input.read(buffer);
				while (bytesRead != -1) {
					output.write(buffer, 0, bytesRead);
					bytesRead = input.read(buffer);
				}

				exeTempFile.deleteOnExit();

				// System.out.println(exeTempFile);
				// System.out.println(exeTempFile.getPath());

				// exe = exeTempFile.getPath();
				exe = exeTempFile.toPath().toString();
				// System.out.println(Paths.get(System.getProperty("user.dir")));
				// System.out.println(exeTempFile.toPath());
				// System.out.println(exe);

				output.close();
				input.close();

				try (PrintWriter stdin = new PrintWriter(p.getOutputStream())) {
					stdin.println(initMetadataComm(exe, outTempFile.getAbsolutePath(), geotiffFilePath, "-m 10000"));
					stdin.flush();
				}
			}
			else {

				// LINUX

				// File tifTempFile = File.createTempFile("iut", ".tif");

				try (PrintWriter stdin = new PrintWriter(p.getOutputStream())) {
					stdin.println("cat /etc/os-release");

					stdin.println("apt-get upgrade -y");
					stdin.println("apt-get update -y");
					// stdin.println("apt -q install -y libtiff5");

					stdin.println("apt-get install -y libtiff-tools");

					stdin.println("apt-get update -y");

					stdin.flush();

					// stdin.println("dpkg -l libtiff-tools");

					// String outPath = "./" +
					// Paths.get(System.getProperty("user.dir")).relativize(outTempFile.toPath()).toString();
					String outPath = outTempFile.getAbsolutePath();

					stdin.println("cd /");

					stdin.println("echo '" + initMetadataComm("tiffdump", outPath, geotiffFilePath, "-m 10000") + "'");
					stdin.println(initMetadataComm("tiffdump", outPath, geotiffFilePath, "-m 10000"));
					stdin.println("echo 'cat " + outPath + "'");
					stdin.println("cat " + outPath);
					stdin.flush();
					// stdin.println("cat " + geotiffFilePath);
				}
			}
			int returnCode;
			try {
				returnCode = p.waitFor();

				InputStream inputStream = new FileInputStream(outTempFile.getAbsolutePath());
				suite.setAttribute(SuiteAttribute.TEST_SUBJECT.getName(),
						IOUtils.toString(inputStream, StandardCharsets.UTF_8));
				inputStream.close();
				// System.out.println((String)
				// suite.getAttribute(SuiteAttribute.TEST_SUBJECT.getName()));

			}
			catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static String prepPath(String string) {
		string = string.replace("jar:", "");
		string = string.replace("file:", "");
		if (SystemUtils.IS_OS_WINDOWS)
			string = string.replace("/", "\\");
		else
			string = string.replace("\\", "/");

		while (string.charAt(0) == '\\' || string.charAt(0) == '/') {
			string = string.substring(1);
		}
		// System.out.println("Path prepped: " + string);
		return string;
	}

	/**
	 * Starts a command prompt
	 * @return
	 */
	private static Process startCmd() {
		String command; // = { "cmd", };

		if (SystemUtils.IS_OS_WINDOWS) {
			command = "cmd";
		}
		else {
			command = "bash";
		}

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			return p;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Dereferences the given URI and stores the resulting resource representation in a
	 * local file. The file will be located in the default temporary file directory.
	 * @param uriRef An absolute URI specifying the location of some resource.
	 * @return A File containing the content of the resource; it may be empty if
	 * resolution failed for any reason.
	 * @throws java.io.IOException If an IO error occurred.
	 */
	public static File dereferenceURI(URI uriRef) throws IOException {
		if ((null == uriRef) || !uriRef.isAbsolute()) {
			throw new IllegalArgumentException("Absolute URI is required, but received " + uriRef);
		}
		if (uriRef.getScheme().equalsIgnoreCase("file")) {
			return new File(uriRef);
		}
		Client client = ClientBuilder.newClient();
		Response rsp = client.target(uriRef).request().get();
		String suffix = null;
		if (rsp.getHeaderString(HttpHeaders.CONTENT_TYPE).endsWith("tif")) {
			suffix = ".tif";
		}
		File destFile = File.createTempFile("entity-", suffix);
		if (rsp.hasEntity()) {
			InputStream is = rsp.readEntity(InputStream.class);
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
		TestSuiteLogger.log(Level.FINE,
				"Wrote " + destFile.length() + " bytes to file at " + destFile.getAbsolutePath());
		return destFile;
	}

	/**
	 * Constructs an absolute URI from the given URI reference and a base URI.
	 *
	 * @see <a href="http://tools.ietf.org/html/rfc3986#section-5.2">RFC 3986, 5.2</a>
	 * @param baseURI The base URI; if present, it must be an absolute URI.
	 * @param uriRef A URI reference that may be relative to the given base URI.
	 * @return The resulting URI.
	 */
	public static URI resolveRelativeURI(String baseURI, String uriRef) {
		URI uri = (null != baseURI) ? URI.create(baseURI) : URI.create("");
		if (null != baseURI && null == uri.getScheme()) {
			throw new IllegalArgumentException("Base URI has no scheme component: " + baseURI);
		}
		return uri.resolve(uriRef);
	}

}

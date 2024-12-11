package org.opengis.cite.geotiff11;

import org.opengis.cite.geotiff11.util.ClientUtils;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.w3c.dom.Document;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.core.Response;

/**
 * A supporting base class that sets up a common test fixture. These configuration methods
 * are invoked before those defined in a subclass.
 */
public class CommonFixture {

	/**
	 * Root test suite package (absolute path).
	 */
	public static final String ROOT_PKG_PATH = "/org/opengis/cite/geotiff11/";

	// TODO: are the members below ever set?
	/**
	 * HTTP client component (JAX-RS Client API).
	 */
	protected Client client;

	/**
	 * An HTTP request message.
	 */
	protected ClientRequestContext request;

	/**
	 * An HTTP response message.
	 */
	protected Response response;

	/**
	 * Initializes the common test fixture with a client component for interacting with
	 * HTTP endpoints.
	 * @param testContext The test context that contains all the information for a test
	 * run, including suite attributes.
	 */
	@BeforeClass
	public void initCommonFixture(ITestContext testContext) {
		Object obj = testContext.getSuite().getAttribute(SuiteAttribute.CLIENT.getName());
		if (null != obj) {
			this.client = Client.class.cast(obj);
		}
		obj = testContext.getSuite().getAttribute(SuiteAttribute.TEST_SUBJECT.getName());
		if (null == obj) {
			throw new SkipException("Test subject not found in ITestContext.");
		}
	}

	/**
	 * <p>
	 * clearMessages.
	 * </p>
	 */
	@BeforeMethod
	public void clearMessages() {
		this.request = null;
		this.response = null;
	}

	/**
	 * Obtains the (XML) response entity as a DOM Document. This convenience method wraps
	 * a static method call to facilitate unit testing (Mockito workaround).
	 * @param response A representation of an HTTP response message.
	 * @param targetURI The target URI from which the entity was retrieved (may be null).
	 * @return A Document representing the entity.
	 * @see ClientUtils#getResponseEntityAsDocument
	 */
	public Document getResponseEntityAsDocument(Response response, String targetURI) {
		return ClientUtils.getResponseEntityAsDocument(response, targetURI);
	}

	/**
	 * Builds an HTTP request message that uses the GET method. This convenience method
	 * wraps a static method call to facilitate unit testing (Mockito workaround).
	 * @param endpoint A URI indicating the target resource.
	 * @param qryParams A Map containing query parameters (may be null);
	 * @param mediaTypes A list of acceptable media types; if not specified, generic XML
	 * ("application/xml") is preferred.
	 * @return A ClientRequest object.
	 *
	 * @see ClientUtils#buildGetRequest
	 */
	/*
	 * seems unused public ClientRequestContext buildGetRequest(URI endpoint, Map<String,
	 * String> qryParams, MediaType... mediaTypes) { return
	 * ClientUtils.buildGetRequest(endpoint, qryParams, mediaTypes); }
	 */

}

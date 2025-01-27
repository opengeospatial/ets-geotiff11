package org.opengis.cite.geotiff11.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.logging.LoggingFeature;
import org.opengis.cite.geotiff11.ReusableEntityFilter;
import org.w3c.dom.Document;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.Response;

/**
 * Provides various utility methods for creating and configuring HTTP client components.
 */
public class ClientUtils {

	/**
	 * Builds a client component for interacting with HTTP endpoints. The client will
	 * automatically redirect to the URI declared in 3xx responses. The connection timeout
	 * is 10 s. Request and response messages may be logged to a JDK logger (in the
	 * namespace "com.sun.jersey.api.client").
	 * @return A Client component.
	 */
	public static Client buildClient() {
		ClientConfig config = new ClientConfig();
		// TODO: currently ReusableEntityFilter doesn't do anything
		// config.register(ReusableEntityFilter.class);
		config.property(ClientProperties.FOLLOW_REDIRECTS, true);
		config.property(ClientProperties.CONNECT_TIMEOUT, 10000);
		config.register(new LoggingFeature()); // TODO: verify if this works
		Client client = JerseyClientBuilder.newClient(config);
		client.register(new ReusableEntityFilter());
		return client;
	}

	/**
	 * Constructs a client component that uses a specified web proxy. Proxy authentication
	 * is not supported. Configuring the client to use an intercepting proxy can be useful
	 * when debugging a test.
	 * @param proxyHost The host name or IP address of the proxy server.
	 * @param proxyPort The port number of the proxy listener.
	 * @return A Client component that submits requests through a web proxy.
	 */
	/*
	 * seems unused public static Client buildClientWithProxy(final String proxyHost,
	 * final int proxyPort) { ClientConfig config = new DefaultClientConfig();
	 * config.getProperties().put( ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true); Client
	 * client = new Client(new URLConnectionClientHandler( new HttpURLConnectionFactory()
	 * { SocketAddress addr = new InetSocketAddress(proxyHost, proxyPort); Proxy proxy =
	 * new Proxy(Proxy.Type.HTTP, addr);
	 *
	 * @Override public HttpURLConnection getHttpURLConnection(URL url) throws IOException
	 * { return (HttpURLConnection) url.openConnection(proxy); } }), config);
	 * client.addFilter(new LoggingFilter()); return client; }
	 */

	/**
	 * Builds an HTTP request message that uses the GET method.
	 * @param endpoint A URI indicating the target resource.
	 * @param qryParams A Map containing query parameters (may be null);
	 * @param mediaTypes A list of acceptable media types; if not specified, generic XML
	 * ("application/xml") is preferred.
	 * @return A ClientRequest object.
	 */
	/*
	 * seems unused public static ClientRequestContext buildGetRequest(URI endpoint,
	 * Map<String, String> qryParams, MediaType... mediaTypes) { UriBuilder uriBuilder =
	 * UriBuilder.fromUri(endpoint); if (null != qryParams) { for (Map.Entry<String,
	 * String> param : qryParams.entrySet()) { uriBuilder.queryParam(param.getKey(),
	 * param.getValue()); } } URI uri = uriBuilder.build(); ClientRequest.Builder
	 * reqBuilder = ClientRequest.create(); if (null == mediaTypes || mediaTypes.length ==
	 * 0) { reqBuilder = reqBuilder.accept(MediaType.APPLICATION_XML_TYPE); } else {
	 * reqBuilder = reqBuilder.accept(mediaTypes); } ClientRequest req =
	 * reqBuilder.build(uri, HttpMethod.GET); return req; }
	 */

	/**
	 * Creates a copy of the given MediaType object but without any parameters.
	 * @param mediaType A MediaType descriptor.
	 * @return A new (immutable) MediaType object having the same type and subtype.
	 */
	/*
	 * seems unused public static MediaType removeParameters(MediaType mediaType) { return
	 * new MediaType(mediaType.getType(), mediaType.getSubtype()); }
	 */

	/**
	 * Obtains the (XML) response entity as a JAXP Source object and resets the entity
	 * input stream for subsequent reads.
	 * @return A Source to read the entity from; its system identifier is set using the
	 * given targetURI value (this may be used to resolve any relative URIs found in the
	 * source).
	 * @return A Source to read the entity from; its system identifier is set using the
	 * given targetURI value (this may be used to resolve any relative URIs found in the
	 * source).
	 * @return A Source to read the entity from; its system identifier is set using the
	 * given targetURI value (this may be used to resolve any relative URIs found in the
	 * source).
	 * @param response A representation of an HTTP response message.
	 * @param targetURI The target URI from which the entity was retrieved (may be null).
	 * @return A Source to read the entity from; its system identifier is set using the
	 * given targetURI value (this may be used to resolve any relative URIs found in the
	 * source).
	 */
	public static Source getResponseEntityAsSource(Response response, String targetURI) {
		Source source = response.readEntity(DOMSource.class);
		if (null != targetURI && !targetURI.isEmpty()) {
			source.setSystemId(targetURI);
		}
		if (response.readEntity(InputStream.class).markSupported()) {
			try {
				// NOTE: entity was buffered by client filter
				response.readEntity(InputStream.class).reset();
			}
			catch (IOException ex) {
				Logger.getLogger(ClientUtils.class.getName())
					.log(Level.WARNING, "Failed to reset response entity.", ex);
			}
		}
		return source;
	}

	/**
	 * Obtains the (XML) response entity as a DOM Document and resets the entity input
	 * stream for subsequent reads.
	 * @param response A representation of an HTTP response message.
	 * @param targetURI The target URI from which the entity was retrieved (may be null).
	 * @return A Document representing the entity; its base URI is set using the given
	 * targetURI value (this may be used to resolve any relative URIs found in the
	 * document).
	 */
	public static Document getResponseEntityAsDocument(Response response, String targetURI) {
		DOMSource domSource = (DOMSource) getResponseEntityAsSource(response, targetURI);
		Document entityDoc = (Document) domSource.getNode();
		entityDoc.setDocumentURI(domSource.getSystemId());
		return entityDoc;
	}

}

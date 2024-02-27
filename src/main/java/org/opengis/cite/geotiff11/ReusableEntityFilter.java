package org.opengis.cite.geotiff11;

import java.io.IOException;

import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

/**
 * Buffers the (response) entity so it can be read multiple times.
 *
 * <p><strong>WARNING:</strong> The entity InputStream must be reset after each
 * read attempt.</p>
 *
 * TODO : currently this doesn't do anything - not sure if it is actually used
 */
@Provider
public class ReusableEntityFilter implements ClientResponseFilter {

    @Override
    public void filter​(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        // leave request entity--it can usually be read multiple times
        // https://javadoc.io/doc/org.glassfish.jersey.bundles/jaxrs-ri/3.0.2/jakarta/ws/rs/client/ClientRequestContext.html;
        if (responseContext.hasEntity()) {
           // TODO : ClientResponseContext has no bufferEntity function
           //        could probably to the same with getEntityStreat + setEntityStream
           //respone.bufferEntity();
        }
    }

}

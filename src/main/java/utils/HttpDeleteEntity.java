package utils;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * HttpDetele不支持设置entity参数，这里对HttpDetele进行扩展，使其支持设置entity参数
 */
public class HttpDeleteEntity extends HttpEntityEnclosingRequestBase {

    public static final String METHOD_NAME = "DELETE";

    @Override
    public String getMethod() { return METHOD_NAME; }

    public HttpDeleteEntity(final String uri) {
        super();
        setURI(URI.create(uri));
    }
    public HttpDeleteEntity(final URI uri) {
        super();
        setURI(uri);
    }
    public HttpDeleteEntity() { super(); }
}

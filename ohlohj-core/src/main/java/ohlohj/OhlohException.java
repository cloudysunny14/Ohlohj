package ohlohj;

import java.util.List;

import ohlohj.internal.http.HttpResponse;

/**
 * 
 * @author cloudysunny14
 *
 */
public class OhlohException  extends Exception {

	private static final long serialVersionUID = 8808484540770312330L;
	private int statusCode = -1;
	private HttpResponse response;
	
	public OhlohException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public OhlohException(String message) {
        this(message, (Throwable) null);
    }
	
	public OhlohException(Exception cause) {
        this(cause.getMessage(), cause);
    }
	
	public OhlohException(String message, HttpResponse res) {
        this(message);
        response = res;
        this.statusCode = res.getStatusCode();
    }
	
	public int getStatusCode() {
        return this.statusCode;
    }
	
	public String getResponseHeader(String name) {
        String value = null;
        if (response != null) {
            List<String> header = response.getResponseHeaderFields().get(name);
            if (header.size() > 0) {
                value = header.get(0);
            }
        }
        return value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((response == null) ? 0 : response.hashCode());
		result = prime * result + statusCode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OhlohException other = (OhlohException) obj;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		if (statusCode != other.statusCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OhlohException [statusCode=" + statusCode + ", response="
				+ response + "]";
	}

}

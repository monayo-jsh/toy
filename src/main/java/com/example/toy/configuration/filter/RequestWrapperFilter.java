package com.example.toy.configuration.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;


public class RequestWrapperFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
		// Do nothing
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		ReadableRequestWrapper wrapper = new ReadableRequestWrapper((HttpServletRequest)request);
		chain.doFilter(wrapper, response);
	}

	@Override
	public void destroy() {
		// Do nothing
	}

	public class ReadableRequestWrapper extends HttpServletRequestWrapper {
		private final Charset encoding;
		private byte[] rawData;
		private boolean isMultipart = false;
		private InputStream inputStream;
		private Map<String, String[]> params = new HashMap<>();

		public ReadableRequestWrapper(HttpServletRequest request) {
			super(request);

			for(String key : request.getParameterMap().keySet()){
 				params.put(key, request.getParameterValues(key));
 			}

			String charEncoding = request.getCharacterEncoding(); // 인코딩 설정
			this.encoding = ObjectUtils.isEmpty(charEncoding) ? StandardCharsets.UTF_8 : Charset.forName(charEncoding);

			try {
				inputStream = request.getInputStream();

				if (request.getContentType() != null && request.getContentType().contains("multipart/form-data")) { // 파일 업로드시 로깅제외
					isMultipart = true;
				} else {
					this.rawData = StreamUtils.copyToByteArray(this.inputStream); // InputStream 을 별도로 저장한 다음 getReader() 에서 새 스트림으로 생성
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public String getParameter(String name) {
			String[] paramArray = getParameterValues(name);
			if (paramArray != null && paramArray.length > 0) {
				return paramArray[0];
			} else {
				return null;
			}
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			return Collections.unmodifiableMap(params);
		}

		@Override
		public Enumeration<String> getParameterNames() {
			return Collections.enumeration(params.keySet());
		}

		@Override
		public String[] getParameterValues(String name) {
			String[] result = null;
			String[] dummyParamValue = params.get(name);

			if (dummyParamValue != null) {
				result = new String[dummyParamValue.length];
				System.arraycopy(dummyParamValue, 0, result, 0, dummyParamValue.length);
			}
			return result;
		}

		public void setParameter(String name, String value) {
			String[] param = {value};
			setParameter(name, param);
		}

		public void setParameter(String name, String[] values) {
			params.put(name, values);
		}

		@Override
		public ServletInputStream getInputStream()
		{
	        return  new CustomServletInputStream(this.rawData);
		}


		@Override
		public BufferedReader getReader() {
			return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
		}

	}

	private static class CustomServletInputStream extends ServletInputStream {

        private ByteArrayInputStream buffer;

        public CustomServletInputStream(byte[] contents) {
            this.buffer = new ByteArrayInputStream(contents);
        }

        @Override
        public int read() throws IOException {
            return buffer.read();
        }

        @Override
        public boolean isFinished() {
            return buffer.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new RuntimeException("Not implemented");
        }
    }

}


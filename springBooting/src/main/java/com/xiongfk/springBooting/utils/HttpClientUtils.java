package com.xiongfk.springBooting.utils;

import com.xiongfk.springBooting.base.BaseCommonLog;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HTTP/HTTPS请求绕过证书检测代码实现
 * @author xiongfukun
 * Description:TODO
 * @date 2018年9月26日
 * @version V.1.0
 */
public class HttpClientUtils extends BaseCommonLog {
	private static  RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间

	private static CloseableHttpClient httpclient = HttpClients.createDefault();

	public static String send(String httpUrl, String message) throws IOException {
		String result = null ;
		HttpPost httpPost = new HttpPost(httpUrl);
		//设置数据读取超时5s   传输超时5s    链接请求超时5s
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(5000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).build();
		httpPost.setConfig(requestConfig) ;
		message = URLEncoder.encode(message, "UTF-8") ;
		StringEntity entity = new StringEntity(message);
		httpPost.setEntity(entity);
		CloseableHttpResponse response = httpclient.execute(httpPost);
		BufferedReader in = null ;
		try {
			InputStream content = response.getEntity().getContent() ;
			in = new BufferedReader(new InputStreamReader(content));
			StringBuilder sb = new StringBuilder();
			String line = "" ;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			result = sb.toString() ;
			logger.info("接收原始报文：" + URLDecoder.decode(result, "UTF-8")) ;
		} finally {
			EntityUtils.consume(response.getEntity());
			response.close();
		}
		return result ;
	}

	/**
	 * 发起post请求，请求参数以Map集合形式传入，封装到List <NameValuePair> 发起post请求
	 * @param httpUrl
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postData(String httpUrl, Map<String, String> params) throws Exception {
		String result = null ;
		CloseableHttpClient httpclient = createSSLClientDefault();
		//httpclient.
		BufferedReader in = null ;
		HttpPost httpPost = new HttpPost(httpUrl);
		httpPost.setConfig(requestConfig);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		StringBuffer paramsBuf = new StringBuffer() ;
		for(Entry<String, String> e : params.entrySet()) {
			nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
			paramsBuf.append("&").append(e.getKey()).append("=").append(e.getValue()) ;
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		try {
			//报文参数27：&id=jn-3-767744&groupPlatProTerminalId=119667&extend=uwJZ8j3CkpGPL4rM5J6KJhjR99O7yAe3BAGLS8ooI8ijNqKHfzTaK6W9wQvjZEVOmWJ3HxFb2O9D
			//wDbe3++UiQ==&xxtCode=370000&terminalType=1&role=3&type=3
			logger.info("post请求报文地址：" + httpUrl+"?"+paramsBuf.toString()) ;
			CloseableHttpResponse response = httpclient.execute(httpPost);
			InputStream content = response.getEntity().getContent() ;
			in = new BufferedReader(new InputStreamReader(content, "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line = "" ;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			result = sb.toString() ;
			logger.info("响应报文：" + result) ;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		} finally {
			httpclient.close();
		}
		return result;
	}

	public static String post(String httpUrl, Object obj) throws Exception {
		Map<String, String>  params = getParamData(obj);
		String result = null ;
		try {
			result = post(httpUrl,params);
			return result ;
		} catch (Exception e) {
			e.printStackTrace() ;
		} finally {
			httpclient.close();
		}
		return null ;
	}

	private static Map<String, String>  getParamData(Object obj) {
		Class cla = obj.getClass();
		Map<String, String> map = new HashMap<String, String>();
		Method[] methods = cla.getDeclaredMethods();
		try {
			for (Method m : methods) {
				String mname = m.getName();
				if (mname.startsWith("get")) {
					String name = mname.substring(3, mname.length());// 截取字段
					name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());// 把首字母变小写
					String value = m.invoke(obj)==null?"":m.invoke(obj).toString();
					map.put(name,value);// 取值
				}
			}
			Class superclass = cla.getSuperclass();
			while (!superclass.equals(Object.class)) {
				Method[] superclassmethods = superclass.getDeclaredMethods();
				for (Method m : superclassmethods) {
					String mname = m.getName();
					if (mname.startsWith("get")) {
						String name = mname.substring(3, mname.length());// 截取字段
						name = name.substring(0, 1).toLowerCase()
								+ name.substring(1, name.length());// 把首字母变小写
						String value = m.invoke(obj)==null?"":m.invoke(obj).toString();
						map.put(name,value);// 取值
					}
				}
				superclass = superclass.getSuperclass();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return map;
	}

	public static CloseableHttpClient createSSLClientDefault() throws Exception {
		try {
			//SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			// 在JSSE中，证书信任管理器类就是实现了接口X509TrustManager的类。我们可以自己实现该接口，让它信任我们指定的证书。
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			//信任所有
			X509TrustManager x509mgr = new X509TrustManager() {
				//　该方法检查客户端的证书，若不信任该证书则抛出异常
				public void checkClientTrusted(X509Certificate[] xcs, String string) {
				}
				//该方法检查服务端的证书，若不信任该证书则抛出异常
				public void checkServerTrusted(X509Certificate[] xcs, String string) {
				}
				// 　返回受信任的X509证书数组。
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(null, new TrustManager[] { x509mgr }, null);
			////创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			//  HttpsURLConnection对象就可以正常连接HTTPS了，无论其证书是否经权威机构的验证，只要实现了接口X509TrustManager的类MyX509TrustManager信任该证书。
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			throw new Exception(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		// 创建默认的httpClient实例.
		return  HttpClients.createDefault();
	}
	
	/**
	 * 测试类
	 * @param args
	 * @throws Exception
	 */
//	public static void main(String[] args) throws Exception {
//		String requestxml="<EbizReq> <EbizHead> <SourceCode>SDB01</SourceCode> <TransType>903</TransType> <SerialNo>189000019776</SerialNo> <Asyn>N</Asyn> <CallBackUrl></CallBackUrl> <BusinessNo>BZCF10015851</BusinessNo> </EbizHead> <TransInfo> <GradedHealthNoticeSerialNo>JG0000111001</GradedHealthNoticeSerialNo> <UnderwritingResults>Y</UnderwritingResults> </TransInfo> </EbizReq>";
//		Map<String,String> paramMap = new HashMap<String,String>();
//		String greatSignKey = ReportSecurityUtil.encryptReportByBody(requestxml);
//		String xml = SecurityUtil.aesEncrypt(requestxml, "FOSUN2016");
//		System.out.println(xml);
//		System.out.println("签名--"+greatSignKey);
//		paramMap.put("request_xml", xml);
//		paramMap.put("ebiz_sign", greatSignKey);
//		String response = postData("https://interface.winbaoxian.cn/thridparty/callback/fxlhIntelligCallback", paramMap);
//        String newStr = response.replaceAll(" ","\n");
//		System.out.println("==================\n"+newStr);
//	}
}
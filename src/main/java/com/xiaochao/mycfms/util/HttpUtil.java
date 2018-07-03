package com.xiaochao.mycfms.util;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import javax.annotation.Resource;

@SuppressWarnings("deprecation")
public class HttpUtil {

	@Resource(name = "configure4OSS")
	private static Configuration configure4OSS;

	/**
	 * 调用URLAPI
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static JSONObject httpApiByPost(String url, String parameters) {
		LogUtil.logInfo("Starting httpAPI  ");
		BufferedReader reader = null;
		String strMessage = "";
		StringBuffer buffer = new StringBuffer();
		JSONObject jsonObject = null;
		try {
			// 接报文的地址
			URL uploadServlet = new URL(url);
			HttpURLConnection servletConnection = (HttpURLConnection) uploadServlet.openConnection();
			// 设置连接参数
			servletConnection.setRequestMethod("POST");
			servletConnection.setDoOutput(true);
			servletConnection.setDoInput(true);
			servletConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			servletConnection.setConnectTimeout(10000);
			servletConnection.setReadTimeout(10000);
			servletConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

			// Map<String, List<String>> headerFields =
			// servletConnection.getHeaderFields();
			// System.out.println(headerFields);
			// 开启流，写入JSON数据
			OutputStreamWriter output = new OutputStreamWriter(servletConnection.getOutputStream());
			StringBuffer strbuf = new StringBuffer();
			// strbuf.append("{'productId':'220'}");
			strbuf.append(parameters);
			output.write(strbuf.toString());
			output.flush();
			output.close();
			// 获取返回的数据
			InputStream inputStream = servletConnection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream));
			while ((strMessage = reader.readLine()) != null) {
				buffer.append(strMessage);
			}
			jsonObject = JSONObject.parseObject(buffer.toString());
			jsonObject = jsonExcludeNull(jsonObject);
			return jsonObject;
		} catch (Exception e) {
			// LogUtil.logError("调用httpAPI出错,url:" + url + ",parameters:" +
			// parameters, e);
//			LogUtil.logError(BaseMsgId.BC10000071E.getMessage(),
//					new Exception("调用httpAPI出错,url:" + url + ",parameters:" + parameters, e));
			return null;
		} finally {
			LogUtil.logInfo("Ending httpAPI");
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// LogUtil.logError("调用httpAPI关闭输入流时出错", e);
				LogUtil.logInfo("调用httpAPI关闭输入流时出错");
//				LogUtil.logError(BaseMsgId.BC10000071E.getMessage(),
//						new Exception("调用httpAPI出错,url:" + url + ",parameters:" + parameters, e));
			}
		}

	}

	/**
	 * 调用URLAPI
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static JSONObject httpApiByPostHX(String url, String parameters) {
		// LogUtil.logInfo("Starting httpAPI ");
		BufferedReader reader = null;
		String strMessage = "";
		StringBuffer buffer = new StringBuffer();
		JSONObject jsonObject = null;
		try {
			// 接报文的地址
			URL uploadServlet = new URL(url);
			HttpURLConnection servletConnection = (HttpURLConnection) uploadServlet.openConnection();
			// 设置连接参数
			servletConnection.setRequestMethod("POST");
			servletConnection.setDoOutput(true);
			servletConnection.setDoInput(true);
			servletConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			servletConnection.setConnectTimeout(15000);
			servletConnection.setReadTimeout(15000);
			// servletConnection.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded;charset=utf-8");
			servletConnection.setRequestProperty("Content-Type", "application/json");

			// Map<String, List<String>> headerFields =
			// servletConnection.getHeaderFields();
			// System.out.println(headerFields);
			// 开启流，写入JSON数据
			OutputStreamWriter output = new OutputStreamWriter(servletConnection.getOutputStream(), "utf8");
			StringBuffer strbuf = new StringBuffer();
			// strbuf.append("{'productId':'220'}");
			strbuf.append(parameters);
			output.write(strbuf.toString());
			output.flush();
			output.close();
			// 获取返回的数据
			InputStream inputStream = servletConnection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, "utf8"));
			while ((strMessage = reader.readLine()) != null) {
				buffer.append(strMessage);
			}
			jsonObject = JSONObject.parseObject(buffer.toString());
			jsonObject = jsonExcludeNull(jsonObject);
			return jsonObject;
		} catch (Exception e) {
			// LogUtil.logError("调用httpAPI出错,url:" + url + ",parameters:" +
			// parameters, e);
//			LogUtil.logError(BaseMsgId.BC10000071E.getMessage(),
//					new Exception("调用httpAPI出错,url:" + url + ",parameters:" + parameters, e));
			return null;
		} finally {
			// LogUtil.logInfo("Ending httpAPI");
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// LogUtil.logError("调用httpAPI关闭输入流时出错", e);
				LogUtil.logInfo("调用httpAPI关闭输入流时出错");
//				LogUtil.logError(BaseMsgId.BC10000071E.getMessage(),
//						new Exception("调用httpAPI出错,url:" + url + ",parameters:" + parameters, e));
			}
		}
	}

	

	/**
	 * JSONObject的value去空，Spring mvc传JSONObject至页面value为空会报错
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject jsonExcludeNull(JSONObject json) {
		String jsonString = json.toString();
		jsonString = jsonString.replaceAll(":null", ":''");
		JSONObject jsonDispose = JSONObject.parseObject(jsonString);
		return jsonDispose;
	}

	public static String md5(String source) {

		StringBuffer sb = new StringBuffer(32);

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(source.getBytes("utf-8"));

			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toLowerCase().substring(1, 3));
			}
		} catch (Exception e) {
			return null;
		}

		return sb.toString();
	}

	/**
	 * 函数功能：用httpClient模仿表单的方式上传文件
	 * 
	 * @param serverUrl
	 *            服务端IP
	 * @param uploadFilePath
	 *            要上传的文件路径
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String uploadFileWithHttpForm(String serverUrl, String uploadFilePath) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 请求处理页面
		HttpPost httppost = new HttpPost(serverUrl);
		// 创建待处理的文件
		FileBody file = new FileBody(new File(uploadFilePath));
		// 对请求的表单域进行填充
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("file", file);

		// 设置请求
		httppost.setEntity(reqEntity);
		// 执行请求
		HttpResponse response;
		StringBuffer respStr = new StringBuffer();
		try {
			response = httpclient.execute(httppost);
			// 获取响应
			HttpEntity httpEntity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"));
			respStr = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				respStr.append(line);
			}

		} catch (ClientProtocolException e) {
			LogUtil.logError(e);
		} catch (IOException e) {
			LogUtil.logError(e);
		}

		return respStr.toString();
	}

	public static void downloadFile(String serverUrl, String downloadFilePath) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(serverUrl);
		try {
			HttpResponse response = httpclient.execute(httpget);
			if (200 != (response.getStatusLine().getStatusCode())) {
				LogUtil.logError("response:"+response);
//				LogUtil.logError(BaseMsgId.BC10000249E.getMessage());
			}
			HttpEntity entity = response.getEntity();
			InputStream in = entity.getContent();
			File file = new File(downloadFilePath);
			try {
				FileOutputStream fout = new FileOutputStream(file);
				int l = -1;
				byte[] tmp = new byte[1024];
				while ((l = in.read(tmp)) != -1) {
					fout.write(tmp, 0, l);
				}
				fout.flush();
				fout.close();
			} finally {
				in.close();
			}
			httpclient.close();

		} catch (ClientProtocolException e) {
			LogUtil.logError(e);
		} catch (IOException e) {
			LogUtil.logError(e);
		}

	}


    
	/**
	 * Description:
	 * @author:admin
	 * @date:2017年7月18日
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static JSONObject httpApiByPostUserCenter(String url, String parameters) {
        BufferedReader reader = null;
        String strMessage = "";
        StringBuffer buffer = new StringBuffer();
        JSONObject jsonObject = null;
        try {
            // 接报文的地址
            URL uploadServlet = new URL(url);
            HttpURLConnection servletConnection = (HttpURLConnection) uploadServlet.openConnection();
            // 设置连接参数
            servletConnection.setRequestMethod("POST");
            servletConnection.setDoOutput(true);
            servletConnection.setDoInput(true);
            servletConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            servletConnection.setConnectTimeout(15000);
            servletConnection.setReadTimeout(15000);
            servletConnection.setRequestProperty("Content-Type", "text/plain");

            // 开启流，写入JSON数据
            OutputStreamWriter output = new OutputStreamWriter(servletConnection.getOutputStream(), "utf8");
            StringBuffer strbuf = new StringBuffer();
            strbuf.append(parameters);
            output.write(strbuf.toString());
            output.flush();
            output.close();
            // 获取返回的数据
            InputStream inputStream = servletConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, "utf8"));
            while ((strMessage = reader.readLine()) != null) {
                buffer.append(strMessage);
            }
            jsonObject = JSONObject.parseObject(buffer.toString());
            jsonObject = jsonExcludeNull(jsonObject);
            return jsonObject;
        } catch (Exception e) {
//            LogUtil.logError(BaseMsgId.BC10000071E.getMessage(),
//                    new Exception("调用httpAPI出错,url:" + url + ",parameters:" + parameters, e));
            return null;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                LogUtil.logInfo("调用httpAPI关闭输入流时出错");
//                LogUtil.logError(BaseMsgId.BC10000071E.getMessage(),
//                        new Exception("调用httpAPI出错,url:" + url + ",parameters:" + parameters, e));
            }
        }
	}
	public static void main(String[] args) {
		String url = "http://sa.huixing.com/api/ldap/check";
		String parameters = "app=4a&username=hao.qiao&password=qiaohao201314";
		try {
			JSONObject jo = httpApiByPost(url, new String(parameters.getBytes("utf-8"), "utf-8"));
			System.out.println(jo.get("retcode"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

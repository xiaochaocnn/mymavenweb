package com.xiaochao.mycfms.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 处理文件工具类
 * 
 * @author qh
 *
 */
public class FileUtil {

	/**
	 * 
	 * @Title: uploadLargeFile
	 * @Description: 上传大文件
	 * @param configure4OSS
	 * @param fileName
	 * @return
	 * @author hao.qiao
	 */
	public static JSONObject uploadLargeFile(Configuration configure4OSS, String fileName) {

		String serverUrl = configure4OSS.getValue("largeUploadserverUrl"); // 文件存储服务器的url
																			// http://openapi.bbtfax.com/file/largeupload
		String appkey = configure4OSS.getValue("appkey"); // 渠道码
		String appsecret = configure4OSS.getValue("appsecret");// 密钥
		String localFilePath = configure4OSS.getValue("101_localCategory");// 文件写到本地目录
		String category = configure4OSS.getValue("101_category");
		String business = configure4OSS.getValue("101_business");// 业务标识
		String t = "" + System.currentTimeMillis() / 1000; // 格林威治时间

		String data = "{\"category\":\"" + category + "\"}"; // 用于验签
		String tag = "";
		try {
			tag = URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// LogUtil.logError("encodeURI编码异常：" + e);
			e.printStackTrace();
		}
		String m = HttpUtil.md5(t + appsecret + data);
		serverUrl = serverUrl + "?appkey=" + appkey + "&category=" + category + "&t=" + t + "&m=" + m.substring(0, 12)
				+ "&filename=" + fileName + "&business=" + business + "&tag=" + tag;
		String temp = HttpUtil.uploadFileWithHttpForm(serverUrl, localFilePath + fileName);
		JSONObject object = JSON.parseObject(temp);
		// LogUtil.logInfo("大文件接口返回结果：" + object);
		if (object != null && "2000000".equals(object.getString("retcode"))) {
			// LogUtil.logInfo("uploadfile success");
			return object;
		} else {
			// LogUtil.logError(BaseMsgId.BC10000250E.getMessage());
			return null;
		}
	}

	@SuppressWarnings("restriction")
	public static String downLoadLargeFile(Configuration configure4OSS, String fileName, String localFileName) {

		try {
			if (!"".equals(fileName) && fileName != null) {
				fileName = (new sun.misc.BASE64Encoder()).encode(URLEncoder.encode(fileName, "UTF-8").getBytes())
						.replaceAll("\r|\n|\t", "");
			} else {
				// LogUtil.logError(BaseMsgId.BC10000251E.getMessage());
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			// LogUtil.logError("远程文件地址编码失败", e);
		}

		String serverUrl = configure4OSS.getValue("largeDownloadserverUrl"); // 文件存储服务器的url
		String appkey = configure4OSS.getValue("appkey"); // 渠道码
		String appsecret = configure4OSS.getValue("appsecret");// 密钥
		String localFilePath = configure4OSS.getValue("101_localCategory");// 文件写到本地目录
		String business = configure4OSS.getValue("101_business");// 业务标识

		String time = "" + System.currentTimeMillis() / 1000; // 格林威治时间
		String m = HttpUtil.md5(time + appsecret + fileName + "{}");
		serverUrl = serverUrl + "?appkey=" + appkey + "&t=" + time + "&m=" + m.substring(0, 12) + "&path=" + fileName
				+ "&business=" + business + "&tag=%7b%7d";
		String fileLocation = localFilePath + localFileName;
		HttpUtil.downloadFile(serverUrl, localFilePath + localFileName);
		return fileLocation;

	}

	/**
	 * 
	 * @Title: processFile
	 * @Description: 结算单明细写入文件
	 * @param fieldNames
	 *            字段名
	 * @param dataset
	 *            数据集合
	 * @param firstLine
	 *            第一行数据
	 * @author qh
	 */
	public static <T> boolean processFile(BufferedWriter writer, String fileName, String[] fieldNames, List<T> dataset,
			String smentNo) {
		// 第一行：结算单号，日期，金额总和，条数
		StringBuffer sb = null;
		boolean flag = false;
		try {

			// 遍历集合数据，产生数据行
			for (int i = 0; i < dataset.size(); i++) {
				sb = new StringBuffer();
				JSONObject obj = JSONObject.parseObject(JSON.toJSONString(dataset.get(i)));
				for (int j = 0; j < fieldNames.length; j++) {
					if (j == 1) {
						sb.append(smentNo + ",");
					}
					sb.append(obj.get(fieldNames[j]) + ",");// 获取值

				}
				writer.write(sb.substring(0, sb.length() - 1) + System.getProperty("line.separator"));
			}

			writer.flush();
			flag = true;
		} catch (FileNotFoundException e) {
			// LogUtil.logError("文件写入异常", e);
		} catch (IOException e) {
			// LogUtil.logError("文件写入异常", e);
		}
		return flag;

	}

	/**
	 * 
	 * @Title: writeFirstLine
	 * @Description: 在文件第一行写入内容
	 * @author qh
	 */
	public static void writeFirstLine(String content, String filePath) {
		RandomAccessFile randomFile = null;
		try {
			randomFile = new RandomAccessFile(filePath, "rw");
			randomFile.seek(0);
			randomFile.write(content.toString().getBytes("UTF-8"));
		} catch (FileNotFoundException e) {
			// LogUtil.logError("未找到文件异常" + e);
			e.printStackTrace();
		} catch (IOException e) {
			// LogUtil.logError("文件写入异常" + e);
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e) {
					// LogUtil.logError("文件写入异常" + e);
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * deleteFile Method:删除本地文件，递归删除
	 *
	 * @param @param
	 *            delpath
	 * @param @return
	 * @param @throws
	 *            FileNotFoundException
	 * @param @throws
	 *            IOException
	 * @return boolean
	 * @throws @since
	 *             CodingExample Ver 1.0
	 */

	public static boolean deleteFile(String delpath) throws FileNotFoundException, IOException {
		try {
			File file = new File(delpath);
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				File[] fileList = file.listFiles();
				for (int i = 0; i < fileList.length; i++) {
					File delfile = fileList[i];
					if (!delfile.isDirectory()) {
						// LogUtil.logInfo("相对路径=" + delfile.getPath());
						// LogUtil.logInfo("绝对路径=" + delfile.getAbsolutePath());
						// LogUtil.logInfo("文件全名=" + delfile.getName());
						// delfile.delete();
						// LogUtil.logInfo("删除文件成功");
					} else if (delfile.isDirectory()) {
						deleteFile(fileList[i].getPath());
					}
				}
				file.delete();
			}
		} catch (FileNotFoundException e) {
			// LogUtil.logInfo("deletefile() Exception:" + e.getMessage());
		}
		return true;
	}

}

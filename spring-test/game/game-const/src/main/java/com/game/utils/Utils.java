package com.game.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.servlet.http.HttpServletRequest;

import com.google.protobuf.ByteString;

public class Utils {

	public static int BUFFER_SIZE = 2048;

	public static String bytesToString(ByteString src, String charSet) {
		if (StringUtils.isEmpty(charSet)) {
			charSet = "UTF-8";
		}
		return bytesToString(src.toByteArray(), charSet);
	}

	public static String bytesToString(byte[] input, String charSet) {
		if (input == null || input.length == 0) {
			return "";
		}
		ByteBuffer buffer = ByteBuffer.allocate(input.length);
		buffer.put(input);
		buffer.flip();
		Charset charset = null;
		CharsetDecoder decoder = null;
		CharBuffer charBuffer = null;
		try {
			charset = Charset.forName(charSet);
			decoder = charset.newDecoder();
			charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
			return charBuffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String getCurrentPath() {
		int index = -1;
		String path = Utils.class.getProtectionDomain().getCodeSource().getLocation().getPath();

		index = path.indexOf("classes");
		if (index != -1) {
			path = path.substring(0, index) + "classes/";
			return path;
		}

		index = path.indexOf("lib");
		if (index != -1) {
			path = path.substring(0, index) + "lib/";
			return path;
		}

		index = path.indexOf("bin");
		if (index != -1) {
			path = path.substring(0, index) + "bin/";
			return path;
		}

		return path;
	}

	private static Random r = new Random();

	/**
	 * 返回随机的数字 1~upLimit
	 * 
	 * @param upLimit
	 * @return
	 */
	public static int getRandomInt(int upLimit) {

		if (upLimit <= 0) {
			return 0;
		} else {
			return r.nextInt(upLimit) + 1;
		}

	}

	/**
	 * * 返回随机的数字
	 * 
	 * @return
	 */
	public static long getRandomLong() {
		return r.nextLong();
	}

	/***
	 * 获取upLimit 以内 size个数量的随机数 数组
	 * 
	 * @param upLimit
	 * @param size
	 * @return
	 */
	public static Integer[] getRandomIntArray(int upLimit, int size) {
		Integer[] result = null;
		if (upLimit <= size) {
			result = new Integer[upLimit];
			for (int i = 0; i < upLimit; i++) {
				result[i] = i;
			}
		} else {
			Set<Integer> tempSet = new HashSet<Integer>();
			result = new Integer[size];
			int index = 0;
			int pollNum = 0;
			while (tempSet.size() < size && pollNum < 25 * size) {
				Integer temp = getRandomInt(upLimit) - 1;
				if (temp < 0) {
					temp = 0;
				}
				if (!tempSet.contains(temp)) {
					result[index] = temp;
					index++;
					tempSet.add(temp);
				}
				pollNum++;
			}
		}
		return result;
	}

	/**
	 * 
	 * 随机出一个区域
	 * 
	 * @param all
	 * @return
	 */
	public static int getRandomArray(List<Integer> all) {

		int index = 0;

		int max = 0;

		for (int i = 0; i < all.size(); i++) {
			max = max + all.get(i);
		}

		int the = getRandomInt(max);

		int end = 0;

		for (int j = 0; j < all.size(); j++) {
			end = end + all.get(j);
			if (the <= end) {
				index = j;
				break;
			}
		}
		return index;
	}

	public static InputStream decompress(InputStream inputStream) throws Exception {
		byte[] inputByte = inputStreamTOByte(inputStream);
		byte[] output = decompressBytes(inputByte);
		return byteTOInputStream(output);
	}

	public static InputStream compress(InputStream outputStream) throws Exception {
		byte[] outputByte = inputStreamTOByte(outputStream);
		Inflater decompresser = new Inflater();
		decompresser.setInput(outputByte);
		byte[] result = new byte[BUFFER_SIZE];
		decompresser.end();
		return byteTOInputStream(result);
	}

	public static ByteBuffer compress(ByteBuffer byteBuffer) throws Exception {
		byte[] outputByte = byteBuffer.array();
		byte[] result = compressBytes(outputByte);
		return ByteBuffer.wrap(result);
	}

	// 将InputStream转换成byte数组
	public static byte[] inputStreamTOByte(InputStream in) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);
		data = null;
		return outStream.toByteArray();
	}

	// 将byte数组转换成InputStream
	public static InputStream byteTOInputStream(byte[] in) throws Exception {
		ByteArrayInputStream is = new ByteArrayInputStream(in);
		return is;
	}

	private static Inflater decompresser = new Inflater(true);
	private static Deflater compresser = new Deflater();

	public static byte[] compressBytes(byte input[]) {
		compresser.reset();
		compresser.setInput(input);
		compresser.finish();
		byte output[] = new byte[0];
		ByteArrayOutputStream o = new ByteArrayOutputStream(input.length);
		try {
			byte[] buf = new byte[BUFFER_SIZE];
			int got;
			while (!compresser.finished()) {
				got = compresser.deflate(buf);
				o.write(buf, 0, got);
			}
			output = o.toByteArray();
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output;
	}

	public static byte[] decompressBytes(byte input[]) {
		decompresser.reset();
		byte output[] = new byte[0];
		decompresser.setInput(input);
		ByteArrayOutputStream o = new ByteArrayOutputStream(input.length);
		try {
			byte[] buf = new byte[BUFFER_SIZE];

			int got;
			while (!decompresser.finished()) {
				got = decompresser.inflate(buf);
				o.write(buf, 0, got);
			}
			output = o.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static double filterRate(double val) {
		if (val == 1.0 || val == 0.5 || val == 0.0) {
			return val;
		}
		return 0.0;
	}

	public static String getTimeStr(long timeMill) {
		DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date data = new Date(timeMill);
		return sdf.format(data);
	}

	public static String readToString(String fileName) {
		String encoding = "UTF-8";
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

	public static String testCmd() {
		execCmd();
		return "success";
	}

	private static void execCmd() {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				String cmd1 = "sh /var/local/apache-tomcat-9.0.13/bin/upconfig.sh";
				execCmd(cmd1);

			}
		};
		new Thread(run).start();
	}

	public static String execCmd(String cmd) {
		Process proc = null;
		String result = "";
		try {
			System.out.println("exec" + cmd);
			Runtime rt = Runtime.getRuntime();
			// 执行命令, 最后一个参数，可以使用new File("path")指定运行的命令的位置
			proc = rt.exec(cmd);
			int procResult = proc.waitFor();
			System.out.println(procResult);
			InputStream stderr = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(stderr, "GBK");
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			proc.destroyForcibly();

		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static int getRandomInt(int min, int max) {
		if (min == max) {
			return min;
		}
		float middle = (float) (max * Math.random());
		if (Math.random() > 0.5) {
			return (int) Math.floor(min + middle);
		}
		return (int) Math.ceil(min + middle);
	}

	public static String getInnerIp() throws Exception {
		InetAddress inetAddress = InetAddress.getLocalHost();
		return inetAddress.getHostAddress();
	}

	public static String listToString(List<String> mList) {
		String convertedListStr = "";
		if (null != mList && mList.size() > 0) {
			String[] mListArray = mList.toArray(new String[mList.size()]);
			for (int i = 0; i < mListArray.length; i++) {
				if (i < mListArray.length - 1) {
					convertedListStr += mListArray[i] + ",";
				} else {
					convertedListStr += mListArray[i];
				}
			}
			return convertedListStr;
		} else
			return "0";
	}
}

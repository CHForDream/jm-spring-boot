/**
 * 
 */
package buffer;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author pky
 *
 */
public class Test {
	public static void main(String[] args) throws InvalidProtocolBufferException {
		String s = "恭喜你在%s内获得第%s名";
		System.out.println(String.format(s, "50002", "2"));
	}
}

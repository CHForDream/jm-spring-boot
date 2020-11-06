package org.leecode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class SpringBootLeecodeApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringBootLeecodeApplication.class);

		ServerSocketChannel socketChannel = ServerSocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.bind(new InetSocketAddress(9000));
		Selector sel = Selector.open();
		socketChannel.register(sel, SelectionKey.OP_ACCEPT);
	}

}

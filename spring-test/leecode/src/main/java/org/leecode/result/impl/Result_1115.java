package org.leecode.result.impl;

import java.util.concurrent.Semaphore;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_1115 implements IResult {

	/**
	 * 我们提供一个类：
	 * 
	 * class FooBar {
	 * public void foo() {
	 *     for (int i = 0; i < n; i++) {
	 *       print("foo");
	 *   }
	 * }
	 * 
	 * public void bar() {
	 *     for (int i = 0; i < n; i++) {
	 *       print("bar");
	 *     }
	 * }
	 * }
	 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
	 * 
	 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
	 * 
	 *  
	 * 
	 * 示例 1:
	 * 
	 * 输入: n = 1
	 * 输出: "foobar"
	 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
	 * 示例 2:
	 * 
	 * 输入: n = 2
	 * 输出: "foobarfoobar"
	 * 解释: "foobar" 将被输出两次。
	 */

	@Override
	public String process() {
		return "";
	}

	@Override
	public String gerneral() {
		return "";
	}

	public Result_1115() {

	}

	private int n;
	private Semaphore fooSemap = new Semaphore(1);
	private Semaphore barSemap = new Semaphore(0);

	public void foo(Runnable printFoo) throws InterruptedException {

		for (int i = 0; i < n; i++) {
			fooSemap.acquire();
			// printFoo.run() outputs "foo". Do not change or remove this line.
			printFoo.run();
			barSemap.release();
		}
	}

	public void bar(Runnable printBar) throws InterruptedException {

		for (int i = 0; i < n; i++) {
			barSemap.acquire();
			// printBar.run() outputs "bar". Do not change or remove this line.
			printBar.run();
			fooSemap.release();
		}
	}
}

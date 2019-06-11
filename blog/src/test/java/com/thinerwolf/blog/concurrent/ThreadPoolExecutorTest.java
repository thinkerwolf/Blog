package com.thinerwolf.blog.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.thinkerwolf.blog.common.concurrent.CustomThreadPoolExecutor;

public class ThreadPoolExecutorTest {
	public static void main(String[] args) {
		// test();
		testCustom();
	}

	private static void testCustom() {
		CustomThreadPoolExecutor pool = new CustomThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(20));
		for (int i = 0; i < 20; i++) {
			final int num = i + 1;
			pool.execute(() -> {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				System.out.println("pool task num " + num);
			});
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		pool.shutdownNow();
	}

	private static void test() {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20));
		for (int i = 0; i < 20; i++) {
			final int num = i + 1;
			pool.execute(() -> {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				System.out.println("pool task num " + num);
			});
		}

		System.err.println("After execute ........");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		pool.shutdown();
	}

}

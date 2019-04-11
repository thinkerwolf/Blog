package com.thinkerwolf.blog.common.generator;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;

public class IdGeneratorTest {

	public static final long startTime = System.currentTimeMillis() - 4 * 365 * 24 * 60 * 60 * 1000;

	@Test
	public void snowflake() {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.set(Calendar.YEAR, 2015);
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		System.out.println(calendar.getTimeInMillis());
		System.out.println(startTime);
		System.out.println(System.currentTimeMillis());

		SnowflakeIdGenerator gen = new SnowflakeIdGenerator(1, 1);
		long startTime = System.currentTimeMillis();
		int size = 0;
		for (;;) {
			// try {
			// Thread.sleep(1);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			String id = gen.nextId();
			System.out.println(gen.nextId());
			size++;
			long endTime = System.currentTimeMillis();
			if (endTime - startTime >= 10) {
				break;
			}
		}
		System.out.println("generateSize " + size + " time " + 10);

	}

	@Test
	public void uuid() {
		UUIDIdGenerator g = new UUIDIdGenerator();
		System.out.println(g.nextId());
	}
}

package com.thinkerwolf.blog.common.generator;

import java.util.Random;

import com.thinkerwolf.blog.common.util.SLI;

/**
 * snowflake {@link https://juejin.im/post/5a7f9176f265da4e721c73a8}
 * 
 * @author wukai
 *
 */
@SLI("snowflake")
public class SnowflakeIdGenerator extends StringIdGenerator {

	public static final long startTime = 1422720000045L; // 2015-1-1 00:00:00

	private Random random;

	/** 数据中心ID */
	private long datacenterId;
	/** 工作机器ID */
	private long workerId;
	/** 序列号 */
	private long secquence = 0L;
	/** 序列号掩码 */
	private final long secquenceMask;

	/** 数据中心ID位数 */
	private int datacenterIdBits = 5;
	/** 工作机器ID位数 */
	private int workerIdBits = 5;
	/** 序列号位数 */
	private int secquenceBits = 12;

	private int timestampShift;
	private int datacenterIdShift;
	private int workerIdShift;

	private volatile long lastTime;

	public SnowflakeIdGenerator(long datacenterId, long workerId, int workerIdBits, int datacenterIdBits,
			int secquenceBits) {
		this.workerId = workerId;
		this.datacenterId = datacenterId;
		this.workerIdBits = workerIdBits;
		this.datacenterIdBits = datacenterIdBits;
		this.secquenceBits = secquenceBits;
		if (!checkIdMax(workerId, workerIdBits)) {
			throw new RuntimeException("workdId overflow");
		}
		if (!checkIdMax(datacenterId, datacenterIdBits)) {
			throw new RuntimeException("datacenterId overflow");
		}
		this.workerIdShift = secquenceBits;
		this.datacenterIdShift = workerIdShift + workerIdBits;
		this.timestampShift = datacenterIdShift + datacenterIdBits;
		this.secquenceMask = maxIdNum(secquenceBits);
	}

	public SnowflakeIdGenerator(long datacenterId, long workerId) {
		this(datacenterId, workerId, 5, 5, 12);
	}

	public int getDatacenterIdBits() {
		return datacenterIdBits;
	}

	public int getWorkerIdBits() {
		return workerIdBits;
	}

	public int getSecquenceBits() {
		return secquenceBits;
	}

	private boolean checkIdMax(long id, int bits) {
		if (id < 0 || id > maxIdNum(bits)) {
			return false;
		}
		return true;
	}

	@Override
	public String nextId() {
		return String.valueOf(nextLongId());
	}

	private synchronized long nextLongId() {
		long ctime = timeGen();
		if (ctime < lastTime) {
			// 时钟回拨，为了不生成重复，暂时使用随机数生产
			if (random == null) {
				random = new Random();
			}
			return random.nextLong();
		} else if (ctime > lastTime) {
			// 不在毫秒内，序号清0
			secquence = 0;
		} else {
			// 在毫秒内，增加序号
			secquence = (secquence + 1) & secquenceMask;
			if (secquence == 0) {
				// 毫秒内溢出，增加新毫秒
				ctime = getTillNextMillis(ctime);
			}
		}
		lastTime = ctime;
		return ctime << timestampShift | datacenterId << datacenterIdShift | workerId << workerIdShift | secquence;
	}

	/**
	 * 时钟回拨时等待
	 * 
	 * @return
	 */
	protected long getTillNextMillis(long lastTime) {
		long ctime = timeGen();
		for (; ctime <= lastTime;) {
			ctime = timeGen();
		}
		return ctime;
	}

	private long timeGen() {
		return System.currentTimeMillis() - startTime;
	}

	public static long maxIdNum(int bits) {
		bits = Math.abs(bits);
		return -1L ^ (-1L << bits);
	}

}

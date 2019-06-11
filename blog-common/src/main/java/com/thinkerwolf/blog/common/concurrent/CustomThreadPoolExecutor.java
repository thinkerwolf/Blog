package com.thinkerwolf.blog.common.concurrent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CustomThreadPoolExecutor {

	private int workerCountOf() {
		mainLock.lock();
		try {
			return workers.size();
		} finally {
			mainLock.unlock();
		}
	}

	private volatile int coreSize;

	private volatile int maxSize;

	private volatile int keepAliveTime;

	private final TimeUnit timeUnit;

	private final BlockingQueue<Runnable> workQueue;

	private final ThreadFactory threadFactory = Executors.defaultThreadFactory();

	private Set<Worker> workers = new HashSet<>();

	private ReentrantLock mainLock = new ReentrantLock();

	private AtomicBoolean shutdown = new AtomicBoolean(false);

	public CustomThreadPoolExecutor(int coreSize, int maxSize, int keepAliveTime, TimeUnit timeUnit,
			BlockingQueue<Runnable> workQueue) {
		this.coreSize = coreSize;
		this.maxSize = maxSize;
		this.keepAliveTime = keepAliveTime;
		this.timeUnit = timeUnit;
		this.workQueue = workQueue;
	}

	public void execute(Runnable task) {

		int wc = workerCountOf();
		if (wc < coreSize) {
			// 1.当前线程数小于核心数，创建线程
			if (addWorker(task, true)) {
				return;
			}
		}

		boolean suc = workQueue.offer(task);
		if (suc) {
			// recheck
			return;
		} else {
			// 创建线程
			if (addWorker(task, false)) {
				return;
			}
			// System.err.println("Reject task " + task);
			// rejection
		}

	}

	private boolean addWorker(Runnable task, boolean core) {

		int wc = workerCountOf();
		if (wc >= (core ? coreSize : maxSize) || wc >= Integer.MAX_VALUE - 1000) {
			return false;
		}

		Worker worker = new Worker(task);
		Thread t = worker.thread;
		if (t != null) {
			ReentrantLock mainLock = this.mainLock;
			mainLock.lock();
			try {
				workers.add(worker);
				t.start();
				System.err.println("Worker add " + t.getName());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mainLock.unlock();
			}
		}
		return false;
	}

	private Runnable getTask() {
		int wc = workerCountOf();
		ReentrantLock lock = this.mainLock;
		lock.lock();
		try {
			if (workQueue.isEmpty() && shutdown.get()) {
				return null;
			}
		} finally {
			lock.unlock();
		}

		boolean timed = wc > coreSize;
		try {
			return timed ? workQueue.poll(keepAliveTime, timeUnit) : workQueue.take();
		} catch (InterruptedException e) {
			return null;
		}

	}

	public void shutdown() {
		ReentrantLock lock = this.mainLock;
		lock.lock();
		try {
			shutdown.getAndSet(true);
		} finally {
			lock.unlock();
		}
	}

	public void shutdownNow() {
		ReentrantLock lock = this.mainLock;
		lock.lock();
		try {
			shutdown.getAndSet(true);
			workQueue.clear();
			for (Worker worker : workers) {
				Thread t = worker.thread;
				if (!t.isInterrupted()) {
					t.interrupt();
				}
			}
		} finally {
			lock.unlock();
		}
	}

	private final class Worker implements Runnable {

		private Runnable firstTask;

		private Thread thread;

		public Worker(Runnable firstTask) {
			this.firstTask = firstTask;
			this.thread = threadFactory.newThread(this);
		}

		@Override
		public void run() {
			Runnable task = this.firstTask;
			this.firstTask = null;
			for (; task != null || (task = getTask()) != null; task = getTask()) {
				Throwable thrown = null;
				try {
					task.run();
				} catch (RuntimeException e) {
					thrown = e;
					throw e;
				} catch (Error e) {
					thrown = e;
					throw new Error(e);
				} catch (Throwable e) {
					thrown = e;
					throw e;
				} finally {
					processWorkExit(this);
				}

			}

		}
	}

	private void processWorkExit(Worker worker) {

		Thread t = worker.thread;
		System.err.println("Worker exit " + t.getName());
		t.interrupt();

		ReentrantLock lock = this.mainLock;
		lock.lock();
		try {
			workers.remove(worker);
		} finally {
			lock.unlock();
		}

		int wc = workerCountOf();
		if (wc < coreSize) {
			addWorker(null, true);
		}

	}

}

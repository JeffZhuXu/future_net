package com.routesearch.test;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main1 {
	public static void main(String[] args) {
		
		int startTime = 0;
		int allCost = 0;
		List<Task> tasks = new LinkedList<Task>();
		List<Task> finishTasks = new LinkedList<Task>();
		int[] a = new int[]{0,1,3,4,9};
		int[] b = new int[]{2,1,7,5,5};
		int taskSize = a.length;
		for (int i = 0; i < taskSize; i++) {
			Task aTask = new Task(a[i], b[i]);
			tasks.add(aTask);
		}
		System.out.println("所有任务");
		for (Task aTask:tasks ) {
			System.out.println("开始时间："+aTask.startTime+"耗时："+aTask.costTime);
		}
		System.out.println("\r\n");
		Collections.sort(tasks);
		System.out.println("排序后所有任务");
		for (Task aTask:tasks ) {
			System.out.println("开始时间："+aTask.startTime+"耗时："+aTask.costTime);
		}
		System.out.println("\r\n");
		System.out.println("开始执行任务：");
		for (int i = 0; i < taskSize; i++) {
			//排序
			//Collections.sort(tasks);
			boolean execute = false;
			
			Iterator iterator = tasks.iterator();
			while (iterator.hasNext()) {
				Task aTask=(Task)iterator.next();
				if (aTask.startTime<=startTime) {
					aTask.addRelayTime(startTime-aTask.startTime);
					allCost+=aTask.relayTime;
					startTime = startTime+aTask.costTime;
					System.out.println("执行任务---"+"开始时间："+aTask.startTime+"耗时："+aTask.costTime+"延时："+aTask.relayTime);
					finishTasks.add(aTask);
					iterator.remove();
					break;
				}
			}
		}
		System.out.println("平均延时："+((float)allCost/taskSize));
	}

	public static class Task implements Comparable<Task> {
		int startTime = 0;
		int relayTime = 0;
		int costTime =  0;
		public Task() {
		}
		public void addRelayTime(int relay) {
			this.relayTime += relay;
		}
		public Task(int startTime, int costTime) {
			this.startTime = startTime;
			this.costTime = costTime;
		}
		public int compareTo(Task obj) {
			Task b = (Task) obj;
			if (this.costTime == b.costTime) {
				return this.startTime-b.startTime;
			}else {
				return (this.costTime - b.costTime);
			}
		}
	}
}

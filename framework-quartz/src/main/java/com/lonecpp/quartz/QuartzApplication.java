package com.lonecpp.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lonecpp.core.annotation.Database;

/**
 * quartz定时任务框架测试
 * 
 * @author seven sins
 * @date 2018年1月10日 下午8:10:03
 * 
 * 1. 分布式并发环境中使用QUARTZ定时任务调度，会在各个节点会上报任务，存到数据库中，
 * 执行时会从数据库中取出触发器来执行，如果触发器的名称和执行时间相同，则只有一个节点去执行此任务。
 * 
 * 2.当相同的任务定时在一个时间点，在那个时间点，不会被两个节点同时执行。
 * Quartz的集群是在同一个数据库下， 由数据库的数据来确定调度任务是否正在执行， 正在执行则其他服务器就不能去执行该行调度数据。
 * 当线程对表中的数据执行操作时，数据库进行行加锁； 于此同时， 另一个线程对该行数据执行操作前需要获取锁， 
 * 而此时已被占用， 那么这个线程就只能等待， 直到该行锁被释放。
 * 
 * 3. 如果此节点执行失败，则此任务则会被分派到另一节点执行，中途也会自动检查失效的定时调度，
 * 发现不成功的，其他节点立马接过来继续完成定时任务。
 * 
 * 4. 一个节点无法完成的任务，会被集群中拥有相同的任务的节点取代执行。
 * 
 * 5. 一个 Quartz 集群中的每个节点是一个独立的 Quartz 应用，它又管理着其他的节点。意思是你必须对每个节点分别启动或停止。
 * 不像许多应用服务器的集群，独立的 Quartz 节点并不与另一其的节点或是管理节点通信。Quartz 应用是通过数据库表来感知到另一应用的。
 * 每个节点直接与数据库通信，若离开数据库将对其他节点一无所知
 */
@Database
@SpringBootApplication
public class QuartzApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}
}

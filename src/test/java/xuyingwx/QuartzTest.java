package xuyingwx;

import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.SimpleScheduleBuilder.*;

public class QuartzTest {
	private static Logger logger = Logger.getLogger(QuartzTest.class.getName());
	public static void main(String[] args) {
		Scheduler scheduler;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			scheduler.start();
			JobDetail job = JobBuilder.newJob(MyJob.class)
								      .withIdentity("job1", "group1")
								      .build();

		  Trigger trigger = TriggerBuilder.newTrigger()
									      .withIdentity("trigger1", "group1")
									      .startNow()
									      .withSchedule(simpleSchedule()
									              .withIntervalInSeconds(5)
									              .repeatForever())
									      .build();
		
		  scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}


	}
}

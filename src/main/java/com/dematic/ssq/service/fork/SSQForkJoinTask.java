package com.dematic.ssq.service.fork;

import java.util.concurrent.RecursiveTask;

import org.springframework.beans.factory.annotation.Autowired;

import com.dematic.ssq.service.SsqNumberService;

public class SSQForkJoinTask extends RecursiveTask<Boolean> {

   private final Integer startValue;
   private final Integer endValue;
   private static final Integer maxValue = 100;

   private final SsqNumberService ssqNumberService;

   public SSQForkJoinTask(Integer startValue,Integer endValue,SsqNumberService ssqNumberService) {
      this.startValue = startValue;
      this.endValue = endValue;
      this.ssqNumberService=ssqNumberService;
   }


   @Override
   protected Boolean compute() {
      int executeCount = endValue - startValue;
      if (executeCount < maxValue) {
         for (int i=0;i<executeCount;i++){
            ssqNumberService.updateNewSSQ();
         }
         return true;
      }

      int midValue = (startValue + endValue) / 2;
      SSQForkJoinTask ssqForkJoinTask = new SSQForkJoinTask(startValue, midValue, ssqNumberService);
      ssqForkJoinTask.fork();
      SSQForkJoinTask ssqForkJoinTask1 = new SSQForkJoinTask(midValue, endValue, ssqNumberService);
      ssqForkJoinTask1.fork();

      Boolean join = ssqForkJoinTask.join();
      Boolean join1 = ssqForkJoinTask1.join();
      return join&&join1;
   }
}

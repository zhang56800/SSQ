package com.dematic.ssq.controller;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.dematic.ssq.service.SsqNumberService;
import com.dematic.ssq.service.fork.SSQForkJoinTask;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang,haoxiang
 * @since 2022-11-11
 */
@RestController
@RequestMapping("/ssq-number")
public class SsqNumberController {


   @Autowired
   private SsqNumberService ssqNumberService;
   @GetMapping("/run/{count}")
   public String run(@PathVariable Integer count){

      SSQForkJoinTask ssqForkJoinTask = new SSQForkJoinTask(0, count, ssqNumberService);
      ForkJoinPool forkJoinPool = new ForkJoinPool();
      ForkJoinTask<Boolean> submit = forkJoinPool.submit(ssqForkJoinTask);
      Boolean aBoolean = null;
      try {
         aBoolean = submit.get();
      } catch (InterruptedException | ExecutionException e) {
         throw new RuntimeException(e);
      }
      return String.valueOf(aBoolean);
   }
}

package com.dematic.ssq.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.dematic.ssq.entity.SsqNumber;
import com.dematic.ssq.mapper.SsqNumberMapper;
import com.dematic.ssq.service.SsqNumberService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang,haoxiang
 * @since 2022-11-11
 */
@Service
public class SsqNumberServiceImpl extends ServiceImpl<SsqNumberMapper, SsqNumber> implements SsqNumberService {

   public void updateNewSSQ(){

      ArrayList<Integer> redBallList = new ArrayList<>();
      ArrayList<Integer> blueBallList = new ArrayList<>();
      int redBall = 34;
      int blueBall = 17;
      for (int i = 1; i < redBall; i++) {
         redBallList.add(i);
      }
      for (int i = 1; i < blueBall; i++) {
         blueBallList.add(i);
      }
      Random random = new Random();

      String number = "";
      ArrayList<Integer> redList = new ArrayList<>();
      for (int j = 0; j < 6; j++) {
         int redRandom = random.nextInt(redBallList.size() - 1);
         Integer red = redBallList.remove(redRandom);
         redList.add(red);
         List<Integer> collect = redList.stream().sorted().collect(Collectors.toList());
         number= collect.stream().map(String::valueOf).collect(Collectors.joining(" "));

      }

      int blue = random.nextInt(15);
      Integer integer = blueBallList.get(blue);
      number+=" "+integer;


      QueryWrapper<SsqNumber> ssqNumberQueryWrapper = new QueryWrapper<>();
      ssqNumberQueryWrapper.eq("number",number.toString());

      SsqNumber one = this.getOne(ssqNumberQueryWrapper);
      if (one!=null){
         Integer newCount = one.getCount()+1;
         one.setCount(newCount);
         this.updateById(one);
      }else {
         one = new SsqNumber();
         one.setNumber(number.toString());
         one.setCount(0);
         this.save(one);
      }



   }
}

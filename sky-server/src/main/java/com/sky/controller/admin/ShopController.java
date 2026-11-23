package com.sky.controller.admin;

import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@Slf4j
@RequestMapping("/admin/shop")
public class ShopController {
    @Autowired
    private RedisTemplate redisTemplate;
    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        redisTemplate.opsForValue().set("shopStatus", status);
        return Result.success();
    }
    @GetMapping("/status")
    public Result getStatus(){
        Integer status = (Integer)redisTemplate.opsForValue().get("shopStatus");
        log.info("shopStatus: {}",status==1?"open":"close");
        return Result.success(status);
    }
}

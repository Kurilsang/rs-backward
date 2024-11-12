package com.kuril.logindemo.controller;

import com.kuril.logindemo.mapper.MusicDataMapper;
import com.kuril.logindemo.pojo.MusicData;
import com.kuril.logindemo.pojo.Result;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class MusicDataController {
    @Autowired
    MusicDataMapper musicDataMapper;
    @GetMapping("/music_datas")
    public Result getMusicDatas()
    {
        return Result.success(musicDataMapper.selectList(null));
    }
    @PostMapping("/music_data")
    public Result addMusicData(@RequestBody MusicData musicData)
    {
        musicDataMapper.insert(musicData);
        return Result.success();
    }
    @DeleteMapping("/music_data/{id}")
    public Result deleteMusicData(@PathVariable int id)
    {
        musicDataMapper.deleteById(id);
        return Result.success();
    }
}

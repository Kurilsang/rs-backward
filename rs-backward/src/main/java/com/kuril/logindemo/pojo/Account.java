package com.kuril.logindemo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Integer salt;
    private Integer role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

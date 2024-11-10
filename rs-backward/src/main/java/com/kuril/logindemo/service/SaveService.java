package com.kuril.logindemo.service;

import com.kuril.logindemo.pojo.Save;

public interface SaveService {
    public void save(Save save);

    Save getData(int id);
}

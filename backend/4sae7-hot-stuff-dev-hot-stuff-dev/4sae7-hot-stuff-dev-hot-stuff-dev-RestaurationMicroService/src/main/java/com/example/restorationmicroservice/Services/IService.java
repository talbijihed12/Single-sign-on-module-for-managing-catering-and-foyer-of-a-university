package com.example.restorationmicroservice.Services;

import java.util.List;

public interface IService <object> {
    object Create(object T);
    List<object> Read();
    object Update(Long ID ,object T);
    void  Delete(Long ID );
    object getOne(Long ID);

}

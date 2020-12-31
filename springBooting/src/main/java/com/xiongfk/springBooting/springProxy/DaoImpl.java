package com.xiongfk.springBooting.springProxy;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/6/18
 * @Version 1.0
 **/
public class DaoImpl implements Dao {

    @Override
    public void insert() {
        System.out.println("This is insert()");
    }

    @Override
    public void delete() {
        System.out.println("This is delete()");
    }

    @Override
    public void update() {
        System.out.println("This is update()");
    }
}

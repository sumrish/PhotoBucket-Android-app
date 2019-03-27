package com.example.userphotobucket.utils;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class S {

    public static Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }


    public static Scheduler computation() {
        return Schedulers.computation();
    }


    public static Scheduler io() {
        return Schedulers.io();
    }

    public static Scheduler newThread() {
        return Schedulers.newThread();
    }
}
package com.gioov.example.service;

import org.flowable.task.api.Task;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ExampleService {

    void startProcess();

    List<Task> getTasks(String assignee);

}




package com.example.john.sdaprojectjohnbuckley;

import android.provider.BaseColumns;

public final class TaskContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private TaskContract() {}

    /* Inner class that defines the table contents */
    public static class TaskFeedEntry implements BaseColumns {
        public static final String TASKTABLE_NAME = "tasklist";
        public static final String TASK_NAME = "TaskDescription";
        public static final String TASKENTRY_NAME = "Enteredby";
        public static final String TASK_DATE = "DueDate";
    }
}


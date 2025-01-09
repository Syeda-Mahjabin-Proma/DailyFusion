package com.example.dailyfusion;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class workList extends Activity {

    private static final String PREFS_NAME = "TaskPrefs";
    private static final String TASK_LIST_KEY = "TaskList";

    private LinearLayout allTask;
    private Button addTask, deleteAll;

    private List<String> taskList = new ArrayList<>();
    private List<Boolean> taskCompletionStatus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worklist);
        findViews();

        loadTasks();
        recreateTaskViews();

        addNewTask();
        deleteAllTask();
    }

    private void findViews() {
        allTask = findViewById(R.id.allTask);
        addTask = findViewById(R.id.addTask);
        deleteAll = findViewById(R.id.deleteAll);
    }

    private void saveTasks() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            builder.append(taskList.get(i))
                    .append("|")
                    .append(taskCompletionStatus.get(i) ? "1" : "0");
            if (i < taskList.size() - 1) {
                builder.append(";");
            }
        }

        editor.putString(TASK_LIST_KEY, builder.toString());
        editor.apply();
    }

    private void loadTasks() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedTasks = prefs.getString(TASK_LIST_KEY, "");

        if (!savedTasks.isEmpty()) {
            String[] tasks = savedTasks.split(";");
            for (String task : tasks) {
                String[] parts = task.split("\\|");
                taskList.add(parts[0]);
                taskCompletionStatus.add(parts[1].equals("1"));
            }
        }
    }

    private void recreateTaskViews() {
        for (int i = 0; i < taskList.size(); i++) {
            addTaskView(taskList.get(i), taskCompletionStatus.get(i));
        }
    }

    private void addNewTask() {
        addTask.setOnClickListener(v -> {
            String taskText = "Task " + (taskList.size() + 1);

            taskList.add(taskText);
            taskCompletionStatus.add(false);

            saveTasks();
            addTaskView(taskText, false);
        });
    }

    private void addTaskView(String text, boolean isCompleted) {
        LinearLayout taskLayout = new LinearLayout(this);
        taskLayout.setOrientation(LinearLayout.HORIZONTAL);
        taskLayout.setPadding(10, 10, 10, 10);
        taskLayout.setBackgroundColor(Color.parseColor("#000000"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 10, 0, 10);
        taskLayout.setLayoutParams(params);

        CheckBox checkBox = new CheckBox(this);
        checkBox.setChecked(isCompleted);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int index = taskList.indexOf(text);
            if (index != -1) {
                taskCompletionStatus.set(index, isChecked);
                saveTasks();
            }
        });

        TextView taskText = new TextView(this);
        taskText.setText(text);
        taskText.setTextColor(Color.parseColor("#FFFFFF"));
        taskText.setPadding(20, 0, 20, 0);
        LinearLayout.LayoutParams taskTextParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
        );
        taskTextParams.gravity = Gravity.CENTER_VERTICAL;
        taskText.setLayoutParams(taskTextParams);

        Button editButton = new Button(this);
        editButton.setText("Edit");
        editButton.setTextSize(15);
        editButton.setOnClickListener(v -> editTask(taskText));

        Button deleteButton = new Button(this);
        deleteButton.setText("Delete");
        deleteButton.setTextSize(15);
        deleteButton.setOnClickListener(v -> {
            allTask.removeView(taskLayout);
            int index = taskList.indexOf(text);
            if (index != -1) {
                taskList.remove(index);
                taskCompletionStatus.remove(index);
                saveTasks();
            }
        });

        taskLayout.addView(checkBox);
        taskLayout.addView(taskText);
        taskLayout.addView(editButton);
        taskLayout.addView(deleteButton);

        allTask.addView(taskLayout);
    }

    private void deleteAllTask() {
        deleteAll.setOnClickListener(v -> {
            allTask.removeAllViews();
            taskList.clear();
            taskCompletionStatus.clear();
            saveTasks();
        });
    }

    private void editTask(TextView taskText) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        final EditText input = new EditText(this);
        input.setText(taskText.getText());
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String newText = input.getText().toString();
            int index = taskList.indexOf(taskText.getText().toString());
            if (index != -1) {
                taskList.set(index, newText);
                saveTasks();
            }
            taskText.setText(newText);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}

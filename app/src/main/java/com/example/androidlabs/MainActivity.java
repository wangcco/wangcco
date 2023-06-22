package com.example.androidlabs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView todoListView;
    private EditText todoEditText;
    private Switch urgentSwitch;
    private Button addButton;

    private List<TodoItem> todoList;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoListView = findViewById(R.id.todoListView);
        todoEditText = findViewById(R.id.todoEditText);
        urgentSwitch = findViewById(R.id.urgentSwitch);
        addButton = findViewById(R.id.addButton);

        todoList = new ArrayList<>();
        todoAdapter = new TodoAdapter();

        todoListView.setAdapter(todoAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = todoEditText.getText().toString().trim();
                boolean urgent = urgentSwitch.isChecked();

                if (!text.isEmpty()) {
                    TodoItem todoItem = new TodoItem(text, urgent);
                    todoList.add(todoItem);
                    todoAdapter.notifyDataSetChanged();

                    todoEditText.setText("");
                    urgentSwitch.setChecked(false);
                } else {
                    Toast.makeText(MainActivity.this, R.string.enter_text, Toast.LENGTH_SHORT).show();
                }
            }
        });

        todoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.delete_title);
                builder.setMessage(getString(R.string.delete_message, position));
                builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        todoList.remove(position);
                        todoAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton(R.string.cancel, null);
                builder.show();

                return true;
            }
        });
    }

    private class TodoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return todoList.size();
        }

        @Override
        public Object getItem(int position) {
            return todoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                view = inflater.inflate(R.layout.list_item_todo, parent, false);
            }

            TodoItem todoItem = (TodoItem) getItem(position);

            TextView todoTextView = view.findViewById(R.id.todoTextView);
            todoTextView.setText(todoItem.getText());

            if (todoItem.isUrgent()) {
                view.setBackgroundColor(Color.RED);
                todoTextView.setTextColor(Color.WHITE);
            } else {
                view.setBackgroundColor(Color.WHITE);
                todoTextView.setTextColor(Color.BLACK);
            }

            return view;
        }
    }
}

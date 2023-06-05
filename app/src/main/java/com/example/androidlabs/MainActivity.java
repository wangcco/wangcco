import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    private List<TodoItem> itemList;
    private TodoAdapter adapter;

    private EditText editText;
    private Switch urgentSwitch;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        urgentSwitch = findViewById(R.id.urgentSwitch);
        addButton = findViewById(R.id.addButton);

        itemList = new ArrayList<>();
        adapter = new TodoAdapter();

        listView.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                boolean urgent = urgentSwitch.isChecked();
                TodoItem item = new TodoItem(text, urgent);

                itemList.add(item);
                adapter.notifyDataSetChanged();

                editText.setText("");
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.delete_title)
                        .setMessage(getString(R.string.delete_message, position))
                        .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                itemList.remove(position);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, R.string.item_deleted, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();

                return true;
            }
        });
    }

    private class TodoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.todo_item, parent, false);
            }

            TextView todoText = view.findViewById(R.id.todoText);
            TodoItem item = itemList.get(position);
            todoText.setText(item.getText());

            if (item.isUrgent()) {
                view.setBackgroundColor(Color.RED);
                todoText.setTextColor(Color.WHITE);
            } else {
                view.setBackgroundColor(Color.TRANSPARENT);
                todoText.setTextColor(Color.BLACK);
            }

            return view;
        }
    }
}

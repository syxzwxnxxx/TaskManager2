package sdemoalarmmanager.edu.rp.c347.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnTask;
    ListView lv;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTask = (Button) findViewById(R.id.btnTask);
        lv = (ListView) this.findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(this);
        ArrayList<Task> tasks = dbh.getAllTask();

        adapter  = new TaskAdapter(this, R.layout.row, tasks);
        lv.setAdapter(adapter);
    }
}

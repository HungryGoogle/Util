package deepin.leeutil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import deepin.ctk.CtkApp;
import deepin.ctk.media.VoiceUtil;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CtkApp.getIns().init(getApplicationContext());

        final List<String> list=new ArrayList<String>();
        list.add("voice");
        list.add("meteria_dialog");
        list.add("system_alert_dialog");

        ListView listView;
        listView = (ListView)findViewById(R.id.id_listview);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (list.get(i)) {
                    case "voice":
                        startActivity(new Intent(getApplication(), TestVoiceActivity.class));
                        break;
                    case "meteria_dialog":
                        startActivity(new Intent(getApplication(), TestMeteriaDialogActivity.class));
                        break;
                    case "system_alert_dialog":
                        startActivity(new Intent(getApplication(), TestPopupDialogActivity.class));
                        break;
                    default:break;

                }
            }
        });

    }
}

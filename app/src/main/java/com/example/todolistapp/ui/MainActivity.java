package com.example.todolistapp.ui;

import android.os.Bundle;

import com.example.todolistapp.R;
import com.example.todolistapp.model.EventModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static com.example.todolistapp.ui.DetailFragment.DETAIL_EVENT_MODEL;

public class MainActivity extends AppCompatActivity implements SummaryFragment.OnActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SummaryFragment fragment = SummaryFragment.newInstance();
        changeFragment(fragment);
    }

    public void changeFragment(final Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onActionCallback(EventModel eventModel) {
        Bundle args = new Bundle();
        args.putSerializable(DETAIL_EVENT_MODEL, eventModel);
        changeFragment(DetailFragment.newInstance(args));
    }
}

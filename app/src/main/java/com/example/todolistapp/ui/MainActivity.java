package com.example.todolistapp.ui;

import android.os.Bundle;

import com.example.todolistapp.R;
import com.example.todolistapp.application.TodoListApplication;
import com.example.todolistapp.model.EventModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static com.example.todolistapp.ui.DetailFragment.DETAIL_EVENT_MODEL;

public class MainActivity extends AppCompatActivity implements SummaryFragment.OnActionListener {

    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isTablet = TodoListApplication.isTablet(this);

        changeFragment(SummaryFragment.newInstance(), R.id.flContainer);

        if (isTablet) {
            changeFragment(DetailFragment.newInstance(null), R.id.flContainerDetail);
        }
    }

    public void changeFragment(final Fragment fragment, int layoutId) {
        getSupportFragmentManager().beginTransaction()
                .replace(layoutId, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    @Override
    public void onActionCallback(EventModel eventModel) {
        Bundle args = new Bundle();
        args.putSerializable(DETAIL_EVENT_MODEL, eventModel);

        int layoutId;

        if (isTablet) {
            layoutId = R.id.flContainerDetail;
        } else {
            layoutId = R.id.flContainer;
        }

        changeFragment(DetailFragment.newInstance(args), layoutId);
    }

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (isTablet) {
            if (fragments <= 2) {
                finish();
                return;
            }
        } else {
            if (fragments <= 1) {
                finish();
                return;
            }
        }

        super.onBackPressed();
    }
}

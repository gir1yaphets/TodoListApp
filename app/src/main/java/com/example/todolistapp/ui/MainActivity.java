package com.example.todolistapp.ui;

import android.os.Bundle;

import com.example.todolistapp.R;
import com.example.todolistapp.application.TodoListApplication;
import com.example.todolistapp.model.CategoryModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static com.example.todolistapp.ui.DetailFragment.CATEGORY_MODEL;

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
    public void onActionCallback(CategoryModel categoryModel) {
        Bundle args = new Bundle();
        args.putSerializable(CATEGORY_MODEL, categoryModel);

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

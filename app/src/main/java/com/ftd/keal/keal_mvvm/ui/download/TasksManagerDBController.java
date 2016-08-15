package com.ftd.keal.keal_mvvm.ui.download;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.ftd.keal.keal_mvvm.KealMvvmApplication;
import com.ftd.keal.keal_mvvm.R;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16/8/15 10:49.
 */
public class TasksManagerDBController {
    private final SQLiteDatabase db;

    public TasksManagerDBController() {
        TasksManagerDBOpenHelper openHelper = new TasksManagerDBOpenHelper(KealMvvmApplication.CONTEXT);

        db = openHelper.getWritableDatabase();
    }

    public List<TasksManagerModel> getAllTasks() {
        final Cursor c = db.rawQuery("SELECT * FROM " + TasksManagerModel.TABLE_NAME, null);

        final List<TasksManagerModel> list = new ArrayList<>();
        try {
            if (!c.moveToLast()) {
                return list;
            }

            do {
                TasksManagerModel model = new TasksManagerModel();
                model.setId(c.getInt(c.getColumnIndex(TasksManagerModel.ID)));
                model.setName(c.getString(c.getColumnIndex(TasksManagerModel.NAME)));
                model.setUrl(c.getString(c.getColumnIndex(TasksManagerModel.URL)));
                model.setPath(c.getString(c.getColumnIndex(TasksManagerModel.PATH)));
                list.add(model);
            } while (c.moveToPrevious());
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return list;
    }

    public TasksManagerModel addTask(final String url, final String path) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(path)) {
            return null;
        }

        // have to use FileDownloadUtils.generateId to associate TasksManagerModel with FileDownloader
        final int id = FileDownloadUtils.generateId(url, path);

        TasksManagerModel model = new TasksManagerModel();
        model.setId(id);
        model.setName(KealMvvmApplication.CONTEXT.getString(R.string.tasks_manager_demo_name, id));
        model.setUrl(url);
        model.setPath(path);

        final boolean succeed = db.insert(TasksManagerModel.TABLE_NAME, null, model.toContentValues()) != -1;
        return succeed ? model : null;
    }
}

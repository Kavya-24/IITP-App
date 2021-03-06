package com.grobo.notifications.notifications;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotificationViewModel extends AndroidViewModel {

    private NotificationsRepository notificationsRepository;
    private LiveData<List<Notification>> allNotifications;

    public NotificationViewModel(@NonNull Application application) {
        super(application);
        notificationsRepository = new NotificationsRepository(application);
        allNotifications = notificationsRepository.loadAllNotifications();
    }

    public LiveData<List<Notification>> loadAllNotifications() {
        return allNotifications;
    }

    public void insert (Notification notification) {
        notificationsRepository.insert(notification);
    }
    public void delete (Notification notification) {
        notificationsRepository.delete(notification);
    }
    public Notification getNotificationByTimestamp (Long time) {
        return notificationsRepository.getNotificationByTimestamp(time);
    }
}

extern "C" {
    void SimpleNotifier_Register() {
        UIApplication *app = [UIApplication sharedApplication];
        if ([app respondsToSelector:@selector(registerUserNotificationSettings:)])
        {
            UIUserNotificationSettings *settings = [UIUserNotificationSettings settingsForTypes:UIUserNotificationTypeAlert | UIUserNotificationTypeBadge | UIUserNotificationTypeSound categories:nil];
            [app registerUserNotificationSettings:settings];
            [app registerForRemoteNotifications];
        }
    }
}
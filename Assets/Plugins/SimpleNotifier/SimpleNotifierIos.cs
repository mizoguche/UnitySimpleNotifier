using UnityEngine;
using System;

#if UNITY_IOS
using System.Runtime.InteropServices;
#endif

namespace UnitySimpleNotifier
{
	public class SimpleNotifierIos : SimpleNotifierImplementor
	{
#if UNITY_IOS
		[DllImport("__Internal")]
		private static extern void SimpleNotifier_Register ();

		public override void ScheduleNotification (DateTime dateTime, string title, string body)
		{
			SimpleNotifier_Register ();

			LocalNotification notification = new LocalNotification ();
			notification.fireDate = dateTime;
			notification.alertAction = title;
			notification.alertBody = body;

			NotificationServices.ScheduleLocalNotification (notification);
		}

		public override void CancelAllNotifications ()
		{
			NotificationServices.CancelAllLocalNotifications ();
		}
#endif
	}
}
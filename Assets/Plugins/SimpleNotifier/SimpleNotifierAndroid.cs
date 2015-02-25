using UnityEngine;
using System;

namespace UnitySimpleNotifier
{
	public class SimpleNotifierAndroid : SimpleNotifierImplementor
	{
		#if UNITY_ANDROID
		
		public override void ScheduleNotification (DateTime dateTime, string title, string body)
		{
			AndroidJavaObject service = new AndroidJavaClass ("info.mizoguche.simplenotification.SimpleNotificationService");
			service.CallStatic ("scheduleNotification", ToUnixTime (dateTime) * 1000, title, body);
		}
		
		public override void CancelAllNotifications ()
		{
//			NotificationServices.CancelAllLocalNotifications ();
		}

		public long ToUnixTime (DateTime date)
		{
			var epoch = new DateTime (1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
			Debug.Log (Convert.ToInt64 ((date - epoch).TotalSeconds));
			return Convert.ToInt64 ((date - epoch).TotalSeconds);
		}
		#endif
	}
}

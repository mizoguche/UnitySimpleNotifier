using UnityEngine;
using System;

namespace UnitySimpleNotifier
{
	public class SimpleNotifierImplementor
	{
		public virtual void ScheduleNotification (DateTime dateTime, string title, string body)
		{
			Debug.Log ("SimpleNotifierImplementor#ScheduleNotification called.");
			Debug.Log ("Scheduled notify at " + dateTime.ToString ());
			Debug.Log ("But nothing happened becase running in editor.");
		}
	
		public virtual void CancelAllNotifications ()
		{
			Debug.Log ("SimpleNotifierImplementor#CancelAllNotifications called.");
			Debug.Log ("Nothing happened becase running in editor.");
		}
	}
}

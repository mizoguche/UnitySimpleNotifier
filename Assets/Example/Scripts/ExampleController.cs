using UnityEngine;
using System;
using UnitySimpleNotifier;

public class ExampleController : MonoBehaviour
{
	public void OnClickNotifyAfter5seconds ()
	{
		DateTime dateTime = DateTime.UtcNow.AddSeconds (5);

		SimpleNotifier.ScheduleNotification (dateTime, "test title", "test body");
	}
}

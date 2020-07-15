# move_activity_to_front

Prerequisites for use:

1. The application is only back to the background (HOME key), rather than being killed.
2. Because the flutter call is required, the code in the flutter part must still be alive.

---

When to use: Some push plugins cannot return the application to the foreground after clicking, then you can try it.

There is no guarantee that all devices will be effective.

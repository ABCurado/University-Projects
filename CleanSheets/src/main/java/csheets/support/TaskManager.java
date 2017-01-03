package csheets.support;

import java.util.Timer;

public class TaskManager {

    private int seconds = 60;

    private int after = 0;

    private Timer timer;

    public TaskManager() {
        this.timer = new Timer();
    }

    /**
     * Fires a new Task on every given seconds (default is 60 seconds).
     *
     * @param task Task to be executed.
     * @return Self.
     */
    public TaskManager fire(Task task) {

        this.timer.schedule(task, this.after * 1000, this.seconds * 1000);

        this.after = 0;
        this.seconds = 60;

        return this;
    }

    /**
     * Fires a new Task only once.
     *
     * @param task Task to be executed.
     * @return self
     */
    public TaskManager once(Task task) {
        Task once = new Task() {
            @Override
            public void run() {
                this.fire();

                this.cancel();
                
                if (timer != null) {
                    timer.purge();
                }
            }

            @Override
            public void fire() {
                task.fire();
            }
        };

        this.timer.schedule(once, this.after * 1000);

        this.after = 0;

        return this;
    }

    /**
     * Sets the seconds for each task to be executed.
     *
     * @param seconds Seconds.
     * @return self
     */
    public TaskManager every(int seconds) {
        this.seconds = seconds;

        return this;
    }

    /**
     * Sets the delay for each task to be executed.
     *
     * @param seconds Seconds.
     * @return self
     */
    public TaskManager after(int seconds) {
        this.after = seconds;

        return this;
    }

    /**
     * Destroys the manager.
     */
    public void destroy() {
        this.timer.cancel();
        this.timer = null;
    }
}

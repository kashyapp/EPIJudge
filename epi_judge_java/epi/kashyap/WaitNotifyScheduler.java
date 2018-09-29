package epi.kashyap;

import org.jetbrains.annotations.NotNull;

import java.time.Clock;
import java.time.Duration;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

interface Scheduler {
    void schedule(Runnable r, long timeMs);
    void start();
    void quit();

    class Job implements Comparable<Job> {
        protected final Runnable runnable;
        protected final long timeMs;

        private Job(Runnable runnable, long timeMs) {
            this.runnable = runnable;
            this.timeMs = timeMs;
        }

        public static Job of(Runnable r, long timeMs) {
            return new Job(r, timeMs);
        }

        @Override
        public int compareTo(@NotNull Job that) {
            return (int)(this.timeMs - that.timeMs);
        }
    }
}

abstract class AbstractScheduler extends Thread {
    protected final Clock clock;
    private int count = 0;
    private volatile boolean stopped = false;

    protected AbstractScheduler(Clock clock) {
        this.clock = clock;
    }

    protected boolean isStopped() {
        return stopped;
    }

    public void quit() {
        System.out.println("quitting");
        this.stopped = true;
    }

    protected void launch(Scheduler.Job job) {
        String name = String.format("Job-%08d", count);
        System.out.println("Launched job " + name + " ETD=" + job.timeMs + " ATD=" + clock.millis());
        new Thread(job.runnable, name).start();
        count++;
    }

    public abstract void run();
}

class LockedScheduler extends AbstractScheduler implements Scheduler {
    private final PriorityQueue<Job> pq = new PriorityQueue<>();
    private final ReentrantLock lck = new ReentrantLock();
    private final Condition available = lck.newCondition();

    protected LockedScheduler(Clock clock) {
        super(clock);
    }


    @Override
    public void schedule(Runnable r, long timeMs) {
        lck.lock();
        try {
            pq.add(Job.of(r, timeMs));
            available.signal();
        } finally {
            lck.unlock();
        }
    }

    @Override
    public void run() {
        while (!isStopped()) {
            lck.lock();
            try {
                if (!pq.isEmpty()) {
                    Job job = pq.peek();
                    long wait = job.timeMs - clock.millis();
                    if (wait <= 0) {
                        launch(pq.poll());
                    } else {
                        available.await(wait, TimeUnit.MILLISECONDS);
                    }
                } else {
                    available.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lck.unlock();
            }
        }
    }
}

class QueueScheduler extends AbstractScheduler implements Scheduler {
    private final PriorityQueue<Job> pq = new PriorityQueue<>();

    private final SynchronousQueue<Job> q = new SynchronousQueue<>();

    QueueScheduler(Clock clock) {
        super(clock);
    }

    @Override
    public void schedule(Runnable r, long timeMs) {
        try {
            q.put(Job.of(r, timeMs));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("starting");
        while (!isStopped()) {
            try {
                if (!pq.isEmpty()) {
                    Job job = pq.peek();
                    long wait = job.timeMs - clock.millis();
                    if (wait <= 0) {
                        launch(pq.poll());
                    } else {
                        receive(q.poll(wait, TimeUnit.MILLISECONDS));
                    }
                } else {
                    receive(q.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void receive(Job j) {
        if (j != null) {
            pq.offer(j);
        } else {
            System.out.println("nothing received");
        }
    }
}

class WaitNotifyScheduler extends AbstractScheduler implements Scheduler {
    public WaitNotifyScheduler(Clock clock) {
        super(clock);
    }

    private final PriorityQueue<Job> pq = new PriorityQueue<>();

    @Override
    public void run() {
        System.out.println("starting");
        while (!isStopped()) {
            synchronized (pq) {
                try {
                    if (pq.isEmpty()) {
                        pq.wait();
                    } else {
                        long now = clock.millis();
                        Job j = pq.peek();
                        long waitMs = j.timeMs - now;
                        if (waitMs <= 0) {
                            launch(pq.poll());
                        } else {
//                            System.out.println("waiting " + waitMs);
                            pq.wait(waitMs);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void schedule(Runnable r, long timeMs) {
        synchronized (pq) {
            pq.add(Job.of(r, timeMs));
            pq.notify();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Clock clock = Clock.offset(Clock.systemUTC(), Duration.ofMillis(0 - Clock.systemUTC().millis()));

//        Scheduler s = new QueueScheduler(clock);
//        Scheduler s = new WaitNotifyScheduler(clock);
        Scheduler s = new LockedScheduler(clock);
        s.start();

        List.of(5, 400, 401, 402, 403, 401, 401, 401, 401, 120, 300, 5, 800, 1000).forEach((offsetMs) ->
        {
            s.schedule(
                    () -> System.out.println("I am task at " + offsetMs),
                    offsetMs);
        });
        Thread.sleep(2000);
        s.quit();
    }
}
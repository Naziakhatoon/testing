package ProducerandConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class AProducer extends Thread {
    private BlockingQueue<String> myqueue;
    private String threadName;

    public AProducer(String threadName, BlockingQueue<String> myqueue) throws InterruptedException {
        this.myqueue = myqueue;
        this.threadName = threadName;
    }

    public void run() {
        try {
            while (true) {
                for (int i = 0; i <= 10000; i++) {
                    myqueue.put("Hi : " + threadName + " Value : " + i);
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BConsumer extends Thread {
    private final BlockingQueue<String> myqueue;
    public BConsumer(BlockingQueue<String> myqueue) {
        this.myqueue = myqueue;
    }

    public void run() {
        while (true) {
            try {
                String receivedMessage = myqueue.take();
                System.out.println("Consumer received Message " + receivedMessage);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("***Consumer Successfully Received the Data***");
        }
    }

}


class Test2 {
    public static void main(String[] args) throws InterruptedException {
        //Global global = new Global();
        BlockingQueue<String> myqueue = new LinkedBlockingQueue<String>();
        AProducer[] xpro_obj = new AProducer[5];
        for (int i = 0; i < 5; i++) {
            xpro_obj[i] = new AProducer(" From Producer " + (i + 1), myqueue);
            xpro_obj[i].start();
        }

        BConsumer bconsumer = new BConsumer(myqueue);
        bconsumer.start();

    }

}
package com.manning.nettyinaction.chapter1;

/**
 * Listing 1.1  of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class CallbackFetcherExample {

    public interface Fetcher {
        void fetchData(FetchCallback callback);
    }

    public interface FetchCallback {
        void onData(Data data);
        void onError(Throwable cause);
    }

    public class Worker {
        public void doWork() {
            Fetcher fetcher = null; // obtain Fetcher instance
            fetcher.fetchData(new FetchCallback() {
                @Override
                public void onData(Data data) {
                    System.out.println("Data received: " + data);
                }

                @Override
                public void onError(Throwable cause) {
                    System.err.println("An error accour: " + cause.getMessage());
                }
            });

        }
    }

    public class Data {
        // holds your data
    }
}

package diplomna.service.serviceImpl;


import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

class AsyncResponse<V> implements Future<V> {

    private V value;
    private Exception exceptionException;
    private boolean isCompletedExceptionally = false;
    private boolean isCancelled = false;
    private boolean isDone = false;
    private long checkCompletedInterval = 100;


    public AsyncResponse() {
    }

    public AsyncResponse(V val) {
        this.value = val;
        this.isDone = true;
    }

    public AsyncResponse(Throwable ex) {
        this.exceptionException = new Exception(ex);
        this.isCompletedExceptionally = true;
        isDone = true;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        this.isCancelled = true;
        this.isDone = true;
        return false;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }

   public boolean isCompletedExceptionally() {
        return this.isCompletedExceptionally;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        block(0);

        if (isCancelled()) {
            throw new ExecutionException(this.exceptionException);
        }
        if (isCompletedExceptionally()) {
            throw new ExecutionException(this.exceptionException);
        }

        if (isDone()) {
            return this.value;
        }
        throw new InterruptedException();
    }

    @Override
    public V get(long timeout, @NotNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {

        int timeoutInMillis = (int) unit.toMillis(timeout);
        block(timeoutInMillis);

        if (isCancelled()){
            throw new CancellationException();
        }
        if (isCompletedExceptionally()){
            throw new ExecutionException(this.exceptionException);
        }
        if (isDone()){
            return this.value;
        }
        throw new InterruptedException();
    }

    private void block(long timeout) throws InterruptedException {
        long start = System.currentTimeMillis();

        while (!isDone && !isCancelled()){
            if (timeout > 0){
                long now = System.currentTimeMillis();
                if (now > start + timeout){
                    break;
                }
            }
            Thread.sleep(checkCompletedInterval);
        }
    }


    public void complete(V success) {
    }

    public void completeExceptionally(Exception e) {
    }
}

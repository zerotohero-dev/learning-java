package io.volkan;

public class FinalizeTest {
    private boolean resourcesInUse;

    public synchronized void allocateResources() {
        performAllocate();
        resourcesInUse = true;
    }

    public synchronized void releaseResources() {
        performRelease();
        resourcesInUse = false;
    }

    @Override
    protected synchronized void finalize() throws Throwable {
        if (!resourcesInUse) {return;}

        releaseResources();
    }

    protected void performAllocate() {}
    protected void performRelease() {}
}

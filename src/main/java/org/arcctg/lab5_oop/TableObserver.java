package org.arcctg.lab5_oop;

public interface TableObserver {
    void onShapeSelected(int index);
    void onShapeDeleted(int index);
}

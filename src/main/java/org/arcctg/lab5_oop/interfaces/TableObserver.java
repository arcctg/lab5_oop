package org.arcctg.lab5_oop.interfaces;

public interface TableObserver {
    void onShapeSelected(int index);
    void onShapeDeleted(int index);
}

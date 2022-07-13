package main.components;

import javafx.collections.ObservableList;
import javafx.scene.control.*;

public class LimitedSelectionModel extends MultipleSelectionModel {

    private MultipleSelectionModel originalSelectionModel;
    private Integer selectedRowsLimit;

    public LimitedSelectionModel(MultipleSelectionModel originalSelectionModel, Integer selectedRowsLimit) {
        this.originalSelectionModel = originalSelectionModel;
        this.originalSelectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        this.selectedRowsLimit = selectedRowsLimit;
    }


    @Override
    public void select(int i) {
        if (isSelected(i)) {
            clearSelection(i);
        } else {
            if (this.getSelectedItems().size() < selectedRowsLimit) {
                this.originalSelectionModel.select(i);
            }
        }
    }

    @Override
    public void select(Object o) {
        if (this.getSelectedItems().size() < selectedRowsLimit) {
            this.originalSelectionModel.select(o);
        }
    }

    @Override
    public void clearAndSelect(int i) {
        select(i);
    }

    @Override
    public void clearSelection(int i) {
        this.originalSelectionModel.clearSelection(i);
    }

    @Override
    public void clearSelection() {
        this.originalSelectionModel.clearSelection();
    }

    @Override
    public boolean isSelected(int i) {
        return this.originalSelectionModel.isSelected(i);
    }

    @Override
    public boolean isEmpty() {
        return this.originalSelectionModel.isEmpty();
    }

    @Override
    public void selectPrevious() {
        if (this.getSelectedItems().size() < selectedRowsLimit-1) {
            this.originalSelectionModel.selectPrevious();
        }
    }

    @Override
    public void selectNext() {
        if (this.getSelectedItems().size() < selectedRowsLimit-1) {
            this.originalSelectionModel.selectNext();
        }
    }

    @Override
    public ObservableList<Integer> getSelectedIndices() {
        return this.originalSelectionModel.getSelectedIndices();
    }

    @Override
    public ObservableList getSelectedItems() {
        return this.originalSelectionModel.getSelectedItems();
    }

    @Override
    public void selectIndices(int i, int... ints) {
        if (this.getSelectedItems().size() < selectedRowsLimit-1) {
            this.originalSelectionModel.selectIndices(i, ints);
        }
    }

    @Override
    public void selectAll() { }

    @Override
    public void selectFirst() {
        if (this.getSelectedItems().size() < selectedRowsLimit) {
            this.originalSelectionModel.selectFirst();
        }
    }

    @Override
    public void selectLast() {
        if (this.getSelectedItems().size() < selectedRowsLimit) {
            this.originalSelectionModel.selectLast();
        }
    }
}

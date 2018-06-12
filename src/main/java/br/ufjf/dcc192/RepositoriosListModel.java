package br.ufjf.dcc192;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class RepositoriosListModel implements ListModel<Repositorio> {

    private final List<Repositorio> repos;
    private final List<ListDataListener> dataListeners;
    
    public RepositoriosListModel(List<Repositorio> repos) {
        this.repos = repos;
        this.dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return repos.size();
    }

    @Override
    public Repositorio getElementAt(int index) {
        return repos.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        this.dataListeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        this.dataListeners.remove(l);
    }
    
}

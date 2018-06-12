package br.ufjf.dcc192;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ParticipantesListModel implements ListModel<Pessoa> {

    private final List<Pessoa> pessoas;
    private final List<ListDataListener> dataListeners;
    
    ParticipantesListModel(List<Pessoa> parts) {
        this.pessoas = parts;
        dataListeners = new ArrayList<>();
    } 

    @Override
    public int getSize() {
        return pessoas.size();
    }

    @Override
    public Pessoa getElementAt(int index) {
        return pessoas.get(index);
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

package geekbrains.ru.lesson1mvc;

import java.util.ArrayList;
import java.util.List;

class Model {
    private List<Integer> mList;
    Model(){
        mList = new ArrayList<>(3);
        mList.add(0);
        mList.add(0);
        mList.add(0);
    }
    int getElementValueAtIndex(int _index){
        return mList.get(_index);
    }
    void setElementValueAtIndex(int _index, int value){
        mList.set(_index, value);
    }
}



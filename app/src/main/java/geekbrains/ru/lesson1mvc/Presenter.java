package geekbrains.ru.lesson1mvc;

class Presenter {
    private Model mModel;
    private MainActivity view;

    Presenter(MainActivity view) {
        this.mModel = new Model();
        this.view = view;
    }

    private int calcNewModelValue(int modelElementIndex) {
        int currentValue = mModel.getElementValueAtIndex(modelElementIndex);
        return currentValue + 1;
    }

    void buttonClick(int btnIndex) {
        int newModelValue;
        newModelValue = calcNewModelValue(btnIndex % 10);
        mModel.setElementValueAtIndex(btnIndex % 10, newModelValue);
        view.setButtonText(btnIndex, newModelValue);
    }
}

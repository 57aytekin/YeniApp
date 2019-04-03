package com.aytekincomez.yeniapp.Activity.Activity.register;

public interface RegisterView {

    void showProgress();
    void hideProgress();
    void onRequestSuccess(String message);
    void onRequestError(String message);
}

package com.aytekincomez.yeniapp.Activity.Activity.profileDuzenle;

import com.aytekincomez.yeniapp.Activity.Model.User;

import java.util.List;

public interface ProfilDuzenleView {

    void onRequestSuccess(String message);
    void onRequestError(String message);
    void onGetResult(List<User> user);
    void showLoading();
    void hideLoading();
}

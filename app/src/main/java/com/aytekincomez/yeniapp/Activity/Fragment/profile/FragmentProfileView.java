package com.aytekincomez.yeniapp.Activity.Fragment.profile;

import com.aytekincomez.yeniapp.Activity.Model.User;

import java.util.HashMap;
import java.util.List;

public interface FragmentProfileView {

    void showProgress();
    void hideProgress();
    void onRequestSucces(String message);
    void onRequestError(String message);
    void onGetResult(HashMap<String, String> map);
}

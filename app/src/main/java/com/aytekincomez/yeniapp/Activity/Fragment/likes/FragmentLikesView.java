package com.aytekincomez.yeniapp.Activity.Fragment.likes;

import com.aytekincomez.yeniapp.Activity.Model.Likes;

import java.util.List;

public interface FragmentLikesView {
    void showProgress();
    void hideProgress();
    void onGetResult(List<Likes> likes);
    void onRequestError(String message);
}

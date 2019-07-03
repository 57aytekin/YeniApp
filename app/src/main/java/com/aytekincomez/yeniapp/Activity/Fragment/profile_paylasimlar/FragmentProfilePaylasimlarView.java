package com.aytekincomez.yeniapp.Activity.Fragment.profile_paylasimlar;

import com.aytekincomez.yeniapp.Activity.Model.Post;

import java.util.List;

public interface FragmentProfilePaylasimlarView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Post> list);
    void onErrorLoading(String message);

}

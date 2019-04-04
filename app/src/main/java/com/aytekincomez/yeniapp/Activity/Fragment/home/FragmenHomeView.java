package com.aytekincomez.yeniapp.Activity.Fragment.home;


import com.aytekincomez.yeniapp.Activity.Model.Post;

import java.util.List;

public interface FragmenHomeView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Post> posts);
    void onErrorLoading(String message);

}

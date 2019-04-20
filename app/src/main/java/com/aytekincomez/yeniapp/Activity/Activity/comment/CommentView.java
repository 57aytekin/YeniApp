package com.aytekincomez.yeniapp.Activity.Activity.comment;

import com.aytekincomez.yeniapp.Activity.Model.Comment;
import java.util.List;

public interface CommentView {

    void showProgress();
    void hideProgress();
    void showLoading();
    void hideLoading();
    void onRequestSuccess(String message);
    void onRequestError(String message);
    void onGetResult(List<Comment> list);


}

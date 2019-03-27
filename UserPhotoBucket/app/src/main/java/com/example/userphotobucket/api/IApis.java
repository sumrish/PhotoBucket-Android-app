package com.example.userphotobucket.api;

import com.example.userphotobucket.ui.album.model.Album;
import com.example.userphotobucket.ui.photo.model.Photo;
import com.example.userphotobucket.ui.user.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApis {

    @GET("/users")
    Observable<Response<List<User>>> getAllUsers();

    @GET("/albums?")
    Observable<Response<List<Album>>> getAllUserAlbums(@Query("userId") int id);

    @GET("/photos?")
    Observable<Response<List<Photo>>> getAllPhotos(@Query("albumId") int id);
}

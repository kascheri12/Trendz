package com.trendzcatalog.trendz.viewmodels.implementation;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;
import com.trendzcatalog.trendz.ServiceGenerator;
import com.trendzcatalog.trendz.constants.UploadErrorCode;
import com.trendzcatalog.trendz.models.ClothingArticle;
import com.trendzcatalog.trendz.models.ImageFile;
import com.trendzcatalog.trendz.services.ClosetService;
import com.trendzcatalog.trendz.services.FileUploadService;
import com.trendzcatalog.trendz.viewmodels.interfaces.IMainActivityViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by kennethascheri on 11/20/15.
 */
public class MainActivityViewModel implements IMainActivityViewModel {

    private List<ImageFile> uploadedFiles;

    @Override
    public void uploadImageFiles(List<ImageFile> files) {
        new UploadFilesTask().execute(files.toArray(new ImageFile[files.size()]));
    }

    class UploadFilesTask extends AsyncTask<ImageFile, String, Integer> {
        Integer totalCount = 0;

        @Override
        protected Integer doInBackground(ImageFile... params) {
            final Integer uploadCount = 0;
            totalCount = params.length;
            uploadedFiles = new ArrayList<ImageFile>();
            for (int index = 0; index < params.length; index++) {
                FileUploadService service = ServiceGenerator.createService(FileUploadService.class);
                File file = new File(params[index].getImageFileLocation());

                String title = params[index].getImageFileTitle();
                RequestBody photo = RequestBody.create(MediaType.parse("image/jpeg"), file);
                RequestBody body = new MultipartBuilder()
                        .type(MultipartBuilder.FORM)
                        .addFormDataPart("photo", params[index].getImageFileTitle(), photo)
                        .build();

                Call<List<ImageFile>> call = service.upload(body);
                call.enqueue(new Callback<List<ImageFile>>() {
                    @Override
                    public void onResponse(Response<List<ImageFile>> response, Retrofit retrofit) {
                        for (int i = 0; i < response.body().size(); i++) {
                            ImageFile img = response.body().get(i);
                            ClothingArticle ca = new ClothingArticle(0);
                            ca.ClosetID = 1;
                            Random r = new Random();
                            ca.StyleTypeID = r.nextInt(3-1+1) + 1;
                            ca.SubStyleTypeID = 2;
                            ca.MaterialID = 1;
                            ca.ColorID = 3;
                            ca.ImageFileID = img.getImageFileID();
                            ca.DateCreated = img.getDateCreated();
                            ca.LastModified = img.getLastModified();
                            ca.UpdatedByID = img.getUpdatedByID();
                            ca.Active = img.getActive();
                            ClosetService service = ServiceGenerator.createService(ClosetService.class);
                            Call<ClothingArticle> call = service.SaveClothingArticle(ca.ClothingArticleID,
                                    ca.ClosetID,
                                    img.getImageFileID(),
                                    ca.StyleTypeID,
                                    ca.SubStyleTypeID,
                                    ca.MaterialID,
                                    ca.ColorID);
                            try {
                                Response<ClothingArticle> cloth = call.execute();
                                if (cloth != null) {

                                }
                            } catch (Exception ex) {

                            }

                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("Upload", t.getMessage());
                    }
                });
            }
            return uploadCount;
        }

        @Override
        protected void onPostExecute(Integer uploadCount) {
            UploadErrorCode errorCode = UploadErrorCode.OK;
            if (uploadCount == 0)
                errorCode = UploadErrorCode.Failed;
            else if (uploadCount < totalCount)
                errorCode = UploadErrorCode.PartlySuccessful;
        }
    }
}